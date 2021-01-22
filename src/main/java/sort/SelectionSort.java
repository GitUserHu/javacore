package sort;

import util.JavaUtils;

/**
 * 选择排序
 * 每轮依次比较相邻两个元素，找出最大值，放到每轮的末尾。
 * 与冒泡排序不同 的是，选择排序每轮只交换一次。
 * @author Jiabing
 *
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr1= {2,3,56,3,2,43,5,5454,222,11,1};
		
		int[] arr2= {1,2,3,4,5,6,7,8,9,10,11};
		System.out.print("未排序时：");
		JavaUtils.display(arr1);
		
		System.out.println();
		selectionSort(arr1);
		//selectionSortImprove(arr1);
		JavaUtils.display(arr1);

	}
	/**
	 * 普通选择排序
	 * @param arr ：需要排序的数组
	 * @return ： 排序后的数组
	 */
	public static int[] selectionSort(int[] arr) {
		
		int len =arr.length;
		int index=0;
		int temp=0;
		for (int i =0;i<len-1;i++) {
			index=0;
			
			for(int j=1;j<len-i;j++) {
				if(arr[index]<arr[j]) {
					index=j;
				}
			}
			if(index!=len-i-1) {
				temp=arr[index];
				arr[index]=arr[len-i-1];
				arr[len-i-1]=temp;
			}
			System.out.print("第"+(i+1)+"轮\t：");
			JavaUtils.display(arr);
			System.out.println();
			
		}
		return arr;
	}

	/**
	 * 选择排序 改进：
	 * 每一轮确定最大值的同时确定最小值
	 * @param arr
	 * @return
	 */
	public static int[] selectionSortImprove(int[] arr) {
		int max_index=0;
		int min_index=0;
		int len=arr.length;
		int temp=0;
		for(int i = 0; i<(len/2) ; i++) {
			max_index=i;
			min_index=i;
			for(int j=i+1;j<len-i;j++) {
				if(arr[max_index]<arr[j]) {
					max_index=j;
					
				}
				if(arr[min_index]>arr[j]){
					min_index=j;
					
				}
			}
			if(i==2) {
				System.out.println();
			}
			if(max_index!=len-1-i) {
				temp=arr[max_index];
				arr[max_index]=arr[len-1-i];
				arr[len-1-i]=temp;
				//这里有一个误区：
				//如果不处理这种情况，会出现 “最大值不能正常归位”
				//比如：2	 3	56	3	2	43	5	5454	222	11	1 这一组数据
				//第一轮时：max_index = 7(5454所在的位置) min_index = 10(1所在的位置)，而此时min_index=len-1-i，即该轮应该放最大数值的位置。
				//当 arr[7] 和arr[10]交换数据后，即5454 和1交换位置后，最小值的位置发生了变化，如果不处理这种情况，那么接下来交换最小值时，就会出错。
				//那么如果，我们将最小值的交换放在最大值的交换的前面时，就应该处理 max_index=i时的情况。
				if(min_index==len-1-i) {
					min_index=max_index;
				}
			}
			if(min_index!=i) {
				temp=arr[min_index];
				arr[min_index]=arr[i];
				arr[i]=temp;
			}
			
			System.out.print("第"+(i+1)+"轮\t：");
			JavaUtils.display(arr);
			System.out.println();
			
		}
		return arr;
	}

}
