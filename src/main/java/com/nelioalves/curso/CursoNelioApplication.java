package com.nelioalves.curso;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.domain.Produto;
import com.nelioalves.curso.repositories.CategoriaRepository;
import com.nelioalves.curso.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner {

	@Autowired
	ProdutoRepository produtoRepository;

	//CategoriaRepository responsável pela manipulação dos dados
	@Autowired
	CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoNelioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");

		Produto p1 = new Produto("Computador", 2000d);
		Produto p2 = new Produto("Impressora", 800d);
		Produto p3 = new Produto( "Mouse", 80d);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}
}
