# elasticsearch-java-test
To run elasticache, you can run with docker:
```
docker run -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -v /data/elasticsearch/:/usr/share/elasticsearch/data elasticsearch:7.5.2
```

With elasticache running, put your elasticache endpoint in ElasticSearchConnection.java file
```
private final String elasticSearchEndpoint = "?.?.?.?";
```

To create a lot of Documents, run the CreateDocument.java file with how much loop you want
```
for (int i = 0; i < 1; i++) {
```

To see a some search, look in SearchDocument.java

