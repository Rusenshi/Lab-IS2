/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistencia;

import com.objetos.Paciente;
import com.objetos.Reactivo;
import com.objetos.Stock;
import java.sql.Date;
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
public class DAOStockSQL implements Dao<Stock,Integer>{

    @Override
    public Optional<Stock> get(Integer nroLote) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM STOCK WHERE activo=1 AND nroLote=?");
            stm.setInt(1, nroLote);
            ResultSet res = stm.executeQuery();   
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                DAOReactivoSQL daoReactivo = new DAOReactivoSQL();
                Optional<Reactivo> react = daoReactivo.get(res.getString(4));
                if (react.isPresent()){
                    Stock stock = new Stock(res.getInt(1),res.getInt(2),res.getDate(3),react.get());
                    return Optional.ofNullable(stock); 
                }
                //Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
//                return Optional.ofNullable(p); 
                return Optional.empty();
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
    public List<Stock> getAll() {
        // Crea lista temporal para recorrer resultset
        List<Stock> stocks = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM STOCK WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Crea un dao de reactivo para ir recuperando los Reactivos de cada stock y agregarlos al objeto
            DAOReactivoSQL daoReactivo = new DAOReactivoSQL();
            
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                Optional<Reactivo> react = daoReactivo.get(res.getString(4));
                if (react.isPresent()){
                    Stock s = new Stock(res.getInt(1),res.getInt(2),res.getDate(3),react.get());
                    stocks.add(s);
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return stocks;
    }

    @Override
    public void save(Stock t) {
        try{
            // Preparar Insercion
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO STOCK VALUES (?,?,?,?,1);");
            stm.setInt(1, t.getNroLote());
            stm.setInt(2, t.getCantidad());
            stm.setDate(3, (Date) t.getFechaVencimiento()); // Posible error por casteo
            stm.setString(4, t.getReactivo().getNombre());
            
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
    public void update(Stock t) {
        try{
            // Preparar Modificación
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE STOCK SET nroLote=?,cantidad=?,fechaVencimiento=?,Reactivo_nombre=? WHERE activo = 1 AND nroLote=?;");
            stm.setInt(1, t.getNroLote());
            stm.setInt(2, t.getCantidad());
            stm.setDate(3, t.getFechaVencimiento());
            stm.setString(4, t.getReactivo().getNombre());
            stm.setInt(5, t.getNroLote());
            
            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al modificar");
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(Integer nroLote) {
        try{
            // Preparar Eliminacion (Logica)
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE STOCK SET activo=0 WHERE nroLote=?");
            stm.setInt(1, nroLote);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
    
}
