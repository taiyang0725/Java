package com.call.it;

/**
 * 取钱线程
 * */
public class DrawThread extends Thread {

	private Account account;// 模拟用户账户
	private double drawAmount;// 取的钱数

	public DrawThread(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		/**
		 * 重复执行100次取钱操作
		 * */
		for (int i = 0; i < 100; i++) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				account.draw(drawAmount);
			
		}

	}

}
