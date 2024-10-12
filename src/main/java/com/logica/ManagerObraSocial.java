/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.ObraSocial;
import com.persistencia.DAOObraSocialSQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ideapad Gaming 3
 */
public class ManagerObraSocial {
    public static List<ObraSocial> lastQuery = new ArrayList<ObraSocial>();
    public static DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
    
    public static boolean validarNombre(String nombre){
        return daoObraSocial.get(nombre).isPresent();
    }
    
    public static ObraSocial recuperarObraSocial(String nombre){
        Optional<ObraSocial> obraSocial = daoObraSocial.get(nombre);
        if (obraSocial.isPresent()){
            return obraSocial.get();
        }
        else return null;
    }
    
    public static ArrayList<ObraSocial> obtenerObrasSocialesDisponibles(){
        lastQuery = daoObraSocial.getAll();
        return (ArrayList<ObraSocial>) lastQuery;
    }
    
    public static ArrayList<ObraSocial> obtenerObrasSocialesPorDni(int dni){
        lastQuery = daoObraSocial.getByDni(dni);
        return (ArrayList<ObraSocial>) lastQuery;
    }
    
    
}
