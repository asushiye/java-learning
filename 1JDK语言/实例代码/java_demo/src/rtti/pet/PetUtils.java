package rtti.pet;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @comment : 统计不同宠物的计数
 * @since : 2019-11-05
 */

public class PetUtils {

    public static void forNameCountPet(AbstractPetCreator abstractPetCreator, int size) {
        PetCounter counter = new PetCounter(abstractPetCreator);

        for (Pet pet : abstractPetCreator.createArray(size)) {
            System.out.println(pet.getClass().getName());
            if (pet instanceof MuttDog) {
                counter.count(MuttDog.class);
            };
            if (pet instanceof PugDog) {
                counter.count(PugDog.class);
            };
            if (pet instanceof Garfield) {
                counter.count(Garfield.class);
            };
            if (pet instanceof Cat) {
                counter.count(Cat.class);
            };
            if (pet instanceof Dog) {
                counter.count(Dog.class);
            };
            if (pet instanceof Pet) {
                counter.count(Pet.class);
            };
        }
        System.out.println(counter);
    }

    public static void classCountPet(AbstractPetCreator abstractPetCreator, int size) {
        PetCounter counter = new PetCounter(abstractPetCreator);

        for (Pet pet : abstractPetCreator.createArray(size)) {
            System.out.println(pet.getClass().getName());
            for(Map.Entry<Class<? extends Pet>, Integer> pair: counter.entrySet()){
                if (pair.getKey().isInstance(pet)){
                    counter.put(pair.getKey(), pair.getValue()+1);
                }
            }
        }
        System.out.println(counter);
    }

    public static void typeCounter(AbstractPetCreator abstractPetCreator, int size) {
        TypeCounter counter = new TypeCounter(Pet.class);

        for (Pet pet : abstractPetCreator.createArray(size)) {
            System.out.println(pet.getClass().getName());
            counter.count(pet);
        }
        System.out.println(counter);
    }
}
