/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TAOPRO
 */
public class TaiKhoanDAO {

    private static TaiKhoanDAO instance;

    public static TaiKhoanDAO getInstance() {
        if (instance == null) {
            instance = new TaiKhoanDAO();
        }
        return instance;
    }

    private TaiKhoanDAO() {
    }

    public boolean Login(String tendangnhap, String matkhau) {
        String query = "SELECT * FROM TAIKHOAN WHERE TAIKHOAN.TENDANGNHAP = ? AND TAIKHOAN.MATKHAU = ?";
        Object[] parameters = {tendangnhap, matkhau};
        try {
            ResultSet result = DataProvider.getInstance().executeQuery(query, parameters);
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public TaiKhoanDTO layTKTheoTen(String tendangnhap) {
        String query = "SELECT * FROM TAIKHOAN WHERE TENDANGNHAP = ?";
        Object[] paramaters = {tendangnhap};

        try {
            ResultSet result = DataProvider.getInstance().executeQuery(query, paramaters);
            if (result.next()) {
                return new TaiKhoanDTO(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean ThayDoiMK(String tendangnhap, String matkhaucu, String matkhaumoi){
         String query = "{call USP_ChangePassword(?, ?, ?)}";
        try (Connection conn = DataProvider.getInstance().getConnection(); CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setString(1, tendangnhap);
            stmt.setString(2, matkhaucu);
            stmt.setString(3, matkhaumoi);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
