package com.kai.utility;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    private static DruidDataSource dataSource=null;
    private static Properties properties=new Properties();
    static {
        try {
            InputStream steam = DB.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(steam);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static Connection getConnection ()  {

        DruidPooledConnection connection=null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return connection;

    }
    public  static void close(Connection connection, Statement  statement, ResultSet resultSet)
    {

        if (connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
        }
        if (statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
        }
        if (resultSet!=null)
        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    public static DataSource getDataSource() {
        return dataSource;

    }

    public static void main(String[] args) {
        Connection connection = DB.getConnection();
        System.out.println(connection);

    }


}
