# silverspin
Silverspin Tech Challenge

Create a working website for Ordering and Shipping that simulates an ordering system in a microservice environment/architecture.

The website should have the following:
- A backend Order application that should:
  - Allow creation, viewing and maintaining of orders
    - Should send order requests to a separate Shipping application to trigger shipment and receive a corresponding tracking number

- A backend Shipping application that should
  - Process and store shipping data for orders
  - Assign and return a tracking number for an order
  - Be able to provide updates to the Order application when an order has been shipped
 
- A webpage that allows placing an order and monitoring its status


Created a monorepo with the following submodules:

1. application

This will serve the pages that allows placing of orders and monitoring its status. It also doubles as the backend Order 
application for simplicity. Run `docker compose up` to instantiate a mysql container. Uses liquibase for database migrations.
TODO:
a. send order requests via api-gateway to Shipping application
b. add Kafka consumers to listen to updates to shipping status
c. automated tests

2. shipping-service

Processes and stores shipping data for orders. Currently, has one endpoint GET /orders/{orderId}, which will retrieve
delivery details for that order id or persist a Delivery entity. This endpoint will return a DeliveryDto containing the 
tracking number, order id, current shipping status.

For this exercise, it uses the same database utilized by the application module but typically each microservice should 
have their own datasource, especially if there is a clear demarcation in their usage.
TODO:
a. add Kafka producers to emit shipping status events that the application module can listen to.
b. add a simulator that replicates the transitions in shipping status (i.e. IN_TRANSIT, DELIVERED, etc.). In real world apps, 
this data could be requested or received from third-party delivery services.
c. automated tests

3. service-discovery

Uses Eureka service registry. Microservices can easily find and communicate with each other dynamically by registering 
to this service.
TODO:
a. implementation
c. automated tests

4. api-gateway

Uses Spring Cloud Reactive gateway for a single point of entry and for routing requests to various microservices. Has 
circuit breaker integration. It can also address other cross-cutting concerns like authentication/authorization, 
monitoring and analytics, logging and tracing, aggregating data, load balancing and others.
TODO: implementation
