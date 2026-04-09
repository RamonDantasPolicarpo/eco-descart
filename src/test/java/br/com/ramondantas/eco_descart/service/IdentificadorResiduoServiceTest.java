package br.com.ramondantas.eco_descart.service;

import br.com.ramondantas.eco_descart.dto.ResiduoDTO;
import br.com.ramondantas.eco_descart.model.Residuo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IdentificadorResiduoServiceTest {

    // Criando um mock para não gastar créditos de AI e não precisar disponibilizar minha API KEY
    // Usando RETURNS_DEEP_STUBS para permitir encadeamento de chamadas (chatClient.prompt().user(...).call().entity(...))
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ChatClient.Builder chatClientBuilder;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ChatClient chatClient;

    private IdentificadorResiduoService service;

    @BeforeEach
    void setUp() {
        // Ensinando o builder falso retornar o cliente falso
        when(chatClientBuilder.build()).thenReturn(chatClient);

        // Iniciando o serviço com o Builder mockado
        service = new IdentificadorResiduoService(chatClientBuilder);
    }

    @Test
    void deveIdentificarCaixaDePizzaComoPapelao() {
        // 1 - Criando a reposta da IA que queremos simular
        Residuo mockResiduo = new Residuo(
                "Papelão",
                "Caixa de pizza com manchas de óleo",
                false,
                true,
                false,
                "Remova o excesso de comida antes de descartar.",
                List.of("Lixeira Azul", "Ecoponto")
        );

        // Ensinado o Mockito a devolver o mockResiduo quando o metodo entity() for chamado
        when(chatClient.prompt().user(anyString()).call().entity(Residuo.class)).thenReturn(mockResiduo);

        // 2 - Executando o metodo real do servico
        String input = "Uma caixa de pizza suja";
        ResiduoDTO resultado = service.identificarResiduo(input);

        // 3 - Verificando se o DTO mapeou tudo certo
        assertEquals("Papelão", resultado.tipo());
        assertTrue(resultado.isReciclavel());
        assertFalse(resultado.isEspecial());
    }
}