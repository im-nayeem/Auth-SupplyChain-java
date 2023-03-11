package DB;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created on 31-Jan-23
 *
 * @author Nayeem
 */


public class DatabaseConnection {
    private Connection conn=null;
    private ResultSet rs;
    private Statement stmt;
    private  PreparedStatement pstmt;
    private static BasicDataSource dataSource = new BasicDataSource();


    // static block to load uname and pass from properties,to create connection pool
    static
    {
        try {
            Properties prop = new Properties();
            prop.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("dbConfig.properties"));

            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl(prop.getProperty("db.url") + "?allowMultiQueries=true&characterEncoding=utf8");
            dataSource.setUsername(prop.getProperty("db.username"));
            dataSource.setPassword(prop.getProperty("db.password"));
            dataSource.setMaxIdle(300);
            dataSource.setMaxActive(100);
            dataSource.setMaxOpenPreparedStatements(400);
        } catch (IOException e) {
            throw new RuntimeException(e + " Couldn't read DB properties or set connection pool!");
        }

    }

    /** ------- Constructor ---- **/
    public DatabaseConnection() {
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e + "\nCouldn't Connect With Database");
        }

    }

    /** ================= Methods ======================*/

    /**
     * Method to execute query
     * @param query the query to be executed
     */
    public void execute(String query) {
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + "\nCouldn't Create Statement");
        }
    }

    /**
     * Method to execute query and return result set
     * @param query the query to be executed
     * @return rs the ResultSet
     */
    public ResultSet executeQuery(String query) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e + "\nCouldn't Create Statement");
        }
        return rs;
    }

    /**
     * Method to prepare statement
     * @param query the query to be executed
     * @return prepared statement
     */
    public PreparedStatement getPreparedStatement(String query)
    {
        try {
            pstmt = conn.prepareStatement(query);
            return  pstmt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAutoCommit(Boolean state){
        try {
            conn.setAutoCommit(state);
        } catch (SQLException e) {
            throw new RuntimeException(e+" Setting Auto Commit ");
        }
    }
    public void commit(){
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e+" Commit to DB");
        }
    }
    public void rollback(){
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e+" Rollback ");
        }
    }

    public void close() {
        try {
            if(rs!=null)
                rs.close();
            if(stmt!=null)
                stmt.close();
            if(pstmt!=null)
                pstmt.close();
            if(conn!=null)
                 conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e+" Close DB");
        }
    }
}
