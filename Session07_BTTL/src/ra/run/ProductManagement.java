package ra.run;

import ra.business.ProductBusiness;
import ra.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        do {
            System.out.println("********************MENU********************");
            System.out.println("1.Hiển thị thông tin sản phẩm");
            System.out.println("2.Thêm mới sản phẩm");
            System.out.println("3.Cập nhật sản phẩm");
            System.out.println("4.Xóa sản phẩm");
            System.out.println("5.Tìm kiếm sản phẩm theo mã sản phẩm");
            System.out.println("6.Tìm kiếm sản phẩm trong khoản giá");
            System.out.println("7.Thoát");
            System.out.println("Lựa chọn của bạn:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    ProductManagement.displayListProduct();
                    break;
                case 2:
                    ProductManagement.creatDataProduct(sc);
                    break;
                case 3:
                    ProductManagement.updateDataProduct(sc);
                    break;
                case 4:
                    ProductManagement.deleteDataProduct(sc);
                    break;
                case 5:
                    ProductManagement.searchDataProduct(sc);
                    break;
                case 6:
                    break;
                case 7:
                    break;

            }
        } while (true);
    }

    public static void displayListProduct() throws SQLException {
        List<Product> listProduct = ProductBusiness.getAllProduct();
        System.out.println("THÔNG TIN CÁC SẢN PHẨM:");
        listProduct.stream().forEach(pr -> pr.displayData());
    }

    public static void creatDataProduct(Scanner sc) throws SQLException {
        // Nhập dữ liệu cho 1 sản phẩm để thêm mới
        Product prNew = new Product();
        prNew.inputData(sc);
        // Gọi creatProduct của ProductBusiness để thực hiện thêm dữ liệu vào DB
       boolean result=ProductBusiness.creatProduct(prNew);
       if(result){
           System.out.println("Thêm mới thành công");
       }else{
           System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
       }
    }

    public static void updateDataProduct(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật :");
        int productIdUpdate=Integer.parseInt(sc.nextLine());
        //Kiểm tra mã sản phẩm có tồn tại hay không
        Product pr =ProductBusiness.getProductById(productIdUpdate);
        if(pr!=null){
            //Mã sản phẩm tồn tại trong CSDL
            System.out.println("Nhập vào tên sản phẩm cần cập nhật");
            pr.setProductName(sc.nextLine());
            System.out.println("Nhập vào giá sản phẩm cần cập nhật");
            pr.setPrice(Float.parseFloat(sc.nextLine()));
            System.out.println("Nhập vào trạng sản phẩm cần cập nhật");
            pr.setProductStatus(Boolean.parseBoolean(sc.nextLine()));
            //Thực hiện cập nhật
            boolean result=ProductBusiness.updateProduct(pr);
            if(result){
                System.out.println("Mã sản phẩm :"+productIdUpdate+" vừa được cập nhật thành công");
            }else{
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        }else{
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }

    }

    public static void deleteDataProduct(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần xóa :");
        int productIdDelete =Integer.parseInt(sc.nextLine());
        //Kiểm tra productIdDelete có tồn tại trong CSDL hay k
        Product pr =ProductBusiness.getProductById(productIdDelete);
        if(pr!=null){
            boolean result=ProductBusiness.deleteDataProduct(productIdDelete);
            if(result){
                System.out.println("Mã sản phẩm :"+productIdDelete+" vừa được xóa thành công");
            }else{
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        }else{
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void searchDataProduct(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần tìm kiếm :");
        int productIdSearch =Integer.parseInt(sc.nextLine());
        //Kiểm tra productIdDelete có tồn tại trong CSDL hay k
        Product pr =ProductBusiness.getProductById(productIdSearch);
        if(pr!=null){
            List<Product> listProduct = ProductBusiness.searchDataProduct(productIdSearch);
            System.out.println("THÔNG TIN CÁC SẢN PHẨM:");
            listProduct.stream().forEach(product -> product.displayData());

        }else{
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }
}
