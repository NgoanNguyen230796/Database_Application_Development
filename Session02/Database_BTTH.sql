-- Tạo CSDL QUANLYBANHANG
create database QUAN_LY_BAN_HANG;
use QUAN_LY_BAN_HANG;

-- 1.Tạo bảng KHACHHANG
create table KHACHHANG(
	Ma_KH nvarchar(4) primary key,
    Ten_KH nvarchar(30) not null,
    Dia_Chi nvarchar(50) ,
    Ngay_Sinh datetime,
    -- SoDT phải là duy nhất
    So_DT nvarchar(15) unique
);

-- 2.Tạo bảng NHANVIEN
create table NHANVIEN(
	Ma_NV nvarchar(4) primary key,
    Ho_Ten nvarchar(30) not null,
    Gioi_Tinh bit not null,
    Dia_Chi nvarchar(50) not null,
    Ngay_Sinh datetime not null,
    Dien_Thoai nvarchar(15),
    Email text,
    Noi_Sinh nvarchar(20) not null,
    Ngay_Vao_Lam datetime,
    Ma_NQL nvarchar(4)
);
select * from NHANVIEN;
insert into NHANVIEN(Ma_NV,Ho_Ten,Gioi_Tinh,Dia_Chi,Ngay_Sinh,Dien_Thoai,Email,Noi_Sinh,Ngay_Vao_Lam,Ma_NQL)
values 
(N'NV01',N'Nguyễn Ngoan',1,N'Hà Nam','1996-07-23 6:00:00','01697575256','ngoanxitrum2307@gmail.com',N'Lý Nhân Hà Nam','2023-01-12 17:00:00','NQL1'),
('NV02',N'Nguyễn Thu',1,N'Hà Nội','1996-12-10 9:00:00','01666107082','thu@gmail.com',N'Cầu giấy Hà Nội','2023-01-20 4:00:00','NQL2');
-- 3.Tạo bảng NHACUNGCAP
create table NHACUNGCAP(
	Ma_NCC nvarchar(5) primary key,
    Ten_NCC nvarchar(50) not null,
    Dia_Chi nvarchar(50) not null,
    Dien_Thoai nvarchar(15) not null,
    Email nvarchar(30) not null,
    Website nvarchar(30)
);
-- 4.Tạo bảng LOAISP
create table LOAISP(
	Ma_Loai_SP nvarchar(4) primary key,
    Ten_Loai_SP nvarchar(30) not null,
    Ghi_Chu nvarchar(100) not null
);

-- 5.Tạo bảng SANPHAM
create table SANPHAM(
	Ma_SP nvarchar(4) primary key,
    Ma_Loai_SP nvarchar(4) not null,
    Ten_SP nvarchar(50) not null,
    Don_Vi_Tinh nvarchar(10) not null,
    Ghi_Chu nvarchar(100)
);
-- 6.Tạo bảng PHIEUNHAP
create table PHIEUNHAP(
	So_PN nvarchar(5) primary key,
    Ma_NV nvarchar(4) not null,
    Ma_NCC nvarchar(5) not null,
    -- Ngaynhap mặc định là ngày hiện tại
    Ngay_Nhap datetime default(current_date()),
    Ghi_Chu nvarchar(100),
    -- Ma_NV khóa ngoại của bảng NHANVIEN(Ma_NV)
    foreign key(Ma_NV) references NHANVIEN(Ma_NV)
);

-- 7.Tạo bảng CTPHIEUNHAP
create table CTPHIEUNHAP(
	Ma_SP nvarchar(4) ,
    So_PN nvarchar(5) ,
    -- Soluong mặc định là 0
    So_Luong smallint default(0),
    -- Gianhap có giá trị lớn hơn hoặc bằng 0
    Gia_Nhap real check(Gia_Nhap>=0),
    -- Khóa ngoại của bảng SanPham(SanPham)
    foreign key(Ma_SP) references SanPham(Ma_SP),
    -- Khóa ngoại của bảng PhieuNhap(So_PN)
     foreign key(So_PN) references PhieuNhap(So_PN),
     -- Khóa chính của bảng CTPHIEUNHAP
     primary key(Ma_SP,So_PN)
);
-- 8.Tạo bảng PHIEUXUAT
create table PHIEUXUAT(
	So_PX nvarchar(5) ,
    Ma_NV nvarchar(4) ,
    Ma_KH nvarchar(4),
    -- NgayBan có giá trị lớn hơn hoặc bằng ngày hiện tại
    Ngay_Ban date check(Ngay_Ban >= CURDATE()),
    Ghi_Chu text,
    -- Khóa ngoại của bảng NhanVien(Ma_NV)
    foreign key(Ma_NV) references NhanVien(Ma_NV),
    -- Khóa ngoại của bảng KHACHHANG(Ma_KH)
     foreign key(Ma_KH) references KHACHHANG(Ma_KH),
     -- Khóa chính của bảng CTPHIEUNHAP
     primary key(So_PX)
);
-- 9.Tạo bảng CTPHIEUXUAT
create table CTPHIEUXUAT(
	So_PX nvarchar(5) ,
    Ma_SP nvarchar(4) ,
    -- SoLuong và GiaBan phải có giá trị lớn hơn 0
    So_Luong smallint not null check(So_Luong >0),
    Gia_Ban real not null check(Gia_Ban>0),
    -- Khóa ngoại của bảng PHIEUXUAT(So_PX)
    foreign key(So_PX) references PHIEUXUAT(So_PX),
    -- Khóa ngoại của bảng SanPham(Ma_SP)
     foreign key(Ma_SP) references SanPham(Ma_SP),
     primary key(So_PX,Ma_SP)
);









