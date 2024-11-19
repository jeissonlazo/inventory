package org.example.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static String URL = "jdbc:postgresql://ep-long-pine-a4ah2n1q.us-east-1.aws.neon.tech/inventario";
    static String USER = "shopping_cart_owner";
    static String PASSWORD = "DPyCA59TOQhu";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
