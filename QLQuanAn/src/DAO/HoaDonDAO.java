/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDonDTO;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author TAOPRO
 */
public class HoaDonDAO {

    private static HoaDonDAO instance;

    public static HoaDonDAO getInstance() {
        if (instance == null) {
            instance = new HoaDonDAO();
        }
        return instance;
    }

    private HoaDonDAO() {
    }

    public List<HoaDonDTO> layTKDSHD(java.util.Date ngaybatdau, java.util.Date ngayketthuc) throws SQLException {
        List<HoaDonDTO> listHD = new ArrayList<>();
        String query = "EXEC USP_GetListBillByDateOut ?, ?";
        try (Connection con = DataProvider.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            // Chuyển đổi ngày bắt đầu và ngày kết thúc sang java.sql.Date
            java.sql.Date startDate = new java.sql.Date(ngaybatdau.getTime());
            java.sql.Date endDate = new java.sql.Date(ngayketthuc.getTime());

            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setMahoadon(rs.getInt("MAHOADON"));
                    hd.setMaban(rs.getInt("MABAN"));
                    hd.setManhanvien(rs.getString("MANHANVIEN"));
                    hd.setNgayvao(rs.getDate("NGAYVAO"));
                    hd.setNgayra(rs.getDate("NGAYRA"));
                    hd.setGiamgia(rs.getInt("GIAMGIA"));
                    hd.setThanhtien(rs.getDouble("THANHTIEN"));

                    listHD.add(hd);
                }
            }
        }
        return listHD;
    }

    public List<HoaDonDTO> layTKDSHDtheoTenNV(java.util.Date dateNgayBatDau, java.util.Date dateNgayKetThuc, String manv) throws SQLException {
        List<HoaDonDTO> listHD = new ArrayList<>();
        String query = "EXEC USP_GetListBillByDateOutAndStaff ?, ?, ?";
        try (Connection con = DataProvider.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            // Chuyển đổi ngày bắt đầu và ngày kết thúc sang java.sql.Date
            java.sql.Date startDate = new java.sql.Date(dateNgayBatDau.getTime());
            java.sql.Date endDate = new java.sql.Date(dateNgayKetThuc.getTime());

            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            pstmt.setString(3, manv);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setMahoadon(rs.getInt("MAHOADON"));
                    hd.setMaban(rs.getInt("MABAN"));
                    hd.setManhanvien(rs.getString("MANHANVIEN"));
                    hd.setNgayvao(rs.getDate("NGAYVAO"));
                    hd.setNgayra(rs.getDate("NGAYRA"));
                    hd.setGiamgia(rs.getInt("GIAMGIA"));
                    hd.setThanhtien(rs.getDouble("THANHTIEN"));

                    listHD.add(hd);
                }
            }
        }
        return listHD;
    }
}
