package com.crud.crud.repository;
import org.springframework.data.repository.CrudRepository;
// OU  import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.crud.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    // métodos manipulação de dados personalizados, são colocados aqui
    
} 
