/*
Fichero de creación de la base de datos
=======================================
  
	--
	(PRIMERO DE TODO):	LA FORMA MÁS SENCILLA DE OBTENER UN SERVIDOR MYSQL ES
				INSTALAR XAMPP YA QUE VIENE CON APACHE, MYSQL Y TOMCAT.
				PODRÁS INTERACTUAR CON MYSQL DE FORMA VISUAL MEDIANTE WEB.
	--
	(PASO 1): 	Tienes que acceder desde consola o utilizando PHPMyAdmin de XAMPP y acceder
			como root, para acceder como administrador y poder crear el usuario.
			Creamos un usuario en nuestro servidor MySQL con permisos de administrador
			para la base de datos 'reminderapp'.	
				
			USUARIO:	usureminder
			PASSWORD:	usureminder
				
			*IMPORTANTE:	Aunque borremos la base de datos 'reminderapp', la referencia a
					los privilegios en dicha base de datos no se borra ya que
					todos los privilegios en las bases de datos de los usuarios
					se guardan en el servidor MySQL y no en la propia base de datos
					'reminderapp'.
					Así pues con ejecutar una única vez los comandos del (PASO 1) bastará.
				
			*NOTA:	Podemos usar el usuario root aunque aconsejo crear el usuario
				'usureminder' para no comprometer la seguridad de las otras bases de datos
				y de nuestro propio servidor MySQL.
	--
	(PASO 2): 	Creamos la base de datos y sus correspondientes tablas. Además se han añadido
			unos INSERT a modo de ejemplo.
				
			*IMPORTANTE:	Si cargamos el *.sql desde PHPMyAdmin debemos tener cuidado con
			el 'Conjunto de caracteres del archivo', debemos seleccionar x-user-defined que quiere
			decir que se seleccionará el tipo de caracteres según lo definido dentro del *.sql.
			Si copias el código desde consola esto no te debe preocupar.
	--
	
*/

/*
---------------
INICIO (PASO 1)
---------------

create user 'usureminder'@'localhost' identified by 'usureminder';
grant all on reminderapp.* to 'usureminder'@'localhost' identified by 'usureminder';

------------
FIN (PASO 1)
------------
*/

/*
---------------
INICIO (PASO 2)
---------------
*/

drop database if exists reminderapp;
create database reminderapp;
use reminderapp;
	
create table recordatorio (
	id int(6) primary key auto_increment,
	asunto char(100),
	fecha_alarma date,
	hora_alarma time,
	fecha_creacion timestamp
	)
	engine = InnoDB 
	default character set = latin1 
	collate = latin1_general_ci;

insert into recordatorio
	(usuario_log,asunto,alarma_activada,fecha_alarma,hora_alarma) values
	('esgarbal','Hola #1',true,'2013-05-10','22:00:00'),
	('esgarbal','Hola #2',false,'2013-06-10','22:10:00'),
	('juanito','Hola #1',true,'2013-10-10','10:30:00');

select * from recordatorio;

/*
------------
FIN (PASO 2)
------------
*/
