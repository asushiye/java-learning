package lambda;

public class MyLambda {
    public static void main(String[] args){
        LambdaController lambdaController = new LambdaController();
        lambdaController.addService(new LambdaService(){
            public void printService(){
                System.out.println("In Java 7 you would have to implement this interface ");
            };
        });

        lambdaController.addService(()->System.out.println("In java 8 using a Java lambda expression "));

        MyComparator myComparator = (a1, a2) ->{return a1 > a2; } ;
        boolean result = myComparator.compare(2, 5);
        System.out.println(result);
    }
}
