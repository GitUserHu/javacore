package sort;
/**
 * 冒泡排序，以及改进
 * 每一轮依次比较相邻两个元素，进行交换，将最大值（最小值）放到每轮的末尾。
 * @author Jiabing
 *
 */
public class BubbleSort {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] arr1= {2,3,56,3,2,43,5,5454,222,11,1};
		
		int[] arr2= {1,2,3,4,5,6,7,8,9,10,11};
		System.out.print("未排序时：");
		display(arr2);
		
		
		bubble(arr2);
		
		//bunnleImprove(arr2);
	}
	/**
	 * 普通冒泡排序算法
	 * @param arr
	 * @return
	 * @throws Exception
	 */
	public static int[] bubble(int[] arr) throws Exception {
		//普通，普通冒泡排序
		if(arr==null||arr.length==0) {
			throw new Exception("array cannot be null!");
		}
		int count=0;
		int temp=0;
		int len=arr.length;
		for(int i= 0;i<len-1;i++) {
			boolean exchange=false;
			for(int j=1;j<len-i;j++) {
				if(arr[j-1]>arr[j]) {
					temp=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
					exchange=true;
				}
				count++;
			}
			
			System.out.print("第"+(i+1)+"轮\t：");
			display(arr);
			System.out.println();
			if(!exchange) break;
		}
		System.err.println("次数："+count);
		return arr;
	}
	/**
	 * 双向冒泡排序：
	 * 冒泡出最大值的同时，冒泡出最小值
	 * @param arr ： 需要排序的数组
	 * @return arr ：排序后的数组
	 */
	public static int[] bunnleImprove(int[] arr) {
		int len=arr.length;
		int temp=0;
		
		int count=0;
		int asc=0;
		int desc=len-1;
		int counter=0;
		
		while(asc<desc) {
			boolean exchange=false;
			for(int i= asc;i<len-1;i++) {
				
				if(arr[i]>arr[i+1]){
					temp=arr[i+1];
					arr[i+1]=arr[i];
					arr[i]=temp;
					exchange=true;
				}	
				counter++;
			}
			if(!exchange) break;
			asc++;
			for(int j=desc;j>0;j--) {
				//
				if(arr[j-1]>arr[j]){
					temp=arr[j-1];
					arr[j-1]=arr[j];
					arr[j]=temp;
					
				}
				counter++;
			}
			desc--;
			count++;
			System.out.print("第"+count+"轮\t：");
			display(arr);
			System.out.println();
			
				
			
			
		}
		System.out.println("次数："+counter);
		return arr;
	}
	public static void display(int[] arr) {
		int len=arr.length;
		for(int i=0;i<len;i++) {
			System.out.print(arr[i]+"\t");
		}
	}

}
