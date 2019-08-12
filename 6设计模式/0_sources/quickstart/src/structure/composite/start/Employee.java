package structure.composite.start;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/12
 */

public class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.subordinates = new ArrayList<Employee>();
    }

    public void add(Employee employee){
        this.subordinates.add(employee);
    }

    public void remove(Employee employee){
        this.subordinates.remove(employee);
    }
    public List<Employee> getSubordinates(){
        return this.subordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }
}
