-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2022 at 04:18 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cisgame`
--

-- --------------------------------------------------------

--
-- Table structure for table `best_score`
--

CREATE TABLE `best_score` (
  `Best_Score_Level` int(11) NOT NULL,
  `Best_Score_Player_Id` int(11) DEFAULT NULL,
  `Best_Score_Point` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `best_score`
--

INSERT INTO `best_score` (`Best_Score_Level`, `Best_Score_Player_Id`, `Best_Score_Point`) VALUES
(1, NULL, 0),
(2, NULL, 0),
(3, NULL, 0),
(4, NULL, 0),
(5, NULL, 0),
(6, NULL, 0),
(7, NULL, 0),
(8, NULL, 0),
(9, NULL, 0),
(10, NULL, 0),
(11, NULL, 0),
(12, NULL, 0),
(13, NULL, 0),
(14, NULL, 0),
(15, NULL, 0),
(16, NULL, 0),
(17, NULL, 0),
(18, NULL, 0),
(19, NULL, 0),
(20, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `game_score`
--

CREATE TABLE `game_score` (
  `Game_Score_Id` int(11) NOT NULL,
  `Player_Id` int(11) NOT NULL,
  `Level` int(11) NOT NULL,
  `Score` int(11) NOT NULL,
  `Time` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `Player_Id` int(11) NOT NULL,
  `Player_Username` varchar(255) NOT NULL,
  `Player_Email` varchar(255) NOT NULL,
  `Player_Password` varchar(255) NOT NULL,
  `Player_Level` int(11) NOT NULL DEFAULT 1,
  `Player_Role` varchar(255) NOT NULL DEFAULT 'Player'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`Player_Id`, `Player_Username`, `Player_Email`, `Player_Password`, `Player_Level`, `Player_Role`) VALUES
(0, 'admin', 'admin', 'admin', 0, 'Admin'),
(5, 'iamafzalahamed2', 'afzal@gmail.com', 'afzal123', 8, 'Player'),
(8, 'iamafzalahamed125', 'afzal25@gmail.com', 'sdasd', 1, 'Player');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `best_score`
--
ALTER TABLE `best_score`
  ADD PRIMARY KEY (`Best_Score_Level`),
  ADD KEY `p3` (`Best_Score_Player_Id`);

--
-- Indexes for table `game_score`
--
ALTER TABLE `game_score`
  ADD PRIMARY KEY (`Game_Score_Id`),
  ADD KEY `pid` (`Player_Id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`Player_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `best_score`
--
ALTER TABLE `best_score`
  MODIFY `Best_Score_Level` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `game_score`
--
ALTER TABLE `game_score`
  MODIFY `Game_Score_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `Player_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `best_score`
--
ALTER TABLE `best_score`
  ADD CONSTRAINT `p3` FOREIGN KEY (`Best_Score_Player_Id`) REFERENCES `player` (`Player_Id`);

--
-- Constraints for table `game_score`
--
ALTER TABLE `game_score`
  ADD CONSTRAINT `pid` FOREIGN KEY (`Player_Id`) REFERENCES `player` (`Player_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
