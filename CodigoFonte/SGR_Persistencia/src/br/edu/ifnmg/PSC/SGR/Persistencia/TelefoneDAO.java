/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Telefone;
import br.edu.ifnmg.PSC.SGR.Aplicacao.TelefoneRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DONO
 */
public class TelefoneDAO extends EntidadeDAO<Telefone> implements TelefoneRepositorio {

    public TelefoneDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Telefone filtro) throws ViolacaoRegraNegocioException { 

        HashMap<String,String> telefone=new HashMap<>();
        telefone.put("individuo", Integer.toString(filtro.getId()));

        if(Buscar(telefone).size()>3)
            throw new ViolacaoRegraNegocioException("Limite maximo ja alcançado");
        
        return super.Salvar(filtro);

    }
    
    @Override
    protected String getConsultaDelete() {
        return "delete from Telefone";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from Telefone";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Telefone(telefone,individuo) values(?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Telefone set telefone = ?, individuo = ? where id = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Telefone filtro) {
         try {    
            
            sql.setString(1, filtro.getTelefone());
            sql.setInt(2, filtro.getIndividuo());
            if(filtro.getId() > 0)
                sql.setInt(3, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Telefone setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            Telefone filtro = new Telefone();
            filtro.setId( resultado.getInt("id") );
            filtro.setTelefone( resultado.getString("telefone") );
            filtro.setIndividuo( resultado.getInt("individuo") );
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


}
