package ra.run;

import ra.business.BookBusiness;
import ra.entity.Book;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static ra.entity.Book.*;

public class BookManagement {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        do {
            System.out.println("********************MENU********************");
            System.out.println("1.Hiển thị thông tin sách");
            System.out.println("2.Thêm mới sách");
            System.out.println("3.Cập nhật sách");
            System.out.println("4.Xóa sách");
            System.out.println("5.Tìm kiếm sách theo tên sách");
            System.out.println("6.Thống kê sách theo tác giả");
            System.out.println("7.Sắp xếp sách theo giá tăng dần");
            System.out.println("8.Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = validateChoice();
            switch (choice) {
                case 1:
                    BookManagement.displayListBook();
                    break;
                case 2:
                    BookManagement.creatDataBook(sc);
                    break;
                case 3:
                    BookManagement.displayListBook();
                    BookManagement.updateDataBook(sc);
                    break;
                case 4:
                    BookManagement.displayListBook();
                    BookManagement.deleteDataBook(sc);
                    break;
                case 5:
                    BookManagement.displayListBook();
                    BookManagement.searchDataBook(sc);
                    break;
                case 6:
                    BookManagement.getCntBookByAuthorName();
                    break;
                case 7:
                    BookManagement.displayAscendingPrice();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn của bạn từ 1-8");

            }
        } while (true);
    }

    //Validate choice
    public static int validateChoice() {
        while (true) {
            String inputChoiceStr = sc.nextLine().trim();
            if (inputChoiceStr.isEmpty()) {
                System.err.println("Lựa chọn không được để trống,vui lòng nhập lại");
            } else {
                try {
                    int choiceMenu = Integer.parseInt(inputChoiceStr);
                    if (choiceMenu > 0) {
                        return choiceMenu;
                    } else {
                        System.err.println("Lựa chọn phải là số nguyên lớn hơn 0,vui lòng nhập lại");
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Lựa chọn phải là số,vui lòng nhập lại");
                }
            }
        }
    }

    //1.Hiển thị dữ liệu trên database
    public static void displayListBook() throws SQLException {
        List<Book> listBook = BookBusiness.getAllBook();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Mã sách", "Tên sách", "Giá", "Tác giả", "Trạng thái");
        listBook.forEach(Book::displayData);
    }

    //2.Thêm mới dữ liệu
    public static void creatDataBook(Scanner sc) throws SQLException {
        // Nhập dữ liệu cho 1 sản phẩm để thêm mới
        Book bookNew = new Book();
        bookNew.inputData(sc);
        // Gọi creatProduct của ProductBusiness để thực hiện thêm dữ liệu vào DB
        boolean result = BookBusiness.creatDataBook(bookNew);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
        }
    }

    //3.Cập nhật dữ liệu
    public static void updateDataBook(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sách cần cập nhật :");
        String bookIdUpdate = sc.nextLine();
        //Kiểm tra mã sách có tồn tại hay không
        Book book = BookBusiness.getBookById(bookIdUpdate);
        if (book != null) {
            //Mã sách tồn tại trong CSDL
            System.out.println("Nhập vào tên sách cần cập nhật");
            book.setBookName(inputBookName());
            System.out.println("Nhập vào giá sách cần cập nhật");
            book.setPrice(inputBookPrice());
            System.out.println("Nhập vào tên tác giả cần cập nhật");
             book.setAuthor(inputBookAuthor());
            System.out.println("Nhập vào trạng thái cần cập nhật");
            book.setStatus(inputBookStatus());
            //Thực hiện cập nhật
            boolean result = BookBusiness.updateBook(book);
            if (result) {
                System.out.println("Mã sách :" + bookIdUpdate + " vừa được cập nhật thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sách không tồn tại");
        }

    }

    //4.Xóa dữ liệu
    public static void deleteDataBook(Scanner sc) throws SQLException {
        System.out.println("Nhập vào mã sách cần xóa :");
        String bookIdDelete = sc.nextLine();
        //Kiểm tra bookIdDelete có tồn tại trong CSDL hay k
        Book book = BookBusiness.getBookById(bookIdDelete);
        if (book != null) {
            boolean result = BookBusiness.deleteDataBook(bookIdDelete);
            if (result) {
                System.out.println("Mã sách :" + bookIdDelete + " vừa được xóa thành công");
            } else {
                System.out.println("Có lỗi xảy ra trong quá trình thực hiện,vui lòng thực hiện lại");
            }
        } else {
            //Mã sản phẩm k tồn tại trong CSDL
            System.err.println("Mã sách không tồn tại");
        }
    }

    //5.Tìm kiếm thông tin sách theo mã sách
    public static void searchDataBook(Scanner sc) throws SQLException {
        System.out.println("Nhập vào tên sách cần tìm kiếm :");
        String bookNameSearch = sc.nextLine();
        List<Book> listBook = BookBusiness.searchDataBook(bookNameSearch);
        if (listBook.size() != 0) {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Mã sách", "Tên sách", "Giá", "Tác giả", "Trạng thái");
            listBook.forEach(Book::displayData);
        } else {
            System.out.println("Không tìm thấy kết quả");
        }
    }
    public static void getCntBookByAuthorName() throws SQLException {
        System.out.println("Thống kê số lươợng sách theo tên tác giả là :");
        System.out.printf("%-20s%-20s\n", "Tác giả", "Số lượng sách");
        BookBusiness.bookStatisticsByAuthor();
    }

    public static void displayAscendingPrice() throws SQLException {
        List<Book> listBook = BookBusiness.ascendingPrice();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Mã sách", "Tên sách", "Giá", "Tác giả", "Trạng thái");
        listBook.forEach(Book::displayData);
    }




}
