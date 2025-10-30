# 🚀 Encurtador de URL (urlshortener)

Um serviço de API REST para encurtamento de URL, simples e eficiente, construído com Spring Boot.

Este projeto permite aos usuários enviar uma URL longa e receber uma URL curta e única. Ao acessar a URL curta gerada, o usuário é redirecionado para a URL original.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído usando o seguinte stack:

* **Java 21**
* **Spring Boot 3.5.7**
* **Spring Web:** Para criar os endpoints da API REST.
* **Spring Data MongoDB:** Para persistência de dados e consulta das URLs.
* **Apache Commons Lang 3:** Utilizado para gerar os códigos alfanuméricos curtos.
* **Maven:** Gerenciador de dependências.

---

## 🏁 Como Rodar

### Pré-requisitos

Para rodar este projeto localmente, você vai precisar de:

1.  **Java (JDK) 21** ou superior.
2.  **Maven** 3.9+
3.  **MongoDB** (um servidor rodando localmente ou um cluster na nuvem, como o MongoDB Atlas).

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd urlshortener
    ```

2.  **Configure o Banco de Dados:**
    Abra (ou crie) o arquivo `src/main/resources/application.properties` e configure a string de conexão do seu MongoDB:

    ```properties
    # Configuração do MongoDB
    spring.data.mongodb.uri=mongodb://localhost:27017/urlshortener
    
    # Porta padrão do servidor
    server.port=8080
    ```
    *(Substitua pela sua string de conexão se for diferente)*

3.  **Rode a aplicação:**
    Use o plugin do Maven para iniciar o servidor:
    ```bash
    mvn spring-boot:run
    ```

A aplicação estará rodando em `http://localhost:8080`.

---

## 🤖 API Endpoints

Interaja com a API usando os endpoints abaixo.

### 1. Encurtar uma URL

Cria um novo link curto para a URL longa enviada. O sistema verifica se o ID gerado já existe para evitar duplicatas.

* **Método:** `POST`
* **Endpoint:** `/shorten-url`
* **Body (JSON):**

    ```json
    {
      "url": "[https://algum-site-muito-longo.com/com-varios-parametros?q=teste](https://algum-site-muito-longo.com/com-varios-parametros?q=teste)"
    }
    ```

* **Resposta (Sucesso `200 OK`):**
  *(Retorna a URL encurtada completa)*

    ```json
    {
      "shortUrl": "http://localhost:8080/aBcDe"
    }
    ```

### 2. Redirecionar para a URL longa

Acessa a URL curta e redireciona (HTTP 302) para a URL original armazenada.

* **Método:** `GET`
* **Endpoint:** `/{id}`
* **Exemplo de Acesso (Navegador ou cURL):**
  `http://localhost:8080/aBcDe`

* **Resposta (Sucesso):**
    * **Status Code:** `302 Found`
    * **Header:** `Location: https://algum-site-muito-longo.com/...`

* **Resposta (Erro):**
  Se o `id` ("aBcDe") não for encontrado no banco de dados:
    * **Status Code:** `404 Not Found`