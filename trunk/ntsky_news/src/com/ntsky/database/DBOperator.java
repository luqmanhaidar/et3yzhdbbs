package com.ntsky.database;

import java.sql.*;
/**
 * �˴���������������
 * �������ڣ�(2003-8-10 9:08:53)
 * @author��Administrator
 */
public abstract class DBOperator {
    public abstract void Close();//�ر����ݿ�����
    public abstract void prepareStatement(String strSql);//�������ݿ�����Ķ���
    public abstract void executeUpdate();//ִ�и���(��������
    public abstract ResultSet executeQuery();//ִ�в�ѯ(������)
    public abstract void executeUpdate(String strSql);//ִ��Sql������DataSet
    //ִ��Sql��䣬û�з���ֵ
    public abstract  ResultSet executeQuery(String strSql);//ִ��Sql������DataSet
    public abstract Connection getConnection();
}
