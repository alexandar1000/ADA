package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Owner, Long> {
    @Query(value = "SELECT user_name FROM public.users", nativeQuery = true)
    List<String> fetchUserNames();

    Owner findByUserName(String userName);
}
