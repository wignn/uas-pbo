package com.mycompany.program.kasir;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import com.mycompany.program.kasir.config.connect;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author tigfi
 */
public class Kasir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var stm = new connect();
        stm.db();
        String password = "password123";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10)); 
        System.out.println("Password asli: " + password);
        System.out.println("Hash bcrypt: " + hashedPassword);
        
        
        boolean isPasswordCorrect = BCrypt.checkpw(password, hashedPassword);
        System.out.println("Password cocok: " + isPasswordCorrect);
    }

}
