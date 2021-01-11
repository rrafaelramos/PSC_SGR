/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Cliente;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ClienteRepositorio;
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
public class ClienteDAO extends IndividuoFilhoDAO<Cliente> implements ClienteRepositorio{

    private EnderecoDAO enderecoRepositorio= new EnderecoDAO();
    
    public ClienteDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Cliente filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getCpf()==null)
            throw new ViolacaoRegraNegocioException("CPF é obrigatorio");
        if(filtro.getEndereco()==null)
            throw new ViolacaoRegraNegocioException("Endereço é obrigatorio");  
        if(filtro.getEndereco().getNum()==0)
            throw new ViolacaoRegraNegocioException("Endereço é obrigatorio");        
        
        HashMap<String,String> fornecedor=new HashMap<>();
        fornecedor.put("cpf", filtro.getCpf());
        
        if(!Buscar(fornecedor).isEmpty() && filtro.getId()==0)
            throw new ViolacaoRegraNegocioException("CPF ja cadastrado.");
        
        if(filtro.getUsuario()==0)
            if(Sessao.isLogged())
                filtro.setUsuario(Sessao.getUsuario().getId());
            else
                System.exit(0);        
        
        if(enderecoRepositorio.Salvar(filtro.getEndereco()))    
            return super.Salvar(filtro);
        
        return false;
    }
    
    @Override
    public boolean Deletar(Cliente filtro) {
        try {
            if(enderecoRepositorio.Deletar(filtro.getEndereco()))
                return super.Deletar(filtro);        
        } catch (ViolacaoRegraNegocioException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    protected String getConsultaDelete() {
        return super.individuoRepositorio.getConsultaDelete();
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * FROM Cliente JOIN Individuo ON Cliente.individuo=Individuo.id";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Cliente(cpf, obs, endereco, usuario, individuo) values(?,?,?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Cliente set cpf=?, obs=?, endereco=?, usuario=? where individuo = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Cliente filtro) {
         try {    
            
            sql.setString(1, filtro.getCpf());
            sql.setString(2, filtro.getObs());
            sql.setInt(3, filtro.getEndereco().getId());
            sql.setInt(4, filtro.getUsuario());
            sql.setInt(5, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Cliente setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        
        try {
            
            Cliente filtro = new Cliente();
            
            super.individuoRepositorio.setDados(resultado, filtro);
            
            filtro.setCpf(resultado.getString("cpf") );
            filtro.setObs(resultado.getString("obs") );
            filtro.setEndereco(enderecoRepositorio.Abrir(resultado.getInt("endereco") ));
            
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}