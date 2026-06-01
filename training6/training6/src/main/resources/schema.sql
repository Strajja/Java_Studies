DROP TABLE IF EXISTS "vehicles";

CREATE TABLE "vehicles"(

                        id bigserial PRIMARY KEY NOT NULL ,
                        brand varchar(255),
                        model varchar(255)
);