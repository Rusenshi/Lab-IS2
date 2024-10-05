/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.helloworld.ejemplopostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jerem
 */
public class DataBaseSingleton {
    // Valores para la conexión a la base de datos (su nombre, URL, Usuario y Contraseña)
    // Nota: crear una base de datos en pgadmin con el nombre de DB_NAME para poder conectarse
    private static final String DB_NAME = "lab-is2";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres"; // Revisar estos nombres, son diferentes en mi pc
    private static final String DB_PWD = "admin";
    
    
    
    private Connection conn;
    private static Statement query = null;
    private static PreparedStatement p_query = null;
    private static ResultSet result = null;
    private static DataBaseSingleton dataBaseInstance;

    // Sin parametros
    public static Statement getInstance() {

        if (dataBaseInstance == null) {
            dataBaseInstance = new DataBaseSingleton(DB_URL, DB_USER, DB_PWD);
        }
        return query;
    }

    private DataBaseSingleton(String DB_URL, String DB_USER, String DB_PWD) {
        try{
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            query = conn.createStatement();
        }
        catch(SQLException e){
            System.out.println("Error al iniciar la base de datos");
            conn = null;
        }
    }
    
    public Connection getDBConnection(){
        return conn;
    }
    
    public Statement getDBQuery(){
        return query;
    }
}



