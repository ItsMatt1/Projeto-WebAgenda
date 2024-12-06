package com.WebAgenda.WebAgenda.Repositories;

import com.WebAgenda.WebAgenda.Model.Agenda;
import com.WebAgenda.WebAgenda.Model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findBySituacao(Situacao situacao);

    @Query("SELECT a FROM Agenda a WHERE a.data = :data ORDER BY " +
            "CASE a.situacao " +
            "  WHEN 'AGENDADO' THEN 1 " +
            "  WHEN 'REALIZADO' THEN 2 " +
            "  WHEN 'CANCELADO' THEN 3 " +
            "END")
    List<Agenda> findAgendasDoDiaOrdenadas(@Param("data") Date data);

    List<Agenda> findByUsuarioId(Integer usuarioId);

    long countBySituacao(Situacao situacao);
}