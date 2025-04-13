# 📄 Sistema de Processamento de Faturas com Spring Batch

Este projeto é um sistema desenvolvido em Java com Spring Boot e Spring Batch que processa faturas de cartões de crédito. Ele realiza a leitura dos dados, enriquece as informações dos clientes via uma API externa e gera arquivos `.txt` formatados por fatura. O sistema utiliza múltiplos bancos de dados: um para controle dos jobs do Spring Batch e outro para armazenar os dados da aplicação.

---

## 🚀 Funcionalidades

- Leitura de dados de faturas de cartão de crédito.
- Enriquecimento de dados com consumo de API externa via `RestTemplate`.
- Escrita de arquivos de fatura com:
  - Cabeçalho personalizado.
  - Rodapé com totalizador de transações.
  - Agrupamento e formatação das transações por cliente.
- Criação de múltiplos arquivos (`MultiResourceItemWriter`) — um por fatura.
- Separação de `DataSources` para controle do Spring Batch e dados da aplicação.

---

## ⚙️ Tecnologias utilizadas

- Java 17+
- Spring Boot 3+
- Spring Batch
- MySQL
- Maven
- HikariCP
- REST API
- FileSystem (`FlatFileItemWriter`)
- Formatação com `LineAggregator`, `FlatFileHeaderCallback` e `FlatFileFooterCallback`

---

## 🏗️ Arquitetura

- `ItemReader`: Lê as faturas do banco de dados `fatura_cartao_credito`.
- `ItemProcessor`: Consome uma API externa para carregar informações do cliente.
- `ItemWriter`: Escreve cada fatura em um arquivo `.txt`, formatado e com informações detalhadas.
- `Job`: Orquestra o processo batch.
- `Step`: Realiza o processamento em chunks de faturas.

---

## 🛠️ Configuração

### application.properties

```properties
# Spring Batch DB
spring.datasource.jdbcUrl=jdbc:mysql://localhost:3306/spring_batch
spring.datasource.username=root
spring.datasource.password=123456

# Application DB
app.datasource.jdbcUrl=jdbc:mysql://localhost:3306/fatura_cartao_credito
app.datasource.username=root
app.datasource.password=123456

# Inicializar tabelas do Spring Batch
spring.batch.jdbc.initialize-schema=always
```

## ▶️ Executando o Job
O job é executado automaticamente na inicialização da aplicação.

Se desejar rodar manualmente, você pode criar um CommandLineRunner ou utilizar JobLauncher.

## 📂 Saída dos Arquivos
Os arquivos .txt são gerados no diretório:

* bash
* Copiar
* Editar
* files/fatura/

Cada arquivo contém:

* Nome e endereço do cliente

* Número do cartão

* Lista formatada de transações

* Totalizador no rodapé

Exemplo de nome do arquivo: 0.txt, 1.txt, 2.txt, ...

## ✅ Requisitos
* Java 17+

* MySQL

* Maven

## 🧪 Testes
Em desenvolvimento. Futuramente, serão adicionados testes unitários para os componentes do Spring Batch e integração com base de dados e arquivos.

## 📌 Possíveis melhorias
Parametrização do diretório de saída via application.properties

Paginação e paralelismo na leitura

Tratamento de falhas e SkipPolicy

Relatórios em PDF com JasperReports


