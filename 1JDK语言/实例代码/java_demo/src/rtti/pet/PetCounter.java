package rtti.pet;

import java.util.LinkedHashMap;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
    public PetCounter(AbstractPetCreator abstractPetCreator){
        for (Class<? extends Pet> clazz : abstractPetCreator.allTypes()) {
            this.put(clazz, 0);
        }
    }

    public void count(Class<? extends Pet> type) {
        put(type, get(type) + 1);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for(Class<? extends Pet> key: this.keySet()){
            result.append(key.getSimpleName());
            result.append("=");
            result.append(this.get(key));
            result.append(", ");
        }
        result.delete(result.length()-2, result.length());
        result.append("}");
        return result.toString();
    }
}
