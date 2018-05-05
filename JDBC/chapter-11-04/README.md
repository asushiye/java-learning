# Java-DataBase-ConnectionString

		oracle
		mysql
		h2
		sqlserver
		PostgreSQL

## oracle

Java JDBC Thin Driver have three mode

Oracle JDBC Thin using a ServiceName
> jdbc:oracle:thin:@//<host>:<port>/<service_name> 
> recommmend to database cluster

Oracle JDBC Thin using an SID
> jdbc:oracle:thin:@<host>:<port>:<SID> 

Oracle JDBC Thin using a TNSName:
> jdbc:oracle:thin:@<TNSName> 

```
Class.forName("oracle.jdbc.driver.OracleDriver"); 
String url="jdbc:oracle:thin:@localhost:1521:orcl"; //orcl is SID 
String user="test"; 
String password="test"; 
Connection conn= DriverManager.getConnection(url,user,password);

```

## mysql

```
Class.forName("com.mysql.jdbc.Driver"); 
String url ="jdbc:mysql://localhost/myDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8" 
String user="test"; 
String password="test"; 
Connection conn= DriverManager.getConnection(url,user,password);

```

## h2

```
Class.forName("org.h2.Driver")
String url="jdbc:h2:tcp://localhost/mem:testmemdb";   // tcp 模式链接远程
String url="jdbc:h2:file:~/testmemdb";   // localhost 
String url="jdbc:h2:file:E:/H2/testmemdb";   // window only
String user="test"; 
String password="test"; 
Connection conn= DriverManager.getConnection(url,user,password);
```

## sqlserver

```
Class.forName("com.microsoft.sqlserver.SQLServerDriver")； 
String url="jdbc:sqlserver://localhost:1433;DatabaseName=mydb"; 
String user="sa"; 
String password=""; 
Connection conn= DriverManager.getConnection(url,user,password);

```


## PostgreSQL

```
Class.forName("org.postgresql.Driver").newInstance(); 
String url ="jdbc:postgresql://localhost/myDB" 
String user="myuser"; 
String password="mypassword"; 
Connection conn= DriverManager.getConnection(url,user,password);

```

