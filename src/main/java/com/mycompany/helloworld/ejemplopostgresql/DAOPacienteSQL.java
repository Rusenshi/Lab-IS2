/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.helloworld.ejemplopostgresql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jerem
 */
public class DAOPacienteSQL implements Dao<Paciente,Integer>{

    @Override
    public Optional<Paciente> get(Integer id) {
    try{
            // Crea lista temporal para recorrer resultset
            List<Paciente> pacientes = new ArrayList<>();
            
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTES WHERE activo=1 AND dni=?");
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();   
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                pacientes.add(new Paciente(res.getString(2),res.getString(3),res.getInt(1),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8)));
            }
            
            // Comprueba si encontró al paciente de dni "id"
            if (pacientes.isEmpty()) return Optional.empty();
            
            // Retorna el unico elemento que debería haber en la lista
            return Optional.ofNullable(pacientes.get((int) 0)); 
        }
        catch(SQLException e){
            System.out.println("Error al buscar por clave");
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

    @Override
    public List<Paciente> getAll() {
        // Crea lista temporal para recorrer resultset
        List<Paciente> pacientes = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTES WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                pacientes.add(new Paciente(res.getString(2),res.getString(3),res.getInt(1),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8)));
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return pacientes;
    }

    @Override
    public void save(Paciente t) {
        try{
            // Preparar Insercion
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO PACIENTES VALUES (?,?,?,?,?,?,?,?,1);");
            stm.setInt(1, t.getDni());
            stm.setString(2, t.getNombre());
            stm.setString(3, t.getApellido());
            stm.setInt(4, t.getSexo());
            stm.setInt(5, t.getEdad());
            stm.setString(6, t.getDomicilio());
            stm.setString(7, t.getTelefono());
            stm.setString(8, t.getCorreoElectronico());
            
            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al insertar");
            System.out.println(e.toString());
        }
    }

    @Override
    public void update(Paciente t) {
        try{
            // Preparar Modificación
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTES SET dni=?,nombre=?,apellido=?,sexo=?,edad=?,domicilio=?,telefono=?,correoElectronico=? WHERE activo = 1 AND dni=?;");
            stm.setInt(1, t.getDni());
            stm.setString(2, t.getNombre());
            stm.setString(3, t.getApellido());
            stm.setInt(4, t.getSexo());
            stm.setInt(5, t.getEdad());
            stm.setString(6, t.getDomicilio());
            stm.setString(7, t.getTelefono());
            stm.setString(8, t.getCorreoElectronico());
            stm.setInt(9, t.getDni());
            
            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al modificar");
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            // Preparar Eliminacion (Logica)
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTES SET activo=0 WHERE dni=?");
            stm.setInt(1, id);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
}
