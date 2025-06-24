package com.crud.crud.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
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
    public void setRequirements(String requierements){
        this.requirements = requirements;
    }

}
