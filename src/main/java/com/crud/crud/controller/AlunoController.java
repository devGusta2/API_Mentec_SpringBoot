package com.crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud.model.Aluno;
import com.crud.crud.service.AlunoServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoServices as;

    @PostMapping("/save")
    public Aluno save(@RequestBody Aluno a){
        Aluno novoAluno = as.save(a);
        return novoAluno;
    }

    @GetMapping("/findall")
    public List<Aluno> findAll(){
        return as.listarTodos();
    }

    @GetMapping("/teste")
    public String teste(){
        return "Servidor Funcionando !";
    }

    
    @PutMapping("/update/{id}")
    public Aluno update(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        return as.atualizar(id, alunoAtualizado);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        as.deletar(id);
        return "Aluno com ID " + id + " foi deletado com sucesso.";
    }
    

    
}
