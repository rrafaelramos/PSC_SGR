/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Entidade;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Repositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Sessao;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DONO
 */
public abstract class EntidadeDAO<T extends Entidade> implements Repositorio<T> {
    
    protected Connection conexao;
    
    protected String where = "";

    public EntidadeDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgr","root","");

    }

    protected abstract String getConsultaInsert();
    protected abstract String getConsultaUpdate();
    protected abstract String getConsultaDelete();
    protected abstract String getConsultaBuscar();
    
    protected abstract void setParametros(PreparedStatement sql, T filtro);
    protected abstract T setDados(ResultSet resultado) throws ViolacaoRegraNegocioException;
    
    
    @Override
    public boolean Salvar(T filtro) throws ViolacaoRegraNegocioException{
        try {
            PreparedStatement sql = null;
            
            // Objeto não está salvo no BD ---> INSERT
            if(filtro.getId() == 0){
                // Criar a consulta SQL de inserção
                sql = conexao.prepareStatement(getConsultaInsert());
            
            // Objeto já está salvo no BD ---> UPDATE
            } else {
                // Criar a consulta SQL de inserção
                sql = conexao.prepareStatement(getConsultaUpdate());
            }    
            
            setParametros(sql, filtro);

            if (sql.executeUpdate() > 0) {
                
                if(filtro.getId()==0){

                    final Comparator<Entidade> comparadorId = (e1, e2) -> Integer.compare(e1.getId(), e2.getId());

                    List<T> tmp = this.Buscar(null);

                    Entidade maiorid = tmp.stream().max(comparadorId).get();

                    filtro.setId(maiorid.getId());
                    
                }                
                return true;
            }
                
            else 
                return false;
            
        } catch(SQLException ex) {
            Logger.getLogger(EntidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean Deletar(T filtro) throws ViolacaoRegraNegocioException{
        if(filtro.getId()==0)
            throw new ViolacaoRegraNegocioException("Nenhum registro selecionado.");
        try {
            PreparedStatement sql = conexao.prepareStatement(getConsultaDelete());
            
            sql.setInt(1, filtro.getId());
            
            if (sql.executeUpdate() > 0) {
                filtro = null;
                return true;
            } else 
                return false;
            
        } catch(SQLException ex) {
             return false;
        }
    }

    @Override
    public T Abrir(int id) throws ViolacaoRegraNegocioException {
        try {

            String sqlfinal = this.getConsultaBuscar();
            
            HashMap<String,String> filtro = new HashMap<>();
            filtro.put("id", Integer.toString(id));
            
            setBuscaFiltros(filtro);
                
            sqlfinal += " where " + where;
            
            where = "";
            
            PreparedStatement sql = conexao.prepareStatement( sqlfinal );
            
            ResultSet resultado = sql.executeQuery();
            
            if(resultado.next())
                return this.setDados(resultado);
            else 
                return null;
            
        } catch (SQLException ex) {
            Logger.getLogger(EntidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<T> Buscar(HashMap<String,String> filtro) throws ViolacaoRegraNegocioException {
        
        // Cria uma lista vazia com o tipo T
        List<T> lista = new ArrayList<>();
        
        try {
            
            String sqlFinal = this.getConsultaBuscar();

            if(filtro==null)
                filtro=new HashMap<>();

            String classe= this.getClass().getName();
        
            classe=classe.substring((classe.lastIndexOf(".")+1), classe.length());
            
            if(((((Sessao.isLogged() && !"UsuarioDAO".equals(classe))&&!"EmailDAO".equals(classe))&&!"TelefoneDAO".equals(classe))&&!"EnderecoDAO".equals(classe))
                    &&!"IndividuoDAO".equals(classe))
                        filtro.put("usuario", Integer.toString(Sessao.getUsuario().getId()));            
            
            if(!filtro.isEmpty()){
                this.setBuscaFiltros(filtro);
                sqlFinal += " where " + where;
                this.where = "";
            }
            
            PreparedStatement sql = conexao.prepareStatement( sqlFinal );
                       
            ResultSet resultado = sql.executeQuery();
            
            while(resultado.next())
                lista.add( this.setDados(resultado) );
            
            return lista;
            
        } catch (SQLException ex) {
            Logger.getLogger(EntidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    protected void setBuscaFiltros(HashMap<String, String> filtro) {
        
        for(String key : filtro.keySet()){
            try{
                
                if(key=="id")
                    adicionarFiltro(key, new Integer(filtro.get(key)));    
                
                else if(key=="nome")
                    adicionarFiltro(key, filtro.get(key));    
                
                else if(key=="individuo")
                    adicionarFiltro(key, new Integer(filtro.get(key)));
                
                else if(key=="cnpj")
                    adicionarFiltro(key, filtro.get(key));    
                
                else if(key=="cpf")
                    adicionarFiltro(key, filtro.get(key));
                
                else if(key=="descricao")
                    adicionarFiltro(key, filtro.get(key));                
                
                else if(key=="descricaoExata")
                    adicionarFiltroExato("descricao", filtro.get(key));                                
                
                else if(key=="password")
                    adicionarFiltroExato(key, filtro.get(key));
                
                else if(key=="userName")
                    adicionarFiltroExato(key, filtro.get(key));
                
                else if(key=="usuario")
                    adicionarFiltro(key, new Integer(filtro.get(key)));
                
                else if(key=="email")
                    adicionarFiltroExato(key, filtro.get(key));
                
                else if(key=="dataTermino")
                    adicionarFiltro(key, filtro.get(key));
                
                else if(key=="dataPrevisto")
                    adicionarFiltro(key, filtro.get(key));

                else if(key=="tipo")
                    adicionarFiltro(key, new Integer(filtro.get(key)));

                else if(key=="status")
                    adicionarFiltro(key, new Integer(filtro.get(key)));                

                else if(key=="acontecimento")
                    adicionarFiltro(key, new Integer(filtro.get(key)));
                
                else
                    throw  new ViolacaoRegraNegocioException("filtro ´"+key+"´ não cadastrado");
            
            } catch (ViolacaoRegraNegocioException ex) {
                Logger.getLogger(EntidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    
    private void adicionarFiltro(String campo, String valor){
        if(where.length() > 0)
            where += " and ";
        
        where += campo + " like '%"+ valor + "%'";
        
    }
    
    private void adicionarFiltro(String campo, int valor){
        if(where.length() > 0)
            where += " and ";
        
        where += campo + " = "+ Integer.toString(valor);
        
    }

    private void adicionarFiltroExato(String campo, String valor){
        if(where.length() > 0)
            where += " and ";
        
        where +=campo + "='"+ valor + "'";
        
    }
}
