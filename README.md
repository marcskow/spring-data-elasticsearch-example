## Spring + ElasticSearch example

This project contains SpringBoot Application with Spring Data Elasticsearch extension. 

The idea is to have main data storage in postgres with additional indexing in Elasticsearch
for some entities. Advanced and fast search is not needed for all tables, 
what's more many people are not familiar with Elasticsearch, with many reasons it might not be the best idea
to use it as primary data source. 

Example will be further developed.

Spring Data Elasticsearch description can be found at:  
https://spring.io/projects/spring-data-elasticsearch

### Dependencies versions

| Component | Version |
| ----- | ----- |
| SpringBoot | 2.1.3 |
| Spring Data Elasticsearch| 3.1.5 |
| ElasticSearch (Docker)| 6.2.4 |

Spring Data Elasticsearch supports particular versions of ElasticSearch 
so it is relevant to check which version should be installed.
Updated version matrix should be available [here](https://github.com/spring-projects/spring-data-elasticsearch), 
but I copied the state that was in the creation time of my example:

| Spring Data Elasticsearch	| Elasticsearch |
| ----- | ----- |
| 3.2.x | 6.5.0 |
| 3.1.x	| 6.2.2 |
| 3.0.x	| 5.5.0 |
| 2.1.x	| 2.4.0 |
| 2.0.x	| 2.2.0 |
| 1.3.x	| 1.5.2 |

### Additional Info

Transport Client is currently preferred way to be used with Spring Data Elasticsearch
because Elasticsearch Rest Client is not fully supported yet. My suggestion is to use 
Transport Client until Spring Data Elasticsearch 3.2.x release (Rest Client should get Spring Data Elasticsearch support at 3.2.0).
Or even later, because it is even not deprecated yet (it would be deprecated in elastic 7.0, and removed in 8.0, but but by that time
it will definitely get full support and documentation of use in).

I also really like using newest versions of different libraries and frameworks, but one have to notice
that when talking about databases or other tools which use specific language or custom DSLs, old client can support more queries than
a newer one.

### Instruction
Clone repository:  
```git clone https://github.com/marcskow/spring-data-elasticsearch-example.git```

Run Elasticsearch docker or install locally and provide (or use default one):
- cluster name in application.properties file
- configuration in ElasticSearchConfiguration class  

My suggestion is to simply run docker image:

```docker run -p 9200:9200 -p 9300:9300 -d -e "discovery.type=single-node" -e "cluster.name=elasticsearch" --name marcskow-elastic docker.elastic.co/elasticsearch/elasticsearch:6.2.4```

Finally run the application:

```./gradlew bootRun```

After that test it e.g. with postman:
```
curl -X POST \
  http://localhost:8080/api/persons \
  -H 'Content-Type: application/json' \
  -d '{
        "firstName":"Marcin",  
        "lastName":"Skowron",
        "age":78,
        "phoneNumber":444555666 
}'
```

```
curl -X GET 'http://localhost:8080/api/search?firstName=Marcin&page=0&size=5'
```

### Reindexing
Reindexing process in my app does not provide consistency, it is only proof of concept about 
making whole database table reindex. It also do not prevent from threat race problems 
with indexing new documents during reindex process.
This example should only show the idea of reindexing, but it is only prototype now 
and it is not clear when and how I would improve the process.

Probably
[Index Alias](https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html)
should be used in this case.

### Links
How to configure Spring Data Elasticsearch?  
[Baeldung](https://www.baeldung.com/spring-data-elasticsearch-tutorial)
(working fine with Spring Data Elasticsearch 3.1.5)

[Elasticsearch Documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)

How to use Elasticsearch as an advanced search engine with existing application (or new one as secondary data source)  
[David Pilato](http://david.pilato.fr/blog/2015/05/09/advanced-search-for-your-legacy-application/)

### Extensions

- [ ] Prepare scripts generating big amount of data used to measure Elasticsearch queries performance
- [ ] Prepare whole database reindexing possibility
- [ ] Use custom Elasticsearch mappings and settings with Spring Data ES
- [ ] Asynchronous indexing of data based on entities creation events
