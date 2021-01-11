/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

import java.util.Objects;

/**
 *
 * @author Alucard
 */
public class Cliente extends Individuo implements ReferenciaUsuario{
    private String cpf;
    private Endereco endereco;
    private String obs;
    private int usuario;

    public Cliente() {
        this.endereco = new Endereco();
    }

    @Override
    public int getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ViolacaoRegraNegocioException { 
        if((cpf.length()!= 11))
            throw new ViolacaoRegraNegocioException("CPF deve conter 11 digitos");
        this.cpf = cpf; 
    }
    
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) throws ViolacaoRegraNegocioException {
        if((obs!=null&&!obs.isEmpty())&&(obs.length() > 400))
            throw new ViolacaoRegraNegocioException("Maximo: 400 caracteres");        
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.cpf);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }    
    
    @Override
    public String toString() {
        return "Cliente{" + "cpf=" + cpf + " " + super.toString() +  ", endereco=" + endereco + ", obs=" + obs + '}';
    }
    
}
