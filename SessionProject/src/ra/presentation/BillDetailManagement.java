package ra.presentation;

import ra.businnes.BillDetailBusiness;
import ra.colors.ColorsMenu;
import ra.entity.Bill;
import ra.entity.Bill_Detail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.MainManagement.sc;

public class BillDetailManagement {
    public static final String border = "-";
    public static List<Bill_Detail> listBillDetail = new ArrayList<>();

    public static void displayBillDetail(Bill bil) throws SQLException {
        listBillDetail = BillDetailBusiness.getAllDataBillDetailByReceipt(bil.getBill_Id());
        String repeated = new String(new char[127]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-15s | %-30s | %-20s |\n", "Mã phiếu chi tiết", "Mã phiếu nhập", "Mã sản phẩm", "Số lượng nhập", "Giá Nhập");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        listBillDetail.forEach(Bill_Detail::displayDataBillDetail);
        System.out.println("* " + repeated + " *");
    }

    public static void displayBillDetailForReceipt() throws SQLException {
        listBillDetail=BillDetailBusiness.getAllDataBillDetail();
        String repeated = new String(new char[127]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-15s | %-30s | %-20s |\n", "Mã phiếu chi tiết", "Mã phiếu nhập", "Mã sản phẩm", "Số lượng nhập", "Giá Nhập");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        listBillDetail.forEach(Bill_Detail::displayDataBillDetail);
        System.out.println("* " + repeated + " *");
    }
    public static void displayBillDetailForBill() throws SQLException {
        listBillDetail=BillDetailBusiness.getAllDataBill();
        String repeated = new String(new char[127]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-15s | %-30s | %-20s |\n", "Mã phiếu chi tiết", "Mã phiếu nhập", "Mã sản phẩm", "Số lượng nhập", "Giá Nhập");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        listBillDetail.forEach(Bill_Detail::displayDataBillDetail);
        System.out.println("* " + repeated + " *");
    }

    public static void creatDataBillDetail(Scanner sc, long billDetailId) throws SQLException {
        System.out.print("Nhập số lượng Bill Detail mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng Bill Detail mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào chi tiết phiếu nhập thứ " + (i + 1) + ":");
                            Bill_Detail billDetailNew = new Bill_Detail();
                            billDetailNew.inputDataBillDetail(billDetailId);
                            boolean result = BillDetailBusiness.creatDataBillDetail(billDetailNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công Bill Detail" + ColorsMenu.ANSI_RESET);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công Bill Detail thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng Bill Detail mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng Bill Detail mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }
    public static void creatDataBillDetailForBill(Scanner sc, long billDetailId) throws SQLException {
        System.out.print("Nhập số lượng Bill Detail mà bạn muốn thêm :");
        while (true) {
            String inputNumberStr = sc.nextLine().trim();
            if (inputNumberStr.isEmpty()) {
                System.err.println("Số lượng Bill Detail mà bạn muốn thêm không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int number = Integer.parseInt(inputNumberStr);
                    if (number > 0) {
                        for (int i = 0; i < number; i++) {
                            System.out.println("Nhập vào chi tiết phiếu xuất thứ " + (i + 1) + ":");
                            Bill_Detail billDetailNew = new Bill_Detail();
                            billDetailNew.inputDataBillDetail(billDetailId);
                            boolean result = BillDetailBusiness.creatDataBillDetail(billDetailNew);
                            if (result && number == 1) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công Bill Detail" + ColorsMenu.ANSI_RESET);
                            } else if (result) {
                                System.out.println(ColorsMenu.GREEN_BOLD + "Thêm mới thành công Bill Detail thứ " + (i + 1) + ColorsMenu.ANSI_RESET);
                            } else {
                                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                            }
                        }
                        break;
                    } else {
                        System.err.println("Số lượng Bill Detail mà bạn muốn thêm phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Số lượng Bill Detail mà bạn muốn thêm phải là số,vui lòng nhập lại");
                } catch (Exception ex3) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }
    public static void updateBillDetailForReceipt() throws SQLException {
        boolean isExitUpdateDataReceiptMenu = true;
        boolean result;
        System.out.println("Nhập vào mã phiếu chi tiết mà bạn cần cập nhập thông tin");
        while (isExitUpdateDataReceiptMenu) {
            long billDetailId = Bill_Detail.inputBillDetailId();
            Bill_Detail billDetail = BillDetailBusiness.getAllDataBillDetailByBillDetailIdAnd_bill_Type1(billDetailId);
            if (billDetail == null) {
                System.out.println(ColorsMenu.RED_BOLD+"Có vẻ bạn nhập nhầm mã phiếu chi tiết rồi,vui lòng nhập lại"+ColorsMenu.ANSI_RESET);
            } else {
                String repeated = new String(new char[69]).replace("\0", border);
                System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
                System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin ==========");
//                System.out.println("--              1.Mã phiếu nhập                                     --");
                System.out.println("--              1.Mã sản phẩm                                       --");
                System.out.println("--              2.Số lượng nhập                                     --");
                System.out.println("--              3.Giá Nhập                                          --");
                System.out.println("--              4.Thoát khỏi cập thông tin chi tiết nhập phiếu nhâp --");
                System.out.println(repeated + ColorsMenu.ANSI_RESET);
                System.out.println("Lựa chọn của bạn là :");
                int choiceUpdate = validateChoiceUpdateReceipt();
                switch (choiceUpdate) {
//                    case 1:
//                        System.out.println("Bạn muốn thay đổi mã phiếu nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getBill_Id() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                        billDetail.setBill_Id(Bill_Detail.inputBillDetailId());
//                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
//                        if (result) {
//                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
//                        } else {
//                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                        }
//                        isExitUpdateDataReceiptMenu = false;
//                        break;
                    case 1:
                        System.out.println("Bạn muốn thay đổi mã sản phẩm" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getProduct_Id() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        billDetail.setProduct_Id(Bill_Detail.inputProductId());
                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billDetailId + " vừa được cập nhật thành công mã sản phẩm" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    case 2:
                        System.out.println("Bạn muốn thay đổi số lượng" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getQuantity() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        billDetail.setQuantity(Bill_Detail.inputQuantity());
                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billDetailId + " vừa được cập nhật thành công số lượng" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    case 3:
                        System.out.print("Bạn muốn thay đổi giá nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getPrice() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        billDetail.setPrice(Bill_Detail.inputPrice());
                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billDetailId + " vừa được cập nhật thành công giá nhập" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    case 4:
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    default:
                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");
                }

            }
        }
    }
    public static void updateBillDetailForBill() throws SQLException {
        boolean isExitUpdateDataReceiptMenu = true;
        boolean result;
        System.out.println("Nhập vào mã phiếu chi tiết mà bạn cần cập nhập thông tin");
        while (isExitUpdateDataReceiptMenu) {
            long billDetailId = Bill_Detail.inputBillDetailId();
            Bill_Detail billDetail = BillDetailBusiness.getAllDataBillDetailByBillDetailIdAnd_bill_Type0(billDetailId);
            if (billDetail == null) {
                System.out.println(ColorsMenu.RED_BOLD+"Có vẻ bạn nhập nhầm mã phiếu chi tiết rồi,vui lòng nhập lại"+ColorsMenu.ANSI_RESET);
            } else {
                String repeated = new String(new char[69]).replace("\0", border);
                System.out.println(ColorsMenu.PURPLE_BOLD + repeated);
                System.out.println("========= Vui lòng lựa chọn danh mục cần cập nhật thông tin ==========");
//                System.out.println("--              1.Mã phiếu nhập                                     --");
                System.out.println("--              1.Mã sản phẩm                                       --");
                System.out.println("--              2.Số lượng xuất                                     --");
                System.out.println("--              3.Giá xuất                                          --");
                System.out.println("--              4.Thoát khỏi cập thông tin chi tiết nhập phiếu xuất --");
                System.out.println(repeated + ColorsMenu.ANSI_RESET);
                System.out.println("Lựa chọn của bạn là :");
                int choiceUpdate = validateChoiceUpdateReceipt();
                switch (choiceUpdate) {
//                    case 1:
//                        System.out.println("Bạn muốn thay đổi mã phiếu nhập" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getBill_Id() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
//                        billDetail.setBill_Id(Bill_Detail.inputBillDetailId());
//                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
//                        if (result) {
//                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + receiptUpdate + " vừa được cập nhật thành công mã nhân viên nhập" + ColorsMenu.ANSI_RESET);
//                        } else {
//                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
//                        }
//                        isExitUpdateDataReceiptMenu = false;
//                        break;
                    case 1:
                        System.out.println("Bạn muốn thay đổi mã sản phẩm" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getProduct_Id() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        billDetail.setProduct_Id(Bill_Detail.inputProductId());
                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billDetailId + " vừa được cập nhật thành công mã sản phẩm" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    case 2:
                        System.out.println("Bạn muốn thay đổi số lượng xuất" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getQuantity() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        billDetail.setQuantity(Bill_Detail.inputQuantity());
                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billDetailId + " vừa được cập nhật thành công số lượng xuất" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    case 3:
                        System.out.print("Bạn muốn thay đổi giá xuất" + " từ " + "[" + ColorsMenu.RED_BOLD + billDetail.getPrice() + ColorsMenu.ANSI_RESET + "]" + " thành : ");
                        billDetail.setPrice(Bill_Detail.inputPrice());
                        result = BillDetailBusiness.updateDataBillDetail(billDetail);
                        if (result) {
                            System.out.println(ColorsMenu.GREEN_BOLD + "Mã :" + billDetailId + " vừa được cập nhật thành công giá xuất" + ColorsMenu.ANSI_RESET);
                        } else {
                            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
                        }
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    case 4:
                        isExitUpdateDataReceiptMenu = false;
                        break;
                    default:
                        System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-4");
                }

            }
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
}
