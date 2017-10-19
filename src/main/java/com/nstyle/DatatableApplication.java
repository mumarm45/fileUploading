package com.nstyle;

import com.nstyle.Service.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatatableApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatatableApplication.class, args);
	}
	@Bean
	CommandLineRunner init(FileService fileService) {
		return (args) -> {
			fileService.deleteAll();
			fileService.init();
		};
	}
}
