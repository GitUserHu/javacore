package sort;

import util.JavaUtils;
/**
 * 插入排序：
 * 将需要排序的元素的前面所有元素看成有序的，依次比较，如果被比较的元素大于该需要排序的元素，则被比较的元素后移。
 * @author Jiabing
 *
 */
public class InsertionSort {

	
	
	public static void main(String[] args) {
		int[] arr1= {2,3,56,3,2,43,5,5454,222,11,1};
		int[] arr2= {1,2,3,4,5,6,7,8,9,10,11};
		
		
		int[] arr=arr1;
		System.out.print("未排序时：");
		JavaUtils.display(arr);
		
		System.out.println();
		//selectionSort(arr1);
		//insertionSort(arr1);
		insertionSortImprove(arr);
		JavaUtils.display(arr);

	}
	/**
	 * 普通插入排序
	 * @param arr
	 * @return
	 */
	public static int[] insertionSort(int[] arr) {
		int len=arr.length;
		int temp=0;
		int index =-1;//需，需要排序的元素应该插入的下标
		for(int i =0 ;i< len-1;i++) {
			temp=arr[i+1];
			for(int j=i+1;j>0;j--) {
				 index =-1;
				 //当， 当大于 需要排序的元素的值时，右移。
				if(arr[j-1]>temp) {
					arr[j]=arr[j-1];
				}else if(temp>=arr[j-1]) {//当， 小于需要排序的元素的值时，确定插入的下标为j，退出该轮循环
					index=j;
					break;
				}
				
			}
			//如，如果本轮结束后 index为-1，即没有找到要插入的下标，那么，插入的位置的下标为0.
			if(index==-1) {
				index=0;
			}
			//如果，如果插入的下标与本身的所在的下标一致，不变更位置。否则进行插入操作。
			if(i+1!=index) {
				arr[index]=temp;
			}
			
			System.out.print("第"+(i+1)+"轮\t：");
			JavaUtils.display(arr);
			System.out.println();
		}
		return arr;
	}
	
	/**
	 * 插入排序改进版
	 * 修改比较的方式：普通插入排序是依次比较，改进版使用二分法比较。
	 * @param arr
	 * @return
	 */
	public static int[] insertionSortImprove(int[] arr) {
		int len =arr.length;
		int index=-1;
		int temp=0;
		for(int i =0;i<len-1;i++) {
			index =-1;
			temp=arr[i+1];
			if(i==2) {
				System.out.println();
			}
			index=findIndex(temp, arr, 0, i);
			if(index!=i+1) {
				move(arr, index, i);
				arr[index]=temp;
			}
			
			System.out.print("第"+(i+1)+"轮\t：");
			JavaUtils.display(arr);
			System.out.println();
		}
		return arr;
	}
	
	/**
	 * 二分法找到需要插入的位置
	 * @param target 需要排序的元素的值
	 * @param arr    目标数组
	 * @param start  二分法中的起始位置
	 * @param end	  二分法中的结束位置
	 * @return		插入的位置
	 */
	public static int findIndex(int target,int[] arr,int start,int end) {
		if(target<arr[start]) {
			return start;
		}
		if(target>=arr[end]) {
			return end+1;
		}
		
		int curr=(start+end)/2;
		if(curr==start) {
			return start+1;
		}
		if(target>=arr[curr]) {
			//表，表示只有
			start=curr;
			
		}else if(target<arr[curr]) {
			//表，表示只有
			end=curr;
		}
		
		return findIndex(target, arr, start, end);
	}
	/**
	 * 将数组   index ~ end 位置的元素 依次后移一位。
	 * @param arr 目标数组
	 * @param index 
	 * @param origin
	 */
	public static void move(int[]  arr,int index,int end) {
		for(int i=end;i>=index;i--) {
			arr[i+1]=arr[i];
		}
	}
}
