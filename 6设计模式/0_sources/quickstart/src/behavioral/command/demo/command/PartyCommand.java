package behavioral.command.demo.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/20
 */

public class PartyCommand implements Command {
    private List<Command> commands= new ArrayList<>();
    public PartyCommand() {

    }

    public void addCommand(Command command){
        commands.add(command);
     }

     public void clearCommand(){
        commands.clear();
     }

    @Override
    public void execute() {
        for(Command command: commands){
            command.execute();
        }
    }

    @Override
    public void undo() {
        for(Command command: commands){
            command.undo();
        }
    }
}
