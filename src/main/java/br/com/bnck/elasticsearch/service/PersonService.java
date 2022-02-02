package br.com.bnck.elasticsearch.service;

import br.com.bnck.elasticsearch.document.Person;
import br.com.bnck.elasticsearch.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 14:15
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public void save(final Person person) {
        repository.save(person);
    }

    public Person findById(final String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Person> list() {
        List<Person> result = new ArrayList<>();
        Iterable<Person> all = repository.findAll();
        all.forEach(result::add);
        return result;
    }
}
