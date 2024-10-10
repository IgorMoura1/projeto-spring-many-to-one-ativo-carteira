<h4>This Java Spring Boot project manages investment assets (Ativo) linked to specific wallet (Carteira). The project uses Lombok to minimize boilerplate code, making it more efficient to handle typical class functions like getters, setters, and object builders.</h4>

<h4>Key Features:</h4>

<h5>Entities:</h5>

<p>Ativo (Investment Asset) containing details like ID, name, type, current value, and its association with a Carteira.
Relationships are established through a foreign key linking assets to their respective portfolios.</p>
Lombok Usage:

<h5>Automates common methods (@Getter, @Setter, @Builder, @NoArgsConstructor, @AllArgsConstructor), streamlining object management.</h5>

<h5>Service Layer:</h5>

<p>Methods to handle asset creation, retrieval, deletion, and updates, including search functionalities for assets based on investor names and calculating asset value averages.</p>

<h5>Controller Endpoints:</h5>

<p>POST: Create new assets linked to portfolios.
GET: Retrieve all assets or specific ones by ID, search by investor name, and calculate average asset values.
DELETE: Remove assets by ID.
Technologies:
Spring Boot for web API management.
Lombok to reduce boilerplate.
JPA for database interaction.
This project is designed to simplify managing investment portfolios by providing efficient CRUD operations and search capabilities through well-structured service and controller layers.</p>

<h4>This is an exemplo of a DTO response:</h4>

<pre><code>{
  "id": 1,
  "nome": "Ação XPTO",
  "tipo": "Ação",
  "valorAtual": 100.0,
  "carteira": {
    "id": 1,
    "nome": "Carteira de Investimentos A",
    "investidor": "João Silva"
  }
}</code></pre>