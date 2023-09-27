drop database shop_management;
create database shop_management;
use shop_management;
-- Tạo bảng Categories
create table Categories
(
    catalogId     int primary key auto_increment,
    catalogName   varchar(100) not null unique,
    priority      int,
    catalogStatus bit default (1)
);
-- Tạo bảng Product
create table Product
(
    productId     varchar(5) primary key,
    productName   varchar(100) not null unique,
    price         float check (price > 0),
    creates       date default (current_date),
    quantity      int  default (0),
    view          int  default (0),
    catalogId     int,
    productStatus bit  default (1),
    -- catalogId FK – Categories
    foreign key (catalogId) references Categories (catalogId)
);
select *
from Product;
-- Lấy toàn bộ dữ liệu của bảng Categories (chỉ hiển thị các danh mục có status = 1)
DELIMITER //
create procedure get_all_data_categories()
BEGIN
    select *
    from Categories ca
    where ca.catalogStatus = 1;
end //
DELIMITER ;

-- Thêm mới dữ liệu cho bảng Categories
DELIMITER //
create procedure create_data_categories(
    in input_catalogName varchar(100),
    in input_priority int,
    in input_catalogStatus bit
)
BEGIN
    insert into Categories(catalogName, priority, catalogStatus)
    VALUES (input_catalogName, input_priority, input_catalogStatus);
end //
DELIMITER ;
-- Cập nhật dữ liệu cho bảng categories
DELIMITER //
create procedure update_data_categories(
    in input_catalogId int,
    in input_catalogName varchar(100),
    in input_priority int
)
BEGIN
    update Categories
    set catalogName=input_catalogName,
        priority=input_priority
    where catalogId = input_catalogId;
end //
DELIMITER ;

-- Xóa dữ liệu cho bảng categories
DELIMITER //
create procedure delete_data_categories(
    in input_catalogId int
)
BEGIN
    delete
    from Categories
    where catalogId = input_catalogId;
end //
DELIMITER ;

--  Hiển thị danh mục sắp xếp theo độ ưu tiên tăng dần categories
DELIMITER //
create procedure get_all_data_categories_by_priority()
BEGIN
    select *
    from Categories
    order by priority;
end //
DELIMITER ;

-- Dựa vào mã danh muc lấy thông tin categoriesId
DELIMITER //
create procedure get_categories_by_id(
    in input_catalogId int
)
BEGIN
    select * from Categories where catalogId = input_catalogId;
end //
DELIMITER ;

-- Tìm kiếm danh mục theo tên categoriesName
DELIMITER //
create procedure search_data_by_categoriesName(
    in input_categoriesName varchar(100)
)
BEGIN
    select *
    from Categories ca
    where ca.catalogName like concat('%', input_categoriesName, '%');
end //
DELIMITER ;

-- Cập nhật thông tin danh mục categories
DELIMITER //
create procedure update_data_categories(
    in input_catalogId int,
    in input_catalogName varchar(100),
    in input_priority int)
BEGIN
    update Categories
    set catalogName=input_catalogName,
        priority=input_priority
    where catalogId = input_catalogId;

end //
DELIMITER ;
-- Cập nhật trạng thái danh mục catalogStatus
DELIMITER //
create procedure update_data_by_catalogStatus(
    in input_catalogId int,
    in input_catalogStatus bit
)
BEGIN
    update Categories
    set catalogStatus=input_catalogStatus
    where catalogId = input_catalogId;
end //
DELIMITER ;

-- Lấy toàn bộ dữ liệu của bảng Product với những sản phẩm có productStatus=1
DELIMITER //
create procedure get_all_data_product()
BEGIN
    select *
    from Product pr
    where pr.productStatus = 1;
end //
DELIMITER ;
-- Lấy toàn bộ dữ liệu của bảng Product
DELIMITER //
create procedure get_data_product()
BEGIN
    select * from Product;
end //
DELIMITER ;

-- Hiển thị sản phẩm theo giá giảm dần
DELIMITER //
create procedure get_all_data_product_by_price()
BEGIN
    select *
    from Product pr
    order by pr.price desc;
end //
DELIMITER ;
call get_all_data_product_by_price();

-- Thêm mới dữ liệu cho bảng Product
DELIMITER //
create procedure create_data_product(
    in input_productId varchar(5),
    in input_productName varchar(100),
    in input_price float,
    in input_creates date,
    in input_quantity int,
    in input_view int,
    in input_productStatus bit,
    in input_catalogId int
)
BEGIN
    insert into Product(productId, productName, price, creates, quantity, view, productStatus, catalogId)
    VALUES (input_productId, input_productName, input_price, input_creates, input_quantity, input_view,
            input_productStatus, input_catalogId);
end //
DELIMITER ;

-- Dựa vào mã sản phẩm lấy thông tin productId
DELIMITER //
create procedure get_product_by_id(
    in input_productId varchar(5)
)
BEGIN
    select * from Product where productId = input_productId;
end //
DELIMITER ;

-- Cập nhật dữ liệu cho bảng product
DELIMITER //
create procedure update_data_product(
    in input_productId varchar(5),
    in input_productName varchar(100),
    in input_price float,
    in input_creates date,
    in input_quantity int,
    in input_view int,
    in input_catalogId int
)
BEGIN
    update Product
    set productName=input_productName,
        price=input_price,
        creates=input_creates,
        quantity=input_quantity,
        view=input_view,
        catalogId=input_catalogId
    where productId = input_productId;
end //
DELIMITER ;

-- Cập nhật trạng thái cho bảng product
DELIMITER //
create procedure update_data_by_productStatus(
    in input_productId varchar(5),
    in input_productStatus bit
)
BEGIN
    update Product
    set productStatus=input_productStatus
    where productId = input_productId;
end //
DELIMITER ;
-- Xóa dữ liệu cho bảng product
DELIMITER //
create procedure delete_data_product(
    in input_productId varchar(5)
)
BEGIN
    delete
    from Product
    where productId = input_productId;
end //
DELIMITER ;

-- Tìm kiếm sản phẩm theo tên sản phẩm
DELIMITER //
create procedure search_data_by_productName(
    in input_productName varchar(100)
)
BEGIN
    select *
    from Product pr
    where pr.productName like concat('%', input_productName, '%');
end //
DELIMITER ;

-- Tìm kiếm sản phẩm trong khoảng giá a-b

DELIMITER //
create procedure search_data_fromPrice_ToPrice(
    in fromPrice float,
    in toPrice float
)
BEGIN
    select *
    from Product pr
    where pr.price between fromPrice and toPrice;
end //
DELIMITER ;

-- Bán sản phẩm
DELIMITER //
create procedure sell_products(
    in input_productId varchar(5),
    in input_quantity int
)
BEGIN
    update Product
    set quantity=quantity - input_quantity
    where productId = input_productId;
end //
DELIMITER ;

-- Thống kê số danh mục theo trạng thái danh mục
DELIMITER //
create procedure statistics_categories_by_categoryStatus()
BEGIN
    select ca.catalogStatus, count(ca.catalogId) as 'countByCatalogStatus'
    from Categories ca
    group by ca.catalogStatus;
end //
DELIMITER ;

call statistics_categories_by_categoryStatus();

-- Thống kê số lượng sản phẩm theo trạng thái sản phẩm
DELIMITER //
create procedure statistics_product_by_productStatus()
BEGIN
    select pr.productStatus, count(pr.productId) as 'countByProductStatus'
    from Product pr
    group by pr.productStatus;
end //
DELIMITER ;
call statistics_product_by_productStatus();
-- Thống kê số lượng sản phẩm theo từng danh mục
DELIMITER //
create procedure statistics_products_by_category()
BEGIN
    select ca.catalogName, count(pr.productId) as 'cntProductByCatalogName'
    from Product pr
             join Categories ca on pr.catalogId = ca.catalogId
    group by ca.catalogName;
end //
DELIMITER ;
call statistics_products_by_category();







