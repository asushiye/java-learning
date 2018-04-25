# Java IO Tutorial
chapter-10-01

Java IO is an API that comes with Java which is targeted at reading and writing data (input and output). 

For instance, read data from a file or over network, and write to a file or write a response back over the network.

The Java IO API is located in the **Java IO package (java.io)**.

## Source and Destination

The most typical sources and destinations of data are these

* Files
* Pipes
* Network Connections
* In-memory Buffers (e.g. arrays)
* System.in, System.out, System.error

a program reading data from a source and writing it to some destination:

> Source --> Program  --> Destination

## Streams data flow 

A stream is a conceptually endless flow of data. 

You can either read from a stream or write to a stream. 

A stream is connected to a data source or a data destination. 

Streams in Java IO can be either byte based (reading and writing bytes) or character based (reading and writing characters).


> Source --> InputStream / Reader  --> Program
> Program  --> OutputStream / Writer  -->  Destination

## Java IO Purposes and Features

Java IO contains many subclasses of the InputStream, OutputStream, Reader and Writer classes

* File Access
* Network Access
* Internal Memory Buffer Access
* Inter-Thread Communication (Pipes)
* Buffering
* Filtering
* Parsing
* Reading and Writing Text (Readers / Writers)
* Reading and Writing Primitive Data (long, int etc.)
* Reading and Writing Objects


## Java IO Class Overview Table

|	|Byte Based	|Character Based|
| 	|Input	|Output	|Input	|Output|
|-|-|-|-|
|Basic	|InputStream	|OutputStream	|Reader InputStreamReader	|Writer OutputStreamWriter|
|Arrays	|ByteArrayInputStream	|ByteArrayOutputStream	|CharArrayReader	|CharArrayWriter|
|Files	|FileInputStream RandomAccessFile	|FileOutputStream RandomAccessFile	|FileReader	|FileWriter|
|Pipes	|PipedInputStream	|PipedOutputStream	|PipedReader	|PipedWriter|
|Buffering	|BufferedInputStream	|BufferedOutputStream	|BufferedReader	|BufferedWriter|
|Filtering	|FilterInputStream	|FilterOutputStream	|FilterReader	|FilterWriter|
|Parsing	|PushbackInputStream StreamTokenizer |	  	|PushbackReader LineNumberReader	 | |
|Strings	 |	| 	|StringReader	|StringWriter|
|Data	|DataInputStream	|DataOutputStream	 	| | |
|Data - Formatted |	| 	|PrintStream	 |	|PrintWriter|
|Objects	|ObjectInputStream	|ObjectOutputStream	 	 | | |
|Utilities	|SequenceInputStream|
 

