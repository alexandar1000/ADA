CREATE TABLE public.repositories
(
    repo_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( start 1 ),
    repo_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fk_user_id bigint NOT NULL,
    CONSTRAINT repositories_pkey PRIMARY KEY (repo_id),
    CONSTRAINT repositories_fkey FOREIGN KEY (fk_user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
)

TABLESPACE pg_default;

ALTER TABLE public.repositories
    OWNER to "ada-team";