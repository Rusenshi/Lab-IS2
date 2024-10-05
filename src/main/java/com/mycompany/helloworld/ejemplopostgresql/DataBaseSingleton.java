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
    private Connection conn;
    private static Statement query = null;
    private static PreparedStatement p_query = null;
    private static ResultSet result = null;
    private static DataBaseSingleton dataBaseInstance;

    // Sin parametros
    public static Statement getInstance() {

        if (dataBaseInstance == null) {
            dataBaseInstance = new DataBaseSingleton("jdbc:postgresql://localhost:5432/prueba", "postgres", "admin");
        }
        return query;
    }
    
    
    
    
    // Con parametros
    public static DataBaseSingleton getInstance(String DB_URL, String DB_USER, String DB_PWD) {

        if (dataBaseInstance == null) {
            dataBaseInstance = new DataBaseSingleton(DB_URL, DB_USER, DB_PWD);
        }
        return dataBaseInstance;
    }

    private DataBaseSingleton(String DB_URL, String DB_USER, String DB_PWD) {
        try{
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            query = conn.createStatement();
        }
        catch(SQLException e){
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



