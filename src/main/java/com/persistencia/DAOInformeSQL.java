/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistencia;

import com.objetos.Analisis;
import com.objetos.Informe;
import com.objetos.ObraSocial;
import com.objetos.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Ideapad Gaming 3
 */
public class DAOInformeSQL implements Dao<Informe,Integer>{

    @Override
    public Optional<Informe> get(Integer nroOrdenServicio) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM INFORME WHERE activo=1 AND Turno_nroOrdenServicio=?");
            stm.setInt(1, nroOrdenServicio);
            ResultSet res = stm.executeQuery();   
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                Informe inf = new Informe(res.getInt(1));
                
                // Obtener las obras sociales vinculadas a este paciente
                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM INFORME_TIENE_ANALISIS WHERE activo=1 AND Informe_Turno_nroOrdenServicio=?");
                stmOS.setInt(1, nroOrdenServicio);
                ResultSet resOS = stmOS.executeQuery();  
                
                // Llenamos la tabla de hash
                while(resOS.next()){
                    float valorObtenido = resOS.getFloat(3);
                    DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
                    Optional<Analisis> analisis = daoAnalisis.get(resOS.getString(1));
                    if (analisis.isPresent()){
                        inf.getResultados().put(analisis.get(), valorObtenido);
                    }
                }
                
                return Optional.ofNullable(inf); 
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
    public List<Informe> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Informe t) {
        try{
            // Preparar Insercion del paciente
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO INFORME VALUES (?,?,?,1);");
            stm.setInt(1, t.getNroOrdenServicio());
            stm.setString(2, t.getMedioElectronico());
            stm.setInt(3, t.getTecnico().getDni());
            
            stm.execute();
            
            // Itera sobre la tabla de hask de analisis del informe
            Set<Analisis> setOfKeys = t.getResultados().keySet();
 
            for (Analisis key : setOfKeys) {
                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO INFORME_TIENE_ANALISIS VALUES (?,?,?,1);");
                stmOS.setString(1, key.getNombre());
                stmOS.setInt(2, t.getNroOrdenServicio());
                stmOS.setFloat(3, t.getResultados().get(key));
                stmOS.execute();
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
    public void update(Informe t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
