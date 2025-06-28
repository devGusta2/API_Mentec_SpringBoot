package com.crud.crud.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crud.crud.repository.MentorshipsRepository;
import com.crud.crud.model.Mentorships;
public class MentorshipsServices implements MentorshipsRepository{
    private final MentorshipsRepository mentorshipsRepository;
    public mentorshipsServices(MentorshipsRepository mentorshipsRepository){
        this.mentorshipsRepository = mentorshipsRepository;
    }
}
