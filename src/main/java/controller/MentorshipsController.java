package controller;

import com.crud.crud.model.Aluno;
import com.crud.crud.model.Mentorships;
import com.crud.crud.service.MentorshipsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentorships")
public class MentorshipsController {
    public MentorshipsController as;

    @PostMapping("/save")
     public Mentorships save(@RequestBody Mentorships a){
        Mentorships novaMentoria = as.save(a);
        return novaMentoria;
    }

    @GetMapping("/findall")
    public List<Mentorships> findAll(){
        return (List<Mentorships>) as.findAll();
    }

    @GetMapping("/teste")
    public String teste(){
        return "Servidor Funcionando !";
    }
}
