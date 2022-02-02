package br.com.bnck.elasticsearch.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 13:57
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "br.com.bnck.elasticsearch.repository")
@ComponentScan(basePackages = {"br.com.bnck.elasticsearch"})
public class Config extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String elasticsearchUrl;

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo(elasticsearchUrl)
                .build();
        return RestClients.create(config).rest();
    }
}
