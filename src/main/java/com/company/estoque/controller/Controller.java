/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.estoque.controller;

import com.company.estoque.model.Schema;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Interface que serve de base para todos os controllers.
 * @author guilu
 * @param <T>
 */
public interface Controller<T extends Schema> {
    public ResultSet read();
    public ResultSet read(String ...where);
    public void create(String ...values) throws Exception;
    public void delete(int id);
    public void update(Map<String, Object> updates, String ...where);
}
