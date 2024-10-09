/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

/**
 *
 * @author jerem
 */
public class Reactivo {
    private String nombre;
    private float cantidadRecipiente;

    public Reactivo(String nombre, float cantidadRecipiente) {
        this.nombre = nombre;
        this.cantidadRecipiente = cantidadRecipiente;
    }

    public float getCantidadRecipiente() {
        return cantidadRecipiente;
    }

    public void setCantidadRecipiente(float cantidadRecipiente) {
        this.cantidadRecipiente = cantidadRecipiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Reactivo{" + "nombre=" + nombre + ", cantidadRecipiente=" + cantidadRecipiente + '}';
    }
    
}
