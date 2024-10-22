/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.ObraSocial;
import com.objetos.Paciente;
import com.persistencia.DAOObraSocialSQL;
import com.persistencia.DAOPacienteSQL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jerem
 */
public class ManagerPaciente {
    private static DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
    private static DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
    
    private static ArrayList<Paciente> lastQuery = new ArrayList<>();
//    private static ArrayList<Paciente> pacienteQuery = new ArrayList<Paciente>();
    
    
    public static boolean comprobarExistencia(int dni){
        return (dni > 0 && dni < 99999999) && daoPaciente.get(dni).isPresent();
    }
    
    public static boolean validarDni(String dniString){
        try{
            int dni = Integer.parseInt(dniString);
            return (dni > 0 && dni < 99999999);
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    
    
    public static boolean validarNombre(String nombre){
        return nombre.matches ("\\w+[\\s+\\w+]*"); // Verifica solo texto
    }    
    
    public static boolean validarEdad(int edad){
        return (edad > 0 && edad < 120);
    }
    
    public static boolean validarEdad(String edadString){
        try{
            int edad = Integer.parseInt(edadString);
            return (edad > 0 && edad < 120);
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean validarTelefono(String telefono){
        return telefono.matches ("\\d+"); // Verifica solo numeros
    }
    
    public static boolean validarDomicilio(String domicilio){
        return domicilio.matches ("[\\w+\\s+]*\\d+");  // comprueba el formato de domicilio (<nombre [con espacios]> <numero>)
    }
    
    public static boolean validarCorreoElectronico(String correo){
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(correo);
        return matcher.matches();
    }
    
    public static boolean validarCampos(int dni, String nombre, String apellido, int edad, String domicilio, String telefono, String correo){
        return comprobarExistencia(dni) && validarNombre(nombre) && validarNombre(apellido) && validarEdad(edad) && validarDomicilio(domicilio) && validarTelefono(telefono) && validarCorreoElectronico(correo);
    }
    
    public static boolean validarPaso1(int dni, String nombre, String apellido, int edad){
        return comprobarExistencia(dni) && validarNombre(nombre) && validarNombre(apellido) && validarEdad(edad);
    }
    
    
    
    public static ArrayList<ObraSocial> obtenerObrasSociales(int dni){
        ArrayList<ObraSocial> obrasSocialesDni = new ArrayList<>();
        if (comprobarExistencia(dni)){
            obrasSocialesDni = daoObraSocial.getByDni(dni);
        }
        return obrasSocialesDni;
    }

    public static void guardarPaciente(String dniString, String nombre, String apellido, String edadString, int sexo, String domicilio, String telefono, String correo, Set<String> obrasSociales){
        // Guardar Paciente
        int dni = Integer.parseInt(dniString);
        int edad = Integer.parseInt(edadString);

        Paciente p = new Paciente(dni,nombre,apellido,edad,sexo,domicilio,telefono,correo);
        for(String os : obrasSociales){
            ObraSocial obraSocial = ManagerObraSocial.recuperarObraSocial(os);
            p.getObrasSociales().add(obraSocial);
        }


        daoPaciente.save(p);
        
        System.out.println(p);
                    
    }
    
    public static Paciente obtenerPaciente(String dniString){
        int dni = -1;
        try{
            dni = Integer.parseInt(dniString);
        }
        catch(NumberFormatException e){
            return null;
        }
        Optional<Paciente> p = daoPaciente.get(dni);
        if (p.isPresent()) return p.get();
        return null;
    }
}
