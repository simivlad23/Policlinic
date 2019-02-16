

-- -----------------------------------------------------
-- Schema LantPoliclinici
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema LantPoliclinici
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS LantPoliclinici;
USE LantPoliclinici ;

-- -----------------------------------------------------
-- Table `LantPoliclinici`.`specializare`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS specializare (
  idSpecializare INT(11) NOT NULL AUTO_INCREMENT,
  Denumire VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (idSpecializare));


-- -----------------------------------------------------
-- Table `LantPoliclinici`.`unitate_medicala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS unitate_medicala (
  idUnitate_Medicala INT(11) NOT NULL AUTO_INCREMENT,
  denumire VARCHAR(45) NULL DEFAULT NULL,
  adresa VARCHAR(45) NULL DEFAULT NULL,
  descriere_Servicii VARCHAR(45) NULL DEFAULT NULL,
  Program_functionare VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (idUnitate_Medicala));

-- -----------------------------------------------------
-- Table `LantPoliclinici`.`Functie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Functie (
  idFunctie INT NOT NULL AUTO_INCREMENT,
  Nume_functie VARCHAR(45) NULL,
  PRIMARY KEY (idFunctie));



-- -----------------------------------------------------
-- Table `LantPoliclinici`.`utilizator_angajat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS utilizator_angajat (
  CNP VARCHAR(14) NOT NULL,
  unitate_medicala_idUnitate_Medicala INT(11) NOT NULL,
  Functie_idFunctie INT NOT NULL,
  username VARCHAR(45) NOT NULL,
  parola VARCHAR(45) NOT NULL,
  Nume VARCHAR(45) NULL DEFAULT NULL,
  Prenume VARCHAR(45) NULL DEFAULT NULL,
  Adresa VARCHAR(45) NULL DEFAULT NULL,
  Numar_de_telefon VARCHAR(45) NULL DEFAULT NULL,
  Email VARCHAR(45) NULL DEFAULT NULL,
  Cont_IBAN VARCHAR(45) NULL DEFAULT NULL,
  Data_angajarii DATE NULL DEFAULT NULL,
  salariu_negociat INT NULL,
  numar_ore INT NULL,
  PRIMARY KEY (CNP),
    FOREIGN KEY (unitate_medicala_idUnitate_Medicala) REFERENCES LantPoliclinici.unitate_medicala (idUnitate_Medicala),
    FOREIGN KEY (Functie_idFunctie) REFERENCES LantPoliclinici.Functie (idFunctie)
   );



-- -----------------------------------------------------
-- Table `LantPoliclinici`.`medic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS medic (
  utilizator_angajat_CNP VARCHAR(14) NOT NULL,
  Specializare_idSpecializare INT(11) NOT NULL,
  gradul VARCHAR(45) NULL DEFAULT NULL,
  procent_servicii INT(11) NULL DEFAULT NULL,
  titlu_stiintific VARCHAR(45) NULL DEFAULT NULL,
  postul_didactic VARCHAR(45) NULL DEFAULT NULL,
  
  PRIMARY KEY (utilizator_angajat_CNP),
    FOREIGN KEY (Specializare_idSpecializare) REFERENCES LantPoliclinici.specializare (idSpecializare),
    FOREIGN KEY (utilizator_angajat_CNP) REFERENCES LantPoliclinici.utilizator_angajat (CNP)
);


-- -----------------------------------------------------
-- Table `LantPoliclinici`.`pacient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pacient (
  CNP VARCHAR(14) NOT NULL,
  Nume VARCHAR(45) NULL DEFAULT NULL,
  Prenume VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (CNP)
  );


-- -----------------------------------------------------
-- Table `LantPoliclinici`.`serviciu_medical`
-- ---------------------------------------------------- 
CREATE TABLE IF NOT EXISTS serviciu_medical (
  id_Serviciu_Medical INT(11) NOT NULL AUTO_INCREMENT,
  Unitate_Medicala_idUnitate_Medicala INT(11) NOT NULL,
  specializare_idSpecializare INT(11) NOT NULL,
  Nume_serviciu VARCHAR(45) NULL,
  pret INT(11) NULL DEFAULT NULL,
  durata INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (id_Serviciu_Medical),
    FOREIGN KEY (Unitate_Medicala_idUnitate_Medicala) REFERENCES LantPoliclinici.unitate_medicala (idUnitate_Medicala),
    FOREIGN KEY (specializare_idSpecializare) REFERENCES LantPoliclinici.specializare (idSpecializare)
   );

-- -----------------------------------------------------
-- Table  `Asistent_medical`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Asistent_medical (
  utilizator_angajat_CNP VARCHAR(14) NOT NULL,
  tip_asistent VARCHAR(45) NULL,
  grad_asistent VARCHAR(45) NULL,
  PRIMARY KEY (utilizator_angajat_CNP),
    FOREIGN KEY (utilizator_angajat_CNP) REFERENCES LantPoliclinici.utilizator_angajat (CNP)
    );

-- -----------------------------------------------------
-- Table `LantPoliclinici`.`programare`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS programare (
  id_Programare INT(11)  NOT NULL auto_increment,
  Pacient_CNP VARCHAR(14) NOT NULL,
  medic_utilizator_angajat_CNP VARCHAR(14) NOT NULL,
  id_serviciu int(11),
  data_programare DATETIME NOT NULL,
  PRIMARY KEY (id_Programare),
	FOREIGN KEY (id_serviciu) references LantPoliclinici.serviciu_medical(id_Serviciu_Medical),
    FOREIGN KEY (Pacient_CNP) REFERENCES LantPoliclinici.pacient (CNP),
    FOREIGN KEY (medic_utilizator_angajat_CNP) REFERENCES LantPoliclinici.medic (utilizator_angajat_CNP)
    );


CREATE TABLE IF NOT EXISTS consultatie (
programare_id_Programare int(11) not null,
Simtome varchar(45) null default null,
Investigatii varchar(45) null default null,
Diagnostic varchar(45) null default null,
Tratament varchar (45) null default null,

PRIMARY KEY (programare_id_Programare),
FOREIGN KEY (programare_id_Programare) references programare (id_Programare)
);


