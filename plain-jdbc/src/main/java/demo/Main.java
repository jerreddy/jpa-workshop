package demo;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        //createTable();
       // insertUser("siva","siva@gmail.com");
        //updateUser(1, "siva1","siva1@gmail.com");
        findUserById(1);
    }

    static void insertUser(String name, String email) throws SQLException {
        String sql = "insert into users(name , email) values(?,?)";
        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    static void findUserById(int id) {
        String sql = "select * from users where id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println(rs.getInt("id")+":"+rs.getString("name")+":"+rs.getString("email"));
            } else {
                System.out.println("No user found with id="+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateUser(int id, String name, String email) throws SQLException {
        String sql = "update users set name=? , email=? where id=?";
        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    static void createTable() throws SQLException {
        String sql = "create table users(id int auto_increment primary key, name varchar(100) not null, email varchar(100))";
        Connection conn = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","admin");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

}
