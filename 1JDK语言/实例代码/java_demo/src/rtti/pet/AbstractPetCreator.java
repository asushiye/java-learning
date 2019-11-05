package rtti.pet;

import java.util.List;
import java.util.Random;

/**
 * @author : zhenyun.su
 * @comment : 通过类对象，创建宠物实例
 * @since : 2019-11-05
 */

public abstract class AbstractPetCreator {
    private Random random = new Random(47);
    public abstract List<Class<? extends Pet>> allTypes();
    public abstract List<Class<? extends Pet>> types();

    public Pet randomPet(){
        int n = random.nextInt(types().size());
        try{
            return types().get(n).newInstance();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Pet[] createArray(int size){
        Pet[]  pets = new Pet[size];
        for (int i = 0; i < size; i++) {
            pets[i] = randomPet();
        }
        return pets;
    }
}
