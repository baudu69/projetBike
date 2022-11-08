SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : byke
--

DROP DATABASE IF EXISTS byke;
CREATE DATABASE IF NOT EXISTS byke;
USE byke;

-- --------------------------------------------------------

--
-- Structure de la table utilisateur
--

CREATE TABLE utilisateur
(
	num_util       INT(11) NOT NULL AUTO_INCREMENT,
	nom_util       CHAR(20)      DEFAULT NULL,
	prenom_util    CHAR(20)      DEFAULT NULL,
	date_naissance DATE          DEFAULT NULL,
	taille         DECIMAL(6, 2) DEFAULT NULL,
	poids          DECIMAL(6, 2) DEFAULT NULL,
	PRIMARY KEY (num_util)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table utilisateur
--

INSERT INTO utilisateur (num_util, nom_util, prenom_util, date_naissance, taille, poids)
VALUES (1, 'GALLOIS', 'Jean', '1964-04-23', '183.00', '75.00'),
       (2, 'VILLE', 'Pierre', '1962-04-12', '173.00', '69.00');

-- --------------------------------------------------------

--
-- Structure de la table sortie
--

CREATE TABLE sortie
(
	num_sortie         INT(11) NOT NULL AUTO_INCREMENT,
	num_util           INT(11) NOT NULL,
	date_sortie        DATE          DEFAULT NULL,
	heure_depart       TIME          DEFAULT NULL,
	heure_arrivee      TIME          DEFAULT NULL,
	lieu_depart        CHAR(30)      DEFAULT NULL,
	distance_parcourue DECIMAL(6, 2) DEFAULT NULL,
	PRIMARY KEY (num_sortie),
	FOREIGN KEY (num_util) REFERENCES utilisateur (num_util)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table sortie
--

INSERT INTO sortie (num_sortie, num_util, date_sortie, heure_depart, heure_arrivee, lieu_depart, distance_parcourue)
VALUES (1, 1, '2022-09-30', '08:15:00', '12:20:00', 'SAINT GENIS LES OLLLIERES', '86.20'),
       (2, 2, '2022-09-23', '08:15:00', '12:45:00', 'SAINT GENIS LES OLLLIERES', '88.10');

-- --------------------------------------------------------

--
-- Structure de la table velo
--

CREATE TABLE velo
(
	id_velo  INT(11) NOT NULL AUTO_INCREMENT,
	nom_velo CHAR(20)      DEFAULT NULL,
	roues    DECIMAL(6, 2) DEFAULT NULL,
	casette  CHAR(10)      DEFAULT NULL,
	PRIMARY KEY (id_velo)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table velo
--

INSERT INTO velo (id_velo, nom_velo, roues, casette)
VALUES (1, 'LAPIERRE X-CONTROL 3', '29.00', '11/42'),
       (2, 'CANONDALE SYNASPE', '700.00', '11/28'),
       (3, 'CUBE Kathmandu Hybri', '29.00', '11/52');


-- --------------------------------------------------------

--
-- Structure de la table vae
--

CREATE TABLE vae
(
	id_velo            INT(11) NOT NULL,
	puissance_moteur   INT(11) DEFAULT NULL,
	puissance_batterie INT(11) DEFAULT NULL,
	PRIMARY KEY (id_velo),
	FOREIGN KEY (id_velo) REFERENCES velo (id_velo)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table vae
--

INSERT INTO vae (id_velo, puissance_moteur, puissance_batterie)
VALUES (3, 250, 625);

-- --------------------------------------------------------

--
-- Structure de la table vtt
--

CREATE TABLE vtt
(
	id_velo INT(11) NOT NULL,
	PRIMARY KEY (id_velo),
	FOREIGN KEY (id_velo) REFERENCES velo (id_velo)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table vtt
--

INSERT INTO vtt (id_velo)
VALUES (1);

-- --------------------------------------------------------

--
-- Structure de la table route
--

CREATE TABLE route
(
	id_velo    INT(11) NOT NULL,
	type_cadre CHAR(20) DEFAULT NULL,
	PRIMARY KEY (id_velo),
	FOREIGN KEY (id_velo) REFERENCES velo (id_velo)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table route
--

INSERT INTO route (id_velo, type_cadre)
VALUES (2, 'CARBONE');


-- --------------------------------------------------------

--
-- Structure de la table achete
--

CREATE TABLE achete
(
	id_velo   INT(11) NOT NULL,
	num_util  INT(11) NOT NULL,
	date_jour DATE    NOT NULL,
	PRIMARY KEY (id_velo, num_util, date_jour),
	FOREIGN KEY (id_velo) REFERENCES velo (id_velo)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table achete
--

INSERT INTO achete (id_velo, num_util, date_jour)
VALUES (1, 1, '2013-11-10'),
       (2, 1, '2018-05-12'),
       (3, 1, '2020-09-15');

-- --------------------------------------------------------

--
-- Structure de la table etape
--

CREATE TABLE etape
(
	id_etape   INT(11) NOT NULL AUTO_INCREMENT,
	num_etape  INT(11) NOT NULL,
	num_sortie INT(11) NOT NULL,
	nom_etape  CHAR(25)        DEFAULT NULL,
	latitude   DECIMAL(16, 14) DEFAULT NULL,
	longitude  DECIMAL(16, 14) DEFAULT NULL,
	PRIMARY KEY (id_etape),
	FOREIGN KEY (num_sortie) REFERENCES sortie (num_sortie),
	UNIQUE KEY (num_etape, num_sortie)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table etape
--

INSERT INTO etape (id_etape, num_etape, num_sortie, nom_etape, latitude, longitude)
VALUES (1, 1, 1, 'RONTALON', '45.66052627560000', '4.63221406937000'),
       (2, 1, 2, 'COL DE LA LUERE', '45.76320538304853', '4.62908812807553'),
       (3, 2, 1, 'SAINT ANDRE LA COTE', '45.63100000000000', '4.60100000000000'),
       (4, 2, 2, 'COL DE MALVAL', '45.74772421385712', '4.61158104883301'),
       (5, 3, 1, 'SAINT MARTIN EN HAUT', '45.66000000000000', '4.55900000000000'),
       (6, 3, 2, 'DUERNE', '45.68635971090647', '4.52697130259405'),
       (7, 4, 1, 'SAINTE CATHERINE', '45.39582000000000', '-73.56574300000000'),
       (8, 4, 2, 'AVEIZE', '45.68026051032419', '4.48057381147755'),
       (9, 5, 1, 'Intersection', '45.62861466155582', '4.52521573815698'),
       (10, 5, 2, 'GREYZIEU LE MARCHE', '45.66182791270027', '4.42396251904246'),
       (11, 6, 1, 'Intersection', '45.65620857484888', '4.55312020725788'),
       (12, 6, 2, 'SAINTE FOY L\'ARGENTIERE', '45.70569896294748', '4.47046508170943'),
       (13, 7, 1, 'CROIX PERRIERE', '45.68594298751449', '4.57593439831060'),
       (14, 7, 2, 'MONTROMANT', '45.70802393301300', '4.52539125664541'),
       (15, 8, 1, 'COL DES BROSSES', '45.70682093090597', '4.55467737970977'),
       (16, 8, 2, 'LA CROIX DE PARS', '45.72126840968257', '4.56804910562751'),
       (17, 9, 1, 'COL DE MALVAL', '45.74772421385712', '4.61158104883301'),
       (18, 9, 2, 'Intersection', '45.72491361448375', '4.59929470994397'),
       (19, 10, 1, 'COL DE LA LUERE', '45.76320538304853', '4.62908812807553'),
       (20, 10, 2, 'COL DE MALVAL', '45.74772421385712', '4.61158104883301'),
       (21, 11, 1, 'POLLIONNAY', '45.76592776077334', '4.66072522354320'),
       (22, 11, 2, 'COL DE LA LUERE', '45.76320538304853', '4.62908812807553'),
       (23, 12, 1, 'SAINTE CONSORCE', '45.77646635536404', '4.69614559515543'),
       (24, 12, 2, 'POLLIONNAY', '45.76592776077334', '4.66072522354320'),
       (25, 13, 1, 'SAINT GENIS LES OLLIERES', '45.75796018172125', '4.72596372582408'),
       (26, 13, 2, 'SAINTE CONSORCE', '45.77646635536404', '4.69614559515543'),
       (27, 14, 2, 'SAINT GENIS LES OLLIERES', '45.75796018172125', '4.72596372582408');


-- --------------------------------------------------------

--
-- Structure de la table a_pour_distance
--

CREATE TABLE a_pour_distance
(
	id_etape_dep INT(11) NOT NULL,
	id_etape_arr INT(11) NOT NULL,
	nbr_km       DECIMAL(6, 2) DEFAULT NULL,
	PRIMARY KEY (id_etape_dep, id_etape_arr),
	FOREIGN KEY (id_etape_dep) REFERENCES etape (id_etape),
	FOREIGN KEY (id_etape_arr) REFERENCES etape (id_etape)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Déchargement des données de la table a_pour_distance
--

INSERT INTO a_pour_distance (id_etape_dep, id_etape_arr, nbr_km)
VALUES (1, 3, '6.40'),
       (3, 5, '6.80'),
       (5, 7, '8.60'),
       (7, 9, '5.60'),
       (9, 11, '5.30'),
       (11, 13, '7.20'),
       (13, 15, '5.10'),
       (15, 17, '8.60'),
       (17, 19, '2.70'),
       (19, 21, '6.50'),
       (21, 23, '5.20'),
       (23, 25, '4.90'),
       (25, 1, '15.20'),
       (2, 4, '2.60'),
       (4, 6, '12.90'),
       (6, 8, '4.60'),
       (8, 10, '6.40'),
       (10, 12, '8.10'),
       (12, 14, '6.70'),
       (14, 16, '5.10'),
       (16, 18, '2.70'),
       (18, 20, '3.70'),
       (20, 22, '2.70'),
       (22, 24, '6.50'),
       (24, 26, '5.20'),
       (26, 27, '4.90'),
       (27, 2, '10.70');


/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
