/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DB_connection {
    private static Connection Myconnection;

    public static void init() {
        try {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {

                System.out.println("Class not found");
            }
            Myconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "root");

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public static Connection getConnection() {
        if (Myconnection != null) {
            return Myconnection;
        } else {
            try {

                Myconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "root", "root");

            } catch (Exception ex) {

            }
        }
        return Myconnection;

    }

    public static void close(ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }


}