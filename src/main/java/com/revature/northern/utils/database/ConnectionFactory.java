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
//            Class.forName("org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final Properties props = new Properties();

    private ConnectionFactory() {
//        try {
//            props.load(new FileReader("webapps/northern/WEB-INF/classes/db/db.properties")); // added on 8/27
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        //String url = "java-react2022.cypwf3sial7p.us-east-2.rds.amazonaws.com";
       String url = "jdbc:postgresql://host.docker.internal:5432/postgres?currentSchema=northernbookstore";
        String userName ="postgres";
        String password = "revature";

        Connection conn = DriverManager.getConnection(url, userName, password);
        if (conn == null) throw new RuntimeException("Could not establish connection with the database!");
        return conn;
    }
}