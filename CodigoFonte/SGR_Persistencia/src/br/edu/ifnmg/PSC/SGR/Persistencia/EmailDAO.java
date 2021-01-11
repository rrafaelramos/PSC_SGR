/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Email;
import br.edu.ifnmg.PSC.SGR.Aplicacao.EmailRepositorio;
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
public class EmailDAO extends EntidadeDAO<Email> implements EmailRepositorio {

    public EmailDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Email filtro) throws ViolacaoRegraNegocioException { 

        HashMap<String,String> email=new HashMap<>();
        email.put("individuo", Integer.toString(filtro.getId()));

        if(Buscar(email).size()>3)
            throw new ViolacaoRegraNegocioException("Limite maximo ja alcançado");
        
        return super.Salvar(filtro);

    }
    
    @Override
    protected String getConsultaDelete() {
        return "delete from Email where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from Email";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Email(email,individuo) values(?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Email set email = ?, individuo = ? where id = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Email filtro) {
         try {    
            
            sql.setString(1, filtro.getEmail());
            sql.setInt(2, filtro.getIndividuo());
            if(filtro.getId() > 0)
                sql.setInt(3, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Email setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            Email filtro = new Email();
            filtro.setId( resultado.getInt("id") );
            filtro.setEmail( resultado.getString("email") );
            filtro.setIndividuo( resultado.getInt("individuo") );
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


}
