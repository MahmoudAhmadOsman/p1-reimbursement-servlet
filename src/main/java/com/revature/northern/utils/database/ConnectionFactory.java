package com.revature.northern.utils.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Singleton design pattern */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final Properties props = new Properties();

    private ConnectionFactory() {

        // commented on 9/7
//        try {
//            //props.load(new FileReader("src/main/resources/db.properties"));
//           // props.load(new FileReader("webapps/northern/WEB-INF/classes/db/db.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }

    // commented on 9/7
//    public Connection getConnection() throws SQLException {
//        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
//        if (conn == null) throw new RuntimeException("Could not establish connection with the database!");
//        return conn;
//    }

    public Connection getConnection() throws SQLException {
       String url = "jdbc:postgresql://host.docker.internal:5432/postgres?currentSchema=northernbookstore";
        String userName ="postgres";
        String password = "revature";

        Connection conn = DriverManager.getConnection(url, userName, password);
        if (conn == null) throw new RuntimeException("Could not establish connection with the database!!");
        return conn;
    }
}