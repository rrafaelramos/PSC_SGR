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
public enum Uf {
    AC(0,"AC"),
    AL(1,"AL"),
    AM(2,"AM"),
    AP(3,"AF"),
    BA(4,"BA"),
    CE(5,"CE"),
    DF(6,"DF"),
    ES(7,"ES"),
    GO(8,"GO"),
    MA(9,"MA"),
    MG(10,"MG"),
    MS(12,"MS"),
    MT(13,"MT"),
    PA(14,"PA"),
    PB(15,"PB"),
    PE(16,"PE"),
    PI(17,"PI"),
    PR(18,"PR"),
    RJ(19,"RJ"),
    RN(20,"RN"),
    RO(21,"RO"),
    RR(22,"RR"),
    RS(23,"RS"),
    SC(24,"SC"),
    SE(25,"SE"),
    SP(26,"SP"),
    TO(27,"TC");
    
    private int id;
    private String descricao;
    
    private Uf(int id, String desc){
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
    
    public static Uf Abrir(int id){
        for(Uf t : Uf.values())
            if(id == t.getId())
                return t;
        return null;
    }    
}
