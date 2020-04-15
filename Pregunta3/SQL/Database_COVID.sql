CREATE DATABASE COVID_19;

USE COVID_19;
DROP TABLE COVID19;

CREATE TABLE COVID19(
	id int auto_increment PRIMARY KEY,
	fecha date,
	casos_confirmados int,
	recuperados int,
	descartados int,
	casos_criticos int,
	fallecidos int,
	cantones_afectados int,
	total_mujeres int,
	total_hombres int
);

USE COVID_19;
ALTER TABLE COVID19 MODIFY fecha varchar(10);

USE COVID_19;
INSERT INTO COVID19 VALUES (1, '2020/04/01', 100, 0, 3, 6, 1, 13, 48, 52);
INSERT INTO COVID19 VALUES (0, '2020/04/02', 106, 0, 5, 7, 1, 14, 52, 54);
INSERT INTO COVID19 VALUES (0, '2020/04/03', 115, 0, 7, 7, 1, 17, 55, 60);
INSERT INTO COVID19 VALUES (0, '2020/04/04', 160, 3, 10, 8, 1, 19, 75, 85);
INSERT INTO COVID19 VALUES (0, '2020/04/05', 180, 5, 10, 8, 2, 19, 88, 92);
INSERT INTO COVID19 VALUES (0, '2020/04/06', 200, 8, 12, 11, 2, 20, 100, 100);
INSERT INTO COVID19 VALUES (0, '2020/04/07', 250, 10, 16, 13, 3, 20, 120, 130);