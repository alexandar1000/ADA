package com.ucl.ADA.core.boostrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // TODO: This is a 'integration testing call and should be omitted for ow'
    }

    private void loadData() throws FileNotFoundException {
    }
}
