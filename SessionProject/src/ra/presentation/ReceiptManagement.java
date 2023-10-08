package ra.presentation;

import ra.businnes.BillDetailBusiness;
import ra.businnes.ReceiptBusiness;
import ra.colors.ColorsMenu;
import ra.entity.Bill;
import ra.entity.Bill_Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.MainManagement.sc;

//import static ra.Choice.ReceiptChoice.*;
//import static ra.Choice.ReceiptChoice.Search;

public class ReceiptManagement {
    public static final String border = "-";
    public static final int perPage = 10;
    public static final int indexOfPage = 10;
    public static List<Bill> listBill = new ArrayList<>();

    static void displayReceiptMenu(String employeeId) throws SQLException {
        boolean isExitReceiptMenu = true;
        do {
            String repeated = new String(new char[67]).replace("\0", border);
            System.out.println(ColorsMenu.GREEN_BOLD_BRIGHT + repeated);
            System.out.println("========================= QUẢN LÝ PHIẾU NHẬP =========================");
            System.out.println("--              1. Danh sách phiếu nhập                             --");
            System.out.println("--              2. Tạo phiếu nhập                                   --");
            System.out.println("--              3. Cập nhật thông tin phiếu nhập                    --");
            System.out.println("--              4. Chi tiết phiếu nhập                              --");
            System.out.println("--              5. Duyệt phiếu nhập                                 --");
            System.out.println("--              6. Tìm kiếm phiếu nhập                              --");
            System.out.println("--              7. Quay lại(WAREHOUSE MANAGEMENT)                   --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceReceiptMenu = validateChoiceReceiptMenu();
            switch (choiceReceiptMenu) {
                case 1:
                    ReceiptManagement.displayDataReceipt();
                    break;
                case 2:
                    ReceiptManagement.displayDataReceipt();
                    ReceiptManagement.creatDataReceiptFromBill(sc, employeeId);
                    break;
                case 3:
                    listBill = ReceiptBusiness.getAllDataReceiptByBillStatusOther2();
                    if (listBill.isEmpty()) {
                        System.out.println("Không có phiếu nhập nào với trạng thái Tạo hoặc Hủy cả");
                    } else {
                        repeated = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated + " *");
                        listBill.forEach(Bill::displayDataReceipt);
                        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
                        ReceiptManagement.updateDataReceipt(sc);
                    }
                    break;
                case 4:
                    BillDetailManagement.displayBillDetailForReceipt();
                    break;
                case 5:
                    listBill = ReceiptBusiness.getAllDataReceiptByBillStatusOther2();
                    if (listBill.isEmpty()) {
                        System.out.println("Không có phiếu nhập nào với trạng thái Tạo hoặc Hủy cả");
                    } else {
                        repeated = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated + " *");
                        listBill.forEach(Bill::displayDataReceipt);
                        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
                        ReceiptManagement.approveReceipt(employeeId);
                    }
                    break;
                case 6:
                    ReceiptManagement.displayDataReceipt();
                    ReceiptManagement.searchReceipt(employeeId);
                    break;
                case 7:
                    isExitReceiptMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-7");
            }
        } while (isExitReceiptMenu);
    }

    public static int validateChoiceReceiptMenu() {
        while (true) {
            String inputChoiceReceiptMenuStr = sc.nextLine().trim();
            if (inputChoiceReceiptMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong RECEIPT MANAGEMENT không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceReceiptMenu = Integer.parseInt(inputChoiceReceiptMenuStr);
                    if (choiceReceiptMenu > 0) {
                        return choiceReceiptMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong RECEIPT MANAGEMENT phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong RECEIPT MANAGEMENT phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void displayDataReceipt() throws SQLException {
        listBill = ReceiptBusiness.getAllDataReceipt();
        if (listBill.isEmpty()) {
            System.out.println("Không có phiếu nhập nào cả");
        } else {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            listBill.forEach(Bill::displayDataReceipt);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
        }
    }

    public static void displayDataReceiptBillStatus0() throws SQLException {
        listBill = ReceiptBusiness.getAllDataReceiptByBillStatus0();

    }

    public static void displayDataReceiptBillStatusOther2() throws SQLException {
        listBill = ReceiptBusiness.getAllDataReceiptByBillStatusOther2();
        if (listBill.isEmpty()) {
            System.out.println("Không có phiếu nhập nào với trạng thái Tạo hoặc Hủy cả");
        } else {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            listBill.forEach(Bill::displayDataReceipt);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);

        }
    }


    public static void creatDataReceiptFromBill(Scanner sc, String employeeId) throws SQLException {
        System.out.print("Nhập số lượng phiếu nhập mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng phiếu nhập mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào phiếu nhập thứ " + (i + 1) + ":");
                            Bill billNew = new Bill();
                            billNew.inputDataReceiptForBill(employeeId);
                            boolean result = ReceiptBusiness.creatDataReceipt(billNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công phiếu nhập" + ColorsMenu.ANSI_RESET);
                                long billIdNow = ReceiptBusiness.maxIndex();
                                BillDetailManagement.creatDataBillDetail(sc, billIdNow);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công phiếu nhập thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                                long billIdNow = ReceiptBusiness.maxIndex();
                                BillDetailManagement.creatDataBillDetail(sc, billIdNow);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng phiếu nhập mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng phiếu nhập mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }
//    public static void creatDataReceiptFromBillUser(Scanner sc,String employeeId) throws SQLException {
//        System.out.print("Nhập số lượng phiếu nhập mà bạn muốn thêm :");
//        while (true) {
//            String inputNumberStr = sc.nextLine().trim();
//            if (inputNumberStr.isEmpty()) {
//                System.err.println("Số lượng phiếu nhập mà bạn muốn thêm không được để trống,vui lòng nhập lại");
//            } else {
//                try {
//                    int number = Integer.parseInt(inputNumberStr);
//                    if (number > 0) {
//                        for (int i = 0; i < number; i++) {
//                            System.out.println("Nhập vào phiếu nhập thứ " + (i + 1) + ":");
//                            Bill billNew = new Bill();
//                            billNew.inputDataReceiptForBill(employeeId);
//                            boolean result = ReceiptBusiness.creatDataReceipt(billNew);
//                            if (result && number == 1) {
//                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công phiếu nhập" + ColorsMenu.ANSI_RESET);
//                                long billIdNow = ReceiptBusiness.maxIndex();
//                                BillDetailManagement.creatDataBillDetail(sc, billIdNow);
//                            } else if (result) {
//                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công phiếu nhập thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
//                                long billIdNow = ReceiptBusiness.maxIndex();
//                                BillDetailManagement.creatDataBillDetail(sc, billIdNow);
//                            } else {
//                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                            }
//                        }
//                        break;
//                    } else {
//                        System.err.println("Số lượng phiếu nhập mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
//                    }
//
//                } catch (NumberFormatException ex) {
//                    System.err.println("Số lượng phiếu nhập mà bạn muốn thêm phải là số,vui lòng nhập lại");
//                } catch (Exception ex3) {
//                    System.err.println("Lỗi hệ thống");
//                }
//
//            }
//        }
//    }

    public static void updateDataReceipt(Scanner sc) throws SQLException {
        boolean isExitUpdateDataReceiptMenu = true;
        boolean result;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu nhâp cần cập nhật :");
        String receiptUpdate = Bill.inputReceiptUpdate();
        Bill bil = ReceiptBusiness.getReceiptByBillIdOrBillCode(receiptUpdate);
        updateUser(bil, receiptUpdate, isExitUpdateDataReceiptMenu);
    }

    public static void updateDataReceiptUser(Scanner sc) throws SQLException {
        boolean isExitUpdateDataReceiptMenu = true;
        boolean result;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu nhâp cần cập nhật :");
        String receiptUpdate=Bill.inputReceiptUpdate();
        Bill bil = ReceiptBusiness.getReceiptByBillIdOrBillCode(receiptUpdate);
        updateUser(bil, receiptUpdate, isExitUpdateDataReceiptMenu);
    }

    private static void update(Bill bil, String receiptUpdate, boolean isExitUpdateDataReceiptMenu) throws SQLException {
        boolean result;
        if (bil != null) {
            if (bil.getBill_Status() != 2) {
                List<Bill_Detail> listBillDetail = BillDetailBusiness.getAllDataBillDetailByReceipt(bil.getBill_Id());
                do {
                    if (!listBillDetail.isEmpty()) {
                        String repeated = new String(new char[69]).replace("\0", border);
                        System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
                        System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin =========");
                        System.out.println("--              1.Mã nhân viên nhập                                --");
                        System.out.println("--              2.Ngày tạo                                         --");
                        System.out.println("--              3.Mã nhân viên duyệt                               --");
                        System.out.println("--              4.Ngày duyệt                                       --");
                        System.out.println("--              5.Trạng thái                                       --");
                        System.out.println("--              6.Cập nhật cả chi tiết phiếu nhập của phiếu nhập   --");
                        System.out.println("--              7.Thoát khỏi cập thông tin nhập phiếu nhâp         --");
                        System.out.println(repeated + ColorsMenu.ANSI_RESET);
                        System.out.println("Lựa chọn của bạn là :");
                        int choiceUpdate = validateChoiceUpdateReceipt();
                        switch (choiceUpdate) {
                            case 1:
                                System.out.println("Bạn muốn thay đổi mã nhân viên nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Created() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setEmp_Id_Created(Bill.inputEmpIdCreated());
                                result = ReceiptBusiness.updateDataReceiptForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 2:
                                System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getCreated_Bill() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setCreated_Bill(Bill.inputCreatedBill());
                                result = ReceiptBusiness.updateDataReceiptForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 3:
                                System.out.println("Bạn muốn thay đổi mã nhân viên duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Auth() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setEmp_Id_Auth(Bill.inputEmpIdAuth());
                                result = ReceiptBusiness.updateDataReceiptForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên duyệt" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 4:
                                System.out.print("Bạn muốn thay đổi ngày duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getAuth_Date() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setAuth_Date(Bill.inputAuthDate());
                                result = ReceiptBusiness.updateDataReceiptForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày duyệt" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 5:
                                String StatusValue = bil.getBill_Status() == 0 ? "Tạo" : bil.getBill_Status() == 1 ? "Hủy" : "Duyệt";
                                System.out.println("Bạn muốn thay đổi trạng thái" + " từ " + "[" + ColorsMenu.RED_BOLD + StatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setBill_Status(Bill.inputBillStatusUpdate());
                                result = ReceiptBusiness.updateDataReceiptForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 6:
                                ReceiptManagement.updateBillDetail(bil);
                                break;
                            case 7:
                                isExitUpdateDataReceiptMenu = false;
                                break;
                            default:
                                System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
                        }
                    }
                } while (isExitUpdateDataReceiptMenu);
            } else {
                System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc chỉ được cập nhật thông tin phiếu nhập khi trạng thái đang ở trạng thái Tạo hoặc Hủy" + ColorsMenu.ANSI_RESET);
            }
        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }

    private static void updateUser(Bill bil, String receiptUpdate, boolean isExitUpdateDataReceiptMenu) throws SQLException {
        boolean result;
        if (bil != null) {
            if (bil.getBill_Status() != 2) {
                List<Bill_Detail> listBillDetail = BillDetailBusiness.getAllDataBillDetailByReceipt(bil.getBill_Id());
                do {
                    if (!listBillDetail.isEmpty()) {
                        String repeated = new String(new char[69]).replace("\0", border);
                        System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
                        System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin =========");
                        System.out.println("--              1.Mã nhân viên nhập                                --");
                        System.out.println("--              2.Ngày tạo                                         --");
                        System.out.println("--              3.Trạng thái                                       --");
                        System.out.println("--              4.Cập nhật cả chi tiết phiếu nhập của phiếu nhập   --");
                        System.out.println("--              5.Thoát khỏi cập thông tin nhập phiếu nhâp         --");
                        System.out.println(repeated + ColorsMenu.ANSI_RESET);
                        System.out.println("Lựa chọn của bạn là :");
                        int choiceUpdate = validateChoiceUpdateReceipt();
                        switch (choiceUpdate) {
                            case 1:
                                System.out.println("Bạn muốn thay đổi mã nhân viên nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Created() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setEmp_Id_Created(Bill.inputEmpIdCreated());
                                result = ReceiptBusiness.updateDataReceiptForBillUser(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 2:
                                System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getCreated_Bill() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setCreated_Bill(Bill.inputCreatedBill());
                                result = ReceiptBusiness.updateDataReceiptForBillUser(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;

                            case 3:
                                String StatusValue = bil.getBill_Status() == 0 ? "Tạo" : bil.getBill_Status() == 1 ? "Hủy" : "Duyệt";
                                System.out.println("Bạn muốn thay đổi trạng thái" + " từ " + "[" + ColorsMenu.RED_BOLD + StatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setBill_Status(Bill.inputBillStatusUpdate());
                                result = ReceiptBusiness.updateDataReceiptForBillUser(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 4:
                                ReceiptManagement.updateBillDetail(bil);
                                break;
                            case 5:
                                isExitUpdateDataReceiptMenu = false;
                                break;
                            default:
                                System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
                        }
                    }
                } while (isExitUpdateDataReceiptMenu);
            } else {
                System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc chỉ được cập nhật thông tin phiếu nhập khi trạng thái đang ở trạng thái Tạo hoặc Hủy" + ColorsMenu.ANSI_RESET);
            }
        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }

    public static int validateChoiceUpdateReceipt() {
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

    public static void updateBillDetail(Bill bil) throws SQLException {
        boolean isCheckUpdateBillDetail = true;
        while (isCheckUpdateBillDetail) {
            BillDetailManagement.displayBillDetail(bil);
            System.out.println("Phiếu nhập có cả chi tiết phiếu nhập ,bạn có muốn cập nhật không ?");
            System.out.println("1.Có");
            System.out.println("2.Không");
            System.out.println("Nhập vào lựa chọn của bạn :");
            int choice = validateChoiceUpdateBillDetail();
            switch (choice) {
                case 1:
                    BillDetailManagement.updateBillDetailForReceipt();
                    isCheckUpdateBillDetail = false;
                    break;
                case 2:
                    isCheckUpdateBillDetail = false;
                    break;
                default:
                    System.out.println("Vui lòng lựa chọn từ 1-2");
            }

        }
    }

    public static int validateChoiceUpdateBillDetail() {
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

    public static void approveReceipt(String employeeId) throws SQLException {
        boolean isCheckUpdateBillDetail = true;
        boolean result;
        ReceiptManagement.displayDataReceiptBillStatus0();
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu nhâp cần duyệt :");
        String receiptUpdate = Bill.inputReceiptUpdate();
        Bill bil = null;
        bil = ReceiptBusiness.getReceiptByBillIdOrBillCodeAndBillStatus0(receiptUpdate);
        approve(bil, isCheckUpdateBillDetail, receiptUpdate, employeeId);
    }

    private static void approve(Bill bil, boolean isCheckUpdateBillDetail, String receiptUpdate, String employeeId) throws SQLException {
        boolean result;
        if (bil != null) {
            while (isCheckUpdateBillDetail) {
                System.out.println("Bạn có muốn duyệt không ?");
                System.out.println("1.Có");
                System.out.println("2.Không");
                System.out.println("Nhập vào lựa chọn của bạn :");
                int choice = validateChoiceUpdateBillDetail();
                switch (choice) {
                    case 1:
                        Bill billNew = new Bill();
                        billNew.inputDataReceiptAuth(bil.getBill_Id(), employeeId);
//                        bil.setBill_Status((short) 2);
                        result = ReceiptBusiness.updateDataApproveReceipt(billNew);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái từ" + ColorsMenu.YELLOW_BOLD + " Tạo " + ColorsMenu.ANSI_RESET + "sang" + ColorsMenu.GREEN_BOLD + " Duyệt " + ColorsMenu.ANSI_RESET + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isCheckUpdateBillDetail = false;
                        break;
                    case 2:
                        isCheckUpdateBillDetail = false;
                        break;
                    default:
                        System.out.println("Vui lòng lựa chọn từ 1-2");
                }

            }
        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại hoặc là trạng thái phiếu nhập là Huỷ" + ColorsMenu.ANSI_RESET);
        }
    }

    public static void searchReceipt(String employeeId) throws SQLException {
        boolean isExitUpdateDataReceiptMenu = true;
        boolean result = false;
        boolean isCheckSearch = true;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu nhâp cần tìm kiếm :");
        String receiptUpdate = Bill.inputReceiptUpdate();
        Bill bil = null;
        bil = ReceiptBusiness.getReceiptByBillIdOrBillCode(receiptUpdate);
        if (bil != null) {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            List<Bill> listBill = new ArrayList<>();
            listBill.add(bil);
            listBill.forEach(Bill::displayDataReceipt);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
            String repeated2 = new String(new char[69]).replace("\0", border);
            System.out.println(ColorsMenu.PURPLE_BOLD + repeated2);
            System.out.println("=========      Vui lòng lựa chọn danh mục                   =========");
            System.out.println("--              1.Cập nhật phiếu nhập                              --");
            System.out.println("--              2.Duyệt phiếu nhập                                 --");
            System.out.println("--              3.Thoát khỏi tìm kiếm phiếu nhập                   --");
            System.out.println(repeated2 + ColorsMenu.ANSI_RESET);
            System.out.println("Lựa chọn của bạn là :");
            int choiceUpdate = validateChoiceUpdateReceipt();
            while (isCheckSearch) {
                switch (choiceUpdate) {
                    case 1:
                        if (bil.getBill_Status() != 2) {
                            update(bil, receiptUpdate, isCheckSearch);
                        } else {
                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu nhập này đã được duyệt không thể cập nhật được" + ColorsMenu.ANSI_RESET);
                        }
                        isCheckSearch = false;
                        break;
                    case 2:
                        if (bil.getBill_Status() != 2) {
                            approve(bil, isCheckSearch, receiptUpdate, employeeId);
                        } else {
                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu nhập này đang ở chế độ duyệt rồi" + ColorsMenu.ANSI_RESET);
                        }
                        isCheckSearch = false;
                        break;
                    case 3:
                        isCheckSearch = false;
                        break;
                    default:
                        System.out.println("Vui lòng lựa chọn từ 1 đến 3");
                }
            }
//            if (bil.getBill_Status() != 2) {
//                boolean isCheckSearch = true;
//                while (isCheckSearch) {
//                    String repeated = new String(new char[69]).replace("\0", border);
//                    System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
//                    System.out.println("=========       Vui lòng lựa chọn danh mục sau đây          =========");
//                    System.out.println("--              1.Cập nhật phiếu nhập                              --");
//                    System.out.println("--              2.Duyệt phiếu nhập                                 --");
//                    System.out.println("--              3.Thoát khỏi menu tìm kiếm                         --");
//                    System.out.println(repeated + ColorsMenu.ANSI_RESET);
//                    System.out.println("Lựa chọn của bạn là :");
//                    int choiceSearch = validateChoiceUpdateReceipt();
//                    switch (choiceSearch) {
//                        case 1:
//                            assert bil != null;
//                            List<Bill_Detail> listBillDetail = BillDetailBusiness.getAllDataBillDetailByReceipt(bil.getBill_Id());
//                            if (!listBillDetail.isEmpty()) {
//                                String repeated2 = new String(new char[69]).replace("\0", border);
//                                System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
//                                System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin =========");
//                                System.out.println("--              1.Mã nhân viên nhập                                --");
//                                System.out.println("--              2.Ngày tạo                                         --");
//                                System.out.println("--              3.Mã nhân viên duyệt                               --");
//                                System.out.println("--              4.Ngày duyệt                                       --");
//                                System.out.println("--              5.Trạng thái                                       --");
//                                System.out.println("--              6.Cập nhật cả chi tiết phiếu nhập của phiếu nhập   --");
//                                System.out.println("--              7.Thoát khỏi cập thông tin nhập phiếu nhâp         --");
//                                System.out.println(repeated + ColorsMenu.ANSI_RESET);
//                                System.out.println("Lựa chọn của bạn là :");
//                                int choiceUpdate = validateChoiceUpdateReceipt();
//                                switch (choiceUpdate) {
//                                    case 1:
//                                        System.out.println("Bạn muốn thay đổi mã nhân viên nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Created() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setEmp_Id_Created(Bill.inputEmpIdCreated());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 2:
//                                        System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getCreated_Bill() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setCreated_Bill(Bill.inputCreatedBill());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 3:
//                                        System.out.println("Bạn muốn thay đổi mã nhân viên duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Auth() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setEmp_Id_Auth(Bill.inputEmpIdAuth());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên duyệt" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 4:
//                                        System.out.print("Bạn muốn thay đổi ngày duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getAuth_Date() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setAuth_Date(Bill.inputAuthDate());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày duyệt" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 5:
//                                        String StatusValue = bil.getBill_Status() == 0 ? "Tạo" : bil.getBill_Status() == 1 ? "Hủy" : "Duyệt";
//                                        System.out.println("Bạn muốn thay đổi trạng thái" + " từ " + "[" + ColorsMenu.RED_BOLD + StatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setBill_Status(Bill.inputBillStatusUpdate());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 6:
//                                        ReceiptManagement.updateBillDetail(bil);
//                                        break;
//                                    case 7:
//                                        isExitUpdateDataReceiptMenu = false;
//                                        break;
//                                    default:
//                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
//                                }
//                            }
//                            break;
//                        case 2:
//                            bil = ReceiptBusiness.getReceiptByBillIdOrBillCodeAndBillStatus0(receiptUpdate);
//                            boolean isCheckUpdateBillDetail=true;
//                            if (bil != null) {
//                                while (isCheckUpdateBillDetail) {
//                                    System.out.println("Bạn có muốn duyệt không ?");
//                                    System.out.println("1.Có");
//                                    System.out.println("2.Không");
//                                    System.out.println("Nhập vào lựa chọn của bạn :");
//                                    int choice = validateChoiceUpdateBillDetail();
//                                    switch (choice) {
//                                        case 1:
//                                            Bill billNew = new Bill();
//                                            billNew.inputDataReceiptAuth(bil.getBill_Id());
//                                            result = ReceiptBusiness.updateDataApproveReceipt(billNew);
//                                            if (result) {
//                                                System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái từ" + ColorsMenu.YELLOW_BOLD + " Tạo " + ColorsMenu.ANSI_RESET + " sang " + ColorsMenu.GREEN_BOLD + " Duyệt " + ColorsMenu.ANSI_RESET + ColorsMenu.ANSI_RESET);
//                                            } else {
//                                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                            }
//                                            isCheckUpdateBillDetail = false;
//                                            break;
//                                        case 2:
//                                            isCheckUpdateBillDetail = false;
//                                            break;
//                                        default:
//                                            System.out.println("Vui lòng lựa chọn từ 1-2");
//                                    }
//
//                                }
//                            } else {
//                                //Mã nhân viên k tồn tại trong CSDL
//                                System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại" + ColorsMenu.ANSI_RESET);
//                            }
//                            break;
//                        case 3:
//                            isCheckSearch = false;
//                            break;
//                        default:
//                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
//
//                    }
//
//                }
//            } else {
//                System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc chỉ được cập nhật thông tin phiếu nhập khi trạng thái đang ở trạng thái Tạo hoặc Hủy" + ColorsMenu.ANSI_RESET);
//            }
        } else {
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }

    public static void searchReceiptUser(String employeeId) throws SQLException {
        boolean isExitUpdateDataReceiptMenu = true;
        boolean result = false;
        boolean isCheckSearch = true;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu nhâp cần tìm kiếm :");
        String receiptUpdate = Bill.inputReceiptUpdate();
        Bill bil = null;
        bil = ReceiptBusiness.getReceiptByBillIdOrBillCode(receiptUpdate);
        if (bil != null) {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            List<Bill> listBill = new ArrayList<>();
            listBill.add(bil);
            listBill.forEach(Bill::displayDataReceipt);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
            String repeated2 = new String(new char[69]).replace("\0", border);
            System.out.println(ColorsMenu.PURPLE_BOLD + repeated2);
            System.out.println("=========      Vui lòng lựa chọn danh mục                   =========");
            System.out.println("--              1.Cập nhật phiếu nhập                              --");
//            System.out.println("--              2.Duyệt phiếu nhập                                 --");
            System.out.println("--              2.Thoát khỏi tìm kiếm phiếu nhập                   --");
            System.out.println(repeated2 + ColorsMenu.ANSI_RESET);
            System.out.println("Lựa chọn của bạn là :");
            int choiceUpdate = validateChoiceUpdateReceipt();
            while (isCheckSearch) {
                switch (choiceUpdate) {
                    case 1:
                        if (bil.getBill_Status() != 2) {
                            updateUser(bil, receiptUpdate, isCheckSearch);
                        } else {
                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu nhập này đã được duyệt không thể cập nhật được" + ColorsMenu.ANSI_RESET);
                        }
                        isCheckSearch = false;
                        break;
//                    case 2:
//                        if (bil.getBill_Status() != 2) {
//                            approve(bil, isCheckSearch, receiptUpdate,employeeId);
//                        } else {
//                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu nhập này đang ở chế độ duyệt rồi" + ColorsMenu.ANSI_RESET);
//                        }
//                        isCheckSearch = false;
//                        break;
                    case 2:
                        isCheckSearch = false;
                        break;
                    default:
                        System.out.println("Vui lòng lựa chọn từ 1 đến 2");
                }
            }
//            if (bil.getBill_Status() != 2) {
//                boolean isCheckSearch = true;
//                while (isCheckSearch) {
//                    String repeated = new String(new char[69]).replace("\0", border);
//                    System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
//                    System.out.println("=========       Vui lòng lựa chọn danh mục sau đây          =========");
//                    System.out.println("--              1.Cập nhật phiếu nhập                              --");
//                    System.out.println("--              2.Duyệt phiếu nhập                                 --");
//                    System.out.println("--              3.Thoát khỏi menu tìm kiếm                         --");
//                    System.out.println(repeated + ColorsMenu.ANSI_RESET);
//                    System.out.println("Lựa chọn của bạn là :");
//                    int choiceSearch = validateChoiceUpdateReceipt();
//                    switch (choiceSearch) {
//                        case 1:
//                            assert bil != null;
//                            List<Bill_Detail> listBillDetail = BillDetailBusiness.getAllDataBillDetailByReceipt(bil.getBill_Id());
//                            if (!listBillDetail.isEmpty()) {
//                                String repeated2 = new String(new char[69]).replace("\0", border);
//                                System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
//                                System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin =========");
//                                System.out.println("--              1.Mã nhân viên nhập                                --");
//                                System.out.println("--              2.Ngày tạo                                         --");
//                                System.out.println("--              3.Mã nhân viên duyệt                               --");
//                                System.out.println("--              4.Ngày duyệt                                       --");
//                                System.out.println("--              5.Trạng thái                                       --");
//                                System.out.println("--              6.Cập nhật cả chi tiết phiếu nhập của phiếu nhập   --");
//                                System.out.println("--              7.Thoát khỏi cập thông tin nhập phiếu nhâp         --");
//                                System.out.println(repeated + ColorsMenu.ANSI_RESET);
//                                System.out.println("Lựa chọn của bạn là :");
//                                int choiceUpdate = validateChoiceUpdateReceipt();
//                                switch (choiceUpdate) {
//                                    case 1:
//                                        System.out.println("Bạn muốn thay đổi mã nhân viên nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Created() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setEmp_Id_Created(Bill.inputEmpIdCreated());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 2:
//                                        System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getCreated_Bill() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setCreated_Bill(Bill.inputCreatedBill());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 3:
//                                        System.out.println("Bạn muốn thay đổi mã nhân viên duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Auth() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setEmp_Id_Auth(Bill.inputEmpIdAuth());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên duyệt" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 4:
//                                        System.out.print("Bạn muốn thay đổi ngày duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getAuth_Date() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setAuth_Date(Bill.inputAuthDate());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công ngày duyệt" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 5:
//                                        String StatusValue = bil.getBill_Status() == 0 ? "Tạo" : bil.getBill_Status() == 1 ? "Hủy" : "Duyệt";
//                                        System.out.println("Bạn muốn thay đổi trạng thái" + " từ " + "[" + ColorsMenu.RED_BOLD + StatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                                        bil.setBill_Status(Bill.inputBillStatusUpdate());
//                                        result = ReceiptBusiness.updateDataReceiptForBill(bil);
//                                        if (result) {
//                                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái" + ColorsMenu.ANSI_RESET);
//                                        } else {
//                                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                        }
//                                        break;
//                                    case 6:
//                                        ReceiptManagement.updateBillDetail(bil);
//                                        break;
//                                    case 7:
//                                        isExitUpdateDataReceiptMenu = false;
//                                        break;
//                                    default:
//                                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
//                                }
//                            }
//                            break;
//                        case 2:
//                            bil = ReceiptBusiness.getReceiptByBillIdOrBillCodeAndBillStatus0(receiptUpdate);
//                            boolean isCheckUpdateBillDetail=true;
//                            if (bil != null) {
//                                while (isCheckUpdateBillDetail) {
//                                    System.out.println("Bạn có muốn duyệt không ?");
//                                    System.out.println("1.Có");
//                                    System.out.println("2.Không");
//                                    System.out.println("Nhập vào lựa chọn của bạn :");
//                                    int choice = validateChoiceUpdateBillDetail();
//                                    switch (choice) {
//                                        case 1:
//                                            Bill billNew = new Bill();
//                                            billNew.inputDataReceiptAuth(bil.getBill_Id());
//                                            result = ReceiptBusiness.updateDataApproveReceipt(billNew);
//                                            if (result) {
//                                                System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công trạng thái từ" + ColorsMenu.YELLOW_BOLD + " Tạo " + ColorsMenu.ANSI_RESET + " sang " + ColorsMenu.GREEN_BOLD + " Duyệt " + ColorsMenu.ANSI_RESET + ColorsMenu.ANSI_RESET);
//                                            } else {
//                                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                                            }
//                                            isCheckUpdateBillDetail = false;
//                                            break;
//                                        case 2:
//                                            isCheckUpdateBillDetail = false;
//                                            break;
//                                        default:
//                                            System.out.println("Vui lòng lựa chọn từ 1-2");
//                                    }
//
//                                }
//                            } else {
//                                //Mã nhân viên k tồn tại trong CSDL
//                                System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại" + ColorsMenu.ANSI_RESET);
//                            }
//                            break;
//                        case 3:
//                            isCheckSearch = false;
//                            break;
//                        default:
//                            System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-3");
//
//                    }
//
//                }
//            } else {
//                System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc chỉ được cập nhật thông tin phiếu nhập khi trạng thái đang ở trạng thái Tạo hoặc Hủy" + ColorsMenu.ANSI_RESET);
//            }
        } else {
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu nhâp không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }
}
