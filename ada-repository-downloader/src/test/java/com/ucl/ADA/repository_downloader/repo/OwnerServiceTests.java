package com.ucl.ADA.repository_downloader.repo;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerRepository;
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

public class OwnerServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllUsers(){

        Owner owner = new Owner("naum");
        Owner owner2 = new Owner("nina");

        owner.setOwnerID(1L);
        owner2.setOwnerID(2L);

        List<Owner> owners = new ArrayList<>();
        owners.add(owner);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        List<Owner> retrievedRepos = userService.listUsers();

        verify(ownerRepository).findAll();

        assertThat(retrievedRepos).hasSize(2);

        assertThat(retrievedRepos.get(0).getOwnerID()).isEqualTo(1L);
        assertThat(retrievedRepos.get(1).getOwnerID()).isEqualTo(2L);

        assertThat(retrievedRepos.get(0).getUserName()).isEqualTo("naum");
        assertThat(retrievedRepos.get(1).getUserName()).isEqualTo("nina");
    }

    @Test
    void getUser(){
        Owner owner = new Owner("naum");
        owner.setOwnerID(122L);
        when(ownerRepository.findById(122L)).thenReturn(Optional.of(owner));

        Owner retrievedOwner = userService.getUser(122L);

        verify(ownerRepository).findById(122L);

        assertThat(retrievedOwner.getUserName()).isEqualTo("naum");
        assertThat(retrievedOwner.getOwnerID()).isEqualTo(122L);
    }

    @Test
    void getNonExistingUser(){

        when(ownerRepository.findById(122L)).thenReturn(Optional.empty());

        Owner retrievedOwner = userService.getUser(122L);

        verify(ownerRepository).findById(122L);

        assertThat(retrievedOwner).isNull();
    }
}
