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
public class Email implements Entidade{
    String email;
    Integer individuo;
    int id;

    public Email() {
    }

    public Email(String email, Integer individuo, int id) throws ViolacaoRegraNegocioException {
        setEmail(email);
        this.individuo = individuo;
        this.id = id;
    }

    public Email(String email, Integer individuo) throws ViolacaoRegraNegocioException {
        setEmail(email);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ViolacaoRegraNegocioException {
        if(!email.contains("@")||(!email.contains(".com")))
            throw new ViolacaoRegraNegocioException("Email deve conter '@' e '.com'");
        if(email.length()>50)
            throw new ViolacaoRegraNegocioException("Máximo: 50 caraqcteres");
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.email);
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
        final Email other = (Email) obj;
        if (this.email != other.email) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return email;
    }

}
