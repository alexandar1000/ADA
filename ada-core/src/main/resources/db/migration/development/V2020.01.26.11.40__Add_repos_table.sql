CREATE TABLE public.repository
(
    repo_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( start 1 ),
    repo_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    owner_id bigint NOT NULL,
    CONSTRAINT repositories_pkey PRIMARY KEY (repo_id),
    CONSTRAINT repositories_fkey FOREIGN KEY (owner_id)
        REFERENCES public.owner (owner_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
)

TABLESPACE pg_default;

ALTER TABLE public.repository
    OWNER to "ada-team";