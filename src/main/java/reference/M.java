/**
 * Created with IntelliJ IDEA.
 * User: JiaB
 * Date: 2020/9/22 21:52
 */
package reference;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize");
    }
}
