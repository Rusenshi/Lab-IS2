/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author jerem
 */
public class Turno {
    private int nroOrdenServicio;
    private Paciente paciente;
    private Date fecha;
    private String nombreMedico;
    private String diagnostico;
    private Set<Analisis> analisis;

    public Turno() {
    }

    public Turno(int nroOrdenServicio, Paciente paciente, Date fecha, String nombreMedico, String diagnostico, Set<Analisis> analisis) {
        this.nroOrdenServicio = nroOrdenServicio;
        this.paciente = paciente;
        this.fecha = fecha;
        this.nombreMedico = nombreMedico;
        this.diagnostico = diagnostico;
        this.analisis = analisis;
    }

    public int getNroOrdenServicio() {
        return nroOrdenServicio;
    }

    public void setNroOrdenServicio(int nroOrdenServicio) {
        this.nroOrdenServicio = nroOrdenServicio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Set<Analisis> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(Set<Analisis> analisis) {
        this.analisis = analisis;
    }
    
    
    
}
