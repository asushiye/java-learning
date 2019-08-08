package structure.adapter.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class ElectricAdapter implements Motor {

    private ElectricMotor electricMotor;

    public ElectricAdapter() {
        this.electricMotor = new ElectricMotor();
    }

    @Override
    public void driver() {
        this.electricMotor.electricDriver();
    }
}
