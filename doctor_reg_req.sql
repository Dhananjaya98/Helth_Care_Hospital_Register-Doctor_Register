-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 06, 2020 at 01:56 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor_reg_req`
--

DROP TABLE IF EXISTS `doctor_reg_req`;
CREATE TABLE IF NOT EXISTS `doctor_reg_req` (
  `D_ID` int(12) NOT NULL AUTO_INCREMENT,
  `D_Name` varchar(30) NOT NULL,
  `D_PhoneNumber` varchar(10) NOT NULL,
  `D_Fee` varchar(10) NOT NULL,
  `D_Email` varchar(30) NOT NULL,
  `D_Specialization` varchar(30) NOT NULL,
  `D_NIC` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `D_Hospitals` varchar(100) NOT NULL,
  PRIMARY KEY (`D_ID`),
  UNIQUE KEY `D_NIC` (`D_NIC`)
) ENGINE=InnoDB AUTO_INCREMENT=34224 DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
