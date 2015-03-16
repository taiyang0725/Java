package com.call.it;

public class Test {
	public static void main(String[] args) {
		
		Account account=new Account("123", 0);
		
		
		new DrawThread("取钱者A", account, 400).start();
		new DrawThread("取钱者B", account, 400).start();
		new DrawThread("取钱者C", account, 10000).start();
		
		
		
		new DepositThread("存钱A", account, 1000).start();
		new DepositThread("存钱B", account, 1000).start();
		new DepositThread("存钱C", account, 1000).start();
		new DepositThread("存钱D", account, 1000).start();
		new DepositThread("存钱E", account, 1000).start();
	}

}
