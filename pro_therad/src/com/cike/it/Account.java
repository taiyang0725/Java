package com.cike.it;

public class Account {

	private String accountNo;// 账户编号
	private double balance;// 账户余额

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

//	public void setBalance(double balance) {
//		this.balance = balance;
//	}

	public int hashCode() {
		return accountNo.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == Account.class) {
			Account target = (Account) obj;
			return target.getAccountNo().equals(accountNo);
		}

		return false;

	}
	/**
	 * 同步方法保证线程安全问题
	 * */
	public synchronized void getMoney(double  drawAmount) {
		
		if (balance >= drawAmount) {
			System.out.println(Thread.currentThread() + "取钱成功,所取金额：" + drawAmount);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**
			 * 修改余额
			 * */
			 balance-=drawAmount;
			System.out.println(Thread.currentThread()+ "\t余额是：" +balance);

		} else {
			System.out.println(Thread.currentThread() + "余额不足！");
		}

		

	}

}
