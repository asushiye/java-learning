package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Tv;

/**
 * @author : zhenyun.su
 * @comment : 电视开启命令对象
 * @since : 2019/8/20
 */

public class TvOffCommand implements Command {
    private Tv tv;

    public TvOffCommand(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.off();
    }

    @Override
    public void undo() {
        this.tv.on();
    }
}
