/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.Analisis;
import com.objetos.Informe;
import com.objetos.Turno;
import com.persistencia.DAOAnalisisSQL;
import com.persistencia.DAOInformeSQL;
import com.persistencia.DAOTurnoSQL;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ideapad Gaming 3
 */
public class ManagerInforme {
    private static DAOTurnoSQL daoTurno = new DAOTurnoSQL();
    private static DAOInformeSQL daoInforme = new DAOInformeSQL();
    private static DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
    private static int indexTurnoCargado = -1;
    
    public static boolean existeTurno(String nroOrdenServicioString){
        int nroOrdenServicio = 0;
        try{
            nroOrdenServicio = Integer.parseInt(nroOrdenServicioString);
        }
        catch(NumberFormatException e){
            return false;
        }
        
        return daoTurno.get(nroOrdenServicio).isPresent();
    }
//    public static void llenarTablaAnalisis(int nroOrdenServicio, DefaultTableModel tablal){
//        Optional<Turno> t = daoTurno.get(nroOrdenServicio);
//        if (t.isPresent()){
//            System.out.println(t.get());
//            
//            // Rellenar tablas con los analisis
//            for(Analisis analisis : t.get().getAnalisis()){
//                Object[] row = {analisis.getNombre(),analisis.getValorReferencia(),0};
//                tablal.addRow(row);
//            }
//        }
//    }
    public static int llenarTablaAnalisis(int turnoIndex, DefaultTableModel tablal){
        indexTurnoCargado = turnoIndex;
        Turno t = ManagerTurno.obtenerListaTurnosPorDNI().get(turnoIndex);
        // Rellenar tablas con los analisis
        for(Analisis analisis : t.getAnalisis()){
            Object[] row = {analisis.getNombre(),analisis.getValorReferencia(),0};
            tablal.addRow(row);
        }
//        Optional<Turno> t = daoTurno.get(turnoIndex);
//        if (t.isPresent()){
//            System.out.println(t.get());
//            
//            // Rellenar tablas con los analisis
//            for(Analisis analisis : t.get().getAnalisis()){
//                Object[] row = {analisis.getNombre(),analisis.getValorReferencia(),0};
//                tablal.addRow(row);
//            }
//        }
        return t.getNroOrdenServicio();
    }
    
//    public static boolean validarNumero(String numero){
//        return numero.matches ("\\d+"); // Verifica solo numeros
//    }
    
    public static boolean validarNumero(String numeroString){
        float numero = 0;
        try{
            numero = Float.parseFloat(numeroString);
        }
        catch(NumberFormatException e){
            return false;
        }
        
        return true;
//        return numero.matches ("\\d+"); // Verifica solo numeros
    }
    
    public static boolean validarResultado(DefaultTableModel tabla){
        boolean exito = true;
        Vector<Vector> data = tabla.getDataVector();
        for(Vector row : data){
            exito &= validarNumero(row.get(2).toString());
            if (!exito) return false;
        }
        return exito;
    }
    
//    public static void guardarInforme(String nroOrdenServicioString, DefaultTableModel tabla){
//        int nroOrdenServicio = 0;
//        try{
//            nroOrdenServicio = Integer.parseInt(nroOrdenServicioString);
//        }
//        catch(NumberFormatException e) {
//            return;
//        }
//        Informe informe = new Informe(nroOrdenServicio);
//        
//        Vector<Vector> data = tabla.getDataVector();
//        for(Vector row : data){
//            Optional<Analisis> analisis = daoAnalisis.get(row.get(0).toString());
//            float resultado = Float.parseFloat(row.get(2).toString());
//            if (analisis.isPresent()){
//                // Llena tabla de hash
//                informe.getResultados().put(analisis.get(), resultado);
//            }
//        }
//        
//        System.out.println(informe);
//        
//        daoInforme.save(informe);
//    }
    public static void guardarInforme(DefaultTableModel tabla){
        int nroOrdenServicio = 0;
        if (indexTurnoCargado == -1) return; // No se ha cargado los turnos de un paciente
        try{
            nroOrdenServicio = ManagerTurno.obtenerListaTurnosPorDNI().get(indexTurnoCargado).getNroOrdenServicio();
        }catch(IndexOutOfBoundsException e){
            System.out.println("ERROR INTERNO: El combobox y la lista de turno por dni no coinciden en indices, saliendo...");
            return;
        }
        Informe informe = new Informe(nroOrdenServicio);
        
        
        Vector<Vector> data = tabla.getDataVector();
        for(Vector row : data){
            Optional<Analisis> analisis = daoAnalisis.get(row.get(0).toString());
            float resultado = Float.parseFloat(row.get(2).toString());
            if (analisis.isPresent()){
                // Llena tabla de hash
                informe.getResultados().put(analisis.get(), resultado);
            }
        }
        
        System.out.println(informe);
        
        daoInforme.save(informe);
        indexTurnoCargado=-1;
        daoTurno.delete(nroOrdenServicio);
        tabla.getDataVector().clear();
    }
    
    
    
}
