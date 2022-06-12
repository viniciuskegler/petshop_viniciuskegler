package br.com.viniciuskegler.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabela {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop_pu");
        emf.close();
    }
}
