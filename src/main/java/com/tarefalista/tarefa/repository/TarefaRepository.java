package com.tarefalista.tarefa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefalista.tarefa.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
