package ra.presentation;

import ra.businnes.ReceiptBusiness;
import ra.colors.ColorsMenu;
import ra.entity.Bill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManagement {
    public static final Scanner sc = new Scanner(System.in);
    public static final String border = "-";
    public static List<Bill> listBill = new ArrayList<>();

    public static void menuUser(String employeeId, String userName) throws SQLException {
        do {
            String repeated = new String(new char[69]).replace("\0", border);
            System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
            System.out.println("======================= WAREHOUSE MANAGEMENT ========================");
            System.out.println("--              1. Danh sách phiếu nhập theo trạng thái            --");
            System.out.println("--              2. Tạo phiếu nhập                                  --");
            System.out.println("--              3. Cập nhật phiếu nhập                             --");
            System.out.println("--              4. Tìm kiếm phiếu nhập                             --");
            System.out.println("--              5. Danh sách phiếu xuất theo trạng thái            --");
            System.out.println("--              6. Tạo phiếu xuất                                  --");
            System.out.println("--              7. Cập nhật phiếu xuất                             --");
            System.out.println("--              8. Tìm kiếm phiếu xuất                             --");
            System.out.println("--              9. Thoát                                           --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            String repeated1 = new String(new char[179]).replace("\0", border);
            System.out.print("Lựa chọn của bạn là :");
            int choiceAdminMenu = validateChoiceUserMenu();
//            String repeated1 = null;
            switch (choiceAdminMenu) {
                case 1:
                    UserManagement.displayDataReceipt(employeeId, userName);
                    break;
                case 2:
                    listBill = ReceiptBusiness.getAllDataReceiptBillStatusByEmpId(employeeId);
                    if(listBill.isEmpty()){
                        System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
                    }else{
                    repeated1 = new String(new char[179]).replace("\0", border);
                    System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
                    System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                    System.out.println("* " + repeated1 + " *");
                    listBill.forEach(Bill::displayDataReceipt);
                    System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    }
                    ReceiptManagement.creatDataReceiptFromBillUser(sc, employeeId);

                    break;
                case 3:
//                    UserManagement.displayDataReceipt(employeeId,userName);
                    listBill = ReceiptBusiness.getAllDataReceiptBillStatusByEmpId(employeeId);
                    if(listBill.isEmpty()){
                        System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
                    }else{
                        repeated1 = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated1 + " *");
                        listBill.forEach(Bill::displayDataReceipt);
                        System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    }

//                    repeated1 = new String(new char[179]).replace("\0", border);
//                    System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
//                    System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
//                    System.out.println("* " + repeated1 + " *");
//                    listBill.forEach(Bill::displayDataReceipt);
//                    System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    ReceiptManagement.updateDataReceiptUser(sc);

                    break;
                case 4:
                    listBill = ReceiptBusiness.getAllDataReceiptBillStatusByEmpId(employeeId);
                    if(listBill.isEmpty()){
                        System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
                    }else{
                        repeated1 = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated1 + " *");
                        listBill.forEach(Bill::displayDataReceipt);
                        System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    }
//                    repeated1 = new String(new char[179]).replace("\0", border);
//                    System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
//                    System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
//                    System.out.println("* " + repeated1 + " *");
//                    listBill.forEach(Bill::displayDataReceipt);
//                    System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
//                    ReceiptManagement.updateDataReceiptUser(sc);

                    ReceiptManagement.searchReceiptUser(employeeId);
                    break;
                case 5:
                    UserManagement.displayDataBill(employeeId, userName);
                    break;
                case 6:
                    listBill = ReceiptBusiness.getAllDataBillBillStatusByEmpId(employeeId);
                    if(listBill.isEmpty()){
                        System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
                    }else{
                        repeated1 = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên xuất", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated1 + " *");
                        listBill.forEach(Bill::displayDataBill);
                        System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    }

                    BillManagement.creatDataBillUser(sc, employeeId);
                    break;
                case 7:
                    listBill = ReceiptBusiness.getAllDataBillBillStatusByEmpId(employeeId);
                    if(listBill.isEmpty()){
                        System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
                    }else{
                        repeated1 = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên xuất", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated1 + " *");
                        listBill.forEach(Bill::displayDataBill);
                        System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    }
                    BillManagement.updateDataBillUser(sc);

                    break;
                case 8:
                    listBill = ReceiptBusiness.getAllDataBillBillStatusByEmpId(employeeId);
                    if(listBill.isEmpty()){
                        System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
                    }else{
                        repeated1 = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated1 + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên xuất", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated1 + " *");
                        listBill.forEach(Bill::displayDataBill);
                        System.out.println("* " + repeated1 + " *" + ColorsMenu.ANSI_RESET);
                    }
                    BillManagement.searchBillUser(employeeId);

                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-9");
            }

        } while (true);
    }

    public static int validateChoiceUserMenu() {
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
    //1.Danh sách phiếu nhập theo trạng thái

    public static void displayDataReceipt(String employeeId, String userName) throws SQLException {
        List<Bill> listBill = ReceiptBusiness.getAllDataReceiptBillStatusByEmpId(employeeId);
        if (listBill.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
        } else {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            listBill.forEach(Bill::displayDataReceipt);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
        }
    }

    //1.Danh sách phiếu xuất theo trạng thái
    public static void displayDataBill(String employeeId, String userName) throws SQLException {
        List<Bill> listBill = ReceiptBusiness.getAllDataBillBillStatusByEmpId(employeeId);
        if (listBill.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "UserName :" + userName + " không có phiếu nhập theo trạng thái Tạo hoặc Hủy nào cả" + ColorsMenu.ANSI_RESET);
        } else {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            listBill.forEach(Bill::displayDataBill);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
        }
    }
}
