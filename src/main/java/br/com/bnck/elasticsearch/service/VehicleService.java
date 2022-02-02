package br.com.bnck.elasticsearch.service;

import br.com.bnck.elasticsearch.document.Person;
import br.com.bnck.elasticsearch.document.Vehicle;
import br.com.bnck.elasticsearch.helper.Indices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 15:45
 */
@Slf4j
@Service
public class VehicleService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final RestHighLevelClient client;

    @Autowired
    public VehicleService(RestHighLevelClient client) {
        this.client = client;
    }

    public Boolean index(final Vehicle vehicle) {
        try{
            final String vehicleAsString = MAPPER.writeValueAsString(vehicle);
            final IndexRequest request = new IndexRequest(Indices.VEHICLE_INDEX);
            request.id(vehicle.getId());
            request.source(vehicleAsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);

        } catch(final Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public Vehicle getById(final String vehicleId) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(Indices.VEHICLE_INDEX, vehicleId),
                    RequestOptions.DEFAULT
            );
            if(Objects.isNull(documentFields) || documentFields.isSourceEmpty()) {
                return null;
            }

            return MAPPER.readValue(documentFields.getSourceAsString(), Vehicle.class);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Vehicle> list() {
        try {
            GetResponse documentFields = client.get(
                    new GetRequest(Indices.VEHICLE_INDEX),
                    RequestOptions.DEFAULT
            );
            if(Objects.isNull(documentFields)) {
                return Collections.emptyList();
            }
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }
}
