# Bank Project [Backend]

There is a prototype of the BackEnd Bank's Core Services data.

Data consist of clients, accounts, products, accounts, transactions and managers
[pom.xml](pom.xml)

## Technologies:

- JDK - v.17.07.7
- Lombok - v.1.18.26
- Hibernate - v.6.1.7
- Spring Boot v.3.0.4
- MySQL v.8.0.32
- Liquibase v.4.23.0
- Mockito
- JUnit
- Swagger
- AOP
- MapStruct
- SLF4J

___

* [ApiDoc Link](http://localhost:8080/swagger-ui/index.html)
* [JACOCO Link](http://localhost:63342/BankProj/target/site/jacoco/index.html)

___

## Database structure

### Table Client ( Bank's Clients table )

| Column name | Type        | Description                                   |
|-------------|-------------|-----------------------------------------------|
| id          | binary(16)  | id key of row - unique, not null, primary key | 
| manager_id  | bigint      | manager id                                    |
| status      | integer(1)  | client's status                               |
| tax_code    | varchar(20) | client's TAX code (external ID) , unique      |
| first_name  | varchar(50) | client's name                                 |
| last_name   | varchar(50) | client's surname                              |
| email       | varchar(60) | client's e-mail                               |                               
| address     | varchar(80) | client's address                              |
| phone       | varchar(20) | client's phone                                |                                
| created_at  | timestamp   | timestamp of row creation                     |
| updated_at  | timestamp   | timestamp of last update                      |

### Table Account (Bank's accounts table)

| Column name   | Type          | Description                                   |
|---------------|---------------|-----------------------------------------------|
| id            | binary(16)    | id key of row - unique, not null, primary key |
| client_id     | binary(16)    | client id                                     |         
| name          | varchar(100)  | a name of account                             |                              
| type          | integer(1)    | account type                                  |                                   
| status        | integer(1)    | status of tne account                         |                          
| balance       | decimal(15,2) | balance of the account in currency            | 
| currency_code | integer(2)    | account currency code                         |                          
| created_at    | timestamp     | timestamp of row creation                     |
| updated_at    | timestamp     | timestamp of last update                      |

### Table Product ( Sets of Bank's available Products)

| Column name   | Type         | Description                                                              |
|---------------|--------------|--------------------------------------------------------------------------|
| id            | bigint       | id key of row - unique, not null, primary key                            |
| manager_id    | bigint       | manager id                                                               |
| name          | varchar(70)  | product's name                                                           |
| status        | integer(1)   | product's status                                                         |
| currency_code | integer(2)   | currency of product                                                      |
| interest_rate | decimal(6,4) | interest rate of product                                                 |
| limit         | integer      | limit of credit a product ( 0 - no limit, 0 < - limit which can be used) |
| created_at    | timestamp    | timestamp of row creation                                                |
| updated_at    | timestamp    | timestamp of last update                                                 |

### Table Agreement (Bank's - Client's  Agreement table)

| Column name   | Type          | Description                                   |
|---------------|---------------|-----------------------------------------------|
| id            | bigint        | id key of row - unique, not null, primary key |
| account_id    | binary(16)    | client's account                              | 
| product_id    | bigint        | product id (table product)                    | 
| interest_rate | decimal(6,4)	 | current interest rate of agreement            | 
| status        | integer       | agreement's status                            | 
| sum           | decimal(15,2) | amount of agreement                           | 
| created_at    | timestamp     | timestamp of row creation                     | 
| updated_at    | timestamp     | timestamp of last update                      | 

### Table Transaction (Bank's Product table)

| Column name        | Type          | Description                                   |
|--------------------|---------------|-----------------------------------------------|
| 	id                | binary(16)    | id key of row - unique, not null, primary key | 
| 	debit_account_id  | binary(16)    | transaction's debit account                   | 
| 	credit_account_id | binary(16)    | transaction's credit account                  | 
| 	type              | integer(1)    | transaction type                              | 
| 	amount            | decimal(12,2) | transaction amount in the account currency    | 
| 	description       | varchar(255)  | description of transaction                    | 
| 	created_at        | timestamp     | timestamp of row creation                     |     
| 	created_at        | timestamp     | timestamp of row update                       | 

### Table Manager (Bank's managers )

| Column name | Type        | Description                                   |
|-------------|-------------|-----------------------------------------------|
| 	id         | bigint      | id key of row - unique, not null, primary key | 
| 	first_name | varchar(50) | manager's name                                | 
| 	last_name  | varchar(50) | manager's surname                             | 
| 	status     | integer     | manager's status                              | 
| 	created_at | timestamp   | timestamp of row creation                     | 
| 	update_at  | timestamp   | timestamp of row update                       |