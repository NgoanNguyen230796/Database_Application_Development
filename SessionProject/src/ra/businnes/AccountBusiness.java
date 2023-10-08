package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Account;
import ra.entity.AccountDisplaySearch;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountBusiness {

    // Hiển thị danh sách tài khoản mỗi lần tối đa 10 sản phẩm
    public static List<Account> getAllDataAccount(int dataOfPage, int indexOfPage)throws SQLException{
        List<Account> listAccount=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_account(?,?)}");
            callSt.setInt(1, dataOfPage);
            callSt.setInt(2, indexOfPage);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listAccount = new ArrayList<>();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAcc_Id(rs.getInt("acc_Id"));
                acc.setUser_Name(rs.getString("user_Name"));
                acc.setPassword(rs.getString("password"));
                acc.setPermission(rs.getBoolean("permission"));
                acc.setEmp_Id(rs.getString("emp_Id"));
                acc.setAcc_Status(rs.getBoolean("acc_Status"));
                listAccount.add(acc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listAccount;

    }
    // Lấy dữ liệu của bảng Account theo Account Id
    public static Account getAccountById(int accountId) throws SQLException {
        Account acc = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_account_by_accountId(?)}");
            //set giá trị tham số vào
            callSt.setInt(1, accountId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                acc = new Account();
                acc.setAcc_Id(rs.getInt("acc_Id"));
                acc.setUser_Name(rs.getString("user_Name"));
                acc.setPassword(rs.getString("password"));
                acc.setPermission((rs.getBoolean("permission")));
                acc.setEmp_Id(rs.getString("emp_Id"));
                acc.setAcc_Status(rs.getBoolean("acc_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return acc;
    }

    // Lấy dữ liệu của bảng Account ngoài input Account Id
    public static List<Account> getAccountOtherByAccountId(int accountId) throws SQLException {
        List<Account> listAccount=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_account_other_account_Id(?)}");
            //set giá trị tham số vào
            callSt.setInt(1, accountId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            listAccount = new ArrayList<>();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAcc_Id(rs.getInt("acc_Id"));
                acc.setUser_Name(rs.getString("user_Name"));
                acc.setPassword(rs.getString("password"));
                acc.setPermission((rs.getBoolean("permission")));
                acc.setEmp_Id(rs.getString("emp_Id"));
                acc.setAcc_Status(rs.getBoolean("acc_Status"));
                listAccount.add(acc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listAccount;
    }


    //Đếm số bản ghi trong bảng Account
    public static int getCountAllDataAccount() throws SQLException{
        int cntAccount=0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_count_all_data_account()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                cntAccount = rs.getInt("cntAccount");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntAccount;

    }

    // Thêm mới tài khoản vào bảng Account
    public static boolean creatDataAccount(Account acc) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_account(?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setString(1,acc.getUser_Name());
            callSt.setString(2, acc.getPassword());
            callSt.setBoolean(3, acc.getPermission());
            callSt.setString(4, acc.getEmp_Id());
            callSt.setBoolean(5, acc.getAcc_Status());
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
    // Đếm số lượng theo UserName
    public static Account getAccountByUserName(String userName) throws SQLException {
        Account ac = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_account_by_userName(?)}");
            //set giá trị tham số vào
            callSt.setString(1, userName);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                ac = new Account();
                ac.setAcc_Id(rs.getInt("acc_Id"));
                ac.setUser_Name(rs.getString("user_Name"));
                ac.setPassword(rs.getString("password"));
                ac.setPermission(rs.getBoolean("permission"));
                ac.setEmp_Id(rs.getString("emp_Id"));
                ac.setAcc_Status(rs.getBoolean("acc_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return ac;
    }
    // Cập nhật trạng thái tài khoản
    public static boolean updateDataAccountStatus(Account acc) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_account_Status(?,?)}");
            callSt.setString(1,acc.getUser_Name());
            callSt.setBoolean(2, acc.getAcc_Status());
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

    public static List<Account> searchDataAccountByAccountUserName(String accountSearch) throws SQLException {
        List<Account> listAccount= null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        String employeeName=null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_account_by_accountUserName_or_Emp_Name(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, accountSearch);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listAccount = new ArrayList<>();
            while (rs.next()) {
                AccountDisplaySearch ac =new AccountDisplaySearch();
                ac.setAcc_Id(rs.getInt("acc_Id"));
                ac.setUser_Name(rs.getString("user_Name"));
                ac.setPassword(rs.getString("password"));
                ac.setPermission(rs.getBoolean("permission"));
                ac.setEmp_Id(rs.getString("emp_Id"));
                ac.setEm_Name(rs.getString("emName"));
                ac.setAcc_Status(rs.getBoolean("acc_Status"));
                listAccount.add(ac);
//                employeeName=rs.getString("emName");
//                System.out.printf("%-20s", employeeName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listAccount;
    }


}
