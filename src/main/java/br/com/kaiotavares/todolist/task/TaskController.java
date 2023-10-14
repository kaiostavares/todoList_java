package br.com.kaiotavares.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiotavares.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController// Faz com que o Spring gerencie isso 
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");//Pegando a string do atributo
        taskModel.setIdUSer((UUID)idUser);//Passando par o UUID

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de início/ data de término deve ser maior do que a data atual");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A data de início deve ser menor do que a data de término");
        }
        var task = this.taskRepository.save(taskModel); //Vai gerar uma tarefa criada
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/") //Trazer tudo relacionado ao usuário
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUSer((UUID) idUser);
        return tasks;
    }
    
    // htpp://localhost:8080/tasks/9128918-asjgahsha-21776217
    @PutMapping("/{id}")
    //PathVariable relacionado ao id correspondente a determinada task da nossa aplicação
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        var task = this.taskRepository.findById(id).orElse(null);

        if(task==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Tarefa não encontrada");
        }

        var idUser = request.getAttribute("idUser");
        if(!task.getIdUSer().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Usuário não têm permissão para alterar essa tarefa");
        }
        Utils.copyNonNullProperties(taskModel, task);//Mesclando os dados nulos com não nulos
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }
}
