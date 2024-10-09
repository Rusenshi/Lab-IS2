/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistencia;

import com.objetos.Analisis;
import com.objetos.ObraSocial;
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
public class DAOAnalisisSQL implements Dao<Analisis,String>{

    @Override
    public Optional<Analisis> get(String nombre) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM ANALISIS WHERE activo=1 AND nombre=?");
            stm.setString(1, nombre);
            ResultSet res = stm.executeQuery();   
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                Analisis analisis = new Analisis(res.getString(1),res.getFloat(2),res.getString(3),res.getFloat(4));
                //Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                
                // Obtener los reactivos vinculados a este analisis
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM ANALISIS_USA_REACTIVO WHERE activo=1 AND Analisis_nombre =?");
                stmAnalisis.setString(1, nombre);
                ResultSet resAnalisis = stmAnalisis.executeQuery();
                
                // Creamos un DAO de reactivo para crear los objetos de Reactivos
                DAOReactivoSQL daoReactivo = new DAOReactivoSQL();
                
                while(resAnalisis.next()){
//                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
                    Optional <Reactivo> r = daoReactivo.get(resAnalisis.getString(1));
                    if (r.isPresent()){
                        analisis.getReactivosUsados().add(r.get());
                    }
                }
                
                
                return Optional.ofNullable(analisis); 
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
    public List<Analisis> getAll() {
        // Crea lista temporal para recorrer resultset
        List<Analisis> analisis = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM ANALISIS WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                Analisis a = new Analisis(res.getString(1),res.getFloat(2),res.getString(3),res.getFloat(4));
                
                // Obtener los reactivos vinculados a este analisis
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM ANALISIS_USA_REACTIVO WHERE activo=1 AND Analisis_nombre =?");
                stmAnalisis.setString(1, a.getNombre());
                ResultSet resAnalisis = stmAnalisis.executeQuery();
                
                // Creamos un DAO de reactivo para crear los objetos de Reactivos
                DAOReactivoSQL daoReactivo = new DAOReactivoSQL();  
                while(resAnalisis.next()){
//                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
                    Optional <Reactivo> r = daoReactivo.get(resAnalisis.getString(1));
                    if (r.isPresent()){
                        a.getReactivosUsados().add(r.get());
                    }
                }
                
                
                analisis.add(a);
                
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return analisis;
    }

    @Override
    public void save(Analisis t) {
        try{
            // Preparar Insercion del paciente
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO ANALISIS VALUES (?,?,?,?,1);");
            stm.setString(1, t.getNombre());
            stm.setFloat(2, t.getValorReferencia());
            stm.setString(3, t.getMetodoUsado());
            stm.setFloat(4, t.getMonto());
            
            stm.execute();
            
            // Itera sobre la lista de obras sociales del paciente
            for(Reactivo r : t.getReactivosUsados()){
                // Prepara Insercion en la tabla que relaciona un paciente con una obra social (Paciente_tiene_obraSocial)
                PreparedStatement stmReact = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO ANALISIS_USA_REACTIVO VALUES (?,?,1);");
                stmReact.setString(2, t.getNombre());
                stmReact.setString(1, r.getNombre());
                stmReact.execute();
            }
        }
        catch(SQLException e){
            System.out.println("Error al insertar");
            System.out.println(e.getSQLState());
            System.out.println(e.toString());
            
            // Control de Clave Duplicada y por Activo = 0
//            if (e.getSQLState().equals("23505")){
//                try {
//                    // Preparar Eliminacion (Logica)
//                    PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE PACIENTE SET dni=?,nombre=?,apellido=?,sexo=?,edad=?,domicilio=?,telefono=?,correoElectronico=?,activo=1 WHERE dni=?");
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
    public void update(Analisis t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String nombre) {
        try{
            // Preparar Eliminacion (Logica)
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE ANALISIS SET activo=0 WHERE nombre=?");
            stm.setString(1, nombre);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
    
}
