use quanlydiemsv;
/*
    50.Cho biết những sinh viên đạt điểm cao nhất trong từng môn.

*/
#
# select kq.MaMH,kq.MaSV,kq.Diem
# from ketqua kq
# where kq.Diem (select kq.MaMH,sum(kq.Diem) as 'max'
#                 from ketqua kq
#                 group by kq.MaMH);

/*
    51.Cho biết những khoa không có sinh viên học
*/

select *
from dmkhoa khoa
where khoa.MaKhoa not in (select distinct sv.MaKhoa
                          from ketqua kq
                                   join dmsv sv on kq.MaSV = sv.MaSV
                                   join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
                          group by sv.MaKhoa);

/*
    52.Cho biết sinh viên chưa thi môn cơ sở dữ liệu.
*/
select *
from dmsv sv
where sv.MaSV not in (select sv.MaSV
                      from dmsv sv
                               join ketqua kq on sv.MaSV = kq.MaSV
                               join dmmh mh on kq.MaMH = mh.MaMH
                      where mh.TenMH = 'Cơ Sở Dữ Liệu'
                      group by sv.MaSV);

/*
    53.Cho biết sinh viên nào không thi lần 1 mà có dự thi lần 2.
*/

select *
from dmsv sv
where sv.MaSV not in (select sv.MaSV
                      from dmsv sv
                               join ketqua kq on sv.MaSV = kq.MaSV
                      where kq.LanThi = 1
                      group by sv.MaSV);

/*
    54.Cho biết môn nào không có sinh viên khoa anh văn học.

*/
select mh.TenMH
from dmmh mh
         join ketqua kq on mh.MaMH = kq.MaMH
         join dmsv sv on kq.MaSV = sv.MaSV
where mh.TenMH not in (select mh.TenMH
                       from dmmh mh
                                join ketqua kq on mh.MaMH = kq.MaMH
                                join dmsv sv on kq.MaSV = sv.MaSV
                                join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
                       where khoa.TenKhoa = 'Anh Văn'
                       group by mh.TenMH)
group by mh.TenMH;

/*
    55.Cho biết những sinh viên khoa anh văn chưa học môn văn phạm
*/
select sv.MaSV, sv.HoSV, sv.TenSV, sv.NoiSinh, khoa.TenKhoa
from dmsv sv
         join ketqua kq on sv.MaSV = kq.MaSV
         join dmmh mh on kq.MaMH = mh.MaMH
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
where khoa.TenKhoa = 'Anh Văn'
  and sv.MaSV not in (select sv.MaSV
                      from dmsv sv
                               join ketqua kq on sv.MaSV = kq.MaSV
                               join dmmh mh on kq.MaMH = mh.MaMH
                               join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
                      where khoa.TenKhoa = 'Anh Văn'
                        and mh.TenMH = 'Văn Phạm'
                      group by sv.MaSV)
group by sv.MaSV;

/*
    56.Cho biết những sinh viên không rớt môn nào.
*/
select sv.MaSV
from dmsv sv
where sv.MaSV not in (select kq.MaSV
                      from ketqua kq
                      where kq.Diem <= 5
                      group by kq.MaSV)
group by sv.MaSV;

/*
    57.Cho biết những sinh viên học khoa anh văn có học bổng và những sinh viên
chưa bao giờ rớt.

*/

select *
from dmsv sv
         join ketqua kq on sv.MaSV = kq.MaSV
         join dmkhoa khoa on sv.MaKhoa = khoa.MaKhoa
where khoa.TenKhoa = 'Anh Văn'
  and sv.HocBong > 0
  and sv.MaSV not in (select kq.MaSV
                      from ketqua kq
                      where kq.Diem < 5);

/*
    58.Cho biết khoa nào có đông sinh viên nhận học bổng nhất và khoa nào khoa
nào có ít sinh viên nhận học bổng nhất
*/

select khoa.MaKhoa,count(sv.MaSV) as 'Số lượng sinh viên'
from dmkhoa khoa join dmsv sv on khoa.MaKhoa=sv.MaKhoa
where sv.HocBong>0
group by khoa.MaKhoa
having count(sv.MaSV) >= all (
    select count(*)
    from dmsv sv
    where sv.HocBong>0
    group by sv.MaKhoa
)
union
select khoa.MaKhoa,count(sv.MaSV) as 'Số lượng sinh viên'
from dmkhoa khoa join dmsv sv on khoa.MaKhoa=sv.MaKhoa
where sv.HocBong>0
group by khoa.MaKhoa
having count(sv.MaSV) <= all (
    select count(*)
    from dmsv sv
    where sv.HocBong>0
    group by sv.MaKhoa
);

/*
    59.Cho biết 3 sinh viên học nhiều môn nhất.
*/
select kq.MaSV,count(*) as 'Số lượng môn học'
from ketqua kq
group by kq.MaSV
limit 3;

/*
    60.Cho biết những môn được tất cả các sinh viên theo học
*/

select kq.MaMH as 'Môn học mà tất cả các sinh viên theo học'
from ketqua kq
group by kq.MaMH
having count(distinct kq.MaSV)=(select count(sv.MaSV)
                                from dmsv sv);

/*
    61.Cho biết những sinh viên học những môn giống sinh viên có mã số A02 học.
*/
select distinct kq.MaSV
from ketqua kq
where exists(
    select distinct kq2.MaMH
        from ketqua kq2
      where kq2.MaSV='A02'and kq2.MaMH=kq.MaMH
);

/*
    62.Cho biết những sinh viên học những môn bằng đúng những môn mà sinh
viên A02 học
*/








