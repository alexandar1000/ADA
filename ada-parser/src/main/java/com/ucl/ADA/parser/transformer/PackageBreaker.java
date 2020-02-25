package com.ucl.ADA.parser.transformer;

import lombok.Getter;

import java.util.*;

@Getter
class PackageBreaker {

    private Map<String, Set<String>> packageContents = new HashMap<>();

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


