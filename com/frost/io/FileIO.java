package com.frost.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

import algorithm.Convert;

public class FileIO {
	private FileReader reader;
	private BufferedReader br;
	private FileWriter writer;
	private BufferedWriter bw;
	private FileInputStream fis;
	private FileOutputStream fos;
	private File file;
	private boolean mode;
	/**
	 * 
	 * @param f
	 *            true means write, while false means read
	 * @param flag
	 * @throws IOException
	 */
	public FileIO(String f, boolean flag) throws IOException {
		mode=flag;
		if (flag == true) {
			file = new File(f);
			if(file.exists())
				writer = new FileWriter(file);
			else writer = new FileWriter(file);
			bw = new BufferedWriter(writer);
			fos=new FileOutputStream(file,true);
		} else {
			file = new File(f);
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			fis=new FileInputStream(file);
		}
	}
	public boolean isExist(){
		return file.isFile()&&file.exists();
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String ReadbyLine() throws IOException {
		// read file content from file
		String str = br.readLine();
		if (str == null) {
			br.close();
			reader.close();
		}
		return str;
	}
	public String ReadAll()throws IOException{
		String str=null;
		return str;
	}
	/**
	 * 
	 * @param s
	 * @throws IOException
	 */
	public void WritebyLine(String s) throws IOException {
		bw.write(s);
		bw.newLine();// 换行
		bw.flush();// 换行
	}
	/**
	 * 
	 * @throws IOException
	 */
	public void release() throws IOException {
		if(!mode){
			br.close();
			reader.close();
			fis.close();
		}else{
			bw.close();
			writer.close();
			fos.close();
		}
	}
	public void Write(byte[] buf,int offset,int len) throws IOException {
		// TODO Auto-generated method stub
//		char[] cbuf=new char[buf.length/2];
//		for(int i=0;i<buf.length;i++){
//			cbuf[i/2]|=buf[i]<<(8*i%2);
//		}
//		bw.write(cbuf, offset, len);
//		bw.flush();
		fos.write(buf, offset, len);
	}
	/**
	 * read len byte to buf
	 * @param buf
	 * @param len
	 * @throws IOException 
	 */
	public int ReadtoBuf(byte[] buf) throws IOException {
		// TODO Auto-generated method stub
		char[] cbuf=new char[buf.length/2];
		int num=br.read(cbuf)*2;
		if(num!=-2){
			for(int i=0;i<buf.length;i+=2){
				//buf[i]=Byte.parseByte(String.valueOf(cbuf[i]));
				buf[i]=(byte)((cbuf[i/2] & 0xFF00) >> 8);
				buf[i+1]=(byte)((cbuf[i/2] & 0xFF));
			}
		}
		return num;
		//return fis.read(buf);
	}
	public int ReadtoBuf(byte[] buf,int offset) throws IOException {
		// TODO Auto-generated method stub
		fis.skip(offset);
		return fis.read(buf);
	}
}
