/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

/**
 *
 * @author jerem
 */
public class ObraSocial {
    private String nombre;

    public ObraSocial(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ObraSocial{" + "nombre=" + nombre + '}';
    }
    
    
}
