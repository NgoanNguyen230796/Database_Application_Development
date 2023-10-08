package ra.businnes;

import ra.colors.ColorsMenu;
import ra.connect.ConnectDB;
import ra.entity.Bill;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillBusiness {
    public static List<Bill> getAllDataBill() throws SQLException {
        List<Bill> listBill = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_Bill()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil = new Bill();
                bil.setBill_Id(rs.getLong("bill_Id"));
                bil.setBill_Code(rs.getString("bill_Code"));
                bil.setBill_Type(rs.getBoolean("bill_Type"));
                bil.setEmp_Id_Created(rs.getString("emp_Id_Created"));
                bil.setCreated_Bill(String.valueOf(rs.getDate("created")));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date")));
                bil.setBill_Status(rs.getShort("bill_Status"));
                listBill.add(bil);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listBill;

    }

    public static List<Bill> getAllDataBillByBillStatus0() throws SQLException {
        List<Bill> listBill = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_Bill_by_bill_Type_and_bill_Status0()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil = new Bill();
                bil.setBill_Id(rs.getLong("bill_Id"));
                bil.setBill_Code(rs.getString("bill_Code"));
                bil.setBill_Type(rs.getBoolean("bill_Type"));
                bil.setEmp_Id_Created(rs.getString("emp_Id_Created"));
                bil.setCreated_Bill(String.valueOf(rs.getDate("created")));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date")));
                bil.setBill_Status(rs.getShort("bill_Status"));
                listBill.add(bil);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listBill;

    }

    public static List<Bill> getAllDataBillByBillStatusOther2() throws SQLException {
        List<Bill> listBill = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_Bill_by_bill_Type_and_bill_StatusOther2()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil = new Bill();
                bil.setBill_Id(rs.getLong("bill_Id"));
                bil.setBill_Code(rs.getString("bill_Code"));
                bil.setBill_Type(rs.getBoolean("bill_Type"));
                bil.setEmp_Id_Created(rs.getString("emp_Id_Created"));
                bil.setCreated_Bill(String.valueOf(rs.getDate("created")));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date")));
                bil.setBill_Status(rs.getShort("bill_Status"));
                listBill.add(bil);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listBill;

    }

    // Lấy dữ liệu của bảng Bill theo Bill Code
    public static Bill getBillByBillCode(String billCode) throws SQLException {
        Bill bil = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_Bill_by_Bill_Code(?)}");
            //set giá trị tham số vào
            callSt.setString(1, billCode);

            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                bil = new Bill();
                bil.setBill_Id(rs.getLong("bill_Id"));
                bil.setBill_Code(rs.getString("bill_Code"));
                bil.setBill_Type(rs.getBoolean("bill_Type"));
                bil.setEmp_Id_Created(rs.getString("emp_Id_Created"));
                bil.setCreated_Bill(String.valueOf(rs.getDate("created")));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date")));
                bil.setBill_Status(rs.getShort("bill_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return bil;
    }

    // Lấy dữ liệu theo bill Id hoặc Bill code trong bảng Bill
    public static Bill getBillByBillIdOrBillCode(String inputBill) throws SQLException {
        Bill bil = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_bill_by_billType_or_billCode(?)}");
            //set giá trị tham số vào
            callSt.setString(1, inputBill);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                bil = new Bill();
                bil.setBill_Id(rs.getLong("bill_Id"));
                bil.setBill_Code(rs.getString("bill_Code"));
                bil.setBill_Type(rs.getBoolean("bill_Type"));
                bil.setEmp_Id_Created(rs.getString("emp_Id_Created"));
                bil.setCreated_Bill(String.valueOf(rs.getDate("created")));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date")));
                bil.setBill_Status(rs.getShort("bill_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return bil;
    }

    public static Bill getBillByBillIdOrBillCodeAndBillStatus0(String inputBill) throws SQLException {
        Bill bil = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_Bill_by_billType_or_billCode_and_billStatus(?)}");
            //set giá trị tham số vào
            callSt.setString(1, inputBill);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                bil = new Bill();
                bil.setBill_Id(rs.getLong("bill_Id"));
                bil.setBill_Code(rs.getString("bill_Code"));
                bil.setBill_Type(rs.getBoolean("bill_Type"));
                bil.setEmp_Id_Created(rs.getString("emp_Id_Created"));
                bil.setCreated_Bill(String.valueOf(rs.getDate("created")));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date")));
                bil.setBill_Status(rs.getShort("bill_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return bil;
    }

    public static boolean creatDataBill(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call creat_data_Bill(?,?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setString(1, bil.getBill_Code());
            callSt.setBoolean(2, bil.getBill_Type());
            callSt.setString(3, bil.getEmp_Id_Created());
            callSt.setString(4, String.valueOf(bil.getCreated_Bill()));
            callSt.setString(5, bil.getEmp_Id_Auth());
            callSt.setString(6, String.valueOf(bil.getAuth_Date()));
            callSt.setShort(7, bil.getBill_Status());
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean creatApproveDataBill(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call creat_approve_data_Bill(?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setString(1, bil.getEmp_Id_Auth());
            callSt.setString(2, String.valueOf(bil.getAuth_Date()));
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    // Cập nhật Bill
    public static boolean updateDataBillForBill(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_bill(?,?,?,?,?,?)}");
            callSt.setLong(1, bil.getBill_Id());
            callSt.setString(2, bil.getEmp_Id_Created());
            callSt.setString(3, String.valueOf(bil.getCreated_Bill()));
            callSt.setString(4, bil.getEmp_Id_Auth());
            callSt.setString(5, String.valueOf(bil.getAuth_Date()));
            callSt.setShort(6, bil.getBill_Status());
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateDataBillForBillUser(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_BillUser(?,?,?,?)}");
            callSt.setLong(1,bil.getBill_Id());
            callSt.setString(2, bil.getEmp_Id_Created());
            callSt.setString(3, bil.getCreated_Bill());
            callSt.setShort(4, bil.getBill_Status());
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

//    public static void isCheckApproveBill(long billUpdate) throws SQLException {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        boolean result = false;
//        try {
//            conn = ConnectDB.openConnection();
//            callSt = conn.prepareCall("{call isCheckApproveBill(?)}");
//            callSt.setLong(1,billUpdate);
//            callSt.registerOutParameter(2, Types.DECIMAL );
//            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
//            // Thực hiện gọi procrdue
//            callSt.executeUpdate();
//            //Lấy thông điệp từ stored procedure
//            String message=callSt.getString(2);
//            System.out.println(ColorsMenu.YELLOW_BOLD+message+ColorsMenu.ANSI_RESET);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectDB.closeConnection(conn, callSt);
//        }
//    }
    public static void updateDataApproveBill(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call approveBill(?,?,?,?)}");
            callSt.setLong(1, bil.getBill_Id());
            callSt.setString(2, bil.getEmp_Id_Auth());
            callSt.setString(3, String.valueOf(bil.getAuth_Date()));
            callSt.registerOutParameter(4, Types.DECIMAL );
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            String message=callSt.getString(4);
            System.out.println(ColorsMenu.YELLOW_BOLD+message+ColorsMenu.ANSI_RESET);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
    }

    // Tìm kiếm phiếu nhập trong Bill có phiếu nhập chi tiết hay không

    public static long maxIndex() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        long maxIndex = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call index_max_in_Bill()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                maxIndex = rs.getInt("maxIndex");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return maxIndex;
    }
}
