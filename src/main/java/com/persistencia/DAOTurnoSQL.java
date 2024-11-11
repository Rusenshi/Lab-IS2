/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.persistencia;

import com.objetos.Analisis;
import com.objetos.ObraSocial;
import com.objetos.Paciente;
import com.objetos.Reactivo;
import com.objetos.Turno;
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
public class DAOTurnoSQL implements Dao<Turno,Integer>{

    @Override
    public Optional<Turno> get(Integer nroOrdenServicio) {
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO WHERE activo=1 AND nroOrdenServicio=?");
            stm.setInt(1, nroOrdenServicio);
            ResultSet res = stm.executeQuery();
            
            // res tiene el cursor por defecto en -1, si res.next retorna true, significa que hay un elemento en la lista, y por ende es el buscado
            if (res.next()){
                Turno t = new Turno();
                
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
                t.setNroOrdenServicio(res.getInt(1));
                t.setFecha(res.getDate(2));
                t.setNombreMedico(res.getString(3));
                t.setDiagnostico(res.getString(4));
                
                // Obtener paciente y vincularlo con el objeto a retornar
                DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
                Optional<Paciente> p = daoPaciente.get(res.getInt(5));
                if (p.isPresent()){
                    t.setPaciente(p.get());
                }
                
                // Obtener obra social elegida
                DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
                Optional<ObraSocial> os = daoObraSocial.get(res.getString(6));
                if (os.isPresent()){
                    t.setObraSocial(os.get());
                }
                
                // Agregar todos los analisis vinculados al turno que aparecen en la tabla "Turno_tiene_Analisis"
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO_TIENE_ANALISIS WHERE activo=1 AND Turno_nroOrdenServicio=?");
                stmAnalisis.setInt(1, nroOrdenServicio);
                ResultSet resAnalisis = stmAnalisis.executeQuery();  
                DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
                
                while(resAnalisis.next()){
                    Optional <Analisis> a = daoAnalisis.get(resAnalisis.getString(2));
                    if (a.isPresent()){
                        t.getAnalisis().add(a.get());
                    }
                    // Hacer uso del Dao de Analisis para recuperar el Analisis completo (consultando la tabla "Analisis_usa_Reactivo")
                }
                
                
                //Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                return Optional.ofNullable(t); 
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
    public List<Turno> getAll() {
        // Crea lista temporal para recorrer resultset
        List<Turno> turnos = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO WHERE activo=1");
            ResultSet res = stm.executeQuery();
            
            // Para la creacion del objeto paciente
            DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
            DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
//                Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                Turno t = new Turno();
                t.setNroOrdenServicio(res.getInt(1));
                t.setFecha(res.getDate(2));
                t.setNombreMedico(res.getString(3));
                t.setDiagnostico(res.getString(4));
                
                
                // Obtener paciente y vincularlo con el objeto a retornar
                Optional<Paciente> p = daoPaciente.get(res.getInt(5));
                if (p.isPresent()){
                    t.setPaciente(p.get());
                }
                
                // Obtener obra social elegida
                DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
                Optional<ObraSocial> os = daoObraSocial.get(res.getString(6));
                if (os.isPresent()){
                    t.setObraSocial(os.get());
                }

                // Obtener las obras sociales vinculadas a este paciente
//                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni =?");
//                stmOS.setInt(1, p.getDni());
//                ResultSet resOS = stmOS.executeQuery();   
//                while(resOS.next()){
//                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
//                }
                
                // Agregar todos los analisis vinculados al turno que aparecen en la tabla "Turno_tiene_Analisis"
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO_TIENE_ANALISIS WHERE activo=1 AND Turno_nroOrdenServicio=?");
                stmAnalisis.setInt(1, t.getNroOrdenServicio());
                ResultSet resAnalisis = stmAnalisis.executeQuery();  
                
                while(resAnalisis.next()){
                    // Hace uso del Dao de Analisis para recuperar el Analisis completo (consultando la tabla "Analisis_usa_Reactivo")
                    Optional <Analisis> a = daoAnalisis.get(resAnalisis.getString(2));
                    if (a.isPresent()){
                        t.getAnalisis().add(a.get());
                    }
                }
                turnos.add(t);
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener todos");
            System.out.println(e.toString());
        }
        
        return turnos;
    }

    public List<Turno> getByDate(String date){
        // Crea lista temporal para recorrer resultset
        List<Turno> turnos = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO WHERE activo=1 AND fecha='"+date+"'");
//            stm.setDate(1, date);
            ResultSet res = stm.executeQuery();
            
            // Para la creacion del objeto paciente
            DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
            DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
//                Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                Turno t = new Turno();
                t.setNroOrdenServicio(res.getInt(1));
                t.setFecha(res.getDate(2));
                t.setNombreMedico(res.getString(3));
                t.setDiagnostico(res.getString(4));
                
                
                // Obtener paciente y vincularlo con el objeto a retornar
                Optional<Paciente> p = daoPaciente.get(res.getInt(5));
                if (p.isPresent()){
                    t.setPaciente(p.get());
                }
                
                // Obtener obra social elegida
                DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
                Optional<ObraSocial> os = daoObraSocial.get(res.getString(6));
                if (os.isPresent()){
                    t.setObraSocial(os.get());
                }

                // Obtener las obras sociales vinculadas a este paciente
//                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni =?");
//                stmOS.setInt(1, p.getDni());
//                ResultSet resOS = stmOS.executeQuery();   
//                while(resOS.next()){
//                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
//                }
                
                // Agregar todos los analisis vinculados al turno que aparecen en la tabla "Turno_tiene_Analisis"
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO_TIENE_ANALISIS WHERE activo=1 AND Turno_nroOrdenServicio=?");
                stmAnalisis.setInt(1, t.getNroOrdenServicio());
                ResultSet resAnalisis = stmAnalisis.executeQuery();  
                
                while(resAnalisis.next()){
                    // Hace uso del Dao de Analisis para recuperar el Analisis completo (consultando la tabla "Analisis_usa_Reactivo")
                    Optional <Analisis> a = daoAnalisis.get(resAnalisis.getString(2));
                    if (a.isPresent()){
                        t.getAnalisis().add(a.get());
                    }
                }
                turnos.add(t);
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener por fecha");
            System.out.println(e.toString());
        }
        
        return turnos;
    }
    
    public List<Turno> getByDateWithHistory(String date){
        // Crea lista temporal para recorrer resultset
        List<Turno> turnos = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO WHERE fecha='"+date+"'");
//            stm.setDate(1, date);
            ResultSet res = stm.executeQuery();
            
            // Para la creacion del objeto paciente
            DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
            DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
//                Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                Turno t = new Turno();
                t.setNroOrdenServicio(res.getInt(1));
                t.setFecha(res.getDate(2));
                t.setNombreMedico(res.getString(3));
                t.setDiagnostico(res.getString(4));
                
                
                // Obtener paciente y vincularlo con el objeto a retornar
                Optional<Paciente> p = daoPaciente.get(res.getInt(5));
                if (p.isPresent()){
                    t.setPaciente(p.get());
                }
                
                // Obtener obra social elegida
                DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
                Optional<ObraSocial> os = daoObraSocial.get(res.getString(6));
                if (os.isPresent()){
                    t.setObraSocial(os.get());
                }

                // Obtener las obras sociales vinculadas a este paciente
//                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni =?");
//                stmOS.setInt(1, p.getDni());
//                ResultSet resOS = stmOS.executeQuery();   
//                while(resOS.next()){
//                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
//                }
                
                // Agregar todos los analisis vinculados al turno que aparecen en la tabla "Turno_tiene_Analisis"
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO_TIENE_ANALISIS WHERE activo=1 AND Turno_nroOrdenServicio=?");
                stmAnalisis.setInt(1, t.getNroOrdenServicio());
                ResultSet resAnalisis = stmAnalisis.executeQuery();  
                
                while(resAnalisis.next()){
                    // Hace uso del Dao de Analisis para recuperar el Analisis completo (consultando la tabla "Analisis_usa_Reactivo")
                    Optional <Analisis> a = daoAnalisis.get(resAnalisis.getString(2));
                    if (a.isPresent()){
                        t.getAnalisis().add(a.get());
                    }
                }
                turnos.add(t);
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener por fecha");
            System.out.println(e.toString());
        }
        
        return turnos;
    }
    
    
    public List<Turno> getByDNI(int dni){
        // Crea lista temporal para recorrer resultset
        List<Turno> turnos = new ArrayList<>();
        
        try{
            // Preparar Consulta
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO WHERE activo=1 AND Paciente_dni=?");
            stm.setInt(1, dni);
            ResultSet res = stm.executeQuery();
            
            // Para la creacion del objeto paciente
            DAOPacienteSQL daoPaciente = new DAOPacienteSQL();
            DAOAnalisisSQL daoAnalisis = new DAOAnalisisSQL();
            // Recorrer resultado de la consulta y convertirlo en una lista
            while(res.next()){
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
//                Paciente p = new Paciente(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getInt(5),res.getString(6),res.getString(7),res.getString(8));
                Turno t = new Turno();
                t.setNroOrdenServicio(res.getInt(1));
                t.setFecha(res.getDate(2));
                t.setNombreMedico(res.getString(3));
                t.setDiagnostico(res.getString(4));
                
                
                // Obtener paciente y vincularlo con el objeto a retornar
                Optional<Paciente> p = daoPaciente.get(res.getInt(5));
                if (p.isPresent()){
                    t.setPaciente(p.get());
                }
                
                // Obtener obra social elegida
                DAOObraSocialSQL daoObraSocial = new DAOObraSocialSQL();
                Optional<ObraSocial> os = daoObraSocial.get(res.getString(6));
                if (os.isPresent()){
                    t.setObraSocial(os.get());
                }

                // Obtener las obras sociales vinculadas a este paciente
//                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM PACIENTE_TIENE_OBRASOCIAL WHERE activo=1 AND Paciente_dni =?");
//                stmOS.setInt(1, p.getDni());
//                ResultSet resOS = stmOS.executeQuery();   
//                while(resOS.next()){
//                    p.getObrasSociales().add(new ObraSocial(resOS.getString(2)));
//                }
                
                // Agregar todos los analisis vinculados al turno que aparecen en la tabla "Turno_tiene_Analisis"
                PreparedStatement stmAnalisis = DataBaseSingleton.getInstance().getConnection().prepareStatement("SELECT * FROM TURNO_TIENE_ANALISIS WHERE activo=1 AND Turno_nroOrdenServicio=?");
                stmAnalisis.setInt(1, t.getNroOrdenServicio());
                ResultSet resAnalisis = stmAnalisis.executeQuery();  
                
                while(resAnalisis.next()){
                    // Hace uso del Dao de Analisis para recuperar el Analisis completo (consultando la tabla "Analisis_usa_Reactivo")
                    Optional <Analisis> a = daoAnalisis.get(resAnalisis.getString(2));
                    if (a.isPresent()){
                        t.getAnalisis().add(a.get());
                    }
                }
                turnos.add(t);
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener por fecha");
            System.out.println(e.toString());
        }
        
        return turnos;
    }
    
    @Override
    public void save(Turno t) {
        try{
            // Preparar Insercion del paciente
            // Nota: Activo es la variable para el eliminado logico
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO TURNO VALUES (?,?,?,?,?,?,1);");
            stm.setInt(1, t.getNroOrdenServicio());
            stm.setDate(2, t.getFecha());
            stm.setString(3, t.getNombreMedico());
            stm.setString(4, t.getDiagnostico());
            stm.setInt(5, t.getPaciente().getDni());
            stm.setString(6, t.getObraSocial().getNombre());
            
            stm.execute();
            
            
            
            // Itera sobre la lista de obras sociales del paciente
            for(Analisis a : t.getAnalisis()){
                // Prepara Insercion en la tabla que relaciona un paciente con una obra social (Paciente_tiene_obraSocial)
                PreparedStatement stmOS = DataBaseSingleton.getInstance().getConnection().prepareStatement("INSERT INTO TURNO_TIENE_ANALISIS VALUES (?,?,1);");
                stmOS.setInt(1, t.getNroOrdenServicio());
                stmOS.setString(2, a.getNombre());
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
    public void update(Turno t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer nroOrdenServicio) {
        try{
            // Preparar Eliminacion (Logica)
            PreparedStatement stm = DataBaseSingleton.getInstance().getConnection().prepareStatement("UPDATE TURNO SET activo=0 WHERE nroOrdenServicio=?");
            stm.setInt(1, nroOrdenServicio);

            stm.execute();
        }
        catch(SQLException e){
            System.out.println("Error al eliminar");
            System.out.println(e.toString());
        }
    }
    
}
