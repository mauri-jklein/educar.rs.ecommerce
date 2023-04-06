/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ItemPedido;
import entity.Pedido;
import repository.ItemPedidoRepository;
import repository.PedidoRepository;

/**
 *
 * @author Mauri
 */
public class PedidoService {
    
    private final ItemPedidoRepository itemPedidoRepository = new ItemPedidoRepository();
    private final PedidoRepository pedidoRepository = new PedidoRepository();
    
    public Pedido salvarPedido(Pedido pedido){
        double valorTotal = 0;
        for(ItemPedido ip: pedido.getItensPedido()){
            valorTotal+=ip.getValor();
        }
        pedido.setValorTotal(valorTotal);
        Pedido pedidoSalvo = pedidoRepository.salvarPedido(pedido);
        for(ItemPedido ip: pedido.getItensPedido()){
            this.itemPedidoRepository.salvarItemPedido(ip, pedidoSalvo.getId());
        }
       return pedidoSalvo;
    }
    
    
}
