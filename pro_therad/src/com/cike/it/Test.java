package com.cike.it;
/**
 * 测试类
 * */
public class Test {
	public static void main(String[] args) {
		
		Account account=new Account("123", 1000);
		
		new ThreadSecurity("张三",account, 500).start();
		new ThreadSecurity("李四",account, 600).start();
		new ThreadSecurity("王五",account, 100).start();
	}

}
