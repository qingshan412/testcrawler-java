package com.search.spider;

public class HrefOfPage
{
	/**
	 * get hyperlink in the web source
	 */
	public static void getHrefOfContent(String content)
	{
		System.out.println("start");
		String[] contents = content.split("<a href=\"");
		for (int i = 1; i < contents.length; i++)
		{
			int endHref = contents[i].indexOf("\"");
			String aHref = FunctionUtils.getHrefOfInOut(contents[i].substring(0,endHref));;
			
			if (aHref != null)
			{
				String href = FunctionUtils.getHrefOfInOut(aHref);
				
				if (!UrlQueue.isContains(href)
						&& href.indexOf("/code/explore") != -1
						&& !VisitedUrlQueue.isContains(href))
				{
					UrlQueue.addElem(href);
				}
			}
		}
		
		System.out.println(UrlQueue.size() + "--number of links catched");
		System.out.println(VisitedUrlQueue.size() + "--number of webpages processed");
	}
}
