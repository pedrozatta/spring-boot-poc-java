package br.com.jasp.example.service;

import br.com.jasp.example.model.Analyze;
import br.com.jasp.example.repository.AnalyzeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnalyzeService {

    @Autowired
    protected AnalyzeRepository repository;

    public List<Integer> analyze(List<String> list) {
        return list
                .stream()
                .map(this::analyze)
                .collect(Collectors.toList());
    }

    public int analyze(String string) {
        if (!isValid(string)) {
            return -1;
        }
        Optional<Analyze> analyze = repository.findByValue(string);
        if (analyze.isPresent()) {
            log.trace("Utilizando resultado pre calculado");
            return analyze.get().getResult();
        }
        Integer result = verifyHelper(string, 0);
        this.save(string, result);
        log.info("Calculado novo resultado");
        return result;
    }

    protected Analyze save(String value, Integer result) {
        return repository.save(Analyze.builder().value(value).result(result).build());
    }

    protected int verifyHelper(String string, int quantity) {
        String newString = string.replace("{}", "");
        if (newString.length() == 0) {
            return quantity;
        } else if (string.equals(newString)) {
            quantity++;
            newString = fix(newString);
        }
        return verifyHelper(newString, quantity);
    }

    protected boolean isValid(String string) {
        if (string.length() % 2 != 0) {
            return false;
        }
        if (!string.matches("[{}]*")) {
            return false;
        }
        return true;

    }

    protected String fix(String string) {
        char[] charArray = string.toCharArray();
        if (charArray[0] == '}') {
            charArray[0] = '{';
        } else {
            charArray[charArray.length - 1] = '}';
        }
        return String.valueOf(charArray);
    }

}