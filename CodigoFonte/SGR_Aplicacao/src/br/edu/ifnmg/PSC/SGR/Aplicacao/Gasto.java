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
public class Gasto extends MovimentacaoMonetaria<Gasto>{
    private String descricao;

    public Gasto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws ViolacaoRegraNegocioException {
        if(descricao.length()>50)
            throw new ViolacaoRegraNegocioException("Máximo: 50 caraqcteres");        
        this.descricao = descricao;
    }
    
}
