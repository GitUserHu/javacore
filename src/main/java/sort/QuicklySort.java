package sort;

import util.JavaUtils;

/**
 * 快速排序
 * 
 * @author Jiabing
 *
 */
public class QuicklySort {
	static int[] arr1= {2,3,56,3,2,43,5,5454,222,11,1};
	static int[] arr= {57,68,59,52,72,28,96,33,24,19};
	public static void main(String[] args) {
		quickSort(0,arr1.length);
		JavaUtils.display(arr1);
	}
	/**
	 * 普通快速排序
	 * @param arr
	 * @return
	 */
	public static void quickSort(int low,int high) {
		if(low>=high) {
			return;
		}
		//分区： low->index 和 index->high
		int index=partition(low, high) ;
		quickSort(low,index);
		quickSort(index+1, high);
	}
	
	public static int partition(int start,int end) {
		int index =start;
		//start = 0  , end = len -1
		int compareIndex=end-1;
		for(int i =start;i<end;i++) {
			
			if(compareIndex>index) {
				if(arr1[index]>arr1[compareIndex]) {
					swap(index,compareIndex);
					//交换下标。
					int temp=compareIndex;
					compareIndex=index+1;
					index=temp;
					continue;
				}
				compareIndex--;
				
			}else {
				if(arr1[index]<arr1[compareIndex]) {
					
					swap(index,compareIndex);
					//交换下标。
					int temp=compareIndex;
					compareIndex=index-1;
					index=temp;
					continue;
				}
				compareIndex++;
			}
		}
		return index;	
	}
	
	public static void swap(int index,int compareIndex){
		int temp=arr1[compareIndex];
		arr1[compareIndex]=arr1[index];
		arr1[index]=temp;
	}

}
