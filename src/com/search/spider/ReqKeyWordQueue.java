package com.search.spider;

import java.util.LinkedList;

public class ReqKeyWordQueue
{
	/**超链接队列*/
    public static LinkedList<String> KeyWordQueue = new LinkedList<String>();
         
    /**队列中对应最多的超链接数量*/
    public static final int MAX_SIZE = 10000; 
         
    public synchronized static void addElem(String KeyWord) 
    { 
    	KeyWordQueue.add(KeyWord); 
    } 
         
    public synchronized static String outElem() 
    { 
        return KeyWordQueue.removeFirst(); 
    } 
         
    public synchronized static boolean isEmpty() 
    { 
        return KeyWordQueue.isEmpty(); 
    } 
         
    public  static int size() 
    { 
        return KeyWordQueue.size(); 
    } 
         
    public  static boolean isContains(String KeyWord) 
    { 
        return KeyWordQueue.contains(KeyWord); 
    } 
}
