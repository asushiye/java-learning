
public class MyException {

    public static float divide(int numberToDivide, int numberToDivideBy)
            throws Exception{
        if(numberToDivideBy == 0){
            throw new Exception("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }

    public static void main(String[] args){
        this.divide(2,0);
    }
}
