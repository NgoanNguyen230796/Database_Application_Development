package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Bill;
import ra.entity.BillDetailDisplay;
import ra.entity.Bill_Detail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDetailBusiness {
    public static List<Bill_Detail> getAllDataBillDetailByReceipt(long billId) throws SQLException {
        List<Bill_Detail> listBillDetail = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_billDetail_by_receipt(?)}");
            //set giá trị tham số vào
            callSt.setLong(1, billId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            listBillDetail=new ArrayList<>();
            while (rs.next()) {
                BillDetailDisplay billDetail=new BillDetailDisplay();
                billDetail.setBill_Detail_Id(rs.getLong("bill_Detail_Id"));
                billDetail.setBill_Id(rs.getLong("bill_Id"));
                billDetail.setBillCode(rs.getString("billCode"));
                billDetail.setProduct_Id(rs.getString("product_Id"));
                billDetail.setQuantity(rs.getInt("quantity"));
                billDetail.setPrice(rs.getFloat("price"));
                listBillDetail.add(billDetail);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listBillDetail;
    }

    public static Bill_Detail getAllDataBillDetailByBillDetailIdAnd_bill_Type1(long billDetailId) throws SQLException {
        Bill_Detail billDetail = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_Bill_Detail_by_Bill_Detail_Id_And_bill_Type1(?)}");
            //set giá trị tham số vào
            callSt.setLong(1, billDetailId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về

            while (rs.next()) {
                billDetail=new Bill_Detail();
                billDetail.setBill_Detail_Id(rs.getLong("bill_Detail_Id"));
                billDetail.setBill_Id(rs.getLong("bill_Id"));
                billDetail.setProduct_Id(rs.getString("product_Id"));
                billDetail.setQuantity(rs.getInt("quantity"));
                billDetail.setPrice(rs.getFloat("price"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return billDetail;
    }
    public static Bill_Detail getAllDataBillDetailByBillDetailIdAnd_bill_Type0(long billDetailId) throws SQLException {
        Bill_Detail billDetail = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_Bill_Detail_by_Bill_Detail_Id_And_bill_Type0(?)}");
            //set giá trị tham số vào
            callSt.setLong(1, billDetailId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về

            while (rs.next()) {
                billDetail=new Bill_Detail();
                billDetail.setBill_Detail_Id(rs.getLong("bill_Detail_Id"));
                billDetail.setBill_Id(rs.getLong("bill_Id"));
                billDetail.setProduct_Id(rs.getString("product_Id"));
                billDetail.setQuantity(rs.getInt("quantity"));
                billDetail.setPrice(rs.getFloat("price"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return billDetail;
    }

    public static boolean creatDataBillDetail(Bill_Detail billDetail) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_bill_detail(?,?,?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setLong(1,billDetail.getBill_Id());
            callSt.setString(2, billDetail.getProduct_Id());
            callSt.setInt(3, billDetail.getQuantity());
            callSt.setFloat(4, billDetail.getPrice());
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
    public static boolean updateDataBillDetail(Bill_Detail billDetail) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_Bill_Detail(?,?,?,?,?)}");
            callSt.setLong(1,billDetail.getBill_Detail_Id());
            callSt.setLong(2, billDetail.getBill_Id());
            callSt.setString(3, billDetail.getProduct_Id());
            callSt.setInt(4, billDetail.getQuantity());
            callSt.setFloat(5, billDetail.getPrice());
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

    public static Bill getBillIByBillIdOrBillCode(String inputBill) throws SQLException {
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
                bil.setCreated_Bill(String.valueOf(rs.getDate("created").toLocalDate()));
                bil.setEmp_Id_Auth(rs.getString("emp_Id_Auth"));
                bil.setAuth_Date(String.valueOf(rs.getDate("auth_Date").toLocalDate()));
                bil.setBill_Status(rs.getShort("bill_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return bil;
    }
    public static List<Bill_Detail> getAllDataBillDetail() throws SQLException {
        List<Bill_Detail> listBillDetail = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_Bill_Detail_By_Bill_Type1()}");
            //set giá trị tham số vào
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            listBillDetail=new ArrayList<>();
            while (rs.next()) {
                BillDetailDisplay billDetail=new BillDetailDisplay();
                billDetail.setBill_Detail_Id(rs.getLong("bill_Detail_Id"));
                billDetail.setBill_Id(rs.getLong("bill_Id"));
                billDetail.setBillCode(rs.getString("billCode"));
                billDetail.setProduct_Id(rs.getString("product_Id"));
                billDetail.setQuantity(rs.getInt("quantity"));
                billDetail.setPrice(rs.getFloat("price"));
                listBillDetail.add(billDetail);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listBillDetail;
    }
    public static List<Bill_Detail> getAllDataBill() throws SQLException {
        List<Bill_Detail> listBillDetail = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_Bill_By_Bill_Type1()}");
            //set giá trị tham số vào
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            listBillDetail=new ArrayList<>();
            while (rs.next()) {
                BillDetailDisplay billDetail=new BillDetailDisplay();
                billDetail.setBill_Detail_Id(rs.getLong("bill_Detail_Id"));
                billDetail.setBill_Id(rs.getLong("bill_Id"));
                billDetail.setBillCode(rs.getString("billCode"));
                billDetail.setProduct_Id(rs.getString("product_Id"));
                billDetail.setQuantity(rs.getInt("quantity"));
                billDetail.setPrice(rs.getFloat("price"));
                listBillDetail.add(billDetail);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listBillDetail;
    }

}
