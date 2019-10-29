# Java Internationalization: Overview
chapter-09-01

		Handling International Input
		Handling International Output
		Adapting Operations
		User Information Needed
		Application Internationalization Layer
		Why an Internationalization Component?
		Java's Internationalization Classes

Internationalizing a Java application typically means making the application able to handle multiple languages, number formats, date formats etc. This text gives you an overview of these aspects.

Basically, your application should be able to handle international input, output and operations. 

In other words, your application should be able to adapt itself to **input**, **output** and **operations** specific to different locations and user preferences.

## Handling International Input
Input is everything that your application receives from its users, either directly through the user interface, sent to it via service calls (e.g. SOAP or REST calls), imported from files etc. Input includes:

		Form input including
		Text (language + character encoding)
		Number formats
		Date and time formats and calculations
		Files received
		Service calls received
## Handling International Output
Output is everything your application is showing or sending to its users, either during the installation process, or during general use. Output includes:

		Text (language + character encoding)
		Number and currency formatting
		Date and time formatting and calculations
		Character comparisons
		String comparisons


## Adapting Operations

 your application may need to adapt certain internal operations to the users location and preferences

 For instance, a web shop may need to add different amounts of VAT to a sale


## User Information Needed
为了正确地将您的应用程序本地化为用户，您可能需要以下一项或多项内容：

首选语言
居住国家
当前位置
首选时区



The user should be able to tell what language he or she prefers the application to use

用户居住国可能会影响用户可以使用的功能以及这些功能的工作方式

The application may take action based on the users location

## Application Internationalization Layer
In order to handle internationalization of input and output, your application will have a kind of internationalization layer. 

## Java's Internationalization Classes

Java has a set of built-in classes that can help you handle internationalization of your application. 

These classes are:

|Class|	Description|
|-|-|
|Locale	|The Locale class represents a language and a country or region. A Locale may also represent a certain type of formatting - e.g. date or number formatting.|
|ResourceBundle	|The ResourceBundle class can contain localized texts or components (objects). You obtain a ResourceBundle for a specific Locale, and thus obtain texts or objects localized to that Locale.|
|NumberFormat	|The NumberFormat class is used to format numbers according to a certain Locale.|
|DecimalFormat	|The DecimalFormat class is used to format numbers according to customized formatting patterns. These patterns are also Locale sensitive.|
|DateFormat	|The DateFormat class is used to format dates according to a specific Locale.|
|SimpleDateFormat	|The SimpleDateFormat class is used to parse and format dates according to custom formatting patterns. These patterns are also Locale sensitive.|



总而言之，尝试将太多本地化设置与用户的语言，国家或地区联系起来是没有意义的。

让用户选择使用什么语言，时区，使用什么数字格式和日期格式等。也可以彼此独立地进行更改。

不要将其绑定到用户的本国。

