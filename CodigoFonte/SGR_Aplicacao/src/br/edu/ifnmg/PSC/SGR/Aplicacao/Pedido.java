/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

import java.util.Date;

/**
 *
 * @author DONO
 */
public class Pedido extends Acontecimento {
    protected Date dataRealizado;
    protected float diferenca;
    protected int tipo;

    public Pedido() {
    }

    public Date getDataRealizado() {
        return dataRealizado;
    }

    public void setDataRealizado(Date dataRealizado) {
        this.dataRealizado = dataRealizado;
    }

    public float getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(float diferenca) {
        this.diferenca = diferenca;
    }
    
}
