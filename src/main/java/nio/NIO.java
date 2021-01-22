package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/**
 * 关于buffer的写入和读取时 position、limit、capacity的变化见该JAVA项目的资源图片（sources/pictures/comprehension.png）
 * @author JiaBing
 *	JAVA 1.7+
 */
public class NIO {
	public static void main (String[] args) throws IOException {
		//readFile();
		
		//writeFile();
		
		copy();
	}
	
	public static void  multiplexing() throws IOException {

	}
	
	/**
	 * NIO:将文件1的内容复制到文件2
	 * @throws IOException 
	 */
	public static void copy() throws IOException {
		Path file_test=Paths.get("E:\\Programming Folder\\inter.txt");
		Path file_test2=Paths.get("E:\\Programming Folder\\inter3.docx");
		FileChannel fc_test=null;
		FileChannel fc_test2=null;
		ByteBuffer buffer =null;
		try {
			//打开的test.txt文件的通道  fc_test
			 fc_test=FileChannel.open(file_test, StandardOpenOption.READ);
			//打开的test.txt文件的通道  fc_test2
			 fc_test2=FileChannel.open(file_test2, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW); // 如果写入的文件不存在，则创建一个文件
			//定义一个ByteBuffer 用作从通道读取或者写入数据的缓存容器,并分配容量为128
			buffer=ByteBuffer.allocate(128);
			//使用fc_test通道读取test.txt文件的内容，将读取的内容通过通道fc_test2写入到test2.txt文件中
			while(fc_test.read(buffer)!=-1) {
				//从buffer读取或者写入数据时 position和limit的值都会都会发生变化
				//buffer转为读取模式
				buffer.flip();
				fc_test2.write(buffer);
				//方便下一轮写入操作
				buffer.clear();
			}
			System.out.println("Done!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			fc_test.close();
			fc_test2.close();
			
		}
	}
	
	/**
	 * NIO:向文件中写入内容
	 */
	public static void writeFile() {
		Path file=Paths.get("/test2.txt");
		try {
			FileChannel fc=FileChannel.open(file, StandardOpenOption.WRITE,StandardOpenOption.READ);
			//ByteBuffer bb= ByteBuffer.allocate(16);
			byte[] content= "Testing for writing into a file with NIO".getBytes();
			ByteBuffer bb= ByteBuffer.allocate(content.length);
			bb.put(content);
			//只有position和limit之间的数据才会被写入到文件,因此需要clean()方法将position 设置为0，将limit设置为容量的大小
			//否则没有内容写入
			bb.flip();
			fc.write(bb);	
			System.out.println("sussss");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 用FileChannel 打开文件的通道。利用通道，将数据读取到buffer中，然后将buffer中的内容依次输出。
	 */
	public static void readFile() {
		Path file=Paths.get("/test.txt");

		try (FileChannel fc = FileChannel.open(file, StandardOpenOption.WRITE, StandardOpenOption.READ)){
		    ByteBuffer buf = ByteBuffer.allocate(128);  //分配一个容量为128字节的Buffer
		    while ((fc.read(buf)) != -1) {  //循环载入内容到Buffer
		       buf.flip();  //使Buffer由写模式转为读模式
		       
		       while (buf.hasRemaining()) {
		          System.out.print((char)buf.get()); //循环读取Buffer
		       }
		       buf.clear();  //清理Buffer，为下一次写入做准备
		    }
		    System.out.println();
		} catch(Exception e) {
				e.printStackTrace();
		}
	}
}
