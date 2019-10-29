# IntelliJIDEA 

## Introduction   ------    简介

		概况


## HotKey   ------    快捷键

		文件操作
		光标操作
		代码操作
		注释操作
		窗口操作
		构建操作


## Configure   ------    配置

		注释配置
			文件头注释模板
			方法头注释模板
			验证注释模板
		导出导入配置信息
			导出配置信息
			导入配置信息


## plugin   ------    插件

		插件管理
			安装插件
			卸载插件
		常用插件

			
		

## VS  ------chapter-88-02


## 编码

由于window默认的编码格式，不是UTF-8，

在idea没有设置编码时，有可能会导致源代码，控制台及终端等等窗口输出乱码问题

我们会在idea启动参数文件中idea64.exe.vmoptions 或idea.exe.vmoptions，配置编码格式

```
-Dfile.encoding=UTF-8

-Dconsole.encoding=UTF-8
```

当然我们也可以，直接配置window的编码格式
![window10-utf-8](window10-utf-8.png)

