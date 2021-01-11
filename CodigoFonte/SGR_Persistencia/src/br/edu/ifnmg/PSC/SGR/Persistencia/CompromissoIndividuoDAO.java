/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Compromisso;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Sessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DONO
 */
public class CompromissoIndividuoDAO {
    
    private Connection conexao;
    private PreparedStatement sql;
    
    public CompromissoIndividuoDAO(Connection conexao) {
        this.conexao=conexao;
    }

    public boolean Salvar(Compromisso compromisso){
        try {
            if(Deletar(compromisso))
                for(Integer ind : compromisso.getListaIndividuos()){
                    sql = conexao.prepareStatement("insert into CompromissoIndividuo(compromisso,individuo) values(" + compromisso.getId() + "," + ind.toString() + ")");

                    if (!(sql.executeUpdate() > 0))
                        return false;

                }
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(CompromissoIndividuoDAO.class.getName()).log(Level.SEVERE, null, ex);
            Deletar(compromisso);
        }
        
        return true;
    }
    
    private boolean Deletar(Compromisso compromisso){
        try {
            sql = conexao.prepareStatement("delete from CompromissoIndividuo where compromisso ="+compromisso.getId());
            
            if (!(sql.executeUpdate() > 0))
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(CompromissoIndividuoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void Buscar(Compromisso compromisso){
        try {
            
            String sqlFinal = "select * from CompromissoIndividuo where compromisso="+compromisso.getId();
            
            sql = conexao.prepareStatement( sqlFinal );
                       
            ResultSet resultado = sql.executeQuery();
            
            while(resultado.next())
                compromisso.addIndividuo( resultado.getInt("individuo") );
            
        } catch (SQLException ex) {
            Logger.getLogger(EntidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
