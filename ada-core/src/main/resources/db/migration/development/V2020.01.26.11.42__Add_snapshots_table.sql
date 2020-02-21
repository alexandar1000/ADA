CREATE TABLE public.snapshot
(
    snapshot_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (start 1),
    branch_id bigint NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    CONSTRAINT snapshot_pkey PRIMARY KEY (snapshot_id),
    CONSTRAINT snapshot_fkey FOREIGN KEY (branch_id)
        REFERENCES public.branch (branch_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
)

TABLESPACE pg_default;

ALTER TABLE public.snapshot
    OWNER to "ada-team";