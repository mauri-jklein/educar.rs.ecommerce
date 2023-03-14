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
	valor decimal not null,
	id_pedido int references pedido,
	id_produto int references produto
);
--inserindo clientes
insert into cliente values(default, 'bruno klein', 'brunoklein@gmail.com', '1234'); 
insert into cliente values(default, 'mauri klein', 'maurijklein@gmail.com', '1234'); 
--inserindo produtos
insert into produto values(default, 'mouse', 'mouse gamer exbom', 67.90);
insert into produto values(default, 'notebook', 'intel core i7 - 2.8 GHz', 5824.50);
--inserindo pedido
insert into pedido values(default, default, 5067.89, 1);
insert into pedido values(default, default, 4999.99, 2);
--inserindo item_pedido
insert into item_pedido values(default, 67.90, 1, 1);
insert into item_pedido values(default, 4999.99, 1, 2);
insert into item_pedido values(default, 4999.99, 2, 2);