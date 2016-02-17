--
-- Fichier g�n�r� par SQLiteStudio v3.0.5sur mer. f�vr. 17 11:28:59 2016
--
-- Encodage texte utilis�: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Ride
CREATE TABLE Ride (
    IdRide       INTEGER PRIMARY KEY AUTOINCREMENT
                         UNIQUE
                         NOT NULL,
    IdUser       INTEGER CONSTRAINT FK_Ride_User REFERENCES User (IdUser) 
                         NOT NULL,
    IdUserOnRide INTEGER NOT NULL
                         UNIQUE,
    Distance     DOUBLE
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
