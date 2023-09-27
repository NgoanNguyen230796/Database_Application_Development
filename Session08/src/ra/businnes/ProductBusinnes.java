package ra.businnes;

import ra.connect.ConnectDB;
import ra.entity.Categories;
import ra.entity.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBusinnes {
    //1. Hiển thị sản phẩm (Chỉ hiển thị sản phẩm có trạng thái là 1)
    public static List<Product> getAllProduct() throws SQLException {
        List<Product> listProduct=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_product()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getString("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setCreates(String.valueOf(rs.getDate("creates")));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setView(rs.getInt("view"));
                pr.setCatalogId(rs.getInt("catalogId"));
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
    //1. Hiển thị sản phẩm (Chỉ hiển thị sản phẩm có trạng thái là 1 và 0)
    public static List<Product> getProduct() throws SQLException {
        List<Product> listProduct=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_data_product()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getString("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setCreates(String.valueOf(rs.getDate("creates")));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setView(rs.getInt("view"));
                pr.setCatalogId(rs.getInt("catalogId"));
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
    //2. Hiển thị sản phẩm theo giá giảm dần
    public static List<Product> getAllProductByPrice() throws SQLException {
        List<Product> listProduct=null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_product_by_price()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getString("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setCreates(String.valueOf(rs.getDate("creates")));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setView(rs.getInt("view"));
                pr.setCatalogId(rs.getInt("catalogId"));
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
    //3. Thêm mới sản phẩm
    public static boolean creatDataProduct(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_product(?,?,?,?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1,pr.getProductId());
            callSt.setString(2, pr.getProductName());
            callSt.setFloat(3, pr.getPrice());
            callSt.setString(4, pr.getCreates());
            callSt.setInt(5, pr.getQuantity());
            callSt.setInt(6, pr.getView());
            callSt.setBoolean(7, pr.getProductStatus());
            callSt.setInt(8, pr.getCatalogId());

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
    //Lấy dữ liệu sản phẩm theo Id
    public static Product getProductById(String productId) throws SQLException {
        Product pr = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_id(?)}");
            //set giá trị tham số vào
            callSt.setString(1, productId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                pr = new Product();
                pr.setProductId(rs.getString("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setCreates(String.valueOf(rs.getDate("creates")));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setView(rs.getInt("view"));
                pr.setProductStatus(rs.getBoolean("productStatus"));
                pr.setCatalogId(rs.getInt("catalogId"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
       return pr;
    }
    //4. Cập nhật sản phẩm
    public static boolean updateDataProduct(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_product(?,?,?,?,?,?,?)}");
            callSt.setString(1,pr.getProductId());
            callSt.setString(2, pr.getProductName());
            callSt.setFloat(3, pr.getPrice());
            callSt.setString(4, pr.getCreates());
            callSt.setInt(5, pr.getQuantity());
            callSt.setInt(6, pr.getView());
            callSt.setInt(7, pr.getCatalogId());
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
    //5. Cập nhật trạng thái sản phẩm
    public static boolean updateProductStatus(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_by_productStatus(?,?)}");
            callSt.setString(1, pr.getProductId());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setBoolean(2, pr.getProductStatus());
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
    //6. Xóa sản phẩm
    public static boolean deleteDataProduct(String productId) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call delete_data_product(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, productId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return result;
    }
    //7. Tìm kiếm sản phẩm theo tên sản phẩm
    public static List<Product> searchDataProductByProductName(String productName) throws SQLException {
        List<Product> listProduct= null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_by_productName(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, productName);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getString("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setCreates(String.valueOf(rs.getDate("creates")));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setView(rs.getInt("view"));
                pr.setProductStatus(rs.getBoolean("productStatus"));
                pr.setCatalogId(rs.getInt("catalogId"));
                listProduct.add(pr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;
    }

    //8.Tìm kiếm sản phẩm trong khoảng giá a-b
    public static List<Product> searchDataProductFromPriceToPrice(float fromPrice,float toPrice) throws SQLException {
        List<Product> listProduct= null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_fromPrice_ToPrice(?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setFloat(1, fromPrice);
            callSt.setFloat(2, toPrice);

            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product pr = new Product();
                pr.setProductId(rs.getString("productId"));
                pr.setProductName(rs.getString("productName"));
                pr.setPrice(rs.getFloat("price"));
                pr.setCreates(String.valueOf(rs.getDate("creates")));
                pr.setQuantity(rs.getInt("quantity"));
                pr.setView(rs.getInt("view"));
                pr.setProductStatus(rs.getBoolean("productStatus"));
                pr.setCatalogId(rs.getInt("catalogId"));
                listProduct.add(pr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return listProduct;
    }

    // 9. Bán sản phẩm
    public static boolean sellProducts(Product pr) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call sell_products(?,?)}");
            callSt.setString(1, pr.getProductId());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setInt(2, pr.getQuantity());
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
}
