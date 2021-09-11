package br.edu.ifce.utils;

import java.io.IOException;
import java.sql.*;

public class ConnectionFactory {
    private final static String driver = "org.postgresql.Driver";
    private final static String host = "jdbc:postgresql://localhost:5432/ecommerce";
    private final static String username = "postgres";
    private final static String password = "12345";

    public static Connection getConnection() throws Exception {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(host, username, password);
        } catch (SQLException ex) {
            throw new Exception("Erro na conexão.", ex);
        }
    }

    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        closeConnection(con, stmt);
    }

}
