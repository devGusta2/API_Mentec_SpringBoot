package com.crud.crud.controller;

import com.crud.crud.model.Aluno;
import com.crud.crud.model.Mentorships;
import com.crud.crud.repository.MentorshipsRepository;
import com.crud.crud.service.MentorshipsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mentorships")
public class MentorshipsController {
 

    @Autowired
    private MentorshipsRepository repository;

    @PostMapping("/save")
    public Mentorships save(@RequestBody Mentorships a) {
        return repository.save(a);
    }

    @GetMapping("/findall")
    public List<Mentorships> findAll() {
        return repository.findAll();
    }

    @GetMapping("/teste")
    public String teste() {
        return "Servidor Funcionando !";
    }
    



    

    @PutMapping("/update/{id}")
public ResponseEntity<Mentorships> atualizar(
        @PathVariable Long id,
        @RequestBody Mentorships newMentorship) {

    Mentorships mentorship = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mentoria com ID " + id + " não encontrada."));

    mentorship.setTitle(newMentorship.getTitle());
    mentorship.setDescription(newMentorship.getDescription());

    if (newMentorship.getGoal() != null && !newMentorship.getGoal().isBlank()) {
        mentorship.setGoal(newMentorship.getGoal());
    }

    Mentorships updated = repository.save(mentorship);
    return ResponseEntity.ok(updated);
}











    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "Mnetoria com ID " + id + " foi deletado com sucesso.";
    }

  
}
