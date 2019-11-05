package rtti.pet;

import java.util.HashMap;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class TypeCounter extends HashMap<Class<?>, Integer> {
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        super();
        this.baseType = baseType;
    }

    public void count(Object o) {
        Class<?> clazz = o.getClass();
        if (baseType.isAssignableFrom(clazz)) {
            addClass(clazz);
        } else {
            throw new RuntimeException("error type");
        }
    }

    public void addClass(Class<?> c) {
        Integer value = get(c);
        this.put(c, value == null ? 1 : value + 1);
        Class<?> superClazz = c.getSuperclass();
        if (superClazz != null&& baseType.isAssignableFrom(superClazz)) {
            addClass(superClazz);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Class<?> key : this.keySet()) {
            result.append(key.getSimpleName());
            result.append("=");
            result.append(this.get(key));
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("}");
        return result.toString();
    }
}
