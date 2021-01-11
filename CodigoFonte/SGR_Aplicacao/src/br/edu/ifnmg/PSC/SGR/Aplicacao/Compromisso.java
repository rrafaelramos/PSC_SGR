/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alucard
 */
public class Compromisso extends Acontecimento{
    
    private Date dataTermino;
    private Endereco endereco;
    private TipoCompromisso tipo;
    List<Integer> ListaIndividuos=new ArrayList<>();

    public Compromisso() {
        this.endereco = new Endereco();
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoCompromisso getTipo() {
        return tipo;
    }

    public void setTipo(TipoCompromisso tipo) {
        this.tipo = tipo;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) throws ViolacaoRegraNegocioException {
        if(dataTermino.getTime()<=dataPrevisto.getTime())
            throw new ViolacaoRegraNegocioException("A data de termino tem de ser maior que a data prevista");
        this.dataTermino = dataTermino;
    }

    public List<Integer> getListaIndividuos() {
        return ListaIndividuos;
    }

    public void setListaIndividuos(List<Integer> ListaIndividuos) {
        this.ListaIndividuos = ListaIndividuos;
    }
    
    public void addIndividuo(Integer individuo){
        this.ListaIndividuos.add(individuo);
    }
    
    public void removeIndividuo(Integer individuo){
        this.ListaIndividuos.remove(individuo);
    }
    
    @Override
    public String toString() {
        return "Compromisso{" + "dataTermino=" + dataTermino + '}';
    }
    
}
