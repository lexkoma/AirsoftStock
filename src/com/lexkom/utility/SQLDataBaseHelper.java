package com.lexkom.utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDataBaseHelper {
    private final static Logger LOGGER = Logger.getLogger("SQLDataBaseHelper.class");
    //Connection
    private static Connection conn = null;

    //Connection String
    private static final String connStr = "jdbc:sqlite:userAccounts.db";


//    public static Connection getConnectionDB(){
//        try {
//            conn = DriverManager.getConnection(connStr);
//            return conn;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //Connect to DB
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting JDBC Driver
        try {
//            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(connStr);
        }  catch ( SQLException sqlexception ) {
            LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
        }
        System.out.println("connection");
//        return connection;
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqlexception){
            LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
        }
    }

    public static void insertIntoDB(String email, String pass) throws SQLException {
        String sql = "INSERT INTO users(EMAIL, PASSWORD) VALUES(?,?)";
        PreparedStatement statement = null;
        try {
            dbConnect();
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, pass);
            statement.execute();
        } catch (ClassNotFoundException | SQLException sqlexception) {
            LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
        } finally {
            if (statement != null) {
                //Close statement
                statement.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }


    public static void initDB() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
//        String sql = "CREATE TABLE persons " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EMAIL VARCHAR(128) NOT NULL UNIQUE," +
                "PASSWORD VARCHAR(128) NOT NULL" +
                ")";
        Statement statement = null;
        try {
            dbConnect();
            statement = conn.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException sqlexception) {
            LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                //Close statement
                statement.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}

