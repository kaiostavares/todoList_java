package br.com.kaiotavares.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data //Faz todos os Getters e Setters
@Entity(name="tb_users")
public class UserModel {

    @Id//Indicando que vai ser nossa chave primária
    @GeneratedValue(generator = "UUID")
    private UUID id; //Atributo de chave primária

    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}