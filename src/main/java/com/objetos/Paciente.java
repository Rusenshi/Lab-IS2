/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

import java.util.ArrayList;

/**
 *
 * @author jerem
 */
public class Paciente {
    private String nombre;
    private String apellido;
    private int dni;
    private int sexo;
    private int edad;
    private String domicilio;
    private String telefono;
    private String correoElectronico;
    private ArrayList<ObraSocial> obrasSociales; // Para ver si es particular ver si esta lista está vacia

    public Paciente(int dni, String nombre, String apellido, int sexo, int edad, String domicilio, String telefono, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sexo = sexo;
        this.edad = edad;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.obrasSociales = new ArrayList<ObraSocial>();
    }
    
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public int getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public ArrayList<ObraSocial> getObrasSociales() {
        return obrasSociales;
    }

    public void setObrasSociales(ArrayList<ObraSocial> obrasSociales) {
        this.obrasSociales = obrasSociales;
    }
    
    
    
    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", sexo=" + sexo + ", edad=" + edad + ", domicilio=" + domicilio + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", obrasSociales=" + obrasSociales + '}';
    }

    
    
    
}
