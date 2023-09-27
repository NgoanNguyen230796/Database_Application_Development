-- 1.Tạo database Trigger_Demo
create database Trigger_Demo;
use Trigger_Demo;
-- 2.Tạo bảng product gồm các trường :productId(pk),productName,Price(float)
create table product(
                        productId int primary key,
                        productName varchar(20),
                        price float
);
-- 3.Tạo 1 tringger đc kích hoạt từ trước khi sự kiện insert đc xảy ra trên bảng
-- product ngăn chặn insert các dữ liệu có price nhỏ hơn 0
DELIMITER //
create trigger  before_insert_product_data before insert on product for each row
begin
    -- product ngăn chặn insert các dữ liệu có price nhỏ hơn 0
    if (NEW.price<0) then
		SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = 'Giá nhập vào phải lớn hơn 0 ,vui lòng nhập lại';
end if;
end //
DELIMITER ;
insert into product
values(2,'SP02',30);
select * from product;
-- 4.Tạo 1 trigger được kích hoạt trước khi sự kiện update được xảy ra trên bảng
-- product để xử lý gán lại price =0 nếu như price cập nhật < 0

DELIMITER //
create trigger  before_update_product_data before update on product for each row
begin
    -- product ngăn chặn update các dữ liệu có price nhỏ hơn 0
    if (NEW.price<0) then
    -- nếu như price cập nhật < 0
		set NEW.price=0;
end if;
end //
DELIMITER ;

update product
set price=-20
where productId=1;
