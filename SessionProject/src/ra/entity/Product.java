package ra.entity;

import ra.businnes.ProductBusiness;
import ra.colors.ColorsMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static ra.presentation.MainManagement.sc;

public class Product {
    private String product_Id;
    private String product_Name;
    private String manufacturer;
    private String created;
    private short batch;
    private int quantity;
    private Boolean product_Status;

    public Product() {
    }

    public Product(String product_Id, String product_Name, String manufacturer, String created, short batch, int quantity, Boolean product_Status) {
        this.product_Id = product_Id;
        this.product_Name = product_Name;
        this.manufacturer = manufacturer;
        this.created = created;
        this.batch = batch;
        this.quantity = quantity;
        this.product_Status = product_Status;
    }

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public short getBatch() {
        return batch;
    }

    public void setBatch(short batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getProduct_Status() {
        return product_Status;
    }

    public void setProduct_Status(Boolean product_Status) {
        this.product_Status = product_Status;
    }

    public void inputData() {
        LocalDate currentDate = LocalDate.now();
        System.out.print("Nhập vào mã sản phẩm = ");
        this.product_Id = inputProductId();
        System.out.print("Nhập vào tên sản phẩm = ");
        this.product_Name = inputProductName();
        System.out.print("Nhập vào tên nhà sản xuất = ");
        this.manufacturer = inputManufacturer();

//        System.out.print("Nhập vào ngày tạo = ");
        this.created = String.valueOf(currentDate);
        System.out.print("Nhập vào lô chứa sản phẩm = ");
        this.batch = inputBatch();
//        System.out.print("Nhập vào số lượng sản phẩm = ");
//        this.quantity=inputQuantity();
        System.out.println("Vui lòng chọn trạng thái sản phẩm = ");
        this.product_Status = inputProductStatus();
    }

    public static String inputProductId() {
        while (true) {
            String inputProductIdStr = sc.nextLine().trim();
            if (inputProductIdStr.isEmpty()) {
                System.err.println("Mã sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputProductIdStr.length() > 5) {
                        System.err.println("Mã sản phẩm chỉ được phép tối đa 5 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra mã sản phẩm có tồn tại hay không
                        Product pr = ProductBusiness.getProductById(inputProductIdStr);
                        if (pr != null) {
                            //Mã sản phẩm tồn tại trong CSDL
                            System.err.println("Mã sản phẩm đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputProductIdStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputProductIdUpdate() {
        while (true) {
            String inputProductIdStr = sc.nextLine().trim();
            if (inputProductIdStr.isEmpty()) {
                System.err.println("Mã sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputProductIdStr.length() > 5) {
                        System.err.println("Mã sản phẩm chỉ được phép tối đa 5 ký tự, vui lòng nhập lại");
                    } else {
                        return inputProductIdStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }


            }
        }
    }

    public static String inputProductName() {
        while (true) {
            String inputProductNameStr = sc.nextLine().trim();
            if (inputProductNameStr.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputProductNameStr.length() > 150) {
                        System.err.println("Tên sản phẩm chỉ được phép tối đa 150 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Tên sản phẩm có tồn tại hay không
                        Product pr = ProductBusiness.getProductByName(inputProductNameStr);
                        if (pr != null) {
                            //Tên sản phẩm tồn tại trong CSDL
                            System.err.println("Tên sản phẩm đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputProductNameStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputProductNameSearch() {
        while (true) {
            String inputProductNameStr = sc.nextLine().trim();
            if (inputProductNameStr.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputProductNameStr.length() > 150) {
                        System.err.println("Tên sản phẩm chỉ được phép tối đa 150 ký tự, vui lòng nhập lại");
                    } else {
                        return inputProductNameStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputProductUpdateName(String productId) {
        while (true) {
            String inputProductNameStr = sc.nextLine().trim();
            if (inputProductNameStr.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputProductNameStr.length() > 150) {
                        System.err.println("Tên sản phẩm chỉ được phép tối đa 150 ký tự, vui lòng nhập lại");
                    } else {
                        //Kiểm tra Tên sản phẩm có tồn tại hay không
                        List<Product> prAllDataOtherInputProductIdUpdate = ProductBusiness.getProductOtherById(productId);
                        boolean isCheckTrungProductName = false;
                        for (Product pr : prAllDataOtherInputProductIdUpdate) {
                            if (pr.getProduct_Name().equalsIgnoreCase(inputProductNameStr)) {
                                isCheckTrungProductName = true;
                                break;
                            }
                        }
                        if (isCheckTrungProductName) {
                            //Tên sản phẩm tồn tại trong CSDL
                            System.err.println("Tên sản phẩm đã bị trùng, vui lòng nhập lại");
                        } else {
                            return inputProductNameStr;
                        }
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputManufacturer() {
        while (true) {
            String inputManufacturerStr = sc.nextLine().trim();
            if (inputManufacturerStr.isEmpty()) {
                System.err.println("Tên nhà sản xuất không được để trống, vui lòng nhập lại");
            } else {
                try {
                    if (inputManufacturerStr.length() > 200) {
                        System.err.println("Tên nhà sản xuất chỉ được phép tối đa 200 ký tự, vui lòng nhập lại");
                    } else {
                        return inputManufacturerStr;
                    }
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputCreated() {
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

    public static short inputBatch() {
        while (true) {
            String inputBatchStr = sc.nextLine().trim();
            if (inputBatchStr.isEmpty()) {
                System.err.println("Lô chứa sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    short soSl = (short) Integer.parseInt(inputBatchStr);
                    if (soSl > 0) {
                        return soSl;
                    } else {
                        System.err.println("Lô chứa sản phẩm phải là số nguyên lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lô chứa sản phẩm phải là số, vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    //    public static int inputQuantity() {
//        while (true) {
//            String inputQuantityStr = sc.nextLine().trim();
//            if (inputQuantityStr.isEmpty()) {
//                System.err.println("Số lượng sản phẩm không được để trống, vui lòng nhập lại");
//            } else {
//                try {
//                    int slSanPham=Integer.parseInt(inputQuantityStr);
//                    if (slSanPham > 0) {
//                        return slSanPham;
//                    } else {
//                        System.err.println("Số lượng sản phẩm phải là số nguyên lớn hơn 0, vui lòng nhập lại");
//                    }
//                } catch (NumberFormatException ex) {
//                    System.err.println("Số lượng sản phẩm phải là số, vui lòng nhập lại");
//                } catch (Exception ex2) {
//                    System.err.println("Lỗi hệ thống");
//                }
//            }
//        }
//    }
    public static boolean inputProductStatus() {
        System.out.println(ColorsMenu.GREEN_BOLD + "---          1.Hoạt động           --");
        System.out.println("---          2.Không hoạt động     --" + ColorsMenu.ANSI_RESET);
        System.out.print("Lựa chọn trạng thái của bạn là :");
        while (true) {
            String inputProductStatus = sc.nextLine().trim();
            if (inputProductStatus.isEmpty()) {
                System.err.println("Lựa chọn trạng thái sản phẩm không được để trống, vui lòng nhập lại");
            } else {
                try {
                    int statusValue = Integer.parseInt(inputProductStatus);
                    if (statusValue > 2) {
                        System.err.println("Trạng thái mà bạn muốn nhập vào phải nhập là 1 hoặc 2, vui lòng nhập lại");
                    } else if (statusValue > 0) {
                        if (statusValue == 1) {
                            return true;
                        } else {
                            return false;
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

    public void displayDataProduct() {
        String productStatusValue = this.product_Status ? "Hoạt động" : "Không hoạt động";
        System.out.printf("| %-20s | %-30s | %-20s | %-20s | %-20d | %-15d | %-20s|\n", this.product_Id, this.product_Name, this.manufacturer, this.created, this.batch, this.quantity, productStatusValue);

    }

}
