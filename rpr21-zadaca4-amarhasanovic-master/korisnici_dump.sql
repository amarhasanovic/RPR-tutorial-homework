BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnik" (
	"id"	INTEGER,
	"ime"	TEXT,
	"prezime"	TEXT,
	"email"	TEXT,
	"username"	TEXT,
	"password"	TEXT,
	"slika" TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "korisnik" VALUES (1,'Vedran','Ljubović','vljubovic@etf.unsa.ba','vedranlj','test', 'resources/pictures/face-smile.png');
INSERT INTO "korisnik" VALUES (2,'Amra','Delić','adelic@etf.unsa.ba','amrad','test', 'resources/pictures/face-smile.png');
INSERT INTO "korisnik" VALUES (3,'Tarik','Sijerčić','tsijercic1@etf.unsa.ba','tariks','test', 'resources/pictures/face-smile.png');
INSERT INTO "korisnik" VALUES (4,'Rijad','Fejzić','rfejzic1@etf.unsa.ba','rijadf','test', 'resources/pictures/face-smile.png');
COMMIT;
