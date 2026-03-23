package br.com.ramondantas.eco_descart.service;

import br.com.ramondantas.eco_descart.dto.ResiduoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdentificadorResiduoServiceTest {

    private final IdentificadorResiduoService service = new IdentificadorResiduoService();

    @Test
    void deveIdentificarCaixaDePizzaComoPapelao() {
        // Cenário (Caminho Feliz)
        String input = "Uma caixa de pizza suja";

        // Ação
        ResiduoDTO resultado = service.identificarResiduo(input);

        // Verificação
        assertEquals("Papelão", resultado.tipo());
        assertTrue(resultado.isReciclavel());
        assertFalse(resultado.isEspecial());
    }

}
