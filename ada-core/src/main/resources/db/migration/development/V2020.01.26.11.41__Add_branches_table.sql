CREATE TABLE public.branch
(
    branch_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (start 1),
    branch_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    repo_id bigint NOT NULL,
    CONSTRAINT branches_pkey PRIMARY KEY (branch_id),
    CONSTRAINT branches_fkey FOREIGN KEY (repo_id)
        REFERENCES public.repository (repo_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
)

TABLESPACE pg_default;

ALTER TABLE public.branch
    OWNER to "ada-team";