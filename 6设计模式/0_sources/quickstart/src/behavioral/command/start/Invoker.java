package behavioral.command.start;

import java.util.ArrayList;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/15
 */

public class Invoker {
    private ArrayList<Command> commands = new ArrayList<>();

    public void addCommand(Command command){
        commands.add(command);
    }

    public void removeCommand(Command command){
        commands.remove(command);
    }

    public void executeCommand(){
        for(Command command: commands){
            command.execute();
        }
    }
}
