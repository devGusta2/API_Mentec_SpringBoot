package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud.model.Aluno;
import com.crud.crud.service.AlunoServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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
        return (List<Aluno>) as.findAll();
    }

    @GetMapping("/teste")
    public String teste(){
        return "Servidor Funcionando !";
    }

    
}
