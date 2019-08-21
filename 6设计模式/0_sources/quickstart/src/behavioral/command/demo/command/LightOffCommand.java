package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Light;

/**
 * @author : zhenyun.su
 * @comment : 灯关闭命令对象
 * @since : 2019/8/20
 */

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }

    @Override
    public void undo() {
        this.light.on();
    }
}
