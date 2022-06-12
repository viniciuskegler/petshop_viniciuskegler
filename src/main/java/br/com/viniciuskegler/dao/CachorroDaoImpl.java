package br.com.viniciuskegler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.viniciuskegler.model.Cachorro;

public class CachorroDaoImpl extends BaseDaoImpl<Cachorro, Long> implements CachorroDao, Serializable {

	@Override
	public Cachorro pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return sessao.find(Cachorro.class, id);
	}

	@Override
	public Cachorro pesquisarPorNome(String nome, Session sessao) throws HibernateException {
		return (Cachorro) sessao.createQuery("From Cachorro c join fetch c.comportamento where c.nome = :nome").setParameter("nome", nome).getSingleResult();
	}

	@Override
	public List<Cachorro> pesqTreinados(Session sessao) throws HibernateException {
		return sessao.createQuery("From Cachorro c where c.treinado = true").getResultList();
	}

}
