package com.crud.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
@Entity
public class Mentorships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
}
