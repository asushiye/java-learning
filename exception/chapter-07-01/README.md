# chapter-07-01

# Basic Java Exception Handling

# Basic try-catch-finally Exception Handling in Java




Throwing Exceptions

public void divide(int numberToDivide, int numberToDivideBy)
    throws BadNumberException{
        if(numberToDivideBy == 0){
            throw new BadNumberException("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }



Catching Exceptions


    public void callDivide(){
        try {
            int result = divide(2,1);
            System.out.println(result);
        } catch (BadNumberException e) {
            //do something clever with the exception
            System.out.println(e.getMessage());
        }
        System.out.println("Division attempt done");
    }

 
 Propagating Exceptions

     public void callDivide() throws BadNumberException{
        int result = divide(2,1);
        System.out.println(result);
    }


