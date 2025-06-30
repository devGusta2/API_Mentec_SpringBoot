package controller;

import com.crud.crud.model.Mentorships;
import com.crud.crud.repository.MentorshipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
