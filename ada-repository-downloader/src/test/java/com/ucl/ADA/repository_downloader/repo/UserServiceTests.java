package com.ucl.ADA.repository_downloader.repo;

import com.ucl.ADA.repository_downloader.entities.User;
import com.ucl.ADA.repository_downloader.repositories.UserRepository;
import com.ucl.ADA.repository_downloader.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllUsers(){

        User user = new User("naum");
        User user2 = new User("nina");

        user.setUserID(1L);
        user2.setUserID(2L);

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> retrievedRepos = userService.listUsers();

        verify(userRepository).findAll();

        assertThat(retrievedRepos).hasSize(2);

        assertThat(retrievedRepos.get(0).getUserID()).isEqualTo(1L);
        assertThat(retrievedRepos.get(1).getUserID()).isEqualTo(2L);

        assertThat(retrievedRepos.get(0).getUserName()).isEqualTo("naum");
        assertThat(retrievedRepos.get(1).getUserName()).isEqualTo("nina");
    }

    @Test
    void getUser(){
        User user = new User("naum");
        user.setUserID(122L);
        when(userRepository.findById(122L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUser(122L);

        verify(userRepository).findById(122L);

        assertThat(retrievedUser.getUserName()).isEqualTo("naum");
        assertThat(retrievedUser.getUserID()).isEqualTo(122L);
    }

    @Test
    void getNonExistingUser(){

        when(userRepository.findById(122L)).thenReturn(Optional.empty());

        User retrievedUser = userService.getUser(122L);

        verify(userRepository).findById(122L);

        assertThat(retrievedUser).isNull();
    }
}
