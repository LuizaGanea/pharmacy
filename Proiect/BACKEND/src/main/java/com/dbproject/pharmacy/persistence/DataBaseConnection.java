package com.dbproject.pharmacy.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection=null;

    public static Connection getConnection() {
        if(connection==null){
            try {
                connection = DriverManager.getConnection("jdbc:oracle:thin:@bd-dc.cs.tuiasi.ro:1539/orcl",
                        "bd070", "bd070");
                connection.setAutoCommit(false);
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}
