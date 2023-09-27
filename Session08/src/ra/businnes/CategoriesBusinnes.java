package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Categories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoriesBusinnes {
    //1.1. Hiển thị các danh mục (chỉ hiển thị các danh mục có status = 1)
    public static List<Categories> getAllCategories() throws SQLException {
        //1.Tạo đối tượng Connection
        //2.Tạo đối tượng CallableStatement
        //3.Gọi procedue
        //4.Xử lý dữ liệu và trả về listProduct
        List<Categories> listCategories = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_categories()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories ca = new Categories();
                ca.setCatalogId(rs.getInt("catalogId"));
                ca.setCatalogName(rs.getString("catalogName"));
                ca.setPriority(rs.getInt("priority"));
                ca.setCatalogStatus(rs.getBoolean("catalogStatus"));
                listCategories.add(ca);
            }

        } catch (SQLException ex1){
            ex1.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static List<Categories> getAllCategoriesByPriority() throws SQLException {
        //1.Tạo đối tượng Connection
        //2.Tạo đối tượng CallableStatement
        //3.Gọi procedue
        //4.Xử lý dữ liệu và trả về listProduct
        List<Categories> listCategories = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_categories_by_priority()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories ca = new Categories();
                ca.setCatalogId(rs.getInt("catalogId"));
                ca.setCatalogName(rs.getString("catalogName"));
                ca.setPriority(rs.getInt("priority"));
                ca.setCatalogStatus(rs.getBoolean("catalogStatus"));
                listCategories.add(ca);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static boolean creatDataCategories(Categories ca) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_categories(?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, ca.getCatalogName());
            callSt.setInt(2, ca.getPriority());
            callSt.setBoolean(3, ca.getCatalogStatus());
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

    public static Categories getCategoriesById(int catalogId) throws SQLException {
        Categories ca = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_categories_by_id(?)}");
            //set giá trị tham số vào
            callSt.setInt(1, catalogId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                ca = new Categories();
                ca.setCatalogId(rs.getInt("catalogId"));
                ca.setCatalogName(rs.getString("catalogName"));
                ca.setPriority(rs.getInt("priority"));
                ca.setCatalogStatus(rs.getBoolean("catalogStatus"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return ca;
    }

    public static boolean updateCategories(Categories ca) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_categories(?,?,?)}");
            callSt.setInt(1, ca.getCatalogId());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(2, ca.getCatalogName());
            callSt.setInt(3, ca.getPriority());
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

    public static List<Categories> searchDataCategories(String categoriesName) throws SQLException {
        List<Categories> listCategories = null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_by_categoriesName(?)}");
            // set dữ liệu cho các tham số vào của categories
            callSt.setString(1, categoriesName);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listCategories
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories ca = new Categories();
                ca.setCatalogId(rs.getInt("catalogId"));
                ca.setCatalogName(rs.getString("catalogName"));
                ca.setPriority(rs.getInt("priority"));
                ca.setCatalogStatus(rs.getBoolean("catalogStatus"));
                listCategories.add(ca);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }
    public static boolean deleteDataCategories(int CategoriesId) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call delete_data_categories(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setInt(1, CategoriesId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateCategoriesStatus(Categories ca) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_by_catalogStatus(?,?)}");
            callSt.setInt(1, ca.getCatalogId());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setBoolean(2, ca.getCatalogStatus());
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra ?????
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



}
