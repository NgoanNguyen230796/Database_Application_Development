-- 1. Tạo CSDL QuanLyBanHang
create database QuanLyBanHang;
-- 2. Sử dụng CSDL QuanLyBanHang để tạo bảng
use QuanLyBanHang;
/*
	3.Tạo bảng Customer lưu danh sách những khách hàng đã đến cửa hàng gồm cả những người có mua hàng và cả những người không mua hàng.
		cID  -mã khách-varchar(5)-khóa chính
        cName-tên khách hàng-nvarchar(50)-not null
        cAge -tuổi -int 
*/
create table Customer(
	cID char(5) primary key,
    cName varchar(50) not null,
    cAge int
);
/*
	4.Tạo bảng Product gồm các thông tin sau:
		- pID -mã sản phẩm-char(5)-khóa chính
        - pName-tên sản phẩm-varchar(100)-not null-unique
        - pPrice-giá sản phẩm-float-có giá trị lớn hơn 0
*/
create table Product(
	-- pID -mã sản phẩm-char(5)-khóa chính
	pID char(5) primary key,
    -- pName-tên sản phẩm-varchar(100)-not null-unique
    pName varchar(100) not null unique,
    -- pPrice-giá sản phẩm-float-có giá trị lớn hơn 0
    pPrice float check(pPrice>0)
);

/*
	5.Tạo bảng Orders-hóa đơn gồm các thông tin sau
		- oID -Mã hóa đơn-int-tự tăng -khóa chính
        - cID -mã khách-varchar(5)-khóa ngoại của bảng Customer(cID)
        - oDate-Ngày tạo-date-mặc định là ngày hiện tại
        - oTotalPrice-Tổng giá -float -not null
*/
create table Orders(
	-- oID -Mã hóa đơn-int-tự tăng -khóa chính
	oID int auto_increment,
    -- cID -mã khách-varchar(5)-khóa ngoại của bảng Customer(cID)
    cID varchar(5),
    -- oDate-Ngày tạo-date-mặc định là ngày hiện tại
    oDate date default(current_date()),
    -- oTotalPrice-Tổng giá -float -not null
    oTotalPrice float not null,
    -- cID khóa ngoại của bảng Customer(cID)
    foreign key (cID) references Customer(cID),
	primary key(oID)
);

/*
	6.Tạo bảng Order_Detail -Hóa đơn chi tiết gồm các thông tin sau:
		- oID -Mã hóa đơn-int-tự tăng -khóa ngoại của bảng Orders
        - pID -mã sản phẩm-char(5)-khóa ngoại của bảng Product
        - odQTY-Số lượng sản phẩm mua - int
        - (oID,pID) - khóa chính của bảng Order_Detail
*/
create table Order_Detail(
	-- oID -Mã hóa đơn-int-tự tăng -khóa ngoại của bảng Orders
	oID int ,
    -- pID -mã sản phẩm-char(5)-khóa ngoại của bảng Product
    pID char(5),
    -- odQTY-Số lượng sản phẩm mua - int
    quantity int not null,
    -- khóa ngoại của bảng Orders ,Product
    foreign key (oID) references Orders(oID),
    foreign key (pID) references Product(pID),
    -- (oID,pID) - khóa chính của bảng Order_Detail
    primary key(oID,pID)
);


