-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  lun. 30 mars 2020 à 17:55
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pokedex`
--
CREATE DATABASE IF NOT EXISTS `pokedex` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pokedex`;

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_annee` (IN `p_num_annee` CHAR(4))  BEGIN
	INSERT INTO Annee (numero_annee) VALUES (p_num_annee);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_Avoir_Type` (IN `p_num_pkdx` CHAR(3), IN `p_code_type` INT)  BEGIN
	INSERT INTO Avoir_Type (num_pkdex_monde, code_type) VALUES ((
    SELECT num_pkdx_monde FROM Pokemon WHERE num_pkdx_monde = p_num_pkdx), (
    SELECT code_type FROM Type WHERE code_type = p_code_type));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_Evolution` (IN `p_id` INT, IN `p_lib` CHAR(7), IN `p_sous_evol` CHAR(3), IN `p_sur_evol` CHAR(3), IN `p_type_evol` INT)  BEGIN
    DECLARE sous CHAR(3); DECLARE sur CHAR(3); DECLARE tp INT;
    SET sous := (SELECT num_pkdx_monde FROM Pokemon WHERE num_pkdx_monde = p_sous_evol);
	SET sur := (SELECT num_pkdx_monde FROM Pokemon WHERE num_pkdx_monde = p_sur_evol);
	SET tp := (SELECT code_type FROM type WHERE code_type = p_type_evol);
	INSERT INTO evolution(id_evol, libelle_court_evol, num_pkdex_sous_evol, num_pkdex_sur_evol, type_evol)
	VALUES(p_id, p_lib, sous, sur, tp);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_Generation` (IN `p_num_gen` INT, IN `p_lib` VARCHAR(3), IN `p_num_annee` CHAR(4))  BEGIN
	SET FOREIGN_KEY_CHECKS=0;
	INSERT INTO Generation (numero_generation, libelle_generation, annee_generation)
	VALUES (p_num_gen, p_lib, p_num_annee);
	SET FOREIGN_KEY_CHECKS=1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_Image` (IN `p_id` INT, IN `p_url` VARCHAR(50), IN `p_ext` VARCHAR(5))  BEGIN
	INSERT INTO Images (id_img, url_img, extension_img) VALUES (p_id, p_url, p_ext);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_Pkm` (IN `p_num_pkdx` CHAR(3), IN `p_nom` VARCHAR(20), IN `p_desc` VARCHAR(400), IN `p_url` INT, IN `p_legendaire` TINYINT(1), IN `p_gen_appar` INT)  BEGIN
	INSERT INTO Pokemon (num_pkdx_monde, nom_pkm, desc_pkdx_pkm, url_img_pkm, part_legend_pkm, gen_apparition_pkm) 
	VALUES (p_num_pkdx, p_nom, p_desc, (SELECT id_img FROM images Where id_img = p_url), p_legendaire, (SELECT numero_generation FROM generation Where numero_generation = p_gen_appar));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_Type` (IN `p_id` INT, IN `p_lib` VARCHAR(15), IN `p_gen` INT)  BEGIN
	SET FOREIGN_KEY_CHECKS=0;
	INSERT INTO Type (code_type, libelle_type, gen_apparition_type) VALUES (p_id, p_lib, (SELECT numero_generation FROM generation Where numero_generation = p_gen));
	SET FOREIGN_KEY_CHECKS=1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Insert_TypeEvol` (IN `p_id` INT, IN `p_lib` VARCHAR(30))  BEGIN 
	INSERT INTO Type_Evolution (id_type_evol, libelle_type_evol) 
	VALUES (p_id, p_lib);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Annee_ordreA` ()  BEGIN
	Select numero_annee as num 
	From Annee
	Order By num;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Avoir_Type_ordAPkm` ()  BEGIN
	Select num_pkdx_monde as num, code_type as code
	From Avoir_Type
	Order By num;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_cbx_img` ()  BEGIN
	Select CONCAT(url_img, extension_img) as url, id_img as id
	From Images
	Order By id_img;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_cbx_pkm_img` ()  BEGIN
	Select url_img as url, extension_img as ext, num_pkdx_monde as numPkdx, nom_pkm as nomPkm
	From Images
	Inner Join Pokemon
	On id_img = url_img_pkm
	Order By id_img;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_cbx_pkm_img_B` ()  BEGIN
	Select CONCAT(url_img, extension_img) as url, num_pkdx_monde as numPkdx, nom_pkm as nomPkm
	From Images
	Inner Join Pokemon
	On id_img = url_img_pkm
	Order By id_img;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Count_Avoir_Type_Pkm` (IN `p_num_pkdx` CHAR(3))  BEGIN
	Select COUNT(code_type) as numTypePkm
	From Avoir_Type
	WHERE num_pkdex_monde = p_num_pkdx;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Evolution_ordASousEvol` ()  BEGIN
	Select libelle_court_evol as lib, num_pkdex_sous_evol as sous_evol, num_pkdex_sur_evol as sur_evol, type_evol as typeEvol
	From Evolution
	Order By sous_evol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Generation_ordreA` ()  BEGIN
	Select numero_generation as num, libelle_generation as lib, annee_generation as annee
	From Generation
	Order By num;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Image_ordreId` ()  BEGIN
	Select id_img as id, url_img as url, extension_img as ext
	From Images
	Order By id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Pkm_OrdrePkdx` ()  BEGIN 
	Select num_pkdx_monde as num, nom_pkm as nom, desc_pkdx_pkm as descr, url_img_pkm as url, part_legend_pkm as legendaire, gen_apparition_pkm as gen
	From Pokemon
	Order By num;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_TypeEvol_OrdreA` ()  BEGIN 
	Select id_type_evol as id, libelle_type_evol as lib
	From Type_Evolution
	Order By lib;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_Select_Type_ordreACode` ()  BEGIN
	Select code_Type as code, libelle_type as lib, gen_apparition_type as gen
	From Type
	Order By code;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `annee`
--

CREATE TABLE IF NOT EXISTS `annee` (
  `numero_annee` char(4) NOT NULL,
  PRIMARY KEY (`numero_annee`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `avoir_type`
--

CREATE TABLE IF NOT EXISTS `avoir_type` (
  `num_pkdex_monde` char(3) NOT NULL,
  `code_type` int(11) NOT NULL,
  PRIMARY KEY (`num_pkdex_monde`,`code_type`),
  KEY `FK_code_type` (`code_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evolution`
--

CREATE TABLE IF NOT EXISTS `evolution` (
  `id_evol` int(11) NOT NULL,
  `libelle_court_evol` char(7) NOT NULL,
  `num_pkdex_sous_evol` char(3) NOT NULL,
  `num_pkdex_sur_evol` char(3) NOT NULL,
  `type_evol` int(11) NOT NULL,
  PRIMARY KEY (`id_evol`),
  KEY `FK_type_evol_evol` (`type_evol`),
  KEY `FK_num_pkdex_sous_evol` (`num_pkdex_sous_evol`),
  KEY `FK_num_pkdex_sur_evol` (`num_pkdex_sur_evol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `generation`
--

CREATE TABLE IF NOT EXISTS `generation` (
  `numero_generation` int(11) NOT NULL,
  `libelle_generation` varchar(3) NOT NULL,
  `annee_generation` char(4) DEFAULT NULL,
  PRIMARY KEY (`numero_generation`),
  KEY `FK_annee_generation` (`annee_generation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

CREATE TABLE IF NOT EXISTS `images` (
  `id_img` int(11) NOT NULL,
  `url_img` varchar(50) DEFAULT NULL,
  `extension_img` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_img`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pokemon`
--

CREATE TABLE IF NOT EXISTS `pokemon` (
  `num_pkdx_monde` char(3) NOT NULL,
  `nom_pkm` varchar(20) NOT NULL,
  `desc_pkdx_pkm` varchar(400) NOT NULL,
  `url_img_pkm` int(11) DEFAULT NULL,
  `part_legend_pkm` tinyint(1) NOT NULL,
  `gen_apparition_pkm` int(11) NOT NULL,
  PRIMARY KEY (`num_pkdx_monde`),
  KEY `FK_gen_apparition_pkm` (`gen_apparition_pkm`),
  KEY `FK_url_images_pkm` (`url_img_pkm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `code_type` int(11) NOT NULL,
  `libelle_type` varchar(15) NOT NULL,
  `gen_apparition_type` int(11) NOT NULL,
  PRIMARY KEY (`code_type`),
  KEY `FK_gen_apparition_type` (`gen_apparition_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_evolution`
--

CREATE TABLE IF NOT EXISTS `type_evolution` (
  `id_type_evol` int(11) NOT NULL,
  `libelle_type_evol` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_type_evol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avoir_type`
--
ALTER TABLE `avoir_type`
  ADD CONSTRAINT `FK_code_type` FOREIGN KEY (`code_type`) REFERENCES `type` (`code_type`),
  ADD CONSTRAINT `FK_num_pkdex_monde` FOREIGN KEY (`num_pkdex_monde`) REFERENCES `pokemon` (`num_pkdx_monde`);

--
-- Contraintes pour la table `evolution`
--
ALTER TABLE `evolution`
  ADD CONSTRAINT `FK_num_pkdex_sous_evol` FOREIGN KEY (`num_pkdex_sous_evol`) REFERENCES `pokemon` (`num_pkdx_monde`),
  ADD CONSTRAINT `FK_num_pkdex_sur_evol` FOREIGN KEY (`num_pkdex_sur_evol`) REFERENCES `pokemon` (`num_pkdx_monde`),
  ADD CONSTRAINT `FK_type_evol_evol` FOREIGN KEY (`type_evol`) REFERENCES `type_evolution` (`id_type_evol`);

--
-- Contraintes pour la table `generation`
--
ALTER TABLE `generation`
  ADD CONSTRAINT `FK_annee_generation` FOREIGN KEY (`annee_generation`) REFERENCES `annee` (`numero_annee`);

--
-- Contraintes pour la table `pokemon`
--
ALTER TABLE `pokemon`
  ADD CONSTRAINT `FK_gen_apparition_pkm` FOREIGN KEY (`gen_apparition_pkm`) REFERENCES `generation` (`numero_generation`),
  ADD CONSTRAINT `FK_url_images_pkm` FOREIGN KEY (`url_img_pkm`) REFERENCES `images` (`id_img`);

--
-- Contraintes pour la table `type`
--
ALTER TABLE `type`
  ADD CONSTRAINT `FK_gen_apparition_type` FOREIGN KEY (`gen_apparition_type`) REFERENCES `generation` (`numero_generation`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
