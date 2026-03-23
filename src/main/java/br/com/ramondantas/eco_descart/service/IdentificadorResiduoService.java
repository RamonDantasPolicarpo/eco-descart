package br.com.ramondantas.eco_descart.service;

import br.com.ramondantas.eco_descart.dto.ResiduoDTO;
import br.com.ramondantas.eco_descart.model.Residuo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentificadorResiduoService {

    public ResiduoDTO identificarResiduo(String descricaoInput) {

        // Implementar a lógica de integração com a API do Gemini para identificar
        // o tipo de residuo e retornar um objeto válido.

        // Por enquanto, vou simular isso.
        Residuo residuo = mockProcessamentoIA(descricaoInput);

        return new ResiduoDTO(
                residuo.getTipo(),
                residuo.getDescricao(),
                residuo.getInstDescarte(),
                residuo.isReciclavel(),
                residuo.isOrganico(),
                residuo.isEspecial(),
                residuo.getPontosColeta()
        );
    }

    private Residuo mockProcessamentoIA(String Input) {
        String query = Input.toLowerCase();

        if (query.contains("pizza")) {
            return new Residuo("Papelão", "Caixa de pizza usada", false, true, false,
                    "Reciclagem convencional",
                    List.of("Ponto de coleta A", "Ponto de coleta B"));
        } else if (query.contains("garrafa") && query.contains("plastico")) {
            return new Residuo("Plástico", "Garrafa PET de refrigerante", false, true, false,
                    "Reciclagem convencional",
                    List.of("Ponto de coleta C", "Ponto de coleta D"));
        } else if (query.contains("bateria")) {
            return new Residuo("Bateria", "Bateria de celular usada", false, false, true,
                    "Ponto de coleta especializado",
                    List.of("Ponto de coleta E"));
        } else {
            return new Residuo("Desconecido", "Descrição genérica do resíduo", false, false, false,
                    "Verificar local de descarte adequado",
                    List.of());

        }
    }

}
