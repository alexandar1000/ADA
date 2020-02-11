CREATE TABLE public.snapshots
(
    snapshot_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (start 1),
    fk_branch_id bigint NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    CONSTRAINT snapshot_pkey PRIMARY KEY (snapshot_id),
    CONSTRAINT snapshot_fkey FOREIGN KEY (fk_branch_id)
        REFERENCES public.branches (branch_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE 
)

TABLESPACE pg_default;

ALTER TABLE public.snapshots
    OWNER to "ada-team";