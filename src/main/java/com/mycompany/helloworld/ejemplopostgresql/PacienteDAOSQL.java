/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.helloworld.ejemplopostgresql;

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
public class PacienteDAOSQL implements Dao<Paciente>{
    // Capa de persistencia
    private List<Paciente> pacientes = new ArrayList<>();
    
    public PacienteDAOSQL() {
        pacientes.add(new Paciente("jess","ieojde",1,1,1,"","",""));
        pacientes.add(new Paciente("jess33","i223eojde",3,1,1,"","",""));
        
    }

    @Override
    public Optional<Paciente> get(long id) {
        
        try{
            ResultSet res = DataBaseSingleton.getInstance().executeQuery("SELECT * FROM PACIENTES WHERE id="+id);
            while(res.next()){
                pacientes.add(new Paciente(res.getString(0),res.getString(0),res.getInt(0),res.getInt(0),res.getInt(0),res.getString(0),res.getString(0),res.getString(0)));
                // Obtiene los campos de cada columna de la base de datos y crea el objeto.
            }
        }
        catch(SQLException e){
            System.out.println("no se pudo conectar con la base de datos");
        }
        
        
        return Optional.ofNullable(pacientes.get((int) id));
    }

    @Override
    public List<Paciente> getAll() {
        return pacientes;
    }

    @Override
    public void save(Paciente t) {
        pacientes.add(t); // Desglosar y convertir en sql
    }

    @Override
    public void update(Paciente t, String[] params) {
        t.setNombre(Objects.requireNonNull(
          params[0], "Name cannot be null"));
        t.setCorreoElectronico(Objects.requireNonNull(
          params[1], "Email cannot be null"));
        
        pacientes.add(t);
    }

    @Override
    public void delete(Paciente t) {
        pacientes.remove(t);
    }
    
}
