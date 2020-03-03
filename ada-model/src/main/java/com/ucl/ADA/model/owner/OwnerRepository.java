package com.ucl.ADA.model.owner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    //TODO use DSL
    @Query(value = "SELECT user_name FROM public.owner", nativeQuery = true)
    List<String> fetchUserNames();

    Owner findByUserName(String userName);

    List<Owner> findAllByOrderByOwnerIDAsc();
}
