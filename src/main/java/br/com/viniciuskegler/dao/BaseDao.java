package br.com.viniciuskegler.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface BaseDao<T, ID> {
    
    void salvarOuAlterar(T entidade, Session sessao) throws HibernateException;
    void excluir(T entidade, Session sessao) throws HibernateException;
    T pesquisarPorId(ID id, Session sessao) throws HibernateException;
} 
