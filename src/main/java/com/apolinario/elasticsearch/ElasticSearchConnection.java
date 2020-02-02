package com.apolinario.elasticsearch;

import java.net.UnknownHostException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchConnection {
	
	private final String elasticSearchEndpoint = "?.?.?.?";
	private final int elasticSearchPort = 9200;

	public RestHighLevelClient createConnection() throws UnknownHostException {
		RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost(elasticSearchEndpoint, elasticSearchPort, "http")));
        return client;
	}

}
