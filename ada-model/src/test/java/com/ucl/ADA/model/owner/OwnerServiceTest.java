package com.ucl.ADA.model.owner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OwnerServiceTest {
    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterAll
    static void deleteDir(){
        String path = System.getProperty("user.dir")+"/temp";
        deleteQuietly(new File(path));
    }

    @Test
    void getAllOwners(){

        Owner owner = new Owner("naum");
        Owner owner2 = new Owner("nina");

        owner.setId(1L);
        owner2.setId(2L);

        List<Owner> owners = new ArrayList<>();
        owners.add(owner);
        owners.add(owner2);

        when(ownerRepository.findAllByOrderByIdAsc()).thenReturn(owners);

        List<Owner> retrievedRepos = ownerService.listOwners();

        verify(ownerRepository).findAllByOrderByIdAsc();

        assertThat(retrievedRepos).hasSize(2);

        assertThat(retrievedRepos.get(0).getId()).isEqualTo(1L);
        assertThat(retrievedRepos.get(1).getId()).isEqualTo(2L);

        assertThat(retrievedRepos.get(0).getUsername()).isEqualTo("naum");
        assertThat(retrievedRepos.get(1).getUsername()).isEqualTo("nina");
    }

    @Test
    void getAllUserNames(){

        Owner owner = new Owner("naum");
        Owner owner2 = new Owner("nina");

        List<String> ownerNames = new ArrayList<>();
        ownerNames.add(owner.getUsername());
        ownerNames.add(owner2.getUsername());

        when(ownerRepository.fetchAllUsername()).thenReturn(ownerNames);

        List<String> retrievedRepos = ownerService.listAllUsername();

        verify(ownerRepository, times(1)).fetchAllUsername();

        assertThat(retrievedRepos).hasSize(2);

        assertThat(retrievedRepos.get(0)).isEqualTo("naum");
        assertThat(retrievedRepos.get(1)).isEqualTo("nina");
    }

    @Test
    void getOwner(){
        Owner owner = new Owner("naum");
        owner.setId(122L);
        when(ownerRepository.findById(122L)).thenReturn(Optional.of(owner));

        Owner retrievedOwner = ownerService.getOwner(122L);

        verify(ownerRepository).findById(122L);

        assertThat(retrievedOwner.getUsername()).isEqualTo("naum");
        assertThat(retrievedOwner.getId()).isEqualTo(122L);
    }

    @Test
    void getNonExistingOwner(){

        when(ownerRepository.findById(122L)).thenReturn(Optional.empty());

        Owner retrievedOwner = ownerService.getOwner(122L);

        verify(ownerRepository).findById(122L);

        assertThat(retrievedOwner).isNull();
    }

    @Test
    void testDeleteOwner(){
        ownerService.deleteOwner(12L);
        verify(ownerRepository, times(1)).deleteById(12L);
    }

    @Test
    void testDeleteAllOwners(){
        ownerService.deleteAllOwners();
        verify(ownerRepository, times(1)).deleteAll();
    }

    @Test
    void testAddOwner(){
        Owner owner = new Owner("naum");
        owner.setId(122L);

        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        Owner returnedOwner = ownerService.addOwner(owner);
        verify(ownerRepository, times(1)).save(owner);

        assertThat(returnedOwner).isNotNull();
        assertThat(returnedOwner.getUsername()).isEqualTo("naum");
        assertThat(returnedOwner.getId()).isEqualTo(122L);
    }

    @Test
    void testGetOwnerByName(){
        Owner owner = new Owner("naum");
        owner.setId(122L);
        when(ownerRepository.findByUsername(any(String.class))).thenReturn(owner);

        Owner retrievedOwner = ownerService.getOwnerByName("naum");

        verify(ownerRepository).findByUsername("naum");

        assertThat(retrievedOwner.getUsername()).isEqualTo("naum");
        assertThat(retrievedOwner.getId()).isEqualTo(122L);
    }

    @Test
    void getNonExistingOwnerByUserName(){

        when(ownerRepository.findByUsername(any(String.class))).thenReturn(null);

        Owner retrievedOwner = ownerService.getOwnerByName("naum");

        verify(ownerRepository).findByUsername("naum");

        assertThat(retrievedOwner).isNull();
    }

}
