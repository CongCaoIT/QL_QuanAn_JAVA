/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author TAOPRO
 */
public class PhieuNhapDAO {

    private static PhieuNhapDAO instance;

    public static PhieuNhapDAO getInstance() {
        if (instance == null) {
            instance = new PhieuNhapDAO();
        }
        return instance;
    }

    public PhieuNhapDAO() {
    }

    // Phương thức để tạo phiếu nhập mới trong bảng PHIEUNHAP
    public int taoPhieuNhap(int maNCC, Date ngayNhap) {
        int maPhieuNhap = -1;
        try {
            String query = "INSERT INTO PHIEUNHAP (MANCC, NGAYNHAP, DAXOA) VALUES (?, ?, 0)";
            DataProvider dataProvider = DataProvider.getInstance();
            try (Connection con = dataProvider.getConnection(); PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, maNCC);
                pstmt.setDate(2, new java.sql.Date(ngayNhap.getTime()));
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    maPhieuNhap = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maPhieuNhap;
    }
}
