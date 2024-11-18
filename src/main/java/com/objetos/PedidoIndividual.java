/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

/**
 *
 * @author Ideapad Gaming 3
 */
public class PedidoIndividual {
    private int idPedidoCompleto;
    
    private Reactivo reactivo;
    private float precioPorUnidad;
    
    private float cantidad;

    public int getIdPedidoCompleto() {
        return idPedidoCompleto;
    }

    public void setIdPedidoCompleto(int idPedidoCompleto) {
        this.idPedidoCompleto = idPedidoCompleto;
    }
    
    public Reactivo getReactivo() {
        return reactivo;
    }

    public void setReactivo(Reactivo reactivo) {
        this.reactivo = reactivo;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(float precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }
    
    
}
