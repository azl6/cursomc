package com.nelioalves.curso;

import com.nelioalves.curso.domain.Categoria;
import com.nelioalves.curso.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner {

	//CategoriaRepository responsável pela manipulação dos dados
	@Autowired
	CategoriaRepository cr;

	public static void main(String[] args) {
		SpringApplication.run(CursoNelioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		cr.saveAll(Arrays.asList(cat1,cat2));
	}
}
