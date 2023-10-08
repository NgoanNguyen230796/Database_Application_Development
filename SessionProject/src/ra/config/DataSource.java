package ra.config;

import ra.connect.ConnectDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataSource {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/quan_ly_kho";
    public static final String USER = "root";
    public static final String PASS = "Ngoan23071996";

}
