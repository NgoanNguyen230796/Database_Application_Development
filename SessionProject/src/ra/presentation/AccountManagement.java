package ra.presentation;

import ra.Choice.AccountChoice;
import ra.businnes.AccountBusiness;
import ra.businnes.AccountBusiness;
import ra.businnes.EmployeeBusiness;
import ra.colors.ColorsMenu;
import ra.entity.*;
import ra.entity.Account;
import ra.entity.Account;
import ra.entity.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.Choice.AccountChoice.*;
import static ra.presentation.MainManagement.sc;

public class AccountManagement {
    public static final String border = "-";
    public static final int perPage = 10;
    public static final int indexOfPage = 10;
    public static List<Account> listAccount = new ArrayList<>();

    static void displayAccountMenu(String employeeIdOfUserName, String userName) throws SQLException {
        boolean isExitAccountMenu = true;
        do {
            String repeated = new String(new char[67]).replace("\0", border);
            System.out.println(ColorsMenu.GREEN_BOLD_BRIGHT + repeated);
            System.out.println("========================= QUẢN LÝ TÀI KHOẢN =========================");
            System.out.println("--              1. Danh sách tài khoản                             --");
            System.out.println("--              2. Tạo tài khoản mới                               --");
            System.out.println("--              3. Cập nhật trạng thái tài khoản                   --");
            System.out.println("--              4. Tìm kiếm tài khoản                              --");
            System.out.println("--              5. Quay lại(WAREHOUSE MANAGEMENT)                  --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceAccountMenu = validateChoiceAccountMenu();
            switch (choiceAccountMenu) {
                case 1:
                    AccountManagement.displayDataAccount(Display, employeeIdOfUserName, userName);
                    break;
                case 2:
                    AccountManagement.displayDataAccount(Create, employeeIdOfUserName, userName);
                    break;
                case 3:
                    AccountManagement.displayDataAccount(UpdateAccountStatus, employeeIdOfUserName, userName);
                    break;
                case 4:
                    AccountManagement.displayDataAccount(Search, employeeIdOfUserName, userName);
                    break;
                case 5:
                    isExitAccountMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
            }
        } while (isExitAccountMenu);
    }

    public static int validateChoiceAccountMenu() {
        while (true) {
            String inputChoiceAccountMenuStr = sc.nextLine().trim();
            if (inputChoiceAccountMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong ACCOUNT MANAGEMENT không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceAccountMenu = Integer.parseInt(inputChoiceAccountMenuStr);
                    if (choiceAccountMenu > 0) {
                        return choiceAccountMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong ACCOUNT MANAGEMENT phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong ACCOUNT MANAGEMENT phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void displayDataAccount(AccountChoice choiceStatus, String employeeIdOfUserName, String userName) throws SQLException {
        int cntPage = 0;
        int choiceNextPage = 0;
        int i = 1;
        // Đếm tất cả số lượng bản ghi trong Account
        int cntAllDataOfAccount = getCntDataAccount();
        // Tổng số trang sẽ có
        int maxPage = (int) Math.ceil((double) cntAllDataOfAccount / perPage);
        switch (choiceStatus) {
            case Display:
                if (cntAllDataOfAccount > 0) {
                    AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                    if (maxPage >= 1) {
                        boolean isExitDisplayDataAccount = true;
                        do {
                            System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                            if (i == 1 && maxPage == 1) {
                                System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách tài khoản" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                if (choiceNextPage != 0) {
                                    isExitDisplayDataAccount = false;
                                    break;
                                }
                            } else if (i == 1) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi danh sách tài khoản" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                        break;
                                    case 2:
                                        isExitDisplayDataAccount = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                }
                            } else if (i == (int) getTotalPage()) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi danh sách tài khoản" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                        break;
                                    case 2:
                                        isExitDisplayDataAccount = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");

                                }
                            } else {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println("2.Quay lại trang thứ " + (i - 1));
                                System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi danh sách tài khoản" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                        break;
                                    case 2:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                        break;
                                    case 3:
                                        isExitDisplayDataAccount = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                }
                            }
                        } while (isExitDisplayDataAccount);
                    }
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về tài khoản trong bảng Account" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Create:
                if (cntAllDataOfAccount > 0) {
                    boolean isCheckExitCreate = true;
                    do {
                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataAccount = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Tạo tài khoản mới");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 2:
                                            List<Employee> listEmployee = EmployeeBusiness.getShowAllDataEmployeeNotInAccount();
                                            if (listEmployee.isEmpty()) {
                                                System.out.println(ColorsMenu.RED_BOLD + "Tất cả nhân viên đã có tài khoản rồi,k thể tạo mới tài khoản được nữa " + ColorsMenu.ANSI_RESET);
                                                isExitDisplayDataAccount = false;
                                                isCheckExitCreate = false;
                                            } else {
                                                AccountManagement.creatDataAccount(sc);
                                                cntAllDataOfAccount = getCntDataAccount();
                                                maxPage = (int) Math.ceil((double) cntAllDataOfAccount / perPage);
                                                isExitDisplayDataAccount = false;
                                            }
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tạo tài khoản mới" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Tạo tài khoản mới");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 3:
                                            List<Employee> listEmployee = EmployeeBusiness.getShowAllDataEmployeeNotInAccount();
                                            if (listEmployee.isEmpty()) {
                                                System.out.println(ColorsMenu.RED_BOLD + "Tất cả nhân viên đã có tài khoản rồi,k thể tạo mới tài khoản được nữa " + ColorsMenu.ANSI_RESET);
                                                isExitDisplayDataAccount = false;
                                                isCheckExitCreate = false;
                                            } else {
                                                AccountManagement.creatDataAccount(sc);
                                                cntAllDataOfAccount = getCntDataAccount();
                                                maxPage = (int) Math.ceil((double) cntAllDataOfAccount / perPage);
                                                isExitDisplayDataAccount = false;
                                            }
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tạo tài khoản mới" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Tạo tài khoản mới");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 3:
                                            List<Employee> listEmployee = EmployeeBusiness.getShowAllDataEmployeeNotInAccount();
                                            if (listEmployee.isEmpty()) {
                                                System.out.println(ColorsMenu.RED_BOLD + "Tất cả nhân viên đã có tài khoản rồi,k thể tạo mới tài khoản được nữa " + ColorsMenu.ANSI_RESET);
                                                isExitDisplayDataAccount = false;
                                                isCheckExitCreate = false;
                                            } else {
                                                AccountManagement.creatDataAccount(sc);
                                                cntAllDataOfAccount = getCntDataAccount();
                                                maxPage = (int) Math.ceil((double) cntAllDataOfAccount / perPage);
                                                isExitDisplayDataAccount = false;
                                            }
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi tạo tài khoản mới" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Tạo tài khoản mới");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 3:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 4:
                                            List<Employee> listEmployee = EmployeeBusiness.getShowAllDataEmployeeNotInAccount();
                                            if (listEmployee.isEmpty()) {
                                                System.out.println(ColorsMenu.RED_BOLD + "Tất cả nhân viên đã có tài khoản rồi,k thể tạo mới tài khoản được nữa " + ColorsMenu.ANSI_RESET);
                                                isExitDisplayDataAccount = false;
                                                isCheckExitCreate = false;
                                            } else {
                                                AccountManagement.creatDataAccount(sc);
                                                cntAllDataOfAccount = getCntDataAccount();
                                                maxPage = (int) Math.ceil((double) cntAllDataOfAccount / perPage);
                                                isExitDisplayDataAccount = false;
                                            }
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataAccount);
                        }
                    } while (isCheckExitCreate);
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về tài khoản trong bảng Account" + ColorsMenu.ANSI_RESET);
                }
                break;
            case UpdateAccountStatus:
                if (cntAllDataOfAccount > 0) {
                    boolean isCheckExitUpdateStatus = true;
                    do {
                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataAccount = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Cập nhật trạng thái tài khoản");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 2:
                                            AccountManagement.updateDataAccountStatus(sc, employeeIdOfUserName, userName);
                                            cntAllDataOfAccount = getCntDataAccount();
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật trạng thái tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật trạng thái tài khoản");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 3:
                                            AccountManagement.updateDataAccountStatus(sc, employeeIdOfUserName, userName);
                                            cntAllDataOfAccount = getCntDataAccount();
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật trạng thái tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật trạng thái tài khoản");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 3:
                                            AccountManagement.updateDataAccountStatus(sc, employeeIdOfUserName, userName);
                                            cntAllDataOfAccount = getCntDataAccount();
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật trạng thái tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật trạng thái tài khoản");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 3:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 4:
                                            AccountManagement.updateDataAccountStatus(sc, employeeIdOfUserName, userName);
                                            cntAllDataOfAccount = getCntDataAccount();
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataAccount);
                        }
                    } while (isCheckExitUpdateStatus);
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về tài khoản trong bảng Account" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Search:
                if (cntAllDataOfAccount > 0) {
                    boolean isCheckExitSearch = true;
                    do {
                        AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataAccount = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi tìm kiếm tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Tìm kiếm tài khoản cho phép tìm theo username hoặc tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitSearch = false;
                                            break;
                                        case 2:
                                            AccountManagement.searchData(employeeIdOfUserName, userName);
                                            cntPage = 0;
                                            i = 1;
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tìm kiếm tài khoản" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Tìm kiếm tài khoản cho phép tìm theo username hoặc tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitSearch = false;
                                            break;
                                        case 3:
                                            AccountManagement.searchData(employeeIdOfUserName, userName);
                                            cntPage = 0;
                                            i = 1;
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println("1.Quay lại trang thứ " + (i - 1));
                                    System.out.println("2.Thoát khỏi tìm kiếm tài khoản");
                                    System.out.println("3.Tìm kiếm tài khoản cho phép tìm theo username hoặc tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitSearch = false;
                                            break;
                                        case 3:
                                            AccountManagement.searchData(employeeIdOfUserName, userName);
                                            cntPage = 0;
                                            i = 1;
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println("1.Xem trang tiếp theo >>> ");
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println("3.Thoát khỏi tìm kiếm tài khoản");
                                    System.out.println("4.Tìm kiếm tài khoản cho phép tìm theo username hoặc tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            AccountManagement.paginationDataAccount(perPage, cntPage, i, maxPage, cntAllDataOfAccount);
                                            break;
                                        case 3:
                                            isExitDisplayDataAccount = false;
                                            isCheckExitSearch = false;
                                            break;
                                        case 4:
                                            AccountManagement.searchData(employeeIdOfUserName, userName);
                                            cntPage = 0;
                                            i = 1;
                                            isExitDisplayDataAccount = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataAccount);
                        }
                    } while (isCheckExitSearch);

                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về tài khoản trong bảng Account" + ColorsMenu.ANSI_RESET);
                }
                break;

        }
    }

    public static int getCntDataAccount() throws SQLException {
        return AccountBusiness.getCountAllDataAccount();
    }

    public static void paginationDataAccount(int perPage, int cntPage, int i, float maxPage,
                                             int cntAllDataOfAccount) throws SQLException {
        List<Account> listAccount = null;
        String repeated = new String(new char[145]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s |\n", "Mã tài khoản", "Tên tài khoản", "Mật khẩu", "Quyền tài khoản", "Mã nhân viên", "Trạng thái");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        listAccount = AccountBusiness.getAllDataAccount(perPage, cntPage);
        listAccount.forEach(Account::displayDataAccount);
        System.out.println("* " + repeated + " *");
        System.out.print("  Page : " + i + " / " + maxPage + "\t\t\t\t\t\t\t");
        System.out.print("All data : " + cntAllDataOfAccount + "\n");
        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
    }

    public static int validateChoiceDisplayData() {
        while (true) {
            String choiceNextPageStr = sc.nextLine().trim();
            if (choiceNextPageStr.isEmpty()) {
                System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choice = Integer.parseInt(choiceNextPageStr);
                    if (choice > 4) {
                        System.err.println("Lựa chọn phải là những số trong menu,vui lòng nhập lại");
                    } else if (choice > 0) {
                        return choice;
                    } else {
                        System.err.println("Lựa chọn phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static float getTotalPage() throws SQLException {
        int cntAllDataOfAccount = AccountBusiness.getCountAllDataAccount();
        return (int) Math.ceil((double) cntAllDataOfAccount / perPage);
    }

    public static void creatDataAccount(Scanner sc) throws SQLException {
        List<Employee> listEmployee = EmployeeBusiness.getShowAllDataEmployeeNotInAccount();
        System.out.print("Nhập số tài khoản mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng tài khoản mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > listEmployee.size()) {
                        System.out.println(ColorsMenu.RED_BOLD + "Xin lỗi số lượng nhân viên chưa có tài khoản chỉ có " + listEmployee.size() + " vui lòng nhập lại số lượng tài khoản mà bạn muốn thêm" + ColorsMenu.ANSI_RESET);
                    } else if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào tài khoản thứ " + (i + 1) + ":");
                            Account accNew = new Account();
                            accNew.inputDataAccount();
                            boolean result = AccountBusiness.creatDataAccount(accNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công tài khoản" + ColorsMenu.ANSI_RESET);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công tài khoản thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng tài khoản mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng tài khoản mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }

    public static void updateDataAccountStatus(Scanner sc, String employeeId, String userName) throws SQLException {
        System.out.println("Nhập vào tên tài khoản cần cập nhật trạng thái:");
        String userNameUpdate = Account.inputUserNameUpdate();
        //Kiểm tra mã nhân viên có tồn tại hay không
        Account acc = AccountBusiness.getAccountByUserName(userNameUpdate);
        if (acc != null) {
            if (employeeId.equalsIgnoreCase(acc.getEmp_Id())) {
                System.out.println(ColorsMenu.RED_BOLD+"Rất tiếc bạn đang đăng nhập tài khoản có UserName là " + userName + " với mã nhân viên là :" + employeeId + " vì vậy không thể chuyển đổi trạng thái của tài khoản sang Block được"+ColorsMenu.ANSI_RESET);
            } else {
                String accStatusValue = acc.getAcc_Status() ? "Active" : "Block";
                System.out.println("Bạn muốn thay đổi trạng thái tài khoản từ " + "[" + ColorsMenu.RED_BOLD + accStatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                acc.setAcc_Status(Account.inputAccStatusUpdate());
                //Thực hiện cập nhật
                boolean result = AccountBusiness.updateDataAccountStatus(acc);
                if (result) {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Trạng thái tài khoản có tên tài khoản :" + userNameUpdate + " vừa được cập nhật thành công" + ColorsMenu.ANSI_RESET);
                } else {
                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                }
            }

        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD+"Tên tài khoản không tồn tại"+ColorsMenu.ANSI_RESET);
        }
    }


    public static void searchData(String employeeIdOfUserName, String userName) throws SQLException {
        System.out.println("Nhập vào username hoặc tên nhân viên cần tìm kiếm tài khoản:");
        String inputSearch = Account.inputSearch();
        List<Account> listAccountSearch = null;
        listAccountSearch = AccountBusiness.searchDataAccountByAccountUserName(inputSearch);
        if (!listAccountSearch.isEmpty()) {
            String repeated = new String(new char[168]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-20s |\n", "Mã tài khoản", "Tên tài khoản", "Mật khẩu", "Quyền tài khoản", "Mã nhân viên", "Tên nhân viên", "Trạng thái");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listAccountSearch.forEach(Account::displayDataAccount);
            System.out.println("* " + repeated + " *");
            System.out.println(ColorsMenu.GREEN_BOLD + "Bạn có muốn cập nhật trạng thái tài khoản hay không ?" + ColorsMenu.ANSI_RESET);
            System.out.println(ColorsMenu.PURPLE_BOLD+"--                           1.Có                               --"+ColorsMenu.ANSI_RESET);
            System.out.println(ColorsMenu.RED_BOLD+"--                           2.Không                            --"+ColorsMenu.ANSI_RESET);
            System.out.print("Nhập vào lựa chọn của bạn = ");
            boolean isCheckChoice=true;
            while (isCheckChoice){
                int choiceNumberAccountStatusUpdate = AccountManagement.validateChoiceDisplayData();
                switch (choiceNumberAccountStatusUpdate){
                    case 1:
                        if (employeeIdOfUserName.equalsIgnoreCase(listAccountSearch.get(0).getEmp_Id())) {
                            System.out.println(ColorsMenu.RED_BOLD+"Rất tiếc bạn đang đăng nhập tài khoản có UserName là " + userName + " với mã nhân viên là :" + employeeIdOfUserName + " vì vậy không thể chuyển đổi trạng thái của tài khoản sang Block được"+ColorsMenu.ANSI_RESET);
                        } else {
                            Account acc = AccountBusiness.getAccountByUserName(inputSearch);
                            String accStatusValue = acc.getAcc_Status() ? "Active" : "Block";
                            System.out.println("Bạn muốn thay đổi trạng thái tài khoản từ " + "[" + ColorsMenu.RED_BOLD + accStatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                            acc.setAcc_Status(Account.inputAccStatusUpdate());
                            //Thực hiện cập nhật
                            boolean result = AccountBusiness.updateDataAccountStatus(acc);
                            if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Trạng thái tài khoản có tên tài khoản :" + inputSearch + " vừa được cập nhật thành công" + ColorsMenu.ANSI_RESET);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        isCheckChoice=false;
                        break;
                    case 2:
                        isCheckChoice=false;
                        break;
                    default:
                        System.out.println("Vui lòng lựa chọn từ 1 đến 2");
                }
            }

        } else {
            System.out.println(ColorsMenu.RED_BOLD + "Không tìm thấy kết quả" + ColorsMenu.ANSI_RESET);

        }

    }




}
