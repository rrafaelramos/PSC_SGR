/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Acontecimento;
import br.edu.ifnmg.PSC.SGR.Aplicacao.AcontecimentoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Sessao;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Status;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author DONO
 */
    
public class AcontecimentoDAO<T extends Acontecimento> extends EntidadeDAO<Acontecimento> implements AcontecimentoRepositorio { 
    
    public AcontecimentoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    @Override
    public boolean Salvar(Acontecimento filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getDataPrevisto()==null)
            throw new ViolacaoRegraNegocioException("Data Previsto é obrigatoria");
        
        if(filtro.getUsuario()==0)
            if(Sessao.isLogged())
                filtro.setUsuario(Sessao.getUsuario().getId());
            else
                System.exit(0);        
        
        return super.Salvar(filtro);
    }
            

    @Override
    protected String getConsultaDelete() {
        return "delete from Acontecimento where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from Acontecimento ";
    }

    @Override
    protected String getConsultaUpdate() {
        return "update Acontecimento set dataPrevisto=?, status=?,  obs=?, usuario=? where id=?";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Acontecimento(dataPrevisto, status, obs, usuario) values(?,?,?,?)";
    }

    
    @Override
    protected void setParametros(PreparedStatement sql, Acontecimento filtro) {
         try {    
            
            sql.setTimestamp(1, new java.sql.Timestamp( filtro.getDataPrevisto().getTime() )); 
            sql.setInt(2, filtro.getStatus().getId());
            sql.setString(3, filtro.getObs());
            sql.setInt(4, filtro.getUsuario());
            if(filtro.getId() > 0)
                sql.setInt(5, filtro.getId());
                
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Acontecimento setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            Acontecimento filtro = new Acontecimento();
            filtro.setId( resultado.getInt("id") );
            filtro.setUsuario(resultado.getInt("usuario") );
            filtro.setDataPrevisto(new Date( resultado.getDate("dataPrevisto").getTime() ));
            filtro.setStatus(Status.Abrir( resultado.getInt("status") ));
            filtro.setObs(resultado.getString("obs"));
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setDados(ResultSet resultado, T filtro) throws ViolacaoRegraNegocioException{
        try {
            filtro.setId( resultado.getInt("id") );
            filtro.setUsuario(resultado.getInt("usuario") );
            filtro.setDataPrevisto( resultado.getTimestamp("dataPrevisto") );
            filtro.setStatus(Status.Abrir( resultado.getInt("status") ));
            filtro.setObs(resultado.getString("obs"));
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}