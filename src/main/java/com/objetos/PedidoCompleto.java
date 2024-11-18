/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.objetos;

import java.util.List;

/**
 *
 * @author Ideapad Gaming 3
 */
public class PedidoCompleto {
    private int idPedidoCompleto;
    private List<PedidoIndividual> pedidos;
    private float precioTotal;
    private Proveedor proveedor;

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
    public int getIdPedidoCompleto() {
        return idPedidoCompleto;
    }

    public void setIdPedidoCompleto(int idPedidoCompleto) {
        this.idPedidoCompleto = idPedidoCompleto;
    }
    
    public List<PedidoIndividual> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoIndividual> pedidos) {
        this.pedidos = pedidos;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
    
}
