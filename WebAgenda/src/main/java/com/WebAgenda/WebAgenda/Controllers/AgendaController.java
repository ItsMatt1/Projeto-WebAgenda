package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Model.Agenda;
import com.WebAgenda.WebAgenda.Model.Usuario;
import com.WebAgenda.WebAgenda.Model.Vacina;
import com.WebAgenda.WebAgenda.Repositories.AgendaRepository;
import com.WebAgenda.WebAgenda.Model.Situacao;
import com.WebAgenda.WebAgenda.Repositories.UsuarioRepository;
import com.WebAgenda.WebAgenda.Repositories.VacinaRepository;
import com.WebAgenda.WebAgenda.Services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<?> createAgenda(@RequestBody AgendaRequest agenda) throws Throwable {
        Usuario usuario = usuarioRepository.findById(agenda.getUsuario_id())
                .orElseThrow(() -> new Throwable("Usuário não encontrado"));
        Vacina vacina = vacinaRepository.findById(agenda.getVacina_id())
                .orElseThrow(() -> new Throwable("Vacina não encontrada"));

        Agenda agenda2 = new Agenda();
        agenda2.setData(agenda.getData());
        agenda2.setSituacao(agenda.getSituacao());
        agenda2.setDataSituacao(agenda.getDataSituacao());
        agenda2.setUsuario(usuario);
        agenda2.setVacina(vacina);

        agendaRepository.save(agenda2);
        return ResponseEntity.ok("Agenda criada com sucesso!");
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
        agenda.setDataSituacao(new Date(0));
        return agendaRepository.save(agenda);
    }

    @GetMapping("/situacao")
    public List<Agenda> buscarPorSituacao(@RequestParam Situacao situacao) {
        return agendaService.buscarPorSituacao(situacao);
    }
}
