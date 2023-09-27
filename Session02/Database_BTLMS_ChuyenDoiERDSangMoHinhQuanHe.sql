-- Tạo database quan_ly_xuat_nhap
create database quan_ly_xuat_nhap;
-- Sử dụng database quan_ly_xuat_nhap
use quan_ly_xuat_nhap;
/*
		1.Tạo bảng phiếu xuất PHIEUXUAT
		- So_PX -int - Tự động tăng -Khóa chính
        - Ngay_Xuat-date-not null-mặc định ngày hiện tại
*/

create table PHIEUXUAT(
	-- So_PX -int - Tự động tăng -Khóa chính
    So_PX int auto_increment primary key,
    -- Ngay_Xuat-date-not null-mặc định ngày hiện tại
    Ngay_Xuat date default(current_date())
);

/*
		2.Tạo bảng VatTu
		- Ma_VT - char(5)-Khóa chính
        - Ten_VT - char(20)-not null-unique
*/
create table VatTu(
	-- Ma_VT - char(5)-Khóa chính
    Ma_VT char(5) primary key,
    -- Ten_VT - char(20)-not null-unique
    Ten_VT char(20) not null unique
);

/*
	3. Tạo bảng ChiTietPhieuXuat
		- DG_Xuat -float -not null
        -SL_Xuat-int -not null
        - So_PX -int khóa ngoại của bảng PHIEUXUAT(So_PX)
		- Ma_VT - char(5)-khóa ngoại của bảng VatTu(Ma_VT)
		- So_PX,Ma_VT khóa chính của bảng ChiTietPhieuXuat

*/
create table ChiTietPhieuXuat(
	-- DG_Xuat -float -not null
    DG_Xuat float not null,
	-- SL_Xuat-int -not null
    SL_Xuat int not null,
    -- So_PX -int
    So_PX int ,
    -- Ma_VT - char(5)
    Ma_VT char(5),
	-- khóa ngoại của bảng PHIEUXUAT(So_PX)
    foreign key(So_PX) references PHIEUXUAT(So_PX),
	-- khóa ngoại của bảng VatTu(Ma_VT)
    foreign key(Ma_VT) references VatTu(Ma_VT),
    -- So_PX,Ma_VT khóa chính của bảng ChiTietPhieuXuat
    primary key(So_PX,Ma_VT)
);

/*
	4.Tạo bảng PhieuNhap
		- So_PN -int-Khóa chính
        - NgayNhap -date-not null
*/
create table PhieuNhap(
	-- So_PN -int-Khóa chính
	So_PN int primary key,
    -- NgayNhap -date-not null
    NgayNhap date not null
);
/*
	5. Tạo bảng ChiTietPhieuNhap
		- DG_Nhap - float -not null
        - SL_Nhap-int -not null
        - Ma_VT - char(5)-khóa ngoại của bảng VatTu(Ma_VT)
        - So_PN -int-khóa ngoại của bảng PhieuNhap(So_PN)
        - Ma_VT,So_PN khóa chính của bảng ChiTietPhieuNhap
        
*/
create table ChiTietPhieuNhap(
	-- DG_Nhap - float -not null
    DG_Nhap float not null,
    -- SL_Nhap-int -not null
    SL_Nhap int not null,
    -- Ma_VT - char(5)
    Ma_VT char(5) ,
    -- So_PN -int
    So_PN int ,
    -- khóa ngoại của bảng VatTu(Ma_VT)
    foreign key(Ma_VT) references VatTu(Ma_VT),
    -- khóa ngoại của bảng PhieuNhap(So_PN)
    foreign key(So_PN) references PhieuNhap(So_PN),
    -- Ma_VT,So_PN khóa chính của bảng ChiTietPhieuNhap
    primary key(Ma_VT,So_PN)
);

/*
	6.Tạo bảng DonDH
		- So_DH -int -Khóa chính
        - Ngay_DH -date-not null
*/
create table DonDH(
    -- So_DH -int -Khóa chính
    So_DH int primary key,
    -- Ngay_DH -date-not null
    Ngay_DH date not null
);

/*
	7.Tạo bảng ChiTietDonDatHang
		- Ma_VT - char(5)-khóa ngoại của bảng VatTu(Ma_VT)
		- So_DH -int -khóa ngoại của bảng DonDH(So_DH)
       - (Ma_VT,So_DH) khóa chính của bảng ChiTietDonDatHang
*/
create table ChiTietDonDatHang(
	-- Ma_VT - char(5)-
    Ma_VT char(5),
    -- So_DH -int -
    So_DH int ,
    -- khóa ngoại của bảng VatTu(Ma_VT)
    foreign key(Ma_VT) references VatTu(Ma_VT),
    -- khóa ngoại của bảng DonDH(So_DH)
    foreign key(So_DH) references DonDH(So_DH),
    -- (Ma_VT,So_DH) khóa chính của bảng ChiTietDonDatHang
    primary key(Ma_VT,So_DH)
);

/*
	8.Tạo bảng NhaCC
		- Ma_NCC - char(20)-khóa chính
        - Ten_NCC- varchar(20)-not null
        - Dia_Chi -varchar(20)-not null
        - SDT- char(15)-not null
*/

create table NhaCC(
	-- Ma_NCC - char(20)-khóa chính
    Ma_NCC char(20) primary key,
	-- Ten_NCC- varchar(20)-not null
    Ten_NCC varchar(20) not null,
	-- Dia_Chi -varchar(20)-not null
    Dia_Chi varchar(20) not null,
	-- SDT- char(15)-not null
    SDT char(15) not null
);

/*
	9.Tạo bảng CungCap
		- So_DH -int -khóa ngoại của bảng DonDH(So_DH)
        - Ma_NCC - char(20)-khóa ngoại của bảng NhaCC(Ma_NCC)
        -(So_DH,Ma_NCC) khóa chính của bảng CungCap
*/

create table CungCap(
	-- So_DH -int 
    So_DH int,
	-- Ma_NCC - char(20)
    Ma_NCC char(20),
    -- khóa ngoại của bảng DonDH(So_DH)
    foreign key(So_DH) references DonDH(So_DH),
    -- khóa ngoại của bảng NhaCC(Ma_NCC)
    foreign key(Ma_NCC) references NhaCC(Ma_NCC),
	-- (So_DH,Ma_NCC) khóa chính của bảng CungCap
    primary key(So_DH,Ma_NCC)
);





