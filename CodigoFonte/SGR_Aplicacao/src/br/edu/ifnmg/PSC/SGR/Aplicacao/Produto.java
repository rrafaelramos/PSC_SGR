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
public class Produto implements ReferenciaUsuario{
    private int id;
    private String descricao;
    private int estoque;
    private float custo;
    protected int usuario;
    private float lucro;

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao)throws ViolacaoRegraNegocioException { 
        if((descricao.length() < 2 ))
            throw new ViolacaoRegraNegocioException("O campo descrição deve conter pelo menos 2 caracteres");
        if((descricao.length() > 50 ))
            throw new ViolacaoRegraNegocioException("O campo descrição deve conter menos que 51 caracteres");
        this.descricao = descricao; 
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) throws ViolacaoRegraNegocioException { 
        if(estoque < 0)
            throw new ViolacaoRegraNegocioException("Estoque negotivo impossibilitado");
        this.estoque = estoque ; 
    }
    
    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public float getLucro() {
        return lucro;
    }

    public void setLucro(float lucro) {
        this.lucro = lucro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + this.estoque;
        hash = 59 * hash + Float.floatToIntBits(this.custo);
        hash = 59 * hash + Float.floatToIntBits(this.lucro);
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.estoque != other.estoque) {
            return false;
        }
        if (Float.floatToIntBits(this.custo) != Float.floatToIntBits(other.custo)) {
            return false;
        }
        if (Float.floatToIntBits(this.lucro) != Float.floatToIntBits(other.lucro)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", descricao=" + descricao + ", estoque=" + estoque + ", custo=" + custo + ", lucro=" + lucro + '}';
    }

}
