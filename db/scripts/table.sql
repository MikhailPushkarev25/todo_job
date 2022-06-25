create table items (
   id serial primary key,
   name text,
   description text,
   created timestamp,
   done boolean,
   user_id int not null references users(id)
);

create table users(
   id serial primary key,
   name text,
   phone text,
   email text,
   password text
);