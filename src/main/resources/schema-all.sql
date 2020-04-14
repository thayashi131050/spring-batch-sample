drop table if exists people;

create table people( 
  person_id SERIAL NOT NULL
  , first_name VARCHAR (20)
  , last_name VARCHAR (20)
  , PRIMARY KEY (person_id)
);