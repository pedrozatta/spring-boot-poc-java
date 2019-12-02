package br.com.jasp.example.controller;

import br.com.jasp.example.model.Request;
import br.com.jasp.example.model.Response;
import br.com.jasp.example.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pzatta
 */

@RestController
@RequestMapping(path = "/analyzes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExampleController {

    @Autowired
    protected AnalyzeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody @Validated Request body) {
        List<Integer> response = service.analyze(body.getList());
        return new Response(response);
    }

}