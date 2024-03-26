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
public class NguyenLieuDTO {
    private int manguyenlieu;
    private String tennguyenlieu;
    private int soluong;
    private boolean daxoa;

    public NguyenLieuDTO(int manguyenlieu, String tennguyenlieu, int soluong, boolean daxoa) {
        this.manguyenlieu = manguyenlieu;
        this.tennguyenlieu = tennguyenlieu;
        this.soluong = soluong;
        this.daxoa = daxoa;
    }

    public NguyenLieuDTO(ResultSet rs) throws SQLException{
        this.manguyenlieu = rs.getInt("MANGUYENLIEU");
        this.tennguyenlieu = rs.getString("TENNGUYENLIEU");
        this.soluong = rs.getInt("SOLUONG");
        this.daxoa = rs.getBoolean("DAXOA");
    }
    public int getManguyenlieu() {
        return manguyenlieu;
    }

    public void setManguyenlieu(int manguyenlieu) {
        this.manguyenlieu = manguyenlieu;
    }

    public String getTennguyenlieu() {
        return tennguyenlieu;
    }

    public void setTennguyenlieu(String tennguyenlieu) {
        this.tennguyenlieu = tennguyenlieu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public boolean isDaxoa() {
        return daxoa;
    }

    public void setDaxoa(boolean daxoa) {
        this.daxoa = daxoa;
    }
    
}
