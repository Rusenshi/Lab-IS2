/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.persistencia;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jerem
 */
public interface Dao<T,IDTYPE> {
    Optional<T> get(IDTYPE id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(IDTYPE id);
}
