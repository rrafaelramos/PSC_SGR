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
public class Endereco implements Entidade {

    private int id;
    private String rua;
    private int num;
    private String bairro;
    private String cidade;
    private Uf uf;
    private String complemento;
    
    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) throws ViolacaoRegraNegocioException { 
        if(rua.length()>50)
            throw new ViolacaoRegraNegocioException("Máximo: 50 caraqcteres");        
        this.rua = rua;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) throws ViolacaoRegraNegocioException { 
        if(num < 0)
            throw new ViolacaoRegraNegocioException("O número deve ser maior que 0");
        if(num > 99999)
            throw new ViolacaoRegraNegocioException("O número deve ser menor que 99999");
        this.num = num;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro)  throws ViolacaoRegraNegocioException { 
        if(bairro.length()>50)
            throw new ViolacaoRegraNegocioException("Máximo: 50 caraqcteres");        
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade)  throws ViolacaoRegraNegocioException { 
        if(cidade.length()>50)
            throw new ViolacaoRegraNegocioException("Máximo: 50 caraqcteres");        
        this.cidade = cidade;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento)  throws ViolacaoRegraNegocioException { 
        if(complemento.length()>200)
            throw new ViolacaoRegraNegocioException("Máximo: 200 caraqcteres");        
        this.complemento = complemento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Endereco other = (Endereco) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
