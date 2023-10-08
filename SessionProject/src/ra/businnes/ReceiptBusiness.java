package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Bill;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptBusiness {
    // Lấy tất cả dữ liệu phiếu nhập trong Bill
    public static List<Bill> getAllDataReceipt()throws SQLException {
        List<Bill> listBill=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_receipt()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil =new Bill();
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

    public static List<Bill> getAllDataReceiptByBillStatus0()throws SQLException {
        List<Bill> listBill=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_receipt_by_bill_Type_and_bill_Status0()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil =new Bill();
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
    public static List<Bill> getAllDataReceiptByBillStatusOther2()throws SQLException {
        List<Bill> listBill=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_receipt_by_bill_Type_and_bill_StatusOther2()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil =new Bill();
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
    public static Bill getReceiptByBillCode(String billCode) throws SQLException {
        Bill bil = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_receipt_by_Bill_Code(?)}");
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
    //Check trùng phiếu
    public static Bill getReceiptByBillCodeBill(String billCode) throws SQLException {
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
    public static Bill getReceiptByBillIdOrBillCode(String inputBill) throws SQLException {
        Bill bil = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_by_billType_or_billCode(?)}");
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
    public static Bill getReceiptByBillIdOrBillCodeAndBillStatus0(String inputBill) throws SQLException {
        Bill bil = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_by_billType_or_billCode_and_billStatus(?)}");
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
    public static boolean creatDataReceipt(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call creat_data_receipt(?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setString(1, bil.getBill_Code());
            callSt.setBoolean(2, bil.getBill_Type());
            callSt.setString(3, bil.getEmp_Id_Created());
            callSt.setString(4, bil.getEmp_Id_Auth());
            callSt.setString(5, bil.getAuth_Date());
            callSt.setShort(6, bil.getBill_Status());
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
    public static boolean creatApproveDataReceipt(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call creat_approve_data_receipt(?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setString(1, bil.getEmp_Id_Auth());
            callSt.setString(2, bil.getAuth_Date());
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
    // Cập nhật Receipt
    public static boolean updateDataReceiptForBill(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_receipt(?,?,?,?,?,?)}");
            callSt.setLong(1,bil.getBill_Id());
            callSt.setString(2, bil.getEmp_Id_Created());
            callSt.setString(3, bil.getCreated_Bill());
            callSt.setString(4, bil.getEmp_Id_Auth());
            callSt.setString(5, bil.getAuth_Date());
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

    public static boolean updateDataReceiptForBillUser(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_receiptUser(?,?,?,?)}");
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
    public static boolean updateDataBillForBillUser(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_receiptUser(?,?,?,?)}");
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

    public static boolean updateDataApproveReceipt(Bill bil) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call approveReceipt(?,?,?)}");
            callSt.setLong(1,bil.getBill_Id());
            callSt.setString(2,bil.getEmp_Id_Auth());
            callSt.setString(3,bil.getAuth_Date());
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

    // Lấy phiếu nhập theo USerName ,với trạng thái khác Duyệt
    public static List<Bill> getAllDataReceiptBillStatusByEmpId(String employeeId)throws SQLException {
        List<Bill> listBill=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_receipt_Bill_Status_by_Emp_id(?)}");
            callSt.setString(1, employeeId);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil =new Bill();
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
    // Lấy phiếu xuất theo USerName ,với trạng thái khác Duyệt
    public static List<Bill> getAllDataBillBillStatusByEmpId(String employeeId)throws SQLException {
        List<Bill> listBill=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_Bill_Bill_Status_by_Emp_id_User(?)}");
            callSt.setString(1, employeeId);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBill = new ArrayList<>();
            while (rs.next()) {
                Bill bil =new Bill();
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
}
