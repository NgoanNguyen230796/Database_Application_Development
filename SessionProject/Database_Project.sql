create
    database quan_ly_kho;
use
    quan_ly_kho;

create table Product
(
    product_Id     char(5) primary key,
    product_Name   varchar(150) not null unique,
    manufacturer   varchar(200) not null,
    created        date                  default (current_date()),
    batch          smallint     not null,
    quantity       int          not null default (0),
    product_Status bit                   default (1)
);

create table Employee
(
    emp_Id        char(5) primary key,
    emp_Name      varchar(100) not null unique,
    birth_Of_Date date,
    email         varchar(100) not null,
    phone         varchar(100) not null,
    address       text         not null,
    emp_Status    smallint     not null
);

insert into Employee(emp_Id, emp_Name, birth_Of_Date, email, phone, address, emp_Status)
VALUES ('NV001', 'Nguyen Lan', '1996-8-25', 'lanNguyen@gmail.com', '01697575256', 'Ha Nam', 0),
       ('NV002', 'Tran Tu', '1995-4-3', 'tuTran@gmail.com', '01666107082', 'Nam Dinh', 0);


create table Account
(
    acc_Id     int primary key auto_increment,
    user_Name  varchar(30) not null unique,
    password   varchar(30) not null,
    permission bit default (1),
    emp_Id     char(5)     not null unique,
    acc_Status bit default (1),
    -- khóa ngoại của bảng Employee(emp_Id)
    foreign key (emp_Id) references Employee (emp_Id)
);

insert into Account(user_Name, password, permission, emp_Id, acc_Status)
VALUES ('user1', '891011', 1, 'NV009', 1),
('admin1', '1235698', 0, 'NV009', 1);

create table Bill
(
    bill_Id        bigint primary key auto_increment,
    bill_Code      varchar(10) not null unique,
    bill_Type      bit         not null,
    emp_Id_Created char(5)     not null,
    created        date                 default (current_date()),
    emp_Id_Auth    char(5)     null,
    auth_Date      date                 default (current_date()),
    bill_Status    smallint    not null default (0),
    --  Mã nhân viên nhập/xuất (emp_Id_Created) khóa ngoại của bảng Employee(emp_Id)
    foreign key (emp_Id_Created) references Employee (emp_Id),
    -- Mã nhân viên duyệt (emp_Id_Auth) khóa ngoại của bảng Employee(emp_Id)
    foreign key (emp_Id_Auth) references Employee (emp_Id)
);

create table Bill_Detail
(
    bill_Detail_Id bigint primary key auto_increment,
    bill_Id        bigint  not null,
    product_Id     char(5) not null,
    quantity       int     not null check (quantity > 0),
    price          float   not null check ( price > 0 ),
    -- Mã phiếu nhập/xuất(bill_Id) là khóa ngoại của Bill(bill_Id)
    foreign key (bill_Id) references Bill (bill_Id),
    -- Mã sản phẩm(product_Id) là khóa ngoại của Product(product_Id)
    foreign key (product_Id) references Product (product_Id)
);

drop table Bill_Detail;
drop table Bill;
-- get all data in table Product
DELIMITER //
create procedure get_all_data_product(
    -- Maximum data per page
    in data_of_page int,
    -- index of page
    in index_of_page int
)
BEGIN
    select *
    from Product
    limit data_of_page offset index_of_page;
end //
DELIMITER ;
-- get all data other input product_Id
DELIMITER //
create procedure get_all_data_product_other_product_Id(
    in input_product_Id char(5)
)
BEGIN
    select *
    from Product
    where product_Id <> input_product_Id;
end //
DELIMITER ;

-- count productId in table Product
DELIMITER //
create procedure get_count_all_data_product(
)
BEGIN
    select count(pr.product_Id) as 'cntProduct'
    from Product pr;
end //
DELIMITER ;



-- get data in table Product by_productId
DELIMITER //
create procedure get_product_by_productId(
    in input_product_Id char(5)
)
BEGIN
    select *
    from Product
    where product_Id = input_product_Id;
end //
DELIMITER ;
-- get data of table Product by_productName

DELIMITER //
create procedure get_product_by_productName(
    in input_product_Name varchar(150)
)
BEGIN
    select *
    from Product
    where product_Name = input_product_Name;
end //
DELIMITER ;

-- create data in table Product
DELIMITER //
create procedure create_data_product(
    in input_product_Id char(5),
    in input_product_Name varchar(150),
    in input_manufacturer varchar(200),
    in input_created date,
    in input_batch smallint,
    in input_product_Status bit
)
BEGIN
    insert into Product(product_Id, product_Name, manufacturer, created, batch, product_Status)
    VALUES (input_product_Id, input_product_Name, input_manufacturer, input_created, input_batch, input_product_Status);

end //
DELIMITER ;

-- update data in table Product
DELIMITER //
create procedure update_data_product(
    in input_product_Id char(5),
    in input_product_Name varchar(150),
    in input_manufacturer varchar(200),
    in input_created date,
    in input_batch smallint
)
BEGIN
    update Product
    set product_Name =input_product_Name,
        manufacturer=input_manufacturer,
        created=input_created,
        batch=input_batch
    where product_Id = input_product_Id;

end //
DELIMITER ;

-- search data in table Product by product_Name limit and offset

DELIMITER //
create procedure search_data_product_by_product_Name(
    -- Maximum data per page
    in data_of_page int,
    -- index of page
    in index_of_page int,
    in input_search_data varchar(150)
)
BEGIN
    select *
    from Product pr
    where product_Name like concat('%', input_search_data, '%')
    limit data_of_page offset index_of_page;
end //
DELIMITER ;

-- -- search data in table Product by product_Name get all
DELIMITER //
create procedure search_get_all_data_product_by_product_Name(
    in input_search_data varchar(150)
)
BEGIN
    select count(pr.product_Name) as 'cntProductNameSearch'
    from Product pr
    where product_Name like concat('%', input_search_data, '%');
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
    set product_Status=input_productStatus
    where product_Id = input_productId;
end //
DELIMITER ;

-- get all data in table EMPLOYEE not in Account
DELIMITER //
create procedure get_show_all_data_not_in_Account(
)
BEGIN
    select *
    from Employee
    where emp_Id NOT IN (select emy.emp_Id
                         from Employee emy
                                  join Account ac on emy.emp_Id = ac.emp_Id);
end //
DELIMITER ;

-- get data in table EMPLOYEE by_Emp_Id
DELIMITER //
create procedure get_employee_by_employeeId(
    in input_emp_Id char(5)
)
BEGIN
    select *
    from Employee
    where emp_Id = input_emp_Id;
end //
DELIMITER ;

-- get all data in table Employee
DELIMITER //
create procedure get_all_data_employee(
    -- Maximum data per page
    in data_of_page int,
    -- index of page
    in index_of_page int
)
BEGIN
    select *
    from Employee
    limit data_of_page offset index_of_page;
end //
DELIMITER ;
-- get all data other input product_Id
DELIMITER //
create procedure get_all_data_employee_other_employee_Id(
    in input_emp_Id char(5)
)
BEGIN
    select *
    from Employee
    where Employee.emp_Id <> input_emp_Id;
end //
DELIMITER ;

-- count productId in table Employee
DELIMITER //
create procedure get_count_all_data_employee(
)
BEGIN
    select count(emy.emp_Id) as 'cntEmployee'
    from Employee emy;
end //
DELIMITER ;

-- Lấy toàn bộ dữ liệu trong bảng nhân viên ,mà không phải phân trang

DELIMITER //
create procedure get_all_data_employee_not_pagination(
)
BEGIN
    select *
    from Employee em
             join Account ac on em.emp_Id = ac.emp_Id
    where ac.emp_Id = true;
end //
DELIMITER ;

DELIMITER //
create procedure get_all_data_employee_Name_Active(
)
BEGIN
    select *
    from Employee em
    where em.emp_Status = 0;
end //
DELIMITER ;

-- create data in table Employee
DELIMITER //
create procedure create_data_employee(
    in input_emp_Id char(5),
    in input_emp_Name varchar(100),
    in input_birth_Of_Date date,
    in input_email varchar(100),
    in input_phone varchar(100),
    in input_address text,
    in input_emp_Status smallint
)
BEGIN
    insert into Employee(emp_Id, emp_Name, birth_Of_Date, email, phone, address, emp_Status)
    VALUES (input_emp_Id, input_emp_Name, input_birth_Of_Date, input_email, input_phone, input_address,
            input_emp_Status);

end //
DELIMITER ;

-- get data of table Employee by_employeeName

DELIMITER //
create procedure get_employee_by_employeeName(
    in input_employee_Name varchar(100)
)
BEGIN
    select *
    from Employee
    where emp_Name = input_employee_Name;
end //
DELIMITER ;

-- update data in table Employee
DELIMITER //
create procedure update_data_employee(
    in input_emp_Id char(5),
    in input_emp_Name varchar(100),
    in input_birth_Of_Date date,
    in input_email varchar(100),
    in input_phone varchar(100),
    in input_address text,
    in input_emp_Status smallint
)
BEGIN
    update Employee
    set emp_Name     =input_emp_Name,
        birth_Of_Date=input_birth_Of_Date,
        email=input_email,
        phone=input_phone,
        address=input_address,
        emp_Status=input_emp_Status
    where emp_Id = input_emp_Id;

end //
DELIMITER ;

-- Cập nhật trạng thái cho bảng Employee
DELIMITER //
create procedure update_data_by_employeeStatus(
    in input_emp_Id char(5),
    in input_emp_Status smallint
)
BEGIN
    DECLARE isCheck BOOLEAN;
    set isCheck = (SELECT EXISTS(SELECT ac.emp_Id
                                 From Account ac
                                 where ac.emp_Id = input_emp_Id));
    if isCheck then
        update Employee emy
            inner join Account ac on
                emy.emp_Id = ac.emp_Id
        set emy.emp_Status=input_emp_Status,
            ac.acc_Status=IF(emy.emp_Status = 1 or emy.emp_Status = 2, 0, 1)
        where emy.emp_Id = input_emp_Id;
    else
        update Employee emy
        set emp_Status=input_emp_Status
        where emy.emp_Id = input_emp_Id;
    end if;
end //
DELIMITER ;

insert into Account(user_Name, password, permission, emp_Id, acc_Status)
VALUES ('user1', '891011', 1, 'NV001', 1);

select *
from Employee;
select *
from Account;

-- Tìm kiếm thông tin nhân viên theo mã hoặc theo tên nhân viên
DELIMITER //
create procedure search_data_employee_by_employeeName_or_employeeId(
    -- Maximum data per page
    in data_of_page int,
    -- index of page
    in index_of_page int,
    in input_search_data varchar(150)
)
BEGIN
    select *
    from Employee emy
    where emy.emp_Id like concat('%', input_search_data, '%')
       or emy.emp_Name like concat('%', input_search_data, '%')
    limit data_of_page offset index_of_page;
end //
DELIMITER ;

-- -- search data in table Product by product_Name get all
DELIMITER //
create procedure search_get_all_data_employee_by_employeeName_or_employeeId(
    in input_search_data varchar(150)
)
BEGIN
    select count(emy.emp_Id) as 'cntEmployeeSearch'
    from Employee emy
    where emy.emp_Id like concat('%', input_search_data, '%')
       or emy.emp_Name like concat('%', input_search_data, '%');
end //
DELIMITER ;


-- get all data in table Account
DELIMITER //
create procedure get_all_data_account(
    -- Maximum data per page
    in data_of_page int,
    -- index of page
    in index_of_page int
)
BEGIN
    select *
    from Account
    limit data_of_page offset index_of_page;
end //
DELIMITER ;

-- get data in table Account by_Account Id
DELIMITER //
create procedure get_account_by_accountId(
    in input_account_Id int
)
BEGIN
    select *
    from Account
    where acc_Id = input_account_Id;
end //
DELIMITER ;

-- get data in table Account by_User_name
DELIMITER //
create procedure get_account_by_user_name(
    in input_user_name varchar(30)
)
BEGIN
    select *
    from Account
    where user_Name = input_user_name;
end //
DELIMITER ;

-- get all data other input by_Account Id
DELIMITER //
create procedure get_all_data_account_other_account_Id(
    in input_account_Id int
)
BEGIN
    select *
    from Account
    where Account.acc_Id <> input_account_Id;
end //
DELIMITER ;

-- count AccountId in table Account
DELIMITER //
create procedure get_count_all_data_account(
)
BEGIN
    select count(acc.acc_Id) as 'cntAccount'
    from Account acc;
end //
DELIMITER ;

-- get data of table Account by userName
DELIMITER //
create procedure get_account_by_userName(
    in input_userName varchar(30)
)
BEGIN
    select *
    from Account
    where user_Name = input_userName;
end //
DELIMITER ;
-- get data of table Account by acc_id
DELIMITER //
create procedure get_account_by_acc_Id(
    in input_acc_Id int
)
BEGIN
    select *
    from Account
    where acc_Id = input_acc_Id;
end //
DELIMITER ;

-- -- create data in table Account
DELIMITER //
create procedure create_data_account(
    in input_user_Name varchar(30),
    in input_password varchar(30),
    in input_permission bit,
    in input_emp_Id char(5),
    in input_acc_Status bit
)
BEGIN
    insert into Account(user_Name, password, permission, emp_Id, acc_Status)
    VALUES (input_user_Name, input_password, input_permission, input_emp_Id, input_acc_Status);
end //
DELIMITER ;

-- update data in table Account Status
DELIMITER //
create procedure update_data_account_Status(
    in input_user_Name varchar(30),
    in input_acc_Status bit
)
BEGIN
    update Account
    set acc_Status=input_acc_Status
    where user_Name = input_user_Name;

end //
DELIMITER ;

-- Tìm kiếm thông tin tài khoản theo tên tài khoản
DELIMITER //
create procedure search_data_account_by_accountUserName_or_Emp_Name(
    in input_search varchar(100)
)
BEGIN
    select acc.acc_Id,
           acc.user_Name,
           acc.password,
           acc.permission,
           acc.acc_Status,
           acc.emp_Id,
           em.emp_Name as 'emName'
    from Account acc
             join Employee em on acc.emp_Id = em.emp_Id
    where acc.user_Name = input_search
       or em.emp_Name = input_search;
end //
DELIMITER ;

-- --------------------------------RECEIPT MANAGEMENT--------------------------------------------------
-- 1. Danh sách phiếu nhập từ bảng Bill

DELIMITER //
create procedure get_all_data_receipt()
BEGIN
    select *
    from Bill bi
    where bi.bill_Type = true;
end //
DELIMITER ;

-- 1.1 Danh sách phiếu xuất từ bảng Bill

DELIMITER //
create procedure get_all_data_bill()
BEGIN
    select *
    from Bill bi
    where bi.bill_Type = false;
end //
DELIMITER ;

-- 2. Danh sách phiếu nhập từ bảng Bill bill_Type = true,bill_Status=Tạo(0)
DELIMITER //
create procedure get_all_data_receipt_by_bill_Type_and_bill_Status0()
BEGIN
    select *
    from Bill bi
    where bi.bill_Type = true
      and bi.bill_Status = 0;
end //
DELIMITER ;
-- 2.2 Danh sách phiếu xuất từ bảng Bill bill_Type = true,bill_Status=Tạo(0)
DELIMITER //
create procedure get_all_data_bill_by_bill_Type_and_bill_Status0()
BEGIN
    select *
    from Bill bi
    where bi.bill_Type = false
      and bi.bill_Status = 0;
end //
DELIMITER ;

-- 2.3 Danh sách phiếu nhập từ bảng Bill bill_Type = true,bill_Status !=2(Duyệt)
DELIMITER //
create procedure get_all_data_receipt_by_bill_Type_and_bill_StatusOther2()
BEGIN
    select *
    from Bill bi
    where bi.bill_Type = true
      and bi.bill_Status <> 2;
end //
DELIMITER ;
-- 2.4 Danh sách phiếu xuất từ bảng Bill bill_Type = true,bill_Status !=2(Duyệt)
DELIMITER //
create procedure get_all_data_bill_by_bill_Type_and_bill_StatusOther2()
BEGIN
    select *
    from Bill bi
    where bi.bill_Type = false
      and bi.bill_Status <> 2;
end //
DELIMITER ;


-- 2. Danh sách phiếu nhập từ bảng Bill bill_Type = true,bill_Status!=Duyệt (2)
insert into bill(bill_Code, bill_Type, emp_Id_Created, created, emp_Id_Auth, auth_Date, bill_Status)
VALUES ('B001', 1, 'NV001', '2023-8-1', 'NV002', '2023-10-3', 0),
       ('B002', 0, 'NV004', '2023-9-4', 'NV002', '2023-10-1', 1),
       ('B003', 0, 'NV008', '2023-7-1', 'NV002', '2023-9-3', 2),
       ('B004', 1, 'NV007', '2023-5-1', 'NV002', '2023-7-4', 1);

-- 2.Tạo phiếu nhập và phiếu xuất
DELIMITER //
create procedure creat_data_receipt(
    in input_bill_Code varchar(10),
    in input_bill_Type bit,
    in input_emp_Id_Created char(5),
    in input_emp_Id_Auth char(5),
    in input_auth_Date      date,
    in input_bill_Status smallint
)
BEGIN
    insert into Bill(bill_Code, bill_Type, emp_Id_Created, emp_Id_Auth,auth_Date, bill_Status)
    VALUES (input_bill_Code, input_bill_Type, input_emp_Id_Created, input_emp_Id_Auth,input_auth_Date, input_bill_Status);
end //
DELIMITER ;


-- Duyệt phiếu nhập
DELIMITER //
create procedure creat_approve_data_receipt(
    in input_emp_Id_Auth char(5),
    in input_auth_Date date
)
BEGIN
    insert into Bill(emp_Id_Auth, auth_Date)
    VALUES (input_emp_Id_Auth, input_auth_Date);
end //
DELIMITER ;

-- 3.1 Cập nhật thông tin phiếu nhập

DELIMITER //
create procedure get_data_by_billType_or_billCode(in input_bill_Id_or_bill_Code varchar(10))
BEGIN
    SELECT *
    FROM Bill
    WHERE bill_Type = true
      AND bill_Id = input_bill_Id_or_bill_Code

    UNION

    SELECT *
    FROM Bill
    WHERE bill_Type = true
      AND bill_Code = input_bill_Id_or_bill_Code;
end //
DELIMITER ;
-- 3.2 Cập nhật thông tin phiếu xuất
DELIMITER //
create procedure get_data_bill_by_billType_or_billCode(in input_bill_Id_or_bill_Code varchar(10))
BEGIN
    SELECT *
    FROM Bill
    WHERE bill_Type = false
      AND bill_Id = input_bill_Id_or_bill_Code

    UNION

    SELECT *
    FROM Bill
    WHERE bill_Type = false
      AND bill_Code = input_bill_Id_or_bill_Code;
end //
DELIMITER ;
-- Lấy thông tin của phiếu nhập với điều kiện là các trạng thái tạo,bil.bill_Type = true v tìm theo mã hoặc mã code của phiếu nhập
DELIMITER //
create procedure get_data_by_billType_or_billCode_and_billStatus(in input_bill_Id_or_bill_Code varchar(10))
BEGIN
    select *
    from Bill bil
    where bil.bill_Type = true
      and bil.bill_Status = 0
      and (bil.bill_Id = input_bill_Id_or_bill_Code or bil.bill_Code = input_bill_Id_or_bill_Code);
end //
DELIMITER ;
-- Lấy thông tin của phiếu xuất với điều kiện là các trạng thái tạo,bil.bill_Type = true v tìm theo mã hoặc mã code của phiếu nhập
DELIMITER //
create procedure get_data_Bill_by_billType_or_billCode_and_billStatus(in input_bill_Id_or_bill_Code varchar(10))
BEGIN
    select *
    from Bill bil
    where bil.bill_Type = false
      and bil.bill_Status = 0
      and (bil.bill_Id = input_bill_Id_or_bill_Code or bil.bill_Code = input_bill_Id_or_bill_Code);
end //
DELIMITER ;

-- cập nhật dữ liệu cho phiếu nhập
DELIMITER //
create procedure update_data_receipt(
    in input_bill_Id bigint,
    in input_emp_Id_Created char(5),
    in input_created date,
    in input_emp_Id_Auth char(5),
    in input_auth_Date date,
    in input_bill_Status smallint
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = true
                               and bil.bill_Id = input_bill_Id);
    if (isCheckBillStatus = 0 || isCheckBillStatus = 1) then
        update Bill bil
        set bil.emp_Id_Created=input_emp_Id_Created,
            bil.created=input_created,
            bil.emp_Id_Auth=input_emp_Id_Auth,
            bil.auth_Date=input_auth_Date,
            bil.bill_Status=input_bill_Status
        where bil.bill_Id = input_bill_Id;
    end if;
end //
DELIMITER ;

-- cập nhật dữ liệu cho phiếu nhập User
DELIMITER //
create procedure update_data_receiptUser(
    in input_bill_Id bigint,
    in input_emp_Id_Created char(5),
    in input_created date,
    in input_bill_Status smallint
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = true
                               and bil.bill_Id = input_bill_Id);
    if (isCheckBillStatus = 0 || isCheckBillStatus = 1) then
        update Bill bil
        set bil.emp_Id_Created=input_emp_Id_Created,
            bil.created=input_created,
            bil.bill_Status=input_bill_Status
        where bil.bill_Id = input_bill_Id;
    end if;
end //
DELIMITER ;
-- cập nhật dữ liệu cho phiếu xuất User
DELIMITER //
create procedure update_data_BillUser(
    in input_bill_Id bigint,
    in input_emp_Id_Created char(5),
    in input_created date,
    in input_bill_Status smallint
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = false
                               and bil.bill_Id = input_bill_Id);
    if (isCheckBillStatus = 0 || isCheckBillStatus = 1) then
        update Bill bil
        set bil.emp_Id_Created=input_emp_Id_Created,
            bil.created=input_created,
            bil.bill_Status=input_bill_Status
        where bil.bill_Id = input_bill_Id;
    end if;
end //
DELIMITER ;

-- cập nhật dữ liệu cho phiếu xuất
DELIMITER //
create procedure update_data_bill(
    in input_bill_Id bigint,
    in input_emp_Id_Created char(5),
    in input_created date,
    in input_emp_Id_Auth char(5),
    in input_auth_Date date,
    in input_bill_Status smallint
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = false
                               and bil.bill_Id = input_bill_Id);
    if (isCheckBillStatus = 0 || isCheckBillStatus = 1) then
        update Bill bil
        set bil.emp_Id_Created=input_emp_Id_Created,
            bil.created=input_created,
            bil.emp_Id_Auth=input_emp_Id_Auth,
            bil.auth_Date=input_auth_Date,
            bil.bill_Status=input_bill_Status
        where bil.bill_Id = input_bill_Id;
    end if;
end //
DELIMITER ;

-- check trùng phiếu nhập
DELIMITER //
create procedure get_receipt_by_Bill_Code(
    in input_bill_Code varchar(10)
)
BEGIN
    select *
    from Bill bil
    where bil.bill_Code = input_bill_Code
      and bil.bill_Type = true;
end //
DELIMITER ;
-- Check trùng phiếu xuất
DELIMITER //
create procedure get_Bill_by_Bill_Code(
    in input_bill_Code varchar(10)
)
BEGIN
    select *
    from Bill bil
    where bil.bill_Code = input_bill_Code;
end //
DELIMITER ;

-- Tìm kiếm phiếu nhập trong Bill có phiếu nhập chi tiết hay không

DELIMITER //
create procedure get_all_data_billDetail_by_receipt(
    in input_bill_Id bigint
)
BEGIN
    select biDet.*,bil.bill_Code as 'billCode'
    from Bill_Detail biDet
             join Bill bil on biDet.bill_Id = bil.bill_Id
    where biDet.bill_Id = input_bill_Id;
end //
DELIMITER ;
select *
from Product;
select *
from Employee;
select *
from Bill bi
where bi.bill_Type = true;
select *
from Bill_Detail;
insert into Bill_Detail(bill_Id, product_Id, quantity, price)
VALUES (1, 'SP009', 22, 110000),
       (1, 'SP007', 96, 45000);


-- Lấy tất cả dữ liệu trong bảng Bill_Detail với điều kiện là chi tiết phiếu nhập
DELIMITER //
create procedure get_data_Bill_Detail_By_Bill_Type1()
BEGIN
    select biDet.*
    from Bill_Detail biDet
             join Bill bil on biDet.bill_Id = bil.bill_Id
    where bil.bill_Type = true;
end //
DELIMITER ;
-- Lấy tất cả dữ liệu trong bảng Bill_Detail với điều kiện là chi tiết phiếu xuất
DELIMITER //
create procedure get_data_Bill_By_Bill_Type1()
BEGIN
    select biDet.*,bil.bill_Code as 'billCode'
    from Bill_Detail biDet
             join Bill bil on biDet.bill_Id = bil.bill_Id
    where bil.bill_Type = false;
end //
DELIMITER ;
-- Lấy dữ liệu ở bảng Bill_Detail_Id theo Bill_Detail_Id mà phải là
DELIMITER //
create procedure get_data_Bill_Detail_by_Bill_Detail_Id_And_bill_Type1(
    in input_Bill_Detail_Id bigint
)
BEGIN
    select *
    from Bill_Detail bilDet
             join Bill bill on bill.bill_Id = bilDet.bill_Id
    where bilDet.bill_Detail_Id = input_Bill_Detail_Id
      and bill.bill_Type = true;
end //
DELIMITER ;
-- Lấy dữ liệu ở bảng Bill_Detail_Id theo Bill_Detail_Id
DELIMITER //
create procedure get_data_Bill_Detail_by_Bill_Detail_Id_And_bill_Type0(
    in input_Bill_Detail_Id bigint
)
BEGIN
    select *
    from Bill_Detail bilDet
             join Bill bill on bill.bill_Id = bilDet.bill_Id
    where bilDet.bill_Detail_Id = input_Bill_Detail_Id
      and bill.bill_Type = false;
end //
DELIMITER ;

-- Thêm mới ữ liệu cho bảng Bill Detail

DELIMITER //
create procedure create_data_bill_detail(
    in input_bill_Id bigint,
    in input_product_Id char(5),
    in input_quantity int,
    in input_price float
)
BEGIN
    insert into Bill_Detail(bill_Id, product_Id, quantity, price)
    VALUES (input_bill_Id, input_product_Id, input_quantity, input_price);
end //
DELIMITER ;
-- update data in table Bill Detail
DELIMITER //
create procedure update_data_Bill_Detail(
    in input_bill_Detail_Id bigint,
    in input_bill_Id bigint,
    in input_product_Id char(5),
    in input_quantity int,
    in input_price float
)
BEGIN
    update Bill_Detail bilDet
    set bilDet.bill_Id   =input_bill_Id,
        bilDet.product_Id=input_product_Id,
        bilDet.quantity=input_quantity,
        bilDet.price=input_price
    where bilDet.bill_Detail_Id = input_bill_Detail_Id;
end //
DELIMITER ;

-- Lấy bill ID max trong bảng Bill
DELIMITER //
create procedure index_max_in_Bill()
BEGIN
    select MAX(bill_Id) as 'maxIndex'
    from Bill;
end //
DELIMITER ;

-- Lấy toàn bộ dữ liệu trong Product
DELIMITER //
create procedure get_all_data_product_for_billDetail()
BEGIN
    select *
    from Product;
end //
DELIMITER ;

-- Duyệt phiếu nhập
DELIMITER //
create procedure approveReceipt(
    in input_bill_Id bigint,
    in input_emp_Id_Auth char(5),
    in input_auth_Date date
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = true
                               and bil.bill_Id = input_bill_Id
                               and bil.bill_Status = 0);
    if (isCheckBillStatus = 0) then
        update Bill bil
            inner join Bill_Detail billDetail on bil.bill_Id = billDetail.bill_Id
            inner join Product pr on billDetail.product_Id = pr.product_Id
        set bil.bill_Status=2,
            bil.emp_Id_Auth=input_emp_Id_Auth,
            bil.auth_Date=input_auth_Date,
            pr.quantity= pr.quantity + billDetail.quantity
        where bil.bill_Id = input_bill_Id;
    end if;
end //
DELIMITER ;


DELIMITER //
create procedure isCheckApproveBill(
    in input_bill_Id bigint,
    out MESSAGE_TEXT varchar(200)
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    DECLARE isCheckBillQuantity smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = false
                               and bil.bill_Id = input_bill_Id
                               and bil.bill_Status = 0);

    set isCheckBillQuantity = (select (pr.quantity - billDetail.quantity)
                               from Bill bil
                                        inner join Bill_Detail billDetail on bil.bill_Id = billDetail.bill_Id
                                        inner join Product pr on billDetail.product_Id = pr.product_Id
                               where bil.bill_Id = input_bill_Id);
    if (isCheckBillStatus = 0 and isCheckBillQuantity > 0) then
        Set MESSAGE_TEXT = 'Đủ số lượng tồn kho để xuất hàng';
    else
        set MESSAGE_TEXT = 'Không đủ số lượng tồn kho để xuất hàng';
    end if;
end //
DELIMITER ;

select billDetail.product_Id,sum(billDetail.quantity)
from Bill bil
         inner join Bill_Detail billDetail on bil.bill_Id = billDetail.bill_Id
         inner join Product pr on billDetail.product_Id = pr.product_Id
where bil.bill_Id=7
group by billDetail.product_Id;

-- Duyệt phiếu xuất
DELIMITER //
create procedure approveBill(
    in input_bill_Id bigint,
    in input_emp_Id_Auth char(5),
    in input_auth_Date date,
    out MESSAGE_TEXT varchar(200)
)
BEGIN
    DECLARE isCheckBillStatus smallint;
    DECLARE isCheckBillQuantity smallint;
    set isCheckBillStatus = (select bil.bill_Status
                             from Bill bil
                             where bil.bill_Type = false
                               and bil.bill_Id = input_bill_Id
                               and bil.bill_Status = 0);

    set isCheckBillQuantity = (select (pr.quantity - billDetail.quantity)
                               from Bill bil
                                        inner join Bill_Detail billDetail on bil.bill_Id = billDetail.bill_Id
                                        inner join Product pr on billDetail.product_Id = pr.product_Id
                               where bil.bill_Id = input_bill_Id);
    if (isCheckBillStatus = 0 and isCheckBillQuantity > 0) then
        update Bill bil
            inner join Bill_Detail billDetail on bil.bill_Id = billDetail.bill_Id
            inner join Product pr on billDetail.product_Id = pr.product_Id
        set bil.bill_Status=2,
            bil.emp_Id_Auth=input_emp_Id_Auth,
            bil.auth_Date=input_auth_Date,
            pr.quantity= pr.quantity - billDetail.quantity
        where bil.bill_Id = input_bill_Id;
        Set MESSAGE_TEXT = 'Đủ số lượng tồn kho để xuất và đã duyệt thành công phiếu xuất';
    else
        set MESSAGE_TEXT = 'Không đủ số lượng tồn kho để xuất hàng';
    end if;
end //
DELIMITER ;

-- 1. Thống kê chi phí(theo phiếu nhập) theo ngày, tháng, năm
DELIMITER //
create procedure statistics_expense_receipt_by_day_month_year()
BEGIN
    select bill.bill_Status, bill.auth_Date, sum(billDetail.quantity * billDetail.price) as 'sumReceipt'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
    where bill.bill_Type = true
      and bill.bill_Status = 2
    group by bill.auth_Date;
end //
DELIMITER ;
-- 2. Thống kê chi phí(theo phiếu nhập) theo khoảng thời gian theo tháng và năm

DELIMITER //
create procedure statistics_expense_receipt_by_month_year()
BEGIN
    select bill.bill_Status,
           CONCAT(YEAR(bill.auth_Date), '-', MONTH(bill.auth_Date)) as 'monthYear',
           sum(billDetail.quantity * billDetail.price)              as 'sumReceipt'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
    where bill.bill_Type = true
      and bill.bill_Status = 2
    group by monthYear;
end //
DELIMITER ;
-- 3. Thống kê doanh thu theo ngày, tháng, năm
DELIMITER //
create procedure statistics_revenue_bill_by_day_month_year()
BEGIN
    select bill.bill_Status, bill.auth_Date, sum(billDetail.quantity * billDetail.price) as 'revenueBillByDayMonthYear'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
    where bill.bill_Type = false
      and bill.bill_Status = 2
    group by bill.auth_Date;
end //
DELIMITER ;
-- 4. Thống kê doanh thu theo  khoảng thời gian tháng và năm
DELIMITER //
create procedure statistics_revenue_bill_by_month_year()
BEGIN
    select bill.bill_Status,
           CONCAT(YEAR(bill.auth_Date), '-', MONTH(bill.auth_Date)) as 'monthYear',
           sum(billDetail.quantity * billDetail.price)              as 'revenueBillByMonthYear'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
    where bill.bill_Type = false
      and bill.bill_Status = 2
    group by monthYear;
end //
DELIMITER ;

-- 5. Thống kê số nhân viên theo từng trạng thái
DELIMITER //
create procedure statistics_employee_by_Emp_Status()
BEGIN
    select em.emp_Status, count(em.emp_Status) as 'cntEmpStatus'
    from Employee em
    group by em.emp_Status;
end //
DELIMITER ;

-- 6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian
DELIMITER //
create procedure statistics_max_receipt_product_by_month_year()
BEGIN
    select CONCAT(YEAR(bill.auth_Date), '-', MONTH(bill.auth_Date)) as 'monthYear',
           pr.product_Name,
           COUNT(pr.product_Name)                                   as 'cntMaxReceipt'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
             join Product pr on billDetail.product_Id = pr.product_Id
    where bill.bill_Type = true
      and bill.bill_Status = 2
    group by monthYear, pr.product_Name
    having COUNT(pr.product_Name) >= all (select COUNT(pr.product_Name)
                                          from Bill bill
                                                   join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
                                                   join Product pr on billDetail.product_Id = pr.product_Id
                                          where bill.bill_Type = true
                                            and bill.bill_Status = 2
                                          group by pr.product_Name);

end //
DELIMITER ;

-- 7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian

DELIMITER //
create procedure statistics_min_receipt_product_by_month_year()
BEGIN
    select CONCAT(YEAR(bill.auth_Date), '-', MONTH(bill.auth_Date)) as 'monthYear',
           pr.product_Name,
           COUNT(pr.product_Name)                                   as 'cntMinReceipt'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
             join Product pr on billDetail.product_Id = pr.product_Id
    where bill.bill_Type = true
      and bill.bill_Status = 2
    group by monthYear, pr.product_Name
    having COUNT(pr.product_Name) <= all (select COUNT(pr.product_Name)
                                          from Bill bill
                                                   join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
                                                   join Product pr on billDetail.product_Id = pr.product_Id
                                          where bill.bill_Type = true
                                            and bill.bill_Status = 2
                                          group by pr.product_Name);

end //
DELIMITER ;

-- 8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian
DELIMITER //
create procedure statistics_max_bill_product_by_month_year()
BEGIN
    select CONCAT(YEAR(bill.auth_Date), '-', MONTH(bill.auth_Date)) as 'monthYear',
           pr.product_Name,
           COUNT(pr.product_Name)                                   as 'cntMaxBill'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
             join Product pr on billDetail.product_Id = pr.product_Id
    where bill.bill_Type = false
      and bill.bill_Status = 2
    group by monthYear, pr.product_Name
    having COUNT(pr.product_Name) >= all (select COUNT(pr.product_Name)
                                          from Bill bill
                                                   join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
                                                   join Product pr on billDetail.product_Id = pr.product_Id
                                          where bill.bill_Type = false
                                            and bill.bill_Status = 2
                                          group by pr.product_Name);

end //
DELIMITER ;

-- 9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian

DELIMITER //
create procedure statistics_min_bill_product_by_month_year()
BEGIN
    select CONCAT(YEAR(bill.auth_Date), '-', MONTH(bill.auth_Date)) as 'monthYear',
           pr.product_Name,
           COUNT(pr.product_Name)                                   as 'cntMinBill'
    from Bill bill
             join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
             join Product pr on billDetail.product_Id = pr.product_Id
    where bill.bill_Type = false
      and bill.bill_Status = 2
    group by monthYear, pr.product_Name
    having COUNT(pr.product_Name) <= all (select COUNT(pr.product_Name)
                                          from Bill bill
                                                   join Bill_Detail billDetail on bill.bill_Id = billDetail.bill_Id
                                                   join Product pr on billDetail.product_Id = pr.product_Id
                                          where bill.bill_Type = false
                                            and bill.bill_Status = 2
                                          group by pr.product_Name);

end //
DELIMITER ;


-- Danh sách Bill của input_Emp_id theo phiếu nhập và trạng thái không phải là duyệt

DELIMITER //
create procedure get_all_data_receipt_Bill_Status_by_Emp_id(
    in input_Emp_id char(5)
)
BEGIN
    select *
    from Bill bill
    where bill.emp_Id_Created = input_Emp_id
      and bill.bill_Type = true
      and bill_Status <> 2;
end //
DELIMITER ;

-- Danh sách Bill của input_Emp_id theo phiếu nhập và trạng thái không phải là duyệt

DELIMITER //
create procedure get_all_data_Bill_Bill_Status_by_Emp_id_User(
    in input_Emp_id char(5)
)
BEGIN
    select *
    from Bill bill
    where bill.emp_Id_Created = input_Emp_id
      and bill.bill_Type = false
      and bill_Status <> 2;
end //
DELIMITER ;

-- Kiểm tra trong 1 Bill của Bill Detail thì productName k đc trùng nhau

DELIMITER //
create procedure isCheckProductNameInBillDetail(
    in input_Bill_Id long
)
BEGIN
    select billDetail.product_Id
    from Bill_Detail billDetail
    where billDetail.product_Id=input_Bill_Id;
end //
DELIMITER ;
call isCheckProductNameInBillDetail(7);



