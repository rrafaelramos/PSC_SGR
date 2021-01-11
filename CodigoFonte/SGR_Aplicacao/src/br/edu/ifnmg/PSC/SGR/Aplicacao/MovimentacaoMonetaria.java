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
public class MovimentacaoMonetaria<T extends MovimentacaoMonetaria> extends Acontecimento {
    protected float valor;
    
    public MovimentacaoMonetaria() {
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "MovimentacaoMonetaria{" + "valor=" + valor + '}';
    }
    
}
