package ra.presentation;

import ra.businnes.AccountBusiness;
import ra.businnes.ReceiptBusiness;
import ra.colors.ColorsMenu;
import ra.entity.Account;
import ra.entity.Bill;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainManagement {
    public static final Scanner sc = new Scanner(System.in);
    public static final String border = "-";

    public static void main(String[] args) throws SQLException {
//        System.out.println("Nhập lựa chọn để hiển thị menu Admin hay User");
//        System.out.println("1.Admin");
//        System.out.println("2.User");
//        System.out.println("Nhập lựa chọn của bạn:");
//        int choice = Integer.parseInt(sc.nextLine());
//        switch (choice) {
//            case 1:
//                MainManagement.menuAdmin();
//                break;
//            case 2:
////                MainManagement.menuUser();
//                break;
//            default:
//                System.out.println("Vui lòng chọn từ 1-2");
//        }
                MainManagement.checkAccount();
    }

    public static void checkAccount() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_UNDERLINED + "============================= Đăng nhập hệ thống =============================" + ColorsMenu.ANSI_RESET);
        System.out.print(ColorsMenu.YELLOW_BOLD + "1.Vui lòng nhập vào UserName = " + ColorsMenu.ANSI_RESET);
        do {
            String userName = sc.nextLine();
            if (userName.isEmpty()) {
                System.err.println("Không được để trống UserName, vui lòng nhập lại");
            } else {
                //Kiểm tra userName có tồn tại hay không
                Account ac = AccountBusiness.getAccountByUserName(userName);
                if (ac != null) {
                    System.out.print(ColorsMenu.YELLOW_BOLD + "2.Vui lòng nhập vào Password = " + ColorsMenu.ANSI_RESET);
                    while (true) {
                        String password = sc.nextLine();
                        if (password.isEmpty()) {
                            System.err.println("Không được để trống Password, vui lòng nhập lại");
                        } else {
                            //Kiểm tra xem password có đúng k
                            if (ac.getPassword().equals(password)) {
                                boolean isCheckPermission = ac.getPermission();
                                boolean isCheckAccStatus=ac.getAcc_Status();
                                if(isCheckAccStatus){
                                    if (!isCheckPermission) {
                                        System.out.println(ColorsMenu.GREEN_BOLD + "Đăng nhập thành công hệ thống cho tài khoản Admin" + ColorsMenu.ANSI_RESET);
                                        //Show Menu Admin
                                        MainManagement.menuAdmin(ac.getEmp_Id());
                                        break;
                                    } else {
                                        System.out.println(ColorsMenu.GREEN_BOLD + "Đăng nhập thành công hệ thống cho tài khoản User" + ColorsMenu.ANSI_RESET);
                                        // Show Menu User
                                        UserManagement.menuUser(ac.getEmp_Id(),userName);
                                        break;
                                    }
                                }else{
                                    System.out.println(ColorsMenu.RED_BOLD+"Tài khoản này đã bị Block rồi,không thể đăng nhập được vào hệ thống"+ColorsMenu.ANSI_RESET);
                                }

                            } else {
                                System.err.println("Password không đúng,vui lòng nhập lại");
                            }

                        }

                    }
                } else {
                    //userName k tồn tại trong CSDL
                    System.err.println("UserName không tồn tại,vui lòng nhập lại");
                }
            }


        } while (true);
    }

    //Menu Admin
    public static void menuAdmin(String employeeId) throws SQLException {
        do {
            String repeated = new String(new char[69]).replace("\0", border);
            System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
            System.out.println("======================= WAREHOUSE MANAGEMENT ========================");
            System.out.println("--              1. Quản lý sản phẩm                                --");
            System.out.println("--              2. Quản lý nhân viên                               --");
            System.out.println("--              3. Quản lý tài khoản                               --");
            System.out.println("--              4. Quản lý phiếu nhập                              --");
            System.out.println("--              5. Quản lý phiếu xuất                              --");
            System.out.println("--              6. Quản lý báo cáo                                 --");
            System.out.println("--              7. Thoát                                           --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceAdminMenu = validateChoiceAdminMenu();
            switch (choiceAdminMenu) {
                case 1:
                    ProductManagement.displayProductMenu();
                    break;
                case 2:
                    EmployeeManagement.displayEmployeeMenu();
                    break;
                case 3:
                    AccountManagement.displayAccountMenu();
                    break;
                case 4:
                    ReceiptManagement.displayReceiptMenu(employeeId);
                    break;
                case 5:
                    BillManagement.displayBillMenu(employeeId);
                    break;

                case 6:
                    ReportManagement.displayReportMenu();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-7");
            }
        } while (true);
    }

    public static int validateChoiceAdminMenu() {
        while (true) {
            String inputChoiceAccountMenuStr = sc.nextLine().trim();
            if (inputChoiceAccountMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong WAREHOUSE MANAGEMENT không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceAccountMenu = Integer.parseInt(inputChoiceAccountMenuStr);
                    if (choiceAccountMenu > 0) {
                        return choiceAccountMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong WAREHOUSE MANAGEMENT phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong WAREHOUSE MANAGEMENT phải là số,vui lòng nhập lại");
                }
            }
        }
    }



}
