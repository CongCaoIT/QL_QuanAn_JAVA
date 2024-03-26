/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author TAOPRO
 */
public class CongThucDAO {
     private static CongThucDAO instance;

    public static CongThucDAO getInstance() {
        if (instance == null) {
            instance = new CongThucDAO();
        }
        return instance;
    }

    private CongThucDAO() {
    }
}
