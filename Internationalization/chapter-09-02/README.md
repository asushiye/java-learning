# Java Internationalization: Locale
chapter-09-03


java.util.Locale类用于表示“地理，政治或文化”区域，以将给定的文本，数字，日期或操作本地化。 

因此，语言环境对象可以包含国家，地区，语言以及语言的变体，

例如在某个国家的某个地区说的方言，或者在与该语言起源国不同的国家说的方言。


		Locale Standards
		Locale Contents
		Language
		Script
		Country (Region)
		Variant
		Extensions
		Locale Constants
		Locale Constructors
		Locale Builder
		Locale.forLanguageTag()
		Using the Locale Instance
		Locale Criticism

## Locale Standards

 BCP 47 (IETF BCP 47, "Tags for Identifying Languages") 

 LDML (UTS#35 "Unicode Locale Data Markup Language")  BCP 47-compatible extension 


## Locale Contents
A Locale instance contains the following subparts:

* Language
* Script
* Country (region)
* Variant
* Extensions

### Language
The language must be an ISO 639 2 or 3 character language code, or a registered language subtag of up to 8 characters. In case a language has both a 2 and 3 character language code, use the 2 character code. A full list of language codes can be found in the IANA Language Subtag Registry.

Language codes are case insensitive, but the Locale class always use lowercase versions of the language codes.

### Script
The script code represents the written form of the language. Some languages can be written using different scripts (e.g. different alphabets).

The script code is a 4 character code following the ISO 15924 standard. A full list of script codes can be found in the IANA Language Subtag Registry.

Script codes are case insensitive, but the Locale class always uses a version with the first letter in uppercase, and the rest in lowercase.

### Country (Region)
The country code is a 2 character code following the ISO 3166 standard, or a UN M.49 numeric area code. A full list of country and region codes can be found in the IANA Language Subtag Registry.

The country code is case insensitive, but the Locale class uses an uppercase version of the country code.

### Variant
The variant part describes a variant (dialect) of a language follwing the BCP 47 standard. See the JavaDoc for the Locale class for more detail about variant.

### Extensions
The extension part signals extensions to the Locale in addition to the language and region. For instance, what calendar to use when displaying dates (Gregorian, Arab, Japanese etc.). See the JavaDoc for the Locale class for more detail about extensions.


## Creating a Locale

Creating a java.util.Locale instance can be done in four different ways:

* Locale constants
* Locale constructors
* Locale.Builder class (from Java 7)
* Locale.forLanguageTag factory method (from Java 7)

The java.util.Locale class contains a set of constants that represent the most commonly used languages in the world. These are:

```
Locale.CANADA
Locale.CANADA_FRENCH
Locale.CHINA
Locale.CHINESE
Locale.ENGLISH
Locale.FRANCE
```

Creating a java.util.Locale instance
```
Locale locale = Locale.JAPANESE;

Locale(String language)
Locale(String language, String country)
Locale(String language, String country, String variant)

Locale locale = new Locale("en");       // English language
Locale locale = new Locale("en", "UK"); // English language, United Kingdom
Locale locale = new Locale("en", "CA"); // English language, Canada

Locale cLocale = new Locale.Builder().setLanguage("en")
                                     .setRegion("US").build();

Locale aLocale = Locale.forLanguageTag("en-US");
```

Using the Locale Instance

```
Locale locale = new Locale("da", "DK");

ResourceBundle resourceBundle =
        ResourceBundle.getBundle("bundleName", locale);

```

