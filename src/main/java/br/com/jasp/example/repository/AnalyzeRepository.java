package br.com.jasp.example.repository;


import br.com.jasp.example.model.Analyze;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalyzeRepository extends JpaRepository<Analyze, Long> {

    Optional<Analyze> findByValue(String value);

}

