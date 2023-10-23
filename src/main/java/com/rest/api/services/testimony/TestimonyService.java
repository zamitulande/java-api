package com.rest.api.services.testimony;

import java.util.List;
import java.util.Optional;

import com.rest.api.enumerations.EnumTestimony;
import com.rest.api.errors.ExceptionNotFound;
import com.rest.api.models.Testimony;

public interface TestimonyService {
    
    List<Testimony> findAllTestimonies();

    Testimony saveTestimony(Testimony testimony);

    Testimony updateTestimony( Testimony testimony, Integer id);

    void deleteTestimony(Integer id);

    List<Testimony> findWithJPQL(String titulo);

    Testimony findTestimonyById(Integer id) throws ExceptionNotFound;

    Optional<Testimony> findByDescripcionCorta(String descripcion_corta);

    List<Testimony> findByTipo(EnumTestimony.Tipo tipo);



}
