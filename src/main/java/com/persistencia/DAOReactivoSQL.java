/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistencia;

import com.objetos.Paciente;
import com.objetos.Reactivo;
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
public class DAOReactivoSQL implements Dao<Reactivo,String>{

    @Override
    public Optional<Reactivo> get(String nombre) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM REACTIVO WHERE activo=1 AND nombre=?");
            stm.setString(1, nombre);
            ResultSet res = stm.executeQuery();   
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                //Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                Reactivo r = new Reactivo(res.getString(1),res.getFloat(2));
                return Optional.ofNullable(r); 
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
    public List<Reactivo> getAll() {
        // Crea lista temporal para recorrer resultset
        List<Reactivo> reactivos = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM REACTIVO WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                reactivos.add(new Reactivo(res.getString(1),res.getFloat(2)));
                //reactivos.add(new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8)));
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return reactivos;
    }

    @Override
    public void save(Reactivo t) {
        try{
            // Preparar Insercion
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO REACTIVO VALUES (?,?,1);");
            stm.setString(1, t.getNombre());
            stm.setFloat(2, t.getCantidadRecipiente());
            
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
    public void update(Reactivo t) {
        try{
            // Preparar Modificación
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE REACTIVO SET nombre=?,cantidadRecipiente=? WHERE activo = 1 AND nombre=?;");
            stm.setString(1, t.getNombre());
            stm.setFloat(2, t.getCantidadRecipiente());
            stm.setString(3, t.getNombre());
            
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
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE REACTIVO SET activo=0 WHERE nombre=?");
            stm.setString(1, nombre);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
    
}
