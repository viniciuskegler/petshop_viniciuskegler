package br.com.viniciuskegler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.viniciuskegler.model.Comportamento;

public class ComportamentoDaoImpl extends BaseDaoImpl<Comportamento, Long> implements ComportamentoDao, Serializable {

	@Override
	public Comportamento pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return sessao.find(Comportamento.class, id);
	}

	@Override
	public List<Comportamento> pesqTodos(Session sessao) {
		return sessao.createQuery("From Comportamento C").getResultList();
	}

}
