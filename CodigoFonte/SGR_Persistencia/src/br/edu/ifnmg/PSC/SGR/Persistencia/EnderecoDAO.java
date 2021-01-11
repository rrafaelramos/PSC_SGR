/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Persistencia;

import br.edu.ifnmg.PSC.SGR.Aplicacao.Endereco;
import br.edu.ifnmg.PSC.SGR.Aplicacao.EnderecoRepositorio;
import br.edu.ifnmg.PSC.SGR.Aplicacao.Uf;
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
public class EnderecoDAO extends EntidadeDAO<Endereco> implements EnderecoRepositorio {

    public EnderecoDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    @Override
    public boolean Salvar(Endereco filtro) throws ViolacaoRegraNegocioException { 
        if(filtro.getRua()==null || filtro.getRua().isEmpty())
            throw new ViolacaoRegraNegocioException("Rua é obrigatoria");
        if(filtro.getNum()==0 )
            throw new ViolacaoRegraNegocioException("Numero é obrigatorio");
        if(filtro.getBairro()==null || filtro.getBairro().isEmpty())
            throw new ViolacaoRegraNegocioException("Bairro é obrigatorio");        
        if(filtro.getCidade()==null || filtro.getCidade().isEmpty())
            throw new ViolacaoRegraNegocioException("Cidade é obrigatoria");
        if(filtro.getUf()==null)
            throw new ViolacaoRegraNegocioException("UF é obrigatorio");        
        
        return super.Salvar(filtro);
    }

    @Override
    protected String getConsultaDelete() {
        return "delete from Endereco where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select * from Endereco";
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into Endereco(rua,num,bairro,cidade,uf,complemento) values(?,?,?,?,?,?)";
    }
    
    @Override
    protected String getConsultaUpdate() {
        return "update Endereco set rua = ?, num = ?, bairro = ?, cidade = ?, uf = ?, complemento = ? where id = ?";
    }

    @Override
    protected void setParametros(PreparedStatement sql, Endereco filtro) {
         try {    
            
            sql.setString(1, filtro.getRua());
            sql.setInt(2, filtro.getNum());
            sql.setString(3, filtro.getBairro());
            sql.setString(4, filtro.getCidade());
            sql.setInt(5, filtro.getUf().getId());
            sql.setString(6, filtro.getComplemento());
            if(filtro.getId() > 0)
                sql.setInt(7, filtro.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Endereco setDados(ResultSet resultado) throws ViolacaoRegraNegocioException{
        try {
            Endereco filtro = new Endereco();
            filtro.setId( resultado.getInt("id") );
            filtro.setRua( resultado.getString("rua") );
            filtro.setNum( resultado.getInt("num") );
            filtro.setBairro( resultado.getString("bairro") );
            filtro.setCidade( resultado.getString("cidade") );
            filtro.setUf( Uf.Abrir(resultado.getInt("uf") ));
            filtro.setComplemento( resultado.getString("complemento") );
            return filtro;
            
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


}
