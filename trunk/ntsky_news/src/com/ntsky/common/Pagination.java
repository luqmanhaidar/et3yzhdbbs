package com.ntsky.common;

import java.sql.*;
import java.math.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.ntsky.persistence.*;

public class  Pagination{
	private String strPage = null;//page参数变量
	private int curPages;//page参数内部的值
	private int m_rows; //设置每页显示的页数
	private int pages;//总页数

	//取得记录值
	public String strPage(HttpServletRequest request, String page){
	   try{
			strPage = request.getParameter(page);
       }
       catch(Exception e){
			System.out.println("delcolumn"+e.getMessage());
	   }
       return strPage;
    }
	//页面数
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
	//传递每页显示的纪录
	public void setRows(int rows){
		m_rows=rows;
	}
	public int getPages(int rowcounts){
		int test;//变量
		test=rowcounts%m_rows;//取得余数
		if(test==0)
			pages = rowcounts/m_rows;
        else
			pages=rowcounts/m_rows+1;
		return pages;
	}
    /**
     * 文章发布分页
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
     * 留言分页
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
     * 用户分页usr
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
