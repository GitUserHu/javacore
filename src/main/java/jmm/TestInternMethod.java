package jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaBing
 * @date 2019-05-09 22:59:50 
 * 本例使用String.intern()。
 * 作用：如果字符串常量池中已经包含一个等于此String对象的字符串，
 * 则返回代表池中这个字符串的String对象
 * 否则将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用
 * 目的：通过一直往常量池中加字符串常量，出现内存溢出错误
 * 这里对Debug Configuration的VM args 进行了参数限制
 * 不同的版本的JVM得到的结果不同。JDK1.6对应的JVM，常量池分配在永生代中（永生代：不会被gc回收）。
 */
public class TestInternMethod {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern()); // 向常量池中加入字符串常量
		}
	}

}
