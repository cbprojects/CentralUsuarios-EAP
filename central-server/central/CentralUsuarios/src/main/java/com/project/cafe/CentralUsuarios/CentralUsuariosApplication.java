package com.project.cafe.CentralUsuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.project.cafe.CentralUsuarios.CentralUsuariosApplication;

@SpringBootApplication
public class CentralUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralUsuariosApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CentralUsuariosApplication.class);
	}

}
