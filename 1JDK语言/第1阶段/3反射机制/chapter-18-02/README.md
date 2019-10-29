# Java Reflection detail

chapter-18-02

		Reflection - Constructors
		Reflection - Field
		Reflection - Method
		Java Reflection - Getters and Setters
		Private Fields and Methods

## Reflection - Constructors

### get Constructor

`Constructor[] constructors =  DaphneCar.class.getConstructors();`

`Constructor constructor =  DaphneCar.class.getConstructor(String.class);`

### create instance from Constructor

`DaphneCar daphneCar = (DaphneCar)constructor.newInstance("shanghai");`


```
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

```

## Reflection - Field


### get all public Field 
```
            Field[] fields =  DaphneCar.class.getFields();
            for(Field sfield: fields){
                System.out.println(sfield.getName());
            }

```

### get field for fieldName
```
            DaphneCar daphneCar = new DaphneCar("shanghai");
            Field addressField =  DaphneCar.class.getField("address");
            Object fieldType = addressField.getType();
            System.out.println(addressField.getName()+": "+fieldType.toString());
```

### Getting and Setting Field Values

```
            Field addressField =  DaphneCar.class.getField("address");
	    addressField.set(daphneCar, "beijing");
            
            String addressValue = (String)addressField.get(daphneCar);
            System.out.println(addressValue);
```


```
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
```

### Reflection - Method

#### obtaining method object
```

Method[] methods = DaphneCar.class.getMethods();

Method diverMethod = DaphneCar.class.getMethod("drive", String.class);

```

If the method you are trying to access takes no parameters, pass null as the parameter type array, like this:

`Method method =  aClass.getMethod("doSomething", null);`

#### Method Parameters and Return Types

```
Parameter[] parameters= diverMethod.getParameters();

Class[] parameterTypes = diverMethod.getParameterTypes();

Class returnType = diverMethod.getReturnType();
```


#### Invoke method

```
	    Method diverMethod = DaphneCar.class.getMethod("drive", String.class);
            DaphneCar daphneCar = new DaphneCar("shanghai");
            diverMethod.invoke(daphneCar, "daphne");

The null parameter is the object you want to invoke the method on. If the method is static you supply null instead of an object instance.

diverMethod.invoke(null, "daphne");
```


instance

```
    public static void classMethodObject() {
        try{
            
            Method diverMethod = DaphneCar.class.getMethod("drive", String.class);
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


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
```


### Java Reflection - Getters and Setters

* Getter
> A getter method have its name start with "get", take 0 parameters, and returns a value. 

* Setter
> A setter method have its name start with "set", and takes 1 parameter.

```
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



    public static void classMethodObject() {
        try{
            Method[] methods = DaphneCar.class.getMethods();
            for (Method method: methods){
                if (isGetter(method)) System.out.println("getter: "+method);
                if (isSetter(method)) System.out.println("setter: "+method);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

```



DaphneCar

## Private Fields and Methods

add private static method

```
    private static void addOil() {
        System.out.println("gei oil for me");
    }
```

the use of the method getDeclaredMethod("addOil", null). It is this method call that returns the private method


```
            Method  addOilMethod = DaphneCar.class.getDeclaredMethod("addOil", null);
            addOilMethod.setAccessible(true);
            addOilMethod.invoke(null, null);
```

By calling Method.setAcessible(true) you turn off the access checks for this particular Method instance, for reflection only

