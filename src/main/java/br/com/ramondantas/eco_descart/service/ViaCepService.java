package br.com.ramondantas.eco_descart.service;

import br.com.ramondantas.eco_descart.dto.EnderecoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCepService {

    private final RestClient restClient;

    public ViaCepService() {
        this.restClient = RestClient.create("https://viacep.com.br/ws");
    }

    public EnderecoDTO getEndereco(String cep) {
        try {
            // Remove traços do CEP
            String cepLimpo = cep.replaceAll("[^0-9]", "");

            return restClient.get()
                    .uri("/{cep}/json/", cepLimpo)
                    .retrieve()
                    .body(EnderecoDTO.class);
        } catch (Exception e) {
            return new EnderecoDTO("sua região", "");
        }
    }
}
