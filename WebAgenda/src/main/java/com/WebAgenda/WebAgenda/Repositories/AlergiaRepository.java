package com.WebAgenda.WebAgenda.Repositories;

import com.WebAgenda.WebAgenda.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {
}