package demo;

import java.util.*;

public class ListDemo {

    public static void main(String[] args){
        List<String> strings= new ArrayList<String>();
        strings.add("list-item1");
        strings.add("list-item2");
        strings.add("list-item3");

        //Java Generics for Loop
        for (String string :strings)
        {
            System.out.println(string);
        }

        //Iterating a Generic List
        Iterator<String> iterators = strings.iterator();
        while(iterators.hasNext()){
            System.out.println(iterators.next());
        }

        //Ja Generics for Loop
        Set<Integer> sets = new HashSet<Integer>();
        sets.add(1);
        sets.add(2);
        for(Integer i: sets){
            System.out.println(i);
        }

        //Iterating a Generic Set
        Iterator<Integer> iterators1 = sets.iterator();
        while (iterators1.hasNext()){
            System.out.println(iterators1.next());
        }

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "value1");
        map.put(2, "value2");
        for(Integer key: map.keySet()){
            System.out.println(key+": "+map.get(key));
        }
        for (String value: map.values()) {
            System.out.println(value);
        }

        Iterator<Integer> keys =  map.keySet().iterator();
        while (keys.hasNext()){
            System.out.println(keys.next()+"= "+map.get(keys.next()));
        }

        Iterator<String> values = map.values().iterator();
        while (values.hasNext()){
            System.out.println(values.next());
        }
    }
}
