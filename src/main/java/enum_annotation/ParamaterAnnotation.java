package enum_annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @author JiaBing
 * @date 2019-04-26 22:17:11 
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ParamaterAnnotation {
	String[] value();
}
