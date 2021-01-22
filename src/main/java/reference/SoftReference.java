/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/22 21:46
 * SoftReference: 软引用。在分配内存并且内存不够的时候，系统会先进行一次回收，如果还是不够，软引用指向的对象就会被回收。
 * 软引用非常适合缓存使用
 * VM Option: -Xmx20M 设置堆大小20M
 */
package reference;

public class SoftReference {
    public static void main(String[] args) {
        java.lang.ref.SoftReference<byte[]> m = new java.lang.ref.SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        // 再分配一个数组，heap(堆)装不下， 这时候系统会进行垃圾回收。先回收一次，如果内存内存还是不够，系统会把软引用的占用的内存回收掉。
        byte[] b = new byte[1024*1024*11];
        System.out.println(m.get());
    }
}
