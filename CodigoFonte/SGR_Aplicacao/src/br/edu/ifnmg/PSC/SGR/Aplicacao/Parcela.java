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
public class Parcela extends MovimentacaoMonetaria{
    private int pedido;

    public Parcela() {
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Parcela{" + "pedido=" + pedido + '}';
    }
    
}
