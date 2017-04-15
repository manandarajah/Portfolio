create table gsnaccounts (
	username varchar(255) not null,
	password varchar(255) not null,
	firstname varchar(255) not null,
	lastname varchar(255) not null,
	email varchar(255) not null,
	gender varchar(7) not null,
	disable boolean not null,
	primary key (username)
);

create table gsncontacts (
    Id int not null auto_increment,
    ContactUser1 varchar(255) not null,
    ContactUser2 varchar(255) not null,
    primary key (Id),
    foreign key (ContactUser1) references gsnaccounts (Username),
    foreign key (ContactUser2) references gsnaccounts (Username)
);

create table feedtable (
	username varchar(255) not null,
	feed longtext not null,
	feeddate datetime not null default NOW(),
	primary key (username),
	foreign key (username) references gsnaccounts (username)
);