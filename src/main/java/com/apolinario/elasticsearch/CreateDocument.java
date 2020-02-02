
package com.apolinario.elasticsearch;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

//https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.5/java-rest-high-supported-apis.html
public class CreateDocument {

	public static void main(String[] args) throws IOException {
		System.out.println("start...");
		CreateDocument elasticsearchApplication = new CreateDocument();
		System.out.println("creating ES conn...");
		RestHighLevelClient createConnection = (new ElasticSearchConnection()).createConnection();
		System.out.println("creating new document...");
		for (int i = 0; i < 500000; i++) {
			elasticsearchApplication.createNewDocument(createConnection);
		}
		System.out.println("end...");
		createConnection.close();
	}
	
	public void createNewDocument(RestHighLevelClient client) {
		try {
			DocumentDTO documentDTO = new DocumentDTO();
			documentDTO.generateContent();
			System.out.println("creating document: " + documentDTO.getSomeName() + " | " + documentDTO.getNumber() + " | " +documentDTO.getFullText());
			XContentBuilder builder = XContentFactory.jsonBuilder()
					  .startObject()
					  .field("someName", documentDTO.getSomeName())
					  .field("fullText", documentDTO.getFullText())
					  .field("date", documentDTO.getDate())
					  .field("number", documentDTO.getNumber())
					  .endObject();
			
			System.out.println("createNewDocument: ");
			System.out.println(builder);
			
			IndexRequest indexRequest = new IndexRequest(Constants.index).source(builder);
			indexRequest.timeout(TimeValue.timeValueSeconds(1));
			IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println("index: "+indexResponse.getIndex());
			System.out.println("id: "+indexResponse.getId());
			System.out.println("result: "+indexResponse.getResult());
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
