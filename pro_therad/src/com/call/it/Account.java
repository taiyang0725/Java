package com.call.it;

public class Account {
	private String accountNo;// 账户
	private double balance;// 余额

	private boolean flag;// 表示账户中是否有存款

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
		return this.balance;
	}

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
	 * 取钱的方法
	 * */
	public synchronized void draw(double drawAmount) {
		/* 如果flag为假，表明账户中还没有人存钱进去,则取钱的线程阻塞 */
		try {
			if (!flag) {
				wait();
			} else {
				if (balance >= drawAmount) {
					// 执行取钱
					System.out.println(Thread.currentThread().getName() + "取钱："
							+ drawAmount);
					balance -= drawAmount;
					System.out.println("++++++++++++账户余额++++++++++：" + balance);
					// 将标识账户是否已有存款的旗标设为false
					flag = false;
					// 唤醒其他线程
					notifyAll();
				}else{
					System.out.println(Thread.currentThread().getName() + "取钱："
							+ drawAmount);
					System.out.println("余额不足！！！");
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 存钱的方法
	 * */
	public synchronized void deposit(double depositAmount) {
		/* 如果flag是真，表明已有人存钱进去，存钱方法阻塞 */
		try {
			if (flag) {
				wait();
			} else {
				System.out.println(Thread.currentThread().getName() + "存款："
						+ depositAmount);
				balance += depositAmount;
				System.out.println("**********账户余额**********：" + balance);
				flag = true;
				notifyAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
