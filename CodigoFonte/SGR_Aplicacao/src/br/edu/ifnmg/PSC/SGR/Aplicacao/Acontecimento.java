/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

import java.util.Date;

/**
 *
 * @author DONO
 */
public class Acontecimento implements ReferenciaUsuario {
    
    protected int id;
    protected Date dataPrevisto;
    protected Status status;
    protected int usuario;
    protected String obs;

    public Acontecimento() {
    }

    @Override 
    public int getId() {
        return id;
    }

    @Override 
    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPrevisto() {
        return dataPrevisto;
    }

    public void setDataPrevisto(Date dataPrevisto) {
        this.dataPrevisto = dataPrevisto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getObs(){
        return obs;
    }

    public void setObs(String obs) throws ViolacaoRegraNegocioException {
        if((obs!=null&&!obs.isEmpty())&&(obs.length() > 400))
            throw new ViolacaoRegraNegocioException("Maximo: 400 caracteres");
        
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Acontecimento other = (Acontecimento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
