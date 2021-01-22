package jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaBing
 * @date 2019-05-09 21:59:14 
 * 这里通过JVM的调试，来观察堆溢出的情况
 */
public class HeapOOM {

	private static class OOMObject{
		
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
		}

	}

}
