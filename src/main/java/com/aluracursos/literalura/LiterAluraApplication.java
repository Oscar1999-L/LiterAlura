package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.Principal;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aluracursos.literalura.repository.LiterRepository")
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LiterRepository literRepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {

		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(literRepository, autorRepository);
		principal.muestraElMenu();
	}


}
