# ♻️ EcoDescart

[![Java CI with Maven](https://github.com/ramondantaspolicarpo/eco-descart/actions/workflows/ci.yml/badge.svg)](https://github.com/ramondantaspolicarpo/eco-descart/actions/workflows/ci.yml)
[![Versão](https://img.shields.io/badge/version-1.0.0-blue.svg)]()

Sistema inteligente de auxílio ao descarte consciente de resíduos, utilizando Inteligência Artificial para orientar os cidadãos de forma simples e direta.

---

## 🛑 O Problema Real
O descarte incorreto de resíduos sólidos é um dos grandes causadores de poluição ambiental e riscos à saúde pública. Diariamente, milhares de pessoas têm dúvidas sobre como descartar corretamente itens específicos (como lixo eletrônico, materiais perfurocortantes, óleo ou baterias) e acabam misturando-os com o lixo comum, inviabilizando a reciclagem e colocando coletores em perigo.

## 💡 A Proposta de Solução
O **EcoDescart** é uma aplicação de linha de comando (CLI) que utiliza a API do Google Gemini (Spring AI) para atuar como um especialista ambiental. O usuário descreve o lixo que deseja jogar fora e o sistema analisa, classifica e devolve instruções precisas de segurança, embalagem e os pontos de coleta mais adequados.

## 🎯 Público-Alvo
Cidadãos comuns, donas de casa, estudantes e pequenos empreendedores que buscam ter atitudes mais sustentáveis, mas esbarram na falta de informação rápida e clara sobre as regras de descarte e reciclagem.

## ✨ Funcionalidades Principais
* **Identificação Inteligente:** Análise de texto natural do usuário via IA.
* **Classificação de Risco:** Identificação automática de lixo orgânico, reciclável ou resíduo especial/perigoso.
* **Instruções de Descarte Seguras:** Geração de dicas de como embalar itens para proteger os profissionais de limpeza.
* **Mapeamento de Coleta:** Sugestão de pontos genéricos adequados para a entrega do material.

## 🛠️ Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.2.4** (Framework base)
* **Spring AI** (Integração com Google Gemini 2.5 Flash)
* **Maven** (Gerenciamento de dependências e build)
* **JUnit 5 + Mockito** (Testes automatizados e Mocks)
* **Checkstyle** (Linting e análise estática)
* **GitHub Actions** (Pipeline de CI/CD)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* Java 21 instalado
* Maven instalado (ou use o `mvnw` incluso no projeto)
* Uma chave de API válida do Google AI Studio.

### Instalação
1. Clone este repositório:
   git clone https://github.com/ramondantaspolicarpo/eco-descart.git
2. Acesse a pasta do projeto:
   cd eco-descart
3. Configure a sua chave de API exportando a variável de ambiente:
    * **Linux/Mac:** `export GEMINI_API_KEY="sua-chave-aqui"`
    * **Windows (CMD):** `set GEMINI_API_KEY="sua-chave-aqui"`
    * **Windows (PowerShell):** `$env:GEMINI_API_KEY="sua-chave-aqui"`

### Execução
Inicie a aplicação executando o comando abaixo:
./mvnw spring-boot:run

---

## 💻 Exemplo de Uso
Após iniciar, o terminal exibirá o prompt do sistema:

--== EcoDescart: Descarte Inteligente ==--  
Descreva o resíduo (ou 'sair'):  

Agulhas de acupuntura usadas

RESULTADO DA ANÁLISE  
Tipo: Resíduo de Saúde Perfurocortante  
Reciclável: NÃO  
Orgânico: NÃO  
Especial: SIM  
Instrução: Descarte imediatamente após o uso em um coletor rígido e resistente à perfuração. Nunca descarte no lixo comum ou reciclável.  
Pontos de Coleta: [Unidade Básica de Saúde (UBS), Farmácias]  

---

## 🧪 Como rodar os Testes Automatizados
O projeto conta com testes unitários e de integração utilizando Mockito para simular as respostas da Inteligência Artificial. Para executar a suíte de testes, rode:
./mvnw test

## 🧹 Como rodar o Linting (Análise Estática)
O projeto utiliza o Checkstyle para garantir a padronização e a qualidade do código. Para executar a verificação estática, rode:
./mvnw checkstyle:check

---

## 📌 Versão Atual
**v1.0.0**

## 👤 Autor
**Ramon Dantas Policarpo**

🔗 **Repositório Público:** https://github.com/ramondantaspolicarpo/eco-descart
