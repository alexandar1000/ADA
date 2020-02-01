CREATE TABLE public.repositories
(
    repoid bigint NOT NULL,
    repo_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fk_userid bigint NOT NULL,
    CONSTRAINT repositories_pkey PRIMARY KEY (repoid),
    CONSTRAINT fkbcntee4fw6mc8rqxlaxwvnu1 FOREIGN KEY (fk_userid)
        REFERENCES public.users (userid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.repositories
    OWNER to "ada-team";