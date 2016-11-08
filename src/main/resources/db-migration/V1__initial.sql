CREATE TABLE "user"
(
    id bigint NOT NULL,
    birthday date,
    email character varying(255) COLLATE "default".pg_catalog,
    firstname character varying(255) COLLATE "default".pg_catalog,
    lastname character varying(255) COLLATE "default".pg_catalog,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)

CREATE TABLE auditorium
(
    id bigint NOT NULL,
    name character varying(255) COLLATE "default".pg_catalog,
    numberofseats bigint NOT NULL,
    CONSTRAINT auditorium_pkey PRIMARY KEY (id)
)

CREATE TABLE event
(
    id bigint NOT NULL,
    baseprice double precision NOT NULL,
    name character varying(255) COLLATE "default".pg_catalog,
    rating character varying(255) COLLATE "default".pg_catalog,
    CONSTRAINT event_pkey PRIMARY KEY (id)
)

CREATE TABLE airdate
(
    id bigint NOT NULL,
    datetime timestamp without time zone,
    auditorium_id bigint NOT NULL,
    event_id bigint NOT NULL,
    CONSTRAINT airdate_pkey PRIMARY KEY (id),
    CONSTRAINT uk_n6ko9ap12ibwxpe679wfqsww4 UNIQUE (auditorium_id),
    CONSTRAINT fklbe0b12uot042r4xh2ua8cryk FOREIGN KEY (auditorium_id)
        REFERENCES auditorium (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkorl6aao0f8h1ueqauw4f0des5 FOREIGN KEY (event_id)
        REFERENCES event (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE ticket
(
    id bigint NOT NULL,
    purchasedate timestamp without time zone,
    seat bigint NOT NULL,
    airdate_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT ticket_pkey PRIMARY KEY (id),
    CONSTRAINT fk1yex12fi75hf3d313kciosu9k FOREIGN KEY (airdate_id)
        REFERENCES airdate (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkggy0a7l6mmokee4ntu85fcjp3 FOREIGN KEY (user_id)
        REFERENCES "user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE vip_seat
(
    auditorium_id bigint NOT NULL,
    seat_number bigint,
    CONSTRAINT fk6wa6x6nf24j7kmk11o9l1gukj FOREIGN KEY (auditorium_id)
        REFERENCES auditorium (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)