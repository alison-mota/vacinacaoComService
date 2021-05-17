package com.example.vacinacaocomservice.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public void validaDados(UsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um usuário cadastrado com esse E-mail.");
        } else if (usuarioRepository.existsByCpf(request.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um usuário cadastrado com esse CPF.");
        }
    }

    public void converterESalvar(UsuarioRequest request) {
        Usuario usuario = request.toModel();
        usuarioRepository.save(usuario);
    }
}