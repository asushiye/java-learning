package generic.stack;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-12
 */

public class LinkedStack <T> {
    private static class Node<U>{
        U item;
        Node<U> next;
        Node(){ this.item = null;this.next = null;}
        Node(U item, Node<U> node){
            this.item = item;
            this.next = node;
        }
        boolean end(){return (item==null&& next==null);}
    }
    private Node<T> top = new Node<T>();

    public Boolean hasNext(){
        return !top.end();
    }

    public void push(T item){
        top = new Node<T>(item, top);
    }

    public T pop(){
        T item = top.item;
        while (hasNext()){
            top = top.next;
            break;
        }
        return item;
    }
}
