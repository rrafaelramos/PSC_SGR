/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

/**
 *
 * @author petronio
 */
public enum Status {
    PENDENTE(0,"Pendente"),
    REALIZADO(1,"Realizado");
    
    private int id;
    private String descricao;
    
    private Status(int id, String desc){
        this.id = id;
        this.descricao = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    @Override
    public String toString() {
        return descricao;
    }
    
    public static Status Abrir(int id){
        for(Status t : Status.values())
            if(id == t.getId())
                return t;
        return null;
    }    
}
