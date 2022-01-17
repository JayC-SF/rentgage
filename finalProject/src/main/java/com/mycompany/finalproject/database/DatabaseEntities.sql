create database if not exists `RentGage_1533920_Juan-Carlos_Sreng-Flores`;
use `RentGage_1533920_Juan-Carlos_Sreng-Flores`;
drop table if exists maintenance cascade;
drop table if exists contractor cascade;
drop table if exists condo cascade;
drop table if exists unit cascade;
drop table if exists plex cascade;
drop table if exists house cascade;
drop table if exists mortgage cascade;
drop table if exists bank cascade;
drop table if exists lease cascade;
drop table if exists property cascade;
drop table if exists tenant cascade;
drop trigger if exists update_num_of_appt_before_insert;
drop trigger if exists update_num_of_appt_after_delete;
create table if not exists tenant(
	tenant_id int auto_increment,
	first_name varchar(50),
	last_name varchar(50),
	phone varchar(50),
	email varchar(50),
	primary key (tenant_id)
);

create table if not exists bank(
	bank_id int primary key auto_increment,
	bank_name varchar(50),
	address varchar(50),
	phone varchar(50),
	email varchar(50)
);

create table if not exists contractor(
	contractor_id int primary key auto_increment,
	contact_name varchar(50),
	company_name varchar(50),
	address varchar(50),
	specialty varchar(50),
	phone varchar(20),
	email varchar(50)
);

create table if not exists property(
	property_id int primary key auto_increment,
	address varchar(200),
	country varchar(200),
	state varchar(200),
	city varchar(200),
	postal_code varchar(200),
	details varchar(10000)
);
drop table lease;
create table if not exists lease(
	lease_id int primary key auto_increment,
	property_id int,
	tenant_id int,
    extension varchar(200),
	foreign key (property_id) references property(property_id)
		on delete cascade,
	foreign key (tenant_id) references tenant(tenant_id)
		on delete cascade
);
create table if not exists plex(
	property_id int primary key,
    num_of_apprt int default 0,
    foreign key(property_id) references property(property_id)
		on delete cascade
);

create table if not exists house(
	property_id int primary key,
    size double,
    foreign key(property_id) references property(property_id)
		on delete cascade
);

create table if not exists condo(
	property_id int primary key,
    appartment_number varchar(50),
    size double,
    condo_fee double,
    foreign key(property_id) references property(property_id)
		on delete cascade
);
create table if not exists unit(
	unit_id int primary key auto_increment,
    appartment_number varchar(50),
	size double,
    details varchar(10000),
	property_id int,
	foreign key(property_id) references plex(property_id)
		on delete cascade
);

create table if not exists mortgage(
	mortgage_id int primary key auto_increment,
	start_date date,
	end_date date,
	monthly_payment double,
	num_years_contract int,
	total_loan_value double,
	amount_paid double,
	interest_rate double,
	bank_id int,
    property_id int,
	foreign key (bank_id) references bank(bank_id)
		on delete cascade,
	foreign key(property_id) references property(property_id)
		on delete cascade
);

create table if not exists maintenance(
	maintenance_id int primary key auto_increment,
	maintenance_type varchar(200),
	start_date date,
	end_date date,
	total_cost double,
    details varchar(10000),
    property_id int,
    contractor_id int,
    foreign key(property_id) references property(property_id)
		on delete cascade,
	foreign key(contractor_id) references contractor(contractor_id)
		on delete cascade
);

delimiter |
create trigger update_num_of_appt_before_insert before insert on unit 
	for each row 
	begin
		update plex set num_of_apprt = num_of_apprt+1 
			where property_id = NEW.property_id;
	end;
| delimiter ;

delimiter |
create trigger update_num_of_appt_after_delete after delete on unit
	for each row
    begin
		update plex set num_of_apprt = num_of_apprt-1
			where property_id = OLD.property_id;
	end;
| delimiter ;


commit;

