package br.com.viniciuskegler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.viniciuskegler.model.Comportamento;
import br.com.viniciuskegler.model.Gato;

public class GatoDaoImpl extends BaseDaoImpl<Gato, Long> implements GatoDao, Serializable {

	@Override
	public Gato pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return sessao.find(Gato.class, id);
	}

	@Override
	public Gato pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		return (Gato) sessao.createQuery("From Gato g join fetch g.comportamento where g.nome = :nome").setParameter("nome", nome).getSingleResult(); 
	}

	@Override
	public List<Gato> pesqPorComp(Comportamento comportamento, Session sessao) throws HibernateException {
		return sessao.createQuery("From Gato g where g.comportamento = :comportamento").setParameter("comportamento", comportamento).getResultList();
	}
	
}
