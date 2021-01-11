/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoRecebido;
import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoRecebidoRepositorio;
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
public class PedidoRecebidoDAO extends PedidoFilhoDAO<PedidoRecebido> implements PedidoRecebidoRepositorio{
    
    public PedidoRecebidoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
        
    @Override
    protected String getConsultaDelete() {
        return super.pedidoRepositorio.getConsultaDelete();
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * FROM PedidoRecebido JOIN Pedido ON PedidoRecebido.pedido=Pedido.acontecimento";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into PedidoRecebido(cliente, enderecoEntrega, pedido) values(?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update PedidoRecebido set cliente=?, enderecoEntrega=? where pedido = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, PedidoRecebido filtro) {
         try {    
            
            sql.setInt(1, filtro.getCliente().getId());
            sql.setInt(2, filtro.getEnderecoEntrega());
            sql.setInt(3, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected PedidoRecebido setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        
        try {
            
            PedidoRecebido filtro = new PedidoRecebido();
            super.pedidoRepositorio.setDados(resultado, filtro);
            filtro.setEnderecoEntrega(resultado.getInt("enderecoEntrega") );
            ClienteDAO clienteRepositorio=new ClienteDAO();
            filtro.setCliente(clienteRepositorio.Abrir( resultado.getInt("Cliente") ));
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoRecebidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    
}
