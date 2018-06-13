package com.gupiaopiao.dao;


import java.sql.*;

public abstract class BaseDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gupiaopiao" +
            "?serverTimezone=GMT%2B8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yinhangkamima";
    protected Connection conn;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void openConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                return;
            }
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void closeAll(Statement stmt, ResultSet result) {
        closeResultSet(result);
        closeStatement(stmt);
        closeConnection();
    }
}
