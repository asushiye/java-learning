package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Fan;

/**
 * @author : zhenyun.su
 * @comment : 风扇开启低速命令对象
 * @since : 2019/8/20
 */

public class FanLowCommand implements Command {
    private Fan fan;
    private Integer preSpeed;

    public FanLowCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        this.preSpeed = this.fan.getSpeed();
        this.fan.low();
    }

    @Override
    public void undo() {
        this.fan.speed(preSpeed);
    }
}
