package com.example.vacinacaocomservice.vacinacao;

import com.example.vacinacaocomservice.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class VacinacaoRequest {

    @NotBlank
    private String nomeVacina;

    public String getNomeVacina() {
        return nomeVacina;
    }

    public Vacinacao toModel(Usuario usuario) {
        return new Vacinacao(nomeVacina, usuario);
    }
}
