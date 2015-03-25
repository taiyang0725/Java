package com.chat.say;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 负责读取客户端的输入流
 * */
public class ClientThread implements Runnable {
	
	private Socket s;
	
	private BufferedReader br;

	public ClientThread(Socket s) throws IOException {
		super();
		this.s = s;
		 br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
	
		try {
			String content="";
			while((content=br.readLine())!=null){
				System.out.println(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
