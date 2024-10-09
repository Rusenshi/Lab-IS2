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
public class Analisis {
    private String nombre;
//    private Turno turno;
    private float valorReferencia;
    private String metodoUsado;
    private float monto;
    private ArrayList<Reactivo> reactivosUsados;

    public Analisis(String nombre, float valorReferencia, String metodoUsado, float monto) {
        this.nombre = nombre;
//        this.turno = turno;
        this.valorReferencia = valorReferencia;
        this.metodoUsado = metodoUsado;
        this.monto = monto;
        this.reactivosUsados = new ArrayList<Reactivo>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//
//    public Turno getTurno() {
//        return turno;
//    }
//
//    public void setTurno(Turno turno) {
//        this.turno = turno;
//    }

    public float getValorReferencia() {
        return valorReferencia;
    }

    public void setValorReferencia(float valorReferencia) {
        this.valorReferencia = valorReferencia;
    }

    public String getMetodoUsado() {
        return metodoUsado;
    }

    public void setMetodoUsado(String metodoUsado) {
        this.metodoUsado = metodoUsado;
    }

    public ArrayList<Reactivo> getReactivosUsados() {
        return reactivosUsados;
    }

    public void setReactivosUsados(ArrayList<Reactivo> reactivosUsados) {
        this.reactivosUsados = reactivosUsados;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Analisis{" + "nombre=" + nombre + ", valorReferencia=" + valorReferencia + ", metodoUsado=" + metodoUsado + ", monto=" + monto + ", reactivosUsados=" + reactivosUsados + '}';
    }
    
    
}
