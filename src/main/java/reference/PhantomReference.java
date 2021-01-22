/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/22 21:47
 * PhantomReference: 虚引用。唯一的作用： 管理堆外内存
 * VM Option: -Xmx20M 设置堆大小20M
 * 关于虚引用的讲解参考： https://www.bilibili.com/video/BV1xK4y1C7aT?p=7  10分钟左右处开始
 */
package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

public class PhantomReference {

    private static final List<Object> LIST = new LinkedList<>();

    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        java.lang.ref.PhantomReference<M> phantomReference = new java.lang.ref.PhantomReference<>(new M(), QUEUE);
        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024*1024]); // 不断申请内存
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();


        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("虚引用对象被JVM回收了" + poll);
                }
            }
        }).start();
    }
}
