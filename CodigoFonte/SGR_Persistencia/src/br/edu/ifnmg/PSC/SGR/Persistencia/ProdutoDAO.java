/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Produto;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ProdutoRepositorio;
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
public class ProdutoDAO extends EntidadeDAO<Produto> implements ProdutoRepositorio {

    public ProdutoDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(Produto filtro) throws ViolacaoRegraNegocioException { 

        if(filtro.getDescricao()==null)
            throw new ViolacaoRegraNegocioException("Descrição é obrigatoria");
        
        HashMap<String,String> produto=new HashMap<>();
        produto.put("descricaoExata", filtro.getDescricao());
        
        if(!Buscar(produto).isEmpty() && filtro.getId()==0)
            throw new ViolacaoRegraNegocioException("Descrição ja cadastrada.");
        
        if(filtro.getUsuario()==0)
            if(Sessao.isLogged())
                filtro.setUsuario(Sessao.getUsuario().getId());
            else
                System.exit(0);        

        return super.Salvar(filtro);

    }
    
    @Override
    protected String getConsultaDelete() {
        return "delete from Produto where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from Produto";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Produto(descricao,estoque,custo,lucro,usuario) values(?,?,?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Produto set descricao = ?, estoque = ?, custo = ?, lucro =?, usuario where id = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Produto filtro) {
         try {    
            
            sql.setString(1, filtro.getDescricao());
            sql.setInt(2, filtro.getEstoque());
            sql.setFloat(3, filtro.getCusto());
            sql.setFloat(4, filtro.getLucro());
            sql.setInt(5, filtro.getUsuario());
            if(filtro.getId() > 0)
                sql.setInt(6, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Produto setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            Produto filtro = new Produto();
            filtro.setId( resultado.getInt("id") );
            filtro.setUsuario(resultado.getInt("usuario") );
            filtro.setDescricao(resultado.getString("descricao") );
            filtro.setEstoque(resultado.getInt("estoque") );
            filtro.setCusto(resultado.getFloat("custo") );
            filtro.setLucro(resultado.getFloat("lucro") );
            
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


}
