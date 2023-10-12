package br.com.kaiotavares.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //Todo ciclo é gerenciado pelo Spring
    private IUserRepository userRepository;

    @PostMapping("/") //Como a gente está criando, vêm de um método POST
    public UserModel create(@RequestBody UserModel userModel){ //ResquestBody significa que oq eu estou recebendo o body da requisição
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            System.out.println("Usuário já cadastrado");
            return null;
        }
        var userCreated = this.userRepository.save(userModel);
        return userCreated;
    }
}
