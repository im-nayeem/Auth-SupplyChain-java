-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 11, 2023 at 04:15 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `acc_id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `hash_key` varchar(1000) NOT NULL,
  `salt` varchar(1000) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`acc_id`, `email`, `hash_key`, `salt`, `uid`) VALUES
(2, 'nayeemcse@stud.cou.ac.bd', 'pxR9LUGP7fs1vcMKHgiTif6Pvb8WwdeNqLF6Z36PzTEsqKkLjn9vQtsF+Umwomw5hPM5IHIvY0n/aw08hjswcw==', 'nD/KvZrC6ZP+gT5tWkPjoFlBk9y4s/9alHIeUAnenjQrdxz8EwfF3CYj9+EMsazBxrtTQZNkuQsUVOtV4/gtqFSM6G32EM3BPfxsJkNhcRbMqvchqYP9i2v51rKXIIJKpGL4reddCpjYAn6ZL4duYimEFFb+iBcbiC2yF5ps1IOc99U/FsKgSgCkqt5I9on1h79uR1RKfo7rQ7oe8/ESaj6Djyvd0ZfesGAJPLALH30jYwcZAFG6hwdmFQYiNxyv3SNkFrInxc6gFbrjfOeOWXhthFq9FxrjBn3d09wCLLiKxSevbZ+VEgKXn/3APuqTbkilh1r1Q358TY9p5+SEwYN6fVO20nwvKs65XmaICqt5bG43DGXg3MB3bs34SS25SeZGXRUkif+3+QwplKtKseLQKS4OcWi5bGeSbDjfWx8MNFsO69JiS/+z1lvX6n+W2P09VrXKbFnlZAdEavaPKZmR70Tb1xxNwD8n0GYjH060nNY2PRRyFbiAWzOMpQ1ZxQj4yuHWKQTpoxQN8dFWFC2UyIG6HNL8SUQ9VcOwVqOBvrNRXQra6D4XHLgM0a11UjDsbdk1Vy+cpnfPjsB4ztcuZoLQoHDbmeoT7gNwo0q7+pwMWVHjX6sLqyc+jAVD2ZnuIYCG2MvJWbKyOTuYtNEnpng=', 123456789),
(3, 'nayeem.cse.cou@gmail.com', 'hPrIq1VFDBkdj6S8qK7zebjNz6FJu0v8Wka3DZU8QrdlKbYFWKFRT3aDgAi6LK6Dh5asmolAs7sPnfIdRODPlQ==', 'WHGf/pVP1xZILgvk7NSEq6kM3nftKeghL8gKJrwk7+sAJSuB6+I4obN1IFMvHTf6m4A+HPEqhFIevjXyQuTSk+l4T9rLSXb7S4XhZMJu63u65k5ayPzoC9TIHdcuvVvORN7N0HoOIdNeEH7mcdMlQ5KT45+Loy8ltuX/K59aIrLcjvSaMyfjFw9SAy4ac2BY/iQkKhFDdVZ/XxgKreFICz5izPmnfqq8DR8PyCa9hfLdyuoS7DfuqqqxRlQLR2m7Awf2ph2u9k3BBYuqD/qQK9Gw8zVmokxL0owZYvbR25XFUsLpi0QUzF3rWwD474UQde5NoKrjylP8qg/1b5EFVN9jLTB2jS/96AsRQkH0iCqZZpQXVwaZq4MkQkMYSUcwE3Ao1ElQpONTaJpM8Jc+/7Nsn8qDhO4TXnORuOOI+JDYvLI3oD16xAvB4Th/ZrGOLJihkSH05yW5k9ZVpvwVMk0GweyPpT5PJBTo9ILQeXidK0q+y2Swm8letVxKzfJQT9KjDAM13ZfQ7BzLlBkWqo2kuwnUAsvs6m3Noo0R2mN6dF80Wb1IgUNWpvNTRbaVuI7KJXumE2NEqWy2ZtMyg2AuPBlqbtf2/FMMzf+bGW5kCtj+5ykbiY0IDVVigtXecaaXJ6rc7jRAVx0/gjayTHhycuc=', 123456987),
(4, 'hnayeem520@gmail.com', '4wUoyIKN8iQJr1+mLI3I0Ivc+o05WeHl5gMRjOe4yp3fo49ZAHL7RvCq6Nxnq6wrICJnllfEL4HATpYyl5q0PQ==', 'D7Tl4girtmwYKUkJyScgaTO7xGgOZbzd13NasAAlJC3tOFX+yfmRn7m2VBkjK6siag5bEUd10rVk3+9f8AlK+YcqpKaBHVNwDolK9LcMVMZtlY3HKGr+MIq//2t28ljmLLOwvfwmkXEcLftknp8oZaLATlHumW+CvXUncMX+JHF+wH6lU2FBktxYpqzL1LkHoQMgIVK+dRE3S2sSWbMS83NjcHnH19DW8c+R6XBXxCdotpub8zzCGZGtOqgASqnfnbAjbKOBehBoNfEp63y6FfVE711WBz6wVX/1ng1WzSN78WM0uAIglL5ri0HIIfvWX9tVLIkntmIYpaH3N+Z8+g6rV5Lsq68hGEVodqg0wVdXzxZKCpN2pBq6XeVEignKPiGIoGDjYhO2rC8nARqziI6yuHWtoYA6v0JI2PqZqVq5tanhmihtMrFDDWWtjnZlpeEjGoYvaucov48wL/D+oQVm7CCxW9yC1ky455UxRXkWL+9mHQGZfSIK2DfJ1xmxn8vSibnnSt3GqkEXGPmLgQpJFyAHSUtHu9y2Bayf1qG3BbZ1z/pgcQGm6AREUyixM/Lvqt4/rMfzVdsw0Eb9EfBsdZi2tOFuuBaSkBOpcrHuE9z/AXce4REX4M8Sg6Mm8ZCKYWZVPJyW0fDkCUlptCNdJbQ=', 123654987);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` varchar(15) NOT NULL,
  `division` varchar(20) DEFAULT NULL,
  `district` varchar(20) DEFAULT NULL,
  `upazila` varchar(30) DEFAULT NULL,
  `uniion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `division`, `district`, `upazila`, `uniion`) VALUES
('6453553186', 'Dhaka', 'Kishoreganj', 'Austagram', 'Austagram Sadar'),
('8624674302', 'Mymensingh', 'Mymensingh', 'Dhobaura', 'Dhobaura'),
('8624694330', 'Mymensingh', 'Mymensingh', 'Haluaghat', 'Haluaghat');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `email` varchar(30) DEFAULT NULL,
  `hash_key` varchar(500) DEFAULT NULL,
  `salt` varchar(500) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`email`, `hash_key`, `salt`, `id`) VALUES
('hnayeem520@gmail.com', 'A6Y4Of0eF+oiuASyAvMTvMkJ19LagMPNDV4ssJKIlDdqv1AQuH0EIYFIOxERfXj5xT5mSoruXLChuMhT4daRng==', 'qgGpyBa8VX42tH87VuYk0WPVuLw6SgmS24Q3yhD5xcrE2dzdvOOF6FGTdTNl+l4y4IEmpFIw8RtepLpUxVKoER7X8HF59ZFkdFyte7suyT0zHWIniiLfCqdaKLWP/d5XhOYNgg==', 1);

-- --------------------------------------------------------

--
-- Table structure for table `batch`
--

CREATE TABLE `batch` (
  `batch_id` varchar(15) NOT NULL,
  `total_product` int(11) DEFAULT NULL,
  `p_code` varchar(5) DEFAULT NULL,
  `manufac_date` date DEFAULT NULL,
  `exp_date` date DEFAULT NULL,
  `warranty_year` int(11) DEFAULT NULL,
  `warranty_month` int(11) DEFAULT NULL,
  `manufactured` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `batch`
--

INSERT INTO `batch` (`batch_id`, `total_product`, `p_code`, `manufac_date`, `exp_date`, `warranty_year`, `warranty_month`, `manufactured`) VALUES
('B01AP', 10, 'AP', '2023-03-15', '2023-03-15', 0, 6, 1),
('B01HT', 12, 'HT', '2023-03-14', '2026-12-14', 0, 6, 1),
('B01NP', 10, 'NP', '2023-03-11', '2023-03-11', 0, 2, 1),
('B01SP', 5, 'SP', '2023-04-21', '2023-04-21', 1, 0, 1),
('B01UISI', 5, 'UISI', '2023-04-09', '2023-04-09', 0, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `company_info`
--

CREATE TABLE `company_info` (
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `logo_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `company_info`
--

INSERT INTO `company_info` (`name`, `address`, `phone_number`, `email`, `description`, `logo_url`) VALUES
('Amar Company', 'nai', '01521726130', 'amarcompany@gmail.com', 'bolbona', 'http://nourl.com');

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE `distributor` (
  `uid` int(11) DEFAULT NULL,
  `dist_center_road` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `distributor`
--

INSERT INTO `distributor` (`uid`, `dist_center_road`) VALUES
(123456789, 'bolbona');

-- --------------------------------------------------------

--
-- Table structure for table `distributor_agent`
--

CREATE TABLE `distributor_agent` (
  `uid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `distributor_agent`
--

INSERT INTO `distributor_agent` (`uid`) VALUES
(123456987);

-- --------------------------------------------------------

--
-- Table structure for table `product_map`
--

CREATE TABLE `product_map` (
  `name` varchar(100) DEFAULT NULL,
  `p_code` varchar(5) NOT NULL,
  `have_warranty` varchar(3) DEFAULT NULL,
  `have_expiration` varchar(3) DEFAULT NULL,
  `table_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_map`
--

INSERT INTO `product_map` (`name`, `p_code`, `have_warranty`, `have_expiration`, `table_name`) VALUES
('A Product', 'AP', 'yes', 'no', 'table_ap'),
('Laptop Battery', 'HT', 'yes', 'no', 'table_ht'),
('New Product', 'NP', 'yes', 'no', 'table_np'),
('SmartPhone', 'SP', 'yes', 'no', 'table_sp'),
('Headphone', 'UISI', 'yes', 'no', 'table_uisi');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'seller'),
(2, 'distributor'),
(3, 'distributorAgent');

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

CREATE TABLE `seller` (
  `uid` int(11) DEFAULT NULL,
  `shop_name` varchar(30) DEFAULT NULL,
  `shop_road` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seller`
--

INSERT INTO `seller` (`uid`, `shop_name`, `shop_road`) VALUES
(123654987, 'Amar Dokan', 'abc road');

-- --------------------------------------------------------

--
-- Table structure for table `table_ap`
--

CREATE TABLE `table_ap` (
  `pid` varchar(20) NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  `sold_date` date DEFAULT NULL,
  `last_holder` int(11) DEFAULT NULL,
  `batch` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_ap`
--

INSERT INTO `table_ap` (`pid`, `status`, `sold_date`, `last_holder`, `batch`) VALUES
('AP0', 'produced', NULL, NULL, 'B01AP'),
('AP1', 'produced', NULL, NULL, 'B01AP'),
('AP2', 'produced', NULL, NULL, 'B01AP'),
('AP3', 'produced', NULL, NULL, 'B01AP'),
('AP4', 'produced', NULL, NULL, 'B01AP'),
('AP5', 'produced', NULL, NULL, 'B01AP'),
('AP6', 'produced', NULL, NULL, 'B01AP'),
('AP7', 'produced', NULL, NULL, 'B01AP'),
('AP8', 'produced', NULL, NULL, 'B01AP'),
('AP9', 'produced', NULL, NULL, 'B01AP');

-- --------------------------------------------------------

--
-- Table structure for table `table_ht`
--

CREATE TABLE `table_ht` (
  `pid` varchar(20) NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  `sold_date` date DEFAULT NULL,
  `last_holder` int(11) DEFAULT NULL,
  `batch` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_ht`
--

INSERT INTO `table_ht` (`pid`, `status`, `sold_date`, `last_holder`, `batch`) VALUES
('HT0', 'sold', '2023-03-14', 123654987, 'B01HT'),
('HT1', 'distributed', '2023-03-14', 123456987, 'B01HT'),
('HT10', 'produced', NULL, NULL, 'B01HT'),
('HT11', 'produced', NULL, NULL, 'B01HT'),
('HT2', 'stored', '2023-03-14', 123456789, 'B01HT'),
('HT3', 'stored', '2023-03-14', 123456789, 'B01HT'),
('HT4', 'stored', '2023-03-14', 123456789, 'B01HT'),
('HT5', 'stored', '2023-03-14', 123456789, 'B01HT'),
('HT6', 'produced', NULL, NULL, 'B01HT'),
('HT7', 'produced', NULL, NULL, 'B01HT'),
('HT8', 'produced', NULL, NULL, 'B01HT'),
('HT9', 'produced', NULL, NULL, 'B01HT');

-- --------------------------------------------------------

--
-- Table structure for table `table_np`
--

CREATE TABLE `table_np` (
  `pid` varchar(20) NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  `sold_date` date DEFAULT NULL,
  `last_holder` int(11) DEFAULT NULL,
  `batch` varchar(15) DEFAULT NULL,
  `distributor` int(11) DEFAULT NULL,
  `supplier` int(11) DEFAULT NULL,
  `seller` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_np`
--

INSERT INTO `table_np` (`pid`, `status`, `sold_date`, `last_holder`, `batch`, `distributor`, `supplier`, `seller`) VALUES
('NP0', 'supplied', '2023-03-11', 123654987, 'B01NP', NULL, NULL, NULL),
('NP1', 'supplied', '2023-03-11', 123654987, 'B01NP', NULL, NULL, NULL),
('NP2', 'sold', '2023-03-11', 123654987, 'B01NP', NULL, NULL, NULL),
('NP3', 'supplied', '2023-03-11', 123654987, 'B01NP', NULL, NULL, NULL),
('NP4', 'supplied', '2023-03-11', 123654987, 'B01NP', NULL, NULL, NULL),
('NP5', 'supplied', '2023-03-11', 123654987, 'B01NP', NULL, NULL, NULL),
('NP6', 'stored', '2023-03-19', 123456789, 'B01NP', 123456789, NULL, NULL),
('NP7', 'distributed', '2023-03-11', 123456987, 'B01NP', NULL, NULL, NULL),
('NP8', 'distributed', '2023-03-11', 123456987, 'B01NP', NULL, NULL, NULL),
('NP9', 'distributed', '2023-03-11', 123456987, 'B01NP', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `table_sp`
--

CREATE TABLE `table_sp` (
  `pid` varchar(20) NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  `sold_date` date DEFAULT NULL,
  `last_holder` int(11) DEFAULT NULL,
  `batch` varchar(15) DEFAULT NULL,
  `distributor` int(11) DEFAULT NULL,
  `distributor_agent` int(11) DEFAULT NULL,
  `seller` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_sp`
--

INSERT INTO `table_sp` (`pid`, `status`, `sold_date`, `last_holder`, `batch`, `distributor`, `distributor_agent`, `seller`) VALUES
('SP0', 'sold', '2023-08-11', 123654987, 'B01SP', 123456789, 123456987, 123654987),
('SP1', 'sold', '2023-08-11', 123654987, 'B01SP', 123456789, 123456987, 123654987),
('SP2', 'supplied', '2023-04-27', 123456789, 'B01SP', 123456789, NULL, NULL),
('SP3', 'supplied', '2023-04-27', 123456789, 'B01SP', 123456789, NULL, NULL),
('SP4', 'sold', '2023-08-11', 123654987, 'B01SP', 123456789, 123456987, 123654987);

-- --------------------------------------------------------

--
-- Table structure for table `table_uisi`
--

CREATE TABLE `table_uisi` (
  `pid` varchar(20) NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  `sold_date` date DEFAULT NULL,
  `last_holder` int(11) DEFAULT NULL,
  `batch` varchar(15) DEFAULT NULL,
  `distributor` int(11) DEFAULT NULL,
  `supplier` int(11) DEFAULT NULL,
  `seller` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table_uisi`
--

INSERT INTO `table_uisi` (`pid`, `status`, `sold_date`, `last_holder`, `batch`, `distributor`, `supplier`, `seller`) VALUES
('UISI0', 'supplied', '2023-04-09', 123654987, 'B01UISI', 123456789, 123456987, 123654987),
('UISI1', 'supplied', '2023-04-09', 123654987, 'B01UISI', 123456789, 123456987, 123654987),
('UISI2', 'stored', '2023-04-09', 123456789, 'B01UISI', 123456789, NULL, NULL),
('UISI3', 'produced', NULL, NULL, 'B01UISI', NULL, NULL, NULL),
('UISI4', 'produced', NULL, NULL, 'B01UISI', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `nid` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address_id` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`nid`, `name`, `email`, `address_id`) VALUES
(123456789, 'Distributor', 'nayeemcse@stud.cou.ac.bd', '8624694330'),
(123456987, 'DistributorAgent', 'nayeem.cse.cou@gmail.com', '8624674302'),
(123654987, 'Seller', 'hnayeem520@gmail.com', '8624674302');

-- --------------------------------------------------------

--
-- Table structure for table `user_product_affiliation`
--

CREATE TABLE `user_product_affiliation` (
  `nid` int(11) DEFAULT NULL,
  `p_code` varchar(5) DEFAULT NULL,
  `affiliated` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_product_affiliation`
--

INSERT INTO `user_product_affiliation` (`nid`, `p_code`, `affiliated`) VALUES
(123456789, 'SP', 1),
(123456987, 'SP', 1),
(123654987, 'SP', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `uid` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`uid`, `role_id`) VALUES
(123456789, 2),
(123456987, 3),
(123654987, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`acc_id`),
  ADD KEY `email` (`email`),
  ADD KEY `idx_account_uid` (`uid`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`),
  ADD KEY `idx_address_id` (`address_id`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `batch`
--
ALTER TABLE `batch`
  ADD PRIMARY KEY (`batch_id`),
  ADD KEY `idx_batch` (`batch_id`),
  ADD KEY `idx_batch_p_code` (`p_code`);

--
-- Indexes for table `distributor`
--
ALTER TABLE `distributor`
  ADD KEY `idx_distributor` (`uid`);

--
-- Indexes for table `distributor_agent`
--
ALTER TABLE `distributor_agent`
  ADD KEY `idx_supplier` (`uid`);

--
-- Indexes for table `product_map`
--
ALTER TABLE `product_map`
  ADD PRIMARY KEY (`p_code`),
  ADD KEY `idx_product_map` (`p_code`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `seller`
--
ALTER TABLE `seller`
  ADD KEY `idx_seller` (`uid`);

--
-- Indexes for table `table_ap`
--
ALTER TABLE `table_ap`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `last_holder` (`last_holder`),
  ADD KEY `batch` (`batch`);

--
-- Indexes for table `table_ht`
--
ALTER TABLE `table_ht`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `last_holder` (`last_holder`),
  ADD KEY `batch` (`batch`);

--
-- Indexes for table `table_np`
--
ALTER TABLE `table_np`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `last_holder` (`last_holder`),
  ADD KEY `batch` (`batch`);

--
-- Indexes for table `table_sp`
--
ALTER TABLE `table_sp`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `last_holder` (`last_holder`),
  ADD KEY `distributor` (`distributor`),
  ADD KEY `supplier` (`distributor_agent`),
  ADD KEY `seller` (`seller`),
  ADD KEY `batch` (`batch`);

--
-- Indexes for table `table_uisi`
--
ALTER TABLE `table_uisi`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `last_holder` (`last_holder`),
  ADD KEY `distributor` (`distributor`),
  ADD KEY `supplier` (`supplier`),
  ADD KEY `seller` (`seller`),
  ADD KEY `batch` (`batch`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`nid`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `address_id` (`address_id`),
  ADD KEY `idx_users_id` (`nid`);

--
-- Indexes for table `user_product_affiliation`
--
ALTER TABLE `user_product_affiliation`
  ADD UNIQUE KEY `nid` (`nid`,`p_code`),
  ADD KEY `p_code` (`p_code`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `uid` (`uid`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `acc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON UPDATE CASCADE;

--
-- Constraints for table `batch`
--
ALTER TABLE `batch`
  ADD CONSTRAINT `batch_ibfk_1` FOREIGN KEY (`p_code`) REFERENCES `product_map` (`p_code`);

--
-- Constraints for table `distributor`
--
ALTER TABLE `distributor`
  ADD CONSTRAINT `distributor_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`nid`);

--
-- Constraints for table `distributor_agent`
--
ALTER TABLE `distributor_agent`
  ADD CONSTRAINT `distributor_agent_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`nid`);

--
-- Constraints for table `seller`
--
ALTER TABLE `seller`
  ADD CONSTRAINT `seller_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`nid`);

--
-- Constraints for table `table_ap`
--
ALTER TABLE `table_ap`
  ADD CONSTRAINT `table_ap_ibfk_1` FOREIGN KEY (`last_holder`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_ap_ibfk_2` FOREIGN KEY (`batch`) REFERENCES `batch` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `table_ht`
--
ALTER TABLE `table_ht`
  ADD CONSTRAINT `table_ht_ibfk_1` FOREIGN KEY (`last_holder`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_ht_ibfk_2` FOREIGN KEY (`batch`) REFERENCES `batch` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `table_np`
--
ALTER TABLE `table_np`
  ADD CONSTRAINT `table_np_ibfk_1` FOREIGN KEY (`last_holder`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_np_ibfk_2` FOREIGN KEY (`batch`) REFERENCES `batch` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `table_sp`
--
ALTER TABLE `table_sp`
  ADD CONSTRAINT `table_sp_ibfk_1` FOREIGN KEY (`last_holder`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_sp_ibfk_2` FOREIGN KEY (`distributor`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_sp_ibfk_3` FOREIGN KEY (`distributor_agent`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_sp_ibfk_4` FOREIGN KEY (`seller`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_sp_ibfk_5` FOREIGN KEY (`batch`) REFERENCES `batch` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `table_uisi`
--
ALTER TABLE `table_uisi`
  ADD CONSTRAINT `table_uisi_ibfk_1` FOREIGN KEY (`last_holder`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_uisi_ibfk_2` FOREIGN KEY (`distributor`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_uisi_ibfk_3` FOREIGN KEY (`supplier`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_uisi_ibfk_4` FOREIGN KEY (`seller`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `table_uisi_ibfk_5` FOREIGN KEY (`batch`) REFERENCES `batch` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`);

--
-- Constraints for table `user_product_affiliation`
--
ALTER TABLE `user_product_affiliation`
  ADD CONSTRAINT `user_product_affiliation_ibfk_1` FOREIGN KEY (`nid`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `user_product_affiliation_ibfk_2` FOREIGN KEY (`p_code`) REFERENCES `product_map` (`p_code`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`nid`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
