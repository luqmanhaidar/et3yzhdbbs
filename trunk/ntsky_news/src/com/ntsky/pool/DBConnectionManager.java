package com.ntsky.pool;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ntsky.common.*;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ���ݿ����ӳ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class DBConnectionManager {
    static private DBConnectionManager instance; //Ψһʵ��������
    static private int clients; //�ͻ��˵�������Ŀ
    private Vector drivers = new Vector();
    private Hashtable pools = new Hashtable(); //����һ��Hashtable����

    class DBConnectionPool { //����һ��DBConnectionPool��[���DBConnectionManagerҪ����DBConnectionPool���봴��DBConnectionPool�Ķ���]

        private int checkedOut; //�ж��Ƿ��п��õ�����
        private Vector freeConnections = new Vector();//����һ��freeConnections������
        private int maxConn; //���������
        private String name; //���ݿ����ӳ���
        private String user; //�������ݿ��û���
        private String password; //�������ݿ�����
        private String URL; //���ݿ��ַ
        private PrintWriter log; //��־
        /**
         * �����ļ��������
         * @param name
         * @param url
         * @param user
         * @param password
         * @param maxConn
         */
        public DBConnectionPool(String name, String url, String user,
                                String password, int maxConn) {
            /**
             *�����ļ�
             */
            this.name = name; //poolname ���ӳ���
            this.URL = url; //��ַ
            this.user = user; //�û���
            this.password = password; //����
            this.maxConn = maxConn; //����������
            //д��־
            this.log = new PrintWriter(System.err);
            Debug.writeLog("poolname" + this.name); //7��"poolname" + Connection
            Debug.writeLog("URL: " + this.URL); //8��jdbc:mysql://127.0.0.1:3306/nt
            Debug.writeLog("user: " + this.user); //9��user: skyyjl
            Debug.writeLog("password: " + this.password); //10��password: abc
            Debug.writeLog("maxConn: " + this.maxConn); //11��maxConn: 100
        }

        /**
         * �ѿ��е����ӵǼǵ����ӳ��ɵ�freeConnection()����ʵ��
         * ���Ĳ���Ϊ���ظ����ӳص����Ӷ��󡣸ö��󱻼��뵽freeConnections������ĩβ��Ȼ�������ʹ�����Ӽ�����
         * ����notifyAll()��Ϊ��֪ͨ�������ڵȴ��������ӵ��̡߳�
         * @param Conn
         */
        public synchronized void freeConnection(Connection Conn) {
            freeConnections.addElement(Conn);
            checkedOut--;
            notifyAll();
        }

        /**
         * �����ӳ��д��ڿ������ӣ���ֱ�ӷ��أ����򴴽��µ����Ӳ����ء�
         * ���û�п��������������������������������������һ��������ֱ�ӷ���null��
         * ���ڶ����������ȴ�ֱ���п�������Ϊֹ
         * ���еĿ������Ӷ�����Ǽ�����ΪfreeConnections��������Vector���С�
         * ����������ж���һ�������ӣ�getConnection()����ѡȡ��һ����
         * �����µĿ����������Ǵ�β�������������Ӷ�ʹ�����ݿ��������ڳ�ʱ�����ö����رյķ��ռ��͵���С�̶ȡ�
         *
         * @return Connection
         */
        public synchronized Connection getConnection() {
            Connection con = null;
            if (freeConnections.size() > 0) { //��ȡ�����е�һ����������
                con = (Connection) freeConnections.firstElement(); //������һ��connection����������ΪConnection
                freeConnections.removeElementAt(0);
                try {
                    /**
                     * ��һ��getConnection()�ڷ��ؿ������Ӹ��ͻ�����֮ǰ��������isClosed()������֤�����Ծ���Ч��
                     * ��������ӱ��رջ򴥷��쳣��getConnection()�ݹ�ص����Լ��Գ��Ի�ȡ����Ŀ������ӡ�
                     */
                    if (con.isClosed()) { //�ж������Ƿ�ر�
                        Debug.writeLog("�����ӳ�" + name + "ɾ��һ����Ч����");
                        con = getConnection(); //�ݹ�����Լ�,�����ٴλ�ȡ��������
                    }
                }
                catch (SQLException e) {
                    Debug.writeLog("�����ӳ�" + name + "ɾ��һ����Ч����"); // �ݹ�����Լ�,�����ٴλ�ȡ��������
                    con = getConnection();
                }
            }
            /**
             * ���������freeConnections�в������κο������ӣ�getConnection()��������Ƿ��Ѿ�ָ��������������ơ�
             * ���Ѿ�ָ�������鵱ǰ�������Ƿ��Ѿ����Ｋ�ޡ��˴�maxConnΪ0��ʾû�����ơ�
             * ���û��ָ��������������ƻ�ǰ������С�ڸ�ֵ���÷������Դ����µ����ӡ�
             * �紴���ɹ�����������ʹ�����ӵļ���(checkout)�����أ����򷵻ؿ�ֵ��
             */
            else if (maxConn == 0 || checkedOut < maxConn) {
                con = newConnection(); //��������(������maxConn����)
            }
            if (con != null) { //���conn(������)��Ϊ�գ��жϾͼ�
                checkedOut++;
            }
            if (con == null) { //���Ϊ�վͽ�conΪ��д����־
                Debug.writeLog(
                    "DBConnectionPool getConnection(), The Returned Con is null");
            }
            return con; //��������
        }

        /**
         * ��һ���Ժ���Ϊ��λ��ʱ��������ò�����ʾ�ͻ������ܹ��ȴ����ʱ�䡣
         * �������ӵľ�������Ծ��ɵ�һ��getConnection()����ʵ�֡�
         * �μ�ǰһ��getConnection()����.
         * @param timeout �Ժ���Ƶĵȴ�ʱ������
         */
        public synchronized Connection getConnection(long timeout) {
            long startTime = new java.util.Date().getTime(); //���ӵĳ�ʼʱ��
            Connection con;
            /**
             * ��whileѭ���г��Ի��һ�����ӡ����ʧ�ܣ����Ը�����ʱ��ֵΪ��������wait()��
             * wait()�ķ��ؿ��������������̵߳���notify()��notifyAll()��Ҳ����������Ԥ��ʱ���ѵ���
             * Ϊ�ҳ�wait()���ص�����ԭ�򣬳����õ�ǰʱ�����ʼʱ�䣨startTime����
             * ���ֵ����Ԥ��ʱ���򷵻ؿ�ֵ�������ٴε���getConnection()��
             */
            while ( (con = getConnection()) == null) {
                try {
                    wait(timeout);
                }
                catch (InterruptedException e) {

                }
                if ( (new java.util.Date().getTime() - startTime) >= timeout) { //wait()���ص�ԭ���ǳ�ʱ
                    return null; //���ӳ�ʱ,�ͷŵ�����
                }
            }
            return con; //��������
        }

        private void log(String msg) {
            log.println(new java.util.Date() + ": " + msg);
        }

        /**
         * ���ı���Ϣ���쳣д����־�ļ�
         */
        private void log(Throwable e, String msg) {
            log.println(new java.util.Date() + ": " + msg);
            e.printStackTrace(log);
        }

        /**
         * ������������newConnection()����ʵ�֡�
         * �����������Ƿ��Ѿ�ָ�����ݿ��ʺš������йء�
         * JDBC��DriverManager���ṩ���getConnection()��������Щ����Ҫ�õ�JDBC URL������һЩ���������û��ʺź�����ȡ�
         * DriverManager��ʹ��ָ����JDBC URLȷ���ʺ���Ŀ�����ݿ���������򼰽������ӡ�
         */
        private Connection newConnection() {
            Connection con = null;
            try {
                if (user == null) {
                    con = DriverManager.getConnection(URL); //�������ݿ����Ӽ������ݿ���������
                }
                else {
                    con = DriverManager.getConnection(URL, user, password);
                }
                Debug.writeLog("���ӳ�" + name + "����һ���µ�����"); //21��������û����Ӿ�д����־˵�����û�����������
            }
            catch (SQLException e) {
                Debug.writeLog("�޷���������URL������: " + URL);
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
         * ʵ�ֵ�release()������DBConnectionManager���á�
         * �÷�������freeConnections�������ر��������ӣ�Ȼ���������ɾ����Щ���ӡ�
         */
        public synchronized void release() {
            Enumeration allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) { //freeConnections�������
                Connection con = (Connection) allConnections.nextElement(); //ȡ�����е�����
                try {
                    con.close(); //���Թر�
                    Debug.writeLog("�ر����ӳ�" + name + "�е�һ������");
                }
                catch (SQLException e) {
                    Debug.writeLog("�޷��ر����ӳ�" + name + "�е�����");
                    e.printStackTrace(System.out);
                }
            }
            freeConnections.removeAllElements(); //���
        }
    }

    /**
     * *****************************************************************
     * ��������˽���Է�ֹ�������󴴽�����ʵ��
     * ���������ܹ������侲̬������Ҳ��Ϊ�෽������ø�Ψһʵ��������
     * DBConnectionManager��Ľ���������˽�еģ�����Ϊ�˱����������󴴽������ʵ����
     */
    private DBConnectionManager() {
        init();
    }

    /**
     *
     * @param props ���ӳ�����
     */
    private void createPools(Properties props) {
        Enumeration propNames = props.propertyNames(); //����ö�ٶ���----�ö����������Ϊһ��Ԫ��ϵ�У���ε�����nextElement()������˳�򷵻ظ�Ԫ��
        while (propNames.hasMoreElements()) { //�����ö�ٶ������
            String name = (String) propNames.nextElement(); //ȡ��ֵ��ʽΪ�ַ���
            Debug.writeLog("createPools(Properties), name is:  " + name); //3��logPath ��·����д����־
            /**
             * ���������������ԡ�.url����β������
             * ����ÿһ���������������ԣ�����ȡ�����ӳ����ֲ��֣�������ȡ�������ڸ����ӳص�����
             */
            if (name.endsWith(".url")) {
                Debug.writeLog("createPools(Properties), name end with url"); //5
                String poolName = name.substring(0, name.lastIndexOf(".")); //ȡ����.��֮ǰ���ַ���(���ӳص�����)
                String url = props.getProperty(poolName + ".url"); //4��Connection.url ȡ��URL
                Debug.writeLog("createPools(Properties), url is " + url); //6��jdbc:mysql://127.0.0.1:3306/nt д�����ݿ��url"jdbc:mysql://127.0.0.1:3306/������"
                if (url == null) { //���URLΪ�գ�д����־��ʾû���ҵ�ָ����URL
                    Debug.writeLog("û��Ϊ���ӳ�" + poolName + "ָ��URL");
                    continue; //��������ѭ��
                }
                String user = props.getProperty(poolName + ".user"); //�û���
                String password = props.getProperty(poolName + ".password"); //����
                String maxconn = props.getProperty(poolName + ".maxconn", "0"); //���������
                int max; //����һ�����α���
                try {
                    max = Integer.valueOf(maxconn).intValue(); //ȡ�������������ֵ
                }
                catch (NumberFormatException e) {
                    Debug.writeLog("������������������: " + maxconn + " .���ӳ�: " +
                                   poolName);
                    max = 0;
                }
                //�������ӳض��󲢰���������ʵ������pools��
                DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
                //ɢ�б�Hashtable�� ��poolsʵ�����ӳ����ֵ����ӳض���֮���ӳ�䣬�˴������ӳ�����Ϊ�������ӳض���Ϊֵ��
                pools.put(poolName, pool);
                Debug.writeLog("�ɹ��������ӳ�" + poolName); //12���ɹ��������ӳ�Connection
            }
        }
    }

    /**
     * �����Ӷ��󷵻ظ�������ָ�������ӳ�
     * @param name �������ļ��ж�������ӳ�����
     * @param con ���Ӷ���
     */
    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            pool.freeConnection(con);
        }
    }

    /**
     * ���һ�����õ�(���е�)����.���û�п�������,������������С�����������
     * ����,�򴴽�������������
     * @param name �������ļ��ж�������ӳ�����
     * @return Connection �������ӻ�null
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
     * ���һ����������.��û�п�������,������������С���������������,
     * �򴴽�������������.����,��ָ����ʱ���ڵȴ������߳��ͷ�����.
     * @param name ���ӳ�����
     * @param time �Ժ���Ƶĵȴ�ʱ��
     * @return Connection �������ӻ�null
     */
    public Connection getConnection(String name, long time) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }
        return null;
    }

    /**
     * �����þ�һֱ�����ھ�̬����instance��
     * ÿ�ε���getInstance()������һ��DBConnectionManager�Ŀͻ����������
     * �ü�����������DBConnectionManagerΨһʵ���Ŀͻ��������������������ڿ������ӳصĹرղ�����
     * @return ����Ψһʵ��.����ǵ�һ�ε��ô˷���,�򴴽�ʵ��
     */
    public static synchronized DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        clients++; //�Կͻ��˵ķ��ʼ���
        return instance;
    }

    /**
     * ��ȡ������ɳ�ʼ��
     */
    private void init() {
        //[�����ļ�·����ISiteEnvironment.ConfigFile��]����EnvironmentConfig��getProperties()����
        Properties dbProps = EnvironmentConfig.getInstance().getProperties(
            ISiteEnvironment.ConfigFile);
        loadDrivers(dbProps); //װ������
        createPools(dbProps); //����˽�з���createPools()�������ӳض���
    }

    /**
     * װ�غ�ע������JDBC��������
     * �÷�������StringTokenizer��drivers����ֵ�ָ�Ϊ��Ӧ�������������Ƶ��ַ���
     *
     * @param props ����
     */
    private void loadDrivers(Properties props) {
        String driverClasses = props.getProperty("drivers");
        StringTokenizer st = new StringTokenizer(driverClasses);
        while (st.hasMoreElements()) {
            String driverClassName = st.nextToken().trim(); //ȡ����������org.gjt.mm.mysql.Driver
            try {
                Driver driver = (Driver) Class.forName(driverClassName).
                    newInstance();
                DriverManager.registerDriver(driver);
                Debug.writeLog("Load Driver Success !"); //1�����������ɹ�
                drivers.addElement(driver);
                Debug.writeLog("�ɹ�ע��JDBC��������" + driverClassName); //2������JDBC��������org.gjt.mm.mysql.Driver
            }
            catch (Exception e) {
                Debug.writeLog("�޷�ע��JDBC��������: " + driverClassName + ", ����: " +  e);
            }
        }
    }

    /**
     * �ͻ������ڹر�ʱ����release()���Եݼ��ü�����
     * �����һ���ͻ��������release()���ݼ�������ü���Ϊ0���Ϳ��Ե��ø������ӳص�release()�����ر����������ˡ�
     * �ر���������,�������������ע��
     */
    public synchronized void release() { //�ȴ�ֱ�����һ���ͻ��������
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
                Debug.writeLog("����JDBC�������� " + driver.getClass().getName() + "��ע��");
            }
            catch (SQLException e) {
                Debug.writeLog("�޷���������JDBC���������ע��: " + driver.getClass().getName());
                e.printStackTrace(System.out);
            }
        }
    }
}
