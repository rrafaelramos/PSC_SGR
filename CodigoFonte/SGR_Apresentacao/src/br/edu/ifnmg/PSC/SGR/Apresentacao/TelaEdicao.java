/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Apresentacao;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Entidade;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Repositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author petronio
 */
public abstract class TelaEdicao<T extends Entidade> extends javax.swing.JInternalFrame {
    Repositorio<T> repositorio;
    
    T paraSalvar;
    
    private TelaBusca<T> telaMae=null;

    public TelaBusca<T> getTelaMae() {
        return telaMae;
    }

    public void setTelaMae(TelaBusca<T> telaMae) {
        this.telaMae = telaMae;
    }
    
    public abstract void carregaObjeto() throws ViolacaoRegraNegocioException;//pega os campos e seta em "paraSalvar"
    
    public abstract void setCampos(HashMap<String, String> filtro);// carrega os campos com o filtro, caso necessario seta "paraSalvar"
    
    public abstract void setCampos(T filtro);// carrega os campos do registro selecionado na tabela, caso necessario seta "paraSalvar"

    public TelaEdicao() {
        this.setClosable(true);
        this.setMaximizable(true);
    }

    
    public T getEntidade() {
        return paraSalvar;
    }

    public Repositorio<T> getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio<T> repositorio) {
        this.repositorio = repositorio;
    }
    
    public void salvar(boolean fechar){

        if(JOptionPane.showConfirmDialog(this, "Deseja realmente salvar?", "Salvar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            try {
                carregaObjeto();
                
            } catch (ViolacaoRegraNegocioException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                return; 
            }
            
            try {
                if(repositorio.Salvar(paraSalvar)){
                    if(telaMae!=null)
                        telaMae.buscar();
                    JOptionPane.showMessageDialog(rootPane, "Sucesso!");
                    if(fechar)
                        try {
                            this.setClosed(true);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Falha ao salvar o registro!");
                }
            } catch (ViolacaoRegraNegocioException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }
    
    public void deletar(){
        if(JOptionPane.showConfirmDialog(this, "Deseja realmente deletar?", "Deletar", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            try {
                if(repositorio.Deletar(paraSalvar)){
                    if(telaMae!=null)
                        telaMae.buscar();                                   
                    JOptionPane.showMessageDialog(rootPane, "Sucesso!");
                    try {
                        this.setClosed(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(TelaEdicao.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Falha ao remover o registro!");
                }
            } catch (ViolacaoRegraNegocioException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }
    
    public void centraliza() {
        Dimension d = this.getParent().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
    }
    
}
