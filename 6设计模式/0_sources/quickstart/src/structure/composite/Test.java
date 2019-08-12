package structure.composite;


import structure.composite.start.Employee;

/**
 * @author : zhenyun.su
 * @comment : 组合模式（Composite Pattern）, 树形结构，用户在树形菜单，组织结构等等
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        //快速入门
        startTest();
    }

    public static void startTest() {
        Employee ceo = new Employee("szy","IT", 200000);

        Employee devManger = new Employee("devManger","DEV", 20000);

        Employee dev1 = new Employee("dev1","DEV", 20000);
        Employee dev2 = new Employee("dev2","DEV", 20000);

        devManger.add(dev1);
        devManger.add(dev2);

        ceo.add(devManger);

        System.out.println(ceo.toString());
        for(Employee employee: ceo.getSubordinates()){
            System.out.println(employee.toString()+", parent="+ceo.getName());
            for (Employee item:employee.getSubordinates()){
                System.out.println(item.toString()+", parent="+employee.getName());
            }
        }
    }
}
