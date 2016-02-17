--
-- Fichier g�n�r� par SQLiteStudio v3.0.5sur mer. f�vr. 10 15:12:45 2016
--
-- Encodage texte utilis�: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Address
CREATE TABLE Address (IdAddress INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, IdUser INTEGER NOT NULL, Address1 VARCHAR (50), Address2 VARCHAR (50), postalCode VARCHAR (5), City VARCHAR (80), Latitude BIGINT, Longitude BIGINT, TypeAddress VARCHAR (10), Gcoord VARCHAR (100000), CONSTRAINT FK_USER_ADDRESS FOREIGN KEY (IdUser) REFERENCES "sqlitestudio_temp_table" (IdUser));

-- Table: Ride
CREATE TABLE Ride (IdRide INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, IdUser INTEGER CONSTRAINT FK_Ride_User REFERENCES User (IdUser) NOT NULL, IdUserOnRide INTEGER NOT NULL UNIQUE, Distance DOUBLE);

-- Table: User
CREATE TABLE User (IdUser INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, LastName VARCHAR (40) NOT NULL, FirstName VARCHAR (40) NOT NULL, Pseudo VARCHAR (50) NOT NULL, Email VARCHAR (80) UNIQUE NOT NULL, Password VARCHAR (30) NOT NULL, Sex VARCHAR (10), Smoker BOOLEAN, Telephone VARCHAR (20), ConnectedUser BOOLEAN UNIQUE ON CONFLICT ABORT);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
