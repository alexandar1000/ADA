CREATE TABLE public.snapshot
(
    file_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    fk_branch_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fk_b_repo_id bigint NOT NULL,
    CONSTRAINT snapshot_pkey PRIMARY KEY (fk_branch_name, fk_b_repo_id, file_name, "timestamp"),
    CONSTRAINT snapshot_fkey FOREIGN KEY (fk_b_repo_id, fk_branch_name)
        REFERENCES public.branches (fk_repo_id, branch_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
)

TABLESPACE pg_default;

ALTER TABLE public.snapshot
    OWNER to "ada-team";