insert into property(address,country,state) values('add', 'count', 'state');
insert into plex(property_id) values(1);
select * from plex inner join property using(property_id);
SELECT ifnull(AUTO_INCREMENT,0) as AUTO_INCREMENT
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'RentGage_1533920_Juan-Carlos_Sreng-Flores'
AND TABLE_NAME = 'property';
commit;
select max(property_id) from property;
select * from property;
select * from house;
update house, property 
	set 
		property.address = 'addddd',
		property.country = 'countttt',
        property.state = 'statttt',
        property.city = 'cityyy',
        property.postal_code = 'postttt',
        property.details = 'detailssss',
        house.size = 45.555
	where 
		property.property_id = 2
        and
        house.property_id = property.property_id;

delete from property where property_id in (select property_id from property);
delete from property where property_id is null;
/*
insert into bank(bank_name, address, phone, email)
values("Desjardins", "TO EDIT", "TO EDIT", "TO EDIT");
insert into bank(bank_name, address, phone, email)
values("Royal Bank of Canada", "TO EDIT", "TO EDIT", "TO EDIT");
update bank set bank_name = 'RBC', address = '', phone = '', email = ''  where bank_id = 2;
delete from bank where bank_id = 2;
select * from bank;
select max(bank_id) from bank;
create table if not exists Bank
*/
select * from unit;
select * from property;
select * from tenant;
select * from property;
select * from bank;
select mortgage.*, property.address, bank.bank_name from mortgage 
	inner join bank using(bank_id)
    inner join property using(property_id);
insert into mortgage(start_date, end_date, monthly_payment, num_years_contract, total_loan_value, amount_paid, interest_rate, bank_id, property_id)
	values('2021-02-02', '2021-02-03', 0.5, 1, 0.5, 1.0, 1, 2 ,2);
    
select maintenance.*, property.address, company_name from maintenance
	inner join contractor using(contractor_id)
    inner join property using(property_id);
select * from maintenance;
insert into maintenance(maintenance_type, start_date, end_date, total_cost, details, property_id, contractor_id) values(?,?,?,?,?,?,?);
update maintenance set maintenance_type = ?, start_date = ?, end_date = ?, total_cost = ?, details = ?, property_id = ?, contractor_id = ? where maintenance_id = ?;
create database `java_project`;