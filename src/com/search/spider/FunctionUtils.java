package com.search.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FunctionUtils 
{
	/**
	 * find the hyper link through regular expression
	 */
	private static String pat = "https://www.google\\.com/\\?gws_rd=ssl#newwindow=1&q=.*";
			//"^https://www\\.google\\.com/?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
//"https://www\\.google\\.come/\\?gws\\_rd=ssl#newwindow=1\\&q=acfun";
	//^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$
	//^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$
	//^https://www\\.google\\.com/(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$

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
	 */
	public static void createFile(String content, String urlPath)
	{
		/*divide url*/
		String[] elems = divUrl(urlPath);
		StringBuffer path = new StringBuffer();
		
		File file = null;
		for (int i = 1; i < elems.length; i++)
		{
			if (i != elems.length -1)
			{
				path.append(elems[i]);
				path.append(File.separator);
				file = new File("/home/qs/tmp"+File.separator+path.toString());
			}
			
			if (i == elems.length - 1)
			{
				Pattern pattern = Pattern.compile("\\w+\\.[a-zA-Z]");
				Matcher matcher = pattern.matcher(elems[i]);
				if (matcher.matches())
				{
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
		}
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
		int sign = content.indexOf("<pre class=\"");
		String signContent  = content.substring(sign);
		
		int start = signContent.indexOf(">");
		int end = signContent.indexOf("</pre>");
		
		return signContent.substring(start+1, end);
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
		int tmp = content.indexOf("<pre class=\"");
		System.out.println(tmp);
		
		return content.indexOf("<pre class=\"");
	}
	
}
