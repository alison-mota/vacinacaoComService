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
        Usuario usuarioModel = vacinacaoService.localizaUsuario(usuarioId);

        vacinacaoService.salvaVacina(usuarioModel, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vacinação salva!");

    }
}
