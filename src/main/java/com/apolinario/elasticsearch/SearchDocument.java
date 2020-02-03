package com.apolinario.elasticsearch;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SearchDocument {

	public static void main(String[] args) throws IOException {
		System.out.println("start...");
		SearchDocument elasticsearchApplication = new SearchDocument();
		System.out.println("creating ES connection...");
		RestHighLevelClient createConnection = (new ElasticSearchConnection()).createConnection();
		System.out.println("searching document...");
		long timeTook = 0;
		int total_requests = 100;
		for (int i = 0; i < total_requests; i++) {
			timeTook += elasticsearchApplication.searchContent(createConnection).millis();
		}
		System.out.println("time total: "+timeTook+" | avg: "+(timeTook/total_requests));
		System.out.println("end...");
		createConnection.close();
	}
	
	
	//https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.5/java-rest-high-search.html
	private TimeValue searchContent(RestHighLevelClient client) {
		SearchRequest searchRequest = new SearchRequest(Constants.index);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		// Text search here, what you want to search?
//		String createFakeText = "A*";
		String createFakeText = (new DocumentFactor()).createFakeText(5); //randon search
		
		System.out.println("search: "+createFakeText);
		
		MatchQueryBuilder queryStringQuery = QueryBuilders.matchQuery("fullText", createFakeText);
		
		// Search filter, you want to search only the text? other field too?
		BoolQueryBuilder bqb = QueryBuilders.boolQuery();
		bqb.filter(QueryBuilders.matchQuery("number", 253));
		bqb.filter(queryStringQuery);
		
		searchSourceBuilder.query(queryStringQuery);
		searchSourceBuilder.from(0);
		searchSourceBuilder.size(5);
		searchSourceBuilder.timeout(new TimeValue(5, TimeUnit.SECONDS));
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestStatus status = searchResponse.status();
		TimeValue took = searchResponse.getTook();
		Boolean terminatedEarly = searchResponse.isTerminatedEarly();
		boolean timedOut = searchResponse.isTimedOut();
		System.out.println("{ status: "+status+", took: "+took+", terminatedEarly: "+terminatedEarly+", timedOut: "+timedOut+"}");

		SearchHits hits = searchResponse.getHits();
		TotalHits totalHits = hits.getTotalHits();
		long numHits = totalHits.value;
//		TotalHits.Relation relation = totalHits.relation;
		float maxScore = hits.getMaxScore();
		System.out.println("{ hits: "+numHits+", maxScore:"+maxScore+"}");
		/*SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
//			String index = hit.getIndex();
			String id = hit.getId();
//			float score = hit.getScore();
			
//			String sourceAsString = hit.getSourceAsString();
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			String someName = (String) sourceAsMap.get("someName");
			String fullText = (String) sourceAsMap.get("fullText");
			String date = (String) sourceAsMap.get("date");
			double number = (Double) sourceAsMap.get("number");
			System.out.println("{ id: "+id+",someName: "+someName+", number: "+number+", date: "+date+", fullText: "+fullText+"}");
			
		}*/
		return took;
	}
}
