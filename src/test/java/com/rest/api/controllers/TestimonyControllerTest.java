package com.rest.api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rest.api.enumerations.EnumTestimony;
import com.rest.api.models.Testimony;
import com.rest.api.services.testimony.TestimonyService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestimonyController.class)
public class TestimonyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestimonyService testimonyService;

    private Testimony testimonios;

    @BeforeEach
    void setUp() {
        testimonios = Testimony.builder()
                             .id(2)
                             .titulo("sin titulo")
                             .descripcion_corta("sin descricion por el momento")
                             .tipo(EnumTestimony.Tipo.Pandemia)
                             .build();
    }

    @Test
    public void saveTestimony() throws Exception{
         Testimony postTestimony = Testimony.builder()
                             .titulo("sin titulo")
                             .descripcion_corta("sin descricion por el momento")
                             .tipo(EnumTestimony.Tipo.Pandemia)
                             .build();

         Mockito.when(testimonyService.saveTestimony(postTestimony)).thenReturn(postTestimony);
         mockMvc.perform(post("/created").contentType(MediaType.APPLICATION_JSON)
                                                    .content("{\n" +
                                                             " \"titulo\":\"sin titulo\",\n"+
                                                             " \"descripcion_corta\":\"sin descricion por el momento y ahora\",\n"+
                                                             " \"tipo\":\"Pandemia\"\n"+
                                                            "}"))
                                                    .andExpect(status().isOk());
    }

    @Test
    public void findByTipo() throws Exception{
        Mockito.when(testimonyService.findTestimonyById(2)).thenReturn(testimonios);
        mockMvc.perform(get("/findTestimonyById/2")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.titulo").value(testimonios.getTitulo()));
    }


}
