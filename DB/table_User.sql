--
-- Fichier généré par SQLiteStudio v3.0.5sur mer. févr. 10 12:03:11 2016
--
-- Encodage texte utilisé: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Utilisateur
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (IdUtilisateur INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nom VARCHAR (40) NOT NULL, Prenom VARCHAR (40) NOT NULL, Pseudo VARCHAR (50) NOT NULL, Email VARCHAR (80) UNIQUE NOT NULL, Password VARCHAR (30) NOT NULL, Sexe VARCHAR (10), Fumeur BOOLEAN, Telephone VARCHAR (20));

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
