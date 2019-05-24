package demo;

import java.util.*;

public class GenericMethod {

    public static <T> T AddAndReturn(T element, Collection<T> collection){
        collection.add(element);
        return element;
    }

    public static void main(String[] args){
        String stringElement = "name";
        List<String> list =  new ArrayList<String>();
        String thesElement= GenericMethod.AddAndReturn(stringElement, list);
        System.out.println(list.iterator().next());

        Integer integerElement = 1;
        Set<Integer> set = new HashSet<Integer>();
        Integer theiElement = GenericMethod.AddAndReturn(integerElement, set);

        System.out.println(set.iterator().next());
    }
}
