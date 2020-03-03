package com.ucl.ADA.model.source_file;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SourceFileRepository extends CrudRepository<SourceFile, Integer> {
    List<SourceFile> findAllByOrderByFileHashAsc();
}
