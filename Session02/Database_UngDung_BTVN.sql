/*
	1.Tạo bảng Product gồm các thông tin sau:
		- Product_Id -mã sản phẩm-char(5)-khóa chính
        -Product_Name-tên sản phẩm-varchar(100)-not null-unique
        -Price-float-có giá trị lớn hơn 0
        -Description -text-not null
        -Product_status-bit-mặc định là 1
        
	2.Tạo bảng Order-hóa đơn gồm các thông tin sau
		-Order_id -Mã hóa đơn-int-tự tăng
        -Created -Ngày tạo-date-mặc định là ngày hiện tại
        -Total_amout-float
        -Order_Status-bit mặc định là 0
	3. Tạo bảng Order-Detail - Hóa đơn chi tiết gồm các thông tin sau:
    - Order_id - Mã hóa đơn - khóa ngoại của bảng Order
    - Product_id - mã sản phẩm - khóa ngoại của bảng product
    - (Order_id,Product_id) xác định khóa chính của bảng Order_Detail
    - Price - Giá sản phẩm lúc mua - float
    - Quantity - Số lượng sản phẩm mua - int
    - Amount - Thành tiền - tính tự động bằng công thức: price * quantity


*/

create database buy_and_sell;
use buy_and_sell;
create table Product(
	-- Product_Id -mã sản phẩm-char(5)-khóa chính
	product_Id char(5) primary key,
    -- Product_Name-tên sản phẩm-varchar(100)-not null-unique
    product_Name varchar(100) not null unique,
    -- Price-float-có giá trị lớn hơn 0
    price float check(price>0),
    -- Description -text-not null
    description text not null,
    -- Product_status-bit-mặc định là 1
    product_status bit default (1)
);
insert into Product(product_Id,product_Name,price,description,product_status)
values 
	(1,'Fish sauce',20000,'Nam Ngu fish sauce',0),
    (2,'Instant noodle',5000,'Perfect instant noodles',1),
	(3,'shampoo',15000,'tsubaki shampoo',0)
;
-- Tạo bảng Order-hóa đơn gồm các thông tin sau
create table Orders(
	-- Order_id -Mã hóa đơn-int-tự tăng
    order_Id int primary key auto_increment,
	-- Created -Ngày tạo-date-mặc định là ngày hiện tại
    createds date default (CURRENT_DATE()),
	-- Total_amout-float
    total_amout float,
	-- Order_Status-bit mặc định là 0
    order_Status bit default (0)
);
-- Thêm giá trị vào bảng Orders
insert into Orders(total_amout,order_Status)
values 
	-- 2 chai nước mắm nam ngư
	(40000,0),
    -- 3 gói mỳ tôm hảo hảo
    (15000,1),
    -- 1 dây dầu gội 
	(15000,0)
;

-- 3.Tạo bảng Order-Detail-Hóa đơn chi tiết gồm các thông tin sau:
create table Order_Detail(
	-- Order_id -Mã hóa đơn-khóa ngoại của bảng Order
    order_Id int not null,
    -- Product_id-mã sản phẩm-khóa ngoại của bảng product
    product_Id char(5) not null,
    -- Price - Giá sản phẩm lúc mua - float
    price float,
    -- Quantity - Số lượng sản phẩm mua - int
    quantity int,
    -- Amount - Thành tiền - tính tự động bằng công thức: price * quantity
    amount float as (price*quantity),
     foreign key(product_Id) references Product(product_Id),
	 foreign key(order_Id) references Orders(order_Id),
     -- Order_id,Product_id -xác định khóa chính của bảng Order-Detail
	 primary key(order_Id,product_Id)
);
-- Thêm giá trị vào bảng Order_Detail và tính toán thành tiền tính tự động bằng công thức: price * quantity 
insert into Order_Detail(order_Id,product_Id,price,quantity)
values 
	(1,1,20000,2),
    (2,2,15000,3),
    (3,3,15000,1)
;


