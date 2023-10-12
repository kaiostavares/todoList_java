package br.com.kaiotavares.todolist.user;

import lombok.Data;

@Data //Faz todos os Getters e Setters
public class UserModel {
    private String username;
    private String name;
    private String password;
}