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
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author jerem
 */
public class PacienteDAOSQL implements DaoOld<Paciente>{
    // Capa de persistencia
    private List<Paciente> pacientes = new ArrayList<>();
    
    public PacienteDAOSQL() {
        
    }

    @Override
//    public Optional<Paciente> get(long id) {
//        
//        try{
//            // Preparar Consulta
//            // Nota: Activo es la variable para el eliminado logico
//            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTES WHERE id=? AND activo=1");
//            stm.setLong(1, id);
//            ResultSet res = stm.executeQuery();
//            
//            // Recorrer resultado de la consulta y convertirlo en una lista
//            while(res.next()){
//                pacientes.add(new Paciente(res.getString(0),res.getString(0),res.getInt(0),res.getInt(0),res.getInt(0),res.getString(0),res.getString(0),res.getString(0)));
//                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
//            }
//        }
//        catch(SQLException e){
//            System.out.println("no se pudo conectar con la base de datos");
//        }
//        
//        return Optional.ofNullable(pacientes.get((int) id));
//    }
    
    // Retorna un Optional que indica si el objeto de clave "id" existe o no
    public Optional<Paciente> get(long id) {
        
        try{
            // Limpia la lista temporal de objetos de la consulta anterior
            pacientes.clear();
            
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTES WHERE dni=? AND activo=1");
            stm.setLong(1, id);
            ResultSet res = stm.executeQuery();   
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                pacientes.add(new Paciente(res.getString(2),res.getString(3),res.getInt(1),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8)));
            }
            
            // Comprueba si encontró al paciente de dni "id"
            if (pacientes.isEmpty()) return Optional.empty();
            
            return Optional.ofNullable(pacientes.get((int) 0)); // deberia haber uno solo
        }
        catch(SQLException e){
            System.out.println("Error al buscar por clave");
            System.out.println(e.toString());
            return Optional.empty();
        }
        
//        return Optional.ofNullable(pacientes.get((int) id));
    }

    @Override
    public List<Paciente> getAll() {
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTES WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Limpia la lista de la consulta anterior
            pacientes.clear();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                pacientes.add(new Paciente(res.getString(2),res.getString(3),res.getInt(1),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8)));
            }
            
            return pacientes;
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
            return null;
        }
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
    public void update(Paciente t, Object[] params) {
//        t.setNombre(Objects.requireNonNull(
//          params[0], "Name cannot be null"));
//        t.setCorreoElectronico(Objects.requireNonNull(
//          params[1], "Email cannot be null"));
//        
//        pacientes.add(t);
        
        try{
            // modificar primero el objeto paciente, y luego sincronizarlo con la base de datos
            t.setDni((int) params[0]);
            t.setNombre((String) params[1]);
            t.setApellido((String) params[2]);
            t.setSexo((int) params[3]);
            t.setEdad((int) params[4]);
            t.setDomicilio((String) params[5]);
            t.setTelefono((String) params[6]);
            t.setCorreoElectronico((String) params[7]);
            
            System.out.println(t);
            
            // Preparar Modificación
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTES SET dni=?,nombre=?,apellido=?,sexo=?,edad=?,domicilio=?,telefono=?,correoElectronico=?,activo=? WHERE dni=?;");
            stm.setInt(1, t.getDni());
            stm.setString(2, t.getNombre());
            stm.setString(3, t.getApellido());
            stm.setInt(4, t.getSexo());
            stm.setInt(5, t.getEdad());
            stm.setString(6, t.getDomicilio());
            stm.setString(7, t.getTelefono());
            stm.setString(8, t.getCorreoElectronico());
            stm.setInt(10, t.getDni());
            
            if (params.length > 8){
                stm.setInt(9, (int) params[8]);
            }
            else {
                stm.setInt(9, 1); // Potencial problema, analizar despues
            }
            
            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al modificar");
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(Paciente t) {
//        pacientes.remove(t);
        try{
            // Preparar Eliminacion (Logica)
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTES SET activo=0 WHERE dni=?");
            stm.setInt(1, t.getDni());
            
            stm.executeQuery();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
    
}
