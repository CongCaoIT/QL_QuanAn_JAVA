﻿CREATE DATABASE QL_QUANAN_JAVA
GO
USE QL_QUANAN_JAVA
GO

CREATE TABLE NHANVIEN 
(
    MANHANVIEN VARCHAR(10) NOT NULL,
    HOTEN NVARCHAR(100) NULL,
    PHAI NVARCHAR(4) NULL CHECK (PHAI = N'Nam' OR PHAI = N'Nữ'),
    NGAYSINH DATE NULL,
    DIACHI NVARCHAR(255) NULL,
    SDT VARCHAR(12) NULL CHECK (SDT LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
    NGAYVAOLAM DATE NULL,
    LUONGCOBAN DECIMAL(12, 2) NULL DEFAULT 0,
    DAXOA BIT NOT NULL DEFAULT 0

    CONSTRAINT PK_NHANVIEN PRIMARY KEY(MANHANVIEN)
)
GO

CREATE TABLE TAIKHOAN
(
    TENDANGNHAP VARCHAR(50) NOT NULL,
    MANHANVIEN VARCHAR(10) NOT NULL,
    TENHIENTHI NVARCHAR(50) NOT NULL,
    MATKHAU NVARCHAR(100) NOT NULL,
    TRANGTHAI NVARCHAR(50) NULL, --NHAN VIEN OR ADMIN
    DAXOA BIT NOT NULL DEFAULT 0,

    CONSTRAINT PK_TAIKHOAN PRIMARY KEY(TENDANGNHAP),
    CONSTRAINT FK_TAIKHOAN_NHANVIEN FOREIGN KEY(MANHANVIEN) REFERENCES NHANVIEN(MANHANVIEN)
)
GO

CREATE TABLE BAN
(
    MABAN INT IDENTITY NOT NULL,
    SOLUONGNGUOI INT NULL CHECK (SOLUONGNGUOI > 0),    
    TRANGTHAI NVARCHAR(255) NULL,
    DAXOA BIT NOT NULL DEFAULT 0,

    CONSTRAINT PK_BAN PRIMARY KEY(MABAN)
)
GO

CREATE TABLE HOADON
(
    MAHOADON INT IDENTITY NOT NULL, 
    MABAN INT NOT NULL,
    MANHANVIEN VARCHAR(10) NOT NULL,
    NGAYVAO DATE NULL,
    NGAYRA DATE NULL,
    GIAMGIA INT NULL DEFAULT 0,
    THANHTIEN DECIMAL(12, 2) NULL DEFAULT 0,

    CONSTRAINT PK_HOADON PRIMARY KEY(MAHOADON),
    CONSTRAINT FK_HOADON_BAN FOREIGN KEY(MABAN) REFERENCES BAN(MABAN),
    CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY(MANHANVIEN) REFERENCES NHANVIEN(MANHANVIEN)
)
GO

CREATE TABLE NGUYENLIEU
(
    MANGUYENLIEU INT IDENTITY NOT NULL,
    TENNGUYENLIEU NVARCHAR(100) NULL UNIQUE,
    SOLUONG INT NOT NULL,
    DAXOA BIT NOT NULL DEFAULT 0,

    CONSTRAINT PK_NGUYENLIEU PRIMARY KEY(MANGUYENLIEU)
)
GO

CREATE TABLE LOAIMONAN
(
    MALOAIMONAN INT IDENTITY NOT NULL,
    TENLOAIMONAN NVARCHAR(50) NULL UNIQUE,
    DAXOA BIT NOT NULL DEFAULT 0,

    CONSTRAINT PK_LOAIMONAN PRIMARY KEY(MALOAIMONAN)
)
GO

CREATE TABLE MONAN
(
    MAMONAN INT IDENTITY NOT NULL,
    MALOAIMONAN INT NOT NULL,
    TENMONAN NVARCHAR(100) NULL UNIQUE,
    DVT NVARCHAR(20) NULL,
    DONGIA DECIMAL(10, 2) NULL DEFAULT 0,
    HINHANH NVARCHAR(MAX) NULL,
    DAXOA BIT NOT NULL DEFAULT 0,

    CONSTRAINT PK_MONAN PRIMARY KEY(MAMONAN),
    CONSTRAINT FK_MONAN_LOAIMONAN FOREIGN KEY(MALOAIMONAN) REFERENCES LOAIMONAN(MALOAIMONAN)
)
GO

CREATE TABLE CONGTHUC
(
    MACONGTHUC INT IDENTITY NOT NULL,
    MAMONAN INT NOT NULL,
    MANGUYENLIEU INT NOT NULL,
    SOLUONG INT NOT NULL DEFAULT 0,
    DONVI NVARCHAR(100) NULL,

    CONSTRAINT PK_CONGTHUC PRIMARY KEY(MACONGTHUC),
    CONSTRAINT FK_CONTHUC_NGUYENLIEU FOREIGN KEY(MANGUYENLIEU) REFERENCES NGUYENLIEU(MANGUYENLIEU), 
    CONSTRAINT FK_CONGTHUC_MONAN FOREIGN KEY(MAMONAN) REFERENCES MONAN(MAMONAN)
)
GO

CREATE TABLE CHITIETHOADON
(
    MACHITIETHD INT IDENTITY NOT NULL,
    MAMONAN INT NOT NULL,
    MAHOADON INT NOT NULL,
    SOLUONG INT NULL DEFAULT 0,

    CONSTRAINT PK_CHITIETHOADON PRIMARY KEY(MACHITIETHD),
    CONSTRAINT FK_CHITIETHOADON_MONAN FOREIGN KEY(MAMONAN) REFERENCES MONAN(MAMONAN),
    CONSTRAINT FK_CHITIETHOADON_HOADON FOREIGN KEY(MAHOADON) REFERENCES HOADON(MAHOADON)
)
GO

CREATE TABLE NHACUNGCAP
(
   MANCC INT IDENTITY NOT NULL,
   TENNC NVARCHAR(100) NOT NULL,
   DIACHI NVARCHAR(255) NOT NULL,
   EMAIL NVARCHAR(100) NOT NULL,
   SDT VARCHAR(12) NOT NULL,

   CONSTRAINT PK_NCC PRIMARY KEY (MANCC)
)
GO

CREATE TABLE PHIEUNHAP
(
    MAPN INT IDENTITY NOT NULL,
    MANCC INT NOT NULL,
    NGAYNHAP DATE NOT NULL,
    DAXOA BIT NOT NULL,
        
    CONSTRAINT PK_PN PRIMARY KEY (MAPN),
    CONSTRAINT FK_PN_NCC FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC) 
)
GO

CREATE TABLE CHITIETPHIEUNHAP
(
    MACTPN INT IDENTITY NOT NULL,
    MAPN INT NOT NULL,
    MANGUYENLIEU INT NOT NULL,
    DONGIANHAP DECIMAL(18,0) NOT NULL,
    SOLUONGNHAP INT NOT NULL,

    CONSTRAINT PK_CTPN PRIMARY KEY (MACTPN),
    CONSTRAINT FK_CTPN_PN FOREIGN KEY(MAPN) REFERENCES PHIEUNHAP(MAPN),
    CONSTRAINT FK_CTPN_NL FOREIGN KEY(MANGUYENLIEU) REFERENCES NGUYENLIEU(MANGUYENLIEU)
)
GO

INSERT INTO NHANVIEN(MANHANVIEN, HOTEN, PHAI, NGAYSINH, DIACHI, SDT, NGAYVAOLAM, LUONGCOBAN) VALUES
('NV001', N'Cao Tấn Công', N'Nam', '2003-10-26', N'17B Tân Trụ, TP. HCM', '0362111265', '2023-10-01', 300000),
('NV002', N'Thanh Thảo', N'Nữ', '2003-10-26', N'17B Tân Trụ, TP. HCM', '0362111265', '2023-10-01', 100000)
GO
INSERT INTO TAIKHOAN(TENDANGNHAP, MANHANVIEN, TENHIENTHI, MATKHAU, TRANGTHAI) VALUES
('admin', 'NV001', N'ADMIN', '1',  N'Admin'),
('TaoPro', 'NV002', N'Công Cao', '1',  N'Nhân viên')
GO
INSERT INTO LOAIMONAN(TENLOAIMONAN) VALUES
(N'Hấp'),
(N'Xào'),
(N'Nướng'),
(N'Đồ nguội'),
(N'Đồ sống'),
(N'Combo'),
(N'Chiên'),
(N'Luộc'),
(N'Món Ăn kèm'),
(N'Món Tráng miệng'),
(N'Đồ uống cồn'),
(N'Đồ uống không cồn')
GO
INSERT INTO MONAN(MALOAIMONAN, TENMONAN, DVT, DONGIA, HINHANH) VALUES
(1, N'Gà hấp sả', N'Con', 150000, null),
(1, N'Cá thu hấp', N'Con', 100000, null), 
(2, N'Bạch Tuộc Xốt Tôm Chua', N'Đĩa', 269000, null),
(2, N'Cá Hồi Xốt Mù Tạt', N'Đĩa', 279000, null),
(2, N'Tôm Lửa Hồng', N'Đĩa', 289000, null),
(2, N'Cá Mai Xốt Ớt Xanh', N'Đĩa', 149000, null),
(2, N'Cà Tím Xào Lá Quế', N'Đĩa', 79000, null),
(2, N'Rau Rừng Xào X.O', N'Đĩa', 99000, null),
(2, N'Bông Cải Baby Xào Tỏi', N'Đĩa', 99000, null),
(2, N'Đọt Su Xào Ba Rọi Xông Khói', N'Đĩa', 109000, null),
(2, N'Bánh Đa Xào Bò', N'Đĩa', 189000, null),
(3, N'Ếch Nướng Gỗ Sồi', N'Phần', 249000, null),
(3, N'Hàu Hạ Long Nướng Phô Mai (1 con)', N'Con', 39000, null),
(3, N'Sò Điệp Xốt Trứng Muối (1 con)', N'Con', 45000, null),
(3, N'Bào Ngư Xốt Trứng Muối', N'Con', 69000, null),
(3, N'Cà Tím Nướng Mỡ Hành', N'Đĩa', 69000, null),
(3, N'Ba Chỉ Heo Nướng Mật Ong', N'Đĩa', 149000, null),
(3, N'Ba Chỉ Bò Nướng Xốt Cay', N'Đĩa', 179000, null),
(3, N'Dẻ Sườn Bò Mỹ Nướng Xốt Cay', N'Đĩa', 249000, null),
(3, N'Bò Mỹ Nướng Ống Tre', N'Đĩa', 249000, null),
(3, N'Bò Mỹ Xì Xèo', N'Đĩa', 269000, null),
(4, N'Bánh Mè Chả Đùm', N'Phần', 109000, null),
(5, N'Hàu Hạ Long Ăn Sống (1 con)', N'Con', 35000, null),
(6, N'Combo Hải Sản', N'Phần', 599000, null),
(6, N'Combo Thịt Nướng', N'Phần', 699000, null),
(6, N'Combo Đặc Biệt', N'Phần', 899000, null),
(6, N'Đại Tiệc Thịt Nướng Budweiser', N'Phần', 999000, null),
(6, N'Đảo Hải Sản Hoegaarden', N'Phần', 999000, null),
(7, N'Cơm Chiên Thơm Chay', N'Đĩa', 99000, null),
(7, N'Cơm Chiên Cá Mặn', N'Tô', 129000, null),
(7, N'Cơm Chiên Lá Cẩm Hải Sản', N'Tô', 199000, null),
(8, N'Miến Trộn Chua Cay', N'Đĩa', 269000, null),
(9, N'Khoai Tây Phô Mai', N'Đĩa', 79000, null),
(9, N'Khoai Mì Viên Chiên', N'Đĩa', 79000, null),
(9, N'Xôi Chiên Phồng', N'Đĩa', 69000, null),
(9, N'Khoai Lang Tím Nghiền', N'Đĩa', 69000, null),
(9, N'Khoai Mì Nghiền', N'Đĩa', 69000, null),
(10, N'Cam', N'Đĩa', 69000, null),
(10, N'Xoài', N'Đĩa', 49000, null),
(10, N'Nho', N'Đĩa', 59000, null),
(10, N'Bưởi', N'Đĩa', 49000, null),
(10, N'Dưa hấu', N'Đĩa', 59000, null),
(11, N'Budweiser Draught - Ly 330ml', N'Ly', 39000, null),
(11, N'Budweiser Draught - Tháp 3000ml', N'Tháp', 340000, null),
(11, N'Hoegaarden Draught - Ly 250ml', N'Ly', 49000, null),
(11, N'Hoegaarden Draught - Tháp 3000ml', N'Tháp', 450000, null),
(11, N'Budweiser', N'Chai', 29000, null),
(11, N'Corona 250ml', N'Chai', 39000, null),
(11, N'Hoegaarden White', N'Chai', 69000, null),
(11, N'Tiger Lon', N'Lon', 29000, null),
(11, N'Tiger Crytal Lon', N'Lon', 30000, null),
(11, N'Heineken Silver Lon', N'Lon', 35000, null),
(12, N'La Vie Still 450ml', N'Chai', 29000, null),
(12, N'Sting Dâu Lon', N'Lon', 25000, null),
(12, N'7Up Lon', N'Lon', 25000, null),
(12, N'Pepsi Lon', N'Lon', 25000, null),
(12, N'CocaCola Lon', N'Lon', 25000, null)
GO
INSERT INTO NGUYENLIEU(TENNGUYENLIEU, SOLUONG, DAXOA) VALUES
(N'Gà', 1, 0),
(N'Vịt',1,0),
(N'Cá hồi', 1, 0),
(N'Cá thu', 1, 0),
(N'Ếch', 1, 0),
(N'Hào', 1, 0),
(N'Sò điệp', 1, 0),
(N'Bào ngư', 1, 0),
(N'Cà tím', 1, 0),
(N'Rau rừng', 1, 0),
(N'Đường', 1, 0),
(N'Muối', 1, 0),
(N'Bột ngọt', 1, 0),
(N'Bột nêm', 1, 0),
(N'Ớt', 1, 0),
(N'Tỏi', 1, 0),
(N'Tiêu', 1, 0),
(N'Nước mắm', 1, 0),
(N'Giấm', 1, 0)
GO

--Xử lý--

--Đăng nhập
CREATE PROC USP_Login 
    @userName nvarchar(100),
    @passWord nvarchar(100)
AS
BEGIN
    SELECT *
    FROM TAIKHOAN
    WHERE BINARY_CHECKSUM(TENDANGNHAP) = BINARY_CHECKSUM(@userName) AND BINARY_CHECKSUM(MATKHAU) = BINARY_CHECKSUM(@passWord);
END
GO
--Lấy tài khoản bởi tên đăng nhập
CREATE PROC USP_GetAccountByUserName
    @userName nvarchar(100)
AS
    BEGIN
        SELECT * FROM TAIKHOAN WHERE @userName = TENDANGNHAP
    END
GO

--Thay đổi mật khẩu
CREATE PROC USP_ChangePassword
    @userName VARCHAR(50),
    @password NVARCHAR(100),
    @newPassword NVARCHAR(100)
AS
BEGIN
    DECLARE @isRightPass INT = 0

    SELECT @isRightPass = COUNT(*) FROM TAIKHOAN WHERE TENDANGNHAP = @userName AND MATKHAU = @password

    IF(@isRightPass = 1)
        BEGIN
            UPDATE TAIKHOAN SET MATKHAU = @newPassword WHERE TENDANGNHAP = @userName
        END
END
GO
--Lấy danh sách tài khoản
CREATE PROC USP_GetListAccount
AS
    BEGIN
        SELECT * FROM TAIKHOAN
    END
GO
--Lấy danh sách mã nhân viên
CREATE PROC USP_GetListEmployeeByID
AS
    BEGIN 
        SELECT NHANVIEN.MANHANVIEN FROM NHANVIEN
    END
GO
--Lấy danh sách tên nhân viên
CREATE PROC USP_GetListNameEmployee
AS
    BEGIN 
        SELECT TAIKHOAN.TENHIENTHI FROM TAIKHOAN
    END
GO

--Lấy danh sách trạng thái tài khoản
CREATE PROC USP_GetListStatus
AS
    BEGIN 
        SELECT TAIKHOAN.TRANGTHAI FROM TAIKHOAN
    END
GO
--Cài lại mật khẩu
CREATE PROC USP_ResetPassWord
    @userName VARCHAR(50)
AS
    BEGIN
        UPDATE TAIKHOAN
        SET MATKHAU = '1'
        WHERE TENDANGNHAP = @userName
    END
GO
--Thêm tài khoản
CREATE PROC USP_InsertAccount
    @userName VARCHAR(50),
    @staffid VARCHAR(10),
    @displayName NVARCHAR(50),
    @status NVARCHAR(50)
AS
    BEGIN
        INSERT INTO TAIKHOAN(TENDANGNHAP, MANHANVIEN, TENHIENTHI, MATKHAU, TRANGTHAI) VALUES
        (@userName, @staffid, @displayName, '1', @status)
    END
GO
--Lấy tài khoản bởi mã nhân viên
CREATE PROC USP_GetAccountByStaffID
    @staffid VARCHAR(10)
AS
    BEGIN
        SELECT * FROM TAIKHOAN WHERE MANHANVIEN = @staffid
    END
GO
--Xóa tài khoản
CREATE PROC USP_DeleteAccount
    @userName VARCHAR(50)
AS
    BEGIN
        DELETE TAIKHOAN WHERE TENDANGNHAP = @userName
    END
GO
--Sửa tài khoản
CREATE PROC USP_UpdateAccount
    @status NVARCHAR(50),
    @userName VARCHAR(10)
AS
    BEGIN
        UPDATE TAIKHOAN
        SET TRANGTHAI = @status
        WHERE TENDANGNHAP = @userName
    END
GO

--Lấy danh sách nhân viên
CREATE PROC USP_GetListEmployee
AS
    BEGIN
        SELECT * FROM NHANVIEN WHERE DAXOA = 0
    END
GO

--Lấy nhân viên bởi mã nhân viên
CREATE PROC USP_GetEmployeeByStaffID
    @staffid VARCHAR(10)
AS
    BEGIN
        SELECT * FROM NHANVIEN WHERE MANHANVIEN = @staffid
    END
GO

-- Thêm nhân viên
CREATE PROC USP_InsertEmployee
    @staffid VARCHAR(10),
    @name NVARCHAR(100),
    @sex NVARCHAR(4),
    @dateofbirth DATE,
    @address NVARCHAR(255),
    @phone VARCHAR(12),
    @dateofwork DATE,
    @basicsalary DECIMAL(12, 2)
AS
    BEGIN
        INSERT INTO NHANVIEN(MANHANVIEN, HOTEN, PHAI, NGAYSINH, DIACHI, SDT, NGAYVAOLAM, LUONGCOBAN) VALUES
        (@staffid, @name, @sex, @dateofbirth, @address, @phone, @dateofwork, @basicsalary)
    END
GO

--Xóa nhân viên
CREATE PROC USP_DeleteEmployee
    @staffid VARCHAR(10)
AS
    BEGIN
        UPDATE NHANVIEN
        SET DAXOA = 1
        WHERE MANHANVIEN = @staffid
    END
GO

--Sửa nhân viên
CREATE PROC USP_UpdateEmployee
    @staffid VARCHAR(10),
    @name NVARCHAR(100),
    @sex NVARCHAR(4),
    @dateofbirth DATE,
    @address NVARCHAR(255),
    @phone VARCHAR(12),
    @dateofwork DATE,
    @basicsalary DECIMAL(12, 2)
AS
    BEGIN
        UPDATE NHANVIEN
        SET HOTEN = @name, PHAI = @sex, NGAYSINH = @dateofbirth, DIACHI = @address, SDT = @phone, NGAYVAOLAM = @dateofwork, LUONGCOBAN = @basicsalary
        WHERE MANHANVIEN = @staffid
    END
GO

-- Function Loại bỏ dấu tiếng Việt, giữ nguyên chữ in hoa và các ký tự đặc biệt
CREATE FUNCTION [dbo].[fuConvertToUnsign1]
(
 @strInput NVARCHAR(4000)
)
RETURNS NVARCHAR(4000)
AS
BEGIN 
 IF @strInput IS NULL RETURN @strInput
 IF @strInput = '' RETURN @strInput
 DECLARE @RT NVARCHAR(4000)
 DECLARE @SIGN_CHARS NCHAR(136)
 DECLARE @UNSIGN_CHARS NCHAR (136)
 SET @SIGN_CHARS = N'ăâđêôơưàảãạáằẳẵặắầẩẫậấèẻẽẹéềểễệế
 ìỉĩịíòỏõọóồổỗộốờởỡợớùủũụúừửữựứỳỷỹỵý
 ĂÂĐÊÔƠƯÀẢÃẠÁẰẲẴẶẮẦẨẪẬẤÈẺẼẸÉỀỂỄỆẾÌỈĨỊÍ
 ÒỎÕỌÓỒỔỖỘỐỜỞỠỢỚÙỦŨỤÚỪỬỮỰỨỲỶỸỴÝ'
 +NCHAR(272)+ NCHAR(208)
 SET @UNSIGN_CHARS = N'aadeoouaaaaaaaaaaaaaaaeeeeeeeeee
 iiiiiooooooooooooooouuuuuuuuuuyyyyy
 AADEOOUAAAAAAAAAAAAAAAEEEEEEEEEEIIIII
 OOOOOOOOOOOOOOOUUUUUUUUUUYYYYYDD'
 DECLARE @COUNTER int
 DECLARE @COUNTER1 int
 SET @COUNTER = 1
 WHILE (@COUNTER <=LEN(@strInput))
 BEGIN 
 SET @COUNTER1 = 1
 WHILE (@COUNTER1 <=LEN(@SIGN_CHARS)+1)
 BEGIN
 IF UNICODE(SUBSTRING(@SIGN_CHARS, @COUNTER1,1))
 = UNICODE(SUBSTRING(@strInput,@COUNTER ,1) )
 BEGIN 
 IF @COUNTER=1
 SET @strInput = SUBSTRING(@UNSIGN_CHARS, @COUNTER1,1)
 + SUBSTRING(@strInput, @COUNTER+1,LEN(@strInput)-1) 
 ELSE
 SET @strInput = SUBSTRING(@strInput, 1, @COUNTER-1)
 +SUBSTRING(@UNSIGN_CHARS, @COUNTER1,1)
 + SUBSTRING(@strInput, @COUNTER+1,LEN(@strInput)- @COUNTER)
 BREAK
 END
 SET @COUNTER1 = @COUNTER1 +1
 END
 SET @COUNTER = @COUNTER +1
 END
 SET @strInput = replace(@strInput,' ','-')
 RETURN @strInput
END
GO

--Tìm nhân viên theo tên
CREATE PROC USP_SearchEmployeeByName
    @name NVARCHAR(100)
AS
    BEGIN
        SELECT *
        FROM NHANVIEN
        WHERE [dbo].[fuConvertToUnsign1](HOTEN) LIKE N'%' + [dbo].[fuConvertToUnsign1](@name) + N'%' AND DAXOA = 0;
    END
GO

--Tìm nhân viên theo lương
CREATE PROC USP_SearchEmployeeBySalary
     @basicsalary DECIMAL(12, 2)
AS
    BEGIN
        SELECT * FROM NHANVIEN WHERE LUONGCOBAN = @basicsalary AND DAXOA = 0
    END
GO

--Tìm nhân viên theo giới tính
CREATE PROC USP_SearchEmployeeBySex
   @sex NVARCHAR(4)
AS
    BEGIN
        SELECT * FROM NHANVIEN WHERE PHAI = @sex AND DAXOA = 0
    END
GO

--Món ăn
--Lấy mã theo tên loại món được chọn
CREATE PROCEDURE USP_GetMaLoaiMonAnByTenLoai
    @tenloai NVARCHAR(50)
AS
BEGIN
    SELECT MALOAIMONAN 
    FROM LOAIMONAN 
    WHERE TENLOAIMONAN = @tenloai;
END
GO

--Xóa loại món ăn
CREATE PROC USP_XoaLoaiMonAn
    @maLoai INT
AS
    BEGIN
        UPDATE LOAIMONAN
        SET DAXOA = 1
        WHERE MALOAIMONAN = @maLoai
    END
GO

--Thêm loại món ăn
CREATE PROC USP_ThemLoaiMonAn
    @tenLoai NVARCHAR(50)
AS
    BEGIN
        INSERT INTO LOAIMONAN(TENLOAIMONAN) VALUES
        (@tenLoai)
    END
GO

--Sửa loại món ăn
CREATE PROC USP_SuaLoaiMonAn
    @maLoai INT,
    @tenLoai NVARCHAR(50)
AS
    BEGIN
        UPDATE LOAIMONAN 
        SET TENLOAIMONAN = @tenLoai WHERE MALOAIMONAN = @maLoai
    END
GO

--Tìm nhân viên theo tên
CREATE PROC USP_SearchLMAByName
    @name NVARCHAR(100)
AS
    BEGIN
        SELECT *
        FROM LOAIMONAN
        WHERE [dbo].[fuConvertToUnsign1](TENLOAIMONAN) LIKE N'%' + [dbo].[fuConvertToUnsign1](@name) + N'%' AND DAXOA = 0;
    END
GO

CREATE PROCEDURE GetMonAnByLoaiMonAn
    @tenLoaiMonAn NVARCHAR(255)
    AS
        BEGIN
            SELECT MONAN.*
            FROM MONAN
            INNER JOIN LOAIMONAN ON MONAN.MALOAIMONAN = LOAIMONAN.MALOAIMONAN
            WHERE MONAN.DAXOA = 0 AND LOAIMONAN.TENLOAIMONAN = @tenLoaiMonAn;
        END;
GO