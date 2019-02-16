
-- -----------------------------------------------------
-- Procedura adaugare Utilizator_Angajat
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ADAUGARE_Utilizator_Angajat (inCNP VARCHAR(14), inUnitate_medicala_idUnitate_Medicala int, inFunctie_idFunctie int, inUsername varchar(45),inParola varchar(45), inNume varchar(45), inPrenume varchar(45), inAdresa varchar(45), inNumar_de_telefon varchar(45),inEmail varchar(45),inCont_IBAN varchar(45),inData_angajarii date,inSalariu_negociat int, inNumar_ore int)
BEGIN
     # verificam daca exista unitaea respectiva 
     SET @exista =NULL;
     SELECT @exista := unitate_medicala.idUnitate_Medicala 
     FROM unitate_medicala
     where unitate_medicala.idUnitate_Medicala  =inUnitate_medicala_idUnitate_Medicala;
     
     if @exista is not null then
        # daca exisat unitatea medicala putem insera insera
       
			INSERT INTO utilizator_angajat (CNP, unitate_medicala_idUnitate_Medicala, Functie_idFunctie, username,parola, Nume, Prenume, Adresa, Numar_de_telefon,Email,Cont_IBAN,Data_angajarii,salariu_negociat, numar_ore) VALUES 
			(inCNP , inUnitate_medicala_idUnitate_Medicala , inFunctie_idFunctie , inUsername , inParola , inNume , inPrenume , inAdresa , inNumar_de_telefon ,inEmail ,inCont_IBAN ,inData_angajarii ,inSalariu_negociat , inNumar_ore );
			ELSE 
				SELECT CONCAT('Unitata Medicala cu id: ',inUnitate_medicala_idUnitate_Medicala,' nu exista') as rezultat;
			END IF;


end //
DELIMITER ;


-- -----------------------------------------------------
-- Procedura adaugare Medic
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ADAUGARE_MEDIC(inCNP varchar(14),inSpecializare int(11),inGrad varchar(45), inProcent int(11),inTitlu varchar(45),inPost_Didactic varchar(45))
BEGIN
     # Verificam daca exista un utilizatora_angajat cu acel cnp
     SET @exista =NULL;
     SELECT @exista := utilizator_angajat.CNP 
     FROM utilizator_angajat
     where utilizator_angajat.CNP=inCNP;
     
     if @exista is not null then
        # daca este deja inregistrat in tabela utilizator_angajat testam daca exista specializarea respectiva
        SET @exista_specializare =NULL;
		SELECT @exista_specializare := specializare.idSpecializare 
		FROM specializare
		where  specializare.idSpecializare=inSpecializare;
			if @exista_specializare is not null then
			#daca exista specializarea putem insera tabela medic
		
					INSERT INTO medic ( utilizator_angajat_CNP, Specializare_idSpecializare,gradul,procent_servicii,titlu_stiintific,postul_didactic) VALUES 
					( inCNP, inSpecializare, inGrad,inProcent,inTitlu,inPost_Didactic);
			ELSE 
            	SELECT CONCAT('Specializarea cu id:',inSpecializare,' nu exista') as rezultat;
			END IF;
            
            
            else
			SELECT CONCAT('Prima data trebuie inserat in tabela utilizator_angajat si apoi in medic') as rezultat;
		END IF;


end //
DELIMITER ;


-- -----------------------------------------------------
-- Procedura adaugare Asistent Medical
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ADAUGARE_ASISTENT(inCNP varchar(14),inTip varchar(45),inGrad varchar(45))
BEGIN
     # Verificam daca exista un utilizatora_angajat cu acel cnp
     SET @exista =NULL;
     SELECT @exista := utilizator_angajat.CNP 
     FROM utilizator_angajat
     where utilizator_angajat.CNP=inCNP;
     
     if @exista is not null then
        # daca este deja inregistrat in tabela utilizator_angajat putem insera
        # daca nu prima data trebuie inregistrat ca angajat si apoi ca medic
		
					INSERT INTO Asistent_medical(utilizator_angajat_CNP, tip_asistent, grad_asistent) VALUES
					(inCNP,inTip ,inGrad );
		 ELSE 
			 SELECT CONCAT('Prima data trebuie inserat in tabela utilizator_angajat si apoi in medic') as rezultat;
	  END IF;


end //
DELIMITER ;


-- -----------------------------------------------------
-- Procedura adaugare Pacient
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ADAUGARE_PACIENT(inCNP varchar(14),inNume varchar(45),inPrenume varchar(45))
BEGIN
     # Verificam daca mai exisat pacient cu acelasi CNP
     SET @exista =NULL;
     SELECT @exista := pacient.CNP 
     FROM pacient
     where Pacient.CNP=inCNP;
     
     if @exista is null then
        # daca exista este tot null atunci inseream
		
				INSERT INTO pacient ( CNP, Nume,Prenume) VALUES 
				( inCNP, inNume, inPrenume);
			ELSE 
				SELECT CONCAT('Pacientul cu id: ',inCNP,' exista deja in baza de date') as rezultat;
			END IF;


end //
DELIMITER ;

-- -----------------------------------------------------
-- Procedura adaugare Programare
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ADAUGARE_PROGRAMARE (inPacient_CNP varchar(14), inMedic_utilizator_angajat_CNP varchar(14), inServiciu int, indata_programare datetime)
BEGIN
      # Inseram doar daca exista medic cu acel id si paciant cu acel id
     SET @exista =NULL;
     SET @exista_med =NULL;
     SET @exista_serv =NULL;
	 SET @corespunde =NULL;
     SET @corespunde2 =null;
      
     SELECT @exista := pacient.CNP 
     FROM pacient
     where Pacient.CNP=inPacient_CNP;
     
     if @exista is not null then
        # verificam daca exista medicul respectiv
        set @exista_med = null;
        
        SELECT @exista_med := Medic.utilizator_angajat_CNP 
        FROM Medic
        WHERE Medic.utilizator_angajat_CNP =inMedic_utilizator_angajat_CNP;
		
			IF @exista_med IS NOT NULL THEN
            
			# verificam daca serviciul respectiv 
            SELECT @exista_serv := serviciu_medical.id_Serviciu_Medical
			FROM serviciu_medical
			WHERE serviciu_medical.id_Serviciu_Medical =inServiciu;
				IF @exista_serv IS NOT NULL THEN
                 # verificam daca medicul respectv poate oferi acel serviciu
                 SELECT @corespunde := serviciu_medical.specializare_idSpecializare
				 FROM serviciu_medical
				 WHERE serviciu_medical.id_Serviciu_Medical=inServiciu;
				
                SELECT @corespunde2 := medic.Specializare_idSpecializare
				 FROM medic
				 WHERE medic.utilizator_angajat_CNP=inMedic_utilizator_angajat_CNP;
                 if (@corespunde = @corespunde2) then
                 INSERT INTO programare ( Pacient_CNP, medic_utilizator_angajat_CNP,id_serviciu,data_programare) VALUES 
				( inPacient_CNP, inMedic_utilizator_angajat_CNP, inServiciu,indata_programare);
                 else
                 SELECT CONCAT('Medicul nu poate face acest serviciu medical') as rezultat;
                 end if;
             else
             SELECT CONCAT('Servicul medical nu exista') as rezultat;
			end if;
				
			ELSE 
				SELECT CONCAT('Medicul cu id: ',inMedic_utilizator_angajat_CNP,' nu exista') as rezultat;
			END IF;

	    ELSE 
        SELECT CONCAT('Pacientul cu id: ',inPacient_CNP,' nu exista ') as rezultat;
     end if;
end //
DELIMITER ;


-- -----------------------------------------------------
-- Procedura adaugare Consultatie
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ADAUGARE_CONSULTATIE(inProgramare_id int,inSimtome varchar(45),inInvestigatii varchar(45),inDiagnostic varchar(45),inTratanment varchar(45))
BEGIN
     # verificam daca exisat o programare pt consultatie
     SET @exista =NULL;
     SELECT @exista := programare.id_Programare 
     FROM programare
     where programare.id_Programare = inProgramare_id;
     
     
     if @exista is not null then
        # daca exisat programare atunci putem face nserarea in tabela consultatie
       
		
		INSERT INTO consultatie (programare_id_Programare,Simtome,Investigatii,Diagnostic,Tratament)  VALUES 
				( inProgramare_id, inSimtome,inInvestigatii,inDiagnostic,inTratanment);
			ELSE 
				SELECT CONCAT('Nu se poate face o consultatie deorece nu este o programare valida') as rezultat;
			END IF;


end //
DELIMITER ;

-- -----------------------------------------------------
-- Procedura Istoric Medical
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE ISTORIC_PACINT(inCNP varchar(45))
BEGIN
     # verificam daca exisata pacient cu cnp-ul respectiv
     SET @exista =NULL;
     SELECT @exista := pacient.CNP 
     FROM pacient
     where pacient.CNP =inCNP;
     
     if @exista is not null then
		
        SELECT pacient.CNP,pacient.Nume,pacient.Prenume,programare.data_programare,utilizator_angajat.Nume,utilizator_angajat.Prenume,consultatie.diagnostic
        FROM pacient,programare,utilizator_angajat,medic,consultatie
		WHERE  
             utilizator_angajat.CNP=medic.utilizator_angajat_CNP
            and medic.utilizator_angajat_CNP=programare.medic_utilizator_angajat_CNP
            and consultatie.programare_id_Programare=programare.id_Programare
            and programare.Pacient_CNP=pacient.CNP
            and pacient.CNP=inCNP;
            
		#SELECT pacient.CNP,pacient.Nume,pacient.Prenume,programare.data_programare,utilizator_angajat.Nume,utilizator_angajat.Prenume,consultatie.diagnostic
        #FROM pacient inner JOIN programare on 
			Else
				SELECT CONCAT('Pacientul nu exista') as rezultat;
			END IF;


end //
DELIMITER ;




-- -----------------------------------------------------
-- Procedura Istoric Medical
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE RAPORT_PROFIT_LUNA( inLUNA int ,out profit int)
BEGIN

		
		
         if inLuna > month(now()) then  

		SELECT SUM(serviciu_medical.pret) - sum(salariu_negociat) into profit 
		from programare,serviciu_medical,consultatie,utilizator_angajat
		where id_Serviciu_Medical=id_serviciu
		and id_Programare=programare_id_Programare
        and (month(Data_angajarii) < inLuna and year(Data_angajarii) < year(now()))
	    and MONTH(data_programare)=inLuna
        and year(data_programare)=YEAR(NOW())-1;
			#group by month(data_programare);
            
            else
            
		SELECT SUM(serviciu_medical.pret) - sum(salariu_negociat) into profit 
		from programare,serviciu_medical,consultatie,utilizator_angajat
		where id_Serviciu_Medical=id_serviciu
		and id_Programare=programare_id_Programare
        and (month(Data_angajarii) < inLuna and year(Data_angajarii) <=year(now()))
	    and MONTH(data_programare)=inLuna
        and year(data_programare)=year(now());
			#group by month(data_programare);
            
            end if;
            
            
            #select sum(salariu_negociat) as Cheltuieli 
            #from utilizator_angajat
            #where (month(Data_angajarii) < inLuna or year(Data_angajarii) < year(now()));
            
           #select Venit - Cheltueli;
end //
DELIMITER ;


-- -----------------------------------------------------
-- Procedura Istoric Medical
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE RAPORT_PROFIT_LUNA_MEDIC(inLUNA int,inCNP varchar(14),out profit int)
BEGIN
     
		
           
           
         if inLuna > month(now()) then  

		SELECT SUM(serviciu_medical.pret) - salariu_negociat into profit
		from programare,serviciu_medical,consultatie,utilizator_angajat
		where id_Serviciu_Medical=id_serviciu
		and id_Programare=programare_id_Programare
        and (month(Data_angajarii) < inLuna and year(Data_angajarii) < year(now()))
	    and MONTH(data_programare)=inLuna
        and utilizator_angajat.CNP=inCNP;
            
            else
            
        SELECT SUM(serviciu_medical.pret) - salariu_negociat into profit
		from programare,serviciu_medical,consultatie,utilizator_angajat
		where id_Serviciu_Medical=id_serviciu
		and id_Programare=programare_id_Programare
        and (month(Data_angajarii) < inLuna and year(Data_angajarii) < year(now()))
	    and MONTH(data_programare)=inLuna
        and year(data_programare)=year(now())
        and utilizator_angajat.CNP=inCNP;
            
            end if;
           
	
			
end //
DELIMITER ;


-- -----------------------------------------------------
-- Procedura Istoric Medical
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE RAPORT_PROFIT_AN(inAn int)
BEGIN
     
		SELECT SUM(serviciu_medical.pret) - sum(salariu_negociat) 
		from programare,serviciu_medical,consultatie,utilizator_angajat
		where id_Serviciu_Medical=id_serviciu
		and id_Programare=programare_id_Programare
        and (year(Data_angajarii) < inLuna or year(Data_angajarii) < year(now()))
	    and year(data_programare)=inAn;
     

end //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CAUTARE_ANGAJAT(inText varchar(45))
BEGIN
		SELECT Nume,Prenume,Denumire,Nume_functie
        FROM utilizator_angajat,unitate_medicala,functie
        WHERE utilizator_angajat.unitate_medicala_idUnitate_Medicala = unitate_medicala.idUnitate_Medicala 
		AND functie.idFunctie=utilizator_angajat.Functie_idFunctie
        AND (Nume = inText or Prenume = inText or CNP = inText);
     

end //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SALARIU_ANGAJAT(inText varchar(45))
BEGIN
		SELECT salariu_negociat
        FROM utilizator_angajat
        WHERE Nume = inText 
        or Prenume = inText 
        or CNP = inText;
     
end //
DELIMITER ;




     