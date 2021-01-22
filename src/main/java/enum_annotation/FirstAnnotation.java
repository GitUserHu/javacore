package enum_annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @author JiaBing
 * @date 2019-04-26 21:57:43 
 * 注解类型声明中的注解称之为元注解比如该注解声明中的 @Target 和 @Retention
 */
@Retention(RUNTIME) // 在运行时保留
@Target(METHOD) // 方法声明中才是合法的
public @interface FirstAnnotation {

}
