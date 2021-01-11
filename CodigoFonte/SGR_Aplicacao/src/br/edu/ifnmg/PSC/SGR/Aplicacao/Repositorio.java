/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.PSC.SGR.Aplicacao;

import java.util.HashMap;
import java.util.List;


public interface Repositorio<T extends Entidade> {
    
    public boolean Salvar(T obj) throws ViolacaoRegraNegocioException;
    
    public boolean Deletar(T obj)throws ViolacaoRegraNegocioException;
    
    public T Abrir(int id) throws ViolacaoRegraNegocioException;
    
    public List<T> Buscar(HashMap<String,String> filtro) throws ViolacaoRegraNegocioException;
    
}
