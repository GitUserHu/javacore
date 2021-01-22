package serializableDemo;

import java.util.ArrayList;
import java.util.List;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest {

	public static void main(String[] args) throws ClassNotFoundException, IOException{
		serializable();
		unserializable();
		//serializable_1();
	}
	
	/**
	 * 序列化
	 * @throws IOException 
	 */
	public static void serializable() throws IOException {
		File file =new File("/Folder_For_IO_Test/SerializableTest.txt");
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(file));
			//oos.writeObject(new Student("Student_1","123456","001"));
			List<Student> students=new ArrayList<>();
			students.add(new Student("student1","password1","001","ttttt1"));
			students.add(new Student("student2","password1","002","ttttt2"));
			oos.writeObject(students);
			oos.close();
			System.err.println("Serializable Successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (oos!=null) oos.close();
		}
	}
	/**
	 * 反序列化
	 * ..值得注意的是：如果Entity需要反序列化，那么该Entity需要默认的构造函数，即无参构造函数。因为在反序列化的时候，会调用该构造函数。
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void unserializable() throws ClassNotFoundException, IOException {
		File file =new File("/Folder_For_IO_Test/SerializableTest.txt");
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			List<Student> studentList=(List<Student>)ois.readObject();
			//Student studentList=(Student)ois.readObject();
			System.out.println(studentList.toString());
			ois.close();
			System.err.println("unSerializable Successfully.");
		 }catch(EOFException e) {
			//EOFException 表示读文件的时候，到达文件的末尾。
			System.err.println("读取结束...");
			if(ois!=null)
			ois.close();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(ois!=null)
				ois.close();
		}
	}
	
	public static void serializable_1() throws IOException {
		File file = new File("/Folder_For_IO_Test/test20190503.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
		oos.writeObject(new Person("aaaa","bbbb"));
		oos.close();
	}
}

class Person {

	String username;
	String password;
	
	public Person() {
		
	}
	
	public Person(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Person [username=" + username + ", password=" + password + "]";
	}
	
}

class Student extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String testField;
	public Student(String username, String password,String id, String test) {
		super(username, password);
		this.id=id;
		this.testField = test;
	}
	
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException{
		ois.defaultReadObject();
		this.username = (String) ois.readObject();
		this.password = (String) ois.readObject();
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeObject(username);
		oos.writeObject(password);
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", testField=" + testField + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	
	
}
