/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author guilu
 * 
 * 
 * Classe abstrata responsável por gerenciar o comportamento repetido das models
 */
public abstract class Schema {
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

    public String update(Map<String, Object> updates, String ...where) {
        try{
            String sql = "UPDATE "+tableName+" SET ";
            for(Map.Entry<String, Object> o: updates.entrySet()){
                sql = sql + o.getKey() + " = " + o.getValue() + ",";
            }
            sql = sql.substring(0,sql.length() - 1);
            if(where.length > 0) {
                sql = sql + " WHERE ";
                for(String cond: where) {
                    sql = sql + cond + " ";
                }
            }
            sql = sql +";";
            Database.getConnection().createStatement().execute(sql);
            return sql;
        }
        catch(SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    public Schema(String tableName) {
        try{
        this.tableName = tableName;
        Database.getConnection().createStatement().execute(".open database/estoque.db");
        }
        catch(SQLException e)
        {
            System.err.println(e);
        }
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
    
    public void setTableName(String newName) {
        try{
            Database.getConnection().createStatement()
                    .execute("DROP TABLE IF EXISTS"+ tableName +";");
            this.tableName = newName;
            this.createTable();
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public void insert(String ...values) {
        try{
            String sql = "INSERT INTO "+ tableName + "("+ this +") " + "VALUES (";
            if(values.length != fields.size()) {
                throw new SQLException("Número de argumentos diferente "
                        + "do número de colunas da tabela");
            }
            System.out.println(sql);
            Database.getConnection().createStatement().execute(sql);
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
    }

    public ResultSet select() {
        try{
            var sql = "SELECT * FROM "+ tableName;
            Statement stmt = Database.getConnection().createStatement();
            return stmt.executeQuery(sql);
        }
        catch( SQLException e)
        {
            System.err.println(e);
        }
        return null;
    }
    @Override
    public final String toString() {
        var ret = "";
        for(Column c: fields)
        {
            ret = ret + c +",";
        }
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }
    
    
}
