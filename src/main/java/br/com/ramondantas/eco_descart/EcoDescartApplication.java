package br.com.ramondantas.eco_descart;

import br.com.ramondantas.eco_descart.dto.ResiduoDTO;
import br.com.ramondantas.eco_descart.exception.AiIntegrationException;
import br.com.ramondantas.eco_descart.service.IdentificadorResiduoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class EcoDescartApplication implements CommandLineRunner {

	private final IdentificadorResiduoService service;

	public EcoDescartApplication(IdentificadorResiduoService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(EcoDescartApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length > 0 && args[0].equals("test")) {
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("--== EcoDescart: Descarte Inteligente ==--");

		while (true) {
			System.out.println("\nDescreva o resíduo (ou 'sair'):");
			String descricao = sc.nextLine();

			if (descricao.equalsIgnoreCase("sair")) break;

			// Chama o service e recebe o DTO
			try {
				ResiduoDTO resultado = service.identificarResiduo(descricao);

				System.out.println("\n>>> RESULTADO DA ANÁLISE <<<");
				System.out.println("Tipo: " + resultado.tipo());
				System.out.println("Reciclável: " + (resultado.isReciclavel() ? "SIM" : "NÃO"));
				System.out.println("Orgânico: " + (resultado.isOrganico() ? "SIM" : "NÃO"));
				System.out.println("Especial: " + (resultado.isEspecial() ? "SIM" : "NÃO"));
				System.out.println("Instrução: " + resultado.instDescarte());
				if (!resultado.pontosSugeridos().isEmpty()) {
					System.out.println("Pontos de Coleta: " + resultado.pontosSugeridos());
				}
			} catch (AiIntegrationException e) {
				System.out.println("\n[AVISO] O serviço de análise inteligente está indisponível no momento.");
				System.out.println("Detalhe: " + e.getMessage());
				System.out.println("Por favor, aguarde alguns instantes e tente novamente.");
			}
		}

		System.out.println("Obrigado por usar o EcoDescart! Juntos, podemos fazer a diferença para o meio ambiente.");
		System.exit(0);
		
	}
}
