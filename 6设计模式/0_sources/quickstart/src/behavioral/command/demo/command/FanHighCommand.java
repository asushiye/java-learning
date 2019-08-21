package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Fan;

/**
 * @author : zhenyun.su
 * @comment : 风扇开启高速命令对象
 * @since : 2019/8/20
 */

public class FanHighCommand implements Command {
    private Fan fan;
    private Integer preSpeed;

    public FanHighCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        preSpeed = this.fan.getSpeed();
        this.fan.high();
    }

    @Override
    public void undo() {
        this.fan.speed(preSpeed);
    }
}
