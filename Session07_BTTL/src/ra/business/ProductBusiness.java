package ra.business;

import ra.connect.ConnectDB;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {
    public static List<Product> getAllProduct() throws SQLException {
        //1.Tạo đối tượng Connection
        //2.Tạo đối tượng CallableStatement
        //3.Gọi procedue
        //4.Xử lý dữ liệu và trả về listProduct

        List<Product> listProduct = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_product()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getInt("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setProductStatus(rs.getBoolean("productStatus"));
                listProduct.add(pr);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;
    }

    public static boolean creatProduct(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call creat_data_product(?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, pr.getProductName());
            callSt.setFloat(2, pr.getPrice());
            callSt.setBoolean(3, pr.isProductStatus());
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
            // Thực hiện gọi procrdue
            int productId = callSt.executeUpdate();
            System.out.println("Mã sản phẩm vừa thêm mới :" + productId);
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateProduct(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_product(?,?,?,?)}");
            callSt.setInt(1, pr.getProductId());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(2, pr.getProductName());
            callSt.setFloat(3, pr.getPrice());
            callSt.setBoolean(4, pr.isProductStatus());
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
            // Thực hiện gọi procrdue
            int productId = callSt.executeUpdate();
//            System.out.println("Mã sản phẩm vừa được cập nhật :"+productId);
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Product getProductById(int productId) throws SQLException {
        Product product = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_id(?)}");
            //set giá trị tham số vào
            callSt.setInt(1, productId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getFloat("price"));
                product.setProductStatus(rs.getBoolean("productStatus"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return product;
    }

    public static boolean deleteDataProduct(int productId) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call delete_data_product(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setInt(1, productId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Product> searchDataProduct(int productId) throws SQLException {
        List<Product> listProduct = null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_by_productId(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setInt(1, productId);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getInt("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setProductStatus(rs.getBoolean("productStatus"));
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
