package ra.presentation;

import ra.Choice.EmployeeChoice;
import ra.businnes.EmployeeBusiness;
import ra.colors.ColorsMenu;
import ra.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.Choice.EmployeeChoice.*;
import static ra.presentation.MainManagement.sc;

public class EmployeeManagement {
    public static final String border = "-";
    public static final int perPage = 10;
    public static final int indexOfPage = 10;
    public static List<Employee> listEmployee = new ArrayList<>();

    static void displayEmployeeMenu(String employeeIdOfUserName, String userName) throws SQLException {
        boolean isExitEmployeeMenu = true;
        do {
            String repeated = new String(new char[67]).replace("\0", border);
            System.out.println(ColorsMenu.GREEN_BOLD_BRIGHT + repeated);
            System.out.println("========================= QUẢN LÝ NHÂN VIÊN=========================");
            System.out.println("--              1. Danh sách nhân viên                            --");
            System.out.println("--              2. Thêm mới nhân viên                             --");
            System.out.println("--              3. Cập nhật thông tin nhân viên                   --");
            System.out.println("--              4. Cập nhật trạng thái nhân viên                  --");
            System.out.println("--              5. Tìm kiếm nhân viên                             --");
            System.out.println("--              6. Quay lại(WAREHOUSE MANAGEMENT)                 --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceEmployeeMenu = validateChoiceEmployeeMenu();
            switch (choiceEmployeeMenu) {
                case 1:
                    EmployeeManagement.displayDataEmployee(Display, employeeIdOfUserName, userName);
                    break;
                case 2:
                    EmployeeManagement.displayDataEmployee(Create, employeeIdOfUserName, userName);
                    break;
                case 3:
                    EmployeeManagement.displayDataEmployee(Update, employeeIdOfUserName, userName);
                    break;
                case 4:
                    EmployeeManagement.displayDataEmployee(UpdateEmployeeStatus,employeeIdOfUserName,userName);
                    break;
                case 5:
                    EmployeeManagement.displayDataEmployee(Search, employeeIdOfUserName, userName);
                    break;
                case 6:
                    isExitEmployeeMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-6");
            }
        } while (isExitEmployeeMenu);
    }

    public static int validateChoiceEmployeeMenu() {
        while (true) {
            String inputChoiceEmployeeMenuStr = sc.nextLine().trim();
            if (inputChoiceEmployeeMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong EMPLOYEE MANAGEMENT không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceEmployeeMenu = Integer.parseInt(inputChoiceEmployeeMenuStr);
                    if (choiceEmployeeMenu > 0) {
                        return choiceEmployeeMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong EMPLOYEE MANAGEMENT phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong EMPLOYEE MANAGEMENT phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void displayDataEmployee(EmployeeChoice choiceStatus, String employeeIdOfUserName, String userName) throws SQLException {
        int cntPage = 0;
        int choiceNextPage = 0;
        int i = 1;
        // Đếm tất cả số lượng bản ghi trong Employee
        int cntAllDataOfEmployee = getCntDataEmployee();
        // Tổng số trang sẽ có
        int maxPage = (int) Math.ceil((double) cntAllDataOfEmployee / perPage);
        switch (choiceStatus) {
            case Display:
                if (cntAllDataOfEmployee > 0) {
                    EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                    if (maxPage >= 1) {
                        boolean isExitDisplayDataEmployee = true;
                        do {
                            System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                            if (i == 1 && maxPage == 1) {
                                System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                if (choiceNextPage != 0) {
                                    isExitDisplayDataEmployee = false;
                                    break;
                                }
                            } else if (i == 1) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                        break;
                                    case 2:
                                        isExitDisplayDataEmployee = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                }
                            } else if (i == (int) getTotalPage()) {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                        break;
                                    case 2:
                                        isExitDisplayDataEmployee = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");

                                }
                            } else {
                                System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                System.out.println("2.Quay lại trang thứ " + (i - 1));
                                System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                choiceNextPage = validateChoiceDisplayData();
                                switch (choiceNextPage) {
                                    case 1:
                                        i = i + 1;
                                        cntPage = cntPage + indexOfPage;
                                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                        break;
                                    case 2:
                                        i = i - 1;
                                        cntPage = cntPage - indexOfPage;
                                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                        break;
                                    case 3:
                                        isExitDisplayDataEmployee = false;
                                        break;
                                    default:
                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                }
                            }
                        } while (isExitDisplayDataEmployee);
                    }
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về nhân viên trong bảng Employee" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Create:
                if (cntAllDataOfEmployee > 0) {
                    boolean isCheckExitCreate = true;
                    do {
                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataEmployee = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Thêm mới nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 2:
                                            EmployeeManagement.creatDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfEmployee / perPage);
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi thêm mới nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Thêm mới nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 3:
                                            EmployeeManagement.creatDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfEmployee / perPage);
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi thêm mới nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Thêm mới nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 3:
                                            EmployeeManagement.creatDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfEmployee / perPage);
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi thêm mới nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Thêm mới nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 3:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitCreate = false;
                                            break;
                                        case 4:
                                            EmployeeManagement.creatDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            maxPage = (int) Math.ceil((double) cntAllDataOfEmployee / perPage);
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataEmployee);
                        }
                    } while (isCheckExitCreate);
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về nhân viên trong bảng Employee" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Update:
                if (cntAllDataOfEmployee > 0) {
                    boolean isCheckExitUpdate = true;
                    do {
                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataEmployee = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Cập nhật thông tin nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 2:
                                            EmployeeManagement.updateDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật thông tin nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật thông tin nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 3:
                                            EmployeeManagement.updateDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi thêm mới nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Thêm mới nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 3:
                                            EmployeeManagement.updateDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi thêm mới nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Thêm mới nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 3:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdate = false;
                                            break;
                                        case 4:
                                            EmployeeManagement.updateDataEmployee(sc);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataEmployee);
                        }
                    } while (isCheckExitUpdate);
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về nhân viên trong bảng Employee" + ColorsMenu.ANSI_RESET);
                }
                break;
            case UpdateEmployeeStatus:
                if (cntAllDataOfEmployee > 0) {
                    boolean isCheckExitUpdateStatus = true;
                    do {
                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataEmployee = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi danh sách nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "2.Cập nhật trạng thái nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 2:
                                            EmployeeManagement.updateDataEmployeeStatus(sc, employeeIdOfUserName,userName);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật trạng thái nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật trạng thái nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 3:
                                           EmployeeManagement.updateDataEmployeeStatus(sc, employeeIdOfUserName,userName);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật trạng thái nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật trạng thái nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 3:
                                           EmployeeManagement.updateDataEmployeeStatus(sc, employeeIdOfUserName,userName);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi cập nhật trạng thái nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Cập nhật trạng thái nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 3:
                                            isExitDisplayDataEmployee = false;
                                            isCheckExitUpdateStatus = false;
                                            break;
                                        case 4:
                                           EmployeeManagement.updateDataEmployeeStatus(sc, employeeIdOfUserName,userName);
                                            cntAllDataOfEmployee = getCntDataEmployee();
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataEmployee);
                        }
                    } while (isCheckExitUpdateStatus);
                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về nhân viên trong bảng Employee" + ColorsMenu.ANSI_RESET);
                }
                break;
            case Search:
                if (cntAllDataOfEmployee > 0) {
                        EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                        if (maxPage >= 1) {
                            boolean isExitDisplayDataEmployee = true;
                            do {
                                System.out.println("Lựa chọn tiếp theo của bạn sẽ là gì ?");
                                if (i == 1 && maxPage == 1) {
                                    System.out.println(ColorsMenu.RED_BOLD + "1.Thoát khỏi tìm kiếm nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Tìm kiếm nhân viên theo mã hoặc theo tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        case 2:
                                            cntPage = 0;
                                            i = 1;
                                            System.out.println("Nhập vào mã nhân viên hoặc theo tên nhân viên cần tìm kiếm :");
                                            String EmployeeSearch = Employee.inputSearch();
                                            int cntAllDataSearch = EmployeeBusiness.getAllDataSearchEmployee(EmployeeSearch);
                                            int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                            EmployeeManagement.paginationDataSearchEmployee(perPage, cntPage, i, maxPageSearch, EmployeeSearch, cntAllDataSearch);
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-2");
                                    }
//                                if (choiceNextPage != 0) {
//                                    isExitDisplayDataEmployee = false;
//                                    break;
//                                }
                                } else if (i == 1) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tìm kiếm nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Tìm kiếm nhân viên theo mã hoặc theo tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        case 3:
                                            cntPage = 0;
                                            i = 1;
                                            System.out.println("Nhập vào mã nhân viên hoặc theo tên nhân viên cần tìm kiếm :");
                                            String EmployeeSearch = Employee.inputSearch();
                                            int cntAllDataSearch = EmployeeBusiness.getAllDataSearchEmployee(EmployeeSearch);
                                            int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                            EmployeeManagement.paginationDataSearchEmployee(perPage, cntPage, i, maxPageSearch, EmployeeSearch, cntAllDataSearch);
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
                                    }
                                } else if (i == (int) getTotalPage()) {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Quay lại trang thứ " + (i - 1) + ColorsMenu.ANSI_RESET);
                                    System.out.println(ColorsMenu.RED_BOLD + "2.Thoát khỏi tìm kiếm nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("3.Tìm kiếm nhân viên theo mã hoặc theo tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        case 3:
                                            cntPage = 0;
                                            i = 1;
                                            System.out.println("Nhập vào mã nhân viên hoặc theo tên nhân viên cần tìm kiếm :");
                                            String EmployeeSearch = Employee.inputSearch();
                                            int cntAllDataSearch = EmployeeBusiness.getAllDataSearchEmployee(EmployeeSearch);
                                            int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                            EmployeeManagement.paginationDataSearchEmployee(perPage, cntPage, i, maxPageSearch, EmployeeSearch, cntAllDataSearch);
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");

                                    }
                                } else {
                                    System.out.println(ColorsMenu.YELLOW_BOLD + "1.Xem trang tiếp theo >>> " + ColorsMenu.ANSI_RESET);
                                    System.out.println("2.Quay lại trang thứ " + (i - 1));
                                    System.out.println(ColorsMenu.RED_BOLD + "3.Thoát khỏi tìm kiếm nhân viên" + ColorsMenu.ANSI_RESET);
                                    System.out.println("4.Tìm kiếm nhân viên theo mã hoặc theo tên nhân viên");
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Vui lòng nhập lựa chọn của bạn : " + ColorsMenu.ANSI_RESET);
                                    choiceNextPage = validateChoiceDisplayData();
                                    switch (choiceNextPage) {
                                        case 1:
                                            i = i + 1;
                                            cntPage = cntPage + indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 2:
                                            i = i - 1;
                                            cntPage = cntPage - indexOfPage;
                                            EmployeeManagement.paginationDataEmployee(perPage, cntPage, i, maxPage, cntAllDataOfEmployee);
                                            break;
                                        case 3:
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        case 4:
                                            cntPage = 0;
                                            i = 1;
                                            System.out.println("Nhập vào mã nhân viên hoặc theo tên nhân viên cần tìm kiếm :");
                                            String EmployeeSearch = Employee.inputSearch();
                                            int cntAllDataSearch = EmployeeBusiness.getAllDataSearchEmployee(EmployeeSearch);
                                            int maxPageSearch = (int) Math.ceil((double) cntAllDataSearch / perPage);
                                            EmployeeManagement.paginationDataSearchEmployee(perPage, cntPage, i, maxPageSearch, EmployeeSearch, cntAllDataSearch);
                                            isExitDisplayDataEmployee = false;
                                            break;
                                        default:
                                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");

                                    }
                                }
                            } while (isExitDisplayDataEmployee);
                        }

                } else {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Chưa có dữ liệu về nhân viên trong bảng Employee" + ColorsMenu.ANSI_RESET);
                }
                break;

        }
    }

    public static int getCntDataEmployee() throws SQLException {
        return EmployeeBusiness.getCountAllDataEmployee();
    }

    public static void paginationDataEmployee(int perPage, int cntPage, int i, float maxPage,
                                              int cntAllDataOfEmployee) throws SQLException {
        List<Employee> listEmployee = null;
        String repeated = new String(new char[162]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s|\n", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Email", "Số điện thoại", "Địa chỉ", "Trạng thái");
        System.out.println("* " + repeated + " *");
        listEmployee = EmployeeBusiness.getAllDataEmployee(perPage, cntPage);
        listEmployee.forEach(Employee::displayDataEmployee);
        System.out.println("* " + repeated + " *");
        System.out.print("  Page : " + i + " / " + maxPage + "\t\t\t\t\t\t\t");
        System.out.print("All data : " + cntAllDataOfEmployee + "\n");
        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
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

    public static float getTotalPage() throws SQLException {
        int cntAllDataOfEmployee = EmployeeBusiness.getCountAllDataEmployee();
        return (int) Math.ceil((double) cntAllDataOfEmployee / perPage);
    }

    public static void creatDataEmployee(Scanner sc) throws SQLException {
        System.out.print("Nhập số lượng nhân viên mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng nhân viên mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào nhân viên thứ " + (i + 1) + ":");
                            Employee emyNew = new Employee();
                            emyNew.inputDataEmployee();
                            boolean result = EmployeeBusiness.creatDataEmployee(emyNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công nhân viên" + ColorsMenu.ANSI_RESET);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công nhân viên thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng nhân viên mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng nhân viên mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }

    public static void updateDataEmployee(Scanner sc) throws SQLException {
        boolean isExitUpdateDataEmployeeMenu = true;
        boolean result;
        System.out.println("Nhập vào mã nhân viên cần cập nhật :");
        String employeeIdUpdate = Employee.inputEmpIdUpdate();
        //Kiểm tra mã có tồn tại hay không
        Employee emy = EmployeeBusiness.getEmployeeById(employeeIdUpdate);
        if (emy != null) {
            do {
                String repeated = new String(new char[69]).replace("\0", border);
                System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
                System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin =========");
                System.out.println("--              1.Tên nhân viên                                    --");
                System.out.println("--              2.Ngày sinh                                        --");
                System.out.println("--              3.Email                                            --");
                System.out.println("--              4.Số điện thoại                                    --");
                System.out.println("--              5.Địa chỉ                                          --");
                System.out.println("--              6.Thoát khỏi cập nhật thông tin nhân viên          --");
                System.out.println(repeated + ColorsMenu.ANSI_RESET);
                System.out.println("Lựa chọn của bạn là ");
                int choiceUpdate = validateChoiceUpdateEmployee();
                switch (choiceUpdate) {
                    case 1:
                        System.out.print("Bạn muốn thay đổi tên nhân viên" + " từ " + "[" + ColorsMenu.RED_BOLD + emy.getEmp_Name() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        emy.setEmp_Name(Employee.inputEmployeeUpdateName(employeeIdUpdate));
                        result = EmployeeBusiness.updateDataEmployee(emy);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã nhân viên :" + employeeIdUpdate + " vừa được cập nhật thành công tên nhân viên" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataEmployeeMenu = false;
                        break;
                    case 2:
                        System.out.print("Bạn muốn thay đổi ngày sinh" + " từ " + "[" + ColorsMenu.RED_BOLD + emy.getBirth_Of_Date() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        emy.setBirth_Of_Date(Employee.inputBirthOfDate());
                        result = EmployeeBusiness.updateDataEmployee(emy);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã nhân viên :" + employeeIdUpdate + " vừa được cập nhật thành công ngày sinh" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataEmployeeMenu = false;
                        break;
                    case 3:
                        System.out.print("Bạn muốn thay đổi email" + " từ " + "[" + ColorsMenu.RED_BOLD + emy.getEmail() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        emy.setEmail(Employee.inputEmail());
                        result = EmployeeBusiness.updateDataEmployee(emy);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã nhân viên :" + employeeIdUpdate + " vừa được cập nhật thành công email" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataEmployeeMenu = false;
                        break;
                    case 4:
                        System.out.print("Bạn muốn thay đổi số điện thoại" + " từ " + "[" + ColorsMenu.RED_BOLD + emy.getPhone() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        emy.setPhone(Employee.inputPhone());
                        result = EmployeeBusiness.updateDataEmployee(emy);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã nhân viên :" + employeeIdUpdate + " vừa được cập nhật thành công số điện thoại" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataEmployeeMenu = false;
                        break;
                    case 5:
                        System.out.print("Bạn muốn thay đổi địa chỉ" + " từ " + "[" + ColorsMenu.RED_BOLD + emy.getAddress() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        emy.setAddress(Employee.inputAddress());
                        result = EmployeeBusiness.updateDataEmployee(emy);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã nhân viên :" + employeeIdUpdate + " vừa được cập nhật thành công địa chỉ" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataEmployeeMenu = false;
                        break;
                    case 6:
                        isExitUpdateDataEmployeeMenu = false;
                        break;
                    default:
                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
                }
            } while (isExitUpdateDataEmployeeMenu);

        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD+"Mã nhân viên không tồn tại"+ColorsMenu.ANSI_RESET);
        }

    }

    public static int validateChoiceUpdateEmployee() {
        while (true) {
            String inputChoiceEmployeeUpdateMenuStr = sc.nextLine().trim();
            if (inputChoiceEmployeeUpdateMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong menu update không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceEmployeeMenu = Integer.parseInt(inputChoiceEmployeeUpdateMenuStr);
                    if (choiceEmployeeMenu > 0) {
                        return choiceEmployeeMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong menu update phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong menu update phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void updateDataEmployeeStatus(Scanner sc, String employeeIdOfUserName,String userName) throws SQLException {
        System.out.println("Nhập vào mã nhân viên cần cập nhật :");
        String employeeIdUpdate = Employee.inputEmpIdUpdate();
        //Kiểm tra mã nhân viên có tồn tại hay không
        Employee emy = EmployeeBusiness.getEmployeeById(employeeIdUpdate);
        if (emy != null) {
            if (employeeIdOfUserName.equalsIgnoreCase(emy.getEmp_Id())) {
                System.err.println("Rất tiếc bạn đang đăng nhập tài khoản có UserName là "+userName +" với mã nhân viên là :" + employeeIdOfUserName + " vì vậy không thể chuyển đổi trạng thái của nhân viên sang nghỉ vệc hay nghỉ chế độ được");
            } else {
                String EmpStatusValue = emy.getEmp_Status() == 0 ? "Hoạt động" : emy.getEmp_Status() == 1 ? "Nghỉ chế độ" : "Nghỉ việc";
                System.out.println("Bạn muốn thay đổi trạng thái nhân viên" + " từ " + "[" + ColorsMenu.RED_BOLD + EmpStatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                emy.setEmp_Status(Employee.inputEmpStatus());
                //Thực hiện cập nhật
                boolean result = EmployeeBusiness.updateEmployeeStatus(emy);
                if (result) {
                    System.out.println(ColorsMenu.GREEN_BOLD + "Trạng thái nhân viên có mã nhân viên :" + employeeIdUpdate + " vừa được cập nhật thành công" + ColorsMenu.ANSI_RESET);
                } else {
                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                }
            }
        }else{
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD+"Mã nhân viên không tồn tại"+ColorsMenu.ANSI_RESET);
        }

    }

    public static void paginationDataSearchEmployee(int perPage, int cntPage, int i, float maxPage, String
            employeeSearch, int cntAllDataSearchOfEmployee) throws SQLException {
        List<Employee> listEmployeeSearch = null;
        listEmployeeSearch = EmployeeBusiness.searchDataEmployeeByEmployeeNameOrEmployeeId(perPage, cntPage, employeeSearch);
        if (listEmployeeSearch.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không tìm thấy kết quả" + ColorsMenu.ANSI_RESET);
        } else {
            String repeated = new String(new char[162]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s|\n", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Email", "Số điện thoại", "Địa chỉ", "Trạng thái");
            System.out.println("* " + repeated + " *");
            listEmployeeSearch.forEach(Employee::displayDataEmployee);
            System.out.println("* " + repeated + " *");
            System.out.print("  Page : " + i + " / " + maxPage + "\t\t\t\t\t\t\t");
            System.out.print("All data search : " + cntAllDataSearchOfEmployee + "\n");
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
        }
    }

}
