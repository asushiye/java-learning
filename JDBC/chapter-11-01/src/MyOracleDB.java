
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class MyOracleDB {
    private static Connection conn = MyConnection.getConnection();
    ;

    public static void main(String[] args) {
        //  MyOracleDB.insert_data("zhenyun.su",32);
        MyOracleDB.query_all_data();
        MyOracleDB.update_data_PreparedStatement();
        MyOracleDB.update_batch_data_Statement();
    }

    public static boolean insert_data(String name, int age) {
        Statement statement = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("insert into aus_test_head(id, name, age, create_date)");
            stringBuilder.append(" values(aus_test_head_s.nextval,");
            stringBuilder.append(" '");
            stringBuilder.append(name);
            stringBuilder.append(" ',");
            stringBuilder.append(age);
            stringBuilder.append(" , sysdate)");
            statement = MyOracleDB.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT);
            int rowsAffected = statement.executeUpdate(stringBuilder.toString());
            return rowsAffected >= 1 ? true : false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Integer query_all_data() {
        Statement statement = null;
        ResultSet result = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select id, name, age, create_date from aus_test_head where id = 3");
            statement = MyOracleDB.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT);
            result = statement.executeQuery(stringBuilder.toString());
            while (result.next()) {
                System.out.println(result.getString(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
            }
            return result.getFetchSize();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean query_and_update_data() {
        Statement statement = null;
        ResultSet result = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select id, name, age, create_date from aus_test_head where id = 3");
            statement = MyOracleDB.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
            result = statement.executeQuery(stringBuilder.toString());
            while (result.next()) {
                System.out.println(result.getString(2));
            }

            result.updateInt(3, 35);
            result.updateRow();

            result.moveToInsertRow();
            result.updateInt(1, 22);
            result.updateString(2, "zhenyun.su22");
            result.updateInt(3, 22);
            result.updateDate(4, Date.valueOf(LocalDate.now()));
            result.insertRow();

            // result.deleteRow();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Integer update_data_PreparedStatement() {
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("update aus_test_head set age=? where id = ?");
            preparedStatement = MyOracleDB.conn.prepareStatement(stringBuilder.toString());

            preparedStatement.setInt(1,300);
            preparedStatement.setInt(2,3);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static Integer update_batch_data_Statement() {
        Statement statement = null;
        ResultSet result = null;
        try {

            statement = MyOracleDB.conn.createStatement();
            statement.addBatch("update aus_test_head set age=100 where id = 3");
            statement.addBatch("update aus_test_head set age=100 where id = 22");

            int[] rowsAffecteds = statement.executeBatch();

            return rowsAffecteds.length;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
