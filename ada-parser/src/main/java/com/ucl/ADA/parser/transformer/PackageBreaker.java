package com.ucl.ADA.parser.transformer;

import lombok.Getter;

import java.util.*;

@Getter
class PackageBreaker {

    /**
     * a map that contains all package contents. The key is package name and the value is the set of qualified class names under the package
     */
    private Map<String, Set<String>> packageContents = new HashMap<>();

    /**
     * break the qualified name of classes into package name
     *
     * @param classNames a set of class names
     */
    protected PackageBreaker(Set<String> classNames) {
        for (String className : classNames) {
            int p = className.lastIndexOf(".");
            String packageName = (p == -1 ? "" : className.substring(0, p));
            if (!packageContents.containsKey(packageName)) {
                packageContents.put(packageName, new HashSet<>(Collections.singletonList(className)));
            } else {
                Set<String> curr = packageContents.get(packageName);
                curr.add(className);
            }
        }
    }

}


