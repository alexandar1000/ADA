CREATE TABLE public.users
(
    user_id bigint NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT uk_username UNIQUE (user_name)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to "ada-team";