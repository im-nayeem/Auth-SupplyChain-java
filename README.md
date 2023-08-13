# Product Authentication and Supply Chain Management Web Application

The **Product Authentication and Supply Chain Management** web application is a web-based solution for manufacturer companies to enable manufacturers to provide end-level consumers with the means to verify the authenticity of their products and manage the entire supply chain process. This system ensures transparency, traceability, and reliability throughout the product's journey from manufacturing to sale.

## Key Features

- **Product Authentication:** Each product is equipped with two QR codes – one for consumers to verify authenticity and another for sellers to mark the product as sold. By scanning the QR code and visiting the official company website found by scanning QR code, consumers can see basic product information such as product status(manufactured, supplied, distributed, sold), product batch, manufacturing date, expiring date, warranty information etc. Detailed information(seller or product's current holder's information, location, and sold date etc.) are revealed only after the product is marked as sold by the seller after the customer's confirmation to buy in case the product is authentic.
  
- **Automatic Warranty Issuance:** When a seller marks a product as sold, the system automatically issues the product's warranty, if applicable.

- **Supply Chain Management:** The system allows manufacturers to efficiently manage their supply chain by facilitating interactions between manufacturers, distributors, distributor agents, and sellers. It offers features such as trader registration, product assignment, and real-time tracking of products at every stage of the supply chain.
 
- **User Roles:**
The actual supply chain is a complex thing. Here assuming the manufacturers are the supplier who supply products to the distributors.
![supply chain role](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/026bc299-adfa-457b-b168-fa393b8f86fa)
  - **Admin:** Administrators have access to an intuitive admin panel where they can manage distributors, generate new products and batches, generate QR codes, monitor product information, see and manage all traders associated with the company's supply chain and view supply chain statistics.
  - **Distributor:** Distributors have a dedicated panel to manage their operations. They can add distributor agents, assign products to agents, track products, and oversee their affiliated products.
  - **Distributor Agent:** Distributor agents have a panel to manage their operations, including adding sellers, assigning products to sellers, and tracking their affiliated products.
  - **Seller:** Sellers can log in to view their assigned products, mark products as sold, and access their sales statistics on a dashboard.
  After adding a user role the user gets a confirmation mail with a link to complete the account by providing a new account password.

## MVC Design Pattern
This project follows the Model-View-Controller (MVC) design pattern for its architecture:

- **Model:** Represents the application's data and business logic. It interacts with the database and provides data to the other components.

- **View:** Handles the presentation layer, rendering data to the user interface and receiving user input.

- **Controller:** Manages the flow of data between the model and the view. It handles user input, processes requests, and updates the model and views accordingly.

## Technologies Used

- Java EE
- JSP (Java Server Pages)
- JavaScript and jQuery
- HTML and CSS
- MySQL
- Maven
- Apache Tomcat-9 
- IntelliJ IDEA Ultimate as IDE

## Installation and Deployment

1. Fork and Clone the repository to your local machine.
2. Import the `supplychain.sql` file provided in this repository into your MySQL database to create the required schemas.
4. Open the project in IntelliJ IDEA or your preferred Java development environment.
5. Configure the database connection parameters in the `dbConfig.properties` under the resources folder.
6. Configure the mail settings with the app password in `mailConfig.properties` under the resources folder to send confirmation mail using `Google SMTP Server`. Generate your Gmail app password to use the Google SMTP server to send mail. [Click here](https://support.google.com/mail/answer/185833?hl=en) to see how to generate an app password.
7. Build the project using Maven to resolve dependencies and deploy the application on Tomcat-9 or your preferred servlet container.
8. Open the admin panel by visiting `http://localhost:8080/my_web_app_war_exploded/AdminPanel`, use email: `admin@supplier` password: `password@admin` for the first time during initialization and later change the password.

## License
[MIT](https://github.com/im-nayeem/Auth-SupplyChain-java/blob/main/LICENSE)

## Project Report
Please find the project report [here](https://www.overleaf.com/read/dngpbwvhntvp).

## Screenshots
![homepage](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/4c56e9b6-b8f0-42b0-b771-ee4d908a69bd)
![responsive homepage](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/c7da675c-1cbb-4f4e-bf70-19b5ac677017)
![admin dashboard](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/c1c0416d-eb11-42f0-bee2-a2e37d233dc8)
![products](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/39483627-586e-40ac-8736-131e99fb111c)
![batches](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/23e26ae4-f8dd-4831-80ea-669301ce31de)
![bath products](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/fdc7ea2f-e38a-49c7-8054-33370e050c1b)
![qr codes](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/cc13da9e-dc01-4c2c-8fe6-d53e2f54dca9)
![traders](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/ba6abf02-31df-4de8-8120-0a7e629c77f7)
![release-product](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/4e61c142-c5a7-4c3a-8be1-2c31199d17e5)
![add-batch](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/10ac3d65-c86e-43bc-8306-fe383232dedf)
![add-distributor](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/3d37a1fe-2f34-4a40-b512-9904a9659729)
![confirmation-by-email](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/50a833a8-cf3e-42c1-8b7e-5156ef4b781c)
![supply-product](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/e44a4c5a-ccf6-4957-b10e-29ce9b794867)
![image](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/5f2d3c1d-bc49-4f1f-932c-cfa59df2aba5)
![sold product](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/e8bdd79d-1f4f-4e80-83e9-d181b0eafb4b)
![affiliated product](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/171a1914-0a2b-4dc3-b1a9-f36c5840b611)
![mark as sold](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/75f7fccb-03f8-43a0-95c4-2c59df9a4747)
![mark sold](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/d5f0163b-e78e-42fc-b952-be2d1043117c)
![sold stats](https://github.com/im-nayeem/Auth-SupplyChain-java/assets/77660934/af8456d1-837a-4c89-8371-8179c500cb2b)









