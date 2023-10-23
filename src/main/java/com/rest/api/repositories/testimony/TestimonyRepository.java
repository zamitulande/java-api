package com.rest.api.repositories.testimony;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.api.enumerations.EnumTestimony;
import com.rest.api.models.Testimony;






public interface TestimonyRepository extends JpaRepository<Testimony, Integer> {
    
    @Query("SELECT t FROM Testimony t WHERE t.titulo = :titulo")
    List<Testimony> findWithJPQL(String titulo);

    @Query("SELECT t FROM Testimony t WHERE t.descripcion_corta = :descripcion_corta")
    Optional<Testimony> findByDescripcionCorta(String descripcion_corta);

    @Query("SELECT t FROM Testimony t WHERE t.tipo = :tipo")
    List<Testimony> findByTipo(EnumTestimony.Tipo tipo);
}
