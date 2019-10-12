package generic;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-12
 */

public class Test {
    public static void main(String[] args) {
        Holder1 holder1 = new Holder1();
        holder1.setA(new AutoMobile());
//        holder1.setA(new AutoTv());

        Holder2 holder2 = new Holder2();
        holder2.setA(new AutoMobile());
        holder2.setA(new AutoTv());
        AutoMobile mobile2 = (AutoMobile)holder2.getA();

        Holder3<AutoMobile> mobileHolder3 = new Holder3<>();
        mobileHolder3.setA(new AutoMobile());
        AutoMobile mobile3 = mobileHolder3.getA();

        Holder3<AutoTv> tvHolder3 = new Holder3<>();
        tvHolder3.setA(new AutoTv());
    }
}
