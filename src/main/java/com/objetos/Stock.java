/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

import java.sql.Date;

/**
 *
 * @author jerem
 */
public class Stock {
    private int nroLote;
    private int cantidad;
    private Date fechaVencimiento;
    private Reactivo reactivo;

    public Stock(int nroLote, int cantidad, Date fechaVencimiento, Reactivo reactivo) {
        this.nroLote = nroLote;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.reactivo = reactivo;
    }

    public int getNroLote() {
        return nroLote;
    }

    public void setNroLote(int nroLote) {
        this.nroLote = nroLote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Reactivo getReactivo() {
        return reactivo;
    }

    public void setReactivo(Reactivo reactivo) {
        this.reactivo = reactivo;
    }

    @Override
    public String toString() {
        return "Stock{" + "nroLote=" + nroLote + ", cantidad=" + cantidad + ", fechaVencimiento=" + fechaVencimiento + ", reactivo=" + reactivo + '}';
    }
    
    
}
