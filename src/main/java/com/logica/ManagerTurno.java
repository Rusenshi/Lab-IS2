/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.Turno;
import com.persistencia.DAOAnalisisSQL;
import com.persistencia.DAOObraSocialSQL;
import com.persistencia.DAOPacienteSQL;
import com.persistencia.DAOTurnoSQL;
import com.toedter.calendar.JCalendar;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ideapad Gaming 3
 */
public class ManagerTurno {
    private static ArrayList<Turno> lastQuery;
    private static DAOTurnoSQL daoTurno = new DAOTurnoSQL();
    private static DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
    private static DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
    private static DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
    
    public static int obtenerCantidadTurnosPorFecha(String fecha){
        List<Turno> lista = daoTurno.getByDate(fecha);
        return lista.size();
    }
    
    public static boolean validarSoloTexto(String nombre){
        return nombre.matches ("\\w+[\\s+\\w+]*"); // Verifica solo texto
    }
    
    
    // Generacion de claves unicas basadas en existencia
    public static List<Integer> armarListaClaves(){
        List<Integer> lista = new ArrayList<>();
        
        List<Turno> turnos = daoTurno.getAll();
        for(Turno t : turnos){
            lista.add(t.getNroOrdenServicio());
        }
        Collections.sort(lista);
        
        System.out.println(lista);
        
        
        return lista;
    }
    
    public static int createUniqueKey(List<Integer> listaClaves){
        int nextFree = 0;
        int claveAnterior = 0;
        
        for(int clave : listaClaves){
            int dif = clave-claveAnterior;
            System.out.println("dif: " + dif);
            
            if (dif > 1){
                nextFree = clave+1;
                listaClaves.add(nextFree);
                Collections.sort(listaClaves);
                break;
            }
            
            claveAnterior = clave;
        }
        
        System.out.println("lista: " + listaClaves);
        System.out.println("nextFree: " + nextFree);
        return nextFree;
    }
    public static int hashNroOrdenServicio(String dateFormatted){
        int cantidadTurnos = obtenerCantidadTurnosPorFecha(dateFormatted);
//        String cantidadTurnosString;
//        if (cantidadTurnos < 10){
//            cantidadTurnosString = cantidadTurnos + "0";
//        }
//        else{
//            cantidadTurnosString = cantidadTurnos+"";
//        }
        
        String dateFormattedNoGuion = dateFormatted.replace("-", "");
        String hashString = dateFormattedNoGuion+cantidadTurnos;
        System.out.println(hashString);
        return Integer.parseInt(hashString);
    }

    public static void guardarTurno(String s_dniPaciente, String s_obraSocial, String s_medico, String s_diagnostico, JCalendar date_fecha, Set<String> set_analisisElegidos) {
        
        int dia = date_fecha.getDayChooser().getDay();
        int mes = date_fecha.getMonthChooser().getMonth()+1;
        int año = date_fecha.getYearChooser().getYear();
        String fechaFormateada = año+"-"+mes+"-"+dia;
        
        Date fecha = Date.valueOf(fechaFormateada);
        
        // Generar clave unica
        int nroOrdenServicio = ManagerTurno.hashNroOrdenServicio(fechaFormateada);
        
        Turno t = new Turno(nroOrdenServicio,fecha,s_medico,s_diagnostico);
        t.setPaciente(daoPaciente.get(Integer.valueOf(s_dniPaciente)).get());
        t.setObraSocial(daoObraSocial.get(s_obraSocial).get());
        for(String analisisName : set_analisisElegidos){
            t.getAnalisis().add(daoAnalisis.get(analisisName).get());
        }
        daoTurno.save(t);
    }
}
