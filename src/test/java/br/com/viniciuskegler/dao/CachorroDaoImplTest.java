/**
 * 
 */
package br.com.viniciuskegler.dao;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.viniciuskegler.model.Cachorro;
import br.com.viniciuskegler.model.Comportamento;
import br.com.viniciuskegler.util.GeradorRegistros;



public class CachorroDaoImplTest {

	private Session sessao;
	private CachorroDao cachorroDao;
	private Cachorro cachorro;
	private Faker faker;
	
	public CachorroDaoImplTest() {
		cachorroDao = new CachorroDaoImpl();
		faker = new Faker();
	}
	
	@Test
	public void testSalvar() {
		System.out.println("salvar");
		cachorro = new Cachorro(null, 
				faker.dog().name(), 
				new Date(), 
				faker.number().randomDouble(2, 5, 60), 
				"Masculino", 
				faker.dog().breed(),
				true);
		Comportamento comportamento = GeradorRegistros.gerarComportamento();
		cachorro.setComportamento(comportamento);
		sessao = HibernateUtil.abrirConexao();	
		cachorroDao.salvarOuAlterar(cachorro, sessao);
		sessao.close();
		assertNotNull(cachorro.getId());
		
	}
	
	@Test
	public void testAlterar() {
		System.out.println("alterar");
		buscarCachorroDb();
		cachorro.setNome(cachorro.getNome() + faker.lorem().word());
		cachorro.setObservacao(cachorro.getObservacao() + faker.dog().breed());
		sessao = HibernateUtil.abrirConexao();
		cachorroDao.salvarOuAlterar(cachorro, sessao);
		sessao.close();
		sessao = HibernateUtil.abrirConexao();
		Cachorro cachorroPesq = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
		sessao.close();
		assertTrue(cachorro.getNome().equals(cachorroPesq.getNome()) && cachorro.getObservacao().equals(cachorroPesq.getObservacao()));
	}
	
	@Test
	public void testExcluir() {
		System.out.println("excluir");
		buscarCachorroDb();
		sessao = HibernateUtil.abrirConexao();
		cachorroDao.excluir(cachorro, sessao);
		cachorro = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
		sessao.close();
		assertNull(cachorro);
	}
	
	@Test
	public void testPesquisarPorId() {
		System.out.println("pesquisar por id");
		buscarCachorroDb();
		sessao = HibernateUtil.abrirConexao();
		cachorro = cachorroDao.pesquisarPorId(cachorro.getId(), sessao);
		sessao.close();
		assertNotNull(cachorro);
	}

	@Test
	public void testPesquisarPorNome() {
		System.out.println("pesquisar por nome");
		buscarCachorroDb();
		sessao = HibernateUtil.abrirConexao();
		cachorro = cachorroDao.pesquisarPorNome(cachorro.getNome(), sessao);
		sessao.close();
		assertNotNull(cachorro);
	}

	@Test
	public void testPesqTreinados() {
		System.out.println("pesquisar treinados");
		buscarCachorroDb();
		sessao = HibernateUtil.abrirConexao();
		List<Cachorro> cachorros = cachorroDao.pesqTreinados(sessao);
		sessao.close();
		assertTrue(!cachorros.isEmpty());
	}

	private Cachorro buscarCachorroDb() {
		sessao = HibernateUtil.abrirConexao();
		List<Cachorro> cachorros = sessao.createQuery("From Cachorro c").getResultList();
		sessao.close();
		if(!cachorros.isEmpty()) {
			Collections.shuffle(cachorros);
			cachorro = cachorros.get(0);
		}else {
			this.testSalvar();
		}
		return cachorro;
	}

}
