package ra.presentation;

import ra.Choice.ProductChoice;
import ra.businnes.ProductBusiness;
import ra.colors.ColorsMenu;
import ra.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.Choice.ProductChoice.*;
//import static ra.entity.Product.isCheckFindIndex;
import static ra.presentation.MainManagement.sc;

public class ProductManagement {
    public static final String border = "-";
    public static List<Product> listProduct = new ArrayList<>();
    public static final int perPage = 10;
    public static final int indexOfPage = 10;

    public static void displayProductMenu() throws SQLException {
        boolean isExitProductMenu = true;
        do {
            String repeated = new String(new char[67]).replace("\0", border);
            System.out.println(ColorsMenu.GREEN_BOLD_BRIGHT + repeated);
            System.out.println("========================= QUẢN LÝ SẢN PHẨM =========================");
            System.out.println("--              1. Danh sách sản phẩm                             --");
            System.out.println("--              2. Thêm mới sản phẩm                              --");
            System.out.println("--              3. Cập nhật sản phẩm                              --");
            System.out.println("--              4. Tìm kiếm sản phẩm                              --");
            System.out.println("--              5. Cập nhật trạng thái sản phẩm                   --");
            System.out.println("--              6. Quay lại(WAREHOUSE MANAGEMENT)                 --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceProductMenu = validateChoiceProductMenu();
            switch (choiceProductMenu) {
                case 1:
                    ProductManagement.displayDataProduct(Display);
                    break;
                case 2:
                    ProductManagement.displayDataProduct(Create);
                    break;
                case 3:
                    ProductManagement.displayDataProduct(Update);
                    break;
                case 4:
                    ProductManagement.displayDataProduct(Search);
                    break;
                case 5:
                    ProductManagement.displayDataProduct(UpdateProductStatus);
                    break;
                case 6:
                    isExitProductMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-6");
            }
        } while (isExitProductMenu);
    }

    public static int validateChoiceProductMenu() {
        while (true) {
            String inputChoiceProductMenuStr = sc.nextLine().trim();
            if (inputChoiceProductMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong PRODUCT MANAGEMENT không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceProductMenu = Integer.parseInt(inputChoiceProductMenuStr);
                    if (choiceProductMenu > 0) {
                        return choiceProductMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong PRODUCT MANAGEMENT phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong PRODUCT MANAGEMENT phải là số,vui lòng nhập lại");
                }
            }
        }
    }


    public static void displayDataProduct(ProductChoice choiceStatus) throws SQLException {
        int cntPage = 0;
        int choiceNextPage = 0;
        int i = 1;
        // Đếm tất cả số lượng bản ghi trong Product
        int cntAllDataOfProduct = getCntDataProduct();
        // Tổng số trang sẽ có
        int maxPage = (int) Math.ceil((double) cntAllDataOfProduct / perPage);
        switch (choiceStatus) {
            case Display:
                if (cntAllDataOfProduct > 0) {
                    ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                    if (maxPage >= 1) {
                        boolean isExitDisplayDataProduct = true;
                        do {
                            System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                            if (i == 1 && maxPage == 1) {
                                System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                if (choiceNextPage != 0) {
                                    isExitDisplayDataProduct = false;
                                    break;
                                }
                            } else if (i == 1) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi danh sách sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                }
                            } else if (i == (int) getTotalPage()) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi danh sách sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");

                                }
                            } else {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println("2.Quay lại trang thứ " + (i - 1));
                                System.out.println(ColorsMenu.GREEN_BOLD + "3.Thoát khỏi danh sách sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println("Vui lòng nhập lựa chọn của bạn : ");
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 3:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                }
                            }
                        } while (isExitDisplayDataProduct);
                    }
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về sản phẩm trong kho" + ColorsMenu.ANSI_RESET);
                }
                break;
            case DisplayInReceipt:
                if (cntAllDataOfProduct > 0) {
                    ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                    if (maxPage >= 1) {
                        boolean isExitDisplayDataProduct = true;
                        do {
                            System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                           if (i == 1) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn");
                                }
                            } else if (i == (int) getTotalPage()) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn");

                                }
                            } else {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println("2.Quay lại trang thứ " + (i - 1));
                                System.out.println("Vui lòng nhập lựa chọn của bạn : ");
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                }
                            }
                        } while (isExitDisplayDataProduct);
                    }
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về sản phẩm trong kho" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Create:
                if (cntAllDataOfProduct > 0) {
                    boolean isCheckExitCreate = true;
                    do {
                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataProduct = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Thêm mới sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 2:
                                            ProductManagement.creatDataProduct(sc);
//                                            cntPage = 0;
//                                            i =
                                            cntAllDataOfProduct = getCntDataProduct();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfProduct / perPage);
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi thêm mới sản phẩm" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Thêm mới sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 3:
                                            ProductManagement.creatDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfProduct / perPage);
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi thêm mới sản phẩm" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Thêm mới sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 3:
                                            ProductManagement.creatDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfProduct / perPage);
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi thêm mới sản phẩm" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Thêm mới sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 3:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 4:
                                            ProductManagement.creatDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfProduct / perPage);
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataProduct);
                        }
                    } while (isCheckExitCreate);
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về sản phẩm trong kho" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Update:
                if (cntAllDataOfProduct > 0) {
                    boolean isCheckExitUpdate = true;
                    do {
                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataProduct = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Cập nhật sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 2:
                                            ProductManagement.updateDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật sản phẩm" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 3:
                                            ProductManagement.updateDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật sản phẩm" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 3:
                                            ProductManagement.updateDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi cập nhật sản phẩm" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Cập nhật sản phẩm");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 3:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 4:
                                            ProductManagement.updateDataProduct(sc);
                                            cntAllDataOfProduct = getCntDataProduct();
                                            isExitDisplayDataProduct = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataProduct);
                        }

                    } while (isCheckExitUpdate);

                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về sản phẩm trong kho" + ColorsMenu.ANSI_RESET);
                }
                break;
            case UpdateProductStatus:
                if (cntAllDataOfProduct > 0) {
                    boolean isCheckExitUpdateProductStatus = true;
                    do {
                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataProduct = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Cập nhật sản phẩm theo trạng thái");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdateProductStatus = false;
                                            break;
                                        case 2:
                                            ProductManagement.updateDataProductStatus(sc);
                                            isExitDisplayDataProduct = false;
                                            cntAllDataOfProduct = getCntDataProduct();
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật sản phẩm theo trạng thái" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật sản phẩm theo trạng thái");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdateProductStatus = false;
                                            break;
                                        case 3:
                                            ProductManagement.updateDataProductStatus(sc);
                                            isExitDisplayDataProduct = false;
                                            cntAllDataOfProduct = getCntDataProduct();
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật sản phẩm theo trạng thái" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật sản phẩm theo trạng thái");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdateProductStatus = false;
                                            break;
                                        case 3:
                                            ProductManagement.updateDataProductStatus(sc);
                                            isExitDisplayDataProduct = false;
                                            cntAllDataOfProduct = getCntDataProduct();
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi cập nhật sản phẩm theo trạng thái" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Cập nhật sản phẩm theo trạng thái");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                            break;
                                        case 3:
                                            isExitDisplayDataProduct = false;
                                            isCheckExitUpdateProductStatus = false;
                                            break;
                                        case 4:
                                            ProductManagement.updateDataProductStatus(sc);
                                            isExitDisplayDataProduct = false;
                                            cntAllDataOfProduct = getCntDataProduct();
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataProduct);
                        }

                    } while (isCheckExitUpdateProductStatus);

                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về sản phẩm trong kho" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Search:
                if (cntAllDataOfProduct > 0) {
                    ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                    if (maxPage >= 1) {
                        boolean isExitDisplayDataProduct = true;
                        do {
                            System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                            if (i == 1 && maxPage == 1) {
                                System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi tìm kiếm sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println("2.Tìm kiếm sản phẩm theo tên sản phẩm");
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    case 2:
                                        cntPage = 0;
                                        i = 1;
                                        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
                                        String productNameSearch = Product.inputProductNameSearch();
                                        int cntAllDataSearch = ProductBusiness.getAllDataSearchProduct(productNameSearch);
                                        int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                        ProductManagement.paginationDataSearchProduct(perPage, cntPage, i, maxPageSearch, productNameSearch, cntAllDataSearch);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                }
//                                if (choiceNextPage != 0) {

//                                    isExitDisplayDataProduct = false;
//                                    break;
//                                }
                            } else if (i == 1) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tìm kiếm sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println("3.Tìm kiếm sản phẩm theo tên sản phẩm");
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    case 3:
                                        cntPage = 0;
                                        i = 1;
                                        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
                                        String productNameSearch = Product.inputProductNameSearch();
                                        int cntAllDataSearch = ProductBusiness.getAllDataSearchProduct(productNameSearch);
                                        int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                        ProductManagement.paginationDataSearchProduct(perPage, cntPage, i, maxPageSearch, productNameSearch, cntAllDataSearch);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                }
                            } else if (i == (int) getTotalPage()) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tìm kiếm sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println("3.Tìm kiếm sản phẩm theo tên sản phẩm");
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    case 3:
                                        cntPage = 0;
                                        i = 1;
                                        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
                                        String productNameSearch = Product.inputProductNameSearch();
                                        int cntAllDataSearch = ProductBusiness.getAllDataSearchProduct(productNameSearch);
                                        int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                        ProductManagement.paginationDataSearchProduct(perPage, cntPage, i, maxPageSearch, productNameSearch, cntAllDataSearch);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                }
                            } else {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println("2.Quay lại trang thứ " + (i - 1));
                                System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi tìm kiếm sản phẩm" + ColorsMenu.ANSI_RESET);
                                System.out.println("4.Tìm kiếm sản phẩm theo tên sản phẩm");
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 2:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        ProductManagement.paginationDataProduct(perPage, cntPage, i, maxPage, cntAllDataOfProduct);
                                        break;
                                    case 3:
                                        isExitDisplayDataProduct = false;
                                        break;
                                    case 4:
                                        cntPage = 0;
                                        i = 1;
                                        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
                                        String productNameSearch = Product.inputProductNameSearch();
                                        int cntAllDataSearch = ProductBusiness.getAllDataSearchProduct(productNameSearch);
                                        int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                        ProductManagement.paginationDataSearchProduct(perPage, cntPage, i, maxPageSearch, productNameSearch, cntAllDataSearch);
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                }
                            }
                        } while (isExitDisplayDataProduct);
                    }
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về sản phẩm trong kho" + ColorsMenu.ANSI_RESET);
                }
                break;

        }

    }

    public static float getTotalPage() throws SQLException {
        int cntAllDataOfProduct = ProductBusiness.getCountAllDataProduct();
        return (int) Math.ceil((double) cntAllDataOfProduct / perPage);
    }

    public static int getCntDataProduct() throws SQLException {
        return ProductBusiness.getCountAllDataProduct();
    }


    public static void paginationDataProduct(int perPage, int cntPage, int i, float maxPage,
                                             int cntAllDataOfProduct) throws SQLException {
        List<Product> listProduct = null;
        String repeated = new String(new char[162]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-20s | %-20s | %-20s | %-15s | %-20s|\n", "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất", "Ngày tạo", "Lô chứa sản phẩm", "Số lượng", "Trạng thái");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        listProduct = ProductBusiness.getAllDataProduct(perPage, cntPage);
        listProduct.forEach(Product::displayDataProduct);
        System.out.println("* " + repeated + " *");
        System.out.print("  Page : " + i + " / " + maxPage + "\t\t\t\t\t\t\t");
        System.out.print("All data : " + cntAllDataOfProduct + "\n");
        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
    }

    public static void paginationDataSearchProduct(int perPage, int cntPage, int i, float maxPage, String
            productName, int cntAllDataSearchOfProduct) throws SQLException {
        List<Product> listProductSearch = null;
        listProductSearch = ProductBusiness.searchDataProductByProductName(perPage, cntPage, productName);
        if (listProductSearch.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không tìm thấy kết quả" + ColorsMenu.ANSI_RESET);
        } else {
            String repeated = new String(new char[162]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-30s | %-20s | %-20s | %-20s | %-15s | %-20s|\n", "Mã sản phẩm", "Tên sản phẩm", "Nhà sản xuất", "Ngày tạo", "Lô chứa sản phẩm", "Số lượng", "Trạng thái");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listProductSearch.forEach(Product::displayDataProduct);
            System.out.println("* " + repeated + " *");
            System.out.print("  Page : " + i + " / " + maxPage + "\t\t\t\t\t\t\t");
            System.out.print("All data search : " + cntAllDataSearchOfProduct + "\n");
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
        }
    }

    public static void creatDataProduct(Scanner sc) throws SQLException {
        System.out.print("Nhập số lượng sản phẩm mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng sản phẩm mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào sản phẩm thứ " + (i + 1) + ":");
                            Product prNew = new Product();
                            prNew.inputData();
                            boolean result = ProductBusiness.creatDataProduct(prNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công sản phẩm" + ColorsMenu.ANSI_RESET);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công sản phẩm thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng sản phẩm mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng sản phẩm mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }

    public static int validateChoiceDisplayData() {
        while (true) {
            String choiceNextPageStr = sc.nextLine().trim();
            if (choiceNextPageStr.isEmpty()) {
                System.err.println("Lựa chọn muốn xem trang tiếp theo hay không, không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choice = Integer.parseInt(choiceNextPageStr);
                    if (choice > 4) {
                        System.err.println("Lựa chọn muốn xem trang tiếp theo hay không ,phải là những số trong menu,vui lòng nhập lại");
                    } else if (choice > 0) {
                        return choice;
                    } else {
                        System.err.println("Lựa chọn muốn xem trang tiếp theo hay không phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn muốn xem trang tiếp theo hay không, phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void updateDataProduct(Scanner sc) throws SQLException {
        boolean isExitUpdateDataProductMenu = true;
        boolean result;
        System.out.println("Nhập vào mã sản phẩm cần cập nhật :");
        String productIdUpdate = Product.inputProductIdUpdate();
        //Kiểm tra mã sách có tồn tại hay không
        Product pr = ProductBusiness.getProductById(productIdUpdate);
        if (pr != null) {
            do {
                String repeated = new String(new char[69]).replace("\0", border);
                System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
                System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin =========");
                System.out.println("--              1.Tên sản phẩm                                     --");
                System.out.println("--              2.Nhà sản xuất                                     --");
                System.out.println("--              3.Ngày tạo                                         --");
                System.out.println("--              4.Lô chứa sản phẩm                                 --");
                System.out.println("--              5.Thoát khỏi cập nhật sản phẩm                     --");
                System.out.println(repeated + ColorsMenu.ANSI_RESET);
                System.out.println("Lựa chọn của bạn là ");
                int choiceUpdate = validateChoiceUpdateProduct();
                switch (choiceUpdate) {
                    case 1:
                        System.out.print("Bạn muốn thay đổi tên sản phẩm" + " từ " + "[" + ColorsMenu.RED_BOLD + pr.getProduct_Name() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        pr.setProduct_Name(Product.inputProductUpdateName(productIdUpdate));
                        result = ProductBusiness.updateDataProduct(pr);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã sản phẩm :" + productIdUpdate + " vừa được cập nhật thành công tên sản phẩm" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataProductMenu = false;
                        break;
                    case 2:
                        System.out.print("Bạn muốn thay đổi nhà sản xuất" + " từ " + "[" + ColorsMenu.RED_BOLD + pr.getManufacturer() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        pr.setManufacturer(Product.inputManufacturer());
                        result = ProductBusiness.updateDataProduct(pr);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã sản phẩm :" + productIdUpdate + " vừa được cập nhật thành công nhà sản xuất" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataProductMenu = false;
                        break;
                    case 3:
                        System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + pr.getCreated() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        pr.setCreated(Product.inputCreated());
                        result = ProductBusiness.updateDataProduct(pr);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã sản phẩm :" + productIdUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataProductMenu = false;
                        break;
                    case 4:
                        System.out.print("Bạn muốn thay đổi lô chứa sản phẩm" + " từ " + "[" + ColorsMenu.RED_BOLD + pr.getBatch() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        pr.setBatch(Product.inputBatch());
                        result = ProductBusiness.updateDataProduct(pr);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã sản phẩm :" + productIdUpdate + " vừa được cập nhật thành công lô chứa sản phẩm" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataProductMenu = false;
                        break;
                    case 5:
                        isExitUpdateDataProductMenu = false;
                        break;
                    default:
                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
                }
            } while (isExitUpdateDataProductMenu);

        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.out.println("Mã sản phẩm không tồn tại");
        }

    }

    public static void updateDataProductStatus(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật trạng thái :");
        String productIdUpdate = Product.inputProductIdUpdate();
        //Kiểm tra mã sản phẩm có tồn tại hay không
        Product pr = ProductBusiness.getProductById(productIdUpdate);
        if (pr != null) {
            String productStatusValue = pr.getProduct_Status() ? "Hoạt động" : "Không hoạt động";
            System.out.println("Bạn muốn thay đổi trạng thái sản phẩm từ " + "[" + ColorsMenu.RED_BOLD + productStatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
            pr.setProduct_Status(Product.inputProductStatus());
            //Thực hiện cập nhật
            boolean result = ProductBusiness.updateProductStatus(pr);
            if (result) {
                System.out.println(ColorsMenu.GREEN_BOLD + "Trạng thái sản phẩm có mã :" + productIdUpdate + " vừa được cập nhật thành công" + ColorsMenu.ANSI_RESET);
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.out.println("Mã sản phẩm không tồn tại");
        }
    }

    public static int validateChoiceUpdateProduct() {
        while (true) {
            String inputChoiceProductUpdateMenuStr = sc.nextLine().trim();
            if (inputChoiceProductUpdateMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong menu update không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceProductMenu = Integer.parseInt(inputChoiceProductUpdateMenuStr);
                    if (choiceProductMenu > 0) {
                        return choiceProductMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong menu update phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong menu update phải là số,vui lòng nhập lại");
                }
            }
        }
    }


}
