package com.example.vacinacaocomservice.vacinacao;

import com.example.vacinacaocomservice.usuario.Usuario;
import com.example.vacinacaocomservice.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VacinacaoService {

    private final VacinacaoRepository vacinacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public VacinacaoService(VacinacaoRepository vacinacaoRepository, UsuarioRepository usuarioRepository) {
        this.vacinacaoRepository = vacinacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Método responsável por devolver um objeto tipo Usuário ou tratar Null/branco
    public Usuario localizaUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    //Método responsável por converter um objeto Request para uma Vacinação e salvar no banco
    public void salvaVacina(Usuario usuarioModel, VacinacaoRequest request) {
        Vacinacao vacinacao = request.toModel(usuarioModel);
        vacinacaoRepository.save(vacinacao);
    }
}