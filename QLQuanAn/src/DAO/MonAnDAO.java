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
    
    public ArrayList<MonAnDTO> layDSMonDcTK(String tenMon) {
        ArrayList<MonAnDTO> list = new ArrayList<>();
        String query = "EXEC GetMonAnByLoaiMonAn ?";
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
}
