package com.ntsky.common;

import java.io.*;
//����һ����CodeFilter
public class CodeFilter{
	public CodeFilter(){
	}
	//�����ַ�תΪHtml
	public static String toHtml(String s){
		if(s == null){
			s = "";
			return s;
		}
		s = Replace(s.trim(),"&","&amp;");
        s = Replace(s.trim(),"<","&lt;");
		s = Replace(s.trim(),">","&gt;");
		s = Replace(s.trim(),"\t","    ");
		s = Replace(s.trim(),"\r\n","\n");
		s = Replace(s.trim(),"\n","<br>");
		s = Replace(s.trim(),"  "," &nbsp;");
		s = Replace(s.trim(),"'","&#39;");
		s = Replace(s.trim(),"\\","&#92;");
		return s;
    }
    /**
     * ubb
     */
    public static String toUbbHtml(String s){
        if(s == null){
            s = "";
            return s;
        }
        s = Replace(s,"\n","<br>");
        s = Replace(s,"  "," &nbsp;");
        s = Replace(s,"'","&#39;");
        s = Replace(s,"\\","&#92;");
        return s;
    }

	//��
    public static String unHtml(String s){
		s = Replace(s,"<br>","\n");
		s = Replace(s,"&nbsp;"," ");
		return s;
  	}

  //Replace
   public static String Replace(String source,String oldString,String newString){

		StringBuffer output = new StringBuffer();

		int lengthOfsource = source.length();//Դ�ַ�������
		int lengthOfold = oldString.length();//���ַ�������

		int posStart = 0;//��ʼ����λ��
		int pos;//�����������ַ�����λ��

		//source.indexOf(oldString,posStart)����ĳ�Ӵ����ַ���postStart�Ժ��һ�γ��ֵ�λ��,���δ�ҵ��ͷ���һ��-1��
		while((pos = source.indexOf(oldString,posStart)) >= 0){//�õ��ַ�����λ��(eg:�����<br>��ִ�У�û�о���������Ҫ����)

			//����posStart��ʼ��pos-1����֮������ݿ�������һ���ַ����С���ΪposStar��0��ʼ�ġ�
			output.append(source.substring(posStart,pos));//append�������ı���ӵ���ǰStringBuffer�������ݵĽ�β��
			output.append(newString);//�滻�����ַ���

			posStart = pos + lengthOfold;//λ��Ҳ��Ϊ�ҵ���֮���λ��,posΪ�õ���һ�γ����ַ���λ�ã�lengtholdΪ�ַ��ĳ���

		}

		if(posStart < lengthOfsource){
			//source.substring(posStart)��lengthOfsource��ʼ���ַ�����������һ���ַ�����
	        output.append(source.substring(posStart));
		}
		//���������������ת����һ�����Ա�����������ַ������������������Ӧ���ı�������������ݴ洢��
		return output.toString();
   }
 }
