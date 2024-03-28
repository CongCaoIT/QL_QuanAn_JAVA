/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TAOPRO
 */
public class NhanVienDAO {

    private static NhanVienDAO instance;

    public static NhanVienDAO getInstance() {
        if (instance == null) {
            instance = new NhanVienDAO();
        }
        return instance;
    }

    private NhanVienDAO() {
    }

    public List<NhanVienDTO> layDSNV() throws SQLException {
        List<NhanVienDTO> listNV = new ArrayList<>();

        String query = "USP_GetListEmployee";
        DataProvider dataProvider = DataProvider.getInstance();
        try (Connection con = dataProvider.getConnection(); PreparedStatement pstmt = con.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setManhanvien(rs.getString("MANHANVIEN"));
                nv.setHoten(rs.getString("HOTEN"));
                nv.setPhai(rs.getString("PHAI"));
                nv.setNgaysinh(rs.getDate("NGAYSINH"));
                nv.setDiachi(rs.getString("DIACHI"));
                nv.setSdt(rs.getString("SDT"));
                nv.setNgayvaolam(rs.getDate("NGAYVAOLAM"));
                nv.setLuongcoban(rs.getDouble("LUONGCOBAN"));
                nv.setDaxoa(rs.getBoolean("DAXOA"));
                listNV.add(nv);
            }
        }
        return listNV;
    }
}
