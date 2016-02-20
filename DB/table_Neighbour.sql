--
-- Fichier généré par SQLiteStudio v3.0.5sur ven. févr. 19 21:47:47 2016
--
-- Encodage texte utilisé: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Neighbour
CREATE TABLE Neighbour (IdRide INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, IdUser INTEGER CONSTRAINT FK_Ride_User REFERENCES User (IdUser) NOT NULL, IdUserNeighbour INTEGER UNIQUE, Distance DOUBLE);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
