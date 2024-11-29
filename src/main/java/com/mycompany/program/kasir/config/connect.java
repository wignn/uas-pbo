package com.mycompany.program.kasir.config;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tigfi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.JOptionPane;

public class connect {

//    private static final Dotenv dotenv = Dotenv.load();
//    private String Url = dotenv.get("DATABASE_URL");
    
    /*
        You can use dotenv or a local database. The SQL file is added in the 'sql' folder, and you can import it into MySQL
    */
    private Connection con;

    public void db() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restoran" , "root", "");
            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }
}
