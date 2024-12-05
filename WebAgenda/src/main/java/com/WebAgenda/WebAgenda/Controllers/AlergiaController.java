package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Model.Alergia;
import com.WebAgenda.WebAgenda.Repositories.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaRepository alergiaRepository;

    // Criar uma nova alergia
    @PostMapping
    public Alergia criarAlergia(@RequestBody Alergia alergia) {
        return alergiaRepository.save(alergia);
    }

    // Listar todas as alergias
    @GetMapping
    public List<Alergia> listarAlergias() {
        return alergiaRepository.findAll();
    }

    // Buscar alergia por ID
    @GetMapping("/{id}")
    public Alergia buscarAlergiaPorId(@PathVariable Integer id) {
        return alergiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alergia não encontrada com o ID: " + id));
    }

    // Atualizar uma alergia existente
    @PutMapping("/{id}")
    public Alergia atualizarAlergia(@PathVariable Integer id, @RequestBody Alergia alergiaAtualizada) {
        Alergia alergia = alergiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alergia não encontrada com o ID: " + id));

        alergia.setNome(alergiaAtualizada.getNome());
        return alergiaRepository.save(alergia);
    }

    // Deletar uma alergia
    @DeleteMapping("/{id}")
    public void deletarAlergia(@PathVariable Integer id) {
        alergiaRepository.deleteById(id);
    }
}