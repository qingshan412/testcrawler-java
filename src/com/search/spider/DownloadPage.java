package com.search.spider;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class DownloadPage 
{
	/**
	 * get web contents according to url
	 * 
	 * @param url
	 * @return
	 */
	
	public static String getContentFromUrl(String url) 
	{
		/*initialize an HttpClient client end*/
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpGet getHttp = new HttpGet(url);
		
		String content = null;
		
		HttpResponse response;
		
		try 
		{
			/*get information container*/
			/**
			 * used for debug
			 */
			System.out.println("begin to get content");			
			
			response = client.execute(getHttp);
			HttpEntity entity = response.getEntity();
			
			VisitedUrlQueue.addElem(url);
			
			if (entity != null) 
			{
				/*transform information into text information*/
				content = EntityUtils.toString(entity);
				
				/**
				 * used for debug
				 */
				System.out.println(content);
				
				/*find out whether source code should be downloaded*/
				if (FunctionUtils.isCreateFile(url)
						&& FunctionUtils.isHasGoalContent(content) != -1) 
				{
					FunctionUtils.createFile(FunctionUtils.getGoalContent(content), url);
				}
				
			}
		} catch (ClientProtocolException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		} finally
		{
			client.getConnectionManager().shutdown();
		}
		
		return content;
	}

}
