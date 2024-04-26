/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import com.sun.jdi.connect.spi.Connection;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author TAOPRO
 */
public class ChiTietPhieuNhapDAO {

    private static ChiTietPhieuNhapDAO instance;

    public static ChiTietPhieuNhapDAO getInstance() {
        if (instance == null) {
            instance = new ChiTietPhieuNhapDAO();
        }
        return instance;
    }

    private ChiTietPhieuNhapDAO() {
    }

    // Phương thức để lưu thông tin chi tiết phiếu nhập vào bảng CHITIETPHIEUNHAP
    public void luuChiTietPhieuNhap(int maPhieuNhap, List<ChiTietPhieuNhapDTO> danhSachChiTiet) {
        String query = "INSERT INTO CHITIETPHIEUNHAP (MAPN, MANGUYENLIEU, DONGIANHAP, SOLUONGNHAP) VALUES (?, ?, ?, ?)";
        DataProvider dataProvider = DataProvider.getInstance();
        try (java.sql.Connection con = dataProvider.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            for (ChiTietPhieuNhapDTO chiTiet : danhSachChiTiet) {
                pstmt.setInt(1, maPhieuNhap);
                pstmt.setInt(2, chiTiet.getManguyenlieu());
                pstmt.setDouble(3, chiTiet.getDongianhap());
                pstmt.setInt(4, chiTiet.getSoluongnhap());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
