package ioStreamDemo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * 简单的字节流操作：将一个文件的内容复制到另一个文件——>将a文件的内容用inputStream读出来，然后用OutputStream写入到b文件
 * @author Jiabing
 *
 */
public class IOStreamDemo_1 {

	public static void main(String[] args) throws IOException {
		//copyFileWithByte();
		//copyFileWithChar();
		InputStreamReader isr = new InputStreamReader(System.in);
		//isr.re
		Console console=System.console();
		if(console==null) {
			System.err.println("No Console!");
			System.exit(1);
			
			String account=console.readLine("Pls input your account:");
			String password=console.readLine("Pls input your password:");
			System.out.println("Your Account is "+account+" and your password is "+password);
			
		}
	}
	
	
	/**
	 * 使用字节流复制文件：
	 * 一个字节一个字节的复制，效率很低。
	 * @throws IOException
	 */
	public static void copyFileWithByte() throws IOException {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis=new FileInputStream("/Folder_For_IO_Test/a.txt");
			fos=new FileOutputStream("/Folder_For_IO_Test/b.txt");
		
			int c;
			//返回-1表示到达文件的末尾
			while((c=fis.read())!=-1) {
				fos.write(c);
			}
			System.out.println("copy successfully...");
		} finally {
			if(fis!=null) fis.close();
			if(fos!=null) fos.close();
		}
	}
	/**
	 * 使用字符流复制文件：
	 * 一个字符一个字符的读取文件
	 * @throws IOException
	 */
	public static void copyFileWithChar() throws IOException {
		FileReader fr=null;
		FileWriter fw=null;
		try {
			fr=new FileReader("/Folder_For_IO_Test/a.txt");
			fw=new FileWriter("/Folder_For_IO_Test/b.txt");
			int c;
			 while((c=fr.read())!=-1) {
				 fw.write(c);
			 }
			 System.out.println("copy successfully...");
		} finally {
			if(fr!=null) {
				fr.close();
			}
			if(fw!=null) {
				fw.close();
			}
		}
		 	
	}
	
	public static void bufferStream() throws IOException {
		BufferedReader is=null;
		BufferedWriter os=null;
		
		is=new BufferedReader(new FileReader("/Folder_For_IO_Test/a.txt"));
		os=new BufferedWriter(new FileWriter("/Folder_For_IO_Test/b.txt"));
		
	}
}
