package br.com.ramondantas.eco_descart.dto;

import java.util.List;

public record ResiduoDTO(
        String tipo,
        String descricao,
        String instDescarte,
        boolean isReciclavel,
        boolean isOrganico,
        boolean isEspecial,
        List<String> pontosSugeridos
) {}
