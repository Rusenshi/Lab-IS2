/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.Reactivo;
import com.persistencia.DAOReactivoSQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ideapad Gaming 3
 */
public class ManagerReactivo {
    private static final DAOReactivoSQL daoReactivo = new DAOReactivoSQL();
    private static List<Reactivo> lastQuery = new ArrayList<>();
    
    public static List<Reactivo> obtenerTodosLosReactivos(){
        lastQuery = daoReactivo.getAll();
        return lastQuery;
    }
    
    public static List<String> obtenerListaNombresReactivos(){
        List<String> nombresReactivos = new ArrayList<>();
        for(Reactivo r : obtenerTodosLosReactivos()){
            nombresReactivos.add(r.getNombre());
        }
        return nombresReactivos;
    }
    
    public static Reactivo obtenerPorNombre(String nombre){
        Optional<Reactivo> reactivo = daoReactivo.get(nombre);
        if (reactivo.isPresent()){
            return reactivo.get();
        }
        else{
            System.out.println("ERROR EN BASE DE DATOS: EL REACTIVO NO EXISTE");
            return null;
        }
    }
    
}
