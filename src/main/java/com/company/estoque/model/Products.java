/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.model;

/**
 *
 * @author guilu
 */
public class Products extends Schema{

    
    public Products() {
        super();
        this.addColumn("desc", "varchar(255)", new Constraint());
        this.addColumn("preco", "real", new Constraint());
        this.addColumn("qtde_estoque", "integer", new Constraint());
        this.createTable();
    }

    public Products(String tableName) {
        super(tableName);
    }
    
    /**
     *
     * @param values
     * @throws Exception
     */
    @Override
    public void insert(String... values) throws Exception{
        try{
            if(values.length != this.colNum()){
                throw new Exception("Número de argumentos diferente do "
                        + "numero de colunas");
            }
            String sql = "INSERT INTO " + getTableName() + "("
                    + listColumns() + ") VALUES (";
            for(var val: values){
                sql = sql + val + ",";
            }
            sql = sql.substring(0,sql.length() - 1);
            sql = sql + ");";
            Database.getStatement().execute(sql);
            return;
        }
        catch(Exception x)
        {
            System.err.println(x);
        }
        throw new Exception("Não foi possível inseriri dados no banco!");

    }
    
    
}
