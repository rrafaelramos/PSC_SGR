/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Apresentacao;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Entidade;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Repositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Sessao;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author petronio
 */
public abstract class TelaBusca<T extends Entidade> extends javax.swing.JInternalFrame {
    
    Repositorio<T> repositorio;
    
    TelaEdicao<T> tela_edicao;
    
    Class tipo_tela;
    
    HashMap<String,String> filtroPreDeterminado= new HashMap<>();
    
    public abstract int retornaIdSelecionado() throws ViolacaoRegraNegocioException;
    
    public abstract HashMap<String,String> preencheFiltro(HashMap<String,String> filtroPreDeterminado); //pega o valor dos campos e retorna um filtro
    
    public abstract void preencheTabela(List<T> listagem);

    public TelaBusca(Repositorio<T> repositorio, Class tipo_tela) {
        this.repositorio = repositorio;
        this.tipo_tela = tipo_tela;
        this.setClosable(true);
        this.setMaximizable(true);
    }
    
    public TelaBusca(Repositorio<T> repositorio, Class tipo_tela, HashMap<String,String> filtroPreDeterminado) {
        this.repositorio = repositorio;
        this.tipo_tela = tipo_tela;
        this.setClosable(true);
        this.setMaximizable(true);
        this.filtroPreDeterminado=filtroPreDeterminado;
    }    
    
    public void buscar() {
        
        List<T> listagem = null;
        try {
            HashMap<String,String> filtro;
            filtro = preencheFiltro(filtroPreDeterminado);
            listagem = repositorio.Buscar(filtro);
        } catch (ViolacaoRegraNegocioException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        
        preencheTabela(listagem);
    }
    
    public void cadastrar() {
        
        try {
            
            tela_edicao = (TelaEdicao<T>) tipo_tela.newInstance();
            
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TelaBusca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tela_edicao.setRepositorio(repositorio);
        
        tela_edicao.setCampos(preencheFiltro(filtroPreDeterminado));  

        this.getParent().add(tela_edicao);
        
        tela_edicao.setTelaMae(this);
        
        tela_edicao.centraliza();
        
        tela_edicao.setVisible(true);
        
    }
    
    public void editar(){
        
        T filtro = null;
        try {

            if(retornaIdSelecionado()==-1)
                throw new ViolacaoRegraNegocioException("Nenhum registro selecionado.");
            
            filtro = repositorio.Abrir(retornaIdSelecionado());

            tela_edicao = (TelaEdicao<T>) tipo_tela.newInstance();

            tela_edicao.setRepositorio(repositorio);

            tela_edicao.setCampos(filtro);

            this.getParent().add(tela_edicao);

            tela_edicao.setTelaMae(this);

            tela_edicao.centraliza();

            tela_edicao.setVisible(true);

        } catch (ViolacaoRegraNegocioException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(TelaBusca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void centraliza() {
        Dimension d = this.getParent().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
    }
    
}
