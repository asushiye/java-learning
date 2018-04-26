# Java JSON Tutorial
chapter-12-01

JSON is short for JavaScript Object Notation. 

JSON is a popular data exchange format between browsers and web servers because the browsers can parse JSON into JavaScript objects natively. 

On the server, however, JSON needs to be parsed and generated using JSON APIs. 

This Java JSON tutorial focuses on the various choices you have for parsing and generating JSON in Java.


The open source Java JSON APIs often offer more choice and flexibility in how you can work with JSON than the JSR 353 API. 

Therefore the open source APIs are still decent options. Some of the more well-known open source Java JSON APIs are:

* Jackson
* GSON
* Boon
* JSON.org

Jackson
Jackson is a Java JSON API which provides several different ways to work with JSON. Jackson is one of the most popular Java JSON APIs out there. You can find Jackson here:

https://github.com/FasterXML/jackson

Jackson contains 2 different JSON parsers:

1. The Jackson ObjectMapper which parses JSON into custom Java objects, or into a Jackson specific tree structure (tree model).
2. The Jackson JsonParser which is Jackson's JSON pull parser, parsing JSON one token at a time.

Jackson also contains two JSON generators:

1. The Jackson ObjectMapper which can generate JSON from custom Java objects, or from a Jackson specific tree structure (tree model).
2. The Jackson JsonGenerator which can generate JSON one token at a time.

other refer to :http://tutorials.jenkov.com/java-json/index.html


