--Clienti
CREATE TABLE clienttt(id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1), 
            first_name VARCHAR2(30) CONSTRAINT client_first_name_nnn NOT NULL, 
            last_name VARCHAR2(30) CONSTRAINT client_last_name_nnn NOT NULL, 
            cnp VARCHAR2(10) CONSTRAINT client_cnp_nnn NOT NULL, 
            date_of_birth DATE CONSTRAINT client_date_of_birth_nnn NOT NULL, 
            CONSTRAINT client_cnp_ukk UNIQUE(cnp), 
            CONSTRAINT id_c_pkk PRIMARY KEY (id)
        );
        
drop table clienttt;
INSERT INTO clienttt (first_name,last_name,cnp,date_of_birth)  VALUES ('Ana', 'R', 12345, TO_DATE('10-MAY-2001'));
INSERT INTO clienttt (first_name,last_name,cnp,date_of_birth) VALUES ('Carla', 'H', 133456789, TO_DATE('20-MAY-2001'));
INSERT INTO clienttt (first_name,last_name,cnp,date_of_birth) VALUES ('Georgiana', 'A', 143456789, TO_DATE('30-MAY-2001'));
INSERT INTO clienttt (first_name,last_name,cnp,date_of_birth) VALUES ('Bianca', 'B', 153456789, TO_DATE('11-MAY-2001'));
INSERT INTO clienttt (first_name,last_name,cnp,date_of_birth) VALUES ('Stefana', 'H', 163456789, TO_DATE('12-MAY-2001'));


--Medicamente
 CREATE TABLE mediciness(id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) , 
            name VARCHAR2(30) CONSTRAINT name_nnn NOT NULL, 
            description VARCHAR2(350) CONSTRAINT description_nnn NOT NULL, 
            quantity NUMBER(30) CONSTRAINT quantity_nnn NOT NULL, 
            requiresPrescription NUMBER(1) CONSTRAINT requires_prescription_nnn NOT NULL, 
            minAge NUMBER(30) CONSTRAINT min_age_nnn NOT NULL, 
            CONSTRAINT id_m_pff PRIMARY KEY (id)
            );
            
INSERT INTO mediciness (name, description, quantity, requiresPrescription, minAge) VALUES ('Medicament1', 'descriere1', 10, 0, 18);
INSERT INTO mediciness (name, description, quantity, requiresPrescription, minAge) VALUES ('Medicament2', 'descriere2', 10, 0, 18);
INSERT INTO mediciness (name, description, quantity, requiresPrescription, minAge) VALUES ('Medicament3', 'descriere3', 10, 0, 18);
INSERT INTO mediciness (name, description, quantity, requiresPrescription, minAge) VALUES ('Medicament4', 'descriere4', 10, 0, 18);
INSERT INTO mediciness (name, description, quantity, requiresPrescription, minAge) VALUES ('Medicament5', 'descriere5', 10, 0, 18);




--Aprovizionari
CREATE TABLE supplyy( 
            id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
            date_s DATE CONSTRAINT date_aprov_nnn NOT NULL, 
            quantity NUMBER(30) CONSTRAINT quantity_aprov_nnn NOT NULL,
            id_medicament NUMBER(30) UNIQUE CONSTRAINT id_med_aprov_nnn NOT NULL,
            CONSTRAINT fkk_id_medicament_aprov FOREIGN KEY (id_medicament) REFERENCES mediciness (id),
            CONSTRAINT pkk_id_s PRIMARY KEY (id),
            CONSTRAINT ckk_q CHECK (quantity > 0)
        );

INSERT INTO supplyy (date_s, quantity, id_medicament) VALUES ('10-MAY-2021',10,1);
INSERT INTO supplyy (date_s, quantity, id_medicament) VALUES ('10-MAY-2021',10,2);
INSERT INTO supplyy (date_s, quantity, id_medicament) VALUES ('10-MAY-2021',10,3);
INSERT INTO supplyy (date_s, quantity, id_medicament) VALUES ('10-MAY-2021',10,4);
INSERT INTO supplyy (date_s, quantity, id_medicament) VALUES ('10-MAY-2021',10,5);

--Vanzari
CREATE TABLE salee( 
            id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
            date_sale DATE CONSTRAINT date_sale_nnn NOT NULL, 
            quantity NUMBER(30) CONSTRAINT quantity_sale_nnn NOT NULL,
            id_client NUMBER(30) CONSTRAINT id_client_sale_nnn NOT NULL,
            id_medicament NUMBER(30) CONSTRAINT id_med_sale_nnn NOT NULL,
            CONSTRAINT fkk_id_client_sale FOREIGN KEY (id_client) REFERENCES clienttt (id),
            CONSTRAINT fkk_id_medicament_sale FOREIGN KEY (id_medicament) REFERENCES mediciness (id),
            CONSTRAINT pkk_id_sale PRIMARY KEY (id)
            );


INSERT INTO salee (date_sale, quantity, id_client, id_medicament) VALUES ('10-MAY-2022',5,1,1);
INSERT INTO salee (date_sale, quantity, id_client, id_medicament) VALUES ('20-MAY-2022',4,2,2);
INSERT INTO salee (date_sale, quantity, id_client, id_medicament) VALUES ('30-MAY-2022',3,4,3);
INSERT INTO salee (date_sale, quantity, id_client, id_medicament) VALUES ('11-MAY-2022',2,5,4);
INSERT INTO salee (date_sale, quantity, id_client, id_medicament) VALUES ('12-MAY-2022',1,6,5);