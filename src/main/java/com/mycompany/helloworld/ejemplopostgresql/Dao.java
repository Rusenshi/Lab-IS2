/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.helloworld.ejemplopostgresql;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jerem
 */
public interface Dao<T> {
    
    Optional<T> get(long id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, Object[] params);
    
    void delete(T t);
}

