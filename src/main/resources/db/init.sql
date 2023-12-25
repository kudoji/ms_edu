CREATE SCHEMA users_schema;

CREATE TABLE users_schema.users
(
    id serial NOT NULL PRIMARY KEY,
    email character varying(255) NOT NULL,
    fname character varying(255) NOT NULL,
    sname character varying(255) NOT NULL,
    mname character varying(255) NOT NULL
);

CREATE TABLE users_schema.users_subscriptions
(
    id serial NOT NULL PRIMARY KEY,
    user_id int NOT NULL,
    subscriber_id int not null,
    date date not null default current_date
);

CREATE TABLE users_schema.posts
(
    id serial NOT NULL PRIMARY KEY,
    title character varying(100) NOT NULL,
    body character varying NOT NULL,
    author_id int not null
);

CREATE TABLE users_schema.posts_likes
(
    id serial NOT NULL PRIMARY KEY,
    post_id int NOT NULL,
    liked_by_user_id int NOT NULL,
    date date not null default current_date
);

alter table users_schema.users_subscriptions
    add constraint user_id_fk foreign key (user_id)
        references users_schema.users (id)
        on update restrict
        on delete restrict
;
alter table users_schema.users_subscriptions
    add constraint subscriber_id_fk foreign key (subscriber_id)
        references users_schema.users (id)
        on update restrict
        on delete restrict
;

alter table users_schema.posts
    add constraint author_id_fk foreign key (author_id)
        references users_schema.users (id)
        on update restrict
        on delete restrict
;

alter table users_schema.posts_likes
    add constraint post_id_fk foreign key (post_id)
        references users_schema.posts (id)
        on update restrict
        on delete restrict
;
alter table users_schema.posts_likes
    add constraint liked_by_user_id_fk foreign key (liked_by_user_id)
        references users_schema.users (id)
        on update restrict
        on delete restrict
;

alter table users_schema.users
	add column gender_id int not null
;
alter table users_schema.users
	add column city_id int not null
;

create table users_schema.genders(
	id serial not null primary key,
	name character varying(100) not null
);

create table users_schema.cities(
	id serial not null primary key,
	name character varying(150) not null
);

alter table users_schema.users
	add constraint fk_gender_id foreign key (gender_id)
		references users_schema.genders (id)
		on update restrict
		on delete restrict
;

alter table users_schema.users
	add constraint fk_city_id foreign key (city_id)
		references users_schema.cities (id)
		on update restrict
		on delete restrict
;

create index i_user_gender
	on users_schema.users (gender_id)
;

create index i_gender_name
	on users_schema.genders (name)
;

create index i_user_city
	on users_schema.users (city_id)
;

create index i_city_name
	on users_schema.cities (name)
;

create index i_user_city_gender
	on users_schema.users (city_id, gender_id)
;