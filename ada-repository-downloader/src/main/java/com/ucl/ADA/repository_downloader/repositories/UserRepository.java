package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT user_name FROM public.users", nativeQuery = true)
    List<String> fetchUserNames();

    User findByUserName(String userName);
}
