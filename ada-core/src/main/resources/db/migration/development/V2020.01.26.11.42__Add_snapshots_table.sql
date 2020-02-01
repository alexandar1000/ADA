CREATE TABLE public.snapshot
(
    file_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    fk_branchname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fk_b_repoid bigint NOT NULL,
    CONSTRAINT snapshot_pkey PRIMARY KEY (fk_branchname, fk_b_repoid, file_name, "timestamp"),
    CONSTRAINT fk4bdhn6xrhjp92vjc220jxkorp FOREIGN KEY (fk_b_repoid, fk_branchname)
        REFERENCES public.branches (fk_repoid, branch_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.snapshot
    OWNER to "ada-team";