package br.com.ramondantas.eco_descart;

import br.com.ramondantas.eco_descart.dto.EnderecoDTO;
import br.com.ramondantas.eco_descart.dto.ResiduoDTO;
import br.com.ramondantas.eco_descart.exception.AiIntegrationException;
import br.com.ramondantas.eco_descart.service.IdentificadorResiduoService;
import br.com.ramondantas.eco_descart.service.ViaCepService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EcoDescartApplication implements CommandLineRunner {

    private final IdentificadorResiduoService service;
    private final ViaCepService viaCepService;

    public EcoDescartApplication(IdentificadorResiduoService service, ViaCepService viaCepService) {
        this.service = service;
        this.viaCepService = viaCepService;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Tenta buscar a chave das variáveis de ambiente do sistema operacional primeiro
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = System.getProperty("GEMINI_API_KEY");
        }

        // Se não encontrar em lugar nenhum, solicita diretamente no terminal ANTES do Spring Boot subir
        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("====================================================");
            System.out.println("         CONFIGURAÇÃO INICIAL DO ECODESCART         ");
            System.out.println("====================================================");
            System.out.print("Por favor, digite ou cole sua GEMINI_API_KEY: ");
            apiKey = scanner.nextLine().trim();

            while (apiKey.isBlank()) {
                System.out.print("A chave não pode ser vazia. Digite novamente: ");
                apiKey = scanner.nextLine().trim();
            }

            // Injeta dinamicamente a chave informada nas propriedades de sistema
            System.setProperty("GEMINI_API_KEY", apiKey);
            System.out.println("\nChave configurada com sucesso! Inicializando o sistema...\n");
        }

        SpringApplication.run(EcoDescartApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length > 0 && args[0].equals("test")) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--== EcoDescart: Descarte Inteligente ==--\n");

        System.out.print("Para melhorarmos as sugestões, informe seu CEP (apenas números): \n");
        String cep = sc.nextLine();

        System.out.print("Buscando localização... \n");
        EnderecoDTO endereco = viaCepService.getEndereco(cep);

        StringBuilder locBuilder = new StringBuilder();
        if (endereco.logradouro() != null && !endereco.logradouro().isBlank()) {
            locBuilder.append(endereco.logradouro()).append(", ");
        }
        if (endereco.bairro() != null && !endereco.bairro().isBlank()) {
            locBuilder.append(endereco.bairro()).append(" - ");
        }
        locBuilder.append(endereco.localidade()).append(" - ").append(endereco.uf());

        String localizacaoUsuario = locBuilder.toString();
        System.out.println("Localização definida: " + localizacaoUsuario + "\n");

        while (true) {
            System.out.println("==== Descreva o resíduo (ou 'sair'):  ====");
            String descricao = sc.nextLine();

            if (descricao.equalsIgnoreCase("sair")) break;

            Thread spinner = new Thread(() -> {
                String[] anim = {"[■□□□□□]", "[□■□□□□]", "[□□■□□□]", "[□□□■□□]", "[□□□□■□]", "[□□□□□■]", "[□□□□■□]", "[□□□■□□]", "[□□■□□□]", "[□■□□□□]"};
                int x = 0;
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.print("\r[IA] Analisando o resíduo... " + anim[x++ % anim.length]);
                        Thread.sleep(250);
                    }
                } catch (InterruptedException e) {
                }
                System.out.print("\r                                         \r");
            });

            spinner.start();

            try {
                ResiduoDTO resultado = service.identificarResiduo(descricao, localizacaoUsuario);

                spinner.interrupt();
                spinner.join();

                System.out.println("\n>>> RESULTADO DA ANÁLISE <<<");
                System.out.println("Tipo: " + resultado.tipo());
                System.out.println("Reciclável: " + (resultado.isReciclavel() ? "SIM" : "NÃO"));
                System.out.println("Orgânico: " + (resultado.isOrganico() ? "SIM" : "NÃO"));
                System.out.println("Especial: " + (resultado.isEspecial() ? "SIM" : "NÃO"));
                System.out.println("Instrução: " + resultado.instDescarte());
                if (!resultado.pontosSugeridos().isEmpty()) {
                    System.out.println("Pontos de Coleta: " + resultado.pontosSugeridos() + "\n");
                }
            } catch (AiIntegrationException e) {
                spinner.interrupt();
                try {
                    spinner.join();
                } catch (InterruptedException ignored) {
                }

                System.out.println("\n[ERRO] Problema ao conectar com o provedor de IA.");
                System.out.println("Sua chave de API pode estar inválida ou o serviço está fora do ar.");
                System.out.println("Por favor, reinicie a aplicação e verifique sua GEMINI_API_KEY.\n");

                // Quebra o loop para encerrar o programa, já que a chave tá errada
                break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Obrigado por usar o EcoDescart! Juntos, podemos fazer a diferença para o meio ambiente.");
        System.exit(0);
    }
}