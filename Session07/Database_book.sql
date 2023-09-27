create database quan_ly_book;
use quan_ly_book;
create table book
(
    bookId   varchar(4) primary key,
    bookName varchar(50),
    price    float check (price > 0),
    author   varchar(50),
    status   bit
);
-- Lấy toàn bộ dữ liệu của bảng book
DELIMITER //
create procedure get_all_data()
BEGIN
    select * from book;
end //
DELIMITER ;
-- Thêm mới dữ liệu cho bảng book
DELIMITER //
create procedure create_data_book(
    in input_bookId varchar(4),
    in input_bookName varchar(50),
    in input_price float,
    in input_author varchar(50),
    in input_status bit
)
BEGIN
    insert into book(bookId, bookName, price, author, status)
    VALUES (input_bookId, input_bookName, input_price, input_author, input_status);
end //
DELIMITER ;

call create_data_book('B001', 'Sach giao khoa', 250000,'Thu An',1);

-- Cập nhật dữ liệu cho bảng book
DELIMITER //
create procedure update_data_book(
    in input_bookId varchar(4),
    in input_bookName varchar(50),
    in input_price float,
    in input_author varchar(50),
    in input_status bit
)
BEGIN
    update book
    set bookName=input_bookName,
        price=input_price,
        author=input_author,
        status=input_status
    where bookId = input_bookId;
end //
DELIMITER ;

-- Xóa dữ liệu cho bảng book
DELIMITER //
create procedure delete_data_book(
    in input_bookId varchar(4)
)
BEGIN
    delete
    from book
    where bookId = input_bookId;
end //
DELIMITER ;

-- Dựa vào mã sách lấy thông tin sách
DELIMITER //
create procedure get_book_by_id(
    in input_bookId varchar(4)
)
BEGIN
    select * from book where bookId = input_bookId;
end //
DELIMITER ;

-- Tìm kiếm sách theo mã sách
DELIMITER //
create procedure search_data_by_bookName(
    in input_bookName varchar(50)
)
BEGIN
    select * from book b
    where b.bookName like concat('%',input_bookName,'%');
end //
DELIMITER ;

call search_data_by_bookName('T');
-- Thống kê sách theo tác giả
DELIMITER //
create procedure book_statistics_by_author()
BEGIN
    select b.author,count(b.bookId) as bookCount
    from book b
    group by b.author;
end //
DELIMITER ;

-- Sắp xếp sách theo giá tăng dần
DELIMITER //
create procedure ascending_price()
BEGIN
    select *
    from book b
    order by b.price;
end //
DELIMITER ;
