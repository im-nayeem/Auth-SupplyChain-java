# Project: Product Authentication and Supply Chain Management System

This project is a web-based solution for **Product Authentication** at the marginal level by consumer and **Supply Chain Management** by manufacturer company. The application enables the administration to release new products and batches of products. Each product contains a unique ID number. Two QR codes will be generated for each product, one for the customer which contains a link to verify the product by visiting verified website of the company and one for the seller which contains a link to mark it as sold when customer wants to buy this. The product’s status and holder information will be updated in the database at each step of the distribution process in the supply chain such as supply to the distributor(wholesaler), distribution to the seller and sale to the customer. The warranty issuing process is automated by this system. When customer buys a product the warranty will be automatically issued. Administration can make data-driven decisions by analyzing data from admin panel.


## Technologies Used:
* Java EE
* JavaScript
* HTML
* CSS
* MySQL
* Maven
* Tomcat9

## Design Pattern Used:
* Model-View-Controller (MVC)

## Features:
- Authentication of product by customer when customer wants to buy a product
- Management of supply chain
- Automation of warranty issue
- Real-time updates of product status and holder information in the database
- Supply chain data analysis for data-driven decision making

## Installation

1. Clone the repository to your local machine
    ```bash
    git clone https://github.com/im-nayeem/Product-Authentication.git
    ```
2. Import the project into your IDE (Eclipse, IntelliJ Idea, etc.)
3. Configure the database connection in `dbConfig.properties`(my_web_app>src>main>resources>dbConfig.properties)
4. Configure email and email app-password in `mailConfig.properties`(my_web_app>src>main>resources>mailConfig.properties). This email will be used to send OTP for the verification of user's email and sending mails to them.
5. Build and deploy the project on Tomcat server

## License
[MIT](https://github.com/im-nayeem/Auth-SupplyChain-java/blob/main/LICENSE)

## Project Report
Please find the project report [here](https://www.overleaf.com/read/dngpbwvhntvp).






