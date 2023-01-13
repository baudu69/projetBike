
drop database if exists byke;
create database byke;
use byke;

CREATE TABLE hibernate_sequence
(
    next_val BIGINT NULL
) CHARSET = utf8;

CREATE TABLE utilisateur
(
    num_util       INT AUTO_INCREMENT PRIMARY KEY,
    nom_util       CHAR(20)      NULL,
    prenom_util    CHAR(20)      NULL,
    date_naissance DATE          NULL,
    taille         DECIMAL(6, 2) NULL,
    poids          DECIMAL(6, 2) NULL,
    login          VARCHAR(255)  NULL,
    password       VARCHAR(255)  NULL,
    CONSTRAINT UK_18vwp4resqussqmlpqnymfqxk UNIQUE (login)
) CHARSET = utf8;

CREATE TABLE sortie
(
    num_sortie         INT AUTO_INCREMENT PRIMARY KEY,
    num_util           INT           NOT NULL,
    date_sortie        DATE          NULL,
    heure_depart       TIME          NULL,
    heure_arrivee      TIME          NULL,
    lieu_depart        CHAR(30)      NULL,
    distance_parcourue DECIMAL(6, 2) NULL,
    CONSTRAINT sortie_ibfk_1 FOREIGN KEY (num_util) REFERENCES utilisateur (num_util)
) CHARSET = utf8;

CREATE TABLE etape
(
    id_etape                      INT AUTO_INCREMENT PRIMARY KEY,
    num_etape                     INT             NOT NULL,
    num_sortie                    INT             NOT NULL,
    latitude                      DECIMAL(16, 14) NULL,
    longitude                     DECIMAL(16, 14) NULL,
    distance_from_previous        DOUBLE          NULL,
    heure_etape                   TIME            NULL,
    vitesse_moyenne_from_previous DOUBLE          NULL,
    CONSTRAINT num_etape UNIQUE (num_etape, num_sortie),
    CONSTRAINT etape_ibfk_1 FOREIGN KEY (num_sortie) REFERENCES sortie (num_sortie)
) CHARSET = utf8;

CREATE INDEX num_sortie ON etape (num_sortie);

CREATE INDEX num_util ON sortie (num_util);



INSERT INTO byke.hibernate_sequence (next_val)
VALUES (60);


INSERT INTO byke.utilisateur (num_util, nom_util, prenom_util, date_naissance, taille, poids, login, password)
VALUES (1, 'GALLOIS', 'Jean', '1964-04-23', 183.00, 75.00, NULL, NULL),
       (2, 'VILLE', 'Pierre', '1962-04-12', 173.00, 69.00, NULL, NULL),
       (3, 'Audu', 'Bastien', '2000-09-24', 150.00, 90.00, 'baudu',
        '$2a$10$Y.qxXEL808VvG7CCcGz2/eyigzX8M8sw3WWTAknxCIV6ImH6B1b96');


INSERT INTO byke.sortie (num_sortie, num_util, date_sortie, heure_depart, heure_arrivee, lieu_depart,
                         distance_parcourue)
VALUES (1, 3, '2022-09-30', '08:15:00', '12:20:00', 'SAINT GENIS LES OLLLIERES', 86.20),
       (2, 3, '2022-09-23', '08:15:00', '12:45:00', 'SAINT GENIS LES OLLLIERES', 88.10),
       (57, 3, '2023-01-11', '16:32:19', '16:33:04', '16h', 0.00);


INSERT INTO byke.etape (id_etape, num_etape, num_sortie, latitude, longitude, distance_from_previous, heure_etape,
                        vitesse_moyenne_from_previous)
VALUES (1, 1, 1, 45.66052627560000, 4.63221406937000, NULL, NULL, NULL),
       (2, 1, 2, 45.76320538304853, 4.62908812807553, NULL, NULL, NULL),
       (3, 2, 1, 45.63100000000000, 4.60100000000000, NULL, NULL, NULL),
       (4, 2, 2, 45.74772421385712, 4.61158104883301, NULL, NULL, NULL),
       (5, 3, 1, 45.66000000000000, 4.55900000000000, NULL, NULL, NULL),
       (6, 3, 2, 45.68635971090647, 4.52697130259405, NULL, NULL, NULL),
       (7, 4, 1, 45.39582000000000, 4.56574300000000, NULL, NULL, NULL),
       (8, 4, 2, 45.68026051032419, 4.48057381147755, NULL, NULL, NULL),
       (9, 5, 1, 45.62861466155582, 4.52521573815698, NULL, NULL, NULL),
       (10, 5, 2, 45.66182791270027, 4.42396251904246, NULL, NULL, NULL),
       (11, 6, 1, 45.65620857484888, 4.55312020725788, NULL, NULL, NULL),
       (12, 6, 2, 45.70569896294748, 4.47046508170943, NULL, NULL, NULL),
       (13, 7, 1, 45.68594298751449, 4.57593439831060, NULL, NULL, NULL),
       (14, 7, 2, 45.70802393301300, 4.52539125664541, NULL, NULL, NULL),
       (15, 8, 1, 45.70682093090597, 4.55467737970977, NULL, NULL, NULL),
       (16, 8, 2, 45.72126840968257, 4.56804910562751, NULL, NULL, NULL),
       (17, 9, 1, 45.74772421385712, 4.61158104883301, NULL, NULL, NULL),
       (18, 9, 2, 45.72491361448375, 4.59929470994397, NULL, NULL, NULL),
       (19, 10, 1, 45.76320538304853, 4.62908812807553, NULL, NULL, NULL),
       (20, 10, 2, 45.74772421385712, 4.61158104883301, NULL, NULL, NULL),
       (21, 11, 1, 45.76592776077334, 4.66072522354320, NULL, NULL, NULL),
       (22, 11, 2, 45.76320538304853, 4.62908812807553, NULL, NULL, NULL),
       (23, 12, 1, 45.77646635536404, 4.69614559515543, NULL, NULL, NULL),
       (24, 12, 2, 45.76592776077334, 4.66072522354320, NULL, NULL, NULL),
       (25, 13, 1, 45.75796018172125, 4.72596372582408, NULL, NULL, NULL),
       (26, 13, 2, 45.77646635536404, 4.69614559515543, NULL, NULL, NULL),
       (27, 14, 2, 45.75796018172125, 4.72596372582408, NULL, NULL, NULL),
       (58, 1, 57, 45.63625300000000, 4.83433000000000, 0, '16:32:19', NULL),
       (59, 2, 57, 45.63615800000000, 4.83443600000000, 0, '16:33:04', 0);
