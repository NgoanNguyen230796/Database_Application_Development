use jdbc_demo;
select *
from product;
create table product
(
    productId     int primary key auto_increment,
    productName   varchar(100) not null unique,
    price         float,
    productStatus bit
);
-- Lấy tất cả dữ liệu trong bảng product
DELIMITER //
create procedure get_all_product()
BEGIN
    select * from product;
end //
DELIMITER ;
call get_all_product();
-- Thêm mới dữ liệu cho bảng procduct
DELIMITER //
create procedure creat_data_product(
    in input_productName varchar(100),
    in input_price float,
    in input_productStatus bit
)
BEGIN
    insert into product(productName, price, productStatus)
    values (input_productName, input_price, input_productStatus);
end //
DELIMITER ;

call creat_data_product('SP01', 20000, 1);

-- Cập nhật dữ liệu cho bảng procduct
DELIMITER //
create procedure update_data_product(
    in input_productId int,
    in input_productName varchar(100),
    in input_price float,
    in input_productStatus bit
)
BEGIN
    update product
    set productName=input_productName,
        price=input_price,
        productStatus=input_productStatus
    where productId = input_productId;
end //
DELIMITER ;

-- Xóa dữ liệu cho bảng procduct

DELIMITER //
create procedure delete_data_product(
    in input_productId int
)
BEGIN
    delete
    from product
    where productId = input_productId;
end //
DELIMITER ;

-- Dựa vào mã sản phẩm lấy thông tin sản phẩm
DELIMITER //
create procedure get_product_by_id(
    in input_productId int
)
BEGIN
    select * from product where productId = input_productId;
end //
DELIMITER ;
-- Tìm kiếm sản phẩm theo mã sản phẩm

DELIMITER //
create procedure search_data_by_productId(
    in input_productId int
)
BEGIN
    select * from product p
    where p.productId =input_productId;
end //
DELIMITER ;
-- Thống kê giá sản phẩm có giá từ fromPrice đến toPrice

DELIMITER //
create procedure get_cntProduct(
    in fromPrice float,
    in toPrice float,
    out cnt int
)
BEGIN
    select count(p.productId) into cnt
    from product p
    where p.price between fromPrice and toPrice;
end //
DELIMITER ;



