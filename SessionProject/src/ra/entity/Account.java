package ra.entity;

import ra.businnes.AccountBusiness;
import ra.businnes.EmployeeBusiness;
import ra.colors.ColorsMenu;
import ra.presentation.AccountManagement.*;

import java.sql.SQLException;
import java.util.List;

import static ra.presentation.MainManagement.sc;

public class Account {
    public static final String border = "-";
    private int acc_Id;
    private String user_Name;
    private String password;
    private Boolean permission;
    private String emp_Id;
    private Boolean acc_Status;

    public Account() {
    }

    public Account(int acc_Id, String user_Name, String password, Boolean permission, String emp_Id, Boolean acc_Status) {
        this.acc_Id = acc_Id;
        this.user_Name = user_Name;
        this.password = password;
        this.permission = permission;
        this.emp_Id = emp_Id;
        this.acc_Status = acc_Status;
    }

    public int getAcc_Id() {
        return acc_Id;
    }

    public void setAcc_Id(int acc_Id) {
        this.acc_Id = acc_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    public Boolean getAcc_Status() {
        return acc_Status;
    }

    public void setAcc_Status(Boolean acc_Status) {
        this.acc_Status = acc_Status;
    }

    public void inputDataAccount() throws SQLException {
        System.out.print("Nhập vào tên tài khoản = ");
        this.user_Name = inputUserName();
        System.out.print("Nhập vào mật khẩu = ");
        this.password = inputPassword();
        System.out.println("Nhập vào phân quyền tài khoản = ");
        this.permission = inputPermission();
        this.emp_Id = inputEmpId();
        System.out.println("Nhập vào trạng thái = ");
        this.acc_Status = inputAccStatus();
    }

    public static String inputUserNameUpdate() {
        while (true) {
            String inputUserNameUpdateStr = sc.nextLine().trim();
            if (inputUserNameUpdateStr.isEmpty()) {
                System.err.println("Tên tài khoản không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputUserNameUpdateStr.length() > 30) {
                        System.err.println("Tên tài khoản chỉ được phép tối đa 30 ký tự, vui lòng nhập lại");
                    } else {
                        return inputUserNameUpdateStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputUserName() {
        while (true) {
            String inputUserNameStr = sc.nextLine().trim();
            if (inputUserNameStr.isEmpty()) {
                System.err.println("Tên tài khoản không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputUserNameStr.length() > 30) {
                        System.err.println("Tên tài khoản chỉ được phép tối đa 30 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Tên tài khoản có tồn tại hay không
                        Account acc = AccountBusiness.getAccountByUserName(inputUserNameStr);
                        if (acc != null) {
                            //Tên tài khoản tồn tại trong CSDL
                            System.err.println("Tên tài khoản đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputUserNameStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputPassword() {
        while (true) {
            String inputPasswordStr = sc.nextLine().trim();
            if (inputPasswordStr.isEmpty()) {
                System.err.println("Mật khẩu không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputPasswordStr.length() > 30) {
                        System.err.println("Mật khẩu chỉ được phép tối đa 30 ký tự, vui lòng nhập lại");
                    } else {

                        return inputPasswordStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static boolean inputPermission() {
        System.out.println(ColorsMenu.YELLOW_BOLD + "======================== MENU PHÂN QUYỀN CHO TÀI KHOẢN ========================");
        System.out.println("---                       1.Admin                                            --");
        System.out.println("---                       2.User                                             --" + ColorsMenu.ANSI_RESET);
        System.out.print("Lựa chọn phân quyền tài khoản của bạn là :");
        while (true) {
            String inputPermissionStr = sc.nextLine().trim();
            if (inputPermissionStr.isEmpty()) {
                System.err.println("Lựa chọn phân quyền tài khoản không được để trống, vui lòng nhập lại");
            } else {
                try {
                    int permissionValue = Integer.parseInt(inputPermissionStr);
                    if (permissionValue > 2) {
                        System.err.println("Lựa chọn phân quyền tài khoản mà bạn muốn nhập vào phải nhập là 1 hoặc 2, vui lòng nhập lại");
                    } else if (permissionValue > 0) {
                        if (permissionValue == 1) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        System.err.println("Lựa chọn phân quyền tài khoản mà bạn muốn nhập vào phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn phân quyền tài khoản mà bạn muốn nhập vào phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputEmpId() throws SQLException {
        while (true) {
            List<Employee> listEmployee = EmployeeBusiness.getShowAllDataEmployeeNotInAccount();
            try {
                String repeated = new String(new char[20]).replace("\0", border);
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " MENU MÃ NHÂN VIÊN " + repeated + "----" + " *" + ColorsMenu.ANSI_RESET);
                for (int i = 0; i < listEmployee.size(); i++) {
                    System.out.println(ColorsMenu.BLUE_BOLD + (i + 1) + " . " + listEmployee.get(i).getEmp_Id() + ColorsMenu.ANSI_RESET);
                }
                System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + repeated + repeated + "---" + " *" + ColorsMenu.ANSI_RESET);
                System.out.print("Chọn mã nhân viên mà bạn muốn thêm tài khoản vào :");
                while (true) {
                    String inputChoiceNumberStr = sc.nextLine().trim();
                    if (inputChoiceNumberStr.isEmpty()) {
                        System.err.println("Mã nhân viên mà bạn muốn thêm tài khoản vào không được để trống,vui lòng nhập lại");
                    } else {
                        try {
                            int choiceNumber = Integer.parseInt(inputChoiceNumberStr);
                            if (listEmployee.size() == 1 && choiceNumber > 1) {
                                System.err.println("Mã nhân viên mà bạn muốn thêm tài khoản vào phải thuộc danh mục,vui lòng nhập lại");
                            } else if (choiceNumber > listEmployee.size()) {
                                System.err.println("Mã nhân viên mà bạn muốn thêm tài khoản vào phải từ 1 đến " + listEmployee.size() + ",vui lòng nhập lại");
                            } else if (choiceNumber > 0) {
                                return listEmployee.get(choiceNumber - 1).getEmp_Id();
                            } else {
                                System.err.println("Mã nhân viên mà bạn muốn thêm tài khoản vào phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                            }
                        } catch (NumberFormatException ex) {
                            System.err.println("Mã nhân viên mà bạn muốn thêm tài khoản vào phải là số,vui lòng nhập lại");
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

    public static boolean inputAccStatus() {
        System.out.println(ColorsMenu.YELLOW_BOLD + "======================== MENU TRẠNG THÁI CHO TÀI KHOẢN =========================");
        System.out.println("---                       1.Active                                            --");
        System.out.println("---                       2.Block                                             --" + ColorsMenu.ANSI_RESET);
        System.out.print("Lựa chọn trạng thái tài khoản của bạn là :");
        while (true) {
            String inputAccStatusStr = sc.nextLine().trim();
            if (inputAccStatusStr.isEmpty()) {
                System.err.println("Lựa chọn trạng thái tài khoản không được để trống, vui lòng nhập lại");
            } else {
                try {
                    int inputAccStatusValue = Integer.parseInt(inputAccStatusStr);
                    if (inputAccStatusValue > 2) {
                        System.err.println("Lựa chọn trạng thái tài khoản mà bạn muốn nhập vào phải nhập là 1 hoặc 2, vui lòng nhập lại");
                    } else if (inputAccStatusValue > 0) {
                        if (inputAccStatusValue == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        System.err.println("Lựa chọn trạng thái tài khoản mà bạn muốn nhập vào phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn trạng thái tài khoản mà bạn muốn nhập vào phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static boolean inputAccStatusUpdate() {
        System.out.println(ColorsMenu.YELLOW_BOLD + "======================== MENU TRẠNG THÁI CHO TÀI KHOẢN =========================");
        System.out.println("---                       1.Active                                            --");
        System.out.println("---                       2.Block                                             --" + ColorsMenu.ANSI_RESET);
        System.out.print("Lựa chọn trạng thái tài khoản cần cập nhật của bạn là :");
        while (true) {
            String inputAccStatusStr = sc.nextLine().trim();
            if (inputAccStatusStr.isEmpty()) {
                System.err.println("Lựa chọn trạng thái tài khoản không được để trống, vui lòng nhập lại");
            } else {
                try {
                    int inputAccStatusValue = Integer.parseInt(inputAccStatusStr);
                    if (inputAccStatusValue > 2) {
                        System.err.println("Lựa chọn trạng thái tài khoản mà bạn muốn nhập vào phải nhập là 1 hoặc 2, vui lòng nhập lại");
                    } else if (inputAccStatusValue > 0) {
                        if (inputAccStatusValue == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        System.err.println("Lựa chọn trạng thái tài khoản mà bạn muốn nhập vào phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn trạng thái tài khoản mà bạn muốn nhập vào phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputSearch() {
        while (true) {
            String inputSearchStr = sc.nextLine().trim();
            if (inputSearchStr.isEmpty()) {
                System.err.println("Tìm kiếm tài khoản cho phép tìm theo username hoặc tên nhân viên không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputSearchStr.length() > 100) {
                        System.err.println("Tìm kiếm tài khoản cho phép tìm theo username hoặc tên nhân viên chỉ được phép tối đa 100 ký tự, vui lòng nhập lại");
                    } else {
                        return inputSearchStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public void displayDataAccount() {
        String permissionValue = this.permission ? "User" : "Admin";
        String accStatusValue = this.acc_Status ? "Active" : "Block";
        System.out.printf("| %-15d | %-30s | %-15s | %-30s | %-20s | %-20s |\n", this.acc_Id, this.user_Name, this.password, permissionValue, this.emp_Id, accStatusValue);
    }


}
