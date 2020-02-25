package com.ucl.ADA.parser.transformer;

import java.util.Set;

class PackageBreaker {

    private PackageNode root;

    protected PackageBreaker(Set<String> classNames) {
        root = new PackageNode();

        for (String className : classNames) {
            String[] classNameArr = className.split("\\.");

        }
    }

    private static class PackageNode {
        String name;
        Set<PackageNode> subPackageNodes;
        Set<String> classNames;

    }
}


