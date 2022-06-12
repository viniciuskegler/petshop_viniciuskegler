package br.com.viniciuskegler.dao;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.viniciuskegler.model.Comportamento;

public class ComportamentoDaoImplTest {

	private Session sessao;
	private ComportamentoDao comportamentoDao;
	private Faker faker;
	private Comportamento comportamento;
	
	public ComportamentoDaoImplTest() {
		comportamentoDao = new ComportamentoDaoImpl();
		faker = new Faker();
	}
	
	
	@Test
	public void testSalvar() {
	System.out.println("salvar");
	comportamento = new Comportamento(null, faker.lorem().word(), faker.lorem().sentence(), false);
	sessao = HibernateUtil.abrirConexao();
	comportamentoDao.salvarOuAlterar(comportamento, sessao);
	sessao.close();
	assertNotNull(comportamento.getId());
	}
	
	@Test
	public void testAlterar() {
		System.out.println("alterar");
		buscarComportamentoDb();
		sessao = HibernateUtil.abrirConexao();
		comportamento.setTipo(comportamento.getTipo() + faker.lorem().word());
		comportamento.setDescricao(comportamento.getDescricao() + faker.lorem().sentence());
		comportamentoDao.salvarOuAlterar(comportamento, sessao);
		sessao.close();
		sessao = HibernateUtil.abrirConexao();
		Comportamento compAlt = comportamentoDao.pesquisarPorId(comportamento.getId(), sessao);
		sessao.close();
		assertTrue(comportamento.getTipo().equals(compAlt.getTipo()) && comportamento.getDescricao().equals(compAlt.getDescricao()));
	}
	
//	@Test
	public void testExcluir() {
		System.out.println("excluir");
		buscarComportamentoDb();
		sessao = HibernateUtil.abrirConexao();
		comportamentoDao.excluir(comportamento, sessao);
		comportamento = comportamentoDao.pesquisarPorId(comportamento.getId(), sessao);
		sessao.close();
		assertNull(comportamento);
	}
	
	@Test
	public void testPesquisarPorId() {
		System.out.println("pesquisar por id");
		buscarComportamentoDb();
		sessao = HibernateUtil.abrirConexao();
		comportamento = comportamentoDao.pesquisarPorId(comportamento.getId(), sessao);
		sessao.close();
		assertNotNull(comportamento);
	}

	@Test
	public void testPesqTodos() {
		System.out.println("pesquisar todos");
		buscarComportamentoDb();
		sessao = HibernateUtil.abrirConexao();
		List<Comportamento> comps = comportamentoDao.pesqTodos(sessao);
		sessao.close();
		assertTrue(!comps.isEmpty());
	}


	private Comportamento buscarComportamentoDb() {
		sessao = HibernateUtil.abrirConexao();
		List<Comportamento> comportamentos = sessao.createQuery("From Comportamento c").getResultList();
		sessao.close();
		if(!comportamentos.isEmpty()) {
			Collections.shuffle(comportamentos);
			comportamento = comportamentos.get(0);
		}else {
			this.testSalvar();
		}
		return comportamento;
	}

}
