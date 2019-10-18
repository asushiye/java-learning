package generic.list;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        for(String item: "hello su zhen yun".split(" ")){
            randomList.add(item);
        }
        String item = randomList.select();
        System.out.println("get "+item+" from "+randomList.toString());
        randomList.remove(item);
        System.out.println("randomList: "+randomList.toString());
    }
}
