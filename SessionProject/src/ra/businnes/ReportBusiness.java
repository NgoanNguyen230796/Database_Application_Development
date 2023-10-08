package ra.businnes;

import ra.connect.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportBusiness {
    // 1. Thống kê chi phí(theo phiếu nhập) theo ngày, tháng, năm
    public static int statisticsExpenseReceiptByDayMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String type=null;
        int sumReceipt = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_expense_receipt_by_day_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("auth_Date");
                sumReceipt = rs.getInt("sumReceipt");
                System.out.printf("| %-20s | %-20d |\n", date, sumReceipt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return sumReceipt;
    }
    //2. Thống kê chi phí(theo phiếu nhập) theo khoảng thời gian theo tháng và năm
    public static int statisticsExpenseReceiptByMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String type=null;
        int sumReceipt = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_expense_receipt_by_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("monthYear");
                sumReceipt = rs.getInt("sumReceipt");
                System.out.printf("| %-20s | %-20d |\n", date, sumReceipt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return sumReceipt;
    }
    // 3. Thống kê doanh thu theo ngày, tháng, năm
    public static float statisticsRevenueBillByDayMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String type=null;
        float revenueBillByDayMonthYear = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_revenue_bill_by_day_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("auth_Date");
                revenueBillByDayMonthYear = rs.getFloat("revenueBillByDayMonthYear");
                System.out.printf("| %-20s | %-20.1f |\n", date, revenueBillByDayMonthYear);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return revenueBillByDayMonthYear;
    }
    //4. Thống kê doanh thu theo  khoảng thời gian tháng và năm
    public static long statisticsRevenueBillByMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String type=null;
        long revenueBillByMonthYear = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_revenue_bill_by_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("monthYear");
                revenueBillByMonthYear = rs.getLong("revenueBillByMonthYear");
                System.out.printf("| %-20s | %-20d |\n", date, revenueBillByMonthYear);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return revenueBillByMonthYear;
    }

    //5. Thống kê số nhân viên theo từng trạng thái
    public static int statisticsEmployeeByEmpStatus() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        int Emp_Status=0;
        String convertEmp_Status=null;
        int cntEmployee = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_employee_by_Emp_Status()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Emp_Status = rs.getInt("emp_Status");
                convertEmp_Status=Emp_Status==0?"Hoạt động":Emp_Status==1?"Nghỉ chế độ":"Nghỉ việc";
                cntEmployee = rs.getInt("cntEmpStatus");
                System.out.printf("| %-20s | %-20d |\n", convertEmp_Status, cntEmployee);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntEmployee;
    }

    //6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian
    public static void statisticsMaxReceiptProductByMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String product_Name = null;
        int cntMaxReceipt = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_max_receipt_product_by_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("monthYear");
                product_Name = rs.getString("product_Name");
                cntMaxReceipt = rs.getInt("cntMaxReceipt");
                System.out.printf("| %-20s | %-30s | %-25d |\n", date, product_Name,cntMaxReceipt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    }
    //7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian
    public static void statisticsMinReceiptProductByMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String product_Name = null;
        int cntMinReceipt = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_min_receipt_product_by_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("monthYear");
                product_Name = rs.getString("product_Name");
                cntMinReceipt = rs.getInt("cntMinReceipt");
                System.out.printf("| %-20s | %-30s | %-25d |\n", date, product_Name,cntMinReceipt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    }
    //8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian
    public static void statisticsMaxBillProductByMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String product_Name = null;
        int cntMaxBill = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_max_bill_product_by_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("monthYear");
                product_Name = rs.getString("product_Name");
                cntMaxBill = rs.getInt("cntMaxBill");
                System.out.printf("| %-20s | %-30s | %-25d |\n", date, product_Name,cntMaxBill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    }
    // 9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian
    public static void statisticsMinBillProductByMonthYear() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        String date = null;
        String product_Name = null;
        int cntMinBill = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_min_bill_product_by_month_year()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                date = rs.getString("monthYear");
                product_Name = rs.getString("product_Name");
                cntMinBill = rs.getInt("cntMinBill");
                System.out.printf("| %-20s | %-30s | %-25d |\n", date, product_Name,cntMinBill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    }
}
