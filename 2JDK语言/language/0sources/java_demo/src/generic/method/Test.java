package generic.method;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public class Test {
    public static void main(String[] args) {
        getClassName();
        collectionNew();
        genericVarargs();
    }

    public static void getClassName() {
        GenericMethods genericMethods = new GenericMethods();
        genericMethods.getClassName(1);
        genericMethods.getClassName(1L);
        genericMethods.getClassName(1.0f);
        genericMethods.getClassName(1.0d);
        genericMethods.getClassName("hello");
        genericMethods.getClassName('c');
    }
    public static void collectionNew() {
        GenericMethods genericMethods = new GenericMethods();

        ArrayList<String> arrayList= CollectionNewUtils.arrayList();
        genericMethods.getExClassName(arrayList);

        LinkedList<Integer> linkedList= CollectionNewUtils.linkedList();
        genericMethods.getExClassName(linkedList);
    }

    public static void genericVarargs() {
        List<String> list = GenericVarargs.makeList("su", "zhen", "yun");
        System.out.println("list:"+list);
    }
}