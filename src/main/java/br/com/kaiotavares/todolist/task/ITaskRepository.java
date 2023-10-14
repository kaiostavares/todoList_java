package br.com.kaiotavares.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUSer(UUID idUSer); //Pedir para trazer as tasks somente no id do usuário
}
