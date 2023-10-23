package com.rest.api.repositories.testimony;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.rest.api.enumerations.EnumTestimony;
import com.rest.api.models.Testimony;

@DataJpaTest
public class TestimonyRepositoryTest {

    
    @Autowired
    TestimonyRepository testimonyRepository;

    @Autowired
    TestEntityManager testEntityManager;

 

    @BeforeEach
    void setUp() {
         Testimony testimony =
                    Testimony.builder()
                             .titulo("cualquierparte")
                             .descripcion_corta("cualquier parte del texto que aparece")
                             .tipo(EnumTestimony.Tipo.Pandemia)
                             .build();
        testEntityManager.persist(testimony);
    }



    //CUANDO EXISTE EL TITULO EN LA BASE DE DATOS (h2)
    @Test
    public void findTestimonyTituloFound(){
        List<Testimony> testimonio = testimonyRepository.findWithJPQL("cualquierparte");
        for (Testimony testimony : testimonio) {
            assertEquals("cualquierparte", testimony.getTitulo());
        }        
    }

    //CUANDO NO EXISTE LA DESCRIPCION EN LA BASE DE DATOS (h2)
    @Test
    public void findTestimonyDescripcionCortaNotFound(){
        Optional<Testimony> testimonio = testimonyRepository.findByDescripcionCorta("ESTA DESCRIPCION NO EXISTE");
        assertEquals(testimonio, Optional.empty());
    }
   
}
