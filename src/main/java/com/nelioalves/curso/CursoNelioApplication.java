package com.nelioalves.curso;

import com.nelioalves.curso.domain.*;
import com.nelioalves.curso.enums.EstadoPagamento;
import com.nelioalves.curso.enums.TipoCliente;
import com.nelioalves.curso.repositories.*;
import com.nelioalves.curso.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner {


	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursoNelioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	s3Service.uploadFile("C:\\temp\\hjkm.png");
	}
}
