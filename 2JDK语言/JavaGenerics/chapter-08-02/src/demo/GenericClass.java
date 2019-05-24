package demo;

public class GenericClass {
    public static void main(String[] args){

        GenericFactory<GenericEntity> factory =  new GenericFactory<GenericEntity>(GenericEntity.class);
     //   GenericEntity MyGenericEntity =  (GenericEntity) factory.createInstance();
     //   MyGenericEntity.println();

    }
}
