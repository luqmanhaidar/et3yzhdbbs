package com.ntsky.bbs.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ntsky.framework.util.xml.SAXHelper;

import com.ntsky.bbs.NTskyTest;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.config.EmailConfig;
import com.ntsky.bbs.xml.EmailHandler;
import com.ntsky.bbs.exception.XMLException;
import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.xml.bean.Email;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.domain.Announcement;


public class BeanUtilTest extends NTskyTest{

	public void setUp() throws Exception {
		super.setUp();
		
		EditorUtil.setEditorMap();
		
	}
	
	public void testFormatObject(){
		Announcement announcement = new Announcement();
		announcement.setTitle("<script>alert('ttt')</script>");
		announcement.setContent("<script>alert('aaa')</script>");
		BeanUtil.format(announcement);
		
		logger.debug("title = " + announcement.getTitle());
		logger.debug("content = " + announcement.getContent());
		
 	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanUtilTest.class);
	}	
	
}
