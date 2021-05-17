package com.example.vacinacaocomservice.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> novoCadastro(@Valid @RequestBody UsuarioRequest request) {
        //Valida se já existe um usuário com o E-mail ou CPF recebidos.
        usuarioService.validaDados(request);

        usuarioService.converterESalvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado!");


    }
}
