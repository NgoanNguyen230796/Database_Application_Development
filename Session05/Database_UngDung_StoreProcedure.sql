create database quan_ly_san_pham;
use quan_ly_san_pham;
drop table Category;
drop table product;
-- 1. Tạo bảng Danh mục sản phẩm
create table Category
(
    category_Id     int primary key auto_increment,
    category_Name   varchar(50) not null unique,
    description     text,
    category_status bit default (1)
);
-- 2. Tạo bảng sản phẩm
create table Product
(
    product_Id     varchar(5) primary key,
    product_Name   varchar(100) not null unique,
    date_Created   date  default (curdate()),
    price          float default (0),
    description    text,
    title          varchar(200),
    category_Id    int,
    product_status bit   default (1),
    -- Mã danh mục - int - FK references Category(category_Id)
    foreign key (category_Id) references Category (category_Id)
);

-- 3. Thêm các dữ liệu vào 2 bảng
-- 3.1 Thêm các dữ liệu vào bảng Danh mục
insert into category (category_Id, category_Name, description, category_status)
values (1, 'DM01', 'Danh Muc 01', 0),
       (2, 'DM02', 'Danh Muc 02', 1),
       (3, 'DM03', 'Danh Muc 03', 1),
       (4, 'DM04', 'Danh Muc 04', 1),
       (5, 'DM05', 'Danh Muc 05', 0),
       (6, 'DM06', 'Danh Muc 06', 0);
-- 3.2 Thêm các dữ liệu vào bảng sản phẩm

insert into Product (product_Id, product_Name, date_Created, price, description, title, category_Id, product_status)
values (1, 'SP01', '2023-8-19', 2000, 'Hàng thu đông', 'Tittle 01', 1, 0),
       (2, 'SP02', '2023-9-11', 5000, 'Hàng mùa hè', 'Tittle 02', 5, 1),
       (3, 'SP03', '2023-4-20', 7000, 'Hàng dễ vỡ', 'Tittle 03', 4, 0),
       (4, 'SP04', '2023-7-1', 65000, 'Hàng mùa xuân', 'Tittle 04', 2, 1),
       (5, 'SP05', '2023-3-29', 35000, 'Đồ điện tử', 'Tittle 05', 3, 0),
       (6, 'SP06', '2023-5-5', 82000, 'Đồ gia dụng', 'Tittle 06', 2, 1),
       (7, 'SP07', '2023-9-1', 25000, 'Đồ gia dụng giá rẻ', 'Tittle 07', 2, 0),
       (8, 'SP08', '2023-2-9', 11000, 'Đồ gia công bằng tay', 'Tittle 08', 6, 0);

/*
    4. Tạo view gồm các sản phẩm có giá lớn hơn 20000 gồm các thông tin sau:
    mã danh mục, tên danh mục, trạng thái danh mục, mã sản phẩm, tên sản phẩm,
    giá sản phẩm, trạng thái sản phẩm
 */
-- Tạo view
create view vw_information
as
select ca.category_Id,
       ca.category_Name,
       ca.category_status,
       pr.product_Id,
       pr.product_Name,
       pr.price,
       pr.product_status
from category ca
         join product pr on ca.category_Id = pr.category_Id
where pr.price > 20000;
select *
from vw_information;

update category
set category_Name = 'DM01 New'
where category_Id = 1;
-- 5. Tạo các procedure sau:
/*
    procedure cho phép thêm, sửa, xóa, lấy tất cả dữ liệu, lấy dữ liệu theo mã
    của bảng danh mục và sản phẩm
*/

-- 5.1 procedure cho phép thêm danh mục
DELIMITER //
create procedure insert_data_category(
    in input_category_Name varchar(50),
    in input_description text,
    input_category_status bit
)
BEGIN
    insert into Category(category_Name, description, category_status)
    values (input_category_Name, input_description, input_category_status);
end //
DELIMITER ;

call insert_data_category('DM012', 'Danh Muc 12', 1);
select *
from Category;

-- 5.2 procedure cho phép cập nhật danh mục
DELIMITER //
create procedure update_data_category(
    in input_category_Id int,
    in input_category_Name varchar(50),
    in input_description text,
    input_category_status bit
)
BEGIN
    update Category
    set category_Id    = input_category_Id,
        category_Name  = input_category_Name,
        description    =input_description,
        category_status=input_category_status
    where category_Id = input_category_Id;

end //
DELIMITER ;
call update_data_category('1', 'DM01 Update',
    'Danh Muc 1 Update', 0);

-- 5.3 procedure cho phép xóa danh mục

DELIMITER //
create procedure delete_data_category(
    in input_category_Id int
)
BEGIN
    delete from category where category_Id = input_category_Id;
end //
DELIMITER ;
call delete_data_category(13);

-- 5.4 procedure cho phép thêm mới sản phẩm

DELIMITER //
create procedure insert_data_product(
    in input_product_Id varchar(5),
    in input_product_Name varchar(100),
    in input_date_Created date,
    in input_price float,
    in input_description text,
    in input_title varchar(200),
    in input_category_Id int,
    in input_product_status bit
)
BEGIN
    insert into Product(product_Id, product_Name, date_Created, price, description, title, category_Id, product_status)
    values (input_product_Id, input_product_Name, input_date_Created, input_price, input_description, input_title,
            input_category_Id, input_product_status);
end //
DELIMITER ;
select *
from product;
call insert_data_product(9, 'SP09', '2023-9-11',
    56000, 'Sữa rửa mặt', 'Tittle 09', 4, 0);

-- 5.5 procedure cho phép cập nhật sản phẩm

DELIMITER //
create procedure update_data_product(
    in input_product_Id varchar(5),
    in input_product_Name varchar(100),
    in input_date_Created date,
    in input_price float,
    in input_description text,
    in input_title varchar(200),
    in input_category_Id int,
    in input_product_status bit
)
BEGIN
    update product
    set product_Id=input_product_Id,
        product_Name=input_product_Name,
        date_Created=input_date_Created,
        price=input_price,
        description=input_description,
        title=input_title,
        category_Id=input_category_Id,
        product_status=input_product_status
    where product_Id = input_product_Id;
end //
DELIMITER ;
call update_data_product(9, 'SP09 Update', '2023-1-9',
    45000, 'Mặt nạ hàn quốc', 'Title 09 Update', 3, 1);

-- 5.6 procedure cho phép xóa sản phẩm theo id
DELIMITER //
create procedure delete_data_Product(
    in input_product_Id int
)
BEGIN
    delete
    from Product
    where product_Id = input_product_Id;
end //
DELIMITER ;

call delete_data_Product(9);

/*
    procedure cho phép lấy ra tất cả các phẩm có trạng thái là 1
    bao gồm mã sản phẩm, tên sản phẩm, giá, tên danh mục, trạng thái sản phẩm
*/

DELIMITER //
create procedure get_data_product_by_status_1()
BEGIN
    select pr.product_Id                                                             as 'Mã sản phẩm',
           pr.product_Name                                                           as 'Tên sản phẩm',
           pr.price                                                                  as 'Giá',
           ca.category_Name                                                          as 'Tên danh mục',
           (case when ca.category_status = 'true' then 'Active' else 'InActive' end) as 'Trạng thái sản phẩm'
    from Product pr
             join Category ca on pr.category_Id = ca.category_Id
    where pr.product_status = 1;
end //
DELIMITER ;

call get_data_product_by_status_1();

/*
    procedure cho phép thống kê số sản phẩm theo từng mã danh mục

*/

DELIMITER //
create procedure statistics_on_the_number_of_products_by_category()
BEGIN
    select ca.category_Id as 'Mã danh mục', count(*) as 'Số lượng sản phẩm theo từng danh mục'
    from category ca
             join product pr on ca.category_Id = pr.category_Id
    group by ca.category_Id;
end //
DELIMITER ;

call statistics_on_the_number_of_products_by_category();

/*
     procedure cho phép tìm kiếm sản phẩm theo tên sản phầm: mã sản phẩm, tên
    sản phẩm, giá, trạng thái sản phẩm, tên danh mục, trạng thái danh mục

*/

DELIMITER //
create procedure search_data_product(
    in input_data_search varchar(30)
)
BEGIN
    select pr.product_Id                                                             as 'Mã sản phẩm',
           pr.product_Name                                                           as 'Tên sản phẩm',
           pr.price                                                                  as 'Giá',
           (case when pr.product_status = 'true' then 'Active' else 'InActive' end)  as 'Trạng thái sản phẩm',
           ca.category_Name                                                          as 'Tên danh mục',
           (case when ca.category_status = 'true' then 'Active' else 'InActive' end) as 'Trạng thái danh mục'

    from Product pr
             join Category ca on pr.category_Id = ca.category_Id
    where pr.product_Name like concat('%', input_data_search, '%');
end //
DELIMITER ;

call search_data_product('4');

