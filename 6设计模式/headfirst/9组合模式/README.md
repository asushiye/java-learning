# 9组合模式

    业务场景
    面向对象解决方法
    代码实现
    模式定义
    深度和其他模式差异及主要应用在哪些场景

组合模式核心思想是解决菜单和子菜单问题。

## 业务场景

我们面对场景，使用什么方式来表示菜单，嵌套菜单，菜单项

![composite0](composite0.png)

## 面向对象解决方法


![composite1](composite1.png)

![composite2](composite2.png)


## 代码实现

### 定义菜单组合抽象类
```java
package headfirst.composite.scene;
import java.util.Iterator;
/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */
public abstract class MenuComponent {
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException("can not add MenuComponent");
    }
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException("can not remove MenuComponent");
    }
    public MenuComponent getChild(int i){
        throw new UnsupportedOperationException("can not get child MenuComponent");
    }

    public Object getData(){
        throw new UnsupportedOperationException("can not get Menu Data");
    }

    public void print(){
        throw new UnsupportedOperationException("can not print Menu Data");
    }
    public Iterator createIterator(){
        throw new UnsupportedOperationException("can not get iterator");
    }
}
```

### 定义菜单项
```java
package headfirst.composite.scene;
import java.util.Iterator;
/**
 * @author : zhenyun.su
 * @comment :  为菜单项提供空迭代器
 * @since : 2019/8/27
 */
public class MenuItem extends MenuComponent {
    private Object data;
    @Override
    public Object getData(){
        return data;
    }
    public MenuItem(Object data) {
        this.data = data;
    }
    @Override
    public void print(){
        System.out.println(data.toString());
    }
    @Override
    public Iterator createIterator() {
        return new NullIterator();
    }
}
```

### 定义菜单

```java
package headfirst.composite.scene;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */
public class Menu extends MenuComponent{
    private ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    private Object data;
    public Menu(Object data) {
        this.data = data;
    }
    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }
    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }
    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }
    @Override
    public void print() {
        System.out.println("-------------");
        System.out.println(data.toString());
    }
    @Override
    public Iterator createIterator() {
        return new CompositeIterator(menuComponents.iterator());
    }
}
```

### 定义组合迭代器
用于遍历该节点下所有组合节点和叶子节点
```java
package headfirst.composite.scene;
import java.util.Iterator;
import java.util.Stack;
/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */
public class CompositeIterator implements Iterator {
    private Stack stack= new Stack();
    public CompositeIterator(Iterator iterator) {
        this.stack.push(iterator);
    }
    @Override
    public boolean hasNext() {
        if (stack.empty()){
            return false;
        }else{
            Iterator iterator = (Iterator) stack.peek();
            if (!iterator.hasNext()){
                stack.pop();
                return hasNext();
            }else{
                return true;
            }
        }
    }
    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            if (menuComponent instanceof Menu){
                stack.push(menuComponent.createIterator());
            }
            return menuComponent;
        }
        return null;
    }
    @Override
    public void remove() {
        throw  new UnsupportedOperationException();
    }
}

```

### 定义空迭代器

用于处理叶子节点的迭代器
```java
public class NullIterator implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }
    @Override
    public Object next() {
        return null;
    }
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
```

### 女服务员

```java
public class Waitress {
    MenuComponent allMenus;
    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    public void printIterator(){
        allMenus.print();
        Iterator iterator = allMenus.createIterator();
        while (iterator.hasNext()){
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            menuComponent.print();
        }
    }
}
```

### 编写测试

```java
    public static void main(String[] args) {
        //早餐菜单
        MenuComponent breakfastMenu = new Menu(new MenuData("breakfastMenu","breakfast Menu"));
        breakfastMenu.add(new MenuItem(new MenuItemData("noodle", "Onion oil noodles", true, 6.0f)));
        breakfastMenu.add(new MenuItem(new MenuItemData("milk", "Pure milk", false, 3.5f)));
        breakfastMenu.add(new MenuItem(new MenuItemData("Hot dog", "Hot dog", true, 5.0f)));
        breakfastMenu.add(new MenuItem(new MenuItemData("Meat bag", "Meat bag", true, 2.0f)));
        //咖啡子菜单
        MenuComponent coffeeSubMenu = new Menu(new MenuData("coffeeSubMenu","lunch coffee subMenu"));
        coffeeSubMenu.add(new MenuItem(new MenuItemData("Mocha Coffee", "Mocha Coffee", false, 25.0f)));
        coffeeSubMenu.add(new MenuItem(new MenuItemData("Blue Mountain Coffee", "Blue Mountain Coffee", false, 45.5f)));
        //午餐菜单
        MenuComponent lunchMenu = new Menu(new MenuData("lunchMenu","lunch Menu"));
        lunchMenu.add(new MenuItem(new MenuItemData("Beef Rice", "Beef Rice", false, 11.0f)));
        lunchMenu.add(new MenuItem(new MenuItemData("beef noodle", "beef noodle", false, 12.5f)));
        lunchMenu.add(new MenuItem(new MenuItemData("Potato rice", "Potato rice", true, 9.0f)));
        lunchMenu.add(coffeeSubMenu);

        MenuComponent allMenu = new Menu(new MenuData("allMenu","all Menu"));
        allMenu.add(breakfastMenu);
        allMenu.add(lunchMenu);

        Waitress waitress = new Waitress(allMenu);

        waitress.printIterator();
    }
```

## 模式定义

组合模式(composite Pattern)定义：允许你将对象组合树形结构来表现*整体和部分*层次结构，

组合能让客户以一致的方式处理个别对象以及对象集合。

1. 使用组合模式让我们能用树形结构的方式创建对象结构，树里面包含了组合以及个别的对象。
2. 使用组合结构我们能把相同操作应用在组合和个别对象上。


## 深度和其他模式差异及主要应用在哪些场景

### 模板方法中，何时使用实现方法，抽象方法，钩子方法

1. 实现方法，不变的逻辑， 所有子类都具有相同逻辑，放在抽象类实现
2. 抽象方法，会变得逻辑， 所有子类都具有不同逻辑，放子类中实现
3. 钩子方法，用于影响模板算法结构。使模板算法更有弹性

子类必须实现所有抽象类的抽象方法，这样模板算法是完整的

抽象方法不是越多越好，也不是越少越好，越少比较没有弹性，越多子类实现很麻烦，具体看业务情况而定。

### 好莱坞原则
好莱坞原则：别调用我们，我们会调用你。防止类之间**依赖腐败**

依赖腐败
```
高层组件依赖底层组件，底层组件又依赖边侧组件，边侧组件又依赖高层组件，
依赖腐败就发生了，没人能搞懂这个系统设计
```
好莱坞原则是高层组件对待底层组件的方式别来调用我们，我们会来调用你的。
![template5](template5.png)

回顾模板方法设计模式，高层CoffeineBeverage实现模板方法，在需要时候才会调用底层子类的方法。

### 好莱坞原则和依赖倒置原则

依赖倒置原则是尽量避免使用具体类，而使用超类，接口或抽象类，

而好莱坞原则在创建组件上一种技巧，将底层组件挂钩在算法中，在需要时调用子类的方法，而不是依赖底层组件。

当然并不是底层就不能调用高层组件了，在需要时也会调用。

模板方法模式：子类来决定如何实现算法中的步骤。

策略模式：封装可互换的行为，然后用委托的方式来决定要使用哪种行为。

工厂模式：由子类来决定实例化哪个具体类。
