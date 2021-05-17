package com.example.vacinacaocomservice.vacinacao;

import com.example.vacinacaocomservice.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Vacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nomeVacina;
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @Column(updatable = false)
    private final LocalDateTime dataAplicacao = LocalDateTime.now();

    public Vacinacao(String nomeVacina, Usuario usuario) {
        this.nomeVacina = nomeVacina;
        this.usuario = usuario;
    }

    @Deprecated
    public Vacinacao() {
    }
}
