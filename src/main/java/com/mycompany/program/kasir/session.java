/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.program.kasir;

/**
 *
 * @author tigfi
 */
public class session {

    public static String username;
    public static int id_user, id_level;

    public void setSession(int id_user, String username, int id_level) {
        this.id_level = id_level;
        this.username = username;
        this.id_user = id_user;
        System.out.println(this.id_level);
        System.out.println(this.username);


    }

    public String getSession() {
        return "ID User: " + id_user + ", Username: " + username + ", ID Level: " + id_level;
    }

    public int getIdUser() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public int getIdLevel() {
        return id_level;
    }

}
