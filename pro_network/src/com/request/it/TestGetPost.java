package com.request.it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
/**
 * GET/POST请求
 * */
public class TestGetPost {
	
	/**
	 * 向指定的URL发送GET请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是name1=value&name2=value2的形式
	 * @return  URL所代表远程资源的响应
	 */
	private static String sendGet(String url,String param) {
		
		String result="";
		BufferedReader in=null;
		
		try {
			String urlName=url+"?"+param;
			URL realUrl=new URL(urlName);
			
			//打开和URL之间的链接
			URLConnection con=realUrl.openConnection();
			//设置通用的请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection","Keep-Alive");
			con.setRequestProperty("user-agent",
					"Mozilla/4.0(compatible);MSIE 6.0; Windows NT 5.1; SVl");
			 
			//建立连接
			con.connect();
			
			//获取所有响应头字段
			Map<String,List<String>> map=con.getHeaderFields();
			//遍历所有的响应头字段
			for (String key  : map.keySet()) {
				System.out.println(key+"----->"+map.get(key));
			}
			//定义BufferedReader输入流来读取URL的响应
			in=new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String line="";
			while((line=in.readLine())!=null){
				result+="\n"+line;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
		
	}
	
	/**
	 * 向指定的URL发送POST请求
	 * @param url url 发送请求的URL
	 * @param param请求参数，请求参数应该是name1=value&name2=value2的形式
	 * @return URL所代表远程资源的响应
	 */
	private static String sendPost(String url,String param) {
		PrintWriter out =null;
		BufferedReader in =null;
		String result="";
		
		try {
			URL realUrl=new URL(url);
			//打开和URL之间的链接
			URLConnection con=realUrl.openConnection();
			//设置通用的请求方式
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection","Keep-Alive");
			con.setRequestProperty("user-agent",
					"Mozilla/4.0(compatible);MSIE 6.0; Windows NT 5.1; SVl");
			 
			//发送Post请求必须设置如下两行
			con.setDoOutput(true);
			con.setDoInput(true);
			
			//获取URLConnection对象对应的输出流
			out=new PrintWriter(con.getOutputStream());
			//发送请求参数
			out.print(param);
			out.flush();
			//定义BufferedReader输入流来读取URL的响应
			in=new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String line="";
			while((line=in.readLine())!=null){
				result+="\n"+line;
			}	
		} catch (MalformedURLException e) {
			System.out.println("发送POST请求异常！"+e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}		
		return result;
	}
	public static void main(String[] args) {
		String ad="http://localhost:8888/abc/login.jsp";
		String ad1="http://localhost:8888/abc/a.jsp";
		//发送GET请求
//		String s=TestGetPost.sendGet(ad, null);
//		System.out.println(s);
		
		String s1=TestGetPost.sendPost(ad1, "user=李刚&pass=abc");
		System.out.println(s1);
		
	}

}
