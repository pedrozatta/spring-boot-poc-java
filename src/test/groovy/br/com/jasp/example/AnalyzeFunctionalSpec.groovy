package br.com.jasp.example

import br.com.jasp.example.model.Request
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnalyzeFunctionalSpec extends Specification {

    @Shared
    private RESTClient client

    @LocalServerPort
    private int port;

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "Test Success #values"() {
        given:
            def body = new Request(list: values)

        when:
            def result = client.post(path: "analyzes", body: body)

        then:
            result != null
            result.data.list == expected

        where:
            values                             || expected
            ["}{}{", "{{}{}}", "{{{{}}"]       || [2, 0, 1]
            ["}{}{}{", "{{}{}{}}", "{{{{{}}}"] || [2, 0, 1]

    }

}