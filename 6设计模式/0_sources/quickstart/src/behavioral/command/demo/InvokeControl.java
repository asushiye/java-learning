package behavioral.command.demo;

import behavioral.command.demo.command.Command;
import behavioral.command.demo.command.NullCommand;

/**
 * @author : zhenyun.su
 * @comment : 遥控器 - 只有开，关两个按钮
 * @since : 2019/8/20
 */

public class InvokeControl {
    Command[] onCommands;
    Command[] offCommands;
    Command currentCommand;
    Command noCommand;

    public InvokeControl(Integer count) {
        this.noCommand = new NullCommand();
        initCommand(count);
    }

    public void initCommand(Integer count){
        this.onCommands = new Command[7];
        this.offCommands = new Command[7];
        for (int i = 0; i < count; i++) {
            this.onCommands[i] = this.noCommand;
            this.offCommands[i] = this.noCommand;
        }
    }

    public void setCommand(Integer index, Command onComand, Command offComand){
        this.onCommands[index] = onComand;
        this.offCommands[index] = offComand;
    }

    public void onClick(Integer index){
        this.currentCommand = this.onCommands[index];
        this.currentCommand.execute();
    }
    public void offClick(Integer index){
        this.currentCommand = this.offCommands[index];
        this.currentCommand.execute();
    }

    public void undo(){
        this.currentCommand.undo();
    }
}
