/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author guilu
 */
public class Database {
    private static final String URL = "jdbc:sqlite:database/estoque.db";
    private static Connection con = null;

    private Database() {
    }
    private static void createConnection() {
        try {
            con = DriverManager.getConnection(URL);
        }
        catch (SQLException x) {
            System.err.println(x);
        }
    }
    public static Connection getConnection() {
        if(con == null)
        {
            createConnection();
        }
        return con;
    }
    
    public static void closeConnection() {
        try{
            con.close();
            con = null;
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
}