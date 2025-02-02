# Thrift

Thrift is a microservice for tracking expense transactions with support for multiple currencies, automatic conversion,
and monthly limit control. It allows setting limits, tracking overages, and generating reports on transactions.

# Features

- Expense Tracking: Record and store expenses in multiple currencies.
- Monthly Limit Control: Set and manage monthly spending limits for different categories (e.g., Goods, Services).
- Currency Conversion: Automatically convert expenses to a base currency using up-to-date exchange rates.
- Overage Tracking: Flag transactions that exceed the set monthly limit.
- Custom Limits: Clients can set new monthly limits, ensuring control over expenses.
- Detailed Reports: Generate reports on transactions that exceed the limit, with full details including amount,
  currency, and date of the limit.

# Getting Started

## Prerequisites

Ensure you have the following installed:

- Java 21
- Docker
- Taskfile

## Installation

1. Clone the repository

```shell
git clone https://github.com/ivantsarevich/thrift.git
```

2. Edit the .env file with your preferred values
3. Go to deployment and build project

```shell
task build-bank
```

```shell
task build-limit
```

4. Run application

```shell
tast run
```

## API Endpoints

- POST /transactions - Record a new transaction.
- GET /transactions - Get a list of transactions.
- POST /limits - Set the monthly limit.
- GET /limits - Get the current limit.
- Get /transactions/exceeded - Get a list of exceeded transactions.

You can interact with the API using the Swagger UI. There are two different Swagger documentation links:

1. **Bank Swagger UI**:

- [Swagger UI (Bank)](http://localhost:8081/swagger-ui/index.html)

2. **Limit Service Swagger UI**:

- [Swagger UI (Limit Service)](http://localhost:8080/swagger-ui/index.html)

# Usage Example

## Getting a Transactions

```shell
curl -X GET --location "http://localhost:${BANK_PORT:8081}/api/v1/transactions" \
    -H "Content-Type: application/json" \
    -d '{
          "accountFrom": 1,
          "accountTo": 2,
          "currency": "RUB",
          "amount": 100,
          "category": "Product"
        }'
```

## Setting a Limit

```shell
curl -X POST --location "http://localhost:${LIMIT_PORT:8080}/api/v1/limits" \
    -H "Content-Type: application/json" \
    -d '{
          "accountId": 1,
          "sumLimit": 100,
          "category": "Product",
          "limitCurrency": "USD"
        }'
```

# Technologies

- Java
- Spring Boot Data JPA
- Redis
- Lombok
- PostgreSQL
- Docker
- OpenFeign
- Flyway
- MapStruct
- JUnit5
- Mockito