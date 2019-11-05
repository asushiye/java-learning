package rtti.pet;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class Test {
    public static void main(String[] args) {
//        forNamePetCreat();
//        LiteralPetCreat();
        typeCounter();
    }
    public static void forNamePetCreat() {
        PetUtils.forNameCountPet(new ForNamePetCreator(), 10);
        System.out.println("---------------");
        PetUtils.classCountPet(new ForNamePetCreator(), 10);
    }

    public static void LiteralPetCreat() {
        PetUtils.classCountPet(new LiteralPetCreator(), 10);
    }

    public static void typeCounter(){
        PetUtils.typeCounter(new LiteralPetCreator(), 5);
    }
}
