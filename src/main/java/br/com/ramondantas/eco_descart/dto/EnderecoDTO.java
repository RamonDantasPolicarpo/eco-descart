package br.com.ramondantas.eco_descart.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDTO(
        String localidade,
        String uf
) {
}
