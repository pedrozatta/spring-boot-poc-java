package br.com.jasp.example.service

import br.com.jasp.example.repository.AnalyzeRepository
import spock.lang.Specification
import spock.lang.Unroll

class AnalyzeServiceSpec extends Specification {

    AnalyzeService service;

    def setup() {
        service = Spy(AnalyzeService)
        service.repository = Mock(AnalyzeRepository)
    }

    @Unroll
    def "verify with #value"() {
        given:
            service.repository.findByValue(_) >> {
                Optional.empty()
            }

        when:
            def result = service.analyze(value)

        then:
            result == expected

        where:
            value      || expected
            "{"        || -1
            "{{}"      || -1
            "}"        || -1
            "}}{"      || -1
            "{}}"      || -1
            "{ }"      || -1
            "{}a"      || -1
            "{} "      || -1
            ""         || 0
            "{}"       || 0
            "{}{}"     || 0
            "{{}}"     || 0
            "{{}{}}"   || 0
            "}}"       || 1
            "{{{}"     || 1
            "{{{{{}"   || 2
            "{{{{"     || 2
            "}}}}"     || 2
            "}{}{}{}{" || 2
            "}{"       || 2
            "}{{{"     || 3
            "}{{{{{"   || 4

    }

}