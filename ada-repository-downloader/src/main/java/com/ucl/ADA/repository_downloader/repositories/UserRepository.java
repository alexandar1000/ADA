package com.ucl.ADA.repository_downloader.repositories;

import com.ucl.ADA.repository_downloader.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
