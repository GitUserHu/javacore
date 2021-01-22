/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/15 21:51
 * java -XX:+PrintCommandLineFlags -version
 * -XX:InitialHeapSize=266992448 -XX:MaxHeapSize=4271879168 -XX:+PrintCommandLineFlags
 * -XX:+UseCompressedClassPointers 表示使用压缩指针，指针是8个字节，压缩后就是4个字节。
 *                              ClassPointer指的是实际的对象内存中类指针，指向该对象所属的类。
 * -XX:+UseCompressedOops Oop: Ordinary Object Pointer，即普通对象指针。该选项表示压缩普通对象指针，所以压缩后是4个字节。
 *                             Object object = new Object(); object就是普通对象指针。
 * -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC

 * Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
 * 这里的64-bit表示64位即8个字节，所以指针就是8个字节。
 *   ----------------
 *  | MarkWord      |
 *  |---------------|
 *  | ClassPointer  |
 *  |---------------|   ------>  new Object() 普通对象。
 *  | Instance Data |
 *  |---------------|
 *  |  Padding      |
 *  |---------------|
 *
 *  MarkWord + ClassPointer = 对象头。
 *  MarkWord占8个字节
 *  ClassPointer在压缩ClassPointer的情况下占4个字节，否则占8个字节
 *  Instance Data实例数据，Object对象没有实例数据。实例数据指的是成员变量。
 *  Padding表示对其，因为JVM需要保证每个对象的所占内存的大小必须为8的整数倍，不满则补齐。
 *
 *  面试问题： Object o = new Object()占多少字节？
 *  1、不压缩指针的情况下：new Object()占16个字节= 8字节(MarkWord)+4字节(ClassPointer)+4字节(Padding)
 *                      +
 *                      Object object 占 4个字节(Oop: Ordinary Object Pointer)
 *                      = 20字节
 *  2、压缩指针的情况下： new Object()占16个字节= 8字节(MarkWord)+8字节(ClassPointer)
 *                       +
 *                       Object object 占 8个字节(Oop: Ordinary Object Pointer)
 *                       = 24字节
 * */
package jvm;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

public class ObjectClassLayout {
    @Test
    void test() {
        Object o = new Object();
        o.hashCode(); // 当调用了对象的hashcode方法后MarkWord里面的31位的hashcode位存入hashcode值。
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            // 锁的信息记录在MarkWord里面
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        TestObj testObj = new TestObj();
        System.out.println(ClassLayout.parseInstance(testObj).toPrintable());

    }
}
class TestObj {
    long l;
}
