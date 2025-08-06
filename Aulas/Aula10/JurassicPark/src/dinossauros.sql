create table dinossauros (
	id_dinossauro serial primary key,
	nome varchar(250),
	especie varchar(250),
	peso int,
	altura int,
	data_descoberta date
)

select * from dinossauros