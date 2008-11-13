/*
 * 创建日期 2005-5-3
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.ntsky.bbs.webfeed;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import java.util.*;
import java.util.HashSet;

public class Test {

    public static void main(String[] args) {
        Map map = new HashMap();
        int i=0;
        long start = System.currentTimeMillis();
        for(i=0;i<100000;i++){
            map.put(String.valueOf(i),String.valueOf(i));    
        }
        long end = System.currentTimeMillis();
        
        System.out.println("1 : " + (end-start));
        
        long start1 = System.currentTimeMillis();
        System.out.println(map.get("40000"));
        long end1 = System.currentTimeMillis();
        System.out.println("2 : " + (end1-start1));
    
        Hashtable hashtable = new Hashtable();
        long start2 = System.currentTimeMillis();
        for(i=0;i<100000;i++){
            hashtable.put(String.valueOf(i),String.valueOf(i));    
        }
        long end2 = System.currentTimeMillis();   
        System.out.println("3"+(end2-start2));
        
        
        
        long start4 = System.currentTimeMillis();
        hashtable.put("safd","cad");
        long end4 = System.currentTimeMillis();  
        System.out.println("end4-start4 = " + (end4-start4));
        Set set = new HashSet();
        
        
        
        long start3 = System.currentTimeMillis();
        for(i=0;i<10000;i++){
            set.add(String.valueOf(i));    
        }
        long end3 = System.currentTimeMillis();
        System.out.println("end3-start3 = " + (end3-start3));
    }
}
