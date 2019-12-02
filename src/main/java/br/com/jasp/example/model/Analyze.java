package br.com.jasp.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_ANALYZE", uniqueConstraints = @UniqueConstraint(columnNames = {"value"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analyze implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "RESULT")
    private Integer result;

}
