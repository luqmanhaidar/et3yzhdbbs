/* * @(#)log4JServlet.java 1.0 2006-1-24
 *
 * Copyright 2005, Guangdong Information & Engineering Co. Ltd.
 * GDIE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gdie.common;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.PropertyConfigurator;

public class log4JServlet extends HttpServlet {
    /**
     * <code>serialVersionUID</code> µÄ×¢ÊÍ
     */
    private static final long serialVersionUID = 01241026L;

    public void init() throws ServletException{
    	ServletContext sct = getServletContext();
    	System.out.println("[Log4j]: The Root Path: " + sct.getRealPath("/"));
    	System.out.println("[Log4j]: InitServlet init start...");
    	PropertyConfigurator.configure(sct.getRealPath("/")
    			+ getServletConfig().getInitParameter("log4j"));
    	System.out.println("[Log4j]: InitServlet init over.");
    }
}
