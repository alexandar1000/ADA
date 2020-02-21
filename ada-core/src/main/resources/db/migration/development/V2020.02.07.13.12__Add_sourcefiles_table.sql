CREATE TABLE public.sourcefile
(
    snapshot_id bigint NOT NULL,
    file_hash int NOT NULL,
    file_name text NOT NULL,
    CONSTRAINT source_files_pkey PRIMARY KEY (file_hash),
    CONSTRAINT source_files_fkey FOREIGN KEY (snapshot_id)
        REFERENCES public.snapshot (snapshot_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE public.sourcefile
    OWNER to "ada-team";