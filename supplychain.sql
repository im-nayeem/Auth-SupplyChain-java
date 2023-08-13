-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2023 at 01:15 PM
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
-- Database: `supplychain`
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

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `email` varchar(30) DEFAULT NULL,
  `hash_key` varchar(1000) DEFAULT NULL,
  `salt` varchar(1000) DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`email`, `hash_key`, `salt`, `id`) VALUES
('admin@supplier', 'ypsllYISg1/UqxtdXcc02l94UrNCIFjPgEjDsFKyMZ2YJYdUxAf6tiTbSphahFrMNYpb2tJXQwTAOjoVjWBNdg==', 'RWTNicyJOye97zOhQrjgM+u1rst0iklQ76OzWZOLGnGCE7EeyeDeMn8TbbwSNBR+PEGdy3wVJ7M495wwOin0HHM159SXTY9V5IlK9eZUHy6Eh4gQ2XVjLbuXkrjhZhLaV4OuuA==', 1);

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
  `manufactured` int(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE `distributor` (
  `uid` int(11) DEFAULT NULL,
  `dist_center_road` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `distributor_agent`
--

CREATE TABLE `distributor_agent` (
  `uid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) DEFAULT NULL
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

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `uid` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  ADD KEY `idx_d_agent` (`uid`);

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
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`nid`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `address_id` (`address_id`),
  ADD KEY `idx_users_id` (`nid`);

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
  MODIFY `acc_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`);

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
