package ra.businnes;

import ra.connect.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportBusinnes {
    //1.Thống kê số danh mục theo trạng thái danh mục
    public static int statisticsCategoriesByCategoryStatus() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        int cntCategories = 0;
        Boolean categoryStatus = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_categories_by_categoryStatus()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                categoryStatus = rs.getBoolean("catalogStatus");
                String valueCategoryStatus = categoryStatus ? "Active" : "InActive";
                cntCategories = rs.getInt("countByCatalogStatus");
                System.out.printf("%-40s%-20d\n", valueCategoryStatus, cntCategories);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntCategories;
    }

    //2. Thống kê số lượng sản phẩm theo trạng thái sản phẩm
    public static int statisticsProductByProductStatus() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        int cntProduct = 0;
        Boolean productStatus = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_product_by_productStatus()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                productStatus = rs.getBoolean("productStatus");
                String valueProductStatus = productStatus ? "Active" : "InActive";
                cntProduct = rs.getInt("countByProductStatus");
                System.out.printf("%-40s%-20d\n", valueProductStatus, cntProduct);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntProduct;
    }

    //3. Thống kê số lượng sản phẩm theo từng danh mục
    public static int statisticsProductByCategory() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        int cntProduct = 0;
        String catalogName = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectDB.openConnection();
            callSt = conn.prepareCall("{call statistics_products_by_category()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                catalogName = rs.getString("catalogName");
                cntProduct = rs.getInt("cntProductByCatalogName");
                System.out.printf("%-40s%-20d\n", catalogName, cntProduct);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn, callSt);
        }
        return cntProduct;
    }
}
