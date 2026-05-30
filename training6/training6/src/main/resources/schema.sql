DROP TABLE IF EXISTS "vehicles";

CREATE TABLE "games"(

                        id bigserial PRIMARY KEY NOT NULL ,
                        brand varchar(255),
                        model varchar(255)
);