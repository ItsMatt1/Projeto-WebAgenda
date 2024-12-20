# WebAgenda

## Descrição
WebAgenda é uma aplicação backend construída em Java utilizando o Spring Framework. O projeto utiliza o MySQL como banco de dados relacional e requer ferramentas como IntelliJ IDEA ou Eclipse para o desenvolvimento e execução.

## Pré-requisitos
Antes de configurar o projeto, certifique-se de ter os seguintes softwares instalados em sua máquina:

1. **Java Development Kit (JDK)** - Versão 11 ou superior.  
   - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **MySQL Server** - Para hospedar o banco de dados.  
   - [Download MySQL Server](https://dev.mysql.com/downloads/mysql/)

3. **MySQL Workbench** - Para gerenciar o banco de dados.  
   - [Download MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

4. **IDE (Integrated Development Environment)**  
   - IntelliJ IDEA ou Eclipse.  
     - [Download IntelliJ IDEA](https://www.jetbrains.com/idea/)  
     - [Download Eclipse](https://www.eclipse.org/)

5. **Spring Framework** (o projeto já inclui o suporte por meio de Maven/Gradle).

## Configuração

### 1. Clone o repositório:
```bash
git clone https://github.com/username/WebAgenda.git
cd WebAgenda
```
### 2. Configure o Banco de Dados MySQL:
1. Inicie o servidor MySQL.  
2. Crie um banco de dados chamado `WebAgenda`:
   ```sql
   CREATE DATABASE WebAgenda;
   ```
3. Configure o usuário e a senha no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/WebAgenda
   spring.datasource.username=root
   spring.datasource.password=Matheus03216!sql
   ```

> ⚠️ **Atenção:** no meu caso minha senha é essa, mas é necessário trocar para a sua password.

### 3. Importação do Projeto:
- **IntelliJ IDEA**  
  1. Abra o IntelliJ IDEA e selecione *File > Open*.  
  2. Navegue até a pasta do projeto e selecione-a.  
  3. O IntelliJ IDEA reconhecerá automaticamente o Maven/Gradle e fará o download das dependências.  

- **Eclipse**  
  1. Abra o Eclipse e selecione *File > Import*.  
  2. Escolha *Existing Maven Projects* e selecione o diretório do projeto.  

### 4. Rodar o Projeto:
- Execute a classe principal `WebAgendaApplication.java` na sua IDE.  
- O servidor será iniciado em [http://localhost:8080](http://localhost:8080).
- Depois basta abrir a pasta Frontend/index.html e clicar duas vezes para abrir no seu navegador.

## Estrutura do Projeto

```
PROJETO-WEBAGENDA
├── Frontend/
│   ├── css/                  # Arquivos de estilo
│   ├── js/                   # Scripts JavaScript
│   │   └── main.js           # Lógica do Frontend
│   └── index.html            # Arquivo principal para iniciar o Frontend
│                             # *Para iniciar o Frontend, clique duas vezes no index.html*
├── WebAgenda/                # Diretório do Backend (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com.example.webagenda/  # Código fonte principal do Backend
│   │   │   └── resources/
│   │   │       ├── application.properties  # Configurações do Spring
│   │   │       └── static/                 # Arquivos estáticos (opcional)
│   │   └── test/                           # Testes unitários
│   ├── pom.xml                             # Configurações do Maven
│   ├── README.md                           # Documentação do projeto
│   └── target/                             # Arquivos compilados
```

## Configurações Avançadas

### JPA e Hibernate
As configurações do JPA estão localizadas em `application.properties`:
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

Você pode ajustar essas configurações conforme necessário.


## Participantes
Christopher Star Correa de Oliveira: Front-End.

Matheus de Moura Rosa: Back-End.

Pedro Henrique Chaves Junqueira: Database, PDF e Vídeo.
