BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "drzava" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"glavni_grad"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("glavni_grad") REFERENCES "grad"("id")
);
CREATE TABLE IF NOT EXISTS "grad" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"broj_stanovnika"	INTEGER,
	"drzava"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("drzava") REFERENCES "drzava"("id")
);

INSERT INTO grad VALUES (1, 'Pariz', 2100000, 1);
INSERT INTO drzava VALUES (1, 'Francuska', 1);
INSERT INTO grad VALUES (2, 'London', 7556900, 2);
INSERT INTO drzava VALUES (2, 'Engleska', 2);
INSERT INTO grad VALUES (3, 'Beč', 1800000, 3);
INSERT INTO drzava VALUES (3, 'Austrija', 3);
INSERT INTO grad VALUES (4, 'Manchester', 520000, 2);
INSERT INTO grad VALUES (5, 'Graz', 280200, 3);

COMMIT;
