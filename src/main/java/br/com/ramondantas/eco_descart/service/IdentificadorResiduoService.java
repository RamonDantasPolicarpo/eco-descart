package br.com.ramondantas.eco_descart.service;

import br.com.ramondantas.eco_descart.dto.ResiduoDTO;
import br.com.ramondantas.eco_descart.exception.AiIntegrationException;
import br.com.ramondantas.eco_descart.model.Residuo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class IdentificadorResiduoService {

    private final ChatClient chatClient;

    public IdentificadorResiduoService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ResiduoDTO identificarResiduo(String descricaoInput) {

        // Prompt detalhando a tarefa da IA
        String prompt = """
                Você é um especialista ambiental em gestão de resíduos.
                Analise o seguinte item relatado por um usuário para descarte: "%s"
                
                Preencha os dados do resíduo identificando:
                - O tipo (ex: Plástico, Eletrônico, Papel, etc)
                - Uma breve descrição do objeto
                - Se é orgânico (true/false)
                - Se é reciclável (true/false)
                - Se é especial (true/false). ATENÇÃO: Classifique como true (Especial/Perigoso)
                - Instruções claras e diretas de como preparar para o descarte
                - Uma lista contendo o nome de 2 pontos de coleta genéricos (pois o mapa real será integrado futuramente).
                
                """.formatted(descricaoInput);

        // Fazendo a chamada API do Gemini
        try {
            Residuo residuo = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .entity(Residuo.class);

            return new ResiduoDTO(
                    residuo.getTipo(),
                    residuo.getDescricao(),
                    residuo.getInstDescarte(),
                    residuo.isReciclavel(),
                    residuo.isOrganico(),
                    residuo.isEspecial(),
                    residuo.getPontosColeta()
            );
        } catch (Exception e) {
        throw new AiIntegrationException("Erro ao integrar com provedor de AI. ", e);
        }
    }

}
