package com.ucl.ADA.model.owner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    //TODO use DSL
    @Query(value = "SELECT user_name FROM public.owner ORDER BY id ASC", nativeQuery = true)
    List<String> fetchAllUsername();

    Owner findByUsername(String userName);

    List<Owner> findAllByOrderByIdAsc();
}
