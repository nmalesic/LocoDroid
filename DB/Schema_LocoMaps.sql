--
-- Fichier g�n�r� par SQLiteStudio v3.0.5sur mer. f�vr. 10 12:04:50 2016
--
-- Encodage texte utilis�: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Adresse
DROP TABLE IF EXISTS Address;
CREATE TABLE Address (IdAddress INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, IdUser INTEGER NOT NULL, Address1 VARCHAR (50), Address2 VARCHAR (50), postalCode VARCHAR (5), City VARCHAR (80), Latitude BIGINT, Longitude BIGINT, TypeAddress VARCHAR (10), Gcoord VARCHAR (100000), CONSTRAINT FK_USER_ADDRESS FOREIGN KEY (IdUser) REFERENCES USER (IdUser));

-- Table: Utilisateur
DROP TABLE IF EXISTS User;
CREATE TABLE User (Idser INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, firstName VARCHAR (40) NOT NULL, lastName VARCHAR (40) NOT NULL, Pseudo VARCHAR (50) NOT NULL, eMail VARCHAR (80) UNIQUE NOT NULL, Password VARCHAR (30) NOT NULL, Sexe VARCHAR (10), Fumeur BOOLEAN, phone VARCHAR (20));

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
