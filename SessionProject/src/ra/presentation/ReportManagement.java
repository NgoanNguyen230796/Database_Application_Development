package ra.presentation;

import ra.businnes.ReportBusiness;
import ra.colors.ColorsMenu;

import java.sql.SQLException;

import static ra.presentation.MainManagement.sc;

public class ReportManagement {
    public static final String border = "-";

    static void displayReportMenu() throws SQLException {
        boolean isExitBillMenu = true;
        do {
            String repeated = new String(new char[82]).replace("\0", border);
            System.out.println(ColorsMenu.GREEN_BOLD_BRIGHT + repeated);
            System.out.println("============================== QUẢN LÝ PHIẾU XUẤT  ===============================");
            System.out.println("--              1. Thống kê chi phí theo ngày, tháng, năm                       --");
            System.out.println("--              2. Thống kê chi phí theo khoảng thời gian                       --");
            System.out.println("--              3. Thống kê doanh thu theo ngày, tháng, năm                     --");
            System.out.println("--              4. Thống kê doanh thu theo khoảng thời gian                     --");
            System.out.println("--              5. Thống kê số nhân viên theo từng trạng thái                   --");
            System.out.println("--              6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian     --");
            System.out.println("--              7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian        --");
            System.out.println("--              8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian     --");
            System.out.println("--              9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian        --");
            System.out.println("--              10. Quay lại(WAREHOUSE MANAGEMENT)                              --");
            System.out.println(repeated + ColorsMenu.ANSI_RESET);
            System.out.print("Lựa chọn của bạn là :");
            int choiceBillMenu = validateChoiceBillMenu();
            switch (choiceBillMenu) {
                case 1:
                    ReportManagement.statisticsExpenseReceiptByDayMonthYear();
                    break;
                case 2:
                    ReportManagement.statisticsExpenseReceiptByMonthYear();
                    break;
                case 3:
                    ReportManagement.statisticsRevenueBillByDayMonthYear();
                    break;
                case 4:
                    ReportManagement.statisticsRevenueBillByMonthYear();
                    break;
                case 5:
                    ReportManagement.statisticsEmployeeByEmpStatus();
                    break;
                case 6:
                    ReportManagement.statisticsMaxReceiptProductByMonthYear();
                    break;
                case 7:
                    ReportManagement.statisticsMinReceiptProductByMonthYear();
                    break;
                case 8:
                    ReportManagement.statisticsMaxBillProductByMonthYear();
                    break;
                case 9:
                    ReportManagement.statisticsMinBillProductByMonthYear();
                    break;
                case 10:
                    isExitBillMenu = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-10");
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

    public static void statisticsExpenseReceiptByDayMonthYear() throws SQLException {
        System.out.println("Thống kê chi phí theo ngày, tháng, năm là :");
        String repeated = new String(new char[43]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-20s |\n", "Thời gian", "Chi phí");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsExpenseReceiptByDayMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsExpenseReceiptByMonthYear() throws SQLException {
        System.out.println("Thống kê chi phí theo khoảng thời gian là :");
        String repeated = new String(new char[43]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-20s |\n", "Thời gian", "Chi phí");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsExpenseReceiptByMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsRevenueBillByDayMonthYear() throws SQLException {
        System.out.println("Thống kê doanh thu theo ngày, tháng, năm là :");
        String repeated = new String(new char[43]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-20s |\n", "Thời gian", "Doanh thu");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsRevenueBillByDayMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsRevenueBillByMonthYear() throws SQLException {
        System.out.println("Thống kê doanh thu theo khoảng thời gian là :");
        String repeated = new String(new char[43]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-20s |\n", "Thời gian", "Doanh thu");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsRevenueBillByMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsEmployeeByEmpStatus() throws SQLException {
        System.out.println("Thống kê số nhân viên theo từng trạng thái là :");
        String repeated = new String(new char[43]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-20s |\n", "Trạng thái", "Số lượng nhân viên");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsEmployeeByEmpStatus();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsMaxReceiptProductByMonthYear() throws SQLException {
        System.out.println("Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian là :");
        String repeated = new String(new char[81]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm", "Số lượng nhập nhiều nhất");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsMaxReceiptProductByMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsMinReceiptProductByMonthYear() throws SQLException {
        System.out.println("Thống kê sản phẩm nhập ít nhất trong khoảng thời gian là :");
        String repeated = new String(new char[81]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm", "Số lượng nhập ít nhất");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsMinReceiptProductByMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsMaxBillProductByMonthYear() throws SQLException {
        System.out.println("Thống kê sản phẩm nhập ít nhất trong khoảng thời gian là :");
        String repeated = new String(new char[81]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm", "Số lượng nhập ít nhất");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsMaxBillProductByMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    public static void statisticsMinBillProductByMonthYear() throws SQLException {
        System.out.println("Thống kê sản phẩm nhập ít nhất trong khoảng thời gian là :");
        String repeated = new String(new char[81]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm", "Số lượng nhập ít nhất");
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        ReportBusiness.statisticsMinBillProductByMonthYear();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }


}
