package structure.adapter.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class GasolineAdapter implements Motor {
    private GasolineMotor gasolineMotor;

    public GasolineAdapter() {
        this.gasolineMotor = new GasolineMotor();
    }

    @Override
    public void driver() {
        this.gasolineMotor.gasolineDriver();
    }
}
