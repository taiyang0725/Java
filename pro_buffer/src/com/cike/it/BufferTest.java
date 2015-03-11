package com.cike.it;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * Java IO
 * */
public class BufferTest {
	public static void main(String[] args) {
		charestTest();     
	}

	private static void studyBuffer() {
		/**
		 * 创建一个capacity为8的CharBuffer
		 * */
		CharBuffer buffer=CharBuffer.allocate(8); 
		System.out.println("capacity容量:"+buffer.capacity());
        System.out.println("limit界限:"+buffer.limit());
        System.out.println("position位置:"+buffer.position());
        
        /**
         * 放入元素
         * */
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("加入三个元素后，position="+buffer.position());
        
        /**
         * 使用flip（）方法
         * */
        buffer.flip();
        System.out.println("执行flip()后，limit="+buffer.limit());
        System.out.println("执行flip()后,position位置:"+buffer.position());
        
        /**
         * 取出第一个元素
         * */
        System.out.println("第一个元素(position=0)"+buffer.get());
        System.out.println("取出一个元素后,position位置:"+buffer.position());
        
        /**
         * 调用clear方法
         * */
        buffer.clear();
        System.out.println("执行clear()后,limit="+buffer.limit());
        System.out.println("执行clear()后,position="+buffer.position());
        System.out.println("执行clear()后,缓冲区内容并没有被清除="+buffer.get(2));
        System.out.println("执行绝对读取后,position位置:"+buffer.position());
	}
	/**
	 * 获取全部字符集的方法
	 * */
	private static void charestTest(){
		
		SortedMap<String, Charset> map=Charset.availableCharsets();
		
		for (String alias : map.keySet()) {
			//输出字符集的别名和对应的Charset对象
			System.out.println(alias+"---->"+map.get(alias));
		}
		
		
		
		
	}
     
}
