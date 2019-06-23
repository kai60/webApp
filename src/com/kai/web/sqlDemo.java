package com.kai.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlDemo {


    public  static Connection getConnection ()
    {
        Connection c=null;
        try {
           c= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

        return c;
    }

    public static void  insertData()
    {
        Connection connection = null;
        PreparedStatement statement=null;
         connection = sqlDemo.getConnection();


        try {
            statement=connection.prepareStatement("insert into pagetest values (?,?)");
            statement.setInt(1,2);
            statement.setString(2,"text");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }

        }



    }

    public static void main(String[] args) {
        sqlDemo.insertData();;
    }
}
