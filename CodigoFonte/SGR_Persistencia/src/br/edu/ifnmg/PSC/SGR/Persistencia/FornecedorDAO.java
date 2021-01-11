/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Fornecedor;
import br.edu.ifnmg.PSC.SGR.Aplicacao.FornecedorRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Sessao;
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
public class FornecedorDAO extends IndividuoFilhoDAO<Fornecedor> implements FornecedorRepositorio{

    public FornecedorDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Fornecedor filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getCnpj()==null)
            throw new ViolacaoRegraNegocioException("CNPJ é obrigatorio");
        
        HashMap<String,String> fornecedor=new HashMap<>();
        fornecedor.put("cnpj", filtro.getCnpj());
        
        if(!Buscar(fornecedor).isEmpty() && filtro.getId()==0)
            throw new ViolacaoRegraNegocioException("CNPJ ja cadastrado.");
        
        if(filtro.getUsuario()==0)
            if(Sessao.isLogged())
                filtro.setUsuario(Sessao.getUsuario().getId());
            else
                System.exit(0);        
        
        return super.Salvar(filtro);
    }
    
    @Override
    protected String getConsultaDelete() {
        return super.individuoRepositorio.getConsultaDelete();
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * FROM Fornecedor JOIN Individuo ON Fornecedor.individuo=Individuo.id";
        
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Fornecedor(cnpj, usuario, individuo) values(?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Fornecedor set cnpj=?, usuario=? where individuo = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Fornecedor filtro) {
         try {    
            
            sql.setString(1, filtro.getCnpj());
            sql.setInt(2, filtro.getUsuario());
            sql.setInt(3, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Fornecedor setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        
        try {
            
            Fornecedor filtro = new Fornecedor();
            super.individuoRepositorio.setDados(resultado, filtro);
            filtro.setCnpj(resultado.getString("cnpj") );
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}