package ra.business;

import ra.connect.connectDB;
import ra.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookBusiness {
    public static List<Book> getAllBook() throws SQLException {
        //1.Tạo đối tượng Connection
        //2.Tạo đối tượng CallableStatement
        //3.Gọi procedue
        //4.Xử lý dữ liệu và trả về listProduct

        List<Book> listBook = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBook = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                listBook.add(book);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return listBook;
    }

    public static boolean creatDataBook(Book book) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call create_data_book(?,?,?,?,?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, book.getBookId());
            callSt.setString(2, book.getBookName());
            callSt.setFloat(3, book.getPrice());
            callSt.setString(4, book.getAuthor());
            callSt.setBoolean(5, book.getStatus());
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateBook(Book book) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call update_data_book(?,?,?,?,?)}");
            callSt.setString(1, book.getBookId());
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(2, book.getBookName());
            callSt.setFloat(3, book.getPrice());
            callSt.setString(4, book.getAuthor());
            callSt.setBoolean(5, book.getStatus());
            // đăng ký kiểu dữ liệu cho tham số trả ra--->k có dữ liệu trả ra
            // Thực hiện gọi procrdue
            callSt.executeUpdate();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Book getBookById(String bookId) throws SQLException {
        Book book = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call get_book_by_id(?)}");
            //set giá trị tham số vào
            callSt.setString(1, bookId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()) {
                book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return book;
    }

    public static boolean deleteDataBook(String booktId) throws SQLException {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call delete_data_book(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, booktId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Book> searchDataBook(String bookName) throws SQLException {
        List<Book> listBook = null;
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call search_data_by_bookName(?)}");
            // set dữ liệu cho các tham số vào của procedue
            callSt.setString(1, bookName);
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBook = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                listBook.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return listBook;
    }

    public static int bookStatisticsByAuthor() throws SQLException {
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        int cntBook = 0;
        String authorName = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call book_statistics_by_author()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                authorName = rs.getString("author");
                cntBook = rs.getInt("bookCount");
                System.out.printf("%-20s%-20d\n", authorName, cntBook);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return cntBook;
    }

    public static List<Book> ascendingPrice() throws SQLException {
        //1.Tạo đối tượng Connection
        //2.Tạo đối tượng CallableStatement
        //3.Gọi procedue
        //4.Xử lý dữ liệu và trả về listProduct

        List<Book> listBook = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = connectDB.openConnection();
            callSt = conn.prepareCall("{call ascending_price()}");
            //Thực hiện gọi procedue
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listBook = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setPrice(rs.getFloat("price"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getBoolean("status"));
                listBook.add(book);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connectDB.closeConnection(conn, callSt);
        }
        return listBook;
    }


}
