DROP TABLE IF EXISTS "games";

CREATE TABLE "games"(

    id bigserial PRIMARY KEY NOT NULL ,
    title varchar(255),
    genre varchar(255),
    release_year int
);