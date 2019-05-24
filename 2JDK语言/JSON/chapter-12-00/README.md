# json

JSON is short for JavaScript Object Notation. 

JSON is a popular data exchange format between browsers and web servers because the browsers can parse JSON into JavaScript objects natively. 


		结构与类型
		JSON编码指南
		JSON类库
		JSON工具


## 结构与类型

* 只有两种结构：对象内的键值对集合结构和数组。 对象用{}表示、内部是”key”:”value”，数组用[]表示，不同值用逗号分开
* 基本数值有7个：number, string, boolean, object, array, null 
* 再加上结构可以嵌套，进而可以用来表达复杂的数据

```
{
   "Image": {
       "Width":  800,
       "Height": 600,
       "Title":  "View from 15th Floor",
       "Thumbnail": {
           "Url":    "http://www.example.com/image/481989943",
           "Height": 125,
           "Width":  "100"
       },
       "IDs": [116, 943, 234, 38793]
     }
}
```


## JSON编码指南

遵循好的设计与编码风格，能提前解决80%的问题:

* 中文版Google JSON风格指南：https://github.com/darcyliu/google-styleguide/blob/master/JSONStyleGuide.md
* 英文版Google JSON Style Guide：https://google.github.io/styleguide/jsoncstyleguide.xml


属性名和值都是用双引号，不要把注释写到对象里面，对象数据要简洁
不要随意结构化分组对象，推荐是用扁平化方式，层次不要太复杂
驼峰式命名，遵循Bean规范
使用版本来控制变更冲突
对于一些关键字，不要拿来做key
序列化枚举类型时，使用name而不是value
日期要用标准格式处理

风格指南是对在Google创建JSON APIs而提供的指导性准则和建议。

总体来讲，JSON APIs应遵循JSON.org上的规范.

这些指南适用于基于RPC和基于REST风格的API的JSON请求和响应。


### 一般准则
JSON对象中不包含注释
### 使用双引号
所有的属性名必须在双引号内。字符类型的属性值必须使用双引号。其它类型值（如布尔或数字）不应该使用双引号。

### 扁平化数据 VS 结构层次

JSON中的数据元素应以扁平化方式呈现。不能为了方便而将数据任意分组。

一个地址可以有表示两种方式，但结构化的方式对开发人员来讲可能更有意义：

扁平化地址:
```
{
  "company": "Google",
  "website": "http://www.google.com/",
  "addressLine1": "111 8th Ave",
  "addressLine2": "4th Floor",
  "state": "NY",
  "city": "New York",
  "zip": "10011"
}
```

结构化地址：

```
{
  "company": "Google",
  "website": "http://www.google.com/",
  "address": {
    "line1": "111 8th Ave",
    "line2": "4th Floor",
    "state": "NY",
    "city": "New York",
    "zip": "10011"
  }
}
```

### 属性名准则

选择有意义的属性名,驼峰式的，ASCII码字符串,避免使用Javascript中的保留关键字

当JSON对象被当作map使用时，API文件应当做出说明。

Map的键名不一定要遵循属性名称的命名准则。键名可以包含任意的Unicode字符。客户端可使用maps熟悉的方括号来访问这些属性。（例如result.thumbnails["72"]）

### 单数属性名 VS 复数属性名

数组类型应该是复数属性名。其它属性名都应该是单数。

```
{
  // 单数
  "author": "lisa",
  // 一组同胞, 复数
  "siblings": [ "bart", "maggie"],
  // "totalItem" 看起来并不对
  "totalItems": 10,
  // 但 "itemCount" 要好些
  "itemCount": 10
}
```

### 属性值准则

属性值必须是Unicode 的 booleans（布尔）, 数字(numbers), 字符串(strings), 对象(objects), 数组(arrays), 或 null.

```
{
  "canPigsFly": null,     // null
  "areWeThereYet": false, // boolean
  "answerToLife": 42,     // number
  "name": "Bart",         // string
  "moreData": {},         // object
  "things": []            // array
}
```

空或Null 属性值 考虑从JSON中去掉该属性，除非它的存在有很强的语义原因

枚举值应当以字符串的形式呈现

## Java类库

* Fastjson
* Jackson
* Gson


## JSON工具

* 格式化工具: jsbeautifier

* soapui: https://www.soapui.org/downloads/soapui.html

