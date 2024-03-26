/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author TAOPRO
 */
public class LoaiMonAnDAO {
     private static LoaiMonAnDAO instance;

    public static LoaiMonAnDAO getInstance() {
        if (instance == null) {
            instance = new LoaiMonAnDAO();
        }
        return instance;
    }

    private LoaiMonAnDAO() {
    }
}
