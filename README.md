# ♻️ EcoDescart

[![Java CI with Maven](https://github.com/ramondantaspolicarpo/eco-descart/actions/workflows/ci.yml/badge.svg)](https://github.com/ramondantaspolicarpo/eco-descart/actions/workflows/ci.yml)
[![Versão](https://img.shields.io/badge/version-1.1.0-blue.svg)]()

> 🎉 **NOVIDADE:** Agora ficou muito mais fácil usar o EcoDescart! Você não precisa instalar nada de código. Basta baixar o nosso executável `.exe`, dar dois cliques, informar sua chave do Gemini e o sistema já está rodando direto no seu terminal! (Veja a seção "Como Executar" abaixo).

Sistema inteligente de auxílio ao descarte consciente de resíduos, utilizando Inteligência Artificial para orientar os cidadãos de forma simples e direta.

---

## 🛑 O Problema Real
O descarte incorreto de resíduos sólidos é um dos grandes causadores de poluição ambiental e riscos à saúde pública. Diariamente, milhares de pessoas têm dúvidas sobre como descartar corretamente itens específicos (como lixo eletrônico, materiais perfurocortantes, óleo ou baterias) e acabam misturando-os com o lixo comum, inviabilizando a reciclagem e colocando coletores em perigo.

## 💡 A Proposta de Solução
O **EcoDescart** é uma aplicação de linha de comando (CLI) que utiliza a API do Google Gemini (Spring AI) para atuar como um especialista ambiental. O usuário descreve o lixo que deseja jogar fora e o sistema analisa, classifica e devolve instruções precisas de segurança, embalagem e os pontos de coleta mais adequados.

## 🎯 Público-Alvo
Cidadãos comuns e pequenos empreendedores que buscam ter atitudes mais sustentáveis, mas esbarram na falta de informação rápida e clara sobre as regras de descarte e reciclagem.

## ✨ Funcionalidades Principais
* **Geolocalização Inteligente:** Integração com a API pública do ViaCEP para identificar a região do usuário e sugerir pontos de coleta altamente direcionados.
* **Identificação Inteligente:** Análise de texto natural do usuário via IA.
* **Classificação de Risco:** Identificação automática de lixo orgânico, reciclável ou resíduo especial/perigoso.
* **Instruções de Descarte Seguras:** Geração de dicas de como embalar itens para proteger os profissionais de limpeza.
* **Configuração Dinâmica:** Solicitação da chave de API interativa diretamente no terminal, sem necessidade de configurar variáveis de ambiente na máquina.

## 🛠️ Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.2.4** (Framework base)
* **Spring AI** (Integração com Google Gemini 2.5 Flash)
* **API ViaCEP** (Geolocalização e integração REST)
* **Maven** (Gerenciamento de dependências e build)
* **JUnit 5 + Mockito** (Testes automatizados e Mocks)
* **Checkstyle** (Linting e análise estática)
* **GitHub Actions** (Pipeline de CI/CD)

---

## 🚀 Como Executar o Projeto

Para usar o EcoDescart, você precisa de uma chave de API válida do Google AI Studio.  
Você pode obter uma chave gratuita em: https://aistudio.google.com/api-keys.

### 📦 Opção 1: O Jeito Mais Fácil (Executável Standalone)
1. Vá até a aba **Releases** deste repositório e baixe o arquivo `EcoDescart.exe`.
2. Dê um clique duplo no arquivo baixado.
3. O terminal irá se abrir automaticamente. Cole a sua chave de API do Gemini quando solicitado e comece a usar!

### 👨‍💻 Opção 2: Para Desenvolvedores (Código-Fonte)
Se você deseja clonar o projeto e rodá-lo via código:

**Pré-requisitos:** Java 21 e Maven instalados.

1. Clone este repositório:
```bash
   git clone [https://github.com/RamonDantasPolicarpo/eco-descart.git](https://github.com/RamonDantasPolicarpo/eco-descart.git)
   
```

2. Acesse a pasta do projeto:
```bash
cd eco-descart

```


3. Inicie a aplicação executando o comando abaixo (o próprio sistema pedirá a chave de API no terminal):
```bash
./mvnw spring-boot:run

```



### 🐳 Opção 3: Execução via Docker

Caso tenha o Docker instalado na sua máquina, rode o seguinte comando, substituindo `sua-chave-aqui` pela chave de API que você obteve:

```bash
docker run -it -e GEMINI_API_KEY="sua-chave-aqui" ramondantasdev/ecodescart-app

```

---

## 💻 Exemplo de Uso

Após iniciar, o terminal exibirá o prompt do sistema:

---

## 🧪 Como rodar os Testes Automatizados

O projeto conta com testes unitários e de integração utilizando Mockito para simular as respostas da Inteligência Artificial. Para executar a suíte de testes, rode:

```bash
./mvnw test

```

## 🧹 Como rodar o Linting (Análise Estática)

O projeto utiliza o Checkstyle para garantir a padronização e a qualidade do código. Para executar a verificação estática, rode:

```bash
./mvnw checkstyle:check

```

---

## 📌 Versão Atual

**v1.1.0**

## 👤 Autor

**Ramon Dantas Policarpo**

🔗 **Repositório Público:** https://github.com/ramondantaspolicarpo/eco-descart
