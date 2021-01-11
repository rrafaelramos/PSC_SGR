/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Usuario;
import br.edu.ifnmg.PSC.SGR.Aplicacao.UsuarioRepositorio;
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
public class UsuarioDAO extends IndividuoFilhoDAO<Usuario> implements UsuarioRepositorio{

    public UsuarioDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Usuario filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getUserName()==null)
            throw new ViolacaoRegraNegocioException("UserName é obrigatorio");
        if(filtro.getPassword()==null)
            throw new ViolacaoRegraNegocioException("Password é obrigatorio");
        
        HashMap<String,String> usuario=new HashMap<>();
        usuario.put("userName", filtro.getUserName());
        
        if(!Buscar(usuario).isEmpty() && filtro.getId()==0)
            throw new ViolacaoRegraNegocioException("UserName ja cadastrado.");
        
        return super.Salvar(filtro);
        
    }
    
    @Override
    protected String getConsultaDelete() {
        return super.individuoRepositorio.getConsultaDelete();
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * FROM Usuario JOIN Individuo ON Usuario.individuo=Individuo.id";
        
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Usuario(userName, password, individuo ) values(?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Usuario set userName=?, password=? where individuo = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Usuario filtro) {
         try {    
            
            sql.setString(1, filtro.getUserName());
            sql.setString(2, filtro.getPassword());
            sql.setInt(3, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Usuario setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        
        try {
            
            Usuario filtro = new Usuario();
            super.individuoRepositorio.setDados(resultado, filtro);
            filtro.setUserName(resultado.getString("userName") );
            filtro.setPassword(resultado.getString("password") );            
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
}