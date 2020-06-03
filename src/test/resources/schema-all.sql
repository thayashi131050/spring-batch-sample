drop table if exists peopleInput;

create table peopleInput( 
  person_id SERIAL NOT NULL
  , first_name VARCHAR (20)
  , last_name VARCHAR (20)
  , PRIMARY KEY (person_id)
);

insert into peopleInput (first_name, last_name) values ('abc','aaa');
insert into peopleInput (first_name, last_name) values ('bcd','aaa');
insert into peopleInput (first_name, last_name) values ('cde','aaa');
insert into peopleInput (first_name, last_name) values ('def','aaa');

