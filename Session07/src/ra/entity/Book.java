package ra.entity;

import ra.business.BookBusiness;
import ra.run.BookManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static ra.run.BookManagement.sc;

public class Book {
    private String bookId;
    private String bookName;
    private float price;
    private String author;
    private Boolean status;

    public Book() {
    }

    public Book(String bookId, String bookName, float price, String author, Boolean status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.author = author;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void inputData(Scanner sc) {
        System.out.println("Nhập vào mã sách:");
        this.bookId = inputBookId();
        System.out.println("Nhập vào tên sách:");
        this.bookName = inputBookName();
        System.out.println("Nhập vào giá sách:");
        this.price = inputBookPrice();
        System.out.println("Nhập vào tác sách:");
        this.author = inputBookAuthor();
        System.out.println("Nhập vào trạng thái sách:");
        this.status = inputBookStatus();
    }

    public void displayData() {
        String statusValue = this.status ? "Active" : "InActive";
        System.out.printf("%-20s%-20s%-20.1f%-20s%-20s\n", this.bookId, this.bookName, this.price, this.author, statusValue);
    }

    public static String inputBookId() {
        while (true) {
            String inputBookIdStr = sc.nextLine().trim();
            if (inputBookIdStr.length() == 0) {
                System.err.println("Mã sách không được để trống , vui lòng nhập lại");
            } else {
                try {
                    //gồm 4 kí tự, bắt đầu bằng “P”
                    boolean isCheck = isCheckTrungBookId(inputBookIdStr);
                    if (isCheck) {
                        System.err.println("Mã sách đã bị trùng, vui lòng nhập lại");
                    } else {
                        if (inputBookIdStr.length() == 4 && inputBookIdStr.charAt(0) == 'B') {
                            return inputBookIdStr;
                        } else {
                            System.err.println("Mã sách phải gồm 4 ký tự bắt đầu là B,vui lòng nhập lại");
                        }
                    }

                } catch (Exception ex) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }


    }

    public static boolean isCheckTrungBookId(String bookIdNeedCheck) throws SQLException {
        List<Book> listBook = BookBusiness.getAllBook();
        for (Book bok : listBook) {
            if (bok.getBookId().equalsIgnoreCase(bookIdNeedCheck)) {
                return true;
            }
        }
        return false;
    }

    public static String inputBookName() {
        while (true) {
            String inputBookNameStr = sc.nextLine().trim();
            if (inputBookNameStr.length() == 0) {
                System.err.println("Tên sách không được để trống , vui lòng nhập lại");
            } else {
                try {
                    //gồm 4 kí tự, bắt đầu bằng “P”
                    boolean isCheck = isCheckTrungBookName(inputBookNameStr);
                    if (isCheck) {
                        System.err.println("Tên sách đã bị trùng, vui lòng nhập lại");
                    } else {
                        if (inputBookNameStr.length() >=10 && inputBookNameStr.length()<=50) {
                            return inputBookNameStr;
                        } else {
                            System.err.println("Tên sách phải từ 10-50 k tự,vui lòng nhập lại");
                        }
                    }

                } catch (Exception ex) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }

    }
    public static boolean isCheckTrungBookName(String bookNameNeedCheck) throws SQLException {
        List<Book> listBook = BookBusiness.getAllBook();
        for (Book bok : listBook) {
            if (bok.getBookName().equalsIgnoreCase(bookNameNeedCheck)) {
                return true;
            }
        }
        return false;
    }

    public static float inputBookPrice(){
        while (true) {
            String inputBookPriceStr = sc.nextLine().trim();
            if (inputBookPriceStr.length() == 0) {
                System.err.println("Giá sách không được để trống , vui lòng nhập lại");
            } else {
                try {
                    float bookPrice = Float.parseFloat(inputBookPriceStr);
                    if (bookPrice > 0) {
                        return bookPrice;
                    } else {
                        System.err.println("Giá sách phải là số thực lớn hơn 0, vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Giá sách phải là số thực , vui lòng nhập lại");
                } catch (Exception ex2) {
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static String inputBookAuthor() {
        while (true) {
            String bookAuthorStr = sc.nextLine().trim();
            if (bookAuthorStr.length() == 0) {
                System.err.println("Tên tác giả sách không được để trống, vui lòng nhập lại");
            }else{
                try{
                    return bookAuthorStr;

                }catch (Exception ex){
                    System.err.println("Lỗi hệ thống");
                }
            }
        }
    }

    public static boolean inputBookStatus() {
        while (true) {
            String bookStatusStr = sc.nextLine().trim();
            if (bookStatusStr.length() == 0) {
                System.err.println("Trạng thái sách không được để trống, vui lòng nhập lại");
            } else if (bookStatusStr.equalsIgnoreCase("true") || bookStatusStr.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(bookStatusStr);
            } else {
                System.err.println("Trạng thái sách phải là true/false, vui lòng nhập lại");
            }
        }
    }
}
