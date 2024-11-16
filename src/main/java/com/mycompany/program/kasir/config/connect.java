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
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.JOptionPane;

public class connect {

    private static final Dotenv dotenv = Dotenv.load();
    private String Url = dotenv.get("DATABASE_URL");
    private Connection con;

    public void db() {
        try {
            con = DriverManager.getConnection(Url);
            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public Connection getCon(){
        return con;
    }
}
