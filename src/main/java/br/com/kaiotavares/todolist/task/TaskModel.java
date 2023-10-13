package br.com.kaiotavares.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
    /*
    * ID
    * Usuário(ID_USUARIO)
    * Descrição
    * Título
    * Data de início
    * Data de término
    * Prioridade
    */   
@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50)
    private StringBuffer title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID idUSer;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
