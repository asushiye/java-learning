# Java Reflection - Annotations

chapter-18-03

sing Java Reflection you can access the annotations attached to Java classes at runtime

		Custom Annotations
		Custom Class use Annotations
		Class Annotations
		Method Annotations
		Field Annotations
		Parameter Annotations


Annotations are a kind of comment or meta data you can insert in your Java code. These annotations can then be processed at compile time by pre-compiler tools, or at runtime via Java Reflection

refer to annotation chapter-01-07 (Java Annotation)

## Custom Annotations

### Type Annotation

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyTypeAnnotation {
    public String name();
    public String value();
}

```

### Method Annotation

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyMethodAnnotation {
    public String name();
    public String value();
}
```

### Field Annotation

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyFieldAnnotation {
    public String name();
    public String value();
}
```

### Paramter Annotation

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface MyParameterAnnotation {
    public String name();
    public String value();
}
```


## Custom Class use Annotations


```
@MyTypeAnnotation(name = "Annotation", value = "Class")
public class DaphneCar extends Car implements Drivable {

    @MyFieldAnnotation(name = "Annotation", value = "Field")
    public String address;

    public DaphneCar(String address) {
        this.address = address;
    }

    @Override
    @MyMethodAnnotation(name="Annotation", value = "Method")
    public void drive(@MyParameterAnnotation(name = "Annotation", value = "Parameter") String name) {
        System.out.println("drive car is "+name);
    }

    ...

}
```


## Class Annotations

You can access class annotations like this:

```
            // Class Annotation
            Annotation[] classAnnotations = DaphneCar.class.getAnnotations();
            for (Annotation annotation: classAnnotations){
                if (annotation instanceof MyTypeAnnotation){
                    MyTypeAnnotation myTypeAnnotation = (MyTypeAnnotation)annotation;
                    System.out.println("Annotation from class: "+myTypeAnnotation.name()+"= "+myTypeAnnotation.value());
                }
            }
```


## Method Annotations

You can access method annotations like this:

```
            //Method Annotation
            Method driveMethod =  DaphneCar.class.getMethod("drive", String.class);
            Annotation[] methodAnnotations = driveMethod.getDeclaredAnnotations();
            for (Annotation annotation: methodAnnotations){
                if (annotation instanceof MyMethodAnnotation){
                    MyMethodAnnotation myMethodAnnotation = (MyMethodAnnotation)annotation;
                    System.out.println("Annotation from Method: "+myMethodAnnotation.name()+"= "+myMethodAnnotation.value());
                }else{
                    System.out.println(annotation);
                }
            }
```

## Field Annotations

You can access field annotations like this:

```
            //Field Annotation
            Field field = DaphneCar.class.getField("address");
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation: fieldAnnotations){
                if (annotation instanceof MyFieldAnnotation){
                    MyFieldAnnotation myFieldAnnotation = (MyFieldAnnotation)annotation;
                    System.out.println("Annotation from Field: "+myFieldAnnotation.name()+"= "+myFieldAnnotation.value());
                }
            }
```


## Parameter Annotations

You can access parameter annotations like this:

```
           //Parameter Annotation
	    Method driveMethod =  DaphneCar.class.getMethod("drive", String.class);
            Annotation[][] paramAnnotationss = driveMethod.getParameterAnnotations();
            Parameter[] parameters = driveMethod.getParameters();

            int i=0;
            for (Annotation[] paramAnnotations: paramAnnotationss){
                System.out.println(parameters[i++].toString());
                for (Annotation paramAnnotaion: paramAnnotations){
                    if (paramAnnotaion instanceof MyParameterAnnotation) {
                        MyParameterAnnotation myParameterAnnotation = (MyParameterAnnotation)paramAnnotaion;
                        System.out.println("Annotation from Field: "+myParameterAnnotation.name()+"= "+myParameterAnnotation.value());
                    }
                }
            }
```


