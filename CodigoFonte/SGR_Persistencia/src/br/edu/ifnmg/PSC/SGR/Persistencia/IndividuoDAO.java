/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Individuo;
import br.edu.ifnmg.PSC.SGR.Aplicacao.IndividuoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author DONO
 */
    
public class IndividuoDAO<T extends Individuo> extends EntidadeDAO<Individuo> implements IndividuoRepositorio { 
    
    public IndividuoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    @Override
    public boolean Salvar(Individuo filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getNome()==null)
            throw new ViolacaoRegraNegocioException("Nome é obrigatorio");
        
        return super.Salvar(filtro);
    }
            

    @Override
    protected String getConsultaDelete() {
        return "delete from Individuo where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from Individuo ";
    }

    @Override
    protected String getConsultaUpdate() {
        return "update Individuo set nome = ? where id = ?";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Individuo(nome) values(?)";
    }

    
    @Override
    protected void setParametros(PreparedStatement sql, Individuo filtro) {
         try {    
            
            sql.setString(1, filtro.getNome());
            if(filtro.getId() > 0)
                sql.setInt(2, filtro.getId());

            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Individuo setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            Individuo filtro = new Individuo();
            filtro.setId( resultado.getInt("id") );
            filtro.setNome( resultado.getString("nome") );
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void setDados(ResultSet resultado, T filtro) throws ViolacaoRegraNegocioException{
        try {
         
            filtro.setId( resultado.getInt("id") );
            filtro.setNome( resultado.getString("nome") );
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}