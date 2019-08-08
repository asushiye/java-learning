package structure.adapter;

import structure.adapter.demo.ElectricAdapter;
import structure.adapter.demo.GasolineAdapter;
import structure.adapter.demo.Motor;
import structure.adapter.start.Adaptee;
import structure.adapter.start.ClassAdapter;
import structure.adapter.start.ObjectAdapter;
import structure.adapter.start.Target;
import structure.adapter.twoway.Source;
import structure.adapter.twoway.TwoWayAdapter;

/**
 * @author : zhenyun.su
 * @comment : 适配器模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
        classAdapterTest();
        //对象适配器
        objectAdapterTest();
        //适配器模式实战演练
        motorAdapterTest();
        //动态配置来完成适配
        motorDynamicAdapterTest("structure.adapter.demo.ElectricAdapter");
        //双向适配器
        twoWayAdapterTest();
    }

    public static void classAdapterTest(){
        Target target = new ClassAdapter();
        target.request();
    }

    public static void objectAdapterTest(){
        Target target = new ObjectAdapter(new Adaptee());
        target.request();
    }

    public static void motorAdapterTest(){
        Motor electricMotor = new ElectricAdapter();
        electricMotor.driver();

        Motor gasolineMotor = new GasolineAdapter();
        gasolineMotor.driver();
    }

    public static void motorDynamicAdapterTest(String adapterName){
        try {
            Class<?> clazz = Class.forName(adapterName);
            Object object =clazz.newInstance();
            Motor motor = (Motor)object;
            motor.driver();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //双向适配器
    public static void twoWayAdapterTest() {
        Source source = new TwoWayAdapter();
        source.soureRequest();

        structure.adapter.twoway.Target  target = new TwoWayAdapter();
        target.targetRequest();
    }
}
