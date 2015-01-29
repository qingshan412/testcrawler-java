package com.search.spider;

public class MyThread extends Thread 
{
	public MyThread(String str)
	{
		// TODO Auto-generated constructor stub
		super(str);
	}
	
	public void run() 
	{
		synchronized(this)
		{
			for(int i=0;i<10;i++)
			{//控制run的内部这些东西执行的次数，不加这句，run只执行一次。
				System.out.println(i+getName());
				try 
				{
					sleep((long)(Math.random()*1000));
				} catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			//for循环结束
			System.out.println("for循环结束"+getName());
		}

		super.run();
	}
}
