package com.crud.crud.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.crud.crud.model.Mentorships;
import com.crud.crud.repository.MentorshipsRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Mentorships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String teacher;
    private String place;
    private String frequency;
    private String requirements;
    private int duration;
    private String goal;
    public Long getId() {
            return id;
        }
         public void setId(Long id) {
        this.id = id;
    }
    public void setGoal(String goal){
            this.goal = goal;
        }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setTeacher(String teacher){
        this.teacher =  teacher;
    }
    public void setPlace(String place){
        this.place = place;
    }
    public void setFrequency(String frequency){
        this.frequency = frequency;
    }
    public void setRequirements(String requirements){
        this.requirements = requirements;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getTeacher(){
        return teacher;
    }
    public String getPlace(){
        return place;
    }
    public String getFrequency(){
        return frequency;
    }
    public String getRequirements(){
        return requirements;
    }
     public String getGoal(){
        return goal;
    }
    public int getDuration(){
        return duration;
    }
}
