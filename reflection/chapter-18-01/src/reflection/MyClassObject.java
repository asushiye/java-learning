package reflection;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;

public class MyClassObject {
    public static void main(String[] args) {

       // MyClassObject.classMainInfo();
       // MyClassObject.classHashCodeAndEquals();
        System.out.println("------------------------------");

       // MyClassObject.classConstructor();
       // MyClassObject.classFieldObject();
       // MyClassObject.classMethodObject();
       // MyClassObject.classAnnotationObject();
        //MyClassObject.classGenericsObject();
        MyClassObject.reflectionArray();

    }

    public static void classMainInfo(){
        Class myClass = DaphneCar.class;

        System.out.println(myClass.getName());
        System.out.println(myClass.getSimpleName());
        Package myPackage = myClass.getPackage();
        System.out.println(myPackage.getName());

        Class superClass = myClass.getSuperclass();
        System.out.println(superClass.getName());

        Constructor[] myConstructors = myClass.getConstructors();
        Class[] interfaces = myClass.getInterfaces();
        Method[] methods = myClass.getMethods();
        Field[] fields = myClass.getFields();
        Annotation[]  annotations = myClass.getAnnotations();

        System.out.println("Field count: "+fields.length);
        for (Field ann: fields) {
            System.out.println(ann.toString());
        }

        for (Method ann: methods) {
            System.out.println(ann.toString());
        }

        int modifiers = myClass.getModifiers();
        System.out.println("isAbstract: "+ Modifier.isAbstract(modifiers));
        System.out.println("isFinal: "+ Modifier.isFinal(modifiers));
        System.out.println("isInterface: "+ Modifier.isInterface(modifiers));
        System.out.println("isNative: "+ Modifier.isNative(modifiers));
        System.out.println("isPrivate: "+ Modifier.isPrivate(modifiers));
        System.out.println("isProtected: "+ Modifier.isProtected(modifiers));
        System.out.println("isPublic: "+ Modifier.isPublic(modifiers));
        System.out.println("isStatic: "+ Modifier.isStatic(modifiers));
        System.out.println("isStrict: "+ Modifier.isStrict(modifiers));
        System.out.println("isSynchronized: "+ Modifier.isSynchronized(modifiers));
        System.out.println("isTransient: "+ Modifier.isTransient(modifiers));
        System.out.println("isVolatile: "+ Modifier.isVolatile(modifiers));
    }

    public static void classHashCodeAndEquals(){

        DaphneCar daphneCar = new DaphneCar("ddd");
        System.out.println("daphneCar.hashCode: "+ daphneCar.hashCode());
        DaphneCar daphneCar1 = new DaphneCar("ddd");
        System.out.println("daphneCar1.hashCode: "+ daphneCar1.hashCode());

        System.out.println("daphneCar.equals(daphneCar1): "+ daphneCar.equals(daphneCar1));
    }

    public static void classConstructor(){
        Class myClass = DaphneCar.class;
        Constructor[] constructors =  myClass.getConstructors();

        for(Constructor con : constructors){
            System.out.println(con.getName());
            Class[] parameterTypes = con.getParameterTypes();
            for (Class var1: parameterTypes){
                System.out.println(var1.getName());
            }
        }

        try {
            Constructor constructor = myClass.getConstructor(String.class);
            DaphneCar daphneCar = (DaphneCar)constructor.newInstance("shanghai");
            System.out.println(daphneCar.getAddress());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void classFieldObject() {
        try{
            Field[] fields =  DaphneCar.class.getFields();
            for(Field sfield: fields){
                System.out.println(sfield.getName());
            }

            DaphneCar daphneCar = new DaphneCar("shanghai");
            Field addressField =  DaphneCar.class.getField("address");
            Object fieldType = addressField.getType();
            System.out.println(addressField.getName()+": "+fieldType.toString());

            System.out.println(daphneCar.getAddress());
            addressField.set(daphneCar, "beijing");

            String addressValue = (String)addressField.get(daphneCar);
            System.out.println(addressValue);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void classMethodObject() {
        try{
            Method diverMethod = DaphneCar.class.getMethod("drive", String.class);
            System.out.println(diverMethod);
            DaphneCar daphneCar = new DaphneCar("shanghai");
            //The null parameter is the object you want to invoke the method on.
            // If the method is static you supply null instead of an object instance.
            diverMethod.invoke(daphneCar, "daphne");


            Parameter[] parameters= diverMethod.getParameters();
            for (Parameter parmater: parameters) {
                System.out.println(parmater.toString()+" type is "+parmater.getType());
            }

            Class[] parameterTypes = diverMethod.getParameterTypes();
            for (Class param: parameterTypes){
                System.out.println(param.getName()+" type is "+param.getTypeName());
            }
            Class returnType = diverMethod.getReturnType();
            System.out.println("return type: "+returnType.getName());

            //setting getting
            Method[] methods = DaphneCar.class.getMethods();
            for (Method method: methods){
                if (isGetter(method)) System.out.println("getter: "+method);
                if (isSetter(method)) System.out.println("setter: "+method);
            }

            Method  addOilMethod = DaphneCar.class.getDeclaredMethod("addOil", null);
            addOilMethod.setAccessible(true);
            addOilMethod.invoke(null, null);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }

    public static void classAnnotationObject() {
        try{
            // Class Annotation
            Annotation[] classAnnotations = DaphneCar.class.getAnnotations();
            for (Annotation annotation: classAnnotations){
                if (annotation instanceof MyTypeAnnotation){
                    MyTypeAnnotation myTypeAnnotation = (MyTypeAnnotation)annotation;
                    System.out.println("Annotation from class: "+myTypeAnnotation.name()+"= "+myTypeAnnotation.value());
                }
            }

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

            //Field Annotation
            Field field = DaphneCar.class.getField("address");
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation: fieldAnnotations){
                if (annotation instanceof MyFieldAnnotation){
                    MyFieldAnnotation myFieldAnnotation = (MyFieldAnnotation)annotation;
                    System.out.println("Annotation from Field: "+myFieldAnnotation.name()+"= "+myFieldAnnotation.value());
                }
            }

            //Parameter Annotation
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

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void classGenericsObject() {
        try {
            //Generic Method Return Types
            Method getMethod =  DaphneCar.class.getMethod("getStringList");
            Type returnType = getMethod.getGenericReturnType();
            System.out.println(returnType.getClass());

            if (returnType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)returnType;
                System.out.println(parameterizedType);

                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                for (Type type: typeArguments){
                    System.out.println("retrunTypeArgClass = " + (Class)type);
                }
            }

            //Generic Method Paramter Types
            Method setMethod =  DaphneCar.class.getMethod("setStringList", List.class);
            Type[] genericParameterTypes = setMethod.getGenericParameterTypes();
            System.out.println(genericParameterTypes.getClass());

            for (Type genericParameterType: genericParameterTypes) {
                if (genericParameterType instanceof ParameterizedType){
                    ParameterizedType aType = (ParameterizedType)genericParameterType;
                    System.out.println(aType);
                    Type[] typeArguments = aType.getActualTypeArguments();
                    for (Type type: typeArguments){
                        System.out.println("parameterTypeArgClass = " + (Class)type);
                    }
                }
            }

            //Generic Field  Types
            Field field = DaphneCar.class.getField("stringList");
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericType;
                System.out.println(aType);
                Type[] fieldArgTypes = aType.getActualTypeArguments();
                for(Type fieldArgType : fieldArgTypes){
                    Class fieldArgClass = (Class) fieldArgType;
                    System.out.println("fieldArgClass = " + fieldArgClass);
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void reflectionArray(){
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));

        try {
            Class intArrayClass = Class.forName("[I");
            Class stringArrayClass = Class.forName("[Ljava.lang.String;");
            System.out.println(intArrayClass.getName());
            System.out.println(stringArrayClass);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
