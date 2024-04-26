/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
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
public class NhaCungCapDAO {

    private static NhaCungCapDAO instance;

    public static NhaCungCapDAO getInstance() {
        if (instance == null) {
            instance = new NhaCungCapDAO();
        }
        return instance;
    }

    private NhaCungCapDAO() {
    }

    public List<NhaCungCapDTO> layDSNCC() throws SQLException {
        List<NhaCungCapDTO> listNCC = new ArrayList<>();

        String query = "USP_GetListNhaCC";
        DataProvider dataProvider = DataProvider.getInstance();
        try (Connection con = dataProvider.getConnection(); PreparedStatement pstmt = con.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NhaCungCapDTO nv = new NhaCungCapDTO();
                nv.setMancc(rs.getInt("MANCC"));
                nv.setTenncc(rs.getString("TENNC"));
                nv.setDiachi(rs.getString("DIACHI"));
                nv.setEmail(rs.getString("EMAIL"));
                nv.setSdt(rs.getString("SDT"));

                listNCC.add(nv);
            }
        }
        return listNCC;
    }

    public int layMaNCCTheoTen(String tenNCC) {
        int maNCC = -1;
        try {
            String query = "SELECT MANCC FROM NHACUNGCAP WHERE TENNC = ?";
            DataProvider dataProvider = DataProvider.getInstance();
            try (Connection con = dataProvider.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, tenNCC);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        maNCC = rs.getInt("MANCC");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maNCC;
    }

}
