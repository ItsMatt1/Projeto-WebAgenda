package com.WebAgenda.WebAgenda.Repositories;

import com.WebAgenda.WebAgenda.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
}