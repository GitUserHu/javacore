/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/3/18 21:52
 */
package collection;

import java.util.HashSet;

public class Demo_Set {
    public static void main(String[] args) {
        HashSet<MyClass> sets = new HashSet<>();
        MyClass class1 = new MyClass("abc");
        MyClass class2 = new MyClass("ABC");
        sets.add(class1);
        sets.add(class2);
        System.out.println(sets);
        // 分析： 一个元素e能否被add到HashSet容器中取决于以下2点：
        // 1、元素e的hashcode()值在容器中映射到对应的桶。如果找不到对应的桶与之映射，直接可以加入到容器中。
        // 2、在条件1满足的情况下，找到对应的桶中节点p（Node）。如果p.key == e || e.equals(p.key)则不能将元素add到容器中
        //      否则创建一个新的节点newNode = new Node<>(e.hash, e, value, null);并且 p.next = newNode;
        // 所以元素能不能add到HashSet容器中取决于Hashcode和元素之间是否相等(obj1 == obj2 || obj1.equals(obj2))
        // 不能单单只看HashCode值。
    }
    static class MyClass {
        String value;
        public MyClass(String val) {
            value = val;
        }

        /**
         * MyClass对象equals返回true/false取决于value字符串域是否相等。
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof MyClass) {
                MyClass my = (MyClass) o;
                return this.value.equals(my.value);
            }
            return false;
        }

        /**
         * 所有的MyClass对象的hashcode的值都是一样的
         * @return
         */
        @Override
        public int hashCode() {
          return 1;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
