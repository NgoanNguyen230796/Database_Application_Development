package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.StatisticsForBill;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportBusiness {
    // 1. Thống kê chi phí(theo phiếu nhập) theo ngày, tháng, năm
    public static List<StatisticsForBill> statisticsExpenseReceiptByDayMonthYear(String inputDate) throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_expense_receipt_by_day_month_year(?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,inputDate);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setDate( rs.getString("auth_Date"));
                sta.setStatisticsValues(String.valueOf(rs.getInt("sumReceipt")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    return listStatistics;
    }
    //2. Thống kê chi phí(theo phiếu nhập) theo khoảng thời gian theo tháng và năm
    public static List<StatisticsForBill> statisticsExpenseReceiptByInterval(String dateFrom,String dateTo) throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_expense_receipt_by_Interval(?,?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,dateFrom);
            callSt.setString(2,dateTo);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setStatisticsValues(String.valueOf(rs.getInt("sumReceipt")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }
    // 3. Thống kê doanh thu theo ngày, tháng, năm
    public static List<StatisticsForBill>  statisticsRevenueBillByDayMonthYear(String inputDate) throws SQLException {
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_revenue_bill_by_day_month_year(?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,inputDate);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setDate( rs.getString("auth_Date"));
                sta.setStatisticsValues(String.valueOf(rs.getInt("revenueBillByDayMonthYear")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }
    //4. Thống kê doanh thu theo  khoảng thời gian tháng và năm
    public static List<StatisticsForBill>  statisticsRevenueBillByMonthYear(String dateFrom,String dateTo) throws SQLException {
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_revenue_bill_by_Interval(?,?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,dateFrom);
            callSt.setString(2,dateTo);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setStatisticsValues(String.valueOf(rs.getInt("revenueBillByMonthYear")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }

    //5. Thống kê số nhân viên theo từng trạng thái
//    public static List<StatisticsForBill> statisticsEmployeeByEmpStatus() throws SQLException {
//        List<StatisticsForBill> listStatistics = null;
//        Connection conn = null;
//        CallableStatement callSt = null;
//        try {
//            conn = ConnectDB.openConnection();
//            callSt = conn.prepareCall("{call statistics_employee_by_Emp_Status()}");
//            //Thực hiện gọi procedue
//            ResultSet rs = callSt.executeQuery();
//            listStatistics = new ArrayList<>();
//            while (rs.next()) {
//                StatisticsForBill sta = new StatisticsForBill();
//                sta.setEmployeeStatus(rs.getInt("emp_Status"));
//                sta.setStatisticsEmployeeStatus(String.valueOf(rs.getInt("cntEmpStatus")));
//                listStatistics.add(sta);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectDB.closeConnection(conn, callSt);
//        }
//        return listStatistics;
//    }
    public static void statisticsEmployeeByEmpStatus() throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        String emStatusValue=null;
        int cntEmpStatus=0;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_employee_by_Emp_Status()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                emStatusValue= rs.getInt("emp_Status")==0?"Hoạt động":rs.getInt("emp_Status")==1?"Nghỉ chế độ":"Nghỉ việc";
                cntEmpStatus= rs.getInt("cntEmpStatus");
                System.out.printf("| %-20s | %-20d |\n",emStatusValue,cntEmpStatus);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    }

    //6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian
    public static List<StatisticsForBill> statisticsMaxReceiptProductByMonthYear(String dateFrom,String dateTo) throws SQLException {
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_max_receipt_product_by_month_year(?,?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,dateFrom);
            callSt.setString(2,dateTo);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setDate( rs.getString("monthYear"));
                sta.setProductName(rs.getString("product_Name"));
                sta.setStatisticsValues(String.valueOf(rs.getInt("cntMaxReceipt")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }
    //7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian
    public static List<StatisticsForBill>  statisticsMinReceiptProductByMonthYear(String dateFrom,String dateTo) throws SQLException {
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_min_receipt_product_by_month_year(?,?)}");
            callSt.setString(1,dateFrom);
            callSt.setString(2,dateTo);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setDate( rs.getString("monthYear"));
                sta.setProductName(rs.getString("product_Name"));
                sta.setStatisticsValues(String.valueOf(rs.getInt("cntMaxReceipt")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }
    //8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian
    public static List<StatisticsForBill> statisticsMaxBillProductByMonthYear(String dateFrom,String dateTo) throws SQLException {
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_max_bill_product_by_month_year(?,?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,dateFrom);
            callSt.setString(2,dateTo);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setDate( rs.getString("monthYear"));
                sta.setProductName(rs.getString("product_Name"));
                sta.setStatisticsValues(String.valueOf(rs.getInt("cntMaxBill")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }
    // 9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian
    public static List<StatisticsForBill>  statisticsMinBillProductByMonthYear(String dateFrom,String dateTo) throws SQLException {
        List<StatisticsForBill> listStatistics = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_min_bill_product_by_month_year(?,?)}");
            //Thực hiện gọi procedue
            callSt.setString(1,dateFrom);
            callSt.setString(2,dateTo);
            ResultSet rs = callSt.executeQuery();
            listStatistics = new ArrayList<>();
            while (rs.next()) {
                StatisticsForBill sta = new StatisticsForBill();
                sta.setDate( rs.getString("monthYear"));
                sta.setProductName(rs.getString("product_Name"));
                sta.setStatisticsValues(String.valueOf(rs.getInt("cntMinBill")));
                listStatistics.add(sta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listStatistics;
    }
}
