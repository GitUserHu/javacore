/**
 * String拼接
 * User: JiaB
 * Date: 2020/9/3 22:29
 */
package stringbufferbuilder;

import org.junit.jupiter.api.Test;

public class SpliceString {
    @Test
    void test() {
        String str = "";
        for (int i = 0; i < 100; i++) {
            str = str + i;
        }
        System.out.println(str);
    }
}
