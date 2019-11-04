package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface MyAnnotation {
    String value() default "";
    String name() ;
    int age() ;
    String[] newName();
}
