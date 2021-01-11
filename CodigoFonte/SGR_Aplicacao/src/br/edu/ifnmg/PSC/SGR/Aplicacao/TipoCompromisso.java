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
public enum TipoCompromisso {
    GERAL(0,"Geral"),
    REUNIAO(1,"Reunião"),
    VISITA(2, "Visita"),
    OUTRO(3, "Outro");
    
    private int id;
    private String descricao;
    
    private TipoCompromisso(int id, String desc){
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
    
    public static TipoCompromisso Abrir(int id){
        for(TipoCompromisso t : TipoCompromisso.values())
            if(id == t.getId())
                return t;
        return null;
    }    
}
