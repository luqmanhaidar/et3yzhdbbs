package com.ntsky.pool;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 数据库连接池</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class DBConnectionManager {
    static private DBConnectionManager instance; //唯一实例的引用
    static private int clients; //客户端的连接数目
    private Vector drivers = new Vector();
    private Hashtable pools = new Hashtable(); //创建一个Hashtable对象

    class DBConnectionPool { //定义一个DBConnectionPool类[如果DBConnectionManager要访问DBConnectionPool必须创建DBConnectionPool的对象]

        private int checkedOut; //判断是否还有可用的连接
        private Vector freeConnections = new Vector();//创建一个freeConnections的数组
        private int maxConn; //最大连接数
        private String name; //数据库连接池名
        private String user; //访问数据库用户名
        private String password; //访问数据库密码
        private String URL; //数据库地址
        private PrintWriter log; //日志
        /**
         * 配置文件各项参数
         * @param name
         * @param url
         * @param user
         * @param password
         * @param maxConn
         */
        public DBConnectionPool(String name, String url, String user,
                                String password, int maxConn) {
            /**
             *配置文件
             */
            this.name = name; //poolname 连接池名
            this.URL = url; //地址
            this.user = user; //用户名
            this.password = password; //密码
            this.maxConn = maxConn; //最大的连接数
            //写日志
            this.log = new PrintWriter(System.err);
            Debug.writeLog("poolname" + this.name); //7、"poolname" + Connection
            Debug.writeLog("URL: " + this.URL); //8、jdbc:mysql://127.0.0.1:3306/nt
            Debug.writeLog("user: " + this.user); //9、user: skyyjl
            Debug.writeLog("password: " + this.password); //10、password: abc
            Debug.writeLog("maxConn: " + this.maxConn); //11、maxConn: 100
        }

        /**
         * 把空闲的连接登记到连接池由的freeConnection()方法实现
         * 它的参数为返回给连接池的连接对象。该对象被加入到freeConnections向量的末尾，然后减少已使用连接计数。
         * 调用notifyAll()是为了通知其它正在等待可用连接的线程。
         * @param Conn
         */
        public synchronized void freeConnection(Connection Conn) {
            freeConnections.addElement(Conn);
            checkedOut--;
            notifyAll();
        }

        /**
         * 如连接池中存在可用连接，则直接返回，否则创建新的连接并返回。
         * 如果没有可用连接且已有连接总数等于最大限制数，第一个方法将直接返回null，
         * 而第二个方法将等待直到有可用连接为止
         * 所有的可用连接对象均登记在名为freeConnections的向量（Vector）中。
         * 如果向量中有多于一个的连接，getConnection()总是选取第一个。
         * 由于新的可用连接总是从尾部加入向量，从而使得数据库连接由于长时间闲置而被关闭的风险减低到最小程度。
         *
         * @return Connection
         */
        public synchronized Connection getConnection() {
            Connection con = null;
            if (freeConnections.size() > 0) { //获取向量中第一个可用连接
                con = (Connection) freeConnections.firstElement(); //创建第一个connection，并用设置为Connection
                freeConnections.removeElementAt(0);
                try {
                    /**
                     * 第一个getConnection()在返回可用连接给客户程序之前，调用了isClosed()方法验证连接仍旧有效。
                     * 如果该连接被关闭或触发异常，getConnection()递归地调用自己以尝试获取另外的可用连接。
                     */
                    if (con.isClosed()) { //判断连接是否关闭
                        Debug.writeLog("从连接池" + name + "删除一个无效连接");
                        con = getConnection(); //递归调用自己,尝试再次获取可用连接
                    }
                }
                catch (SQLException e) {
                    Debug.writeLog("从连接池" + name + "删除一个无效连接"); // 递归调用自己,尝试再次获取可用连接
                    con = getConnection();
                }
            }
            /**
             * 如果在向量freeConnections中不存在任何可用连接，getConnection()方法检查是否已经指定最大连接数限制。
             * 如已经指定，则检查当前连接数是否已经到达极限。此处maxConn为0表示没有限制。
             * 如果没有指定最大连接数限制或当前连接数小于该值，该方法尝试创建新的连接。
             * 如创建成功，则增加已使用连接的计数(checkout)并返回，否则返回空值。
             */
            else if (maxConn == 0 || checkedOut < maxConn) {
                con = newConnection(); //创建连接(数量由maxConn决定)
            }
            if (con != null) { //如果conn(连接数)不为空，判断就加
                checkedOut++;
            }
            if (con == null) { //如果为空就将con为空写入日志
                Debug.writeLog(
                    "DBConnectionPool getConnection(), The Returned Con is null");
            }
            return con; //返回连接
        }

        /**
         * 用一个以毫秒为单位的时间参数，该参数表示客户程序能够等待的最长时间。
         * 建立连接的具体操作仍旧由第一个getConnection()方法实现。
         * 参见前一个getConnection()方法.
         * @param timeout 以毫秒计的等待时间限制
         */
        public synchronized Connection getConnection(long timeout) {
            long startTime = new java.util.Date().getTime(); //连接的初始时间
            Connection con;
            /**
             * 在while循环中尝试获得一个连接。如果失败，则以给定的时间值为参数调用wait()。
             * wait()的返回可能是由于其它线程调用notify()或notifyAll()，也可能是由于预定时间已到。
             * 为找出wait()返回的真正原因，程序用当前时间减开始时间（startTime），
             * 如差值大于预定时间则返回空值，否则再次调用getConnection()。
             */
            while ( (con = getConnection()) == null) {
                try {
                    wait(timeout);
                }
                catch (InterruptedException e) {

                }
                if ( (new java.util.Date().getTime() - startTime) >= timeout) { //wait()返回的原因是超时
                    return null; //连接超时,释放掉连接
                }
            }
            return con; //返回连接
        }

        private void log(String msg) {
            log.println(new java.util.Date() + ": " + msg);
        }

        /**
         * 将文本信息与异常写入日志文件
         */
        private void log(Throwable e, String msg) {
            log.println(new java.util.Date() + ": " + msg);
            e.printStackTrace(log);
        }

        /**
         * 创建新连接由newConnection()方法实现。
         * 创建过程与是否已经指定数据库帐号、密码有关。
         * JDBC的DriverManager类提供多个getConnection()方法，这些方法要用到JDBC URL与其它一些参数，如用户帐号和密码等。
         * DriverManager将使用指定的JDBC URL确定适合于目标数据库的驱动程序及建立连接。
         */
        private Connection newConnection() {
            Connection con = null;
            try {
                if (user == null) {
                    con = DriverManager.getConnection(URL); //保持数据库连接加载数据库驱动程序
                }
                else {
                    con = DriverManager.getConnection(URL, user, password);
                }
                Debug.writeLog("连接池" + name + "创建一个新的连接"); //21、如果有用户连接就写入日志说明有用户创建了连接
            }
            catch (SQLException e) {
                Debug.writeLog("无法创建下列URL的连接: " + URL);
                e.printStackTrace(System.out);
                return null;
            }
            if (con == null) {
                Debug.writeLog(
                    "DBConnectionPool newConnection(), The Returned Con is null");
            }
            return con;
        }

        /**
         * 实现的release()方法供DBConnectionManager调用。
         * 该方法遍历freeConnections向量并关闭所有连接，然后从向量中删除这些连接。
         */
        public synchronized void release() {
            Enumeration allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) { //freeConnections对象存在
                Connection con = (Connection) allConnections.nextElement(); //取出所有的数组
                try {
                    con.close(); //尝试关闭
                    Debug.writeLog("关闭连接池" + name + "中的一个连接");
                }
                catch (SQLException e) {
                    Debug.writeLog("无法关闭连接池" + name + "中的连接");
                    e.printStackTrace(System.out);
                }
            }
            freeConnections.removeAllElements(); //清空
        }
    }

    /**
     * *****************************************************************
     * 建构函数私有以防止其它对象创建本类实例
     * 其它对象能够调用其静态方法（也称为类方法）获得该唯一实例的引用
     * DBConnectionManager类的建构函数是私有的，这是为了避免其它对象创建该类的实例。
     */
    private DBConnectionManager() {
        init();
    }

    /**
     *
     * @param props 连接池属性
     */
    private void createPools(Properties props) {
        Enumeration propNames = props.propertyNames(); //创建枚举对象----该对象可以想象为一个元素系列，逐次调用其nextElement()方法将顺序返回各元素
        while (propNames.hasMoreElements()) { //如果有枚举对象存在
            String name = (String) propNames.nextElement(); //取出值格式为字符串
            Debug.writeLog("createPools(Properties), name is:  " + name); //3、logPath 将路径名写入日志
            /**
             * 在其中搜索名字以“.url”结尾的属性
             * 对于每一个符合条件的属性，先提取其连接池名字部分，进而读取所有属于该连接池的属性
             */
            if (name.endsWith(".url")) {
                Debug.writeLog("createPools(Properties), name end with url"); //5
                String poolName = name.substring(0, name.lastIndexOf(".")); //取出“.”之前的字符串(连接池的名字)
                String url = props.getProperty(poolName + ".url"); //4、Connection.url 取得URL
                Debug.writeLog("createPools(Properties), url is " + url); //6、jdbc:mysql://127.0.0.1:3306/nt 写入数据库的url"jdbc:mysql://127.0.0.1:3306/数据名"
                if (url == null) { //如果URL为空，写入日志显示没有找到指定的URL
                    Debug.writeLog("没有为连接池" + poolName + "指定URL");
                    continue; //跳出本次循环
                }
                String user = props.getProperty(poolName + ".user"); //用户名
                String password = props.getProperty(poolName + ".password"); //密码
                String maxconn = props.getProperty(poolName + ".maxconn", "0"); //最大连接数
                int max; //定义一个整形变量
                try {
                    max = Integer.valueOf(maxconn).intValue(); //取得最大连接数的值
                }
                catch (NumberFormatException e) {
                    Debug.writeLog("错误的最大连接数限制: " + maxconn + " .连接池: " +
                                   poolName);
                    max = 0;
                }
                //创建连接池对象并把它保存在实例变量pools中
                DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
                //散列表（Hashtable类 ）pools实现连接池名字到连接池对象之间的映射，此处以连接池名字为键，连接池对象为值。
                pools.put(poolName, pool);
                Debug.writeLog("成功创建连接池" + poolName); //12、成功创建连接池Connection
            }
        }
    }

    /**
     * 将连接对象返回给由名字指定的连接池
     * @param name 在属性文件中定义的连接池名字
     * @param con 连接对象
     */
    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            pool.freeConnection(con);
        }
    }

    /**
     * 获得一个可用的(空闲的)连接.如果没有可用连接,且已有连接数小于最大连接数
     * 限制,则创建并返回新连接
     * @param name 在属性文件中定义的连接池名字
     * @return Connection 可用连接或null
     */
    public Connection getConnection(String name) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            Debug.writeLog(
                "DBConnectionManager getConnection(String) ! pool is not null !"); //20
            return pool.getConnection();
        }
        return null;
    }

    /**
     * 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制,
     * 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
     * @param name 连接池名字
     * @param time 以毫秒计的等待时间
     * @return Connection 可用连接或null
     */
    public Connection getConnection(String name, long time) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }
        return null;
    }

    /**
     * 其引用就一直保存在静态变量instance中
     * 每次调用getInstance()都增加一个DBConnectionManager的客户程序计数。
     * 该计数代表引用DBConnectionManager唯一实例的客户程序总数，它将被用于控制连接池的关闭操作。
     * @return 返回唯一实例.如果是第一次调用此方法,则创建实例
     */
    public static synchronized DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        clients++; //对客户端的访问计数
        return instance;
    }

    /**
     * 读取属性完成初始化
     */
    private void init() {
        //[配置文件路径（ISiteEnvironment.ConfigFile）]调用EnvironmentConfig的getProperties()方法
        Properties dbProps = EnvironmentConfig.getInstance().getProperties(
            ISiteEnvironment.ConfigFile);
        loadDrivers(dbProps); //装载驱动
        createPools(dbProps); //调用私有方法createPools()创建连接池对象
    }

    /**
     * 装载和注册所有JDBC驱动程序
     * 该方法先用StringTokenizer将drivers属性值分割为对应于驱动程序名称的字符串
     *
     * @param props 属性
     */
    private void loadDrivers(Properties props) {
        String driverClasses = props.getProperty("drivers");
        StringTokenizer st = new StringTokenizer(driverClasses);
        while (st.hasMoreElements()) {
            String driverClassName = st.nextToken().trim(); //取得驱动程序org.gjt.mm.mysql.Driver
            try {
                Driver driver = (Driver) Class.forName(driverClassName).
                    newInstance();
                DriverManager.registerDriver(driver);
                Debug.writeLog("Load Driver Success !"); //1、加载驱动成功
                drivers.addElement(driver);
                Debug.writeLog("成功注册JDBC驱动程序" + driverClassName); //2、加载JDBC驱动程序org.gjt.mm.mysql.Driver
            }
            catch (Exception e) {
                Debug.writeLog("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " +  e);
            }
        }
    }

    /**
     * 客户程序在关闭时调用release()可以递减该计数。
     * 当最后一个客户程序调用release()，递减后的引用计数为0，就可以调用各个连接池的release()方法关闭所有连接了。
     * 关闭所有连接,撤销驱动程序的注册
     */
    public synchronized void release() { //等待直到最后一个客户程序调用
        if (--clients != 0) {
            return;
        }
        Enumeration allPools = pools.elements();
        while (allPools.hasMoreElements()) {
            DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
            pool.release();
        }
        Enumeration allDrivers = drivers.elements();
        while (allDrivers.hasMoreElements()) {
            Driver driver = (Driver) allDrivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                Debug.writeLog("撤销JDBC驱动程序 " + driver.getClass().getName() + "的注册");
            }
            catch (SQLException e) {
                Debug.writeLog("无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
                e.printStackTrace(System.out);
            }
        }
    }
}
