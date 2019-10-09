# Java IO

    1 io概念
    2 文件访问
    3 流操作

## 1 io概念
		IO简介
			常见来源或目的地
			如何实现数据读写操作
		IO实现类
			类一览表
			基于字节抽象类
			基于字符抽象类

## 2文件操作
		文件读写一览表
		文件内容操作
			基于字节操作文件内容 - FileInputStream, FileOutputStream
			基于字符操作文件内容 - FileReader, FileWriter
			随机访问文件 - RandomAccessFile
		文件属性操作
			常见属性操作
			实例

## 3流操作 Java ByteArray, Piped,  Buffered
		字节数组操作 - ByteArrayInputStream, ByteArrayOutputStream
		管道操作 - PipedInputStream, PipedOutputStream
		缓冲操作 - BufferedInputStream, BufferedOutputStream
		基本类型操作 -DataInputStream, DataOutputStream
		对象操作 - ObjectInputStream	ObjectOutputStream
		格式化输出 - PrintStream (only use to output byte stream)
		组合操作 - SequenceInputStream(only use to input byte stream)
