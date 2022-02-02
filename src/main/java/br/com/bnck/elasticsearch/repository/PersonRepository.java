package br.com.bnck.elasticsearch.repository;

import br.com.bnck.elasticsearch.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 14:13
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}
