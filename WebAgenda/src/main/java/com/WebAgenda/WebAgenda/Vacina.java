package com.WebAgenda.WebAgenda;

import jakarta.persistence.*;

@Entity
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String titulo;

    @Column(length = 200)
    private String descricao;

    @Column(nullable = false)
    private int doses;

    @Column(nullable = false)
    private int periodicidade;

    @Column(nullable = false)
    private int intervalo;

    // Getters e Setters
}
