package createObj;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author JiaBing
 * @date 2019-04-11 22:24:21 
 */
public class ComparableImpl implements Comparable<ComparableImpl>{

	private int count ;
	
	public ComparableImpl(int n) {
		count = n;
	}
	
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return "ComparableImpl [count=" + count + "]";
	}

	@Override
	public int compareTo(ComparableImpl o) {
		
		return count-o.getCount();
	}
	
	
	public static void main(String[] args) {
		
		ComparableImpl c1 = new  ComparableImpl(10);
		ComparableImpl c2 = new  ComparableImpl(200);
		ComparableImpl c3 = new  ComparableImpl(1);
		ComparableImpl c4 = new  ComparableImpl(100);
		ComparableImpl[] arr = new ComparableImpl[4];
		arr[0] = c1;
		arr[1] = c2;
		arr[2] = c3;
		arr[3] = c4;
		ArrayList<ComparableImpl> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		Arrays.sort(arr);
		for (int i = 0; i< arr.length; i++) {
			System.out.println(arr[i]);
		}
				
	}



}
