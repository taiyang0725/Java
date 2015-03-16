package com.call.it;

public class DepositThread extends Thread {
	
	private Account  account;//模拟用户账户
	private double depositAmount;//存的钱数
	
	
	public DepositThread(String name,Account account, double depositAmount) {
		super(name);
		this.account = account;
		this.depositAmount = depositAmount;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			account.deposit(depositAmount);
			
		}
	}
	

}
