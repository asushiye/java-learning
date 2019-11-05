package rtti.pet;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :通过 类字面常量 方式，加载类对象到列表中
 * @since : 2019-11-05
 */

public class LiteralPetCreator extends AbstractPetCreator{
    private static List<Class<? extends Pet>> allTypes = new ArrayList<>();
    private static List<Class<? extends Pet>> types = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private static void loader(){
        allTypes.addAll(Collections.unmodifiableCollection(Arrays.asList(
                Garfield.class, MuttDog.class, PugDog.class,
                Cat.class, Dog.class,
                Pet.class
        )));
        for (Class clazz: allTypes){
            if (!Modifier.isAbstract(clazz.getModifiers())){
                types.add(clazz);
            }
        }
    }
    static {
        loader();
    }
    @Override
    public List<Class<? extends Pet>> allTypes() {
        return allTypes;
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
