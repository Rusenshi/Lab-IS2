/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.Analisis;
import com.objetos.Turno;
import com.persistencia.DAOAnalisisSQL;
import com.persistencia.DAOObraSocialSQL;
import com.persistencia.DAOPacienteSQL;
import com.persistencia.DAOTurnoSQL;
import com.toedter.calendar.JCalendar;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;

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
    private static List<Turno> lastQueryTurnosByDNI;
    private static List<Turno> lastQueryCancelarTurno;
    
    
    public static int obtenerCantidadTurnosPorFecha(String fecha){
//        List<Turno> lista = daoTurno.getByDate(fecha);
        List<Turno> lista = daoTurno.getByDateWithHistory(fecha);
        
        return lista.size();
    }
    
    // Para mostrar al usuario
    public static void filtrarPorDniOFechaCancelarTurno(JComboBox comboboxCancelarTurno, JCalendar calendario,int dni){
        // Rellenar ComboBox de Turnos a cancelar
        comboboxCancelarTurno.removeAllItems();
        
        int dia = calendario.getDayChooser().getDay();
        int mes = calendario.getMonthChooser().getMonth()+1; // Los meses empiezan desde cero en JCalendar
        int año = calendario.getYearChooser().getYear();
//        String fecha = dia + "/"+mes +"/"+ año;     // Para mostrar en pantalla
        String fecha = año+"-"+mes+"-"+dia; // Para la consulta SQL
        
        

        // Para asociacion de la tabla
        if (dni == -1){
            lastQueryCancelarTurno = daoTurno.getByDate(fecha);
        }
        else{
            lastQueryCancelarTurno = daoTurno.getByDateAndDNI(fecha, dni);
        }
        
        
        for(Turno t : lastQueryCancelarTurno){
            String label = "("+t.getPaciente().getDni() +") "+ t.getPaciente().getApellido() +", "+ t.getPaciente().getNombre()+". " + t.getFecha().toString();
            comboboxCancelarTurno.addItem(label);
        }
    }
    
    // Para realizar la cancelacion
    public static Turno obtenerTurnoDesdeComboboxCancelarTurno(int index){
        try{
            return lastQueryCancelarTurno.get(index);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("ERROR INTERNO: ASOCIACION FALLIDA");
            return null;
        }
    }
    
    
    public static List<Turno> obtenerTurnosPorFecha(String fecha){
        List<Turno> lista = daoTurno.getByDate(fecha);
        
        return lista;
    }
    
    public static boolean validarSoloTexto(String nombre){
        return nombre.matches ("\\w+[\\s+\\w+]*"); // Verifica solo texto
    }
    
    public static List<Analisis> obtenerAnalisis(){
        return daoAnalisis.getAll();
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
    
//    public static int createUniqueKey(List<Integer> listaClaves){
//        int nextFree = 0;
//        int claveAnterior = 0;
//        
//        for(int clave : listaClaves){
//            int dif = clave-claveAnterior;
//            System.out.println("dif: " + dif);
//            
//            if (dif > 1){
//                nextFree = clave+1;
//                listaClaves.add(nextFree);
//                Collections.sort(listaClaves);
//                break;
//            }
//            
//            claveAnterior = clave;
//        }
//        
//        System.out.println("lista: " + listaClaves);
//        System.out.println("nextFree: " + nextFree);
//        return nextFree;
//    }
    
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

//    public static boolean esDiaDeSemana(java.util.Date date) {
//        SimpleDateFormat format = new SimpleDateFormat("EEEE");
//        
//        String nombreDia = format.format(date);
//        
//        return !nombreDia.equals("sábado") && !nombreDia.equals("domingo");
//    }

    public static List<String> obtenerTurnosPorDNI(int dni) {
        lastQueryTurnosByDNI = daoTurno.getByDNI(dni);
        List<String> turnosPorDNICombobox = new ArrayList<>();
        for(Turno t : lastQueryTurnosByDNI){
            String label = t.getFecha().toString() + ", Cant Analisis: "+t.getAnalisis().size();
            turnosPorDNICombobox.add(label);
        }
        return turnosPorDNICombobox;
    }

    public static List<Turno> obtenerListaTurnosPorDNI() {
        return lastQueryTurnosByDNI;
    }
    
    public static void eliminarTurno(Turno t){
        daoTurno.delete(t.getNroOrdenServicio());
    }
    
}
