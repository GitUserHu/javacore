/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/3/31 21:00
 * 题目：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 返回：              1
 *                  2    3
 *               4     5    6
 *                 7      8
 * 则重建二叉树并返回。
 */
package concentrateonoffer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Question4 {
    public static void main(String[] args) {

    }
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length == 0){
            return null;
        }
        int rootVal = pre[0];
        if(pre.length == 1){
            return new TreeNode(rootVal);
        }
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        // Arrays.copyOfRange(int[], from, to)的范围是 [from, to)
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, rootIndex+1), Arrays.copyOfRange(in, 0, rootIndex));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, rootIndex+1, pre.length), Arrays.copyOfRange(in, rootIndex+1, in.length));
        return root;
    }



}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}
