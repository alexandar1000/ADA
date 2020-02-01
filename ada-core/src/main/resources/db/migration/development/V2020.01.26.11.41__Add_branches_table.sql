CREATE TABLE public.branches
(
    branch_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fk_repoid bigint NOT NULL,
    CONSTRAINT branches_pkey PRIMARY KEY (branch_name, fk_repoid),
    CONSTRAINT fkamcx93vfl972p18mu6sd650js FOREIGN KEY (fk_repoid)
        REFERENCES public.repositories (repoid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.branches
    OWNER to "ada-team";