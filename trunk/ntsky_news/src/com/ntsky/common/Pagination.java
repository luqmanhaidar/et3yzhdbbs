package com.ntsky.common;

import java.sql.*;
import java.math.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.ntsky.persistence.*;

public class  Pagination{
	private String strPage = null;//page��������
	private int curPages;//page�����ڲ���ֵ
	private int m_rows; //����ÿҳ��ʾ��ҳ��
	private int pages;//��ҳ��

	//ȡ�ü�¼ֵ
	public String strPage(HttpServletRequest request, String page){
	   try{
			strPage = request.getParameter(page);
       }
       catch(Exception e){
			System.out.println("delcolumn"+e.getMessage());
	   }
       return strPage;
    }
	//ҳ����
	public int curPages(String strPage){
		try{
			if(strPage == null){
				curPages = 1;
			}
			else{
				curPages = Integer.parseInt(strPage);
				if(curPages < 1)
					curPages = 1;
			}
		}
		catch(Exception e){
			System.out.print("curPages");
		}
		return curPages;
	}
	//����ÿҳ��ʾ�ļ�¼
	public void setRows(int rows){
		m_rows=rows;
	}
	public int getPages(int rowcounts){
		int test;//����
		test=rowcounts%m_rows;//ȡ������
		if(test==0)
			pages = rowcounts/m_rows;
        else
			pages=rowcounts/m_rows+1;
		return pages;
	}
    /**
     * ���·�����ҳ
     * @param rs
     * @param curPages
     * @return
     */
	public Iterator getPageSet(Iterator rs,int curPages){
		if(curPages==1){
			return rs;
		}
		else{
			int i=1;
			try{
				while(rs.hasNext()){
                    NEWSTable tableNews = (NEWSTable)rs.next();
                    i=i+1;
					if(i>((curPages-1)*m_rows))
						break;
				}
				return rs;
			}
			catch(Exception e){
				System.out.print(e.getMessage());
			}
		}
		return rs;
	}
    /**
     * ���Է�ҳ
     */
    public Iterator getPageSetNote(Iterator rs,int curPages){
        if(curPages==1){
            return rs;
        }
        else{
            int i=1;
            try{
                while(rs.hasNext()){
                    NOTEGuest tableNote = (NOTEGuest)rs.next();
                    i=i+1;
                    if(i>((curPages-1)*m_rows))
                        break;
                }
                return rs;
            }
            catch(Exception e){
                System.out.print(e.getMessage());
            }
        }
        return rs;
    }
    /**
     * �û���ҳusr
     */
    public Iterator getPageSetUsr(Iterator rs,int curPages){
        if(curPages==1){
            return rs;
        }
        else{
            int i=1;
            try{
                while(rs.hasNext()){
                    NEWSUsr tableUsr = (NEWSUsr)rs.next();
                    i=i+1;
                    if(i>((curPages-1)*m_rows))
                        break;
                }
                return rs;
            }
            catch(Exception e){
                System.out.print(e.getMessage());
            }
        }
        return rs;
    }
}
