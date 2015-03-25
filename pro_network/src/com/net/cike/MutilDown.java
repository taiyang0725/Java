package com.net.cike;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
/**
 * 多线程下载
 * */
public class MutilDown {
	
	private void down() {
		
		final int DOWN_THREAD_NUM=5;
		final String OUT_FILE_NAME="down.jpg";
		
		InputStream [] isArr=new InputStream[DOWN_THREAD_NUM];
		RandomAccessFile[] outArr=new RandomAccessFile[DOWN_THREAD_NUM];

		try {
			URL url=new URL("http://images.china-pub.com/"
					+ "ebook35001-40000/35850/shupi.jpg");
			
			//对此URL对象打开第一个输入流
			isArr[0]=url.openStream();
			long fileLen=getFileLength(url);
			System.out.println("网络资源的大小"+fileLen);
			//以输出文件名创建第一个RandomAccessFile输出流
			outArr[0]=new RandomAccessFile(OUT_FILE_NAME, "rw");
			
			//创建一个与下载资源相同的空文件
			for (int i = 0; i < fileLen; i++) {
				outArr[0].write(0);
			}
			//每条线程应该下载的字节数
			long numPerTh=fileLen/DOWN_THREAD_NUM;
			//整个资源整除后剩下的余数
			long left=fileLen%DOWN_THREAD_NUM;
			
			for (int i = 0; i < DOWN_THREAD_NUM; i++) {
				//为每个线程打开一个输入流，一个RandomAccessFile对象
				//让每个线程分别负责下载资源的不同部分
				if(i!=0){
					isArr[i]=url.openStream();
					//以指定输出文件创建多个RandomAccessFile对象
					outArr[i]=new RandomAccessFile(OUT_FILE_NAME, "rw");	
				}
				//分别启动多个线程来下载网络资源
				if(i==DOWN_THREAD_NUM-1){
					//最后一个线程下载指定numPerTh+left个字节
					new DownThread(i+numPerTh,(i+1)*numPerTh+left,
							isArr[i], outArr[i]).start();
				}else{
					//每个线程负责下载numPerTh的字节
					new DownThread(i*numPerTh,(i+1)*numPerTh,
							isArr[i], outArr[i]).start();
				}
				
			}
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 定义获取指定网络资源的长度的方法
	 * */
	private long getFileLength(URL url) throws IOException {
		long len=0;
		//打开该URL对应的URLconnection
		URLConnection con=url.openConnection();
		//获取链接URL对应资源的长度
		long size=con.getContentLength();
		len=size;
		return len;
		

	}
	public static void main(String[] args) {
		new MutilDown().down();
	}

}
