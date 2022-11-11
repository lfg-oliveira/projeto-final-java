/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.model;

/**
 *
 * @author guilu
 */
public class Constraint {
    public static final String PRIMARY_KEY = "PRIMARY KEY ";
    public static final String NOT_NULL = "NOT NULL ";
    public static final String UNIQUE = "UNIQUE "; 
    
    private String text = "";
    public Constraint() {}
    public Constraint(String text) {
        this.text = text;
    }
    
    public Constraint CHECK(String condition)
    {
        this.text += " CHECK("+ condition +") ";
        return new Constraint(this.text);
    }
    
    @Override
    public String toString() {
        return text;
    } 
}
