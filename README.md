# ORDER MANAGEMENT SYSTEM API

## Summary

Order management system REST API in spring boot. I am using an H2 database, liquibase for migration and inserting test data, Maven dependency management.
Liquibase schema and data insertion is in available "src/main/resources/db/changelog/db.changelog-master.xml".

## How to run

1. Clone this repository
2. Navigate to project directory
3. Build the project - mvn clean install
4. Run OrderManagementApplication

## Prerequisites

1. Java 17
2. Maven 3.x

## API functionality

1. Create customer
2. Create product
3. Create order
4. Search all orders by date
5. Search Orders by product id
6. Search Orders by customer id
7. Change quantity of products in an order line
