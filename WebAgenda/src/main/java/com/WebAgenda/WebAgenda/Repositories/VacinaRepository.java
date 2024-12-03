package com.WebAgenda.WebAgenda.Repositories;

import com.WebAgenda.WebAgenda.Model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Integer> {
}
