package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {

    // Hiển thị danh sách sản phẩm mỗi lần tối đa 10 sản phẩm
    public static List<Product> getAllDataProduct(int dataOfPage, int indexOfPage)throws SQLException{
        List<Product> listProduct=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_product(?,?)}");
            callSt.setInt(1, dataOfPage);
            callSt.setInt(2, indexOfPage);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProduct_Id(rs.getString("product_Id"));
                pr.setProduct_Name(rs.getString("product_Name"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setCreated(String.valueOf(rs.getDate("created")));
                pr.setBatch(rs.getShort("batch"));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setProduct_Status(rs.getBoolean("product_Status"));
                listProduct.add(pr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;

    }
    // Lấy dữ liệu của bảng Product theo Product Id
    public static Product getProductById(String productId) throws SQLException {
        Product pr = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_productId(?)}");
            //set giá trị tham số vào
            callSt.setString(1, productId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                pr = new Product();
                pr.setProduct_Id(rs.getString("product_Id"));
                pr.setProduct_Name(rs.getString("product_Name"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setCreated(String.valueOf(rs.getDate("created")));
                pr.setBatch(rs.getShort("batch"));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setProduct_Status(rs.getBoolean("product_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return pr;
    }
    // Lấy dữ liệu của bảng Product ngoài input Product Id
    public static List<Product> getProductOtherById(String productId) throws SQLException {
        List<Product> listProduct=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_product_other_product_Id(?)}");
            //set giá trị tham số vào
            callSt.setString(1, productId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProduct_Id(rs.getString("product_Id"));
                pr.setProduct_Name(rs.getString("product_Name"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setCreated(String.valueOf(rs.getDate("created")));
                pr.setBatch(rs.getShort("batch"));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setProduct_Status(rs.getBoolean("product_Status"));
                listProduct.add(pr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;
    }
    //Đếm số bản ghi trong bảng Product
    public static int getCountAllDataProduct() throws SQLException{
        int cntProduct=0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_count_all_data_product()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                cntProduct = rs.getInt("cntProduct");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntProduct;

    }
    public static Product getProductByName(String productName) throws SQLException {
        Product pr = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_productName(?)}");
            //set giá trị tham số vào
            callSt.setString(1, productName);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                pr = new Product();
                pr.setProduct_Id(rs.getString("product_Id"));
                pr.setProduct_Name(rs.getString("product_Name"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setCreated(String.valueOf(rs.getDate("created")));
                pr.setBatch(rs.getShort("batch"));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setProduct_Status(rs.getBoolean("product_Status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return pr;
    }
    // Thêm mới sản phẩm  vào bảng Product
    public static boolean creatDataProduct(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_product(?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1,pr.getProduct_Id());
            callSt.setString(2, pr.getProduct_Name());
            callSt.setString(3, pr.getManufacturer());
            callSt.setString(4, pr.getCreated());
            callSt.setShort(5, pr.getBatch());
            callSt.setBoolean(6, pr.getProduct_Status());
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

    // Cập nhật sản phẩm
    public static boolean updateDataProduct(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_product(?,?,?,?,?)}");
            callSt.setString(1,pr.getProduct_Id());
            callSt.setString(2, pr.getProduct_Name());
            callSt.setString(3, pr.getManufacturer());
            callSt.setString(4, pr.getCreated());
            callSt.setShort(5, pr.getBatch());
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

    //Cập nhật trạng thái sản phẩm
    public static boolean updateProductStatus(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_by_productStatus(?,?)}");
            callSt.setString(1, pr.getProduct_Id());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setBoolean(2, pr.getProduct_Status());
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

    public static List<Product> searchDataProductByProductName(int dataOfPage,int indexOfPage,String productName) throws SQLException {
        List<Product> listProduct= null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_product_by_product_Name(?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setInt(1, dataOfPage);
            callSt.setInt(2, indexOfPage);
            callSt.setString(3, productName);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProduct_Id(rs.getString("product_Id"));
                pr.setProduct_Name(rs.getString("product_Name"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setCreated(String.valueOf(rs.getDate("created")));
                pr.setBatch(rs.getShort("batch"));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setProduct_Status(rs.getBoolean("product_Status"));
                listProduct.add(pr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;
    }

    // Lấy toàn bộ dữ liệu khi search data
    public static int getAllDataSearchProduct(String productName)throws SQLException{
       int cntProductNameSearch=0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_get_all_data_product_by_product_Name(?)}");
            callSt.setString(1, productName);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            while (rs.next()) {
                cntProductNameSearch=rs.getInt("cntProductNameSearch");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntProductNameSearch;
    }

    public static List<Product> getAllDataProductForBillDetail()throws SQLException{
        List<Product> listProduct=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_product_for_billDetail()}");

            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProduct_Id(rs.getString("product_Id"));
                pr.setProduct_Name(rs.getString("product_Name"));
                pr.setManufacturer(rs.getString("manufacturer"));
                pr.setCreated(String.valueOf(rs.getDate("created")));
                pr.setBatch(rs.getShort("batch"));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setProduct_Status(rs.getBoolean("product_Status"));
                listProduct.add(pr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;

    }




}
