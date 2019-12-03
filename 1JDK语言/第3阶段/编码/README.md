# 编码
		概述
		如何获取系统编码
		源代码分析

## 概述

关于编码知识，请看编码 SZ02_数据单位字符编码及字符集技术文档_1.3v

jre自动获取操作系统的编码file.encoding，作为java运行时的默认编码

## 如何获取系统编码

### jinfo监控工具

通过java监控工具 jinfo查看操作系统

jinfo -sysprops 15096
```
file.encoding = GBK
```

### jdk api 获取
```java
  public static void main(String[] args){
		String encode = System.getProperty("file.encoding");
		system.output.println(encode);
	}
```

linux 默认为UTF-8；中文版本window 默认GBK

## 源代码分析
String
```java
    public byte[] getBytes() {
        return StringCoding.encode(value, 0, value.length);
    }
```

StringCoding
```java
    static byte[] encode(char[] ca, int off, int len) {
        String csn = Charset.defaultCharset().name();
				。。。
		}
```

Charset
```java
    public static Charset defaultCharset() {
        if (defaultCharset == null) {
            synchronized (Charset.class) {
                String csn = AccessController.doPrivileged(
                    new GetPropertyAction("file.encoding"));
                Charset cs = lookup(csn);
                if (cs != null)
                    defaultCharset = cs;
                else
                    defaultCharset = forName("UTF-8");
            }
        }
        return defaultCharset;
    }
```

GetPropertyAction
```java
public class GetPropertyAction implements PrivilegedAction<String> {
    private String theProp;
    private String defaultVal;

    public GetPropertyAction(String var1) {
        this.theProp = var1;
    }

    public GetPropertyAction(String var1, String var2) {
        this.theProp = var1;
        this.defaultVal = var2;
    }

    public String run() {
        String var1 = System.getProperty(this.theProp);
        return var1 == null ? this.defaultVal : var1;
    }
}
```

最终通过 System.getProperty("file.encoding"); 获取都系统编码
