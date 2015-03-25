package com.chat.say;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 负责处理每个线程通信的线程类
 * */
public class ServerThread implements Runnable {
	
	private Socket s;//定义当前线程所处理的Socket
	private BufferedReader br;//该线程所处理的Socket所对应的输入流

	public ServerThread(Socket s) throws IOException {
		super();
		this.s = s;
		 br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String content=null;
			//采用循环不断从Socket中读取客户端发送过来的数据
			while((content=readFromClient())!=null){
				/**
				 * 遍历socketList中的每个socket
				 * 将读取的内容向每一个socket发送一次
				 * */
				for (Socket s  : MyServer.socketList) {
					PrintStream ps=new PrintStream(s.getOutputStream());
					ps.println(content);
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//定义读取客户端数据的方法
	private String readFromClient() {
		
		try {
			return br.readLine();
			
		} catch (IOException e) {
			//如果捕捉到异常，表明该Socket对应的客户端已经关闭
			//删除该Socket
			MyServer.socketList.remove(s);
			
			e.printStackTrace();
		}
		
		
		return null;
		

	}

}
