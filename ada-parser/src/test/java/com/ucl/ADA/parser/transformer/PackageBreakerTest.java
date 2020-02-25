package com.ucl.ADA.parser.transformer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class PackageBreakerTest {

    private PackageBreaker packageBreaker;

    private Set<String> classNames = new HashSet<>(Arrays.asList(
            "package1.Dog",
            "package1.fruit.Banana",
            "package1.fruit.Apple",
            "package2.Water",
            "Pork"
    ));

    private Map<String, Set<String>> expectedPackageContents = new HashMap<>();

    @BeforeEach
    void setUp() {
        expectedPackageContents.put("", new HashSet<>(Collections.singletonList("Pork")));
        expectedPackageContents.put("package1.fruit", new HashSet<>(Arrays.asList("package1.fruit.Banana", "package1.fruit.Apple")));
        expectedPackageContents.put("package2", new HashSet<>(Collections.singletonList("package2.Water")));
        expectedPackageContents.put("package1", new HashSet<>(Collections.singletonList("package1.Dog")));
    }

    @Test
    void testConstructor_hasCorrectSize() {
        packageBreaker = new PackageBreaker(classNames);
        assertThat(packageBreaker.getPackageContents()).hasSize(4);
    }

    @Test
    void testConstructor_hasCorrectContent() {
        packageBreaker = new PackageBreaker(classNames);

        assertThat(packageBreaker.getPackageContents()).containsExactlyInAnyOrderEntriesOf(expectedPackageContents);
    }
}