package br.com.kaiotavares.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; //Response Entity cuidade de retornar em caso de erro e sucesso
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //Todo ciclo é gerenciado pelo Spring
    private IUserRepository userRepository;

    @PostMapping("/") //Como a gente está criando, vêm de um método POST
    public ResponseEntity create(@RequestBody UserModel userModel){ //ResquestBody significa que oq eu estou recebendo o body da requisição
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            System.out.println("Usuário já cadastrado");
            return ResponseEntity.status(400).body("Usuário já existe");
        }
            var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
            userModel.setPassword(passwordHashred);
        //As duas linhas na parte de cima são responsáveis por encriptar nossa senha
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
