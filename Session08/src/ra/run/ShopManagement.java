package ra.run;

import ra.businnes.CategoriesBusinnes;
import ra.businnes.ProductBusinnes;
import ra.businnes.ReportBusinnes;
import ra.entity.Categories;
import ra.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.entity.Product.isCheckFindIndex;

public class ShopManagement {
    public static Scanner sc = new Scanner(System.in);
    public static List<Categories> listCategories = new ArrayList<>();
    public static List<Product> listProduct = new ArrayList<>();

    public static void main(String[] args){
        try{
            do {
                System.out.println("********************SHOP MANAGEMENT********************");
                System.out.println("1. Quản lý danh mục");
                System.out.println("2. Quản lý sản phẩm");
                System.out.println("3. Báo cáo thống kê");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choiceShopManagement = validateChoice();
                switch (choiceShopManagement) {
                    case 1:
                        ShopManagement.categoryMenu();
                        break;
                    case 2:
                        ShopManagement.productMenu();
                        break;
                    case 3:
                        ShopManagement.reportManagement();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");


                }
            } while (true);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static int validateChoice() {
        while (true) {
            String inputChoiceStr = sc.nextLine().trim();
            if (inputChoiceStr.isEmpty()) {
                System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceMenu = Integer.parseInt(inputChoiceStr);
                    if (choiceMenu > 0) {
                        return choiceMenu;
                    } else {
                        System.err.println("Lựa chọn phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void categoryMenu() throws SQLException {
        boolean isExitCatalogMenu = true;
        do {

            System.out.println("========================= CATEGORIES MANAGEMENT ========================");
            System.out.println("1. Hiển thị các danh mục có status = 1");
            System.out.println("2. Hiển thị danh mục sắp xếp theo độ ưu tiên tăng dần");
            System.out.println("3. Thêm mới danh mục");
            System.out.println("4. Cập nhật danh mục");
            System.out.println("5. Xóa danh mục");
            System.out.println("6. Tìm kiếm danh mục theo tên");
            System.out.println("7. Cập nhật trạng thái danh mục");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn là :");
            int choiceCategoryMenu = validateChoiceCategoryMenu();
            switch (choiceCategoryMenu) {
                case 1:
                    ShopManagement.displayListCategories();
                    break;
                case 2:
                    ShopManagement.displayListCategoriesByPriority();
                    break;
                case 3:
                    ShopManagement.creatDataCategories(sc);
                    break;
                case 4:
                    ShopManagement.displayListCategories();
                    ShopManagement.updateDataCategories(sc);
                    break;
                case 5:
                    ShopManagement.displayListCategories();
                    ShopManagement.deleteDataCategories(sc);
                    break;
                case 6:
                    ShopManagement.displayListCategories();
                    ShopManagement.searchDataCategories(sc);
                    break;
                case 7:
                    ShopManagement.displayListCategories();
                    ShopManagement.updateDataCategoriesStatus(sc);
                    break;
                case 8:
                    isExitCatalogMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-8");
            }
        } while (isExitCatalogMenu);
    }

    public static int validateChoiceCategoryMenu() {
        while (true) {
            String inputChoiceCategoryMenuStr = sc.nextLine().trim();
            if (inputChoiceCategoryMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceCategoryMenu = Integer.parseInt(inputChoiceCategoryMenuStr);
                    if (choiceCategoryMenu > 0) {
                        return choiceCategoryMenu;
                    } else {
                        System.err.println("Lựa chọn các mục phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    //1. Hiển thị các danh mục
    public static void displayListCategories() throws SQLException {
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã danh mục", "Tên danh mục", "Độ ưu tiên", "Trạng thái");
        listCategories = CategoriesBusinnes.getAllCategories();
        listCategories.forEach(Categories::displayDataCategories);
    }

    //2. Hiển thị danh mục sắp xếp theo độ ưu tiên tăng dần
    public static void displayListCategoriesByPriority() throws SQLException {
        listCategories = CategoriesBusinnes.getAllCategoriesByPriority();
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã danh mục", "Tên danh mục", "Độ ưu tiên", "Trạng thái");
        listCategories.forEach(Categories::displayDataCategories);
    }

    //3. Thêm mới danh mục
    public static void creatDataCategories(Scanner sc) throws SQLException {
        // Nhập dữ liệu cho 1 danh mục để thêm mới
        Categories caNew = new Categories();
        caNew.inputDataCategories(sc);
        // Gọi creatDataCategories của CategoriesBusinnes để thực hiện thêm dữ liệu vào DB
        boolean result = CategoriesBusinnes.creatDataCategories(caNew);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
        }
    }

    //4. Cập nhật danh mục
    public static void updateDataCategories(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã danh mục cần cập nhật :");
        int categoriesIdUpdate = Integer.parseInt(sc.nextLine());
        //Kiểm tra mã danh mục có tồn tại hay không
        Categories ca = CategoriesBusinnes.getCategoriesById(categoriesIdUpdate);
        if (ca != null) {
            //Mã danh mục tồn tại trong CSDL
            System.out.println("Nhập vào tên danh mục cần cập nhật");
            ca.setCatalogName(sc.nextLine());
            System.out.println("Nhập vào độ ưu tiên cần cập nhật");
            ca.setPriority(Integer.parseInt(sc.nextLine()));
            //Thực hiện cập nhật
            boolean result = CategoriesBusinnes.updateCategories(ca);
            if (result) {
                System.out.println("Mã danh mục :" + categoriesIdUpdate + " vừa được cập nhật thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã danh mục không tồn tại");
        }

    }

    //5.Xóa dữ liệu
    public static void deleteDataCategories(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã danh mục cần xóa :");
        int categoriesIdDelete = Integer.parseInt(sc.nextLine());
        //Kiểm tra categoriesIdDelete có tồn tại trong CSDL hay k
        Categories ca = CategoriesBusinnes.getCategoriesById(categoriesIdDelete);
        listProduct = ProductBusinnes.getProduct();
        if (ca != null) {
            boolean isCheckProduct = false;
            for (Product pr : listProduct) {
                //Kiểm tra xem trong danh sách sản phẩm có chứa mã danh mục thuộc về hay không
                if (pr.getCatalogId() == ca.getCatalogId()) {
                    isCheckProduct = true;
                    break;
                }
            }
            if (!isCheckProduct) {
                boolean result = CategoriesBusinnes.deleteDataCategories(categoriesIdDelete);
                if (result) {
                    System.out.println("Mã danh mục :" + categoriesIdDelete + " vừa được xóa thành công");
                } else {
                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                }
            } else {
                System.out.println("Danh mục chứa sản phẩm ,không thể xóa được");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    //6.Tìm kiếm thông tin sách theo mã sách
    public static void searchDataCategories(Scanner sc) throws SQLException {
        System.out.println("Nhập vào tên sách cần tìm kiếm :");
        String bookCategoriesSearch = sc.nextLine();
        listCategories = CategoriesBusinnes.searchDataCategories(bookCategoriesSearch);
        if (!listCategories.isEmpty()) {
            System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã danh mục", "Tên danh mục", "Độ ưu tiên", "Trạng thái");
            listCategories.forEach(Categories::displayDataCategories);
        } else {
            System.out.println("Không tìm thấy kết quả");
        }
    }

    //7. Cập nhật trạng thái danh mục
    public static void updateDataCategoriesStatus(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã danh mục cần cập nhật :");
        int categoriesIdUpdate = Integer.parseInt(sc.nextLine());
        //Kiểm tra mã danh mục có tồn tại hay không
        Categories ca = CategoriesBusinnes.getCategoriesById(categoriesIdUpdate);
        if (ca != null) {
            //Mã danh mục tồn tại trong CSDL
            System.out.println("Nhập vào trạng thái cần cập nhật");
            ca.setCatalogStatus(Boolean.parseBoolean(sc.nextLine()));
            //Thực hiện cập nhật
            boolean result = CategoriesBusinnes.updateCategoriesStatus(ca);
            if (result) {
                System.out.println("Mã danh mục :" + categoriesIdUpdate + " vừa được cập nhật trạng thái thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã danh mục không tồn tại");
        }
    }


    public static void productMenu() throws SQLException {
        boolean isExitProductMenu = true;
        do {
            System.out.println("========================= PRODUCT MANAGEMENT ========================");
            System.out.println("1. Hiển thị sản phẩm có status = 1");
            System.out.println("2. Hiển thị sản phẩm theo giá giảm dần");
            System.out.println("3. Thêm mới sản phẩm");
            System.out.println("4. Cập nhật sản phẩm");
            System.out.println("5. Cập nhật trạng thái sản phẩm");
            System.out.println("6. Xóa sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("8. Tìm kiếm sản phẩm trong khoảng giá a-b");
            System.out.println("9. Bán sản phẩm");
            System.out.println("10.Thoát");
            System.out.print("Lựa chọn của bạn là :");
            int choiceProductMenu = validateChoiceProductMenu();
            switch (choiceProductMenu) {
                case 1:
                    ShopManagement.displayListProduct();
                    break;
                case 2:
                    ShopManagement.displayListProductByPrice();
                    break;
                case 3:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.creatDataProduct(sc);
                    break;
                case 4:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.updateDataProduct(sc);
                    break;
                case 5:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.updateDataProductStatus(sc);
                    break;
                case 6:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.deleteDataProduct(sc);
                    break;
                case 7:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.searchDataProduct(sc);
                    break;
                case 8:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.searchDataProductFromPriceToPrice(sc);

                    break;
                case 9:
                    ShopManagement.displayListDataAllProduct();
                    ShopManagement.sellProductByProductIdAndQuantity();
                    break;
                case 10:
                    isExitProductMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-10");
            }
        } while (isExitProductMenu);
    }

    public static int validateChoiceProductMenu() {
        while (true) {
            String inputChoiceProductMenuStr = sc.nextLine().trim();
            if (inputChoiceProductMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceProductMenu = Integer.parseInt(inputChoiceProductMenuStr);
                    if (choiceProductMenu > 0) {
                        return choiceProductMenu;
                    } else {
                        System.err.println("Lựa chọn các mục phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục phải là số,vui lòng nhập lại");
                }
            }
        }
    }


    //1. Hiển thị các sản phẩm có trạng thái là 1
    public static void displayListProduct() throws SQLException {
        listProduct = ProductBusinnes.getAllProduct();
        listCategories = CategoriesBusinnes.getAllCategories();
        System.out.println("Hiển thị sản phẩm ");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Ngày tạo", "Số lượng", "Lượt xem", "Trạng thái");
        System.out.printf("%-20s\n", "Mã sản phẩm thuộc về");
        for (Product pr : listProduct) {
            pr.displayDataProduct();
            System.out.printf("%-20s\n", listCategories.get(isCheckFindIndex(pr.getCatalogId())).getCatalogName());
        }

    }


    //1. Hiển thị các sản phẩm
    public static void displayListDataAllProduct() throws SQLException {
        listProduct = ProductBusinnes.getProduct();
        listCategories = CategoriesBusinnes.getAllCategories();
        System.out.println("Hiển thị sản phẩm ");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Ngày tạo", "Số lượng", "Lượt xem", "Trạng thái");
        System.out.printf("%-20s\n", "Mã sản phẩm thuộc về");
        for (Product pr : listProduct) {
            pr.displayDataProduct();
            System.out.printf("%-20s\n", listCategories.get(isCheckFindIndex(pr.getCatalogId())).getCatalogName());
        }

    }

    //2.Hiển thị sản phẩm theo giá giảm dần
    public static void displayListProductByPrice() throws SQLException {
        listProduct = ProductBusinnes.getAllProductByPrice();
        listCategories = CategoriesBusinnes.getAllCategories();
        System.out.println("Hiển thị sản phẩm theo giá giảm dần");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Ngày tạo", "Số lượng", "Lượt xem", "Trạng thái");
        System.out.printf("%-20s\n", "Mã sản phẩm thuộc về");
        for (Product pr : listProduct) {
            pr.displayDataProduct();
            System.out.printf("%-20s\n", listCategories.get(isCheckFindIndex(pr.getCatalogId())).getCatalogName());
        }
    }

    //3. Thêm mới sản phẩm
    public static void creatDataProduct(Scanner sc) throws SQLException {
        listCategories = CategoriesBusinnes.getAllCategories();
        // Nhập dữ liệu cho 1 sản phẩm để thêm mới
        Product prNew = new Product();
        prNew.inputDataProduct(sc);
        System.out.println("Menu quản lý danh mục ");
        for (int j = 0; j < listCategories.size(); j++) {
            System.out.println((j + 1) + " . " + listCategories.get(j).getCatalogName());
        }
        System.out.print("Chọn danh mục mà bạn muốn thêm sản phẩm vào :");
        int choiceNumber = Integer.parseInt(sc.nextLine());
        prNew.setCatalogId(listCategories.get(choiceNumber - 1).getCatalogId());
        // Gọi creatDataProduct của ProductBusinnes để thực hiện thêm dữ liệu vào DB
        boolean result = ProductBusinnes.creatDataProduct(prNew);

        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
        }
    }

    public static void updateDataProduct(Scanner sc) throws SQLException {
        listCategories = CategoriesBusinnes.getAllCategories();
        System.out.println("Nhập vào mã sản phẩm cần cập nhật :");
        String productIdUpdate = sc.nextLine();
        //Kiểm tra mã sách có tồn tại hay không
        Product pr = ProductBusinnes.getProductById(productIdUpdate);
        if (pr != null) {
            //Mã sách tồn tại trong CSDL
            System.out.println("Nhập vào tên sản phẩm cần cập nhật");
            pr.setProductName(sc.nextLine());
            System.out.println("Nhập vào giá sản phẩm cần cập nhật");
            pr.setPrice(Float.parseFloat(sc.nextLine()));
            System.out.println("Nhập vào ngày tạo cần cập nhật");
            pr.setCreates(sc.nextLine());
            System.out.println("Nhập vào số lượng sản phẩm cần cập nhật");
            pr.setQuantity(Integer.parseInt(sc.nextLine()));
            System.out.println("Nhập vào số lượt xem sản phẩm cần cập nhật");
            pr.setView(Integer.parseInt(sc.nextLine()));
            System.out.println("Menu quản lý danh mục ");
            for (int j = 0; j < listCategories.size(); j++) {
                System.out.println((j + 1) + " . " + listCategories.get(j).getCatalogName());
            }
            System.out.print("Chọn danh mục mà bạn muốn cập nhật sản phẩm vào :");
            int choiceNumber = Integer.parseInt(sc.nextLine());
            pr.setCatalogId(listCategories.get(choiceNumber - 1).getCatalogId());

            //Thực hiện cập nhật
            boolean result = ProductBusinnes.updateDataProduct(pr);
            if (result) {
                System.out.println("Mã sản phẩm :" + productIdUpdate + " vừa được cập nhật thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }

    }

    //7. Cập nhật trạng thái danh mục
    public static void updateDataProductStatus(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật :");
        String ProductIdUpdate = sc.nextLine();
        //Kiểm tra mã sản phẩm có tồn tại hay không
        Product pr = ProductBusinnes.getProductById(ProductIdUpdate);
        if (pr != null) {
            //Mã danh mục tồn tại trong CSDL
            System.out.println("Nhập vào trạng thái cần cập nhật");
            pr.setProductStatus(Boolean.parseBoolean(sc.nextLine()));
            //Thực hiện cập nhật
            boolean result = ProductBusinnes.updateProductStatus(pr);
            if (result) {
                System.out.println("Trạng thái sản phẩm có mã :" + ProductIdUpdate + " vừa được cập nhật thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }


    //5.Xóa dữ liệu
    public static void deleteDataProduct(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần xóa :");
        String productIdDelete = sc.nextLine();
        //Kiểm tra productIdDelete có tồn tại trong CSDL hay k
        Product pr = ProductBusinnes.getProductById(productIdDelete);
        if (pr != null) {
            boolean result = ProductBusinnes.deleteDataProduct(productIdDelete);
            if (result) {
                System.out.println("Mã sản phẩm :" + productIdDelete + " vừa được xóa thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    //
    public static void searchDataProduct(Scanner sc) throws SQLException {
        listCategories = CategoriesBusinnes.getAllCategories();
        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
        String productNameSearch = sc.nextLine();
        listProduct = ProductBusinnes.searchDataProductByProductName(productNameSearch);
        if (!listProduct.isEmpty()) {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Ngày tạo", "Số lượng", "Lượt xem", "Trạng thái");
            System.out.printf("%-20s\n", "Mã sản phẩm thuộc về");
            for (Product pr : listProduct) {
                pr.displayDataProduct();
                System.out.printf("%-20s\n", listCategories.get(isCheckFindIndex(pr.getCatalogId())).getCatalogName());
            }
        } else {
            System.out.println("Không tìm thấy kết quả");
        }

    }

    public static void searchDataProductFromPriceToPrice(Scanner sc) throws SQLException {
        listCategories = CategoriesBusinnes.getAllCategories();
        System.out.println("Nhập vào giá sản phẩm từ:");
        float fromPrice = Float.parseFloat(sc.nextLine());
        System.out.println("Nhập vào giá sản phẩm đến:");
        float toPrice = Float.parseFloat(sc.nextLine());
        listProduct = ProductBusinnes.searchDataProductFromPriceToPrice(fromPrice, toPrice);
        if (!listProduct.isEmpty()) {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Ngày tạo", "Số lượng", "Lượt xem", "Trạng thái");
            System.out.printf("%-20s\n", "Mã sản phẩm thuộc về");
            for (Product pr : listProduct) {
                pr.displayDataProduct();
                System.out.printf("%-20s\n", listCategories.get(isCheckFindIndex(pr.getCatalogId())).getCatalogName());
            }
        } else {
            System.out.println("Không tìm thấy kết quả sản phẩm có giá từ " + fromPrice + " đến " + toPrice);
        }

    }

    public static void sellProductByProductIdAndQuantity() throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần bán :");
        String productIdNeedSell = validateProductIdNeedSell();
        Product pr = ProductBusinnes.getProductById(productIdNeedSell);
        if (pr != null) {
            System.out.println("Nhập vào số lượng sản phẩm cần bán :");
            int productQuantityNeedSell = validateQuantityNeedSell(productIdNeedSell);
            pr.setQuantity(productQuantityNeedSell);
            boolean result = ProductBusinnes.sellProducts(pr);
            if (result) {
                System.out.println("Mã sản phẩm :" + productIdNeedSell + " vừa được bán với số lượng " + productQuantityNeedSell);
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sản phẩm cần bán không tồn tại");
        }

    }

    public static String validateProductIdNeedSell() {
        while (true) {
            String inputProductIdStr = sc.nextLine().trim();
            if (inputProductIdStr.isEmpty()) {
                System.err.println("Mã sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    return inputProductIdStr;
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static int validateQuantityNeedSell(String productIdNeedSell) throws SQLException {
        while (true) {
            Product pr = ProductBusinnes.getProductById(productIdNeedSell);
            String inputQuantityStr = sc.nextLine().trim();
            if (inputQuantityStr.isEmpty()) {
                System.err.println("Số lượng cần bán không được để trống, vui lòng nhập lại");
            } else {
                try {
                    int quantity = Integer.parseInt(inputQuantityStr);
                    if (quantity > 0) {
                        if (pr.getQuantity() < quantity) {
                            System.err.println("Số lượng cần bán phải nhỏ hơn số lượng trong kho ( " + pr.getQuantity() + " ), vui lòng nhập lại");
                        } else {
                            return quantity;
                        }
                    } else {
                        System.err.println("Số lượng cần bán phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng cần bán phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }


    public static void reportManagement() throws SQLException {
        boolean isExitReportManagement = true;
        do {

            System.out.println("========================= REPORT MANAGEMENT ========================");
            System.out.println("1. Thống kê số danh mục theo trạng thái danh mục");
            System.out.println("2. Thống kê số lượng sản phẩm theo trạng thái sản phẩm");
            System.out.println("3. Thống kê số lượng sản phẩm theo từng danh mục");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn là :");
            int choiceExitReportManagement = validateChoiceExitReportManagement();
            switch (choiceExitReportManagement) {
                case 1:
                    ShopManagement.getCntCategoriesByCategoryStatus();
                    break;
                case 2:
                    ShopManagement.getCntProductByProductStatus();

                    break;
                case 3:
                    ShopManagement.getCntProductByCatalogName();
                    break;
                case 4:
                    isExitReportManagement = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");
            }
        } while (isExitReportManagement);
    }

    //Validate choice ReportManagement
    public static int validateChoiceExitReportManagement() {
        while (true) {
            String inputChoiceExitReportManagementStr = sc.nextLine().trim();
            if (inputChoiceExitReportManagementStr.isEmpty()) {
                System.err.println("Lựa chọn các mục không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceReportManagement = Integer.parseInt(inputChoiceExitReportManagementStr);
                    if (choiceReportManagement > 0) {
                        return choiceReportManagement;
                    } else {
                        System.err.println("Lựa chọn các mục phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    //1. Thống kê số danh mục theo trạng thái danh mục
    public static void getCntCategoriesByCategoryStatus() throws SQLException {
        System.out.println("Thống kê số danh mục theo trạng thái danh mục là :");
        System.out.printf("%-40s%-20s\n", "Trạng thái danh mục", "Số lượng danh mục");
        ReportBusinnes.statisticsCategoriesByCategoryStatus();
    }

    //2. Thống kê số lượng sản phẩm theo trạng thái sản phẩm
    public static void getCntProductByProductStatus() throws SQLException {
        System.out.println("Thống kê số lượng sản phẩm theo trạng thái sản phẩm là :");
        System.out.printf("%-40s%-20s\n", "Trạng thái sản phẩm", "Số lượng sản phẩm");
        ReportBusinnes.statisticsProductByProductStatus();
    }

    //3. Thống kê số lượng sản phẩm theo từng danh mục
    public static void getCntProductByCatalogName() throws SQLException {
        System.out.println("3. Thống kê số lượng sản phẩm theo từng danh mục là :");
        System.out.printf("%-40s%-20s\n", "Danh mục", "Số lượng sản phẩm");
        ReportBusinnes.statisticsProductByCategory();
    }

}
