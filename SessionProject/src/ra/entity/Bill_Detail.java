package ra.entity;

import ra.businnes.ProductBusiness;
import ra.businnes.ReceiptBusiness;
import ra.colors.ColorsMenu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.MainManagement.sc;

public class Bill_Detail {
    public static final String border = "-";
    private long bill_Detail_Id;
    private long bill_Id;
    private String product_Id;
    private int quantity;
    private float price;

    public Bill_Detail() {
    }

    public Bill_Detail(long bill_Detail_Id, long bill_Id, String product_Id, int quantity, float price) {
        this.bill_Detail_Id = bill_Detail_Id;
        this.bill_Id = bill_Id;
        this.product_Id = product_Id;
        this.quantity = quantity;
        this.price = price;
    }

    public long getBill_Detail_Id() {
        return bill_Detail_Id;
    }

    public void setBill_Detail_Id(long bill_Detail_Id) {
        this.bill_Detail_Id = bill_Detail_Id;
    }

    public long getBill_Id() {
        return bill_Id;
    }

    public void setBill_Id(long bill_Id) {
        this.bill_Id = bill_Id;
    }

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void inputDataBillDetail(long billDetailId) throws SQLException {
        this.bill_Id = billDetailId;
        this.product_Id = inputProductId();
        System.out.print("Nhập vào số lượng nhập = ");
        this.quantity = inputQuantity();
        System.out.print("Nhập vào giá nhập = ");
        this.price = inputPrice();
    }

    public static long inputBillDetailId() {
        while (true) {
            String inputBillDetailIdStr = sc.nextLine().trim();
            if (inputBillDetailIdStr.isEmpty()) {
                System.err.println("Mã phiếu chi tiết không được để trống,vui lòng nhập lại");
            } else {
                try {
                    long inputBillDetailId = Long.parseLong(inputBillDetailIdStr);
                    if (inputBillDetailId > 0) {
                        return inputBillDetailId;
                    } else {
                        System.err.println("Mã phiếu chi tiết phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Mã phiếu chi tiết phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    public static String inputProductId() throws SQLException {
        while (true) {
            List<Product> listProduct = ProductBusiness.getAllDataProductForBillDetail();
                try {
                    String repeated = new String(new char[20]).replace("\0", border);
                    System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + " MENU MÃ SẢN PHẨM" + repeated + "----" + " *" + ColorsMenu.ANSI_RESET);
                    for (int i = 0; i < listProduct.size(); i++) {
                        System.out.println(ColorsMenu.BLUE_BOLD + (i + 1) + " . " + listProduct.get(i).getProduct_Id() + ColorsMenu.ANSI_RESET);
                    }
                    System.out.println(ColorsMenu.YELLOW_BOLD + "* " + repeated + repeated + repeated + "---" + " *" + ColorsMenu.ANSI_RESET);
                    System.out.print("Vui lòng chọn mã sản phẩm :");
                    while (true) {
                        String inputChoiceNumberStr = sc.nextLine().trim();
                        if (inputChoiceNumberStr.isEmpty()) {
                            System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
                        } else {
                            try {
                                int choiceNumber = Integer.parseInt(inputChoiceNumberStr);
                                if (listProduct.size() == 1 && choiceNumber > 1) {
                                    System.err.println("Lựa chọn phải thuộc danh mục,vui lòng nhập lại");
                                } else if (choiceNumber > listProduct.size()) {
                                    System.err.println("Lựa chọn phải từ 1 đến " + listProduct.size() + ",vui lòng nhập lại");
                                } else if (choiceNumber > 0) {
                                    return listProduct.get(choiceNumber - 1).getProduct_Id();
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

        public static int inputQuantity () {
            while (true) {
                String inputQuantityStr = sc.nextLine().trim();
                if (inputQuantityStr.isEmpty()) {
                    System.err.println("Số lượng nhập không được để trống,vui lòng nhập lại");
                } else {
                    try {
                        int inputQuantity = Integer.parseInt(inputQuantityStr);
                        if (inputQuantity > 0) {
                            return inputQuantity;
                        } else {
                            System.err.println("Số lượng nhập phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("Số lượng nhập phải là số,vui lòng nhập lại");
                    }
                }
            }
        }

        public static float inputPrice () {
            while (true) {
                String inputPriceStr = sc.nextLine().trim();
                if (inputPriceStr.isEmpty()) {
                    System.err.println("Giá nhập không được để trống,vui lòng nhập lại");
                } else {
                    try {
                        float inputPrice = Float.parseFloat(inputPriceStr);
                        if (inputPrice > 0) {
                            return inputPrice;
                        } else {
                            System.err.println("Giá nhập phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("Giá nhập phải là số,vui lòng nhập lại");
                    }
                }
            }
        }


        public void displayDataBillDetail () {
            System.out.printf("| %-20d | %-30d | %-15s | %-30d | %-20.1f |\n", this.bill_Detail_Id, this.bill_Id, this.product_Id, this.quantity, this.price);
        }

    }
