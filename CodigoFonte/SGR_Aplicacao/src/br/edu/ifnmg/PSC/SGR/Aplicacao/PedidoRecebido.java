/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

/**
 *
 * @author DONO
 */
public class PedidoRecebido extends Pedido{
    private Cliente cliente;
    private int enderecoEntrega;

    public PedidoRecebido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(int endereco) {
        this.enderecoEntrega = endereco;
    }
   
}