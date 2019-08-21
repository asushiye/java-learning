package behavioral.command.demo.command;

import behavioral.command.demo.receiver.Fan;

/**
 * @author : zhenyun.su
 * @comment : 风扇开启中速命令对象
 * @since : 2019/8/20
 */

public class FanMediumCommand implements Command {
    private Fan fan;
    private Integer preSpeed;

    public FanMediumCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        this.preSpeed = this.fan.getSpeed();
        this.fan.medium();
    }

    @Override
    public void undo() {
        this.fan.speed(preSpeed);
    }
}
