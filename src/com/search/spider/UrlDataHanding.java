package com.search.spider;

public class UrlDataHanding implements Runnable  
{
	/**
	 * download corresponding webpages, get and put corresponding url into unreaded queue
	 * @param url
	 */
	public static int i = 0;
	
	public void dataHanding(String url, String KeyWord) throws Exception
	{
		/**
		 * used for debug
		 */
		System.out.println("it is dataHading now");
		
		HrefOfPage.getHrefOfContent(DownloadPage.getContentFromUrl(url, KeyWord));
	}
	
	public void run()
	{
		while (!UrlQueue.isEmpty())
		{
			/**
			 * used for debug
			 */
			i++;
			System.out.println("it is running for the " + i +" time");
			
			try
			{
				dataHanding(UrlQueue.outElem(), ReqKeyWordQueue.outElem());
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
