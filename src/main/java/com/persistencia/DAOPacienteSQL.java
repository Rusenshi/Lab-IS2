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
public class DAOPacienteSQL implements Dao<Paciente,Integer>{

    @Override
    public Optional<Paciente> get(Integer id) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE WHERE activo=1 AND dni=?");
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();   
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                
                // Obtener las obras sociales vinculadas a este paciente
                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni =?");
                stmOS.setInt(1, id);
                ResultSet resOS = stmOS.executeQuery();   
                while(resOS.next()){
                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
                }
                
                
                return Optional.ofNullable(p); 
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
    public List<Paciente> getAll() {
        // Crea lista temporal para recorrer resultset
        List<Paciente> pacientes = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                
                // Obtener las obras sociales vinculadas a este paciente
                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni =?");
                stmOS.setInt(1, p.getDni());
                ResultSet resOS = stmOS.executeQuery();   
                while(resOS.next()){
                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
                }
                
                
                pacientes.add(p);
                
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
            // Preparar Insercion del paciente
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO PACIENTE VALUES (?,?,?,?,?,?,?,?,1);");
            stm.setInt(1, t.getDni());
            stm.setString(2, t.getNombre());
            stm.setString(3, t.getApellido());
            stm.setInt(4, t.getSexo());
            stm.setInt(5, t.getEdad());
            stm.setString(6, t.getDomicilio());
            stm.setString(7, t.getTelefono());
            stm.setString(8, t.getCorreoElectronico());
            
            stm.execute();
            
            // Itera sobre la lista de obras sociales del paciente
            for(ObraSocial os : t.getObrasSociales()){
                // Prepara Insercion en la tabla que relaciona un paciente con una obra social (Paciente_tiene_obraSocial)
                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO PACIENTE_TIENE_OBRASOCIAL VALUES (?,?,1);");
                stmOS.setInt(1, t.getDni());
                stmOS.setString(2, os.getNombre());
                stmOS.execute();
            }
        }
        catch(SQLException e){
            System.out.println("Error al insertar");
            System.out.println(e.getSQLState());
            System.out.println(e.toString());
            
            // Control de Clave Duplicada y por Activo = 0
            if (e.getSQLState().equals("23505")){
                try {
                    // Preparar Eliminacion (Logica)
                    PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTE SET dni=?,nombre=?,apellido=?,sexo=?,edad=?,domicilio=?,telefono=?,correoElectronico=?,activo=1 WHERE dni=?");
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
                } catch (SQLException sQLException) {
                    System.out.println("Error al reinsertar");
                    System.out.println(e.getSQLState());
                    System.out.println(e.toString());
                }
            }
        }
    }

    @Override
    public void update(Paciente t) {
        try{
            // Preparar Modificación
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTE SET dni=?,nombre=?,apellido=?,sexo=?,edad=?,domicilio=?,telefono=?,correoElectronico=? WHERE activo = 1 AND dni=?;");
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
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTE SET activo=0 WHERE dni=?");
            stm.setInt(1, id);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
}
