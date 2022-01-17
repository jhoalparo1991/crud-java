drop database crud_java;

create database if not exists crud_java;

use crud_java;

create table if not exists users(
	id int primary key auto_increment,
    fullname varchar(100)  not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    createdAt timestamp default now()    
)engine=InnoDB;

create table if not exists product(
	id int primary key auto_increment,
    category_id int not null,
    name varchar(50) not null unique,
    price decimal(18,2) not null,
    createdAt timestamp default now()    
)engine=InnoDB;

create table if not exists category(
	id int primary key auto_increment,
    name varchar(50) not null unique
)engine=InnoDB;

alter table product
add constraint fk_product_category
foreign key(category_id) references category(id)
on update cascade
on delete cascade
