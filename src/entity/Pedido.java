/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauri
 */
public class Pedido {
    private int id;
    private LocalDate data;
    private Double valorTotal;
    private Cliente cliente;
    private List<ItemPedido> itensPedido;

    public Pedido() {
        itensPedido = new ArrayList<>();
    }

    public Pedido(int id, LocalDate data, Double valorTotal, Cliente cliente, List<ItemPedido> itensPedido) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itensPedido = itensPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" + "valorTotal=" + valorTotal + ", cliente=" + cliente + ", itensPedido=" + itensPedido + '}';
    }


    
    
    
}
