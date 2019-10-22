	DROP DATABASE IF EXISTS lufthavn;
    CREATE DATABASE lufthavn;
    USE lufthavn;

CREATE TABLE Standpladser (
  standplads_id int(10) NOT NULL AUTO_INCREMENT, 
  terminal_id   int(10) NOT NULL, 
  PRIMARY KEY (standplads_id));
CREATE TABLE Terminaler (
  terminal_id int(10) NOT NULL AUTO_INCREMENT, 
  PRIMARY KEY (terminal_id));
CREATE TABLE Fly (
  fly_id           int(10) NOT NULL AUTO_INCREMENT, 
  model            varchar(100), 
  capacitet        int(10), 
  hastighed        int(10), 
  landingsplads_id int(10) NOT NULL, 
  størrelse        varchar(50), 
  PRIMARY KEY (fly_id));
CREATE TABLE Destinationer (
  dest_id int(10) NOT NULL AUTO_INCREMENT, 
  navn    varchar(50), 
  land    varchar(50), 
  afstand int(10), 
  PRIMARY KEY (dest_id));
CREATE TABLE Opgavetyper (
  opgavetype_id int(10) NOT NULL AUTO_INCREMENT, 
  type          varchar(50), 
  PRIMARY KEY (opgavetype_id));
CREATE TABLE Opgave (
  opgave_id       int(10) NOT NULL AUTO_INCREMENT, 
  opgavetype_id   int(10) NOT NULL, 
  arbejdsminutter int(10), 
  PRIMARY KEY (opgave_id));
CREATE TABLE Flyopgaver (
  fly_id    int(10) NOT NULL, 
  opgave_id int(10) NOT NULL, 
  hold_id   int(10), 
  done      binary(255));
CREATE TABLE Ankomster (
  ankomst_id       int(10) NOT NULL AUTO_INCREMENT, 
  fra_dest_id      int(10) NOT NULL, 
  landingstidpunkt varchar(50), 
  fly_id           int(10) NOT NULL, 
  PRIMARY KEY (ankomst_id));
CREATE TABLE Adgange (
  afgange_id       int(10) NOT NULL AUTO_INCREMENT, 
  til_dest_id      int(10) NOT NULL, 
  landingstidpunkt varchar(50), 
  fly_id           int(10) NOT NULL, 
  PRIMARY KEY (afgange_id));
CREATE TABLE Personale (
  hold_id       int(10) NOT NULL AUTO_INCREMENT, 
  opgavetype_id int(10) NOT NULL, 
  standplads_id int(10), 
  PRIMARY KEY (hold_id));
CREATE TABLE Personale_login (
  login_id   int(10) NOT NULL AUTO_INCREMENT, 
  brugernavn varchar(50), 
  kodeord    varchar(50), 
  `e-mail`   varchar(100), 
  hold_id    int(10) NOT NULL, 
  PRIMARY KEY (login_id));
CREATE TABLE Standplads_nabo (
  standpladsA_id int(10) NOT NULL, 
  standpladsB_id int(10) NOT NULL);

ALTER TABLE Standpladser ADD CONSTRAINT FKStandplads320695 FOREIGN KEY (terminal_id) REFERENCES Terminaler (terminal_id);
ALTER TABLE Flyopgaver ADD CONSTRAINT FKFlyopgaver936654 FOREIGN KEY (opgave_id) REFERENCES Opgave (opgave_id);
ALTER TABLE Adgange ADD CONSTRAINT FKAdgange855821 FOREIGN KEY (til_dest_id) REFERENCES Destinationer (dest_id);
ALTER TABLE Opgave ADD CONSTRAINT FKOpgave451579 FOREIGN KEY (opgavetype_id) REFERENCES Opgavetyper (opgavetype_id);
ALTER TABLE Ankomster ADD CONSTRAINT FKAnkomster226332 FOREIGN KEY (fra_dest_id) REFERENCES Destinationer (dest_id);
ALTER TABLE Personale_login ADD CONSTRAINT FKPersonale_634738 FOREIGN KEY (hold_id) REFERENCES Personale (hold_id);
ALTER TABLE Standplads_nabo ADD CONSTRAINT FKStandplads921931 FOREIGN KEY (standpladsA_id) REFERENCES Standpladser (standplads_id);
ALTER TABLE Standplads_nabo ADD CONSTRAINT FKStandplads892140 FOREIGN KEY (standpladsB_id) REFERENCES Standpladser (standplads_id);
ALTER TABLE Fly ADD CONSTRAINT FKFly722180 FOREIGN KEY (landingsplads_id) REFERENCES Standpladser (standplads_id);
ALTER TABLE Personale ADD CONSTRAINT FKPersonale306463 FOREIGN KEY (opgavetype_id) REFERENCES Opgavetyper (opgavetype_id);
ALTER TABLE Ankomster ADD CONSTRAINT FKAnkomster800644 FOREIGN KEY (fly_id) REFERENCES Fly (fly_id);
ALTER TABLE Adgange ADD CONSTRAINT FKAdgange406199 FOREIGN KEY (fly_id) REFERENCES Fly (fly_id);
ALTER TABLE Flyopgaver ADD CONSTRAINT FKFlyopgaver122714 FOREIGN KEY (fly_id) REFERENCES Fly (fly_id);
