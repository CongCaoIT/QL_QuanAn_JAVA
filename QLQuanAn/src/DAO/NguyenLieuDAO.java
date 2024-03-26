/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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

    private NguyenLieuDAO() {
    }
}
