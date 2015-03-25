package com.chat.say;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 * 服务端 
 * */
public class MyServer {

	// 定义保存所有Socket的ArrayList

	public static ArrayList<Socket> socketList = new ArrayList<Socket>();

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(30000);
			while (true) {
				// 此行代码会阻塞，将一直等待别人的链接
				Socket s = ss.accept();
				socketList.add(s);

				// 每当客户端连接后启动一条ServerThread线程为客户端服务
				new Thread(new ServerThread(s)).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
