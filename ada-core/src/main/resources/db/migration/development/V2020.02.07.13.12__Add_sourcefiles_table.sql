CREATE TABLE public.source_files
(
    fk_snapshot_id bigint NOT NULL,
    file_hash int NOT NULL,
    file_name text NOT NULL,
    CONSTRAINT source_files_pkey PRIMARY KEY (file_hash),
    CONSTRAINT source_files_fkey FOREIGN KEY (fk_snapshot_id)
        REFERENCES public.snapshots (snapshot_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.source_files
    OWNER to "ada-team";