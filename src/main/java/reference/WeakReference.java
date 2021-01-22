/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/22 21:47
 * WeakReference: 弱引用。不管有没有引用指向这个对象，只要进行了gc就会回收掉弱引用指向的对象。
 * 一次性。
 */
package reference;

public class WeakReference {
    public static void main(String[] args) {
        java.lang.ref.WeakReference<M> m = new java.lang.ref.WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
        // 问题1、为什么Entry要使用弱引用？
        // 答：若是使用强引用，即使tl = null，由于key的引用依然指向ThreadLocal对象，也会存在内存泄漏的情况。
        // 而使用弱引用不会因为key导致内存泄漏
        // 问题2、在使用完ThreadLocal对象tl后，tl = null就可以保证不会出现内存泄漏吗？（因为key是弱引用）
        // 答：key是弱引用，不会存在内存泄漏，但是不能value不是弱引用啊！！！
        // 因此在使用ThreadLocal时，要保证不出现内存泄漏，应该使用tl.remove()方法
        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        // tl = null;
        tl.remove();
    }
}
