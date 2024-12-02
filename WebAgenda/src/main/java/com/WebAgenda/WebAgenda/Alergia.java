package com.WebAgenda.WebAgenda;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String nome;

    @ManyToMany(mappedBy = "alergias")
    private List<Usuario> usuarios = new ArrayList<>();

    // Getters e Setters
}
