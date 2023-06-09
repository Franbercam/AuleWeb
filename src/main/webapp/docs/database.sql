DROP TABLE IF EXISTS eventos;
DROP TABLE IF EXISTS aulas;
DROP TABLE IF EXISTS edificios;

CREATE TABLE edificios (
id int primary KEY,
nombre VARCHAR(200) NOT NULL
);

CREATE TABLE aulas (
id int primary KEY AUTO_INCREMENT,
idEdificios INT NOT NULL,
nombre VARCHAR(200) NOT NULL,
descripcion VARCHAR(1000),
ubicacion VARCHAR(200) NOT NULL,
aforo INT NOT NULL,
numEnchufes INT NOT NULL,
red INT NOT NULL,
tieneProyector BOOLEAN DEFAULT false,
tienePantallaMorotizada BOOLEAN DEFAULT false,
tienePantallaManual BOOLEAN DEFAULT false,
tieneSisAudio BOOLEAN DEFAULT false,
tienePC BOOLEAN DEFAULT false,
tieneMicIna BOOLEAN DEFAULT false, 
tieneMicAla BOOLEAN DEFAULT false,
tieneRetroProy BOOLEAN DEFAULT false,
tieneWifi BOOLEAN DEFAULT FALSE,
FOREIGN KEY (idEdificios) REFERENCES edificios(id) 
);

create table eventos (
id int primary KEY AUTO_INCREMENT,
idAula INT NOT NULL,
nombre VARCHAR(200) NOT NULL,
descripcion VARCHAR(1000) NOT NULL,
nombreResponsable VARCHAR(200) NOT NULL,
emailResponsable VARCHAR(200) NOT NULL,
fechaInicio DATETIME NOT NULL,
fechaFin DATETIME NOT NULL,
tipo ENUM('conferencia','examen','seminario','parcial','meeting','graduacion','otro') NOT NULL,
recurrencia ENUM('diaria','semanal','mensual','nula') NOT NULL,
fechaFinRecurrencia DATETIME,
FOREIGN KEY (idAula) REFERENCES aulas(id)
);

