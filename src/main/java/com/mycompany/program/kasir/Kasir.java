package com.mycompany.program.kasir;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import com.mycompany.program.kasir.config.connect;

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
    }
    
}
