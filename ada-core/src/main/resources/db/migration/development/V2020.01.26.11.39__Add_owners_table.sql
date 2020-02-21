CREATE TABLE public.owner
(
    owner_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (start 1),
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (owner_id),
    CONSTRAINT uk_username UNIQUE (user_name)
)

TABLESPACE pg_default;

ALTER TABLE public.owner
    OWNER to "ada-team";