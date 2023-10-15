package ra.entity;

import ra.businnes.EmployeeBusiness;
import ra.businnes.ReceiptBusiness;
import ra.colors.ColorsMenu;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static ra.presentation.MainManagement.sc;

public class Bill {
    public static final String border = "-";
    private long bill_Id;
    private String bill_Code;
    private Boolean bill_Type;
    private String emp_Id_Created;
    private String created_Bill;
    private String emp_Id_Auth;
    private String auth_Date;
    private short bill_Status;

    public Bill() {
    }

    public Bill(long bill_Id, String bill_Code, Boolean bill_Type, String emp_Id_Created, String created_Bill, String emp_Id_Auth, String auth_Date, short bill_Status) {
        this.bill_Id = bill_Id;
        this.bill_Code = bill_Code;
        this.bill_Type = bill_Type;
        this.emp_Id_Created = emp_Id_Created;
        this.created_Bill = created_Bill;
        this.emp_Id_Auth = emp_Id_Auth;
        this.auth_Date = auth_Date;
        this.bill_Status = bill_Status;
    }

    public long getBill_Id() {
        return bill_Id;
    }

    public void setBill_Id(long bill_Id) {
        this.bill_Id = bill_Id;
    }

    public String getBill_Code() {
        return bill_Code;
    }

    public void setBill_Code(String bill_Code) {
        this.bill_Code = bill_Code;
    }

    public Boolean getBill_Type() {
        return bill_Type;
    }

    public void setBill_Type(Boolean bill_Type) {
        this.bill_Type = bill_Type;
    }

    public String getEmp_Id_Created() {
        return emp_Id_Created;
    }

    public void setEmp_Id_Created(String emp_Id_Created) {
        this.emp_Id_Created = emp_Id_Created;
    }

    public String getCreated_Bill() {
        return created_Bill;
    }

    public void setCreated_Bill(String created_Bill) {
        this.created_Bill = created_Bill;
    }

    public String getEmp_Id_Auth() {
        return emp_Id_Auth;
    }

    public void setEmp_Id_Auth(String emp_Id_Auth) {
        this.emp_Id_Auth = emp_Id_Auth;
    }

    public String getAuth_Date() {
        return auth_Date;
    }

    public void setAuth_Date(String auth_Date) {
        this.auth_Date = auth_Date;
    }

    public short getBill_Status() {
        return bill_Status;
    }

    public void setBill_Status(short bill_Status) {
        this.bill_Status = bill_Status;
    }

    //Nhập dữ lệu cho phiếu nhập Admin
    public void inputDataReceiptForBillAdmin(String employeeId) throws SQLException {
        System.out.print("Nhập vào mã code = ");
        this.bill_Code = inputBillCodeIsCheckDuplicate();
        this.bill_Type = true;
        //Mã nhân viên nhập/xuất
        this.emp_Id_Created = employeeId;
        this.emp_Id_Auth = null;
        //Ngày duyệt
        this.auth_Date=null;
        System.out.println("Nhập vào trạng thái = ");
        this.bill_Status = inputBillStatus();
    }
    //Nhập dữ lệu cho phiếu nhập User
    public void inputDataReceiptForBillUser(String employeeId) throws SQLException {
        System.out.print("Nhập vào mã code = ");
        this.bill_Code = inputBillCodeIsCheckDuplicate();
        this.bill_Type = true;
        //Mã nhân viên nhập/xuất
        this.emp_Id_Created = employeeId;
//        this.emp_Id_Created = inputEmpIdCreated();
//        System.out.print("Nhập vào ngày tạo = ");
        // Ngày tạo Default Curr_date
//        this.created_Bill = inputCreatedBill();
        //Mã nhân viên duyệt
//        this.emp_Id_Auth = inputEmpIdAuth();
        this.emp_Id_Auth = null;
        //Ngày duyệt
        this.auth_Date=null;
        System.out.println("Nhập vào trạng thái = ");
        this.bill_Status = inputBillStatus();
    }

    //Nhập dữ lệu cho phiếu xuất Admin
    public void inputDataBillForBillAdmin(String employeeId) throws SQLException {
        System.out.print("Nhập vào mã code = ");
        this.bill_Code = inputBillCodeIsCheckDuplicate();
        this.bill_Type = false;
//        System.out.println("Nhập vào mã nhân viên xuất phiếu = ");
//        this.emp_Id_Created = inputEmpIdCreatedBill();
        this.emp_Id_Created = employeeId;
        this.emp_Id_Auth = null;
        this.auth_Date=null;
        System.out.println("Nhập vào trạng thái = ");
        this.bill_Status = inputBillStatus();

    }
    //Nhập dữ lệu cho phiếu xuất User
    public void inputDataBillForBillUser(String employeeId) throws SQLException {
        System.out.print("Nhập vào mã code = ");
        this.bill_Code = inputBillCodeIsCheckDuplicate();
        this.bill_Type = false;
        this.emp_Id_Created = employeeId;
        this.emp_Id_Auth = null;
        this.auth_Date=null;
        System.out.println("Nhập vào trạng thái = ");
        this.bill_Status = inputBillStatus();

    }
    // Nhập dữ liệu khi duyệt

    public void inputDataReceiptAuth(long billId, String employeeId) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        this.bill_Id = billId;
        this.emp_Id_Auth = employeeId;
        this.auth_Date = String.valueOf(currentDate);
    }

    public static String inputBillCode() {
        while (true) {
            String inputBillCodeStr = sc.nextLine().trim();
            if (inputBillCodeStr.isEmpty()) {
                System.err.println("Mã code không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputBillCodeStr.length() > 10) {
                        System.err.println("Mã code chỉ được phép tối đa 10 ký tự, vui lòng nhập lại");
                    } else {
                        return inputBillCodeStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputBillCodeIsCheckDuplicate() {
        while (true) {
            String inputBillCodeStr = sc.nextLine().trim();
            if (inputBillCodeStr.isEmpty()) {
                System.err.println("Mã code không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputBillCodeStr.length() > 10) {
                        System.err.println("Mã code chỉ được phép tối đa 10 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Mã code có tồn tại hay không
                        Bill bi = ReceiptBusiness.getReceiptByBillCodeBill(inputBillCodeStr);
                        if (bi != null) {
                            //Mã code tồn tại trong CSDL
                            System.err.println("Mã code đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputBillCodeStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }
//    public static String inputBillCodeIsCheckTrungBill() {
//        while (true) {
//            String inputBillCodeStr = sc.nextLine().trim();
//            if (inputBillCodeStr.isEmpty()) {
//                System.err.println("Mã code không được để trống, vui lòng nhập lại");
//            } else {
//                try {
//                    if (inputBillCodeStr.length() > 10) {
//                        System.err.println("Mã code chỉ được phép tối đa 10 ký tự, vui lòng nhập lại");
//                    } else {
//                        //Kiểm tra Mã code có tồn tại hay không
//                        Bill bi = ReceiptBusiness.getReceiptByBillCodeBill(inputBillCodeStr);
//                        if (bi != null) {
//                            //Mã code tồn tại trong CSDL
//                            System.err.println("Mã code đã bị trùng, vui lòng nhập lại");
//                        } else {
//                            return inputBillCodeStr;
//                        }
//                    }
//                } catch (Exception ex2) {
//                    System.err.println("Lỗi hệ thống");
//                }
//            }
//        }
//    }

    public static String inputEmpIdCreated() throws SQLException {
        while (true) {
            List<Employee> listEmployee = EmployeeBusiness.getAllDataEmployeeNameActive();
            try {
                String repeated = new String(new char[20]).replace("\0", border);
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " MENU MÃ NHÂN VIÊN NHẬP PHIẾU" + repeated  + " *" + ColorsMenu.ANSI_RESET);
                for (int i = 0; i < listEmployee.size(); i++) {
                    System.out.println(ColorsMenu.BLUE_BOLD + (i + 1) + " . " + listEmployee.get(i).getEmp_Id() + ColorsMenu.ANSI_RESET);
                }

                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + repeated + repeated + "---------" + " *" + ColorsMenu.ANSI_RESET);
                System.out.print("Chọn mã nhân viên nhập phiếu :");
                while (true) {
                    String inputChoiceNumberStr = sc.nextLine().trim();
                    if (inputChoiceNumberStr.isEmpty()) {
                        System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
                    } else {
                        try {
                            int choiceNumber = Integer.parseInt(inputChoiceNumberStr);
                            if (listEmployee.size() == 1 && choiceNumber > 1) {
                                System.err.println("Lựa chọn phải thuộc danh mục,vui lòng nhập lại");
                            } else if (choiceNumber > listEmployee.size()) {
                                System.err.println("Lựa chọn phải từ 1 đến " + listEmployee.size() + ",vui lòng nhập lại");
                            } else if (choiceNumber > 0) {
                                return listEmployee.get(choiceNumber - 1).getEmp_Id();
                            } else {
                                System.err.println("Lựa chọn phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                            }
                        } catch (NumberFormatException ex) {
                            System.err.println("Lựa chọn phải là số,vui lòng nhập lại");
                        } catch (Exception ex2) {
                            System.err.println("Lỗi hệ thống");
                        }
                    }
                }

            } catch (Exception ex2) {
                System.err.println("Lỗi hệ thống");
            }
        }
    }

    public static String inputEmpIdCreatedBill() throws SQLException {
        while (true) {
            List<Employee> listEmployee = EmployeeBusiness.getAllDataEmployeeNameActive();
            try {
                String repeated = new String(new char[20]).replace("\0", border);
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " MENU MÃ NHÂN VIÊN XUẤT PHIẾU" + repeated + "----" + " *" + ColorsMenu.ANSI_RESET);

                for (int i = 0; i < listEmployee.size(); i++) {
                    System.out.println(ColorsMenu.BLUE_BOLD + (i + 1) + " . " + listEmployee.get(i).getEmp_Id() + ColorsMenu.ANSI_RESET);
                }
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + repeated + repeated + "------------" + " *" + ColorsMenu.ANSI_RESET);
                System.out.print("Chọn mã nhân viên xuất phiếu :");
                while (true) {
                    String inputChoiceNumberStr = sc.nextLine().trim();
                    if (inputChoiceNumberStr.isEmpty()) {
                        System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
                    } else {
                        try {
                            int choiceNumber = Integer.parseInt(inputChoiceNumberStr);
                            if (listEmployee.size() == 1 && choiceNumber > 1) {
                                System.err.println("Lựa chọn phải thuộc danh mục,vui lòng nhập lại");
                            } else if (choiceNumber > listEmployee.size()) {
                                System.err.println("Lựa chọn phải từ 1 đến " + listEmployee.size() + ",vui lòng nhập lại");
                            } else if (choiceNumber > 0) {
                                return listEmployee.get(choiceNumber - 1).getEmp_Id();
                            } else {
                                System.err.println("Lựa chọn phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                            }
                        } catch (NumberFormatException ex) {
                            System.err.println("Lựa chọn phải là số,vui lòng nhập lại");
                        } catch (Exception ex2) {
                            System.err.println("Lỗi hệ thống");
                        }
                    }
                }

            } catch (Exception ex2) {
                System.err.println("Lỗi hệ thống");
            }
        }
    }

    public static String inputCreatedBill() {
        while (true) {
            String inputCreatedStr = sc.nextLine().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            if (inputCreatedStr.isEmpty()) {
                System.err.println("Ngày tạo không được để trống, vui lòng nhập lại");
            } else {
                try {
                    sdf.parse(inputCreatedStr);
                    return inputCreatedStr;
                } catch (ParseException ex) {
                    System.err.println("Ngày tạo không đúng định dạng yyyy-MM-dd, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputEmpIdAuth() throws SQLException {
        while (true) {
            List<Employee> listEmployee = EmployeeBusiness.getAllDataEmployeeNameActive();
            try {
                String repeated = new String(new char[20]).replace("\0", border);
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " MENU MÃ NHÂN VIÊN DUYỆT PHIẾU" + repeated + "----" + " *" + ColorsMenu.ANSI_RESET);
                for (int i = 0; i < listEmployee.size(); i++) {
                    System.out.println(ColorsMenu.BLUE_BOLD + (i + 1) + " . " + listEmployee.get(i).getEmp_Id() + ColorsMenu.ANSI_RESET);
                }
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + repeated + repeated + "---" + " *" + ColorsMenu.ANSI_RESET);
                System.out.print("Chọn mã nhân viên duyệt phiếu :");
                while (true) {
                    String inputChoiceNumberStr = sc.nextLine().trim();
                    if (inputChoiceNumberStr.isEmpty()) {
                        System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
                    } else {
                        try {
                            int choiceNumber = Integer.parseInt(inputChoiceNumberStr);
                            if (listEmployee.size() == 1 && choiceNumber > 1) {
                                System.err.println("Lựa chọn phải thuộc danh mục,vui lòng nhập lại");
                            } else if (choiceNumber > listEmployee.size()) {
                                System.err.println("Lựa chọn phải từ 1 đến " + listEmployee.size() + ",vui lòng nhập lại");
                            } else if (choiceNumber > 0) {
                                return listEmployee.get(choiceNumber - 1).getEmp_Id();
                            } else {
                                System.err.println("Lựa chọn phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                            }
                        } catch (NumberFormatException ex) {
                            System.err.println("Lựa chọn phải là số,vui lòng nhập lại");
                        } catch (Exception ex2) {
                            System.err.println("Lỗi hệ thống");
                        }
                    }
                }
            } catch (Exception ex2) {
                System.err.println("Lỗi hệ thống");
            }
        }

    }

    public static String inputAuthDate() {
        while (true) {
            String inputAuthDateStr = sc.nextLine().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            if (inputAuthDateStr.isEmpty()) {
                System.err.println("Ngày duyệt không được để trống, vui lòng nhập lại");
            } else {
                try {
                    sdf.parse(inputAuthDateStr);
                    return inputAuthDateStr;
                } catch (ParseException ex) {
                    System.err.println("Ngày duyệt không đúng định dạng yyyy-MM-dd, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static short inputBillStatus() {
        System.out.println(ColorsMenu.GREEN_BOLD + "---          1.Tạo             --");
        System.out.println("---          2.Hủy             --"+ ColorsMenu.ANSI_RESET);
//        System.out.println("---          3.Duyệt           --" );
        System.out.print("Lựa chọn trạng thái của bạn là :");
        while (true) {
            String inputBillStatusStr = sc.nextLine().trim();
            if (inputBillStatusStr.isEmpty()) {
                System.err.println("Lựa chọn trạng thái được để trống, vui lòng nhập lại");
            } else {
                try {
                    int billStatusStrValue = Integer.parseInt(inputBillStatusStr);
                    if (billStatusStrValue > 2) {
                        System.err.println("Trạng thái mà bạn muốn nhập vào phải nhập là 1 hoặc 2, vui lòng nhập lại");
                    } else if (billStatusStrValue > 0) {
                        if (billStatusStrValue == 1) {
                            return 0;
                        }else{
                            return 1;
                        }
//                        if (billStatusStrValue == 1) {
//                            return 0;
//                        } else if (billStatusStrValue == 2) {
//                            return 1;
//                        } else {
//                            return 2;
//                        }
                    } else {
                        System.err.println("Trạng thái mà bạn muốn nhập vào phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Trạng thái mà bạn muốn nhập vào phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static short inputBillStatusUpdate() {
        System.out.println(ColorsMenu.YELLOW_BOLD + "---          1.Tạo             --"+ ColorsMenu.ANSI_RESET);
        System.out.println(ColorsMenu.RED_BOLD+"---          2.Hủy             --" + ColorsMenu.ANSI_RESET);
        System.out.print("Lựa chọn trạng thái của bạn là :");
        while (true) {
            String inputBillStatusStr = sc.nextLine().trim();
            if (inputBillStatusStr.isEmpty()) {
                System.err.println("Lựa chọn trạng thái được để trống, vui lòng nhập lại");
            } else {
                try {
                    int billStatusStrValue = Integer.parseInt(inputBillStatusStr);
                    if (billStatusStrValue > 2) {
                        System.err.println("Trạng thái mà bạn muốn nhập vào phải nhập là 1 hoặc 2, vui lòng nhập lại");
                    } else if (billStatusStrValue > 0) {
                        if (billStatusStrValue == 1) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else {
                        System.err.println("Trạng thái mà bạn muốn nhập vào phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Trạng thái mà bạn muốn nhập vào phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public void displayDataReceipt() {
        String billTypeValue=this.bill_Type?"Phiếu nhập":"Phiếu xuất";
        String emp_Id_AuthValue=null;
        emp_Id_AuthValue = Objects.requireNonNullElse(this.emp_Id_Auth, "***");
        String auth_DateValue=null;
        if(this.auth_Date.equalsIgnoreCase("null")){
            auth_DateValue="***";
        }else{
            auth_DateValue=this.auth_Date;
        }
        String billStatusValue = this.bill_Status == 0 ? "Tạo" : this.bill_Status == 1 ? "Hủy" : "Duyệt";
        System.out.printf("| %-15d | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n",
                this.bill_Id, this.bill_Code, billTypeValue, this.emp_Id_Created, this.created_Bill, emp_Id_AuthValue, auth_DateValue, billStatusValue);

    }

    public void displayDataBill() {
        String billTypeValue=this.bill_Type?"Phiếu nhập":"Phiếu xuất";
        String emp_Id_AuthValue=null;
        if(this.emp_Id_Auth==null){
            emp_Id_AuthValue="***";
        }else{
            emp_Id_AuthValue=this.emp_Id_Auth;
        }
        String auth_DateValue=null;
        if(this.auth_Date.equalsIgnoreCase("null")){
            auth_DateValue="***";
        }else{
            auth_DateValue= this.auth_Date;
        }
        String billStatusValue = this.bill_Status == 0 ? "Tạo" : this.bill_Status == 1 ? "Hủy" : "Duyệt";
        System.out.printf("| %-15d | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s| %-15s|\n",
                this.bill_Id, this.bill_Code, billTypeValue, this.emp_Id_Created, this.created_Bill,emp_Id_AuthValue,auth_DateValue , billStatusValue);

    }

    public static String inputReceiptUpdate() {
        while (true) {
            String inputReceiptUpdateStr = sc.nextLine().trim();
            if (inputReceiptUpdateStr.isEmpty()) {
                System.err.println("Mã phiếu hoặc mã code của phiếu nhâp không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputReceiptUpdateStr.length() > 10) {
                        System.err.println("Mã phiếu hoặc mã code của phiếu nhâp chỉ được phép tối đa 10 ký tự, vui lòng nhập lại");
                    } else {
                        return inputReceiptUpdateStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }

}