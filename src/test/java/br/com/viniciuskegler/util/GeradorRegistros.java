package br.com.viniciuskegler.util;

import com.github.javafaker.Faker;
import br.com.viniciuskegler.model.Comportamento;

public class GeradorRegistros {

	private static Faker faker = new Faker();
	
	public static final Comportamento gerarComportamento() {
		return new Comportamento(null, faker.lorem().word(), faker.lorem().sentence(), false);
	}
	
}
