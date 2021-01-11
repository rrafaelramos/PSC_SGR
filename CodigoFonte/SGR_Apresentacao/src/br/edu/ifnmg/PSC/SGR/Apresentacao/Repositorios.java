/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Apresentacao;

import br.edu.ifnmg.PSC.SGR.Aplicacao.ClienteRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.CompromissoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.EmailRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.EnderecoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.FornecedorRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoProdutoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.PedidoRecebidoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ProdutoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.TelefoneRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.UsuarioRepositorio;
import br.edu.ifnmg.PSC.SGR.Persistencia.ClienteDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.CompromissoDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.EmailDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.EnderecoDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.FornecedorDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.PedidoProdutoDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.PedidoRecebidoDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.ProdutoDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.TelefoneDAO;
import br.edu.ifnmg.PSC.SGR.Persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class Repositorios {
    
    static FornecedorRepositorio fornecedorDAO = null;
    
    public static FornecedorRepositorio getFornecedorRepositorio(){
        if(fornecedorDAO == null)
            try {
                fornecedorDAO = new FornecedorDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return fornecedorDAO;
    }

    static EmailRepositorio emailDAO = null;
    
    public static EmailRepositorio getEmailRepositorio(){
        if(emailDAO == null)
            try {
                emailDAO = new EmailDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return emailDAO;
    }

    static TelefoneRepositorio telefoneDAO = null;
    
    public static TelefoneRepositorio getTelefoneRepositorio(){
        if(telefoneDAO == null)
            try {
                telefoneDAO = new TelefoneDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return telefoneDAO;
    }                

    static EnderecoRepositorio enderecoDAO = null;
    
    public static EnderecoRepositorio getEnderecoRepositorio(){
        if(enderecoDAO == null)
            try {
                enderecoDAO = new EnderecoDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return enderecoDAO;
    }   

    static ClienteRepositorio clienteDAO = null;
    
    public static ClienteRepositorio getClienteRepositorio(){
        if(clienteDAO == null)
            try {
                clienteDAO = new ClienteDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return clienteDAO;
    }   

static ProdutoRepositorio produtoDAO = null;
    
    public static ProdutoRepositorio getProdutoRepositorio(){
        if(produtoDAO == null)
            try {
                produtoDAO = new ProdutoDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return produtoDAO;
    }

    static UsuarioRepositorio usuarioDAO = null;
    
    public static UsuarioRepositorio getUsuarioRepositorio(){
        if(usuarioDAO == null)
            try {
                usuarioDAO = new UsuarioDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return usuarioDAO;
    }

    static CompromissoRepositorio compromissoDAO = null;
    
    public static CompromissoRepositorio getCompromissoRepositorio(){
        if(compromissoDAO == null)
            try {
                compromissoDAO = new CompromissoDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return compromissoDAO;
    }


    static PedidoProdutoRepositorio pedidoProdutoDAO = null;
    
    public static PedidoProdutoRepositorio getPedidoProdutoRepositorio(){
        if(pedidoProdutoDAO == null)
            try {
                pedidoProdutoDAO = new PedidoProdutoDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return pedidoProdutoDAO;
    }


    static PedidoRecebidoRepositorio pedidoRecebidoDAO = null;
    
    public static PedidoRecebidoRepositorio getPedidoRecebidoRepositorio(){
        if(pedidoRecebidoDAO == null)
            try {
                pedidoRecebidoDAO = new PedidoRecebidoDAO();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return pedidoRecebidoDAO;
    }

}