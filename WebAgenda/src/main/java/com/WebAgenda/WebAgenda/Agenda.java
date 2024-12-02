package com.WebAgenda.WebAgenda;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Situacao situacao;

    @Column
    private Date dataSituacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vacina_id", nullable = false)
    private Vacina vacina;

    // Getters e Setters
}

