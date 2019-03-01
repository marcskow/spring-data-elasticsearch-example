package com.marcskow.spring.bootstrap.java.search.elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.marcskow.spring.bootstrap.java.search.elastic")
@ComponentScan(basePackages = {"com.marcskow.spring.bootstrap.java.search.elastic.repository"})
public class ElasticSearchConfiguration {

    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

    @Bean
    public Client client() {
        try {
            Settings elasticsearchSettings = Settings.builder()
                    .put("client.transport.sniff", true)
                    .put("cluster.name", clusterName).build();
            TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
            client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            return client;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
