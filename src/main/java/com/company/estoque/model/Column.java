/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.model;


/**
 * Cada coluna representa uma coluna no banco de dados
 * @author guilu
 */
public class Column {
    private final String name;
    private final String type;
    private final Constraint constraints;
    
    /**
     * @param name nome da coluna
     * @param type tipo da coluna
     * @param constraints outras informações (ex.PRIMARY KEY, AUTOINCREMENT, UNIQUE, etc.)
     */
    public Column(String name, String type, Constraint constraints){
        this.name = name;
        this.type = type;
        this.constraints = constraints;
    }
    
    public String createColumn() {
        return name + " " + type + " " + constraints;
    }

    @Override
    public String toString() {
        return name;
    }
}
