package br.com.ramondantas.eco_descart.model;

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
    private boolean isOrganico;
    private boolean isReciclavel;
    private boolean isEspecial;
    private String instDescarte;
    private List<String> pontosColeta;

}
