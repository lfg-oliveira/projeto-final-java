/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.estoque.controller;

import com.company.estoque.model.Products;
import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller responsável pela model Products
 * @author guilu
 */
public class ProductController implements Controller<Products> {

    @Override
    public ResultSet read() {
        try {
            return prod.select();
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void create(String... values) throws Exception{
        try {
            prod.insert(values);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void delete(int id) {
        prod.delete(id);
    }

    @Override
    public void update(Map<String, Object> updates, String... where) {
        prod.update(updates, where);
    }

    private final Products prod = new Products();

    public ResultSet lastRow() {
        try {
            return prod.lastRow();
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Transforma a string na forma correta para leitura do banco de dados
     *
     * @param v
     * @return
     */
    public static String asString(String v) {
        return "'" + v + "'";
    }

    @Override
    public ResultSet read(String... where) {
        try {
            return prod.select(where);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
