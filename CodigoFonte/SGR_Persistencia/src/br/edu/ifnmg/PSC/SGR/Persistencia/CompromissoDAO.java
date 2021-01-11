/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Compromisso;
import br.edu.ifnmg.PSC.SGR.Aplicacao.CompromissoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.TipoCompromisso;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DONO
 */
public class CompromissoDAO extends AcontecimentoFilhoDAO<Compromisso> implements CompromissoRepositorio{
    
    private CompromissoIndividuoDAO compromissoIndividuoDAO=new CompromissoIndividuoDAO(conexao);
    private EnderecoDAO enderecoRepositorio= new EnderecoDAO();
    
    public CompromissoDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Compromisso filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getTipo()==null)
            throw new ViolacaoRegraNegocioException("Tipo é obrigatorio");
        if(filtro.getEndereco()==null)
            throw new ViolacaoRegraNegocioException("Endereço é obrigatorio");
        if(filtro.getEndereco().getNum()==0)
            throw new ViolacaoRegraNegocioException("Endereço é obrigatorio");
        
        if(enderecoRepositorio.Salvar(filtro.getEndereco()))    
            return super.Salvar(filtro);
        
        return false; 
    }

    @Override
    public List<Compromisso> Buscar(HashMap<String,String> filtro) throws ViolacaoRegraNegocioException {
        List<Compromisso> resultado=super.Buscar(filtro);
        
        for(Compromisso c : resultado)
            compromissoIndividuoDAO.Buscar(c);
        
        return resultado;
    }
    
    @Override
    public Compromisso Abrir(int id) throws ViolacaoRegraNegocioException {
        Compromisso resultado=super.Abrir(id);
        
        if(resultado!=null)
            compromissoIndividuoDAO.Buscar(resultado);
        
        return resultado;
    }
    
    @Override
    protected String getConsultaDelete() {
        return super.acontecimentoRepositorio.getConsultaDelete();
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * FROM Compromisso JOIN Acontecimento ON Compromisso.acontecimento=Acontecimento.id";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Compromisso(dataTermino, endereco, tipo, acontecimento) values(?,?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Compromisso set dataTermino=?, endereco=?, tipo=? where acontecimento = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Compromisso filtro) {
         try {    
            
            sql.setTimestamp(1, new java.sql.Timestamp( filtro.getDataTermino().getTime() )); 
            sql.setInt(2, filtro.getEndereco().getId());
            sql.setInt(3, filtro.getTipo().getId());
            sql.setInt(4, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Compromisso setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        
        try {
            
            Compromisso filtro = new Compromisso();
            super.acontecimentoRepositorio.setDados(resultado, filtro);
            filtro.setDataTermino( resultado.getTimestamp("dataTermino") );
            filtro.setEndereco(enderecoRepositorio.Abrir(resultado.getInt("endereco") ));
            filtro.setTipo(TipoCompromisso.Abrir( resultado.getInt("tipo") ));
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}