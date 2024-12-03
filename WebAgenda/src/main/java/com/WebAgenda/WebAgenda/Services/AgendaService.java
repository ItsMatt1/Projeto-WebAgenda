package com.WebAgenda.WebAgenda.Services;

import com.WebAgenda.WebAgenda.Model.Agenda;
import com.WebAgenda.WebAgenda.Model.Situacao;
import com.WebAgenda.WebAgenda.Repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Agenda> buscarPorSituacao(Situacao situacao) {
        return agendaRepository.findBySituacao(situacao);
    }
}
