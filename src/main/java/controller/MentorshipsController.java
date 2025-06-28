package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crud.crud.model.Mentorships;
import com.crud.crud.repository.MentorshipsRepository;

@RestController
@RequestMapping("/mentorships")
public class MentorshipsController {

    @Autowired
    private MentorshipsRepository mentorshipsRepository;

    @GetMapping("/findall")
    public List<Mentorships> findAll() {
        return mentorshipsRepository.findAll();
    }
}
