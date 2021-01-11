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
public class Individuo implements Entidade {
    
    protected int id;
    protected String nome;
    
    public Individuo() {
    }

    public Individuo(int id) {
        this.id = id;
    }

    public Individuo(String nome) {
        this.nome = nome;
    }

    @Override 
    public int getId() {
        return id;
    }

    @Override 
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ViolacaoRegraNegocioException {
        if(nome.length() < 2)
            throw new ViolacaoRegraNegocioException("O nome deve ser maior do que 1 caractere");
 
        if(nome.length()>50)
            throw new ViolacaoRegraNegocioException("Máximo: 50 caraqcteres");                
        
        this.nome = nome;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
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
        final Individuo other = (Individuo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Individuo{" + "nome=" + nome + '}';
    }

}
