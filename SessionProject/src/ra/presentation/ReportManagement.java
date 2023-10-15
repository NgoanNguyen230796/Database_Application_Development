package ra.presentation;

import ra.businnes.ReportBusiness;
import ra.colors.ColorsMenu;
import ra.entity.StatisticsForBill;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
                    ReportManagement.statisticsExpenseReceiptByInterval();
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

    //1. Thống kê chi phí theo ngày, tháng, năm
    public static void statisticsExpenseReceiptByDayMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào ngày tháng năm mà bạn muốn thống kê chi phí :" + ColorsMenu.ANSI_RESET);
        String inputDate = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsExpenseReceiptByDayMonthYear(inputDate);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có chi phí theo " + inputDate + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê chi phí theo:[" + ColorsMenu.PURPLE_BOLD + inputDate + ColorsMenu.ANSI_RESET + "]");
            String repeated = new String(new char[43]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-20s |\n", "Thời gian", "Chi phí");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listStatistics.forEach(StatisticsForBill::displayStatisticsValuesBill);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    //2. Thống kê chi phí theo khoảng thời gian
    public static void statisticsExpenseReceiptByInterval() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào khoảng thời gian từ :" + ColorsMenu.ANSI_RESET);
        String inputDateFrom = validateInputDate();
        System.out.println(ColorsMenu.PURPLE_BOLD + "Nhập vào khoảng thời gian đến :" + ColorsMenu.ANSI_RESET);
        String inputDateTo = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsExpenseReceiptByInterval(inputDateFrom, inputDateTo);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có chi phí nào từ  " + inputDateFrom + " đến " + inputDateTo + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê chi phí theo khoảng thời gian từ:" + "[" + ColorsMenu.YELLOW_BOLD + inputDateFrom + ColorsMenu.ANSI_RESET + "]" + " đến " + "[" + ColorsMenu.YELLOW_BOLD + inputDateTo + ColorsMenu.ANSI_RESET + "]" + " là :");
            String repeated = new String(new char[66]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-20s | %-20s | \n", "Thời gian từ", "Thời gian đến", "Chi phí");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-20s | %-20s | \n", inputDateFrom, inputDateTo, listStatistics.get(0).getStatisticsValues());
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }

    }

    //3. Thống kê doanh thu theo ngày, tháng, năm
    public static void statisticsRevenueBillByDayMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào ngày tháng năm mà bạn muốn thống kê doanh thu :" + ColorsMenu.ANSI_RESET);
        String inputDate = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsRevenueBillByDayMonthYear(inputDate);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có doanh thu theo " + inputDate + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê doanh thu theo:[" + ColorsMenu.PURPLE_BOLD + inputDate + ColorsMenu.ANSI_RESET + "]");
            String repeated = new String(new char[43]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-20s |\n", "Thời gian", "Chi phí");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listStatistics.forEach(StatisticsForBill::displayStatisticsValuesBill);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    //4. Thống kê doanh thu theo khoảng thời gian
    public static void statisticsRevenueBillByMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào khoảng thời gian từ :" + ColorsMenu.ANSI_RESET);
        String inputDateFrom = validateInputDate();
        System.out.println(ColorsMenu.PURPLE_BOLD + "Nhập vào khoảng thời gian đến :" + ColorsMenu.ANSI_RESET);
        String inputDateTo = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsRevenueBillByMonthYear(inputDateFrom, inputDateTo);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có chi phí nào từ  " + inputDateFrom + " đến " + inputDateTo + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê chi phí theo khoảng thời gian từ:" + "[" + ColorsMenu.YELLOW_BOLD + inputDateFrom + ColorsMenu.ANSI_RESET + "]" + " đến " + "[" + ColorsMenu.YELLOW_BOLD + inputDateTo + ColorsMenu.ANSI_RESET + "]" + " là :");
            String repeated = new String(new char[66]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-20s | %-20s | \n", "Thời gian từ", "Thời gian đến", "Chi phí");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-20s | %-20s | \n", inputDateFrom, inputDateTo, listStatistics.get(0).getStatisticsValues());
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    //5. Thống kê số nhân viên theo từng trạng thái
    public static void statisticsEmployeeByEmpStatus() throws SQLException {
        System.out.println("Thống kê số nhân viên theo từng trạng thái là :");
        String repeated = new String(new char[43]).replace("\0", border);
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        System.out.printf("| %-20s | %-20s |\n", "Trạng thái", "Số lượng nhân viên");
        ReportBusiness.statisticsEmployeeByEmpStatus();
        System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
    }

    //6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian
    public static void statisticsMaxReceiptProductByMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào khoảng thời gian từ :" + ColorsMenu.ANSI_RESET);
        String inputDateFrom = validateInputDate();
        System.out.println(ColorsMenu.PURPLE_BOLD + "Nhập vào khoảng thời gian đến :" + ColorsMenu.ANSI_RESET);
        String inputDateTo = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsMaxReceiptProductByMonthYear(inputDateFrom, inputDateTo);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có sản phẩm nào nhập nhiều nhất trong khoảng thời gian từ" + inputDateFrom + " đến " + inputDateTo + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian từ:" + "[" + ColorsMenu.YELLOW_BOLD + inputDateFrom + ColorsMenu.ANSI_RESET + "]" + " đến " + "[" + ColorsMenu.YELLOW_BOLD + inputDateTo + ColorsMenu.ANSI_RESET + "]" + " là :");
            String repeated = new String(new char[81]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm nhập nhiều nhất", "Số lượng");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listStatistics.forEach(StatisticsForBill::statisticsMaxAndMinReceiptProductByMonthYear);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    //7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian
    public static void statisticsMinReceiptProductByMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào khoảng thời gian từ :" + ColorsMenu.ANSI_RESET);
        String inputDateFrom = validateInputDate();
        System.out.println(ColorsMenu.PURPLE_BOLD + "Nhập vào khoảng thời gian đến :" + ColorsMenu.ANSI_RESET);
        String inputDateTo = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsMinReceiptProductByMonthYear(inputDateFrom, inputDateTo);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có sản phẩm nào nhập nhiều nhất trong khoảng thời gian từ" + inputDateFrom + " đến " + inputDateTo + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê sản phẩm nhập ít nhất trong khoảng thời gian từ:" + "[" + ColorsMenu.YELLOW_BOLD + inputDateFrom + ColorsMenu.ANSI_RESET + "]" + " đến " + "[" + ColorsMenu.YELLOW_BOLD + inputDateTo + ColorsMenu.ANSI_RESET + "]" + " là :");
            String repeated = new String(new char[81]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm nhập ít nhất", "Số lượng");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listStatistics.forEach(StatisticsForBill::statisticsMaxAndMinReceiptProductByMonthYear);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    //8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian
    public static void statisticsMaxBillProductByMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào khoảng thời gian từ :" + ColorsMenu.ANSI_RESET);
        String inputDateFrom = validateInputDate();
        System.out.println(ColorsMenu.PURPLE_BOLD + "Nhập vào khoảng thời gian đến :" + ColorsMenu.ANSI_RESET);
        String inputDateTo = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsMaxBillProductByMonthYear(inputDateFrom, inputDateTo);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có sản phẩm nào xuất nhiều nhất trong khoảng thời gian từ　" + inputDateFrom + " đến " + inputDateTo + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian từ:" + "[" + ColorsMenu.YELLOW_BOLD + inputDateFrom + ColorsMenu.ANSI_RESET + "]" + " đến " + "[" + ColorsMenu.YELLOW_BOLD + inputDateTo + ColorsMenu.ANSI_RESET + "]" + " là :");

            String repeated = new String(new char[81]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm xuất nhiều nhất", "Số lượng");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listStatistics.forEach(StatisticsForBill::statisticsMaxAndMinReceiptProductByMonthYear);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    //9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian
    public static void statisticsMinBillProductByMonthYear() throws SQLException {
        System.out.println(ColorsMenu.YELLOW_BOLD + "Nhập vào khoảng thời gian từ :" + ColorsMenu.ANSI_RESET);
        String inputDateFrom = validateInputDate();
        System.out.println(ColorsMenu.PURPLE_BOLD + "Nhập vào khoảng thời gian đến :" + ColorsMenu.ANSI_RESET);
        String inputDateTo = validateInputDate();
        List<StatisticsForBill> listStatistics = ReportBusiness.statisticsMinBillProductByMonthYear(inputDateFrom, inputDateTo);
        if (listStatistics.isEmpty()) {
            System.out.println(ColorsMenu.RED_BOLD + "Không có sản phẩm nào xuất ít nhất trong khoảng thời gian từ　" + inputDateFrom + " đến " + inputDateTo + " mà bạn vừa nhập" + ColorsMenu.ANSI_RESET);
        } else {
            System.out.println("Thống kê sản phẩm xuất ít nhất trong khoảng thời gian từ:" + "[" + ColorsMenu.YELLOW_BOLD + inputDateFrom + ColorsMenu.ANSI_RESET + "]" + " đến " + "[" + ColorsMenu.YELLOW_BOLD + inputDateTo + ColorsMenu.ANSI_RESET + "]" + " là :");
            String repeated = new String(new char[81]).replace("\0", border);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            System.out.printf("| %-20s | %-30s | %-25s |\n", "Thời gian", "Tên sản phẩm xuất ít nhất", "Số lượng");
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
            listStatistics.forEach(StatisticsForBill::statisticsMaxAndMinReceiptProductByMonthYear);
            System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " *");
        }
    }

    public static String validateInputDate() {
        while (true) {
            String validateInputDateStr = sc.nextLine().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            if (validateInputDateStr.isEmpty()) {
                System.err.println("Ngày mà bạn nhập vào không được để trống, vui lòng nhập lại");
            } else {
                try {
                    sdf.parse(validateInputDateStr);
                    return validateInputDateStr;
                } catch (ParseException ex) {
                    System.err.println("Ngày mà bạn nhập vào không đúng định dạng yyyy-MM-dd, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

}
