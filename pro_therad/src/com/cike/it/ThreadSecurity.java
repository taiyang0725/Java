package com.cike.it;

/**
 * 取钱线程类
 * */
public class ThreadSecurity extends Thread {

	private Account account;// 模拟用户账户
	private double drawAmount;// 当前取钱线程所希望取的钱数

	public ThreadSecurity(String name, Account account, double drawAmount) {
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}

	/**
	 * 当多条线程修改统一共享数据时，将涉及数据安全问题
	 * */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
//		/**
//		 * 同步代码块保证线程安全问题
//		 * */
//		synchronized (account) {
//
//			if (account.getBalance() >= drawAmount) {
//				System.out.println(getName() + "取钱成功,所取金额：" + drawAmount);
//
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				/**
//				 * 修改余额
//				 * */
//				account.setBalance(account.getBalance() - drawAmount);
//				System.out.println(getName() + "\t余额是：" + account.getBalance());
//
//			} else {
//				System.out.println(getName() + "余额不足！");
//			}
//
//		}
		account.getMoney(drawAmount);//直接调用acount的取钱发法取钱
	}

}