package com.nelioalves.curso;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.domain.Cidade;
import com.nelioalves.curso.domain.Estado;
import com.nelioalves.curso.domain.Produto;
import com.nelioalves.curso.repositories.CategoriaRepository;
import com.nelioalves.curso.repositories.CidadeRepository;
import com.nelioalves.curso.repositories.EstadoRepository;
import com.nelioalves.curso.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner {

	//REPOSITORIES responsáveis pela manipulação dos dados
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;


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

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");

		Cidade cid1 = new Cidade("Uberlândia");
		Cidade cid2 = new Cidade("São Paulo");
		Cidade cid3 = new Cidade("Campinas");

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		cid1.setEstado(est1);
		cid2.setEstado(est2);
		cid3.setEstado(est2);

		//salvar primeiro os estados, pois não se tem uma cidade sem estado
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));


	}
}
