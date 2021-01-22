/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/22 21:46
 * Normal Reference: 强引用. 当没有引用指向这个对象的时候，这个对象就会被回收
 */
package reference;

import java.io.IOException;

public class NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        // m = null;
        System.gc();
    }
}

