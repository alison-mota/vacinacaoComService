package com.example.vacinacaocomservice.vacinacao;

import com.example.vacinacaocomservice.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/vacinacao")
public class VacinacaoController {

    private final VacinacaoService vacinacaoService;

    public VacinacaoController(VacinacaoService vacinacaoService) {
        this.vacinacaoService = vacinacaoService;
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<String> novaVacinacao(@Valid @RequestBody VacinacaoRequest request, @PathVariable Long usuarioId){

        //Método responsável por devolver um objeto tipo Usuário ou tratar Null/branco
        Usuario usuarioModel = vacinacaoService.localizaUsuario(usuarioId);

        //Método responsável por converter um objeto Request para uma Vacinação e salvar no banco
        vacinacaoService.salvaVacina(usuarioModel, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vacinação salva!");

    }
}
