package com.search.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FunctionUtils 
{
	/**
	 * find the hyper link through regular expression
	 */
	private static String pat = "https://www.google\\.com/search\\?site=&tbm=isch&source=hp&biw=1855&bih=875&q=.*";
	//https://www.google\\.com/\\?gws_rd=ssl#newwindow=1&q=.*
	//https://www.google.com/search?site=&tbm=isch&source=hp&biw=1855&bih=875&q=
	//"https://www\\.google\\.come/\\?gws\\_rd=ssl#newwindow=1\\&q=acfun";	//^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$

	private static Pattern pattern = Pattern.compile(pat);
	
	private static BufferedWriter writer = null;
	
	/**
	 * search depth
	 */
	public static int depth = 0;
	
	/**
	 * divide url link by "/", and get elements in hyperlink
	 * 
	 * @param url
	 * @return
	 */
	public static String[] divUrl(String url)
	{
		return url.split("/");
	}
	
	/**
	 * whether create new file
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isCreateFile(String url)
	{
		Matcher matcher = pattern.matcher(url);
		/**
		 * used for debug
		 */
		System.out.println(url);
		System.out.println(matcher.matches());
		
		return matcher.matches();
	}
	
	/**
	 * create corresponding file
	 * 
	 * @param content
	 * @param urlPath
	 * @throws IOException 
	 */
	public static void createFile(String content, String urlPath) throws IOException
	{
		/*divide urlsrc=\"|*/
		String[] elems = content.split("\"");
		
		/**
		 * used for debug
		 */
		System.out.println(urlPath);
		System.out.println(elems.length);
		
		//StringBuffer path = new StringBuffer();
		
		//File file = null; 
		int j=0;
		for (int i = 0; i < elems.length; i++)
		{
			if (!elems[i].equals("src="))
			{
				j++;
				System.out.println(elems[i]);
				URL url = new URL(elems[i]);
				File outFile = new File("/home/qs/Documents/Data/2015SP/Pic/" + j +".jpg");
				OutputStream os = new FileOutputStream(outFile);
				InputStream is = url.openStream();
				byte[] buff = new byte[1024];
				while(true) 
				{
					int readed = is.read(buff);
					if(readed == -1) 
					{
						break;
					}
					byte[] temp = new byte[readed];
					System.arraycopy(buff, 0, temp, 0, readed);
					os.write(temp);
				}
				is.close(); 
                os.close();
			}
		}
			/*if (i == elems.length - 1)
			{
				Pattern pattern = Pattern.compile("\\w+\\.[a-zA-Z]");
				Matcher matcher = pattern.matcher(elems[i]);
				if (matcher.matches())
				{
					/**
					 * used for debug
					 *//*
					System.out.println("matches!");
					
					if (!file.exists())
					{
						file.mkdirs();
					}
					String[] fileName = elems[i].split("\\.");
					file = new File("/home/qs/tmp"+File.separator+path.toString()+file.separator+fileName[0]+".txt");
					try
					{
						file.createNewFile();
						writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
						writer.write(content);
						writer.flush();
						writer.close();
						System.out.println("create file successfully!");
					} catch (IOException e)
					{
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}*/
	}
	
	/**
	 * get webpage's hyperlink and turn it into tags
	 * 
	 * @param href
	 * @return
	 */
	public static String getHrefOfInOut(String href)
	{
		/*turn inner link into complete link*/
		String resultHref = null;
		
		/*whether it is outer link*/
		if (href.startsWith("http://"))
		{
			resultHref = href;
		}
		else 
		{
			/*if it is inner link, complete the link address; otherwise overlook it, e.g. a href = "#"*/
			if (href.startsWith("/"))
			{
				resultHref = "http://www.oschina.net" + href;
			}
		}
		return resultHref;
	}
	
	/**
	 * get aiming content from web source
	 * 
	 * @param content
	 * @return
	 */
	public static String getGoalContent(String content)
	{
		/** 
		 * used for debug
		 */  
		
		String s = "";
		Pattern p = Pattern.compile("<img .*?>");
		Matcher m = p.matcher(content);
		List<String> result=new ArrayList<String>();
		while(m.find())
		{
			result.add(m.group());
		}
		for(String s1:result)
		{
			//System.out.println(s1);
			s = s + s1;
		}
		
		p = Pattern.compile("src=.+?\"");
		Matcher mt = p.matcher(s);
		String t="";
		List<String> resultt=new ArrayList<String>();
		while(mt.find())
		{
			resultt.add(mt.group());
		}
		for(String s1:resultt)
		{
			System.out.println(s1);
			t = t + s1;
		}
		
		/**
		 * used for debug
		 */
		System.out.println(t);
		
		return t;
		
		/*int sign = content.indexOf("<a class=\"");
		String signContent  = content.substring(sign);
		
		
		System.out.println(signContent);
		
		int start = signContent.indexOf(">");
		//int end = signContent.indexOf("</pre>");
		
		/**
		 * used for debug
		 */
		/*int end = signContent.indexOf("</a>");
		System.out.println(start + " " + end);
		System.out.println(signContent.substring(start+1, end));
		
		return signContent.substring(start+1, end);*/
	}
	
	/**
	 * whether web source contains aiming file
	 * 
	 * @param content
	 * @return
	 */
	public static int isHasGoalContent(String content)
	{
		/**
		 * used for debug
		 */
		int tmp = content.indexOf("<img ");
		System.out.println(tmp);
		
		return content.indexOf("<img ");
	}
	
}
