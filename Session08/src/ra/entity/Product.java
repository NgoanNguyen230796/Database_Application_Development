package ra.entity;

import ra.businnes.CategoriesBusinnes;
import ra.businnes.ProductBusinnes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static ra.run.ShopManagement.sc;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String creates;
    private int quantity;
    private int view;
    private int catalogId;
    private Boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String creates, int quantity, int view, int catalogId, Boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.creates = creates;
        this.quantity = quantity;
        this.view = view;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCreates() {
        return creates;
    }

    public void setCreates(String creates) {
        this.creates = creates;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public Boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    public void inputDataProduct(Scanner sc) {
        System.out.println("Nhập vào mã sản phẩm:");
        this.productId = validateProductId();
        System.out.println("Nhập vào tên sản phẩm:");
        this.productName = sc.nextLine();
        System.out.println("Nhập vào giá sản phẩm:");
        this.price = Float.parseFloat(sc.nextLine());
        System.out.println("Nhập vào ngày tạo:");
        this.creates = sc.nextLine();
        System.out.println("Nhập vào số lượng sản phẩm:");
        this.quantity = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào lượt xem sản phẩm:");
        this.view = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào trạng thái:");
        this.productStatus = Boolean.parseBoolean(sc.nextLine());
    }

    public static String validateProductId() {
        while (true) {
            String inputProductId = sc.nextLine().trim();
            if (inputProductId.isEmpty()) {
                System.err.println("Mã sản phẩm không được để trống , vui lòng nhập lại");
            } else {
                try {
                    if (inputProductId.length() > 5) {
                        System.err.println("Mã sản phẩm phải dưới 5 ký tự,vui lòng nhập lại");
                    } else {
                        Product pr = ProductBusinnes.getProductById(inputProductId);
                        if (pr != null) {
                            System.err.println("Mã sản phẩm đã bị trùng, vui lòng nhập lại");
                        }else{
                            return inputProductId;
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }

    }

    public static int isCheckFindIndex(int id) throws SQLException {
        List<Categories> listCategories = CategoriesBusinnes.getAllCategories();
        for (int i = 0; i < listCategories.size(); i++) {
            if (listCategories.get(i).getCatalogId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void displayDataProduct() {
//        List<Categories> listCategories = CategoriesBusinnes.getAllCategories();
        String statusValue = this.productStatus ? "Active" : "InActive";
        //this.productId, this.productName, this.price,this.creates,this.quantity,this.view, statusValue,listCategories.get(isCheckFindIndex(this.catalogId)).getCatalogName()
//        System.out.printf("%-20s%-20s%-20.1f%-20s%-20d%-20d%-20s%-20s\n", this.productId, this.productName, this.price,this.creates,this.quantity,this.view, statusValue,listCategories.get(isCheckFindIndex(this.catalogId)).getCatalogName());
        System.out.printf("%-20s%-20s%-20.1f%-20s%-20d%-20d%-20s", this.productId, this.productName, this.price, this.creates, this.quantity, this.view, statusValue);
    }
}
