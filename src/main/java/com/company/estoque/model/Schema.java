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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void update(Map<String, Object> updates, String ...where) {
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
        }
        catch(SQLException e) {
            System.err.println(e);
        }
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
    
    public abstract void insert(String ...values) throws Exception;

    public ResultSet select() throws Exception {
        try{
            var sql = "SELECT rowid, * FROM " + tableName;
            Statement stmt = Database.getConnection()
                    .createStatement();
            return stmt.executeQuery(sql);
        }
        catch( SQLException e)
        {
            System.err.println(e);
        }
        throw new Exception("Não foi selecionar tabela no banco de dados!");
    }
    
    public ResultSet select(String ...where) throws Exception {
        try{
            var sql = "SELECT rowid, * FROM " + tableName + " WHERE ";
            for(var w : where) {
                sql = sql + w;
            }
            sql = sql + ";";
            Statement stmt = Database.getConnection()
                    .createStatement();
            return stmt.executeQuery(sql);
        }
        catch( SQLException e)
        {
            System.err.println(e);
        }
        throw new Exception("Não foi possivel selecionar tabela no banco de dados!");
    }
    
    public ResultSet lastRow() throws Exception{
        try{
            var sql = "SELECT MAX(rowid) FROM " + tableName;
            Statement stmt = Database.getConnection()
                    .createStatement();
            return stmt.executeQuery(sql);
        }
        catch( SQLException e)
        {
            System.err.println(e);
        }
        throw new Exception("Não foi selecionar tabela no banco de dados!");
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
    
    /**
     * Retorna as colunas na ordem que foram inseridas no banco de dados
     * @return
     */
    public String listColumns() {
        var cols = "";
        for(var c: this.fields) {
            cols = cols + c.toString() + ",";
        }
        cols = cols.substring(0,cols.length() - 1);
        return cols;
    }
    
    public void delete(int id) {
        try {
            String sql = "DELETE FROM "+tableName+" WHERE rowid = "+ id;
            Database.getStatement().execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(Schema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int colNum() {
        return this.fields.size();
    }

    public String getTableName() {
        return tableName;
    }

    public List<Column> getFields() {
        return fields;
    }
}
