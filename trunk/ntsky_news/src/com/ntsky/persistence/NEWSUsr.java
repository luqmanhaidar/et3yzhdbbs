package com.ntsky.persistence;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: �û���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class NEWSUsr {

    public String userName=null;
    public String passWd=null;
    public String pwdAgain=null;
    public int sex;
    public String question=null;
    public String answer=null;
    public String emailAddr=null;
    public String qq=null;
    public String http=null;
    public int purview;

    //�û���
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return userName;
    }
    //����
    public void setPassWd(String passWd){
        this.passWd=passWd;
    }
    public String getPassWd(){
        return passWd;
    }
    //�ظ�����
    public void setPwdAgain(String pwdAgain){
        this.pwdAgain=pwdAgain;
    }
    public String getPwdAgain(){
        return pwdAgain;
    }
    //�Ա�
    public void setSex(int sex){
        this.sex=sex;
    }
    public int getSex(){
        return sex;
    }
    //����
    public void setQuestion(String question){
        this.question=question;
    }
    public String getQuestion(){
        return question;
    }
    //��
    public void setAnswer(String answer){
        this.answer=answer;
    }
    public String getAnswer(){
        return answer;
    }
    //E-mail
    public void setEmailAddr(String emailAddr){
        this.emailAddr=emailAddr;
    }
    public String getEmailAddr(){
        return emailAddr;
    }
    //qq
    public void setQq(String qq){
        this.qq=qq;
    }
    public String getQq(){
        return qq;
    }
    //HTTP
    public void setHttp(String http){
        this.http=http;
    }
    public String getHttp(){
        return http;
    }
    //puview(Ȩ��)
    public void setPurview(int purview){
        this.purview=purview;
    }
    public int getPurview(){
        return purview;
    }
}
