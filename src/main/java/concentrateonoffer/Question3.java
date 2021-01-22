/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/3/30 23:17
 * 题目： 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
package concentrateonoffer;

import java.util.ArrayList;
import java.util.Stack;

public class Question3 {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 当传入的listNode是Null时，返回一个空的ArrayList而不是一个null，否则会造成客户端NullPointerException异常
        if (listNode == null)
            return new ArrayList<>(0);
        Stack<Integer> temp = new Stack<>(); // Stack栈是从先进后出

        ArrayList<Integer> list = new ArrayList<>();

        ListNode next = listNode;
        while (next != null) {
            temp.push(next.val);
            next = next.next;
        }
        while (!temp.isEmpty()) {
            list.add(temp.pop());
        }
        return list;
    }
}
class ListNode{
    int val;
    ListNode next = null;
    public ListNode(int val) {
        this.val = val;
    }
}
