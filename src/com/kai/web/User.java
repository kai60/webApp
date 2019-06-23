package com.kai.web;

import com.kai.utility.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String name;
    private String password;
    private Integer id;

    public User(String name, String password, Integer id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public User login()
    {
        User loginUser=null;
        Connection c=null;
        PreparedStatement statement=null;
        ResultSet resultSet =null;
        String sql="select * from user where name=? and password=?";
        try {

            c=DB.getConnection();
            statement= c.prepareStatement(sql);
            statement.setString(1,this.name);
            statement.setString(2,this.password);

            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                Integer id=resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");

                User user1 = new User(name, password, id);
                System.out.println(user1);
                loginUser=user1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DB.close(c,statement,resultSet);

            return loginUser;
        }


    }

    public static void main(String[] args) {




    }
}
