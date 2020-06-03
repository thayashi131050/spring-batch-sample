drop table if exists peopleOutput;

create table peopleOutput( 
  person_id SERIAL NOT NULL
  , full_name VARCHAR (40)
  , PRIMARY KEY (person_id)
);
