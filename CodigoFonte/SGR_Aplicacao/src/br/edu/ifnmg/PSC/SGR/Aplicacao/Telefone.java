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
public class Telefone implements Entidade{
    String telefone;
    Integer individuo;
    int id;

    public Telefone() {
    }

    public Telefone(String telefone, Integer individuo, int id) throws ViolacaoRegraNegocioException {
        setTelefone(telefone);
        this.individuo = individuo;
        this.id = id;
    }

    public Telefone(String telefone, Integer individuo) throws ViolacaoRegraNegocioException {
        setTelefone(telefone);
        this.individuo = individuo;
    }
    
    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }

    public Integer getIndividuo() {
        return individuo;
    }

    public void setIndividuo(int individuo) {
        this.individuo = individuo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws ViolacaoRegraNegocioException {
        if((telefone.length() < 10)||(telefone.length() > 11))
            throw new ViolacaoRegraNegocioException("Telefone deve conter entre 10 e 11 digitos");
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.telefone);
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
        final Telefone other = (Telefone) obj;
        if (this.telefone != other.telefone) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return telefone;
    }

}

