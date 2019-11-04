package generic.stack;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-12
 */

public class Test {
    public static void main(String[] args) {
        LinkedStack<Job> linkedStack = new LinkedStack<>();
        linkedStack.push(new Job("job1"));
        linkedStack.push(new Job("job2"));
        linkedStack.push(new Job("job3"));

        Job job3 = linkedStack.pop();
        Job job2 = linkedStack.pop();
        System.out.println("job3="+job3.getName());
        System.out.println("job2="+job2.getName());
        System.out.println("job1="+linkedStack.pop().getName());
    }
}
