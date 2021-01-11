/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

import java.util.Objects;

/**
 *
 * @author DONO
 */
public class Fornecedor extends Individuo implements ReferenciaUsuario{
    private String cnpj;
    private int usuario;
    
    public Fornecedor() {
    }

    @Override
    public int getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     * @throws br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException
     */
    public void setCnpj(String cnpj) throws ViolacaoRegraNegocioException { 
        if((cnpj.length()!= 14))
            throw new ViolacaoRegraNegocioException("CNPJ deve conter 14 digitos");
        this.cnpj = cnpj; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cnpj);
        return hash;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (this.cnpj != other.cnpj) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "cnpj=" + cnpj + " " + super.toString() + '}';
    }

    
}
