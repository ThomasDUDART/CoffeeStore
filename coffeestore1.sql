-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 13 avr. 2023 à 08:54
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `coffeestore`
--

-- --------------------------------------------------------

--
-- Structure de la table `beans`
--

DROP TABLE IF EXISTS `beans`;
CREATE TABLE IF NOT EXISTS `beans` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pu` int NOT NULL,
  `prix` float NOT NULL,
  `quantite` int NOT NULL,
  `score` int NOT NULL,
  `torrefaction` int NOT NULL,
  `origine` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `beans_ibfk_2` (`origine`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `beans`
--

INSERT INTO `beans` (`id`, `nom`, `pu`, `prix`, `quantite`, `score`, `torrefaction`, `origine`) VALUES
(2, 'Moka Baraka', 1000, 21, 50, 70, 7, 4),
(3, 'Straordinario Gran Miscela', 1000, 20.5, 50, 84, 6, 6),
(4, 'Elite', 1000, 18.5, 50, 68, 9, 2),
(5, 'Brésil Guatemala', 250, 9.78, 40, 65, 7, 3),
(6, 'Bourbon South Huila', 250, 17.9, 20, 86, 9, 7),
(7, 'Old Yeti', 250, 14.9, 25, 96, 5, 2),
(8, 'Heirloom Wallaga', 1000, 44.5, 10, 86, 3, 6),
(9, 'El Cuto', 250, 10.6, 30, 75, 6, 3),
(10, 'Dipilto Viejo', 250, 12.6, 56, 78, 5, 4),
(12, 'Le Mélange Gourmet', 250, 7.5, 25, 86, 7, 2),
(13, 'Caffè Vergnano', 1000, 16.28, 30, 75, 6, 4);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `note`) VALUES
(3, 'cacao'),
(4, 'cèdre'),
(5, 'vanille'),
(6, 'cannelle'),
(7, 'ricassou'),
(8, 'embrayage de peugeot 106'),
(9, 'banane'),
(10, 'noisette'),
(11, 'orange');

-- --------------------------------------------------------

--
-- Structure de la table `notebeans`
--

DROP TABLE IF EXISTS `notebeans`;
CREATE TABLE IF NOT EXISTS `notebeans` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bean` int NOT NULL,
  `notes` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `BeansId` (`bean`),
  KEY `NoteId` (`notes`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `notebeans`
--

INSERT INTO `notebeans` (`id`, `bean`, `notes`) VALUES
(1, 2, 3),
(2, 2, 5),
(3, 3, 3),
(5, 3, 4),
(6, 4, 7),
(11, 4, 8),
(12, 5, 7),
(13, 5, 4),
(14, 6, 8),
(15, 6, 5),
(16, 7, 4),
(17, 7, 7),
(18, 8, 9),
(19, 8, 8),
(20, 9, 10),
(21, 9, 11),
(22, 10, 10),
(23, 10, 8),
(24, 12, 8),
(27, 12, 4),
(28, 13, 7),
(29, 13, 5);

-- --------------------------------------------------------

--
-- Structure de la table `origine`
--

DROP TABLE IF EXISTS `origine`;
CREATE TABLE IF NOT EXISTS `origine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `continent` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pays` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ferme` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `origine`
--

INSERT INTO `origine` (`id`, `continent`, `pays`, `ferme`) VALUES
(2, 'Amérique Latine', 'Brésil', 'Inconnu'),
(3, 'Amérique Latine', 'Guatemala', 'Ferme au rennes'),
(4, 'Amérique Latine', 'Argentine', 'Helmut Reich & Co'),
(5, 'Amérique Latine', 'Colombie', 'Inconnu'),
(6, 'Amérique Latine', 'Nicaraga', 'Hola'),
(7, 'Afrique', 'Ethiopie', 'ONU'),
(8, 'Afrique', 'Ethiopie', 'Inconnu');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `beans`
--
ALTER TABLE `beans`
  ADD CONSTRAINT `beans_ibfk_2` FOREIGN KEY (`origine`) REFERENCES `origine` (`id`);

--
-- Contraintes pour la table `notebeans`
--
ALTER TABLE `notebeans`
  ADD CONSTRAINT `notebeans_ibfk_1` FOREIGN KEY (`bean`) REFERENCES `beans` (`id`),
  ADD CONSTRAINT `notebeans_ibfk_2` FOREIGN KEY (`notes`) REFERENCES `note` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
