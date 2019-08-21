package behavioral.command.demo.receiver;

import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;

/**
 * @author : zhenyun.su
 * @comment : 风扇
 * @since : 2019/8/20
 */

public class Fan {
    public static final Integer HIGH = 3;
    public static final Integer MEDIUM = 2;
    public static final Integer LOW = 1;
    public static final Integer OFF = 0;
    private Integer speed = 0;

    public void high() {
        this.speed = HIGH;
        System.out.println("Fan High speed");
    }

    public void medium() {
        this.speed = MEDIUM;
        System.out.println("Fan Medium speed");
    }

    public void low() {
        this.speed = LOW;
        System.out.println("Fan Low speed");
    }

    public void off() {
        this.speed = OFF;
        System.out.println("Fan Off");
    }

    public Integer getSpeed() {
        return speed;
    }

    public void speed(Integer speed) {
        if (speed.equals(Fan.HIGH)) {
            this.high();
        } else if (speed.equals(Fan.LOW)) {
            this.low();
        } else if (speed.equals(Fan.MEDIUM)) {
            this.medium();;
        } else if (speed.equals(Fan.OFF)) {
            this.off();
        }
    }
}
