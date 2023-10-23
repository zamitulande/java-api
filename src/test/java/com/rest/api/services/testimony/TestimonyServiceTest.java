package com.rest.api.services.testimony;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rest.api.models.Testimony;
import com.rest.api.repositories.testimony.TestimonyRepository;

@SpringBootTest
public class TestimonyServiceTest {


    @Autowired
    private TestimonyService testimonyService;

    @MockBean
    private TestimonyRepository testimonyRepository;

    @BeforeEach
    void setUp() {
            Testimony testimony = Testimony.builder()
                                           .id(2)
                                           .titulo("bomba en casa")
                                           .descripcion_corta("descripcion alguna del testimonio")
                                           .build();

            Mockito.when(testimonyRepository.findWithJPQL("bomba en casa")).thenReturn(Collections.singletonList(testimony));
    }

    @Test
    @DisplayName("Prueba de otencion de informacion de un testimonio enviando un titulo valido")
    public void findByTituloShouldFonund(){
        String titulo = "bomba en casa";
        List<Testimony> testimony = testimonyService.findWithJPQL(titulo);
        for (Testimony testimony2 : testimony) {
            assertEquals(titulo, testimony2.getTitulo());
        }
    }
}
