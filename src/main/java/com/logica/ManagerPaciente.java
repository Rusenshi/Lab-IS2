/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logica;

import com.objetos.Paciente;
import com.persistencia.DAOPacienteSQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jerem
 */
public class ManagerPaciente {
    private static DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
//    private static ArrayList<Paciente> pacienteQuery = new ArrayList<Paciente>();
    
    
    public static boolean validarDni(int dni){
        boolean enRango = (dni > 0 && dni < 99999999);
        if (enRango){
            return enRango && daoPaciente.get(dni).isPresent();
        }
        return false;
    }
    
    
    
}
