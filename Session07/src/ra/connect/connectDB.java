package ra.connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/quan_ly_book";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Ngoan23071996";

    //1.Phương thức mở connection đến DB và trả về đối tượng connection để làm việc
    public static Connection openConnection() throws ClassNotFoundException {
        //Khai báo đối tượng connection
        Connection conn = null;
        //Set driver cho DriverManager
        try {
            Class.forName(DRIVER);
            //Khởi tạo đối tượng conn từ DriverManager
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //2.Đóng kết nối connection
    //Test kết nối thành công hay không
    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = connectDB.openConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }

    public static void closeConnection(Connection conn, CallableStatement callSt) throws SQLException {
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
