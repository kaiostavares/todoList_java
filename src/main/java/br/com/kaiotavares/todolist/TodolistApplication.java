package br.com.kaiotavares.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Anotation
//Nesse caso essa anotation é uma interface, essa anotatio do spring vai definir que essa é a classe inicial do projeto
//Quando iniciamos o projeto temos um tomcat por de baixo dos panos rodando o projeto
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
