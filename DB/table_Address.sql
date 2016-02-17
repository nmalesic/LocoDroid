--
-- Fichier généré par SQLiteStudio v3.0.5sur mer. févr. 17 11:26:02 2016
--
-- Encodage texte utilisé: windows-1252
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Address
CREATE TABLE Address (
    IdAddress      INTEGER        PRIMARY KEY AUTOINCREMENT
                                  NOT NULL,
    IdUser         INTEGER        NOT NULL
                                  REFERENCES User (IdUser),
    Address1       VARCHAR (50),
    Address2       VARCHAR (50),
    postalCode     VARCHAR (5),
    City           VARCHAR (80),
    Latitude       VARCHAR (20),
    Longitude      VARCHAR (20),
    TypeAddress    VARCHAR (10),
    OneLineAddress VARCHAR (1000),
    CONSTRAINT FK_USER_ADDRESS FOREIGN KEY (
        IdUser
    )
    REFERENCES User (IdUser) 
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
