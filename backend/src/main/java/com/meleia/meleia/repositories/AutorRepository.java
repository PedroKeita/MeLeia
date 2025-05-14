package com.meleia.meleia.repositories;

import com.meleia.meleia.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
