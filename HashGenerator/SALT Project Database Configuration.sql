/* Creates and sets database */
create database ctajax_db;
use ctajax_db;

/* Generates customer and employee tables */
create table customer (
	custno int not null auto_increment, 
	first_name varchar(255) not null, 
	last_name varchar(255) not null, 
	password longtext not null, 
	address_1 varchar(255) not null,
	address_2 varchar(255) not null,
	postal_code char(7) not null,
	areacode char(3) not null,
	number char(7) not null,
	email_address varchar(255) not null,
	primary key (custno)
);
create table employee (
	empno int not null auto_increment, 
	first_name varchar(255) not null, 
	last_name varchar(255) not null, 
	password longtext not null, 
	address_1 varchar(255) not null,
	address_2 varchar(255) not null,
	postal_code char(7) not null,
	areacode char(3) not null,
	number char(7) not null,
	email_address varchar(255) not null,
	position varchar(255) not null,
	payrate decimal(2,2) not null,
	datehired date not null,
	primary key (empno)
);

/* Describes customer and employee table information */
desc customer;
desc employee;