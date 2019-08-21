package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Light;

/**
 * @author : zhenyun.su
 * @comment : 灯开启命令对象
 * @since : 2019/8/20
 */

public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }

    @Override
    public void undo() {
        this.light.off();
    }
}
