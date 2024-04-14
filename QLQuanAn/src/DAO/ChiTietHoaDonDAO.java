/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
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
public class ChiTietHoaDonDAO {

    private static ChiTietHoaDonDAO instance;

    public static ChiTietHoaDonDAO getInstance() {
        if (instance == null) {
            instance = new ChiTietHoaDonDAO();
        }
        return instance;
    }

    private ChiTietHoaDonDAO() {
    }

    public List<ChiTietHoaDonDTO> layTKDSCTHD(int mahd) throws SQLException {
        List<ChiTietHoaDonDTO> listCTHD = new ArrayList<>();
        String query = "EXEC USP_GetBillInfoByBill ?";
        try (Connection con = DataProvider.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, mahd);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
                    cthd.setMacthd(rs.getInt("MACHITIETHD"));
                    cthd.setMamonan(rs.getInt("MAMONAN"));
                    cthd.setMahoadon(rs.getInt("MAHOADON"));
                    cthd.setSoluong(rs.getInt("SOLUONG"));

                    listCTHD.add(cthd);
                }
            }
        }
        return listCTHD;
    }
}
