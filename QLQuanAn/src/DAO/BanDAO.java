/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author TAOPRO
 */
public class BanDAO {

    private static BanDAO instance;

    public static BanDAO getInstance() {
        if (instance == null) {
            instance = new BanDAO();
        }
        return instance;
    }

    private BanDAO() {
    }
}
