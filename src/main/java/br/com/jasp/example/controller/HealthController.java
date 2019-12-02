package br.com.jasp.example.controller;

import br.com.jasp.example.model.Request;
import br.com.jasp.example.model.Response;
import br.com.jasp.example.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author pzatta
 */

@RestController
@RequestMapping(path = {"/","/health"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HealthController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String,String>  health() {
        HashMap<String,String> result = new HashMap();
        result.put("status","ok");
        return result;
    }

}