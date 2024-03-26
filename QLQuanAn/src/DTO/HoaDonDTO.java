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
public class HoaDonDTO {
    private int mahoadon;
    private int maban;
    private String manhanvien;
    private Date ngayvao;
    private Date ngayra;
    private int giamgia;
    private double thanhtien;

    public HoaDonDTO(int mahoadon, int maban, String manhanvien, Date ngayvao, Date ngayra, int giamgia, double thanhtien) {
        this.mahoadon = mahoadon;
        this.maban = maban;
        this.manhanvien = manhanvien;
        this.ngayvao = ngayvao;
        this.ngayra = ngayra;
        this.giamgia = giamgia;
        this.thanhtien = thanhtien;
    }
    
    public HoaDonDTO(ResultSet rs) throws SQLException{
        this.mahoadon = rs.getInt("MAHOADON");
        this.maban = rs.getInt("MABAN");
        this.manhanvien = rs.getString("MANHANVIEN");
        this.ngayvao = rs.getDate("NGAYVAO");
        this.ngayra = rs.getDate("NGAYRA");
        this.giamgia = rs.getInt("GIAMGIA");
        this.thanhtien = rs.getDouble("THANHTIEN");
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public Date getNgayvao() {
        return ngayvao;
    }

    public void setNgayvao(Date ngayvao) {
        this.ngayvao = ngayvao;
    }

    public Date getNgayra() {
        return ngayra;
    }

    public void setNgayra(Date ngayra) {
        this.ngayra = ngayra;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
    
}
