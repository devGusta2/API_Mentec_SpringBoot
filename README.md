🔧 Passos:
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/seu-usuario/seu-repositorio.git
cd meu-projeto/backend
Configure o arquivo application.properties (exemplo):

properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost:3306/minha_base
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
cors.allowed.origins=http://localhost:3000
Compile e rode a aplicação:

bash
Copiar
Editar
./mvnw spring-boot:run
A API estará disponível em: http://localhost:8080
