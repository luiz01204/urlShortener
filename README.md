# 🚀 Encurtador de URL (API)

Um serviço de **API REST** para encurtamento de URLs, simples, direto e eficiente — feito com **Spring Boot** e **MongoDB**.  
👉 **Front-end do projeto:** [https://github.com/luiz01204/urlShortener-frontend](https://github.com/luiz01204/urlShortener-frontend)

---

## 🧠 Visão Geral

Essa API permite enviar uma URL longa e receber uma versão encurtada.  
Quando alguém acessa essa URL curta, o sistema redireciona automaticamente para o endereço original.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Web** → Criação dos endpoints REST.
- **Spring Data MongoDB** → Persistência e consultas das URLs.
- **Apache Commons Lang 3** → Geração dos códigos curtos e aleatórios.
- **Maven** → Gerenciador de dependências.

---

## 🏁 Como Rodar o Projeto

### 🔧 Pré-requisitos
Você precisa ter instalado:
- **Java JDK 21** ou superior  
- **Maven 3.9+**  
- **MongoDB** local ou um cluster (ex: MongoDB Atlas)

---

### 🚀 Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/luiz01204/urlShortener.git
   cd urlShortener
   ```

2. **Configure o banco de dados:**
   No arquivo `src/main/resources/application.properties`:

   ```properties
   # Configuração do MongoDB
   spring.data.mongodb.uri=mongodb://localhost:27017/urlshortener

   # Porta da API
   server.port=8080
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

A API rodará em:  
👉 `http://localhost:8080`

---

## ⚙️ Endpoints

### 🔹 1. Encurtar uma URL
Cria uma nova URL curta e retorna o link encurtado.

**Requisição:**  
- **Método:** `POST`  
- **Endpoint:** `/shorten-url`  
- **Body:**
  ```json
  {
    "url": "https://meu-site-grande.com/teste?param=123"
  }
  ```

**Resposta (200 OK):**
```json
{
  "url": "http://localhost:8080/aBcDe"
}
```

> ✅ A API agora retorna **200 OK** (em vez de 302), garantindo compatibilidade com clientes HTTP e front-ends que consomem via Axios ou Fetch.

---

### 🔹 2. Redirecionar para a URL original
Ao acessar a URL curta, a API retorna um **302 Found**, redirecionando automaticamente para o endereço original.

**Requisição:**  
- **Método:** `GET`  
- **Endpoint:** `/{id}`  
- **Exemplo:**  
  ```
  http://localhost:8080/aBcDe
  ```

**Resposta (Sucesso):**
- **Status:** `200 Ok`  
- **Header:**  
  ```
  redirectTo: https://meu-site-grande.com/teste?param=123
  ```

**Resposta (Erro):**
- **Status:** `404 Not Found`
- **Body (exemplo):**
  ```json
  {
	"error": "URL não encontrada",
	"status": 404,
	"timestamp": "2025-11-05T14:30:17.990938361",
	"message": "URL com o ID 'bUtDfiul' não foi encontrada."
  }
  ```

---

## 🧩 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/br/dev/luizmachado/urlShortener/
 │   │   ├── config/         # Configurações globais da aplicação
 │   │   ├── controller/     # Endpoints REST
 │   │   ├── service/        # Lógica de negócio
 │   │   ├── repository/     # Integração com o banco de dados (MongoDB)
 │   │   ├── dto/            # Objetos de transferência de dados (Request/Response)
 │   │   ├── entities/       # Entidades que representam documentos do banco
 │   │   └── exception/      # Tratamento global de exceções e classes customizadas
 │   └── resources/
 │       └── application.properties
 └── test/
     └── java/br/dev/luizmachado/urlShortener/

```

---

## 🌐 Front-end

O projeto front-end que consome essa API está disponível aqui:  
👉 [https://github.com/luiz01204/urlShortener-frontend](https://github.com/luiz01204/urlShortener-frontend)

---

## 💡 Autor

Desenvolvido por **Luiz Antônio dos Santos Machado**  
📎 [GitHub](https://github.com/luiz01204) | [LinkedIn](https://www.linkedin.com/in/luiz-ant%C3%B4nio-dos-santos-machado-393bb314b/)
