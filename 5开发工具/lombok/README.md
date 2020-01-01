# lombok

概述
如何使用

## 概述
  避免重复写Getter/Setter、构造器方法、字符串输出的ToString方法和Equals/HashCode方法


  Lombok是一款Java开发插件，使得Java开发者可以通过其定义的一些注解来消除业务工程中冗长和繁琐的代码，尤其对于简单的Java模型对象（POJO）


  Intellij中安装Lombok的插件


```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
    <scope>provided</scope>
</dependency>
```

截至20191204，lombok最新稳定版1.18.10 Sep, 2019， 基本上一季度出一个版本

## 如何使用

### 注解说明


```
@Getter/@Setter 类的属性自动生成所有字段的@Getter和所有非final字段的@Setter
@ToString 自动生成一个toString方法
@EqualsAndHashCode 自动生成equals和hashCode方法
@NoArgsConstructor, 自动生成无参构造器
@RequiredArgsConstructor, 自动生成指定参数的构造器
@AllArgsConstructor 自动生成包含所有参数的构造器

@Data包含注解的集合 @Getter/@Setter @ToString @EqualsAndHashCode RequiredArgsConstructor等
@Builder注解提供了一种比较推崇的构建值对象的方式
@Synchronized注解类似Java中的Synchronized 关键字，但是可以隐藏同步锁
@NonNull 能够为方法或构造函数的参数提供非空检查
@Cleanup 能够自动释放资源
```

具体的使用，请查看

https://projectlombok.org/features/all

### 快速入门

```java
@Data
public class City {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createdDate;
}
```

```java
import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-12-04
 */

public class Test {
    public static void main(String[] args) {
        cityTest();
//        cityBuilderTest();
    }
    public static void cityTest() {
        City city = new City();
        city.setId(1L);
        city.setCode("101");
        city.setCreatedDate(LocalDateTime.now());
        city.setName("上海");
        System.out.println(city.toString());
        System.out.println(city);
        System.out.println(city.hashCode());
        System.out.println(city.equals(new City(2L, "102")));
    }
}
```

输出结果
```
City(id=1, code=101, name=上海, createdDate=2019-12-04T17:10:21.032)
City(id=1, code=101, name=上海, createdDate=2019-12-04T17:10:21.032)
-508336014
false
```

```java
@Data
public class City {
    private Long id;
    private String code;
    private String name;
    private LocalDateTime createdDate;

    public City() {
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

```

若显性定义构造函数，则以显性构造为准

### 最佳实例
```java

import lombok.AccessLevel;
import lombok.Setter;
import lombok.Data;
import lombok.ToString;

@Data public class DataExample {
  private final String name;
  @Setter(AccessLevel.PACKAGE) private int age;
  private double score;
  private String[] tags;

  @ToString(includeFieldNames=true)
  @Data(staticConstructor="of")
  public static class Exercise<T> {
    private final String name;
    private final T value;
  }
}
```

```java
import java.util.Arrays;

public class DataExample {
  private final String name;
  private int age;
  private double score;
  private String[] tags;

  public DataExample(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public double getScore() {
    return this.score;
  }

  public String[] getTags() {
    return this.tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  @Override public String toString() {
    return "DataExample(" + this.getName() + ", " + this.getAge() + ", " + this.getScore() + ", " + Arrays.deepToString(this.getTags()) + ")";
  }

  protected boolean canEqual(Object other) {
    return other instanceof DataExample;
  }

  @Override public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof DataExample)) return false;
    DataExample other = (DataExample) o;
    if (!other.canEqual((Object)this)) return false;
    if (this.getName() == null ? other.getName() != null : !this.getName().equals(other.getName())) return false;
    if (this.getAge() != other.getAge()) return false;
    if (Double.compare(this.getScore(), other.getScore()) != 0) return false;
    if (!Arrays.deepEquals(this.getTags(), other.getTags())) return false;
    return true;
  }

  @Override public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long temp1 = Double.doubleToLongBits(this.getScore());
    result = (result*PRIME) + (this.getName() == null ? 43 : this.getName().hashCode());
    result = (result*PRIME) + this.getAge();
    result = (result*PRIME) + (int)(temp1 ^ (temp1 >>> 32));
    result = (result*PRIME) + Arrays.deepHashCode(this.getTags());
    return result;
  }

  public static class Exercise<T> {
    private final String name;
    private final T value;

    private Exercise(String name, T value) {
      this.name = name;
      this.value = value;
    }

    public static <T> Exercise<T> of(String name, T value) {
      return new Exercise<T>(name, value);
    }

    public String getName() {
      return this.name;
    }

    public T getValue() {
      return this.value;
    }

    @Override public String toString() {
      return "Exercise(name=" + this.getName() + ", value=" + this.getValue() + ")";
    }

    protected boolean canEqual(Object other) {
      return other instanceof Exercise;
    }

    @Override public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Exercise)) return false;
      Exercise<?> other = (Exercise<?>) o;
      if (!other.canEqual((Object)this)) return false;
      if (this.getName() == null ? other.getValue() != null : !this.getName().equals(other.getName())) return false;
      if (this.getValue() == null ? other.getValue() != null : !this.getValue().equals(other.getValue())) return false;
      return true;
    }

    @Override public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      result = (result*PRIME) + (this.getName() == null ? 43 : this.getName().hashCode());
      result = (result*PRIME) + (this.getValue() == null ? 43 : this.getValue().hashCode());
      return result;
    }
  }
}
```


## 参考资料

官网地址： https://projectlombok.org
