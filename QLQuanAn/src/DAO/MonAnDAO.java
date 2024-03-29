/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.MonAnDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author TAOPRO
 */
public class MonAnDAO {

    private static MonAnDAO instance;

    public static MonAnDAO getInstance() {
        if (instance == null) {
            instance = new MonAnDAO();
        }
        return instance;
    }

    public MonAnDAO() {
    }

    public ArrayList<MonAnDTO> layDSMon() {
        ArrayList<MonAnDTO> list = new ArrayList<>();
        String query = "SELECT * FROM MONAN WHERE DAXOA = 0";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MonAnDTO k = new MonAnDTO(rs);
                k.setMamonan(rs.getInt("MAMONAN"));
                k.setMaloaimonan(rs.getInt("MALOAIMONAN"));
                k.setTenmonan(rs.getString("TENMONAN"));
                k.setDvt(rs.getString("DVT"));
                k.setDongia(rs.getDouble("DONGIA"));
                k.setHinhanh(rs.getString("HINHANH"));
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

    public ArrayList<MonAnDTO> layDSMonDcTK(String tenMon) {
        ArrayList<MonAnDTO> list = new ArrayList<>();
        String query = "SELECT MONAN.* FROM MONAN, LOAIMONAN WHERE MONAN.MALOAIMONAN = LOAIMONAN.MALOAIMONAN AND MONAN.DAXOA = 0 AND LOAIMONAN.TENLOAIMONAN = ?";
        try {
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, tenMon); // Gán giá trị cho tham số tenMon
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MonAnDTO k = new MonAnDTO(rs);
                k.setMamonan(rs.getInt("MAMONAN"));
                k.setMaloaimonan(rs.getInt("MALOAIMONAN"));
                k.setTenmonan(rs.getString("TENMONAN"));
                k.setDvt(rs.getString("DVT"));
                k.setDongia(rs.getDouble("DONGIA"));
                k.setHinhanh(rs.getString("HINHANH"));
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

    public int layMaTheoTenDcChon(String tenLoai) {
        int maLoai = -1;
        try {
            String query = "EXEC USP_GetMaLoaiMonAnByTenLoai ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, tenLoai);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                maLoai = rs.getInt(1);
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return maLoai;
    }

    public String layTenLoaiTheoMaMon(int maMon) {
        try {
            String query = "EXEC USP_LayTenLoaiTheoMaMon ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, maMon);
            ResultSet rs = pstmt.executeQuery(); // Thực hiện truy vấn SELECT

            String tenLoai = null;
            if (rs.next()) {
                tenLoai = rs.getString("TENLOAIMONAN"); // Lấy giá trị tên loại từ ResultSet
            }

            rs.close();
            pstmt.close();
            con.close();

            return tenLoai; // Trả về tên loại
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean xoaMonAn(int maLoai) {
        try {
            String query = "EXEC USP_XoaMonAn ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, maLoai);
            int result = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themMonAn(int maLoai, String tenMon, String unit, double gia, String images) {
        try {
            String query = "EXEC USP_ThemMonAn ?, ?, ?, ?, ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, maLoai);
            pstmt.setString(2, tenMon);
            pstmt.setString(3, unit);
            pstmt.setDouble(4, gia);
            pstmt.setString(5, images);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            con.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean capNhatMonAn(int maLoai, int maMon, String tenMon, String unit, double gia, String images) {
        try {
            String query = "EXEC USP_CapNhatMonAn ?, ?, ?, ?, ?, ?";
            Connection con = DataProvider.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, maLoai);
            pstmt.setInt(2, maMon);
            pstmt.setString(3, tenMon);
            pstmt.setString(4, unit);
            pstmt.setDouble(5, gia);
            pstmt.setString(6, images);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            con.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public ArrayList<String> LayCTCongThucTheoMaMon(int maMon) {
//        ArrayList<String> chiTietCongThuc = new ArrayList<>();
//        try {
//            String query = "EXEC USP_LayCTCongThucTheoMaMon ?";
//            Connection con = DataProvider.getInstance().getConnection();
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setInt(1, maMon);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                String tenMonAn = rs.getString("TENMONAN");
//                String tenNguyenLieu = rs.getString("TENNGUYENLIEU");
//                int soLuong = rs.getInt("SOLUONG");
//                String chiTiet = tenMonAn + ": " + tenNguyenLieu + " - " + soLuong;
//                chiTietCongThuc.add(chiTiet);
//            }
//
//            rs.close();
//            pstmt.close();
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return chiTietCongThuc;
//    }
}
