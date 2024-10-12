/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistencia;

import com.objetos.ObraSocial;
import com.objetos.Paciente;
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
public class DAOObraSocialSQL implements Dao<ObraSocial,String>{

    @Override
    public Optional<ObraSocial> get(String nombre) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM OBRASOCIAL WHERE activo=1 AND nombre=?");
            stm.setString(1, nombre);
            ResultSet res = stm.executeQuery();   
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                ObraSocial os = new ObraSocial(res.getString(1));
                //Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                return Optional.ofNullable(os); 
            }
            else{
                return Optional.empty();
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar por clave");
            System.out.println(e.toString());
            return Optional.empty();
        }
    }
    
    @Override
    public List<ObraSocial> getAll() {
        // Crea lista temporal para recorrer resultset
        List<ObraSocial> obrasSociales = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM OBRASOCIAL WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                obrasSociales.add(new ObraSocial(res.getString(1)));
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return obrasSociales;
    }

    public ArrayList<ObraSocial> getByDni(int dni) {
        // Crea lista temporal para recorrer resultset
        ArrayList<ObraSocial> obrasSociales = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni=?");
            stm.setInt(1, dni);
            ResultSet res = stm.executeQuery();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                obrasSociales.add(new ObraSocial(res.getString(2)));
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return obrasSociales;
    }
    
    
    
    @Override
    public void save(ObraSocial t) {
        try{
            // Preparar Insercion
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO OBRASOCIAL VALUES (?,1);");
            stm.setString(1, t.getNombre());
            
            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al insertar");
            System.out.println(e.getSQLState());
            System.out.println(e.toString());
            
            // Control de Clave Duplicada y por Activo = 0
//            if (e.getSQLState().equals("23505")){
//                try {
//                    // Preparar Eliminacion (Logica)
//                    PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTES SET dni=?,nombre=?,apellido=?,sexo=?,edad=?,domicilio=?,telefono=?,correoElectronico=?,activo=1 WHERE dni=?");
//                    stm.setInt(1, t.getDni());
//                    stm.setString(2, t.getNombre());
//                    stm.setString(3, t.getApellido());
//                    stm.setInt(4, t.getSexo());
//                    stm.setInt(5, t.getEdad());
//                    stm.setString(6, t.getDomicilio());
//                    stm.setString(7, t.getTelefono());
//                    stm.setString(8, t.getCorreoElectronico());
//                    stm.setInt(9, t.getDni());
//                    
//                    stm.execute();
//                } catch (SQLException sQLException) {
//                    System.out.println("Error al reinsertar");
//                    System.out.println(e.getSQLState());
//                    System.out.println(e.toString());
//                }
//            }
        }
    }

    @Override
    public void update(ObraSocial t) {
        try{
            // Preparar Modificación
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE OBRASOCIAL SET nombre=? WHERE activo = 1 AND nombre=?;");
            stm.setString(1, t.getNombre());
            stm.setString(2, t.getNombre());
            
            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al modificar");
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(String nombre) {
        try{
            // Preparar Eliminacion (Logica)
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE OBRASOCIAL SET activo=0 WHERE nombre=?");
            stm.setString(1, nombre);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
    
}
