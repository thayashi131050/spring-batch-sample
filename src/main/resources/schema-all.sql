create table if not exists people( 
  person_id serial NOT NULL
  , first_name VARCHAR (20)
  , last_name VARCHAR (20)
  , PRIMARY KEY (person_id)
);