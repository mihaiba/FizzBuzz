# FizzBuzz

##Spring boot run testing
1. `curl -v -H "Content-Type: application/json" POST -d '{"startInclusive":1, "endInclusive": 20}' http://localhost:8080/fizzbuzz`
1. `curl -v -H "Content-Type: application/json" POST -d '{"startInclusive":1, "endInclusive": 20}' http://localhost:8080/report`



###Generating docker image
`mvn clean install` will publish a new image locally, in order to push it to docker registry we can set docker registry credentials
in jib configuration or use `docker push`

###Running docker image testing

1. `docker run -p 80:8080 fizzbuzz.io/fizzbuzz/fizzbuzz`
1. `curl -H "Content-Type: application/json" POST -d '{"startInclusive":1, "endInclusive": 20}' http://localhost/fizzbuzz | jq`
1. `curl -H "Content-Type: application/json" POST -d '{"startInclusive":1, "endInclusive": 20}' http://localhost/report | jq`
 