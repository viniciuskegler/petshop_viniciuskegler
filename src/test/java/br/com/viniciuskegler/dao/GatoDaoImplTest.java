package br.com.viniciuskegler.dao;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.com.viniciuskegler.model.Comportamento;
import br.com.viniciuskegler.model.Gato;
import br.com.viniciuskegler.util.GeradorRegistros;


public class GatoDaoImplTest {

	private Faker faker;
	private GatoDao gatoDao;
	private Gato gato;
	private Session sessao;
	
	public GatoDaoImplTest() {
		this.faker = new Faker();
		this.gatoDao = new GatoDaoImpl();
	}
	
	@Test
	public void testSalvar() {
		System.out.println("salvar");
		gato = new Gato(null, 
				faker.cat().name(), 
				new Date(), 
				faker.number().randomDouble(2, 5, 30), 
				"Masculino", 
				faker.cat().breed(), 
				false, false);
		Comportamento comportamento = GeradorRegistros.gerarComportamento();
		gato.setComportamento(comportamento);
		sessao = HibernateUtil.abrirConexao();	
		gatoDao.salvarOuAlterar(gato, sessao);
		sessao.close();
		assertNotNull(gato.getId());
		
	}
	
	@Test
	public void testAlterar() {
		System.out.println("salvar");
		buscarGatoDb();
		gato.setNome(gato.getNome() + faker.lorem().word());
		gato.setObservacao(gato.getObservacao() + faker.cat().breed());
		sessao = HibernateUtil.abrirConexao();
		gatoDao.salvarOuAlterar(gato, sessao);
		sessao.close();
		sessao = HibernateUtil.abrirConexao();
		Gato gatoAlt = gatoDao.pesquisarPorId(gato.getId(), sessao);
		sessao.close();
		assertTrue(gato.getNome().equals(gatoAlt.getNome()) && gato.getObservacao().equals(gatoAlt.getObservacao()));
	}
	
	@Test
	public void testExcluir() {
		System.out.println("excluir");
		buscarGatoDb();
		sessao = HibernateUtil.abrirConexao();
		gatoDao.excluir(gato, sessao);
		gato = gatoDao.pesquisarPorId(gato.getId(), sessao);
		sessao.close();
		assertNull(gato);
	}
	
	@Test
	public void testPesquisarPorId() {
		System.out.println("pesquisar por id");
		buscarGatoDb();
		sessao = HibernateUtil.abrirConexao();
		gato = gatoDao.pesquisarPorId(gato.getId(), sessao);
		sessao.close();
		assertNotNull(gato);
	}

	@Test
	public void testPesquisarPorNome() {
		System.out.println("pesquisar por nome");
		buscarGatoDb();
		sessao = HibernateUtil.abrirConexao();
		gato = gatoDao.pesquisarPorNome(gato.getNome(), sessao);
		sessao.close();
		assertNotNull(gato);
	}

	@Test
	public void testPesqPorComp() {
		System.out.println("pesquisar por comportamento");
		buscarGatoDb();
		sessao = HibernateUtil.abrirConexao();
		List<Gato> gatos = gatoDao.pesqPorComp(gato.getComportamento(), sessao);
		sessao.close();
		assertTrue(!gatos.isEmpty());
	}

	private Gato buscarGatoDb() {
		sessao = HibernateUtil.abrirConexao();
		List<Gato> gatos = sessao.createQuery("From Gato g").getResultList();
		sessao.close();
		if(!gatos.isEmpty()) {
			Collections.shuffle(gatos);
			gato = gatos.get(0);
		}else {
			this.testSalvar();
		}
		return gato;
	}
	
}
