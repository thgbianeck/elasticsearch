package br.com.bnck.elasticsearch.service;

import br.com.bnck.elasticsearch.helper.Indices;
import br.com.bnck.elasticsearch.helper.Util;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 15:32
 */
@Slf4j
@Service
public class IndexService {

    private final List<String> INDICES_TO_CREATE = List.of(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient client;

    @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndex() {
        recreateIndices(false);
    }

    public void recreateIndices(final boolean deleteExisting) {
        final String settings = Util.loadAsString("static/es-settings.json");

        if(Objects.isNull(settings)) {
            log.error("Failed to load index settings");
            return;
        }

        for(final String indexName: INDICES_TO_CREATE) {
            try{
                boolean indexExists = client
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if(indexExists) {
                    if(!deleteExisting) {
                        continue;
                    }
                    client.indices().delete(
                            new DeleteIndexRequest(indexName),
                            RequestOptions.DEFAULT
                    );
                }

                final String mappings = Util.loadAsString("static/mappings/" + indexName + ".json");
                if(Objects.isNull(settings) || Objects.isNull(mappings)) {
                    log.error("Filed to create index with name '{}'", indexName);
                    continue;
                }

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(mappings, XContentType.JSON);

                client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

            } catch(final Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
