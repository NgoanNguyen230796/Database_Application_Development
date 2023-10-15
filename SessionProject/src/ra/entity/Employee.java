package ra.entity;

import ra.businnes.EmployeeBusiness;
import ra.colors.ColorsMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import static ra.presentation.MainManagement.sc;

public class Employee {
    private String emp_Id;
    private String emp_Name;
    private String birth_Of_Date;
    private String email;
    private String phone;
    private String address;
    private short emp_Status;

    public Employee() {
    }

    public Employee(String emp_Id, String emp_Name, String birth_Of_Date, String email, String phone, String address, short emp_Status) {
        this.emp_Id = emp_Id;
        this.emp_Name = emp_Name;
        this.birth_Of_Date = birth_Of_Date;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.emp_Status = emp_Status;
    }

    public String getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getBirth_Of_Date() {
        return birth_Of_Date;
    }

    public void setBirth_Of_Date(String birth_Of_Date) {
        this.birth_Of_Date = birth_Of_Date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public short getEmp_Status() {
        return emp_Status;
    }

    public void setEmp_Status(short emp_Status) {
        this.emp_Status = emp_Status;
    }

    public void inputDataEmployee() {
        System.out.print("Nhập vào mã nhân viên = ");
        this.emp_Id = inputEmpId();
        System.out.print("Nhập vào tên nhân viên = ");
        this.emp_Name = inputEmpName();
        System.out.print("Nhập vào ngày sinh = ");
        this.birth_Of_Date = inputBirthOfDate();
        System.out.print("Nhập vào email = ");
        this.email = inputEmail();
        System.out.print("Nhập vào số điện thoại = ");
        this.phone = inputPhone();
        System.out.print("Nhập vào địa chỉ = ");
        this.address = inputAddress();
        System.out.println("Nhập vào trạng thái = ");
        this.emp_Status = inputEmpStatus();
    }

    public static String inputEmpId() {
        while (true) {
            String inputEmpIdStr = sc.nextLine().trim();
            if (inputEmpIdStr.isEmpty()) {
                System.err.println("Mã nhân viên không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputEmpIdStr.length() > 5) {
                        System.err.println("Mã nhân viên chỉ được phép tối đa 5 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Mã nhân viên có tồn tại hay không
                        Employee emy = EmployeeBusiness.getEmployeeById(inputEmpIdStr);
                        if (emy != null) {
                            //Mã nhân viên tồn tại trong CSDL
                            System.err.println("Mã nhân viên đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputEmpIdStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputEmpIdUpdate() {
        while (true) {
            String inputEmpIdUpdateStr = sc.nextLine().trim();
            if (inputEmpIdUpdateStr.isEmpty()) {
                System.err.println("Mã nhân viên không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputEmpIdUpdateStr.length() > 5) {
                        System.err.println("Mã nhân viên chỉ được phép tối đa 5 ký tự, vui lòng nhập lại");
                    } else {
                        return inputEmpIdUpdateStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }

            }
        }
    }

    public static String inputEmpName() {
        while (true) {
            String inputEmpNameStr = sc.nextLine().trim();
            if (inputEmpNameStr.isEmpty()) {
                System.err.println("Tên nhân viên không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputEmpNameStr.length() > 100) {
                        System.err.println("Tên nhân viên chỉ được phép tối đa 100 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Tên nhân viên có tồn tại hay không
                        Employee emy = EmployeeBusiness.getEmployeeByName(inputEmpNameStr);
                        if (emy != null) {
                            //Tên nhân viên tồn tại trong CSDL
                            System.err.println("Tên nhân viên đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputEmpNameStr;
                        }
                    }
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
                System.err.println("Tìm kiếm nhân viên theo mã hoặc theo tên nhân viên không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputSearchStr.length() > 100) {
                        System.err.println("Tìm kiếm nhân viên theo mã hoặc theo tên nhân viên chỉ được phép tối đa 100 ký tự, vui lòng nhập lại");
                    } else {
                        return inputSearchStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputBirthOfDate() {
        while (true) {
            String inputBirthOfDateStr = sc.nextLine().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            if (inputBirthOfDateStr.isEmpty()) {
                System.err.println("Ngày sinh không được để trống, vui lòng nhập lại");
            } else {
                try {
                    sdf.parse(inputBirthOfDateStr);
                    return inputBirthOfDateStr;
                } catch (ParseException ex) {
                    System.err.println("Ngày sinh không đúng định dạng yyyy-MM-dd, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputEmail() {
        while (true) {
            String regexEmail = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            String inputEmailStr = sc.nextLine().trim();
            if (inputEmailStr.isEmpty()) {
                System.err.println("Email không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (Pattern.matches(regexEmail, inputEmailStr)) {
                        return inputEmailStr;
                    } else {
                        System.err.println("Email không đúng định dạng, vui lòng nhập lại");
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputPhone() {
        while (true) {
            String regexPhoneNumber = "^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";
            String inputPhoneStr = sc.nextLine().trim();
            if (inputPhoneStr.isEmpty()) {
                System.err.println("Số điện thoại không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (Pattern.matches(regexPhoneNumber, inputPhoneStr)) {
                        return inputPhoneStr;
                    } else {
                        System.err.println("Số điện thoại không đúng định dạng, vui lòng nhập lại");
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputAddress() {
        while (true) {
            String inputAddressStr = sc.nextLine().trim();
            if (inputAddressStr.isEmpty()) {
                System.err.println("Địa chỉ không được để trống, vui lòng nhập lại");
            } else {
                try {
                    return inputAddressStr;
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static short inputEmpStatus() {
        System.out.println(ColorsMenu.GREEN_BOLD + "---          1.Hoạt động             --");
        System.out.println("---          2.Nghỉ chế độ           --");
        System.out.println("---          3.Nghỉ việc             --" + ColorsMenu.ANSI_RESET);
        System.out.print("Lựa chọn trạng thái của bạn là :");
        while (true) {
            String inputEmpStatusStr = sc.nextLine().trim();
            if (inputEmpStatusStr.isEmpty()) {
                System.err.println("Lựa chọn trạng thái được để trống, vui lòng nhập lại");
            } else {
                try {
                    int empStatusValue = Integer.parseInt(inputEmpStatusStr);
                    if (empStatusValue > 3) {
                        System.err.println("Trạng thái mà bạn muốn nhập vào phải nhập là 1 hoặc 2 hoặc 3, vui lòng nhập lại");
                    } else if (empStatusValue > 0) {
                        if (empStatusValue == 1) {
                            return 0;
                        } else if (empStatusValue == 2) {
                            return 1;
                        } else {
                            return 2;
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

    public static String inputEmployeeUpdateName(String employeeId) {
        while (true) {
            String inputEmployeeNameStr = sc.nextLine().trim();
            if (inputEmployeeNameStr.isEmpty()) {
                System.err.println("Tên nhân viên không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputEmployeeNameStr.length() > 100) {
                        System.err.println("Tên nhân viên chỉ được phép tối đa 100 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Tên sản phẩm có tồn tại hay không
                        List<Employee> prAllDataOtherInputEmployeeIdUpdate = EmployeeBusiness.getEmployeeOtherByEmployeeId(employeeId);
                        boolean isCheckTrungEmployeeName = false;
                        for (Employee emy : prAllDataOtherInputEmployeeIdUpdate) {
                            if (emy.getEmp_Name().equalsIgnoreCase(inputEmployeeNameStr)) {
                                isCheckTrungEmployeeName = true;
                                break;
                            }
                        }
                        if (isCheckTrungEmployeeName) {
                            //Tên sản phẩm tồn tại trong CSDL
                            System.err.println("Tên nhân viên đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputEmployeeNameStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public void displayDataEmployee() {
        String empStatusValue = this.emp_Status == 0 ? "Hoạt động" : this.emp_Status == 1 ? "Nghỉ chế độ" : "Nghỉ việc";
        System.out.printf("| %-15s | %-30s | %-15s | %-30s | %-20s | %-20s | %-15s|\n", this.emp_Id, this.emp_Name, this.birth_Of_Date, this.email, this.phone, this.address, empStatusValue);

    }

}
