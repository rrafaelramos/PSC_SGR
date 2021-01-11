/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Pedido;
import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoProduto;
import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoProdutoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Produto;
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
public class PedidoProdutoDAO extends EntidadeDAO<PedidoProduto> implements PedidoProdutoRepositorio {

    public PedidoProdutoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    @Override
    public boolean Salvar(PedidoProduto filtro) throws ViolacaoRegraNegocioException { 
        try {
            ProdutoDAO produtoRepositorio=new ProdutoDAO();
            Produto produtoAtual=produtoRepositorio.Abrir(filtro.getProduto().getId());
            if(produtoAtual.getEstoque()<filtro.getProduto().getEstoque())
                throw new ViolacaoRegraNegocioException("estoque insuficiente");
            
            if(super.Salvar(filtro)){
                produtoAtual.setEstoque(produtoAtual.getEstoque()-filtro.getProduto().getEstoque());
                return produtoRepositorio.Salvar(produtoAtual);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
    }
    
    @Override
    public boolean Deletar(PedidoProduto filtro) throws ViolacaoRegraNegocioException{    
        try {
            ProdutoDAO produtoRepositorio=new ProdutoDAO();
            Produto produtoAtual=produtoRepositorio.Abrir(filtro.getProduto().getId());
            produtoAtual.setEstoque(produtoAtual.getEstoque()+filtro.getProduto().getEstoque());
            if(super.Deletar(filtro))                
                return produtoRepositorio.Salvar(produtoAtual);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;            
    }
    
    @Override
    protected String getConsultaDelete() {
        return "delete from PedidoProduto where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from PedidoProduto";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into PedidoProduto(pedido,produto,valor,qtd) values(?,?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update PedidoProduto set pedido=?, produto=?, valor=?, qtd=? where id = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, PedidoProduto filtro) {
         try {    
            
            sql.setInt(1, filtro.getPedido().getId());
            sql.setInt(2, filtro.getProduto().getId());
            sql.setFloat(3, (filtro.getProduto().getCusto()+filtro.getProduto().getLucro()));
            sql.setFloat(4, filtro.getProduto().getEstoque());
            if(filtro.getId() > 0)
                sql.setInt(5, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected PedidoProduto setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            PedidoProduto filtro = new PedidoProduto();
            filtro.setId( resultado.getInt("id") );
            PedidoDAO<Pedido> pedidoRepositorio=new PedidoDAO();
            filtro.setPedido(pedidoRepositorio.Abrir(resultado.getInt("pedido")));
            ProdutoDAO produtoRepositorio=new ProdutoDAO();
            filtro.setProduto(produtoRepositorio.Abrir(resultado.getInt("produto")));
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


}
