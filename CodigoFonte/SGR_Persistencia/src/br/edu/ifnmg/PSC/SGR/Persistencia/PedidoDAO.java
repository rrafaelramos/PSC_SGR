/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Pedido;
import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoRepositorio;
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
public class PedidoDAO<T extends Pedido> extends AcontecimentoFilhoDAO<Pedido> implements PedidoRepositorio{
    
    public PedidoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    @Override
    protected String getConsultaDelete() {
        return super.acontecimentoRepositorio.getConsultaDelete();
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * FROM Pedido JOIN Acontecimento ON Pedido.acontecimento=Acontecimento.id";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Pedido(dataRealizado, diferenca, acontecimento) values(?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Pedido set dataRealizado=?, diferenca=? where acontecimento = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Pedido filtro) {
         try {    
            
            sql.setTimestamp(1, new java.sql.Timestamp( filtro.getDataRealizado().getTime() )); 
            sql.setFloat(2, filtro.getDiferenca());
            sql.setInt(3, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Pedido setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        
        try {
            
            Pedido filtro = new Pedido();
            super.acontecimentoRepositorio.setDados(resultado, filtro);
            filtro.setDataRealizado(resultado.getTimestamp("dataRealizado") );
            filtro.setDiferenca(resultado.getFloat("diferenca") );
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void setDados(ResultSet resultado, T filtro) throws ViolacaoRegraNegocioException{

        try {
            super.acontecimentoRepositorio.setDados(resultado, filtro);
            filtro.setDataRealizado(resultado.getTimestamp("dataRealizado") );
            filtro.setDiferenca(resultado.getFloat("diferenca") );
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}