package com.ucl.ADA.model.owner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    /**
     * Return usernames of all owners
     * @return List containing the usernames
     */
    @Query(value = "SELECT user_name FROM public.owner ORDER BY id ASC", nativeQuery = true)
    List<String> fetchAllUsername();

    /**
     * Get owner by username
     * @param userName Git username of the owner
     * @return retrieved owner
     */
    Owner findByUsername(String userName);

    /**
     * Get all owners ordered by their ID's, ascending order
     * @return Sorted list of owners by ID ASC.
     */
    List<Owner> findAllByOrderByIdAsc();
}