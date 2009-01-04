package com.gdie.gdjrb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.gdie.db.DBPool;
import com.gdie.db.Table;

public class Expert {

	private static Logger log = Logger.getLogger(Information.class);

	private int id;

	private String name;

	private String sex;

	private String tel;

	private String email;

	private String photo;

	private String content;// 详细介绍

	private String position;// 职位

	private String describe;// 职位描述

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	private void setRequestParameter(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		if (id != null && !id.trim().equals(""))
			this.id = Integer.parseInt(id);

		this.name = request.getParameter("name");

		this.content = request.getParameterValues("cinfo")[0];

		this.describe = request.getParameter("describe");

		this.email = request.getParameter("email");

		this.photo = request.getParameter("photo");

		this.position = request.getParameter("position");

		this.sex = request.getParameter("sex");

		this.tel = request.getParameter("tel");

	}

	public boolean add(PageContext pageContext) {
		String thisName = this.getClass().getName() + ".add()";
		boolean bResult = false;

		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			this.setRequestParameter(request);

			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "insert into expert(name,sex,tel,email,photo,content,position,des) values(?,?,?,?,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, this.name);
				stmt.setString(2, this.sex);
				stmt.setString(3, this.tel);
				stmt.setString(4, this.email);
				stmt.setString(5, this.photo);
				stmt.setString(6, this.content);
				stmt.setString(7, this.position);
				stmt.setString(8, this.describe);

				stmt.executeUpdate();
				stmt.close();
				con.commit();
				bResult = true;
			} catch (Exception e) {
				con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}

	public boolean del(String id) {
		String thisName = this.getClass().getName() + ".del()";
		boolean bResult = false;
		System.out.println(id);
		try {
			this.id = Integer.parseInt(id);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				con.setAutoCommit(false);
				String sql = "DELETE FROM expert WHERE id=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, this.id);
				stmt.executeUpdate();
				stmt.close();
				con.commit();
				bResult = true;
			} catch (Exception e) {
				con.rollback();
				System.out.println("dfdf");
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}

	public boolean findRecord(int id) {
		String thisName = this.getClass().getName() + ".findRecord()";
		boolean bResult = false;

		try {
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				String sql = "select * from expert where id=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {

					this.id = rs.getInt("id");

					this.name = rs.getString("name");

					this.content = rs.getString("content");

					this.describe = rs.getString("des");

					this.email = rs.getString("email");

					this.photo = rs.getString("photo");

					this.position = rs.getString("position");

					this.sex = rs.getString("sex");

					this.tel = rs.getString("tel");
					bResult = true;
				}

				rs.close();
				stmt.close();
			} catch (Exception e) {
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}

	public boolean edit(PageContext pageContext) {
		String thisName = this.getClass().getName() + ".edit()";
		boolean bResult = false;

		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			this.setRequestParameter(request);
			System.out.println(this.name);
			Connection con = null;
			try {
				DBPool dbpool = new DBPool();
				con = dbpool.getConnection();
				//con.setAutoCommit(false);
				String sql = "update expert set name=?, sex=?, tel=?, email=?, photo=?, content=?, position=?, des=? where id=?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1,this.getName() );
				stmt.setString(2,this.getSex() );
				stmt.setString(3,this.getTel() );
				stmt.setString(4,this.getEmail() );
				stmt.setString(5,this.getPhoto() );
				stmt.setString(6,this.getContent() );
				stmt.setString(7,this.getPosition() );
				stmt.setString(8,this.getDescribe() );
				stmt.setInt(9, this.getId());
				stmt.executeUpdate();
				stmt.close();
				//con.commit();
				bResult = true;
			} catch (Exception e) {
				//con.rollback();
				log.error(thisName + " Exception:" + e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
		} catch (Exception eb) {
			log.error(thisName + " Exception b:" + eb.getMessage());
		}

		return bResult;
	}
}
