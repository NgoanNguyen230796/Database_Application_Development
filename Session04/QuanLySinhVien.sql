Create Database QuanLyDiemSV CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci;
use QuanLyDiemSV;
/*=============DANH MUC KHOA==============*/
Create table DMKhoa
(
    MaKhoa  char(2) primary key,
    TenKhoa nvarchar(30) not null
);
/*==============DANH MUC SINH VIEN============*/
Create table DMSV
(
    MaSV     char(3)      not null primary key,
    HoSV     nvarchar(15) not null,
    TenSV    nvarchar(7)  not null,
    Phai     nchar(7),
    NgaySinh datetime     not null,
    NoiSinh  nvarchar(20),
    MaKhoa   char(2),
    HocBong  float
);
/*===================MON HOC========================*/
create table DMMH
(
    MaMH   char(2)      not null,
    TenMH  nvarchar(25) not null,
    SoTiet tinyint,
    Constraint DMMH_MaMH_pk primary key (MaMH)
);
/*=====================KET QUA===================*/
Create table KetQua
(
    MaSV   char(3) not null,
    MaMH   char(2) not null,
    LanThi tinyint,
    Diem   decimal(4, 2),
    Constraint KetQua_MaSV_MaMH_LanThi_pk primary key (MaSV, MaMH, LanThi)
);
/*==========================TAO KHOA NGOAI==============================*/
Alter table dmsv
    add Constraint DMKhoa_MaKhoa_fk foreign key (MaKhoa)
        References DMKhoa (MaKhoa);
Alter table KetQua
    add constraint KetQua_MaSV_fk foreign key (MaSV) references DMSV (MaSV);
Alter table KetQua
    add constraint DMMH_MaMH_fk foreign key (MaMH) references DMMH (MaMH);
/*==================NHAP DU LIEU====================*/
/*==============NHAP DU LIEU DMMH=============*/
Insert into DMMH(MaMH, TenMH, SoTiet)
values ('01', 'Cơ Sở Dữ Liệu', 45);
Insert into DMMH(MaMH, TenMH, SoTiet)
values ('02', 'Trí Tuệ Nhân Tạo', 45);
Insert into DMMH(MaMH, TenMH, SoTiet)
values ('03', 'Truyền Tin', 45);
Insert into DMMH(MaMH, TenMH, SoTiet)
values ('04', 'Đồ Họa', 60);
Insert into DMMH(MaMH, TenMH, SoTiet)
values ('05', 'Văn Phạm', 60);
/*==============NHAP DU LIEU DMKHOA=============*/
Insert into DMKhoa(MaKhoa, TenKhoa)
values ('AV', 'Anh Văn');
Insert into DMKhoa(MaKhoa, TenKhoa)
values ('TH', 'Tin Học');
Insert into DMKhoa(MaKhoa, TenKhoa)
values ('TR', 'Triết');
Insert into DMKhoa(MaKhoa, TenKhoa)
values ('VL', 'Vật Lý');
/*==============NHAP DU LIEU DMSV=============*/
Insert into DMSV
values ('A01', 'Nguyễn Thị', 'Hải', 'Nữ', '1990-03-20', 'Hà Nội', 'TH', 130000);
Insert into DMSV(MaSV, HoSV, TenSV, Phai, NgaySinh, NoiSinh, MaKhoa, HocBong)
values ('A02', 'Trần Văn', 'Chính', 'Nam', '1992-12-24', 'Bình Định', 'VL', 150000);
Insert into DMSV(MaSV, HoSV, TenSV, Phai, NgaySinh, NoiSinh, MaKhoa, HocBong)
values ('A03', 'Lê Thu Bạch', 'Yến', 'Nữ', '1990-02-21', 'TP Hồ Chí Minh', 'TH', 170000);
Insert into DMSV(MaSV, HoSV, TenSV, Phai, NgaySinh, NoiSinh, MaKhoa, HocBong)
values ('A04', 'Trần Anh', 'Tuấn', 'Nam', '1990-12-20', 'Hà Nội', 'AV', 80000);
Insert into DMSV(MaSV, HoSV, TenSV, Phai, NgaySinh, NoiSinh, MaKhoa, HocBong)
values ('B01', 'Trần Thanh', 'Mai', 'Nữ', '1991-08-12', 'Hải Phòng', 'TR', 0);
Insert into DMSV(MaSV, HoSV, TenSV, Phai, NgaySinh, NoiSinh, MaKhoa, HocBong)
values ('B02', 'Trần Thị Thu', 'Thủy', 'Nữ', '1991-01-02', 'TP Hồ Chí Minh', 'AV', 0);
/*==============NHAP DU LIEU BANG KET QUA=============*/
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A01', '01', 1, 3);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A01', '01', 2, 6);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A01', '02', 2, 6);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A01', '03', 1, 5);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A02', '01', 1, 4.5);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A02', '01', 2, 7);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A02', '03', 1, 10);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A02', '05', 1, 9);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A03', '01', 1, 2);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A03', '01', 2, 5);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A03', '03', 1, 2.5);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A03', '03', 2, 4);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('A04', '05', 2, 10);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('B01', '01', 1, 7);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('B01', '03', 1, 2.5);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('B01', '03', 2, 5);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('B02', '02', 1, 6);
Insert into KetQua(MaSV, MaMH, LanThi, Diem)
values ('B02', '04', 1, 10);

/*
    1. Liệt kê danh sách sinh viên, gồm các thông tin sau:
    Mã sinh viên, Họ sinh viên, Tên sinh viên, Học bổng.
    Danh sách sẽ được sắp xếp theo thứ tự Mã sinh viên tăng dần
*/

select sv.MaSV, sv.TenSV, sv.HocBong
from dmsv sv
order by sv.MaSV;

/*
    2. . Danh sách các sinh viên gồm thông tin sau:
    Mã sinh viên, họ tên sinh viên, Phái, Ngày sinh.
    Danh sách sẽ được sắp xếp theo thứ tự Nam/Nữ.
*/
select sv.MaSV, sv.TenSV, sv.Phai, sv.NgaySinh
from dmsv sv
order by sv.Phai;
/*

    3. Thông tin các sinh viên gồm: Họ tên sinh viên, Ngày sinh, Học bổng.
    Thông tin sẽ được sắp xếp theo thứ tự Ngày sinh tăng dần và Học bổng giảm dần.
*/
select sv.TenSV, sv.NgaySinh, sv.HocBong
from dmsv sv
order by sv.NgaySinh, sv.HocBong desc;

/*
    4. Danh sách các môn học có tên bắt đầu bằng chữ T, gồm các thông tin:
    Mã môn, Tên môn, Số tiết.
*/
select mh.MaMH, mh.TenMH, mh.SoTiet
from dmmh mh
where mh.TenMH like 'T%';

/*
    5. Liệt kê danh sách những sinh viên có chữ cái cuối cùng trong tên là I,
    gồm các thông tin: Họ tên sinh viên, Ngày sinh, Phái.
*/

select concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên sinh viên', sv.NgaySinh, sv.Phai
from dmsv sv
where sv.TenSV like '%I';

/*
    6.Danh sách những khoa có ký tự thứ hai của tên khoa có chứa chữ N,
    gồm các thông tin: Mã khoa, Tên khoa.
*/

select khoa.MaKhoa, khoa.TenKhoa
from dmkhoa khoa
where khoa.TenKhoa like '_N%';

/*
    7.Liệt kê những sinh viên mà họ có chứa chữ Thị
*/
select *
from DMSV;
select *
from dmsv sv
where sv.HoSV like '%Thị%';

/*
    8.Cho biết danh sách các sinh viên có học bổng lớn hơn 100,000, gồm các thông tin:
    Mã sinh viên, Họ tên sinh viên, Mã khoa, Học bổng. Danh sách sẽ được sắp xếp theo thứ tự Mã khoa giảm dần
*/

select sv.MaSV, sv.HoSV, sv.TenSV, sv.MaKhoa, sv.HocBong
from dmsv sv
where sv.HocBong > 100000
order by sv.MaKhoa desc;

/*
    9. Liệt kê các sinh viên có học bổng từ 150,000 trở lên và sinh ở Hà Nội,
    gồm các thông tin: Họ tên sinh viên, Mã khoa, Nơi sinh, Học bổng.
*/

select *
from dmsv;
select sv.HoSV, sv.MaKhoa, sv.NoiSinh, sv.HocBong
from dmsv sv
where sv.HocBong >= 150000
  and sv.NoiSinh = 'Hà Nội';

/*
    10.Danh sách các sinh viên của khoa Anh văn và khoa Vật lý, gồm các thông tin:
    Mã sinh viên, Mã khoa, Phái.
*/

select *
from dmkhoa;

select sv.MaSV, khoa.MaKhoa, sv.Phai
from dmkhoa khoa
         join dmsv sv on khoa.MaKhoa = sv.MaKhoa
where khoa.TenKhoa = 'Anh Văn'
   or khoa.TenKhoa = 'Vật lý';

/*

    11.Cho biết những sinh viên có ngày sinh từ ngày 01/01/1991 đến ngày 05/06/1992 gồm các thông tin:
    Mã sinh viên, Ngày sinh, Nơi sinh, Học bổng.
*/
select sv.MaSV, sv.NgaySinh, sv.NoiSinh, sv.HocBong
from dmsv sv
where sv.NgaySinh between '1991/01/01' and '1992/06/05';

/*
    12.Danh sách những sinh viên có học bổng từ 80.000 đến 150.000,
    gồm các thông tin: Mã sinh viên, Ngày sinh, Phái, Mã khoa.
*/
select sv.MaSV, sv.NgaySinh, sv.Phai, sv.MaKhoa
from dmsv sv
where sv.HocBong between 80000 and 150000;

/*
    13.Cho biết những môn học có số tiết lớn hơn 30 và nhỏ hơn 45,
    gồm các thông tin: Mã môn học, Tên môn học, Số tiết.

*/
select mh.MaMH, mh.TenMH, mh.SoTiet
from dmmh mh
where mh.SoTiet between 30 and 45;

/*
    14.Liệt kê những sinh viên nam của khoa Anh văn và khoa tin học,
    gồm các thông tin: Mã sinh viên, Họ tên sinh viên, tên khoa, Phái.
*/
select sv.MaSV, sv.HoSV, sv.TenSV, khoa.TenKhoa, sv.Phai
from dmkhoa khoa
         join dmsv sv on khoa.MaKhoa = sv.MaKhoa
where sv.Phai = 'Nam'
  and khoa.TenKhoa = N'Anh văn'
  and khoa.TenKhoa = N'Tin học';

/*
    15.Liệt kê những sinh viên nữ, tên có chứa chữ N
*/

select *
from dmsv sv
where sv.Phai = 'Nữ'
  and sv.TenSV like '%N%';

/*
	16.Danh sách sinh viên có nơi sinh ở Hà Nội và sinh vào tháng 02, 
    gồm các thông tin: Họ sinh viên, Tên sinh viên, Nơi sinh, Ngày sinh.
*/
select sv.HoSV, sv.TenSV, sv.NoiSinh, sv.NgaySinh
from dmsv sv
where sv.NoiSinh = 'Hà Nội'
  and month(sv.NgaySinh) = 2;

/*
    17.Cho biết những sinh viên có tuổi lớn hơn 20, thông tin gồm:
    Họ tên sinh viên, Tuổi,Học bổng.
*/
select sv.HoSV, year(now()) - year(sv.NgaySinh) as 'Tuổi', sv.HocBong
from dmsv sv
where year(now()) - year(sv.NgaySinh) > 20;

/*
    18.Danh sách những sinh viên có tuổi từ 20 đến 25, thông tin gồm:
    Họ tên sinh viên, Tuổi, Tên khoa.

*/
select sv.HoSV, year(now()) - year(sv.NgaySinh) as 'Tuổi', khoa.TenKhoa
from dmsv sv
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
where (year(now()) - year(sv.NgaySinh)) between 20 and 25;


/*
    19.Danh sách sinh viên sinh vào mùa xuân (Mùa xuân bắt đầu từ tháng 3 – và kết thúc tháng 5) năm 1990, gồm các thông tin:
    Họ tên sinh viên,Phái, Ngày sinh.
*/

select sv.HoSV, sv.Phai, sv.NgaySinh
from dmsv sv
where year(sv.NgaySinh) = 1990
  and month(sv.NgaySinh) between 3 and 5;

/*
    20.Cho biết thông tin về mức học bổng của các sinh viên, gồm:
    Mã sinh viên, Phái, Mã khoa, Mức học bổng.
    Trong đó, mức học bổng sẽ hiển thị là “Học bổng cao” nếu giá trị của field học bổng lớn hơn 500,000
    và ngược lại hiển thị là “Mức trung bình”

*/

select sv.MaSV,
       sv.Phai,
       sv.MaKhoa,
       (case when sv.HocBong > 500000 then N'Học bổng cao' else N'Học bổng trung bình' end) as 'Mức học bổng'
from dmsv sv;

/*
    21.Cho biết tổng số sinh viên của toàn trường
*/

select count(*) as 'Tổng số sv của toàn trường'
from dmsv;

/*
    22.Cho biết tổng sinh viên và tổng sinh viên nữ.
*/

select count(*) as 'Tổng sinh viên', sum(case sv.Phai when N'Nữ' then 1 else 0 end) as 'Tổng sinh viên nữ'
from dmsv sv;

/*
    23.Cho biết tổng số sinh viên của từng khoa.
*/
select sv.MaKhoa, count(sv.MaKhoa) as 'Tổng số sinh viên'
from dmsv sv
group by sv.MaKhoa;

/*
    24.Cho biết số lượng sinh viên học từng môn.

*/
select mh.TenMH, count(*) as 'Tổng số sinh viên'
from dmmh mh
         join ketqua kq on mh.MaMH = kq.MaMH
group by mh.TenMH;

/*
    25.Cho biết số lượng môn học mà sinh viên đã học
    (tức tổng số môn học có trong bảng kq)


*/

select count(distinct mamh) 'Tổng số môn học'
from ketqua;

/*

    26.Cho biết tổng số học bổng của mỗi khoa.

*/

select khoa.TenKhoa as 'Tên khoa', count(sv.HocBong) as 'Tổng số học bổng của mỗi khoa'
from dmsv sv
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
group by khoa.TenKhoa;

/*
    27.Cho biết học bổng cao nhất của mỗi khoa.

*/

select khoa.TenKhoa as 'Tên khoa', MAX(sv.HocBong) as 'Học bổng cao nhất của mỗi khoa'
from dmsv sv
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
group by khoa.TenKhoa;

/*
    28.Cho biết tổng số sinh viên nam và tổng số sinh viên nữ của mỗi khoa

*/
select khoa.TenKhoa                                    as 'Tên khoa',
       sum(case sv.Phai when N'Nam' then 1 else 0 end) as 'Tổng sv Nam ',
       sum(case sv.Phai when N'Nữ' then 1 else 0 end)  as 'Tổng sv Nữ'
from dmsv sv
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
group by khoa.TenKhoa;

/*
    29.Cho biết số lượng sinh viên theo từng độ tuổi.
*/

select YEAR(NOW()) - YEAR(sv.NgaySinh) as 'Tuổi', count(sv.MaSV) as 'Số lượng sinh viên'
from dmsv sv
group by YEAR(NOW()) - YEAR(sv.NgaySinh);

/*
    30.Cho biết những năm sinh nào có 2 sinh viên đang theo học tại trường.
*/

select YEAR(sv.NgaySinh) as 'Năm sinh', count(*) as 'Số lượng sinh viên'
from dmsv sv
group by YEAR(sv.NgaySinh)
having count(*) = 2;

/*
    31.Cho biết những nơi nào có hơn 2 sinh viên đang theo học tại trường.
*/
select sv.NoiSinh, count(*) as 'Số lượng sinh viên đang theo học tại trường'
from dmsv sv
group by sv.NoiSinh
having count(*) > 2;

/*
    32.Cho biết những môn nào có trên 3 sinh viên dự thi.

*/

select mh.TenMH as 'Tên môn học có trên 3 sinh viên dự thi'
from ketqua kq
         join dmmh mh on kq.MaMH = mh.MaMH
group by mh.TenMH
having count(kq.MaSV) > 3;

/*
    33.Cho biết những sinh viên thi lại trên 2 lần.
*/

select sv.TenSV as 'Tên sinh viên', count(kq.LanThi) as 'Số lần thi lại'
from ketqua kq
         join dmsv sv on kq.MaSV = sv.MaSV
group by sv.TenSV
having count(kq.LanThi) > 2;

/*
    34.Cho biết những sinh viên nam có điểm trung bình lần 1 trên 7.0

*/
select concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên sinh viên',
       sv.Phai                        as 'Giới tính',
       kq.LanThi                      as 'Lần thi',
       avg(kq.Diem)                   as 'Điểm thi TB lần 1'
from ketqua kq
         join dmsv sv on kq.MaSV = sv.MaSV
where sv.Phai = 'Nam'
  and kq.LanThi = 1
group by concat(sv.HoSV, ' ', sv.TenSV), sv.Phai, kq.LanThi
having avg(kq.Diem) > 7.0;

/*
    35.Cho biết danh sách các sinh viên rớt trên 2 môn ở lần thi 1
*/

select *
from ketqua;
select sv.MaSV, kq.LanThi, count(kq.Diem)
from ketqua kq
         join dmsv sv on kq.MaSV = sv.MaSV
where kq.LanThi = 1
  and kq.Diem < 5.0
group by sv.MaSV, kq.LanThi
having count(kq.Diem) > 2;

/*
    36.Cho biết danh sách những khoa có nhiều hơn 2 sinh viên nam

*/
select khoa.TenKhoa, sv.Phai, count(sv.Phai) as 'Số sinh viên nam'
from dmkhoa khoa
         join dmsv sv on khoa.MaKhoa = sv.MaKhoa
where sv.Phai = N'Nam'
group by khoa.TenKhoa, sv.Phai
having count(sv.Phai) > 2;


/*
    37.Cho biết những khoa có 2 sinh viên đạt học bổng từ 200.000 đến 300.000.
*/

select khoa.TenKhoa as 'Những khoa có 2 sinh đạt học bổng từ 200.000 đến 300.000'
from dmsv sv
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
where sv.HocBong between 200000 and 300000
group by khoa.TenKhoa
having count(sv.HocBong) = 2;

/*
   38.Cho biết số lượng sinh viên đậu
   và số lượng sinh viên rớt của từng môn trong lần thi 1.

*/
select mh.TenMH,
       sum(case when kq.Diem >= 5 then 1 else 0 end) as 'Số lượng sinh viên đậu',
       sum(case when kq.Diem < 5 then 1 else 0 end)  as 'Số lượng sinh viên rớt'
from ketqua kq
         join dmmh mh on kq.MaMH = mh.MaMH
where kq.LanThi = 1
group by mh.TenMH;

/*
    39.Cho biết sinh viên nào có học bổng cao nhất
*/
select *
from dmsv sv
order by sv.HocBong desc
limit 1;

/* C2:
select * from dmsv sv
where sv.HocBong=(select max(HocBong) from dmsv);
*/

/*
    40.Cho biết sinh viên nào có điểm thi lần 1 môn cơ sở dữ liệu cao nhất.
*/
select concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên sv',
       kq.LanThi                      as 'Lần thi',
       kq.Diem                        as 'Điểm thi cao nhất môn cơ sở dữ liệu'
from ketqua kq
         join dmsv sv on kq.MaSV = sv.MaSV
         join dmmh mh on kq.MaMH = mh.MaMH
where kq.LanThi = 1
  and mh.TenMH = 'Cơ Sở Dữ Liệu'
group by concat(sv.HoSV, ' ', sv.TenSV), kq.LanThi, kq.Diem
order by kq.Diem desc
limit 1;

/*
    41.Cho biết sinh viên khoa anh văn có tuổi lớn nhất.
*/
select khoa.TenKhoa as 'Tên khoa', YEAR(NOW()) - YEAR(sv.NgaySinh) as 'Tuổi lớn nhất'
from dmsv sv
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
where khoa.TenKhoa = N'Anh Văn'
group by khoa.TenKhoa, YEAR(NOW()) - YEAR(sv.NgaySinh)
order by YEAR(NOW()) - YEAR(sv.NgaySinh) desc
limit 1;

/*
    42.Cho biết khoa nào có đông sinh viên nhất.

*/

select khoa.TenKhoa as 'Tên khoa có đông sinh viên nhất'
from dmkhoa khoa
         join dmsv sv on khoa.MaKhoa = sv.MaKhoa
group by khoa.TenKhoa
having count(khoa.TenKhoa) >= all (select count(sv.masv)
                                   from dmsv sv
                                   group by sv.MaKhoa);


/*
    43.Cho biết khoa nào có đông nữ nhất.
*/

select khoa.TenKhoa as 'Tên khoa có đông nữ nhất'
from dmkhoa khoa
         join dmsv sv on khoa.MaKhoa = sv.MaKhoa
where sv.phai = N'nữ'
group by khoa.TenKhoa
having count(khoa.TenKhoa) >= all (select count(masv)
                                   from dmsv sv
                                   where sv.phai = N'nữ'
                                   group by sv.makhoa);

/*
    44.Cho biết môn nào có nhiều sinh viên rớt lần 1 nhiều nhất
*/
select mh.TenMH, count(kq.Diem) as 'Số lượng sinh viên rớt nhiều nhất'
from ketqua kq
         join dmmh mh on kq.MaMH = mh.MaMH
where kq.LanThi = 1
  and kq.Diem < 5
group by mh.TenMH
having count(kq.Diem) >= all (select count(kq.diem)
                              from ketqua kq
                              where kq.lanthi = 1
                                and kq.diem < 5
                              group by kq.mamh);


/*
    45.Cho biết sinh viên không học khoa anh văn có điểm thi môn phạm lớn hơn
điểm thi văn của sinh viên học khoa anh văn.


*/


/*
    46.Cho biết sinh viên có nơi sinh cùng với Hải.
*/

select sv.MaSV, concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên sv'
from dmsv sv
where sv.NoiSinh = (select sv.NoiSinh
                    from dmsv sv
                    where sv.TenSV = N'Hải');

/*
    47.Cho biết những sinh viên nào có học bổng lớn hơn
    tất cả học bổng của sinh viên thuộc khoa anh văn
*/
select concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên sv', sv.HocBong
from dmsv sv
where sv.HocBong >
      (select sum(sv.HocBong)
       from dmsv sv
                join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
       where khoa.TenKhoa = N'Anh Văn'
       group by khoa.TenKhoa);

/*
    48.Cho biết những sinh viên có học bổng lớn hơn bất kỳ học bổng của sinh viên
học khóa anh văn


*/

select concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên SV'
from dmsv sv
where hocbong >= all (select sv.hocbong
                      from dmsv sv
                               join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa

                      where khoa.TenKhoa = 'Anh Văn');

/*
    49. Cho biết sinh viên nào có điểm thi môn cơ sở dữ liệu lần 2 lớn hơn tất cả điểm
thi lần 1 môn cơ sở dữ liệu của những sinh viên khác
*/

select concat(sv.HoSV, ' ', sv.TenSV) as 'Họ tên SV'
from ketqua kq
         join dmsv sv on kq.MaSV = sv.MaSV
         join dmmh mh on kq.MaMH = mh.MaMH
where kq.LanThi = 2
  and mh.TenMH = N'Cơ Sở Dữ Liệu'
  and kq.Diem >= all (select kq.Diem
                      from ketqua kq
                               join dmmh mh on kq.MaMH = mh.MaMH
                      where mh.TenMH = N'Cơ Sở Dữ Liệu'
                        and kq.LanThi = 1);

/*
    50.Cho biết những sinh viên đạt điểm cao nhất trong từng môn.
*/

select *
from ketqua;



