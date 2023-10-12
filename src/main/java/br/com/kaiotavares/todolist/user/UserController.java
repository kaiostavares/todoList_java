package br.com.kaiotavares.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/") //Como a gente está criando, vêm de um método POST
    public void create(@RequestBody UserModel userModel){ //ResquestBody significa que oq eu estou recebendo o body da requisição
        System.out.println(userModel.getUsername());
    }
}
