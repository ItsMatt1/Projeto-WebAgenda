package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Model.Situacao;
import com.WebAgenda.WebAgenda.Repositories.AgendaRepository;
import com.WebAgenda.WebAgenda.Repositories.UsuarioRepository;
import com.WebAgenda.WebAgenda.Repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping("/estatisticas")
    public ResponseEntity<Map<String, Object>> getEstatisticas() {
        Map<String, Object> estatisticas = new HashMap<>();

        // Total de usuários
        estatisticas.put("totalUsuarios", usuarioRepository.count());

        // Total de vacinas
        estatisticas.put("totalVacinas", vacinaRepository.count());

        // Total de agendas por situação
        estatisticas.put("totalAgendasRealizadas", agendaRepository.countBySituacao(Situacao.REALIZADO));
        estatisticas.put("totalAgendasCanceladas", agendaRepository.countBySituacao(Situacao.CANCELADO));
        estatisticas.put("totalAgendasAgendadas", agendaRepository.countBySituacao(Situacao.AGENDADO));

        // Percentual de agendas realizadas
        long totalAgendas = agendaRepository.count();
        long totalRealizadas = agendaRepository.countBySituacao(Situacao.REALIZADO);
        estatisticas.put("percentualRealizadas", totalAgendas == 0 ? 0 : (totalRealizadas * 100.0 / totalAgendas));

        return ResponseEntity.ok(estatisticas);
    }
}
