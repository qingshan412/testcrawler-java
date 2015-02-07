package com.search.spider;

import java.sql.SQLException;
import java.util.Scanner;
//import com.sreach.spider.UrlDataHanding;  
//import com.sreach.spider.UrlQueue;


public class Test
{
	public static void main(String[] args)throws SQLException
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Please give the keyword：");
		String ReqKeyWord = sc.nextLine();
		sc.close();
		
		/*String url = "http://en.wikipedia.org/wiki/Bilibili";  
	    String url1 = "https://www.google.com/search?hl=en&q=bilibili";
	    //"https://www.google.com/?gws_rd=ssl#newwindow=1&q="
	    //acfun
	    //"https://www.google.com/?gws_rd=ssl#newwindow=1&q=bilibili";  
	    //"http://ajax.googleapis.com/ajax/services/search/web?v=1.0&rsz=large&q="
	    String url2 = "https://www.google.com/imghp?hl=en&tab=wi&ei=HobFVLulIpauyASJuoLQBg&ved=0CAQQqi4oAg";  
	    
	           
	    UrlQueue.addElem(url);
	    UrlQueue.addElem(url1);
	    UrlQueue.addElem(url2);*/
	    String url3 = "https://www.google.com/search?site=&tbm=isch&source=hp&biw=1855&bih=875&q=" + ReqKeyWord;  
	    UrlQueue.addElem(url3);       
	           
	    //UrlDataHanding[] url_Handings = new UrlDataHanding[4];  
	    UrlDataHanding url_Handings = new UrlDataHanding();  
	           
	    new Thread(url_Handings).start();
	    //for(int i = 0 ; i < 4 ; i++)  
	    //{
	    	//url_Handings[i] = new UrlDataHanding();
	        //new Thread(url_Handings[i]).start();
	    //}  
	     
	}  
}