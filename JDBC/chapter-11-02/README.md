# JDBC Driver

A JDBC driver is a set of Java classes that implement the JDBC interfaces, targeting a specific database. The JDBC interfaces comes with standard Java, but the implementation of these interfaces is specific to the database you need to connect to. Such an implementation is called a JDBC driver.

```
    JDBC Driver Type
    JDBC Driver Database Connection
    Query the Database
    ResultSet
          Creating ,Iterating, Accessing ResultSet
          ResultSet three Attributes
          Navigation Methods
```

## JDBC Driver Type

There are 4 different types of JDBC drivers:

- Type 1: JDBC-ODBC bridge driver![\chapter-11-01\driver-type-1](..\chapter-11-01\driver-type-1.png)
- Type 2: Java + Native code driver![\chapter-11-01\driver-type-2](..\chapter-11-01\driver-type-2.png)
- Type 3: All Java + Middleware translation driver![\chapter-11-01\driver-type-3](..\chapter-11-01\driver-type-3.png)
- Type 4: All Java driver.

![\chapter-11-01\driver-type-4](..\chapter-11-01\driver-type-4.png)

Today, most JDBC drivers are type 4 drivers which connects directly to the database.

## JDBC Driver Database Connection

- Loading the JDBC Driver

- Opening the Connection

- Closing the Connection

Loading the JDBC Driver

```
Class.forName("driverClassName");
Class.forName("org.h2.Driver");
```

To open a database connection you use the`java.sql.DriverManager`class.

```
String url      = "jdbc:h2:~/test";   //database specific url.
String user     = "sa";
String password = "";

Connection connection =
    DriverManager.getConnection(url, user, password);
```

Closing the Connection

Once you are done using the database connection you should close it. This is done by calling the Connection.close() method, like this:

```connection.close();```
connection.close();
```

## Query the Database

```
Statement statement = null;

try{
    statement = connection.createStatement();
    ResultSet result    = null;
    try{
        String sql = "select * from people";
        ResultSet result = statement.executeQuery(sql);

        while(result.next()) {

            String name = result.getString("name");
            long   age  = result.getLong("age");

            System.out.println(name);
            System.out.println(age);
        }
    } finally {
        if(result != null) result.close();
    }
} finally {
    if(statement != null) statement.close();
}
```

## Update the Database

### Updating Records

Here is an update record value example:

```
Statement statement = connection.createStatement();
String    sql       = "update people set name='John' where id=123";
int rowsAffected    = statement.executeUpdate(sql);
```

The`rowsAffected`returned by the`statement.executeUpdate(sql)`call, tells how many records in the database were affected by the SQL statement.

### Deleting Records

Here is a delete record example:

```
Statement statement = connection.createStatement();
String    sql       = "delete from people where id=123";
int rowsAffected    = statement.executeUpdate(sql);
```

Again, the`rowsAffected`returned by the`statement.executeUpdate(sql)`call, tells how many records in the database were affected by the SQL statement.

## ResultSet

The text about[queries](http://tutorials.jenkov.com/jdbc/query.html)I shows how the result of a query is returned as a`java.sql.ResultSet`.

A`ResultSet`consists of records. Each records contains a set of columns. Each record contains the same amount of columns, although not all columns may have a value. A column can have a`null`value

### Creating ,Iterating, Accessing ResultSet

You create a`ResultSet`by executing a`Statement`or`PreparedStatement`

```

String sql = "select * from people";

Statement statement = connection.createStatement();
ResultSet result = statement.executeQuery(sql);

PreparedStatement statement = connection.prepareStatement(sql);
ResultSet result = statement.executeQuery();

while(result.next()) {

    result.getString    ("name");
    result.getInt       ("age");
    result.getBigDecimal("coefficient");

    // etc. index
    result.getString    (1);
    result.getInt       (2);
    result.getBigDecimal(3);
    //get index for name
    int nameIndex   = result.findColumn("name");
        int ageIndex    = result.findColumn("age");
        int coeffIndex  = result.findColumn("coefficient");
}
```

### ResultSet three Attributes

When you create a`ResultSet`there are three attributes you can set.

1. Type
2. Concurrency
3. Holdability

```
Statement statement = connection.createStatement(
    ResultSet.TYPE_FORWARD_ONLY,
    ResultSet.CONCUR_READ_ONLY,
    ResultSet.CLOSE_CURSORS_OVER_COMMIT
   );
```

#### ResultSet Types

The type determines some characteristics and abilities of the`ResultSet`

Not all types are supported by all databases and JDBC drivers. You will have to check your database and JDBC driver to see if it supports the type you want to use. The`DatabaseMetaData.supportsResultSetType(int type)`

1. ResultSet.TYPE\_FORWARD\_ONLY

   > `TYPE_FORWARD_ONLY`means that the`ResultSet`can only be navigated forward. That is, you can only move from row 1, to row 2, to row 3 etc. You cannot move backwards in the`ResultSet`.

2. ResultSet.TYPE\_SCROLL\_INSENSITIVE

   > the`ResultSet`can be navigated (scrolled) both forward and backwards. You can also jump to a position relative to the current position, or jump to an absolute position
   > 
   > 在ResultSet处于打开状态时，ResultSet对基础数据源中的更改不敏感,也就是说，如果ResultSet中的记录由另一个线程或进程在数据库中更改，它将不会反映在已打开的此类型的ResulsSet中

3. ResultSet.TYPE\_SCROLL\_SENSITIVE

   > the`ResultSet`can be navigated (scrolled) both forward and backwards. You can also jump to a position relative to the current position, or jump to an absolute position
   > 
   > 在ResultSet处于打开状态时，ResultSet对底层数据源中的更改非常敏感。也就是说，如果ResultSet中的记录由另一个线程或进程在数据库中更改，它将反映在已打开的此类型的ResulsSet中

#### ResultSet Concurrency

the`ResultSet`concurrency determines whether the`ResultSet`can be updated, or only read.

Some databases and JDBC drivers support that the`ResultSet`is updated, but not all databases and JDBC drivers do. The`DatabaseMetaData.supportsResultSetConcurrency(int concurrency)`method returns true or false depending on whether the given concurrency mode is supported or not.

1. ResultSet.CONCUR\_READ\_ONLY

   > the`ResultSet`can only be read.

2. ResultSet.CONCUR_UPDATABLE

   > the`ResultSet`can be both read and updated.

##### Updating a ResultSet

```
    result.updateString     ("name"       , "Alex");
    result.updateInt        ("age"        , 55);
    result.updateBigDecimal ("coefficient", new BigDecimal("0.1323");
    result.updateRow();
```

Notice the`updateRow()`call. It is when`updateRow()`is called that the database is updated with the values of the row. Id you do not call this method, the values updated in the`ResultSet`are never sent to the database. 

If you call`updateRow()`inside a transaction, the data is not actually committed to the database until the transaction is committed.

##### Inserting Rows into a ResultSet

```
result.moveToInsertRow();
result.updateString     (1, "Alex");
result.updateInt        (2, 55);
result.updateBigDecimal (3, new BigDecimal("0.1323");
result.insertRow();

result.beforeFirst();
```

#### ResultSet Holdability

The`ResultSet`holdability determines if a`ResultSet`is closed when the`commit()`method of the underlying`connection`is called.

Not all holdability modes are supported by all databases and JDBC drivers. The`DatabaseMetaData.supportsResultSetHoldability(int holdability)`returns true or false depending on whether the given holdability mode is supported or not

1. ResultSet.CLOSE\_CURSORS\_OVER_COMMIT

   > all`ResultSet`instances are closed when`connection.commit()`method is called on the connection that created the`ResultSet`

2. ResultSet.HOLD\_CURSORS\_OVER_COMMIT

   > the`ResultSet`is kept open when the`connection.commit()`method is called on the connection that created the`ResultSet`.

### Navigation Methods

The`ResultSet`interface contains the following navigation methods. Remember, not all methods work with all`ResultSet`types. What methods works depends on your database, JDBC driver, and the`ResultSet`type.

| Method          | Description                                                                                                                                                                                                     |
| --------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `absolute()`    | Moves the`ResultSet`to point at an absolute position. The position is a row number passed as parameter to the`absolute()`method.                                                                                |
| `afterLast()`   | Moves the`ResultSet`to point after the last row in the`ResultSet`.                                                                                                                                              |
| `beforeFirst()` | Moves the`ResultSet`to point before the first row in the`ResultSet`.                                                                                                                                            |
| `first()`       | Moves the`ResultSet`to point at the first row in the`ResultSet`.                                                                                                                                                |
| `last()`        | Moves the`ResultSet`to point at the last row in the`ResultSet`.                                                                                                                                                 |
| `next()`        | Moves the`ResultSet`to point at the next row in the`ResultSet`.                                                                                                                                                 |
| `previous()`    | Moves the`ResultSet`to point at the previous row in the`ResultSet`.                                                                                                                                             |
| `relative()`    | Moves the`ResultSet`to point to a position relative to its current position. The relative position is passed as a parameter to the relative method, and can be both positive and negative. Moves the`ResultSet` |

The`ResultSet`interface also contains a set of methods you can use to inquire about the current position of the`ResultSet`. These are:

| Method            | Description                                                                                 |
| ----------------- | ------------------------------------------------------------------------------------------- |
| `getRow()`        | Returns the row number of the current row - the row currently pointed to by the`ResultSet`. |
| `getType()`       | Returns the`ResultSet`type.                                                                 |
| `isAfterLast()`   | Returns true if the`ResultSet`points after the last row. False if not.                      |
| `isBeforeFirst()` | Returns true if the`ResultSet`points before the first row. False if not.                    |
| `isFirst()`       | Returns true if the`ResultSet`points at the first row. False if not.                        |

Finally the`ResultSet`interface also contains a method to update a row with database changes, if the`ResultSet`is sensitive to change.

| Method         | Description                                                                       |
| -------------- | --------------------------------------------------------------------------------- |
| `refreshRow()` | Refreshes the column values of that row with the latest values from the database. |
