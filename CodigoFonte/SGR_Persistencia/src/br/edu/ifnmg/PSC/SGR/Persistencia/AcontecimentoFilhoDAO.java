/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Entidade;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Acontecimento;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Repositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.ViolacaoRegraNegocioException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DONO 
 */
public abstract class AcontecimentoFilhoDAO<T extends Acontecimento> extends EntidadeDAO<T> implements Repositorio<T>{

    protected AcontecimentoDAO<T> acontecimentoRepositorio=new AcontecimentoDAO();

    public AcontecimentoFilhoDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public boolean Salvar(T filtro) throws ViolacaoRegraNegocioException { 
        
        if(acontecimentoRepositorio.Salvar(filtro)){
            try {
                PreparedStatement sql = null;

                HashMap<String,String> filhoAcontecimento=new HashMap<>();
                filhoAcontecimento.put("acontecimento", Integer.toString(filtro.getId()));
                
                if(Buscar(filhoAcontecimento).isEmpty()){
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



            } catch(SQLException ex) {
                Logger.getLogger(EntidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
                this.Deletar(filtro);                
                return false;
            }
        }

        this.Deletar(filtro);
        return false;
    }
    
}