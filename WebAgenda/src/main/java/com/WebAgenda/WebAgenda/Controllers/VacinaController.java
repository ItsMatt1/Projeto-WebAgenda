package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Vacina;
import com.WebAgenda.WebAgenda.Repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {
    @Autowired
    private VacinaRepository vacinaRepository;

    @PostMapping
    public Vacina criarVacina(@RequestBody Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    @GetMapping
    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }
}
