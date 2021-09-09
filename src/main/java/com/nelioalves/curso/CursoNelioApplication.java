package com.nelioalves.curso;

import com.nelioalves.curso.domain.*;
import com.nelioalves.curso.enums.TipoCliente;
import com.nelioalves.curso.repositories.*;
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
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;


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

		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefone().addAll(Arrays.asList("27363323","93838393"));

		Endereco e1 = new Endereco("Rua Flores", "300", "Apto. 203", "Jardim", "38220834", cli1, cid1);
		Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 600", "Centro", "38777012", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

	}
}
