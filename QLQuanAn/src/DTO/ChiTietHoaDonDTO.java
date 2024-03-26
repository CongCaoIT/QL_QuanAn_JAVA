/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.*;

/**
 *
 * @author TAOPRO
 */
public class ChiTietHoaDonDTO {

    private int macthd;
    private int mamonan;
    private int mahoadon;
    private int soluong;

    public ChiTietHoaDonDTO(int macthd, int mamonan, int mahoadon, int soluong) {
        this.macthd = macthd;
        this.mamonan = mamonan;
        this.mahoadon = mahoadon;
        this.soluong = soluong;
    }

    public ChiTietHoaDonDTO(ResultSet rs) throws SQLException {
        this.macthd = rs.getInt("MACHITIETHD");
        this.mamonan = rs.getInt("MAMONAN");
        this.mahoadon = rs.getInt("MAHOADON");
        this.soluong = rs.getInt("SOLUONG");
    }

    public int getMacthd() {
        return macthd;
    }

    public void setMacthd(int macthd) {
        this.macthd = macthd;
    }

    public int getMamonan() {
        return mamonan;
    }

    public void setMamonan(int mamonan) {
        this.mamonan = mamonan;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
