/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

import java.util.Hashtable;

/**
 *
 * @author jerem
 */
public class Informe {
    private int nroOrdenServicio;
    private String medioElectronico;
    private Tecnico tecnico;
    private Hashtable<Analisis,Float> resultados;

    public Informe(int nroOrdenServicio) {
        this.nroOrdenServicio = nroOrdenServicio;
        this.medioElectronico = "2";
        this.tecnico = new Tecnico();
        this.resultados = new Hashtable<>();
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    

    public int getNroOrdenServicio() {
        return nroOrdenServicio;
    }

    public void setNroOrdenServicio(int nroOrdenServicio) {
        this.nroOrdenServicio = nroOrdenServicio;
    }

    public String getMedioElectronico() {
        return medioElectronico;
    }

    public void setMedioElectronico(String medioElectronico) {
        this.medioElectronico = medioElectronico;
    }

    public Hashtable<Analisis, Float> getResultados() {
        return resultados;
    }

    public void setResultados(Hashtable<Analisis, Float> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return "Informe{" + "nroOrdenServicio=" + nroOrdenServicio + ", medioElectronico=" + medioElectronico + ", tecnico=" + tecnico + ", resultados=" + resultados + '}';
    }
    
    
    
}
