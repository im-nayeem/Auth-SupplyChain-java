package com.example.my_web_app;

/**
 * Created on 31-Jan-23
 *
 * @author Nayeem
 */
public class Utility {
    public static String getInitialQuery()
    {
        return "CREATE TABLE admin(\n" +
                "    email varchar(30) UNIQUE,\n" +
                "    hash_key varchar(500),\n" +
                "    salt varchar(500),\n" +
                "    id int not null AUTO_INCREMENT PRIMARY KEY\n" +
                "    );\n" +
                " CREATE table address(\n" +
                "     address_id int not null PRIMARY KEY,\n" +
                "     division varchar(20),\n" +
                "     district varchar(20),\n" +
                "     upazila varchar(30),\n" +
                "     uniion varchar(30)\n" +
                "     );\n" +
                " CREATE INDEX idx_address_id ON address(address_id);\n" +
                " CREATE TABLE role(\n" +
                "     role_id int not null AUTO_INCREMENT PRIMARY KEY,\n" +
                "     role_name varchar(15),\n" +
                "     role_desc varchar(15),\n" +
                "     table_name varchar(10)\n" +
                "     );\n" +
                " CREATE TABLE users(\n" +
                "     nid int PRIMARY KEY,\n" +
                "     name varchar(30),\n" +
                "     email varchar(30) UNIQUE,\n" +
                "     address_id int,\n" +
                "     FOREIGN KEY(address_id) REFERENCES address(address_id)\n" +
                "     );\n" +
                " CREATE INDEX idx_users_id on users(nid);\n" +
                "  CREATE TABLE account(\n" +
                "     acc_id int not null AUTO_INCREMENT PRIMARY KEY,\n" +
                "     email varchar(30),\n" +
                "     hash_key varchar(500),\n" +
                "     salt varchar(500),\n" +
                "     uid int,\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid),\n" +
                "     FOREIGN KEY(email) REFERENCES users(email) on UPDATE CASCADE\n" +
                "     );\n" +
                "  CREATE index idx_account_uid on account(uid);\n" +
                "  CREATE TABLE user_role(\n" +
                "     uid int,\n" +
                "     role_id int,\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid),\n" +
                "     FOREIGN key(role_id) REFERENCES role(role_id)\n" +
                "     );\n" +
                " CREATE TABLE distributor(\n" +
                "     uid int,\n" +
                "     dist_center_road varchar(50),\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid)\n" +
                "     );\n" +
                " CREATE index idx_distributor on distributor(uid);\n" +
                " CREATE TABLE supplier(\n" +
                "     uid int,\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid)\n" +
                "     );\n" +
                " CREATE index idx_supplier on supplier(uid);\n" +
                "  CREATE TABLE seller(\n" +
                "     uid int,\n" +
                "     shop_name varchar(30),\n" +
                "     shop_road varchar(30),\n" +
                "     FOREIGN KEY(uid) REFERENCES users(nid)\n" +
                "     );\n" +
                " CREATE INDEX idx_seller on seller(uid);\n" +
                "CREATE TABLE company_info(\n" +
                "    name varchar(100),\n" +
                "    employee_count int,\n" +
                "    supplier_code varchar(5),\n" +
                "    distributor_code varchar(5),\n" +
                "    seller_code varchar(5)\n" +
                "    );\n" +
                " CREATE TABLE product_map(\n" +
                "     name varchar(100),\n" +
                "     p_code varchar(5) PRIMARY KEY,\n" +
                "     have_warranty varchar(3),\n" +
                "     have_expiration varchar(3),\n" +
                "     table_name varchar(20)\n" +
                "     );\n" +
                " CREATE index idx_product_map on product_map(p_code);\n" +
                " CREATE TABLE batch(\n" +
                "     batch_id varchar(15) PRIMARY KEY,\n" +
                "     total_product int,\n" +
                "     p_code varchar(5),\n" +
                "     manufac_date date,\n" +
                "     exp_date date,\n" +
                "     warranty_year int,\n" +
                "     warranty_month int,\n" +
                "      produced int(1) default 0,\n" +
                "     FOREIGN KEY(p_code) REFERENCES product_map(p_code)\n" +
                "     );\n" +
                " CREATE INDEX idx_batch on batch(batch_id);\n" +
                " CREATE INDEX idx_batch_p_code on batch(p_code);\n" +
                " \n" +
                "\n" +
                "\n";
    }

}
