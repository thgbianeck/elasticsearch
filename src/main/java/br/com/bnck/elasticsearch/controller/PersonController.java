package br.com.bnck.elasticsearch.controller;

import br.com.bnck.elasticsearch.document.Person;
import br.com.bnck.elasticsearch.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 14:20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService service;

    @PostMapping
    public void save(@RequestBody final Person person) {
        service.save(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable final String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Person> list() {
        return service.list();
    }

}
