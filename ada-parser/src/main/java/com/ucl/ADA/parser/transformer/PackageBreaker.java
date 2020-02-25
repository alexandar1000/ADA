package com.ucl.ADA.parser.transformer;

import java.util.Set;

class PackageBreaker {

    private static class PackageNode {
        String name;
        Set<PackageNode> subPackageNodes;
        Set<String> classNames;
    }
}


