package com.ucl.ADA.repository_downloader.services;

import com.ucl.ADA.model.owner.Owner;
import com.ucl.ADA.model.owner.OwnerRepository;
import com.ucl.ADA.repository_downloader.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OwnerServiceTests {

    @InjectMocks
    private OwnerService ownerService;

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

        List<Owner> retrievedRepos = ownerService.listUsers();

        verify(ownerRepository).findAll();

        assertThat(retrievedRepos).hasSize(2);

        assertThat(retrievedRepos.get(0).getOwnerID()).isEqualTo(1L);
        assertThat(retrievedRepos.get(1).getOwnerID()).isEqualTo(2L);

        assertThat(retrievedRepos.get(0).getUserName()).isEqualTo("naum");
        assertThat(retrievedRepos.get(1).getUserName()).isEqualTo("nina");
    }

    @Test
    void getAllUserNames(){

        Owner owner = new Owner("naum");
        Owner owner2 = new Owner("nina");

        List<String> ownerNames = new ArrayList<>();
        ownerNames.add(owner.getUserName());
        ownerNames.add(owner2.getUserName());

        when(ownerRepository.fetchUserNames()).thenReturn(ownerNames);

        List<String> retrievedRepos = ownerService.listUserNames();

        verify(ownerRepository, times(1)).fetchUserNames();

        assertThat(retrievedRepos).hasSize(2);

        assertThat(retrievedRepos.get(0)).isEqualTo("naum");
        assertThat(retrievedRepos.get(1)).isEqualTo("nina");
    }

    @Test
    void getUser(){
        Owner owner = new Owner("naum");
        owner.setOwnerID(122L);
        when(ownerRepository.findById(122L)).thenReturn(Optional.of(owner));

        Owner retrievedOwner = ownerService.getUser(122L);

        verify(ownerRepository).findById(122L);

        assertThat(retrievedOwner.getUserName()).isEqualTo("naum");
        assertThat(retrievedOwner.getOwnerID()).isEqualTo(122L);
    }

    @Test
    void getNonExistingUser(){

        when(ownerRepository.findById(122L)).thenReturn(Optional.empty());

        Owner retrievedOwner = ownerService.getUser(122L);

        verify(ownerRepository).findById(122L);

        assertThat(retrievedOwner).isNull();
    }

    @Test
    void testDeleteOwner(){
        ownerService.deleteUser(12L);
        verify(ownerRepository, times(1)).deleteById(12L);
    }

    @Test
    void testDeleteAllOwners(){
        ownerService.deleteAllUsers();
        verify(ownerRepository, times(1)).deleteAll();
    }

    @Test
    void testAddOwner(){
        Owner owner = new Owner("naum");
        owner.setOwnerID(122L);

        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        Owner returnedOwner = ownerService.addUser(owner);
        verify(ownerRepository, times(1)).save(owner);

        assertThat(returnedOwner).isNotNull();
        assertThat(returnedOwner.getUserName()).isEqualTo("naum");
        assertThat(returnedOwner.getOwnerID()).isEqualTo(122L);
    }

    @Test
    void testGetOwnerByName(){
        Owner owner = new Owner("naum");
        owner.setOwnerID(122L);
        when(ownerRepository.findByUserName(any(String.class))).thenReturn(owner);

        Owner retrievedOwner = ownerService.getUserByName("naum");

        verify(ownerRepository).findByUserName("naum");

        assertThat(retrievedOwner.getUserName()).isEqualTo("naum");
        assertThat(retrievedOwner.getOwnerID()).isEqualTo(122L);
    }

    @Test
    void getNonExistingUserByUserName(){

        when(ownerRepository.findByUserName(any(String.class))).thenReturn(null);

        Owner retrievedOwner = ownerService.getUserByName("naum");

        verify(ownerRepository).findByUserName("naum");

        assertThat(retrievedOwner).isNull();
    }
}
