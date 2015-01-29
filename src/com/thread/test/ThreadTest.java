package com.thread.test;

public class ThreadTest extends Thread 
{
	public ThreadTest(String str)
	{//用构造函数控制线程的名字，如果不写，默认是thread-整数
		super(str);
	}

@Override
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

/*
* 此方法可添加也可以不填加，start都能够调用。
* @Override
public synchronized void start() {
// TODO Auto-generated method stub
super.start();
}*/


	public static void main(String args[])
	{
		new ThreadTest("su").start();
		new ThreadTest("zhu").start();
	}
}