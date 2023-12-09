package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private Connection getConnect() {
        final String url = "jdbc:postgresql://217.107.219.154:49307/bonch_2105432";
        final String user = "bonch_2105432";
        final String password = "XnZ2eMhC2gw=";

        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }

    public ResultSet select(String sql) {
        ResultSet rs = null;
        try {
            Connection con = getConnect();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
//            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public boolean insert(String sql){
        Statement st = null;
        try {
            Connection con = getConnect();
            st = con.createStatement();
            int rowsAffected = st.executeUpdate(sql);
            con.close();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return false;
    }

    public boolean update(String sql) {
        try {
            Connection con = getConnect();
            int rowsAffected = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(sql);
            con.close();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(String sql) {
        try {
            Connection con = getConnect();
            int rowsAffected = con.createStatement().executeUpdate(sql);
            con.close();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
