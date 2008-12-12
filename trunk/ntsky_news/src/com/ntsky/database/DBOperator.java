package com.ntsky.database;

import java.sql.*;
/**
 * 此处插入类型描述。
 * 创建日期：(2003-8-10 9:08:53)
 * @author：Administrator
 */
public abstract class DBOperator {
    public abstract void Close();//关闭数据库连接
    public abstract void prepareStatement(String strSql);//创建数据库操作的对象
    public abstract void executeUpdate();//执行更新(带参数）
    public abstract ResultSet executeQuery();//执行查询(带参数)
    public abstract void executeUpdate(String strSql);//执行Sql，返回DataSet
    //执行Sql语句，没有返回值
    public abstract  ResultSet executeQuery(String strSql);//执行Sql，返回DataSet
    public abstract Connection getConnection();
}
