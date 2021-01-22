/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/3/30 23:49
 * 题目：在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *      请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
package concentrateonoffer;

public class Question1 {
    /**
     * 函数解析： 1, 3, 4, 8 参考这个二维数组，考虑从左下角的位置开始，如果target比左下角的数大，则右移，如果比左下角小，则上移。
     * 			 2, 4, 6, 9
     * 			 5, 7, 9 ,11
     *
     * @param target
     * @param array
     * @return
     */
    public  boolean find2(int target, int [][] array) {

        int row = array[0].length - 1;
        int column = array.length - 1;
        int rowIndex = row, columnIndex = 0;
        while (rowIndex >= 0 && columnIndex <= column) {
            int temp = array[columnIndex][rowIndex];
            if (temp > target) rowIndex--;
            if (temp < target) columnIndex++;
            if (temp == target) return true;
        }
        return false;
    }

    /**
     * 解析：一次遍历二维数组中的每个数组，用二分法找到target。
     * @param target
     * @param array
     * @return
     */
    public static boolean find(int target, int [][] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int low = 0;
            int high = array[i].length - 1;
            int mid = 0;
            while (low <= high) {
                mid = (low + high) / 2;
                if (array[i][mid] > target)
                    high = mid - 1;
                else if (array[i][mid] < target)
                    low = mid + 1;
                else return true;
            }


        }
        return false;
    }
}
