package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Agenda;
import com.WebAgenda.WebAgenda.Repositories.AgendaRepository;
import com.WebAgenda.WebAgenda.Situacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
    @Autowired
    private AgendaRepository agendaRepository;

    @PostMapping
    public Agenda criarAgenda(@RequestBody Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    @GetMapping
    public List<Agenda> listarAgendas() {
        return agendaRepository.findAll();
    }

    @GetMapping("/canceladas")
    public List<Agenda> listarCanceladas() {
        return agendaRepository.findBySituacao(Situacao.CANCELADO);
    }

    @GetMapping("/realizadas")
    public List<Agenda> listarRealizadas() {
        return agendaRepository.findBySituacao(Situacao.REALIZADO);
    }

    @PutMapping("/{id}/baixa")
    public Agenda darBaixa(@PathVariable Integer id, @RequestParam Situacao situacao) {
        Agenda agenda = agendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Agenda não encontrada"));
        agenda.setSituacao(situacao);
        agenda.setDataSituacao(new Date());
        return agendaRepository.save(agenda);
    }
}
