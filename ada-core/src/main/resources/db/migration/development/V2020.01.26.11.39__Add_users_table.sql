CREATE TABLE public.users
(
    userid bigint NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (userid),
    CONSTRAINT uk_k8d0f2n7n88w1a16yhua64onx UNIQUE (user_name)
)

TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to "ada-team";