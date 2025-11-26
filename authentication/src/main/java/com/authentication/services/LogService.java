package com.authentication.services;

import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.elasticsearch.client.RestClient;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import com.authentication.models.Log;

@Service
public class LogService {

    private final String indexName = "authentication-service";
    private ElasticsearchClient client;
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    private LogService() {
        RestClient restClient = RestClient.builder(
                new HttpHost("elasticsearch", 9200, "http")).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

         client = new ElasticsearchClient(transport);
    }

    public void saveLog(Log log) {
        try {
            IndexResponse response = client.index(i -> i
                    .index(indexName)
                    .id(log.getUuid().toString())
                    .document(log));

            logger.info("Elasticsearch log indexed with ID: {}, version: {}", response.id(), response.version());
        } catch (IOException e) {
            logger.error("Elasticsearch log failed: {}", e.getMessage());
        }
    }
}
