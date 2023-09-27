-- Tạo CSDL QUANLYBANHANG
create
    database QUAN_LY_BAN_HANG;
use
    QUAN_LY_BAN_HANG;

-- 1.Tạo bảng KHACHHANG
create table KHACHHANG
(
    Ma_KH     nvarchar(4) primary key,
    Ten_KH    nvarchar(30) not null,
    Dia_Chi   nvarchar(50),
    Ngay_Sinh datetime,
    -- SoDT phải là duy nhất
    So_DT     nvarchar(15) unique
);

-- 2.Tạo bảng NHANVIEN
create table NHANVIEN
(
    Ma_NV        nvarchar(4) primary key,
    Ho_Ten       nvarchar(30) not null,
    Gioi_Tinh    bit          not null,
    Dia_Chi      nvarchar(50) not null,
    Ngay_Sinh    datetime     not null,
    Dien_Thoai   nvarchar(15),
    Email        text,
    Noi_Sinh     nvarchar(20) not null,
    Ngay_Vao_Lam datetime,
    Ma_NQL       nvarchar(4)
);

-- 3.Tạo bảng NHACUNGCAP
create table NHACUNGCAP
(
    Ma_NCC     nvarchar(5) primary key,
    Ten_NCC    nvarchar(50) not null,
    Dia_Chi    nvarchar(50) not null,
    Dien_Thoai nvarchar(15) not null,
    Email      nvarchar(30) not null,
    Website    nvarchar(30)
);
-- 4.Tạo bảng LOAISP
create table LOAISP
(
    Ma_Loai_SP  nvarchar(4) primary key,
    Ten_Loai_SP nvarchar(30)  not null,
    Ghi_Chu     nvarchar(100) not null
);

-- 5.Tạo bảng SANPHAM
create table SANPHAM
(
    Ma_SP       nvarchar(4) primary key,
    Ma_Loai_SP  nvarchar(4)  not null,
    Ten_SP      nvarchar(50) not null,
    Don_Vi_Tinh nvarchar(10) not null,
    Ghi_Chu     nvarchar(100),
    -- Khóa ngoại của bảng LOAISP(Ma_Loai_SP)
    foreign key (Ma_Loai_SP) references LOAISP (Ma_Loai_SP)
);
-- 6.Tạo bảng PHIEUNHAP
create table PHIEUNHAP
(
    So_PN     nvarchar(5) primary key,
    Ma_NV     nvarchar(4) not null,
    Ma_NCC    nvarchar(5) not null,
    -- Ngaynhap mặc định là ngày hiện tại
    Ngay_Nhap datetime default (current_date()),
    Ghi_Chu   nvarchar(100),
    -- Ma_NV khóa ngoại của bảng NHANVIEN(Ma_NV)
    foreign key (Ma_NV) references NHANVIEN (Ma_NV),
    -- Ma_NCC khóa ngoại của bảng NHACUNGCAP(Ma_NCC)
    foreign key (Ma_NCC) references NHACUNGCAP (Ma_NCC)
);

-- 7.Tạo bảng CTPHIEUNHAP
create table CTPHIEUNHAP
(
    Ma_SP    nvarchar(4),
    So_PN    nvarchar(5),
    -- Soluong mặc định là 0
    So_Luong smallint default (0),
    -- Gianhap có giá trị lớn hơn hoặc bằng 0
    Gia_Nhap real check (Gia_Nhap >= 0),
    -- Khóa ngoại của bảng SanPham(SanPham)
    foreign key (Ma_SP) references SanPham (Ma_SP),
    -- Khóa ngoại của bảng PhieuNhap(So_PN)
    foreign key (So_PN) references PhieuNhap (So_PN),
    -- Khóa chính của bảng CTPHIEUNHAP
    primary key (Ma_SP, So_PN)
);
-- 8.Tạo bảng PHIEUXUAT
create table PHIEUXUAT
(
    So_PX    nvarchar(5),
    Ma_NV    nvarchar(4),
    Ma_KH    nvarchar(4),
    -- NgayBan có giá trị lớn hơn hoặc bằng ngày hiện tại
    Ngay_Ban date default (current_date()),
    Ghi_Chu  text,
    -- Khóa ngoại của bảng NhanVien(Ma_NV)
    foreign key (Ma_NV) references NhanVien (Ma_NV),
    -- Khóa ngoại của bảng KHACHHANG(Ma_KH)
    foreign key (Ma_KH) references KHACHHANG (Ma_KH),
    -- Khóa chính của bảng CTPHIEUNHAP
    primary key (So_PX)
);
-- 9.Tạo bảng CTPHIEUXUAT
create table CTPHIEUXUAT
(
    So_PX    nvarchar(5),
    Ma_SP    nvarchar(4),
    -- SoLuong và GiaBan phải có giá trị lớn hơn 0
    So_Luong smallint not null check (So_Luong > 0),
    Gia_Ban  real     not null check (Gia_Ban > 0),
    -- Khóa ngoại của bảng PHIEUXUAT(So_PX)
    foreign key (So_PX) references PHIEUXUAT (So_PX),
    -- Khóa ngoại của bảng SanPham(Ma_SP)
    foreign key (Ma_SP) references SanPham (Ma_SP),
    primary key (So_PX, Ma_SP)
);

-- select dữ liệu các bảng
select *
from KHACHHANG;
select *
from NHANVIEN;
select *
from NHACUNGCAP;
select *
from LOAISP;
select *
from PHIEUNHAP;
select *
from CTPHIEUNHAP;
select *
from PHIEUXUAT;
select *
from CTPHIEUXUAT;

-- Bài 3: Dùng lệnh INSERT thêm dữ liệu vào các bảng:
-- insert dữ liệu vào bảng NHANVIEN
insert into NHANVIEN(Ma_NV, Ho_Ten, Gioi_Tinh, Dia_Chi, Ngay_Sinh, Dien_Thoai, Email, Noi_Sinh, Ngay_Vao_Lam, Ma_NQL)
values (N'NV01', N'Nguyễn Ngoan', 1, N'Hà Nam', '1996-07-23 6:00:00', '01697575256', 'ngoanxitrum2307@gmail.com',
        N'Lý Nhân Hà Nam', '2023-01-12 17:00:00', 'NQL1'),
       ('NV02', N'Nguyễn Thu', 1, N'Hà Nội', '1996-12-10 9:00:00', '01666107082', 'thu@gmail.com', N'Cầu giấy Hà Nội',
        '2023-01-20 4:00:00', 'NQL2');
-- insert dữ liệu vào bảng NhaCungCap
insert into NHACUNGCAP (Ma_NCC, Ten_NCC, Dia_Chi, Dien_Thoai, Email, Website)
values ('NCC1', N'Phương Đông', N'Huế', '01698752365', 'phuongDong@gmail.com', 'phuongDong.com'),
       ('NCC2', N'Nam Hạnh', N'Thái Bình', '04863254189', 'namHanh@gmail.com', 'namHanh.com');

-- 1. Thêm 2 Phiếu nhập trong tháng hiện hành. Mỗi phiếu nhập có 2 sản phẩm.
-- Phiếu nhập 1,Mỗi phiếu nhập có 2 sản phẩm.
insert into PHIEUNHAP (So_PN, Ma_NV, Ma_NCC, Ghi_Chu)
values ('PN01', 'NV01', 'NCC1', 'Nhập hàng lô số 1 PN01'),
       ('PN02', 'NV02', 'NCC2', 'Nhập hàng lô số 2 PN02');
-- Phiếu nhập 2,Mỗi phiếu nhập có 2 sản phẩm.
insert into PHIEUNHAP (So_PN, Ma_NV, Ma_NCC, Ghi_Chu)
values ('PN03', 'NV01', 'NCC1', 'Nhập hàng lô số 3 PN01'),
       ('PN04', 'NV02', 'NCC2', 'Nhập hàng lô số 4 PN02');
-- 2. Thêm 2 Phiếu xuất trong ngày hiện hành. Mỗi phiếu xuất có 3 sản phẩm.
-- 2.1 Thêm dữ liệu cho bảng KhachHang
insert into KHACHHANG (Ma_KH, Ten_KH, Dia_Chi, Ngay_Sinh, So_DT)
values ('KH01', N'Nguyễn An', N'Nam Đinh', '1996-12-25 17:00:00', '033548965'),
       ('KH02', N'Nguyễn Nam', N'Thái Bình', '1999-04-12 6:00:00', '052693478'),
       ('KH03', N'Trần Mai', N'Hà Nội', '1998-12-25 8:30:00', '04789652452'),
       ('KH04', N'Đỗ Hiếu', N'Bắc Ninh', '1965-03-23 15:00:00', '0163398577'),
       ('KH05', N'Bùi Tuấn', N'Hà Nội', '1992-04-25 17:00:00', '033548961'),
       ('KH06', N'Lê Hạnh', N'Hải Phòng', '1989-07-3 8:00:00', '0411589635'),
       ('KH07', N'Nguyễn Thảo', N'Nam Đinh', '193-06-9 7:00:00', '0148625782'),
       ('KH08', N'Đỗ Đại', N'Nam Đinh', '1994-09-12 6:00:00', '0125872222'),
       ('KH09', N'Nguyễn Chi', N'Ninh Bình', '1997-08-15 18:00:00', '0896325462'),
       ('KH10', N'Nguyễn Tú', N'Hải Dương', '1991-12-15 4:00:00', '035479621'),
       ('KH11', N'Nguyễn Thảo', N'Thái Nguyên', '1998-02-21 8:00:00', '045798653');
-- 2.2 Phiếu xuất 1,Mỗi phiếu nhập có 3 sản phẩm.
insert into PHIEUXUAT (So_PX, Ma_NV, Ma_KH, Ghi_Chu)
values ('PX001', 'NV01', 'KH01', 'Xuất hàng 01'),
       ('PX002', 'NV01', 'KH02', 'Xuất hàng 02'),
       ('PX003', 'NV01', 'KH03', 'Xuất hàng 03');
-- 2.3 Phiếu xuất 2,Mỗi phiếu nhập có 3 sản phẩm.
insert into PHIEUXUAT (So_PX, Ma_NV, Ma_KH, Ghi_Chu)
values ('PX004', 'NV02', 'KH04', 'Xuất hàng 04'),
       ('PX005', 'NV02', 'KH05', 'Xuất hàng 05'),
       ('PX006', 'NV02', 'KH06', 'Xuất hàng 06');
-- 3. Thêm 1 nhân viên mới (Tùy chọn các thông tin liên quan còn lại)
insert into NHANVIEN (Ma_NV, Ho_Ten, Gioi_Tinh, Dia_Chi, Ngay_Sinh, Dien_Thoai, Email, Noi_Sinh, Ngay_Vao_Lam, Ma_NQL)
values ('NV03', N'Đỗ Đại', 0, N'Nam Định', '2000-12-8 15:45:00', '0159874562', 'DaiDo@gmail.com', N'Nam Định',
        '2020-08-09 13:00', 'NQL1');

-- Bài 4: Dùng lệnh UPDATE cập nhật dữ liệu các bảng
-- 4.1. Cập nhật lại số điện thoại mới cho khách hàng mã KH10. (Tùy chọn các thông tin liên quan)
update khachhang
set So_DT='02564562333'
where Ma_KH = 'KH10';
-- 4.2 Cập nhật lại địa chỉ mới của nhân viên mã NV05 (Tùy chọn các thông tin liên quan)
update NHANVIEN
set Dia_Chi='Nghệ An'
where Ma_NV = 'NV05';
-- Bài 5: Dùng lệnh DELETE xóa dữ liệu các bảng
-- 5.1. Xóa nhân viên mới vừa thêm tại yêu cầu C.3
delete
from nhanvien
where Ma_NV = 'NV03';
-- 5.2 Xóa sản phẩm mã SP15
-- Trước khi xóa phải thêm dữ liệu vào bảng LOAISP
insert into LOAISP (Ma_Loai_SP, Ten_Loai_SP, Ghi_Chu)
values ('L001', 'Sách tiếng anh', 'Vừa nhập năm ngoái'),
       ('L002', 'Sách tiếng việt', 'Vừa nhập năm ngoái'),
       ('L003', 'Sách toán', 'Vừa nhập năm ngoái'),
       ('L004', 'Sách âm nhạc', 'Vừa nhập năm ngoái'),
       ('L005', 'Sách tiếng anh 2', 'Vừa nhập năm ngoái'),
       ('L006', 'Sách tiếng anh 3', 'Vừa nhập năm ngoái'),
       ('L007', 'Sách tiếng anh 4', 'Vừa nhập năm ngoái');
-- Trước khi xóa phải thêm dữ liệu vào bảng SanPham
insert into SANPHAM (Ma_SP, Ma_Loai_SP, Ten_SP, Don_Vi_Tinh, Ghi_Chu)
values ('SP01', 'L003', N'Sản phẩm 01', N'cuốn', 'sản phẩm của tháng 1'),
       ('SP02', 'L002', N'Sản phẩm 02', N'cuốn', 'sản phẩm của tháng 5'),
       ('SP09', 'L003', N'Sản phẩm 09', N'cuốn', 'sản phẩm của tháng 10'),
       ('SP10', 'L004', N'Sản phẩm 10', N'cuốn', 'sản phẩm của tháng 4'),
       ('SP14', 'L005', N'Sản phẩm 14', N'cuốn', 'sản phẩm của tháng 9'),
       ('SP15', 'L005', N'Sản phẩm 15', N'cuốn', 'sản phẩm của tháng 11');
-- Xóa sản phẩm mã SP15 trong bảng SanPham
delete
from SANPHAM
where Ma_SP = 'SP15';

-- Bài 6: Dùng lệnh SELECT lấy dữ liệu từ các bảng
/*
    6.1. Liệt kê thông tin về nhân viên trong cửa hàng, gồm: mã nhân viên, họ tên
    nhân viên, giới tính, ngày sinh, địa chỉ, số điện thoại, tuổi. Kết quả sắp xếp theo tuổi
*/

select nv.Ma_NV,
       nv.Ho_Ten,
       nv.Gioi_Tinh,
       nv.Ngay_Sinh,
       nv.Dia_Chi,
       nv.Dien_Thoai,
       (year(curdate()) - year(nv.Ngay_Sinh)) as 'Tuổi'
from NHANVIEN as nv
order by (year(curdate()) - year(nv.Ngay_Sinh));

/*
    6.2. Liệt kê các hóa đơn nhập hàng trong tháng 6/2018, gồm thông tin số phiếu nhập,
    mã nhân viên nhập hàng, họ tên nhân viên, họ tên nhà cung cấp, ngày nhập hàng, ghi chú.
*/
update PHIEUNHAP
set Ngay_Nhap='2018-06-14 00:00:00'
where So_PN = 'PN02';
select pn.So_PN, pn.Ma_NV, nv.Ho_Ten, ncc.Ten_NCC, pn.Ngay_Nhap, pn.Ghi_Chu
from phieunhap pn
         join NHANVIEN nv on pn.Ma_NV = nv.Ma_NV
         join NHACUNGCAP ncc on pn.Ma_NCC = ncc.Ma_NCC
where year(pn.Ngay_Nhap) = 2018
  and month(pn.Ngay_Nhap) = 6;
/*
    6.3. Liệt kê tất cả sản phẩm có đơn vị tính là chai, gồm tất cả thông tin về sản phẩm.
*/
-- thay đổi dữ liệu trong bảng SANPHAM để phù hợp với yêu cầu của đề bài
update sanpham
set Don_Vi_Tinh='chai'
where Ma_SP = 'SP09';
update sanpham
set Don_Vi_Tinh='chai'
where Ma_SP = 'SP14';
select *
from sanpham sp
where sp.Don_Vi_Tinh = 'chai';

/*
    6.4 . Liệt kê chi tiết nhập hàng trong tháng hiện hành gồm thông tin:
    số phiếu nhập, mã sản phẩm, tên sản phẩm, loại sản phẩm, đơn vị tính, số lượng, giá nhập, thành tiền.
*/
-- Thêm dữ liệu vào bảng CTPHIEUNHAP
insert into CTPHIEUNHAP (Ma_SP, So_PN, So_Luong, Gia_Nhap)
values ('SP01', 'PN01', 2, 2500),
       ('SP02', 'PN04', 5, 15000),
       ('SP09', 'PN02', 6, 5000),
       ('SP14', 'PN03', 2, 3000);

select pn.So_PN,
       pn.Ma_SP,
       sp.Ten_SP,
       sp.Ma_Loai_SP,
       sp.Don_Vi_Tinh,
       pn.So_Luong,
       pn.Gia_Nhap,
       (pn.So_Luong * pn.Gia_Nhap) as 'Thành Tiền'
from CTPHIEUNHAP pn
         join SANPHAM sp on pn.Ma_SP = sp.Ma_SP
         join PHIEUNHAP pnhap on pn.So_PN = pnhap.So_PN
where month(pnhap.Ngay_Nhap) = month(curdate());

/*
    6.5. Liệt kê các nhà cung cấp có giao dịch mua bán trong tháng hiện hành, gồm thông tin:
    mã nhà cung cấp, họ tên nhà cung cấp, địa chỉ, số điện thoại, email, số phiếu nhập, ngày nhập.
    Sắp xếp thứ tự theo ngày nhập hàng.
*/

select ncc.Ma_NCC, ncc.Ten_NCC, ncc.Dia_Chi, ncc.Dien_Thoai, ncc.Email, pn.So_PN, pn.Ngay_Nhap
from nhacungcap ncc
         join PHIEUNHAP pn on ncc.Ma_NCC = pn.Ma_NCC
where month(pn.Ngay_Nhap) = month(curdate());

/*
    6.6. Liệt kê chi tiết hóa đơn bán hàng trong 6 tháng đầu năm 2018 gồm thông tin:
    số phiếu xuất, nhân viên bán hàng, ngày bán, mã sản phẩm, tên sản phẩm,
    đơn vị tính, số lượng, giá bán, doanh thu.

*/
-- thay đổi dữ liệu trong bảng PHIEUXUAT để phù hợp với yêu cầu của đề bài
update PHIEUXUAT
set Ngay_Ban='2018-1-12'
where So_PX = 'PX001';
update PHIEUXUAT
set Ngay_Ban='2018-5-12'
where So_PX = 'PX006';

-- insert dữ liệu vào bảng CTPHIEUXUAT
insert into CTPHIEUXUAT (So_PX, Ma_SP, So_Luong, Gia_Ban)
values ('PX001', 'SP01', 4, 5000),
       ('PX002', 'SP02', 9, 25000),
       ('PX003', 'SP09', 20, 50000),
       ('PX004', 'SP10', 2, 10000),
       ('PX005', 'SP14', 6, 11000),
       ('PX006', 'SP02', 7, 25000);

select px.So_PX,
       nv.Ho_Ten,
       px.Ngay_Ban,
       ctpx.Ma_SP,
       sp.Ten_SP,
       sp.Don_Vi_Tinh,
       ctpx.So_Luong,
       ctpx.Gia_Ban,
       (ctpx.So_Luong * ctpx.Gia_Ban) as 'Doanh Thu'
from PHIEUXUAT px
         join NHANVIEN nv on px.Ma_NV = nv.Ma_NV
         join CTPHIEUXUAT ctpx on px.So_PX = ctpx.So_PX
         join SANPHAM sp on ctpx.Ma_SP = sp.Ma_SP
where year(px.Ngay_Ban) = 2018
  and month(px.Ngay_Ban) between 1
    and 6;

/*
    6.7. Hãy in danh sách khách hàng có ngày sinh nhật trong tháng hiện hành
    (gồm tất cả thông tin của khách hàng)
*/

select *
from KHACHHANG kh
where month(kh.Ngay_Sinh) = month(curdate());

/*
    6.8. Liệt kê các hóa đơn bán hàng từ ngày 15/04/2018 đến 15/05/2018 gồm các thông tin:
    số phiếu xuất, nhân viên bán hàng, ngày bán, mã sản phẩm, tên sản phẩm, đơn vị tính, số lượng, giá bán, doanh thu.
*/

select px.So_PX,
       nv.Ho_Ten,
       px.Ngay_Ban,
       ctpx.Ma_SP,
       sp.Ten_SP,
       sp.Don_Vi_Tinh,
       ctpx.So_Luong,
       ctpx.Gia_Ban,
       (ctpx.So_Luong * ctpx.Gia_Ban) as 'Doanh Thu'
from PHIEUXUAT px
         join NHANVIEN nv on px.Ma_NV = nv.Ma_NV
         join CTPHIEUXUAT ctpx on px.So_PX = ctpx.So_PX
         join SANPHAM sp on ctpx.Ma_SP = sp.Ma_SP
where px.Ngay_Ban between '2018-04-15' and '2018-05-15';

/*
    6.9. Liệt kê các hóa đơn mua hàng theo từng khách hàng, gồm các thông tin:
    số phiếu xuất, ngày bán, mã khách hàng, tên khách hàng, trị giá.
*/

select px.So_PX, px.Ngay_Ban, kh.Ma_KH, kh.Ten_KH, (ctpx.So_Luong * ctpx.Gia_Ban) as 'Trị giá'
from KHACHHANG kh
         join PHIEUXUAT px on kh.Ma_KH = px.Ma_KH
         join CTPHIEUXUAT ctpx on px.So_PX = ctpx.So_PX;

/*
    6.10. Cho biết tổng số chai nước xả vải Comfort đã bán trong 6 tháng đầu năm 2018.
    Thông tin hiển thị: tổng số lượng.
*/

-- thay đổi dữ liệu trong bảng SANPHAM để phù hợp với yêu cầu của đề bài
update SANPHAM
set Ten_SP='Comfort'
where Ma_SP = 'SP01';
update SANPHAM
set Don_Vi_Tinh='chai'
where Ma_SP = 'SP01';
update SANPHAM
set Ten_SP='Comfort'
where Ma_SP = 'SP14';
update PHIEUXUAT
set Ngay_Ban='2018-4-23'
where So_PX = 'PX005';

select sp.Ten_SP, sum(ctpx.So_Luong) as 'Tổng số lượng'
from CTPHIEUXUAT ctpx
         join SANPHAM sp on ctpx.Ma_SP = sp.Ma_SP
         join PHIEUXUAT px on ctpx.So_PX = px.So_PX
where sp.Ten_SP = 'Comfort'
  and month(
        px.Ngay_Ban) between 1
    and 6;


/*
   6.11.Tổng kết doanh thu theo từng khách hàng theo tháng, gồm các thông tin:
tháng, mã khách hàng, tên khách hàng, địa chỉ, tổng tiền.
*/

select px.Ma_KH,
       kh.Ten_KH,
       kh.Dia_Chi,
       MONTH(px.Ngay_Ban)                as 'Doanh thu theo tháng',
       sum(ctpx.So_Luong * ctpx.Gia_Ban) as 'Tổng tiền'
from ctphieuxuat ctpx
         join phieuxuat px on ctpx.So_PX = px.So_PX
         join khachhang kh on px.Ma_KH = kh.Ma_KH
group by px.Ma_KH, MONTH(px.Ngay_Ban)
order by MONTH(px.Ngay_Ban);

/*
    6.12.Thống kê tổng số lượng sản phẩm đã bán theo từng tháng trong năm,
    gồm thông tin: năm, tháng, mã sản phẩm, tên sản phẩm, đơn vị tính, tổng số lượng

*/

select sp.Ma_SP,
       sp.Ten_SP,
       sp.Don_Vi_Tinh,
       sum(ctpx.So_Luong) as 'Tổng số lượng',
       MONTH(px.Ngay_Ban) as 'Tháng',
       YEAR(px.Ngay_Ban)  as 'Năm'
from sanpham sp
         join ctphieuxuat ctpx on sp.Ma_SP = ctpx.Ma_SP
         join phieuxuat px on ctpx.So_PX = px.So_PX
group by sp.Ma_SP, MONTH(px.Ngay_Ban), YEAR(px.Ngay_Ban)
order by MONTH(px.Ngay_Ban), YEAR(px.Ngay_Ban);

/*
    6.13.Thống kê doanh thu bán hàng trong trong 6 tháng đầu năm 2018,
    thông tin hiển thị gồm: tháng, doanh thu.
*/

select MONTH(px.Ngay_Ban) as 'Tháng', SUM(ctpx.So_Luong * ctpx.Gia_Ban) as 'Doanh thu'
from ctphieuxuat ctpx
         join phieuxuat px on ctpx.So_PX = px.So_PX
where YEAR(px.Ngay_Ban) = 2018
  and MONTH(px.Ngay_Ban) between 1 and 6
group by MONTH(px.Ngay_Ban)
order by MONTH(px.Ngay_Ban);

/*
    6.14.Liệt kê các hóa đơn bán hàng của tháng 5 và tháng 6 năm 2018, gồm các thông tin:
    số phiếu, ngày bán, họ tên nhân viên bán hàng, họ tên khách hàng, tổng trị giá.

*/

select px.So_PX,
       px.Ngay_Ban,
       nv.Ho_Ten                         as 'Họ tên nhân viên bán hàng',
       kh.Ten_KH                         as 'Họ tên khách hàng',
       sum(ctpx.So_Luong * ctpx.Gia_Ban) as 'Tổng trị giá'
from ctphieuxuat ctpx
         join phieuxuat px on ctpx.So_PX = px.So_PX
         join khachhang kh on px.Ma_KH = kh.Ma_KH
         join nhanvien nv on px.Ma_NV = nv.Ma_NV
where YEAR(px.Ngay_Ban) = 2018
  and MONTH(px.Ngay_Ban) between 5 and 6
group by px.So_PX;

/*
    6.15.Cuối ngày, nhân viên tổng kết các hóa đơn bán hàng trong ngày, thông tin gồm:
    số phiếu xuất, mã khách hàng, tên khách hàng, họ tên nhân viên bán hàng, ngày bán, trị giá.

*/

select px.So_PX,
       kh.Ma_KH,
       kh.Ten_KH,
       nv.Ho_Ten                         as 'họ tên nhân viên bán hàng',
       DAY(px.Ngay_Ban)                  as 'Ngày bán',
       MONTH(px.Ngay_Ban)                as 'Tháng',
       SUM(ctpx.Gia_Ban * ctpx.So_Luong) as 'Trị giá'
from ctphieuxuat ctpx
         join phieuxuat px on ctpx.So_PX = px.So_PX
         join khachhang kh on px.Ma_KH = kh.Ma_KH
         join nhanvien nv on px.Ma_NV = nv.Ma_NV
where DAY(px.Ngay_Ban) = CURRENT_DATE()
group by px.So_PX, DAY(px.Ngay_Ban)
order by DAY(px.Ngay_Ban);


/*
    16.Thống kê doanh số bán hàng theo từng nhân viên, gồm thông tin:
    mã nhân viên, họ tên nhân viên, mã sản phẩm, tên sản phẩm, đơn vị tính, tổng số
lượng.
*/

select nv.Ma_NV, nv.Ho_Ten, ctpx.Ma_SP, sp.Ten_SP, sp.Don_Vi_Tinh, sum(ctpx.So_Luong) as 'Tổng số sp'
from ctphieuxuat ctpx
         join phieuxuat px on ctpx.So_PX = px.So_PX
         join sanpham sp on ctpx.Ma_SP = sp.Ma_SP
         join khachhang kh on px.Ma_KH = kh.Ma_KH
         join nhanvien nv on px.Ma_NV = nv.Ma_NV
group by nv.Ma_NV, ctpx.Ma_SP;

/*
    17.Liệt kê các hóa đơn bán hàng cho khách vãng lai (KH01) trong quý 2/2018,
thông tin gồm số phiếu xuất, ngày bán, mã sản phẩm, tên sản phẩm, đơn vị
tính, số lượng, đơn giá, thành tiền.

*/
select ctpx.So_PX,
       px.Ngay_Ban,
       ctpx.Ma_SP,
       sp.Ten_SP,
       sp.Don_Vi_Tinh,
       ctpx.So_Luong,
       ctpx.Gia_Ban,
       ctpx.Gia_Ban * ctpx.So_PX as 'Thành tiền'
from ctphieuxuat ctpx
         join phieuxuat px on ctpx.So_PX = px.So_PX
         join sanpham sp on ctpx.Ma_SP = sp.Ma_SP
         join khachhang kh on px.Ma_KH = kh.Ma_KH
         join nhanvien nv on px.Ma_NV = nv.Ma_NV
where kh.Ma_KH = 'KH01'
  and MONTH(px.Ngay_Ban) = 2
  and YEAR(px.Ngay_Ban) = 2018
group by ctpx.So_PX, sp.Ma_SP;

/*
    18.Liệt kê các sản phẩm chưa bán được trong 6 tháng đầu năm 2018, thông tin
gồm: mã sản phẩm, tên sản phẩm, loại sản phẩm, đơn vị tính.
*/

select sp.Ma_SP, sp.Ten_SP, sp.Ma_Loai_SP, sp.Don_Vi_Tinh
from sanpham sp
         join ctphieuxuat ctpx on sp.Ma_SP = ctpx.Ma_SP
         join phieuxuat px on ctpx.So_PX = px.So_PX
where sp.Ma_SP not in (select sp.Ma_SP
                       from sanpham sp
                                join ctphieuxuat ctpx on sp.Ma_SP = ctpx.Ma_SP
                                join phieuxuat px on ctpx.So_PX = px.So_PX
                       where YEAR(px.Ngay_Ban) = 2018
                         and MONTH(px.Ngay_Ban) between 6 and 12)
group by sp.Ma_SP;


/*
    19.Liệt kê danh sách nhà cung cấp không giao dịch mua bán với cửa hàng trong
quý 2/2018, gồm thông tin: mã nhà cung cấp, tên nhà cung cấp, địa chỉ, số
điện thoại.

*/

select ncc.Ma_NCC, ncc.Ten_NCC, ncc.Dia_Chi, ncc.Dien_Thoai
from nhacungcap ncc
         join phieunhap pn on ncc.Ma_NCC = pn.Ma_NCC
where ncc.Ma_NCC not in (select pn.Ma_NCC
                         from phieunhap pn
                         where YEAR(pn.Ngay_Nhap) = 2018
                           and MONTH(pn.Ngay_Nhap) = 2)
group by ncc.Ma_NCC;


/*
    20.Cho biết khách hàng có tổng trị giá đơn hàng lớn nhất trong 6 tháng đầu năm 2018.
*/

select kh.Ma_KH, kh.Ten_KH, sum(ctpx.Gia_Ban * ctpx.So_Luong) as 'Tổng trị giá đơn hàng'
from khachhang kh
         join phieuxuat px on kh.Ma_KH = px.Ma_KH
         join ctphieuxuat ctpx on px.So_PX = ctpx.So_PX
where YEAR(px.Ngay_Ban) = 2018
  and MONTH(px.Ngay_Ban) between 1 and 6
group by kh.Ma_KH
order by sum(ctpx.Gia_Ban * ctpx.So_Luong) desc
limit 1;


/*
    21.Cho biết mã khách hàng và số lượng đơn đặt hàng của mỗi khách hàng.
*/

select px.Ma_KH, count(*) as 'Số lượng đơn đặt hàng'
from phieuxuat px
group by px.Ma_KH;

/*
    22.Cho biết mã nhân viên, tên nhân viên, tên khách hàng kể cả những nhân viên
không đại diện bán hàng.

*/

select distinct nv.Ma_NV,
                nv.Ho_Ten as 'Tên nhân viên',
                kh.Ma_KH,
                kh.Ten_KH
from nhanvien nv
         left join phieuxuat px on px.Ma_NV = nv.Ma_NV
         left join khachhang kh on kh.Ma_KH = px.Ma_KH;


/*
    23.Cho biết số lượng nhân viên nam, số lượng nhân viên nữ
*/

select COUNT(case when nv.Gioi_Tinh = N'true' then 1 else 0 end)  as 'Số lượng nhân viên nữ',
       COUNT(case when nv.Gioi_Tinh = N'false' then 1 else 0 end) as 'Số lượng nhân viên nam'
from nhanvien nv
group by nv.Gioi_Tinh;

/*
    24.Cho biết mã nhân viên, tên nhân viên, số năm làm việc của những nhân viên
có thâm niên cao nhất.

*/

select Nv.Ma_NV,
       NV.Ho_Ten,
       year(NOW()) - year(nv.Ngay_Vao_Lam) as 'Số năm làm việc'
from nhanvien nv
where year(NOW()) - year(NV.Ngay_Vao_Lam) = (select max(year(NOW()) - year(NV.Ngay_Vao_Lam))
                                             from nhanvien nv);

/*
    25.Hãy cho biết họ tên của những nhân viên đã đến tuổi về hưu (nam:60 tuổi,
nữ: 55 tuổi)
*/

select nv.Ho_Ten, YEAR(NOW()) - YEAR(nv.Ngay_Sinh) as 'Tuổi'
from nhanvien nv
where (nv.Gioi_Tinh = N'true' and YEAR(NOW()) - YEAR(nv.Ngay_Sinh) >= 55)
   or (nv.Gioi_Tinh = N'false' and YEAR(NOW()) - YEAR(nv.Ngay_Sinh) >= 60);

/*
    26.Hãy cho biết họ tên của nhân viên và năm về hưu của họ.
*/

select nv.Ho_Ten,
       (case nv.Gioi_Tinh
            when N'true' then (YEAR(NOW())
                                   + (55 - (YEAR(NOW()) - year(nv.Ngay_Sinh))))
            when N'false' then (YEAR(NOW())
                + (60 - (YEAR(NOW()) - year(nv.Ngay_Sinh))))
           end) as 'Năm về hưu'
from nhanvien nv;

/*
    27.Cho biết tiền thưởng tết dương lịch của từng nhân viên. Biết rằng - thâm
niên <1 năm thưởng 200.000 - 1 năm <= thâm niên < 3 năm thưởng
400.000 - 3 năm <= thâm niên < 5 năm thưởng 600.000 - 5 năm <= thâm
niên < 10 năm thưởng 800.000 - thâm niên >= 10 năm thưởng 1.000.000

*/

select nv.Ma_NV ,
       nv.Ho_Ten,
       case
           when year(NOW()) - year(nv.Ngay_Vao_Lam) < 1 then 200000
           when year(NOW()) - year(nv.Ngay_Vao_Lam) < 3 then 400000
           when year(NOW()) - year(nv.Ngay_Vao_Lam) < 5 then 600000
           when year(NOW()) - year(nv.Ngay_Vao_Lam) < 10 then 800000
           else 1000000
           end as TienThuong
from nhanvien nv;

/*
    28.Cho biết những sản phẩm thuộc ngành hàng Hóa mỹ phẩm

*/

select *
from sanpham sp join LOAISP lsp on sp.Ma_Loai_SP=lsp.Ma_Loai_SP
where lsp.Ten_Loai_SP='Hàng hoá mỹ phẩm';

/*
    29.Cho biết những sản phẩm thuộc loại Quần áo.

*/
select *
from sanpham sp join LOAISP lsp on sp.Ma_Loai_SP=lsp.Ma_Loai_SP
where lsp.Ten_Loai_SP='Quần áo';

/*
    30.Cho biết số lượng sản phẩm loại Quần áo.
*/
select sum(lsp.Ten_Loai_SP) as 'Sản phẩm loại Quần áo'
from sanpham sp join LOAISP lsp on sp.Ma_Loai_SP=lsp.Ma_Loai_SP
where lsp.Ten_Loai_SP='Quần áo';

/*
    31.Cho biết số lượng loại sản phẩm ngành hàng Hóa mỹ phẩm
*/

select sum(lsp.Ten_Loai_SP) as 'Số lượng hàng hoá mỹ phẩm'
from sanpham sp join LOAISP lsp on sp.Ma_Loai_SP=lsp.Ma_Loai_SP
where lsp.Ten_Loai_SP='Hàng hoá mỹ phẩm';

/*
    32.Cho biết số lượng sản phẩm theo từng loại sản phẩm
*/

select sp.Ten_SP,count(lsp.Ten_Loai_SP) as 'Số lượng sản phẩm theo từng loại sản phẩm'
from sanpham sp join LOAISP lsp on sp.Ma_Loai_SP=lsp.Ma_Loai_SP
group by sp.Ten_SP,lsp.Ten_Loai_SP;

