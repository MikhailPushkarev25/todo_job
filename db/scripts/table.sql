create table items (
   id serial primary key,
   name text,
   description text,
   created timestamp,
   done boolean,
   users_id int not null references users(id)
);

create table categories (
    id serial primary key,
    name text
);

insert into categories(name) values('авто');
insert into categories(name) values('программирование');
insert into categories(name) values('еда');
insert into categories(name) values('музыка');

create table items_categories (
    items_id int not null references items(id),
    categories_id int not null references categories(id)
);

create table users(
   id serial primary key,
   name text,
   phone text,
   email text,
   password text
);