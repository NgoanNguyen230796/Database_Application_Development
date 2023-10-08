package ra.connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static ra.config.DataSource.*;

public class ConnectDB {

    //Mở connection đến DB
    public static Connection openConnection() {
        //Khai báo đối tượng connection
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            //Set driver cho DriverManager
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //Test kết nối thành công hay không
    public static void main(String[] args) {
        Connection conn = ConnectDB.openConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }

    public static void closeConnection(Connection conn, CallableStatement callSt) {
        if (conn != null) {
            try {
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (callSt != null) {
            try {
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
