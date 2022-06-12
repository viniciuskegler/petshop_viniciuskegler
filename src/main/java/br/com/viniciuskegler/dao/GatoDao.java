package br.com.viniciuskegler.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.viniciuskegler.model.Comportamento;
import br.com.viniciuskegler.model.Gato;

public interface GatoDao extends BaseDao<Gato, Long> {

	Gato pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	List<Gato> pesqPorComp(Comportamento comportamento, Session sessao) throws HibernateException;
}
