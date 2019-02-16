##########################################################################################################
################  INTODUCERE IN TABELA FUNCIE ############################################################
##########################################################################################################
INSERT INTO functie (Nume_Functie) VALUES
('Inspector resurse umane'),
('Expert financiar-contabil'),
('Receptioner'),
('Asistent medical'),
('Medic');
##########################################################################################################

##########################################################################################################
################  INTODUCERE IN TABELA  UNITATE_MEDICALA #################################################
##########################################################################################################
INSERT INTO unitate_medicala (denumire, adresa, descriere_Servicii, Program_functionare) VALUES
('Sfanta Maria','Baia Mare','de toate pt toti','non-stop'),
('Regina Maria','Cluj-Napoca','de toate pt toti','non-stop'),
('EuroMedica','Cluj-Napoca','de toate pt toti','non-stop'),
('MedStar','Cluj-Napoca','de toate pt toti','non-stop'),
('MedLife','Cluj-Napoca','de toate pt toti','non-stop');
##########################################################################################################


##########################################################################################################
################  INTODUCERE IN TABELA  SPECIALIZARE     #################################################
##########################################################################################################
INSERT INTO  specializare (Denumire) VALUES
('Chirurgie'),
('Oftalmologie'),
('Ortopedie-Traumatologie'),
('ORL'),
('Urologie'),
('Medicina interna'),
('Cardiologie'),
('Neurologie'),
('Psihatrie'),
('Boli infectioase');
##########################################################################################################


##########################################################################################################
################  INTODUCERE IN TABELA  Serviciu Medical     #############################################
##########################################################################################################
##########################################################################################################
INSERT INTO  serviciu_medical (Unitate_Medicala_idUnitate_Medicala, specializare_idSpecializare, Nume_serviciu, pret, durata) VALUES
(1, 1, 'Operatie de apenticita', 500, 60),
(2, 2, 'Transplant de cornee', 2000, 120),
(5, 3, 'Operatie menisc', 1000, 60),
(4, 4, 'Rinoplastie', 50, 30),
(1, 5, 'Operatie de prostata', 3000, 240),
(3, 1, 'Operatie de hernie', 600, 60),
(2, 1, 'Operatie de laringita', 500, 60),
(3, 1, 'Operatie de exostoza', 700, 60),
(4, 1, 'Operatie plamani', 1000, 45);
##########################################################################################################

##########################################################################################################
################  INTODUCERE IN TABELA  Pacient              #############################################
##########################################################################################################
INSERT INTO  pacient (CNP,Nume,Prenume) VALUES
('1970316125126', 'Maris', 'Catalin'),
('1970517125136', 'Petrus', 'Diana'),
('1970718125146', 'Petrus', 'Lavinia'),
('1970919125156', 'Coman', 'Vasile'),
('1971120125166', 'Coman', 'Florin');

##########################################################################################################

##########################################################################################################
################  INTODUCERE IN TABELA  Utilizator Angajat   #############################################
##########################################################################################################
INSERT INTO utilizator_angajat (CNP, unitate_medicala_idUnitate_Medicala, Functie_idFunctie, username, parola, Nume, Prenume, Adresa, Numar_de_telefon, Email, Cont_IBAN, Data_angajarii, salariu_negociat, numar_ore) VALUES 
('1970101125111', 1, 1, 'simion', 'simion', 'Vlad', 'Simion', 'Maramures 69', '0769696969', 'simion@gmail.com', '566235432', now(), 12000, 40),
('1970202125112', 4, 2, 'darius', 'darius', 'Corujan', 'Darius', 'Cluj 69', '0769696969', 'darius@gmail.com', '566235432', now(), 8000, 40),
('1970303125113', 3, 3, 'daniel', 'daniel', 'Todea', 'Daniel', 'Rasca 69', '0769696969', 'daniel@gmail.com', '566235432', now(), 8000, 40),
('1970404125114', 5, 4, 'mihai', 'mihai', 'Neamt', 'Mihai', 'Gilau 69', '0769696969', 'mihai@gmail.com', '566235432', now(), 12001, 40),
('1970505125115', 2, 5, 'ioana', 'ioana', 'Lobont', 'Ioana', 'Gherla 69', '0769696969', 'ioana@gmail.com', '566235432', now(), 16000, 40),
('1970606125116', 1, 5, 'lenuta', 'lenuta', 'Vlad', 'Lenuta', 'Maramures 69', '0769696969', 'lenuta@gmail.com', '566235432', now(), 16000, 40);

##########################################################################################################

##########################################################################################################
################  INTODUCERE IN TABELA  Medic                #############################################
##########################################################################################################
INSERT INTO medic (utilizator_angajat_CNP, Specializare_idSpecializare, gradul, procent_servicii, titlu_stiintific, postul_didactic) VALUES
('1970505125115', 1, 'primar', '10', 'ceava', 'profesor'),
('1970606125116', 3, 'specialist', '4', 'ceava', 'lector');


##########################################################################################################
################  INTODUCERE IN TABELA  Asistent_medical     #############################################
##########################################################################################################
INSERT INTO Asistent_medical(utilizator_angajat_CNP, tip_asistent, grad_asistent) VALUES
('1970404125114', 'generalist', 'principal');


INSERT INTO programare (Pacient_CNP, medic_utilizator_angajat_CNP, id_serviciu, data_programare) VALUES 
('1970316125126', '1970505125115', 1, '2018-02-12 10:00:00'),
('1970517125136', '1970606125116', 3, '2018-03-12 09:00:00'),
('1970718125146', '1970505125115', 1, '2018-01-22 12:00:00'),
('1970919125156', '1970606125116', 3, '2018-01-23 11:00:00'),
('1971120125166', '1970505125115', 1, '2018-01-25 14:30:00');


INSERT INTO consultatie (programare_id_Programare, Simtome, Investigatii, Diagnostic, Tratament) VALUES
(1, 'dureti de burta', 'analize de sange', 'toxiinfectie alimentara', 'sanax, repaus la pat'),
(2, 'dureti de cap', 'tomografie','meningita', 'antibiotic ,repaus la pat'),
(3, 'duretii abdominale', 'consultatie medic', 'apenticita', 'antiinflamator,antibiotic ,repaus la pat'),
(4, 'dureti de burta', 'analize de sange', 'toxiinfectie alimentara', 'sanax ,repaus la pat'),
(5,' dureti de burta','analize de sange', 'toxiinfectie alimentara', 'sanax ,repaus la pat');


















