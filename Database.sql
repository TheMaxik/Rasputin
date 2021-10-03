-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 03. Okt 2021 um 01:36
-- Server-Version: 8.0.26-0ubuntu0.20.04.2
-- PHP-Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `rasputin`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `servers`
--

CREATE TABLE `servers` (
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `motd` text NOT NULL,
  `maxplayers` int NOT NULL,
  `whitelist` int NOT NULL,
  `version` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `servers`
--

INSERT INTO `servers` (`ip`, `motd`, `maxplayers`, `whitelist`, `version`, `time`) VALUES
('1.11.169.93', 'A Minecraft Server', 20, 0, '>=1.8b/1.3', '2021-09-28 23:59:12');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `servers`
--
ALTER TABLE `servers`
  ADD PRIMARY KEY (`ip`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
