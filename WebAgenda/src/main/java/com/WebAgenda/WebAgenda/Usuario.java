package com.WebAgenda.WebAgenda;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false)
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false, length = 2)
    private String uf;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Agenda> agendas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_alergia",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    private List<Alergia> alergias = new ArrayList<>();

    // Getters e Setters
}

