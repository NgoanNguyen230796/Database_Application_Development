package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBusiness {
    // Hiển thị danh sách nhân viên mỗi lần tối đa 10 sản phẩm
    public static List<Employee> getAllDataEmployee(int dataOfPage, int indexOfPage)throws SQLException{
        List<Employee> listEmployee=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_employee(?,?)}");
            callSt.setInt(1, dataOfPage);
            callSt.setInt(2, indexOfPage);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listEmployee = new ArrayList<>();
            while (rs.next()) {
                Employee emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
                listEmployee.add(emy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listEmployee;

    }
    public static List<Employee> getAllDataEmployeeNameActive()throws SQLException{
        List<Employee> listEmployee=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_employee_Name_Active()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listEmployee = new ArrayList<>();
            while (rs.next()) {
                Employee emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
                listEmployee.add(emy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listEmployee;

    }
    //Lấy toàn bộ dữ liệu để show menu
    public static List<Employee> getShowAllDataEmployeeNotInAccount()throws SQLException{
        List<Employee> listEmployee=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_show_all_data_not_in_Account()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listEmployee = new ArrayList<>();
            while (rs.next()) {
                Employee emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
                listEmployee.add(emy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listEmployee;

    }
    // Lấy dữ liệu của bảng Employee theo Employee Id
    public static Employee getEmployeeById(String employeeId) throws SQLException {
        Employee emy = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_employee_by_employeeId(?)}");
            //set giá trị tham số vào
            callSt.setString(1, employeeId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return emy;
    }
    // Lấy dữ liệu của bảng Employee ngoài input Employee Id
    public static List<Employee> getEmployeeOtherByEmployeeId(String employeeId) throws SQLException {
        List<Employee> listEmployee=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_employee_other_employee_Id(?)}");
            //set giá trị tham số vào
            callSt.setString(1, employeeId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            listEmployee = new ArrayList<>();
            while (rs.next()) {
                Employee emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
                listEmployee.add(emy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }

    //Đếm số bản ghi trong bảng Employee
    public static int getCountAllDataEmployee() throws SQLException{
        int cntEmployee=0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_count_all_data_employee()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                cntEmployee = rs.getInt("cntEmployee");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntEmployee;

    }

    // Thêm mới nhân viên vào bảng Employee
    public static boolean creatDataEmployee(Employee emy) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_employee(?,?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của Employee
            callSt.setString(1,emy.getEmp_Id());
            callSt.setString(2, emy.getEmp_Name());
            callSt.setString(3, emy.getBirth_Of_Date());
            callSt.setString(4, emy.getEmail());
            callSt.setString(5, emy.getPhone());
            callSt.setString(6, emy.getAddress());
            callSt.setShort(7, emy.getEmp_Status());
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
    public static Employee getEmployeeByName(String employeeName) throws SQLException {
        Employee emy = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_employee_by_employeeName(?)}");
            //set giá trị tham số vào
            callSt.setString(1, employeeName);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return emy;
    }

    // Cập nhật nhân viên
    public static boolean updateDataEmployee(Employee emy) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_employee(?,?,?,?,?,?,?)}");
            callSt.setString(1,emy.getEmp_Id());
            callSt.setString(2, emy.getEmp_Name());
            callSt.setString(3, emy.getBirth_Of_Date());
            callSt.setString(4, emy.getEmail());
            callSt.setString(5, emy.getPhone());
            callSt.setString(6, emy.getAddress());
            callSt.setShort(7, emy.getEmp_Status());
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

    //Cập nhật trạng thái nhân viên
    public static boolean updateEmployeeStatus(Employee emy) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_by_employeeStatus(?,?)}");
            callSt.setString(1, emy.getEmp_Id());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setShort(2, emy.getEmp_Status());
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
    //Tìm kiếm thông tin nhân viên theo mã hoặc theo tên nhân viên
    public static List<Employee> searchDataEmployeeByEmployeeNameOrEmployeeId(int dataOfPage,int indexOfPage,String employeeSearch) throws SQLException {
        List<Employee> listEmployee= null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_employee_by_employeeName_or_employeeId(?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setInt(1, dataOfPage);
            callSt.setInt(2, indexOfPage);
            callSt.setString(3, employeeSearch);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listEmployee = new ArrayList<>();
            while (rs.next()) {
                Employee emy = new Employee();
                emy.setEmp_Id(rs.getString("emp_Id"));
                emy.setEmp_Name(rs.getString("emp_Name"));
                emy.setBirth_Of_Date(String.valueOf(rs.getDate("birth_Of_Date")));
                emy.setEmail(rs.getString("email"));
                emy.setPhone(rs.getString("phone"));
                emy.setAddress(rs.getString("address"));
                emy.setEmp_Status(rs.getShort("emp_Status"));
                listEmployee.add(emy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }
    // Lấy toàn bộ dữ liệu khi search data
    public static int getAllDataSearchEmployee(String employeeSearch)throws SQLException{
        int cntEmployeeSearch=0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_get_all_data_employee_by_employeeName_or_employeeId(?)}");
            callSt.setString(1, employeeSearch);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            while (rs.next()) {
                cntEmployeeSearch=rs.getInt("cntEmployeeSearch");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntEmployeeSearch;
    }
}
