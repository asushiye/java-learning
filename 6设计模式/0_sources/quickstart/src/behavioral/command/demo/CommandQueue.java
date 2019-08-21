package behavioral.command.demo;

import behavioral.command.demo.command.Command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/20
 */

public class CommandQueue {
    private BlockingQueue<Command> commands = new LinkedBlockingQueue<>(100);

    public void add(Command command){
        this.commands.add(command);
    }

    public Command get(){
        return this.commands.poll();
    }
}
