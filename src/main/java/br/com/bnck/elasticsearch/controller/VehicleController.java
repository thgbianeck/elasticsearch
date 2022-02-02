package br.com.bnck.elasticsearch.controller;

import br.com.bnck.elasticsearch.document.Person;
import br.com.bnck.elasticsearch.document.Vehicle;
import br.com.bnck.elasticsearch.service.PersonService;
import br.com.bnck.elasticsearch.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 15:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    public void save(@RequestBody final Vehicle vehicle) {
        service.index(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable final String id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Vehicle> list() {
        return service.list();
    }

}
