package br.com.bnck.elasticsearch.controller;

import br.com.bnck.elasticsearch.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 16:18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/index")
public class IndexController {

    private final IndexService service;

    @PostMapping("/recreate")
    public void recreateAllIndices() {
        service.recreateIndices(true);
    }
}
