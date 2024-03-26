/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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

    private MonAnDAO() {
    }
}
