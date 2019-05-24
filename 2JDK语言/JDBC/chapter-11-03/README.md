# Java-JDBC-advanced

```
PreparedStatement
Batch Updates
Transactions
CallableStatement
DatabaseMetaData
```

## PreparedStatement

A`PreparedStatement`is a special kind of`Statement`object with some useful features

The`PreparedStatement`'s primary features are:

- Easy to insert parameters into the SQL statement.
- Easy to reuse the`PreparedStatement`with new parameters.
- May increase performance of executed statements.
- Enables easier batch updates.

```
String sql = "update people set firstname=? , lastname=? where id=?";

PreparedStatement preparedStatement =
        connection.prepareStatement(sql);

preparedStatement.setString(1, "Gary");
preparedStatement.setString(2, "Larson");
preparedStatement.setLong  (3, 123);

int rowsAffected = preparedStatement.executeUpdate();
```

Reusing a PreparedStatement

```
String sql = "update people set firstname=? , lastname=? where id=?";

PreparedStatement preparedStatement =
        connection.prepareStatement(sql);

preparedStatement.setString(1, "Gary");
preparedStatement.setString(2, "Larson");
preparedStatement.setLong  (3, 123);

int rowsAffected = preparedStatement.executeUpdate();

preparedStatement.setString(1, "Stan");
preparedStatement.setString(2, "Lee");
preparedStatement.setLong  (3, 456);

int rowsAffected = preparedStatement.executeUpdate();
```

once a`PreparedStatement`is prepared, it can be reused after execution

PreparedStatement Performance

If you submit a new, full SQL statement for every query or update to the database, the database has to parse the SQL and for queries create a query plan. By reusing an existing`PreparedStatement`you can reuse both the SQL parsing and query plan for subsequent queries. This speeds up query execution, by decreasing the parsing and query planning overhead of each execution.

First of all, the JDBC driver can cache`PreparedStatement`objects internally, and thus reuse the`PreparedStatement`objects. This may save a little of the`PreparedStatement`creation time.

Second, the cached parsing and query plan could potentially be reused across Java applications, for instance application servers in a cluster, using the same database.

## Batch Updates

A batch update is a batch of updates grouped together, and sent to the database in one "batch", rather than sending the updates one by one.

You can batch both SQL inserts, updates and deletes. It does not make sense to batch select statements.

There are two ways to execute batch updates:

1. Using a Statement
2. Using a PreparedStatement

### Statement Batch Updates

```
Statement statement = null;

try{
    statement = connection.createStatement();

    statement.addBatch("update people set firstname='John' where id=123");
    statement.addBatch("update people set firstname='Eric' where id=456");
    statement.addBatch("update people set firstname='May'  where id=789");

    int[] recordsAffected = statement.executeBatch();
} finally {
    if(statement != null) statement.close();
}
```

### PreparedStatement Batch Updates

```
String sql = "update people set firstname=? , lastname=? where id=?";


PreparedStatement preparedStatement = null;
try{
    preparedStatement =
            connection.prepareStatement(sql);

    preparedStatement.setString(1, "Gary");
    preparedStatement.setString(2, "Larson");
    preparedStatement.setLong  (3, 123);

    preparedStatement.addBatch();

    preparedStatement.setString(1, "Stan");
    preparedStatement.setString(2, "Lee");
    preparedStatement.setLong  (3, 456);

    preparedStatement.addBatch();

    int[] affectedRecords = preparedStatement.executeBatch();

}finally {
    if(preparedStatement != null) {
        preparedStatement.close();
    }
}
```

### batch update inside a transaction

It is important to keep in mind, that each update added to a`Statement`or`PreparedStatement`is executed separately by the database.That means, that some of them may succeed before one of them fails.

To avoid this, you can execute the batch update inside a[transaction](http://tutorials.jenkov.com/jdbc/transaction.html). When executed inside a transaction you can make sure that either all updates are executed, or none are. Any successful updates can be rolled back, in case one of the updates fail.

## Transactions

```
Connection connection = ...
try{
    connection.setAutoCommit(false);

    // create and execute statements etc.

    connection.commit();
} catch(Exception e) {
    connection.rollback();
} finally {
    if(connection != null) {
        connection.close();
    }
}
```

## CallableStatement

A`java.sql.CallableStatement`is used to call stored procedures in a database.

simple example

```
// create callableStatement
CallableStatement callableStatement =
    connection.prepareCall("{call calculateStatistics(?, ?)}");

CallableStatement callableStatement =
    connection.prepareCall("{call calculateStatistics(?, ?)}",
        ResultSet.TYPE_FORWARD_ONLY,
        ResultSet.CONCUR_READ_ONLY,
        ResultSet.CLOSE_CURSORS_OVER_COMMIT
    );

 // Setting Parameter Values
callableStatement.setString(1, "param1");
callableStatement.setInt   (2, 123);

//Executing the CallableStatement
ResultSet result = callableStatement.executeQuery();  
callableStatement.executeUpdate();
```

The`executeQuery()`method is used if the stored procedure returns a`ResultSet`.

If the stored procedure just updates the database, you can call the`executeUpdate()`method instead

### Batch Updates

```
CallableStatement callableStatement =
    connection.prepareCall("{call calculateStatistics(?, ?)}");

callableStatement.setString(1, "param1");
callableStatement.setInt   (2, 123);
callableStatement.addBatch();

callableStatement.setString(1, "param2");
callableStatement.setInt   (2, 456);
callableStatement.addBatch();

int[] updateCounts = callableStatement.executeBatch();
```

### OUT Parameters

```
CallableStatement callableStatement =
    connection.prepareCall("{call calculateStatistics(?, ?)}");

callableStatement.setString(1, "param1");
callableStatement.setInt   (2, 123);

callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

ResultSet result = callableStatement.executeQuery();
while(result.next()) { ... }

String out1 = callableStatement.getString(1);
int    out2 = callableStatement.getInt   (2);
```

## DatabaseMetaData

Obtaining a DatabaseMetaData Instance

```
DatabaseMetaData databaseMetaData = connection.getMetaData();
```

Database Product , Driver Name and Version

```
int    majorVersion   = databaseMetaData.getDatabaseMajorVersion();
int    minorVersion   = databaseMetaData.getDatabaseMinorVersion();

String productName    = databaseMetaData.getDatabaseProductName();
String productVersion = databaseMetaData.getDatabaseProductVersion();
int driverMajorVersion = databaseMetaData.getDriverMajorVersion();
int driverMinorVersion = databaseMetaData.getDriverMinorVersion();
```

### Listing Tables

```
String   catalog          = null;
String   schemaPattern    = null;
String   tableNamePattern = null;
String[] types            = null;

ResultSet result = databaseMetaData.getTables(
    catalog, schemaPattern, tableNamePattern, types );

while(result.next()) {
    String tableName = result.getString(3);
}
```

First you call the`getTables()`method, passing it 4 parameters which are all null. The parameters can help limit the number of tables that are returned in the`ResultSet`. However, since I want all tables returned, I passed null in all of these parameters

### Listing Columns in a Table

```
String   catalog           = null;
String   schemaPattern     = null;
String   tableNamePattern  = "my_table";
String   columnNamePattern = null;


ResultSet result = databaseMetaData.getColumns(
    catalog, schemaPattern,  tableNamePattern, columnNamePattern);

while(result.next()){
    String columnName = result.getString(4);
    int    columnType = result.getInt(5);
}
```

### Primary Key for Table

```
String   catalog   = null;
String   schema    = null;
String   tableName = "my_table";

ResultSet result = databaseMetaData.getPrimaryKeys(
    catalog, schema, tableName);

while(result.next()){
    String columnName = result.getString(4);
}
```

### Supported Features

The`DatabaseMetaData`object also contains information about the features the JDBC driver and the database supports

```
databaseMetaData.supportsGetGeneratedKeys();
databaseMetaData.supportsGroupBy();
databaseMetaData.supportsOuterJoins();

DatabaseMetaData.supportsResultSetType(int type)
DatabaseMetaData.supportsResultSetConcurrency(int concurrency) 
DatabaseMetaData.supportsResultSetHoldability(int holdability)
```
