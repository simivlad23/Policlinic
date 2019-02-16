call ADAUGARE_Utilizator_Angajat('1900101125111', 1, 5, 'irinel1', 'parola', 'Condrac', 'Irina', 'Cluj-Napoca strada Clinicilor', '0751231234', 'irinel@gmail.com', '566235432', now(), 20000, 40);
call ADAUGARE_Utilizator_Angajat('1870202125112', 1, 4, 'iuga23', 'parolatare', 'Iuga', 'Vasile', 'Cluj fabricii 96', '0769690969', 'iuga@gmail.com', '312156632', now(), 1800, 40);
call ADAUGARE_PACIENT('1970316125127', 'Bledea', 'Marius');
call ADAUGARE_ASISTENT('1870202125112', 'generalist', 'principal');
call ADAUGARE_MEDIC('1900101125111', 1, 'specialist', 10, 'ceava', 'lector');
call ADAUGARE_PROGRAMARE('1970316125127', '1900101125111', 2,'2018-02-25 10:00:00');
call ADAUGARE_CONSULTATIE(6, 'stare de lesin', 'masurare tensiune arteriala', 'hiper-tensiune', 'FASINOPRIL, noAlcool');
call ISTORIC_PACINT('1970316125126');