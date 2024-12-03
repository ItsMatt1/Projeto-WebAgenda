package com.WebAgenda.WebAgenda.Repositories;

import com.WebAgenda.WebAgenda.Model.Agenda;
import com.WebAgenda.WebAgenda.Model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findBySituacao(Situacao situacao);
}