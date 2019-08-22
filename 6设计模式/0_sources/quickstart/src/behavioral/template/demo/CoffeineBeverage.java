package behavioral.template.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/22
 */

public abstract class CoffeineBeverage {
    //冲泡饮料模板方法
    public final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if (needCondiment()) {
            addCondiments();
        }
    }
    public void boilWater(){
        System.out.println("boil water");
    }
    public abstract void brew();
    public void pourInCup(){
        System.out.println("pour in cup");
    }

    public abstract void addCondiments();
    public boolean needCondiment(){
        return true;
    }
}
