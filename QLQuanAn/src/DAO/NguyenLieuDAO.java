/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NguyenLieuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TAOPRO
 */
public class NguyenLieuDAO {
     private static NguyenLieuDAO instance;

    public static NguyenLieuDAO getInstance() {
        if (instance == null) {
            instance = new NguyenLieuDAO();
        }
        return instance;
    }

    public NguyenLieuDAO() {
    }
    
    public ArrayList<NguyenLieuDTO> layDSNLieu() {
        ArrayList<NguyenLieuDTO> list = new ArrayList<>();
        String query = "SELECT * FROM NGUYENLIEU WHERE DAXOA = 0";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                NguyenLieuDTO k = new NguyenLieuDTO(rs);
                k.setManguyenlieu(rs.getInt("MANGUYENLIEU"));
                k.setTennguyenlieu(rs.getString("TENNGUYENLIEU"));
                k.setSoluong(rs.getInt("SOLUONG"));
                k.setDaxoa(rs.getBoolean("DAXOA"));
                list.add(k);
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean xoaNgLieu(int nglieu) {
        try {
            String query = "EXEC USP_XoaNgLieu ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, nglieu);
            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean themNgLieu(String TENNL) {
        try {
            String query = "EXEC USP_ThemNgLieu ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, TENNL);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhatNgLieu(int manl, String TENNL) {
        try {
            String query = "EXEC USP_CapNhatNgLieu ?, ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, manl);
            pstmt.setString(2, TENNL);
            int rowsAffected = pstmt.executeUpdate(); // Thực hiện truy vấn UPDATE

            pstmt.close();
            con.close();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
