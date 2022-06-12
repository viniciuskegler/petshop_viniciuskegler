package br.com.viniciuskegler.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.viniciuskegler.model.Cachorro;

public interface CachorroDao extends BaseDao<Cachorro, Long> {

	Cachorro pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	List<Cachorro> pesqTreinados(Session sessao) throws HibernateException;
	
}
