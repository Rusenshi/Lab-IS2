/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.Analisis;
import com.objetos.Reactivo;
import com.persistencia.DAOAnalisisSQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ideapad Gaming 3
 */
public class ManagerAnalisis {
    private static final DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
    private static List<Analisis> todosLosAnalisis = daoAnalisis.getAll();
    public static List<Analisis> obtenerTodosLosAnalisis(){
        return todosLosAnalisis;
    }
    public static void actualizarTodosLosAnalisis(){
        todosLosAnalisis = daoAnalisis.getAll();
    }
    public static List<Analisis> filtrarPorNombre(String nombre){
        List<Analisis> analisisFiltrados = new ArrayList<>();
        
        for(Analisis analisis : todosLosAnalisis){
            // Elimina los elementos que no coincidan con el filtro de busqueda
            if (analisis.getNombre().toLowerCase().startsWith(nombre.toLowerCase())){
                analisisFiltrados.add(analisis);
            }
        }
        
        return analisisFiltrados;
    }
    public static Analisis buscarPorNombre(String nombre){
        Optional<Analisis> analisis = daoAnalisis.get(nombre);
        if (analisis.isPresent()){
            return analisis.get();
        }
        else{
            System.out.println("ERROR: ANALISIS NO ENCONTRADO");
            return null;
        }
    }
    
    public static boolean validarSoloTexto(String nombre){
        return nombre.matches ("\\w+[\\s+\\w+]*"); // Verifica solo texto
    }
    
    
    public static boolean validarValorReferencia(String nombre){
        return nombre.matches ("\\[\\d+ a \\d+ \\w+/\\w+\\]"); // Verifica solo texto
    }
    
    
    public static boolean validarFlotante(String monto){
        try{
            Float.parseFloat(monto);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean comprobarExistenciaAnalisis(String nombre){
        return daoAnalisis.get(nombre).isPresent();
    }
    
    public static boolean validarNombreAnalisis(String nombre){
        return validarSoloTexto(nombre) && daoAnalisis.get(nombre).isEmpty(); // Verifica existencia
    }
    
    
    public static boolean validarTablaReactivos(DefaultTableModel tabla){
        boolean exito = true;
        Vector<Vector> data = tabla.getDataVector();
        for(Vector row : data){
            exito &= validarFlotante(row.get(2).toString());
            if (!exito) return false;
        }
        return exito;
    }

    public static void guardarAnalisis(String nombre, String valor, String monto, String metodo, DefaultTableModel tabla) {
        // Crear Objeto Analisis a partir de los campos
        Analisis analisis = new Analisis(nombre,valor,metodo,Float.parseFloat(monto));
        for(Vector<Object> fila : tabla.getDataVector()){
            String nombreReactivo = (String)fila.get(0);
            float cantidadAUsar = Float.parseFloat(fila.get(2).toString());
            Reactivo r = ManagerReactivo.obtenerPorNombre(nombreReactivo);
            analisis.getReactivosUsados().put(r, cantidadAUsar);
        }
        System.out.println(analisis);
        
        // Guardar en Base de datos
        daoAnalisis.save(analisis);
        actualizarTodosLosAnalisis();
    }
    
    
}
