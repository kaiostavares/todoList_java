package br.com.kaiotavares.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController// Faz com que o Spring gerencie isso 
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");//Pegando a string do atributo
        taskModel.setIdUSer((UUID)idUser);//Passando par o UUID
        var task = this.taskRepository.save(taskModel); //Vai gerar uma tarefa criada
        return task;
    }
    
}
