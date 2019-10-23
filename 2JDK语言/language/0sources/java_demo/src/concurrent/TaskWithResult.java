package concurrent;

import java.util.concurrent.Callable;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-22
 */

public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult "+id;
    }
}
