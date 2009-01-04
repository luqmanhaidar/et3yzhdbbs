package com.gdie.util;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.gdie.db.Table;
import com.gdie.gdjrb.Module;

public class SelectUtil {
	private static Logger log = Logger.getLogger(SelectUtil.class);

	public static String getModule(int mid) {
		String thisName = "SelectUtil.getModule";
		String str = "<select name='mid'><option value=''></option>";
		ResultSet rs=null;
		try {
			rs = Table
					.getRecordsBySql("select * from j_module where IPARENTID="
							+ mid);

			int id;
			String name;
			while (rs.next()) {
				id = rs.getInt("IMODULEID");
				name = rs.getString("SMODULENAME");

				str = str + "<option value='" + id + "'>" + name + "</option>";
			}
			str = str + "</select>";

		} catch (Exception e) {
			str = "";
			log.error(thisName + " Exception:" + e.getMessage());
		}finally{
			if(rs!=null) Table.close(rs);
		}
		return str;
	}

	public static String getPopedom(int tid) {
		String thisName = "SelectUtil.getPopedom";
		String str = "<select name='tid'><option value=''></option>";
		ResultSet rs=null;
		try {
			rs = Table.getRecordsBySql("select * from popedomtype");

			int id;
			String name;
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("type");

				str = str + "<option value='" + id + "' "+(tid==id?"selected":"")+">" + name + "</option>";
			}
			str = str + "</select>";
			Table.close(rs);

		} catch (Exception e) {
			if(rs!=null) Table.close(rs);
			str = "";
			log.error(thisName + " Exception:" + e.getMessage());
		}
		return str;
	}

	public static String getModuleOptionByIdentify(String uid,String identify){
		String thisName = "SelectUtil.getModuleByIdentify";
		String str = "";
		String sql="";
		String mid=Module.getMidByIdentify(identify);
		ResultSet rs=null;
		try {
			
			if(Module.hasSon(Integer.parseInt(mid))){
				sql="select * from j_module where iparentid="+mid;
			}else{
				sql="select * from j_module where IMODULEID="+mid;
			}			
			rs = Table.getRecordsBySql(sql);
			int id;
			String name;
			String identifyTemp;
			while (rs.next()) {
				id = rs.getInt("IMODULEID");
				name = rs.getString("SMODULENAME");
				identifyTemp=rs.getString("identify");
				
				boolean flag=Popedom.validatePop(uid, identifyTemp, "EDIT");
				
				if(flag)				
					str = str + "<option value='" + id + "'>" + name + "</option>";
			}
			

		} catch (Exception e) {
			str = "";
			log.error(thisName + " Exception:" + e.getMessage());
		}finally{
			if(rs!=null) Table.close(rs);
		}
		return str;
	}
	
	/**
	 * 取得全部栏目，减去有子栏目的，减去没有权限的
	 * @param uid
	 * @param identify
	 * @return
	 */
	public static String getModulesByUid(String uid){
		String thisName = "SelectUtil.getModulesByUid";
		String str = "";
		String sql="";
		ResultSet rs=null;
		try {
			
			sql="select * from j_module";
				
			rs = Table.getRecordsBySql(sql);
			int id;
			String name;
			String identifyTemp;
			while (rs.next()) {
				id = rs.getInt("IMODULEID");
				name = rs.getString("SMODULENAME");
				identifyTemp=rs.getString("identify");
				
				if(!Module.hasSon(id)){
					if(Popedom.validatePop(uid, identifyTemp, "EDIT")){
						str = str + "<option value='" + id + "'>" + name + "</option>";						
					}					
				}
				
								
					
			}
			

		} catch (Exception e) {
			str = "";
			log.error(thisName + " Exception:" + e.getMessage());
		}finally{
			if(rs!=null) Table.close(rs);
		}
		return str;
	}
	
	
	
}
