package com.example.vacinacaocomservice.usuario;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Column(unique = true)
    @NotBlank
    @Email
    private String email;
    @Column(unique = true, length = 11)
    @NotBlank
    @CPF
    private String cpf;
    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    public Usuario(String nome, String email, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Deprecated
    public Usuario() {

    }
}
