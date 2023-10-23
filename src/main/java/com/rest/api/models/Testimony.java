package com.rest.api.models;


import com.rest.api.enumerations.EnumTestimony;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "testimonios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Testimony {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please add the titulo")
    @Size(min = 8, max = 25)
    private String titulo;

    @NotBlank
    @Size(min = 30, max = 60)
    private String descripcion_corta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumTestimony.Tipo tipo;

}
