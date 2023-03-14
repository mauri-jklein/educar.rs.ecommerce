create table if not exists produto(
	id serial primary key,
	nome varchar(50) not null,
	descricao varchar(50) not null,
	preco decimal not null	
);

create table if not exists cliente(
	id serial primary key,
	nome varchar(50) not null,
	email varchar(50) not null,
	senha varchar(50) not null	
);

create table if not exists pedido(
	id serial primary key,
	data date not null default CURRENT_DATE, 
	valot_total decimal not null,
	id_cliente int references cliente
);

create table if not exists item_pedido(
	id serial primary key,
	valot decimal not null,
	id_pedido int references pedido,
	id_produto int references produto
);
