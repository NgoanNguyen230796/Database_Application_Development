package ra.presentation;

import ra.businnes.BillBusiness;
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

public class BillManagement {
    public static final String border = "-";
    public static final int perPage = 10;
    public static final int indexOfPage = 10;
    public static List<Bill> listBill = new ArrayList<>();

    static void displayBillMenu(String employeeId) throws SQLException {
        boolean isExitBillMenu = true;
        do {
            String repeated = new String(new char[67]).replace("\0", border);
            System.out.println(ColorsMenu.GREEN_BOLD_BRIGHT + repeated);
            System.out.println("========================= QUẢN LÝ PHIẾU XUẤT =========================");
            System.out.println("--              1. Danh sách phiếu xuất                             --");
            System.out.println("--              2. Tạo phiếu xuất                                   --");
            System.out.println("--              3. Cập nhật thông tin phiếu xuất                    --");
            System.out.println("--              4. Chi tiết phiếu xuất                              --");
            System.out.println("--              5. Duyệt phiếu xuất                                 --");
            System.out.println("--              6. Tìm kiếm phiếu xuất                              --");
            System.out.println("--              7. Quay lại(WAREHOUSE MANAGEMENT)                   --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceBillMenu = validateChoiceBillMenu();
            switch (choiceBillMenu) {
                case 1:
                    BillManagement.displayDataBill();
                    break;
                case 2:
                    BillManagement.displayDataBill();
                    BillManagement.creatDataBill(sc, employeeId);
                    break;
                case 3:
                    listBill = BillBusiness.getAllDataBillByBillStatusOther2();
                    if (listBill.isEmpty()) {
                        System.out.println("Không có phiếu xuất nào với trạng thái Tạo hoặc Hủy cả");
                    } else {
                        repeated = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated + " *");
                        listBill.forEach(Bill::displayDataBill);
                        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
                        BillManagement.updateDataBill(sc);
                    }

                    break;
                case 4:
                    BillDetailManagement.displayBillDetailForBill();
                    break;
                case 5:
                    listBill = BillBusiness.getAllDataBillByBillStatusOther2();
                    if (listBill.isEmpty()) {
                        System.out.println("Không có phiếu xuất nào với trạng thái Tạo hoặc Hủy cả");
                    } else {
                        repeated = new String(new char[179]).replace("\0", border);
                        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
                        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
                        System.out.println("* " + repeated + " *");
                        listBill.forEach(Bill::displayDataBill);
                        System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
                        BillManagement.approveBill(employeeId);
                    }

                    break;
                case 6:
                    BillManagement.displayDataBill();
                    BillManagement.searchBill(employeeId);
                    break;
                case 7:
                    isExitBillMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-7");
            }
        } while (isExitBillMenu);
    }

    public static int validateChoiceBillMenu() {
        while (true) {
            String inputChoiceBillMenuStr = sc.nextLine().trim();
            if (inputChoiceBillMenuStr.isEmpty()) {
                System.err.println("Lựa chọn các mục trong BILL MANAGEMENT không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceBillMenu = Integer.parseInt(inputChoiceBillMenuStr);
                    if (choiceBillMenu > 0) {
                        return choiceBillMenu;
                    } else {
                        System.err.println("Lựa chọn các mục trong BILL MANAGEMENT phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn các mục trong BILL MANAGEMENT phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static void displayDataBill() throws SQLException {
        listBill = BillBusiness.getAllDataBill();
        if (listBill.isEmpty()) {
            System.out.println("Không có phiếu xuất nào cả");
        } else {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            listBill.forEach(Bill::displayDataBill);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
        }
    }

    public static void creatDataBill(Scanner sc, String employeeId) throws SQLException {
        System.out.print("Nhập số lượng phiếu xuất mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng phiếu xuất mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào phiếu xuất thứ " + (i + 1) + ":");
                            Bill billNew = new Bill();
                            billNew.inputDataBillForBill(employeeId);
                            boolean result = ReceiptBusiness.creatDataReceipt(billNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công phiếu xuất" + ColorsMenu.ANSI_RESET);
                                long billIdNow = ReceiptBusiness.maxIndex();
                                BillDetailManagement.creatDataBillDetailForBill(sc, billIdNow);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công phiếu xuất thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                                long billIdNow = ReceiptBusiness.maxIndex();
                                BillDetailManagement.creatDataBillDetailForBill(sc, billIdNow);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng phiếu xuất mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng phiếu xuất mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }

    public static void updateDataBill(Scanner sc) throws SQLException {
        boolean isExitUpdateDataBillMenu = true;
        boolean result;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu xuất cần cập nhật :");
        String billUpdate = Bill.inputReceiptUpdate();
        Bill bil = BillBusiness.getBillByBillIdOrBillCode(billUpdate);
        updateUser(bil, billUpdate, isExitUpdateDataBillMenu);
    }
    public static void updateDataBillUser(Scanner sc) throws SQLException {
        boolean isExitUpdateDataBillMenu = true;
        boolean result;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu xuất cần cập nhật :");
        String billUpdate = Bill.inputReceiptUpdate();
        Bill bil = BillBusiness.getBillByBillIdOrBillCode(billUpdate);
        updateUser(bil, billUpdate, isExitUpdateDataBillMenu);
    }

    private static void update(Bill bil, String billUpdate, boolean isExitUpdateDataBillMenu) throws SQLException {
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
                        System.out.println("--              6.Cập nhật cả chi tiết phiếu nhập của phiếu xuất   --");
                        System.out.println("--              7.Thoát khỏi cập thông tin nhập phiếu xuất         --");
                        System.out.println(repeated + ColorsMenu.ANSI_RESET);
                        System.out.println("Lựa chọn của bạn là :");
                        int choiceUpdate = validateChoiceUpdateBill();
                        switch (choiceUpdate) {
                            case 1:
                                System.out.println("Bạn muốn thay đổi mã nhân viên nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Created() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setEmp_Id_Created(Bill.inputEmpIdCreated());
                                result = BillBusiness.updateDataBillForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 2:
                                System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getCreated_Bill() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setCreated_Bill(Bill.inputCreatedBill());
                                result = BillBusiness.updateDataBillForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 3:
                                System.out.println("Bạn muốn thay đổi mã nhân viên duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Auth() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setEmp_Id_Auth(Bill.inputEmpIdAuth());
                                result = BillBusiness.updateDataBillForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công mã nhân viên duyệt" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 4:
                                System.out.print("Bạn muốn thay đổi ngày duyệt" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getAuth_Date() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setAuth_Date(Bill.inputAuthDate());
                                result = BillBusiness.updateDataBillForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công ngày duyệt" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 5:
                                String StatusValue = bil.getBill_Status() == 0 ? "Tạo" : bil.getBill_Status() == 1 ? "Hủy" : "Duyệt";
                                System.out.println("Bạn muốn thay đổi trạng thái" + " từ " + "[" + ColorsMenu.RED_BOLD + StatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setBill_Status(Bill.inputBillStatusUpdate());
                                result = BillBusiness.updateDataBillForBill(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công trạng thái" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 6:
                                BillManagement.updateBillDetailBill(bil);
                                break;
                            case 7:
                                isExitUpdateDataBillMenu = false;
                                break;
                            default:
                                System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-7");
                        }
                    }
                } while (isExitUpdateDataBillMenu);
            } else {
                System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc chỉ được cập nhật thông tin phiếu xuất khi trạng thái đang ở trạng thái Tạo hoặc Hủy" + ColorsMenu.ANSI_RESET);
            }
        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu xuất không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }

    private static void updateUser(Bill bil, String billUpdate, boolean isExitUpdateDataBillMenu) throws SQLException {
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
                        System.out.println("--              4.Cập nhật cả chi tiết phiếu nhập của phiếu xuất   --");
                        System.out.println("--              5.Thoát khỏi cập thông tin nhập phiếu xuất         --");
                        System.out.println(repeated + ColorsMenu.ANSI_RESET);
                        System.out.println("Lựa chọn của bạn là :");
                        int choiceUpdate = validateChoiceUpdateBill();
                        switch (choiceUpdate) {
                            case 1:
                                System.out.println("Bạn muốn thay đổi mã nhân viên nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getEmp_Id_Created() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setEmp_Id_Created(Bill.inputEmpIdCreated());
                                result = BillBusiness.updateDataBillForBillUser(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 2:
                                System.out.print("Bạn muốn thay đổi ngày tạo" + " từ " + "[" + ColorsMenu.RED_BOLD + bil.getCreated_Bill() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setCreated_Bill(Bill.inputCreatedBill());
                                result = BillBusiness.updateDataBillForBillUser(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công ngày tạo" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;

                            case 3:
                                String StatusValue = bil.getBill_Status() == 0 ? "Tạo" : bil.getBill_Status() == 1 ? "Hủy" : "Duyệt";
                                System.out.println("Bạn muốn thay đổi trạng thái" + " từ " + "[" + ColorsMenu.RED_BOLD + StatusValue + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                                bil.setBill_Status(Bill.inputBillStatusUpdate());
                                result = BillBusiness.updateDataBillForBillUser(bil);
                                if (result) {
                                    System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công trạng thái" + ColorsMenu.ANSI_RESET);
                                } else {
                                    System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                                }
                                break;
                            case 4:
                                BillManagement.updateBillDetailBill(bil);
                                break;
                            case 5:
                                isExitUpdateDataBillMenu = false;
                                break;
                            default:
                                System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-5");
                        }
                    }
                } while (isExitUpdateDataBillMenu);
            } else {
                System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc chỉ được cập nhật thông tin phiếu xuất khi trạng thái đang ở trạng thái Tạo hoặc Hủy" + ColorsMenu.ANSI_RESET);
            }
        } else {
            //Mã nhân viên k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu xuất không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }

    public static int validateChoiceUpdateBill() {
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

    public static void approveBill(String employeeId) throws SQLException {
        boolean isCheckUpdateBillDetail = true;
        boolean isCheckUpdateBillDetailOneMoreTime = true;
        ReceiptManagement.displayDataReceiptBillStatus0();
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu xuất cần duyệt :");
        String billUpdate = Bill.inputReceiptUpdate();
        Bill bil = null;
        bil = BillBusiness.getBillByBillIdOrBillCodeAndBillStatus0(billUpdate);
        approve(bil, isCheckUpdateBillDetail, billUpdate, employeeId);
    }

    private static void approve(Bill bil, boolean isCheckUpdateBillDetail, String billUpdate, String employeeId) throws SQLException {
        if (bil != null) {
            while (isCheckUpdateBillDetail) {
                System.out.println("Bạn có muốn duyệt không ?");
                System.out.println("1.Có");
                System.out.println("2.Không");
                System.out.println("Nhập vào lựa chọn của bạn :");
                int choice = validateChoiceUpdateBillDetail();
                switch (choice) {
                    case 1:
                        boolean result = false;
                        Bill billNew = new Bill();
                        billNew.inputDataReceiptAuth(bil.getBill_Id(), employeeId);
                        BillBusiness.updateDataApproveBill(billNew);
//                        if (result) {
//                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billUpdate + " vừa được cập nhật thành công trạng thái từ" + ColorsMenu.YELLOW_BOLD + " Tạo " + ColorsMenu.ANSI_RESET + "sang" + ColorsMenu.GREEN_BOLD + " Duyệt " + ColorsMenu.ANSI_RESET + ColorsMenu.ANSI_RESET);
//                        } else {
//                            System.out.println("Không đủ số lượng tồn kho để xuất hàng,vui lòng thực hiện lại");
//                        }
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
            //Mã phiếu hoặc mã code k tồn tại trong CSDL
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu xuất không tồn tại hoặc là trạng thái phiếu xuất là Huỷ" + ColorsMenu.ANSI_RESET);
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

    public static void updateBillDetailBill(Bill bil) throws SQLException {
        boolean isCheckUpdateBillDetail = true;
        while (isCheckUpdateBillDetail) {
            BillDetailManagement.displayBillDetail(bil);
            System.out.println("Phiếu xuất có cả chi tiết phiếu xuất ,bạn có muốn cập nhật không ?");
            System.out.println("1.Có");
            System.out.println("2.Không");
            System.out.println("Nhập vào lựa chọn của bạn :");
            int choice = validateChoiceUpdateBillDetail();
            switch (choice) {
                case 1:
                    BillDetailManagement.updateBillDetailForBill();
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

    public static void searchBill(String employeeId) throws SQLException {
        boolean isCheckSearch = true;
        Bill bil = null;
        boolean isExitUpdateDataBillMenu = true;
        boolean result = false;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu xuất cần cập nhật :");
        String billUpdate = Bill.inputReceiptUpdate();
        bil = BillBusiness.getBillByBillIdOrBillCode(billUpdate);
        if (bil != null) {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            List<Bill> listBill = new ArrayList<>();
            listBill.add(bil);
            listBill.forEach(Bill::displayDataBill);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
            String repeated2 = new String(new char[69]).replace("\0", border);
            System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
            System.out.println("=========      Vui lòng lựa chọn danh mục                   =========");
            System.out.println("--              1.Cập nhật phiếu xuất                              --");
            System.out.println("--              2.Duyệt phiếu xuất                                 --");
            System.out.println("--              3.Thoát khỏi tìm kiếm phiếu xuất                   --");
            System.out.println(repeated2 + ColorsMenu.ANSI_RESET);
            System.out.println("Lựa chọn của bạn là :");
            int choiceUpdate = validateChoiceUpdateBill();
            while (isCheckSearch) {
                switch (choiceUpdate) {
                    case 1:
                        if (bil.getBill_Status() != 2) {
                            updateUser(bil, billUpdate, isCheckSearch);
                        } else {
                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu xuất này đã được duyệt không thể cập nhật được" + ColorsMenu.ANSI_RESET);
                        }
                        isCheckSearch = false;
                        break;
                    case 2:
                        if (bil.getBill_Status() != 2) {
                            approve(bil, isCheckSearch, billUpdate, employeeId);
                        } else {
                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu xuất này đang ở chế độ duyệt rồi" + ColorsMenu.ANSI_RESET);
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

        } else {
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu xuất không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }

    public static void searchBillUser(String employeeId) throws SQLException {
        boolean isCheckSearch = true;
        Bill bil = null;
        boolean isExitUpdateDataBillMenu = true;
        boolean result = false;
        System.out.println("Nhập vào mã phiếu hoặc mã code của phiếu xuất cần cập nhật :");
        String billUpdate = Bill.inputReceiptUpdate();
        bil = BillBusiness.getBillByBillIdOrBillCode(billUpdate);
        if (bil != null) {
            String repeated = new String(new char[179]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n", "Mã phiếu", "Mã code", "Loại phiếu", "Mã nhân viên nhập", "Ngày tạo", "Mã nhân viên duyệt", "Ngày duyệt", "Trạng thái");
            System.out.println("* " + repeated + " *");
            List<Bill> listBill = new ArrayList<>();
            listBill.add(bil);
            listBill.forEach(Bill::displayDataBill);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
            System.out.println("* " + repeated + " *" + ColorsMenu.ANSI_RESET);
            String repeated2 = new String(new char[69]).replace("\0", border);
            System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
            System.out.println("=========      Vui lòng lựa chọn danh mục                   =========");
            System.out.println("--              1.Cập nhật phiếu xuất                              --");
//            System.out.println("--              2.Duyệt phiếu xuất                                 --");
            System.out.println("--              2.Thoát khỏi tìm kiếm phiếu xuất                   --");
            System.out.println(repeated2 + ColorsMenu.ANSI_RESET);
            System.out.println("Lựa chọn của bạn là :");
            int choiceUpdate = validateChoiceUpdateBill();
            while (isCheckSearch) {
                switch (choiceUpdate) {
                    case 1:
                        if (bil.getBill_Status() != 2) {
                            updateUser(bil, billUpdate, isCheckSearch);
                        } else {
                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu xuất này đã được duyệt không thể cập nhật được" + ColorsMenu.ANSI_RESET);
                        }
                        isCheckSearch = false;
                        break;
//                    case 2:
//                        if (bil.getBill_Status() != 2) {
//                            approve(bil, isCheckSearch, billUpdate,employeeId);
//                        } else {
//                            System.out.println(ColorsMenu.RED_BOLD + "Rất tiếc phiếu xuất này đang ở chế độ duyệt rồi" + ColorsMenu.ANSI_RESET);
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

        } else {
            System.out.println(ColorsMenu.RED_BOLD + "Mã phiếu hoặc mã code của phiếu xuất không tồn tại" + ColorsMenu.ANSI_RESET);
        }
    }


}
