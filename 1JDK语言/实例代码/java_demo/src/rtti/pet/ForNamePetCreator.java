package rtti.pet;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment : 通过 Class.forName 方式，加载类对象到列表中
 * @since : 2019-11-05
 */
public class ForNamePetCreator extends AbstractPetCreator {
    private static List<Class<? extends Pet>> allTypes = new ArrayList<>();
    private static List<Class<? extends Pet>> types = new ArrayList<>();
    private static String[] typeNames={
            "rtti.pet.Garfield", "rtti.pet.MuttDog", "rtti.pet.PugDog",
            "rtti.pet.Cat","rtti.pet.Dog",
            "rtti.pet.Pet"
    };

    @SuppressWarnings("unchecked")
    private static void loader(){
        try{
            for (int i = 0; i < typeNames.length ; i++) {
                Class<? extends Pet> clazz = (Class<? extends Pet>)Class.forName(typeNames[i]);
                allTypes.add(clazz);
                if (!Modifier.isAbstract(clazz.getModifiers())){
                    types.add(clazz);
                }
            }
        }catch(Exception e){
            throw new RuntimeException("load class of pet");
        }
    }
    static {
        loader();
    }
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }

    @Override
    public List<Class<? extends Pet>> allTypes() {
        return allTypes;
    }
}
