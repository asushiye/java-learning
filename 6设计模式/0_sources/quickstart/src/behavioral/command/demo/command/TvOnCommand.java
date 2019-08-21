package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Tv;

/**
 * @author : zhenyun.su
 * @comment : 电视开启命令对象
 * @since : 2019/8/20
 */

public class TvOnCommand implements Command {
    private Tv tv;

    public TvOnCommand(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.on();
    }

    @Override
    public void undo() {
        this.tv.off();
    }
}
