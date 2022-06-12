package br.com.viniciuskegler.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.viniciuskegler.model.Comportamento;

public interface ComportamentoDao extends BaseDao<Comportamento, Long> {

	List<Comportamento> pesqTodos(Session sessao);
}
