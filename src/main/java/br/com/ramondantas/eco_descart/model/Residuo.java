package br.com.ramondantas.eco_descart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Residuo {

    private String tipo;
    private String descricao;

    @JsonProperty("isOrganico")
    private boolean isOrganico;

    @JsonProperty("isReciclavel")
    private boolean isReciclavel;

    @JsonProperty("isEspecial")
    private boolean isEspecial;

    private String instDescarte;
    private List<String> pontosColeta;

}
