/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilu
 * @param <T>
 * 
 * Classe abstrata responsável por gerenciar o comportamento repetido das models
 */
public abstract class Schema<T> {
    private String tableName = this.getClass().getSimpleName();
    private List<Column> fields = new ArrayList();
    
    /**
     * Cria uma tabela no banco de dados
     */
    public void createTable() {
        try {
            String statement = "CREATE TABLE IF NOT EXISTS "+ tableName +"(\n";
            for(int i = 0; i < fields.size(); i++) {
                statement = statement + fields.get(i).createColumn();
                if(i != (fields.size() - 1)){
                    statement = statement + ",";
                }
            }
            statement = statement + ");";
            System.out.println(statement);
            Database.getConnection().createStatement().execute(statement);
        }
        catch (SQLException x) {
            System.err.println(x);
        }
        finally {
            this.end();
        }
    }

    public Schema(String tableName) {
        this.tableName = tableName;
    }
    
    public Schema() {
        
    }
    public void addColumn(String name, String type, Constraint options) {
        Column c = new Column(name, type, options);
        fields.add(c);
    }
    
    public void end() {
        Database.closeConnection();
    }
}
