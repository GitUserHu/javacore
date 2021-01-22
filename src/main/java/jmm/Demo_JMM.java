package jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Java内存模型
 * @author JiaBing
 * @date 2019-01-02
 */
public class Demo_JMM {
	public static void main(String[] args) throws InterruptedException {
		
		// this variable stores in thread stack; 原始类型的变量无论如何都存储在线程栈上
		int i = 0;
		Model  model = new Model(i);
		// this variable stores in heap; 
		new Thread(()->{
			model.i = 100;
			synchronized (model) {
			
			}
		}).start();
	}

}
class Model{
    int i;
	public Model(int i) {
		this.i = i;
	}
	void show(){
		System.out.println("i = "+i);
	}
	void add(int val) {
		this.i+= val;
	}
}
