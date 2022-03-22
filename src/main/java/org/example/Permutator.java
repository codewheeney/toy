package org.example;

import java.util.Set;
import java.util.TreeSet;

public class Permutator {
    public static Set<String> permutateString(String source) {
        Set<String> permutations = new TreeSet<>();

        if (source.length() == 1) {
            permutations.add(source);
        } else {
            for(int i=0;i<source.length();i++){
                Set<String>temp=permutateString(source.substring(0, i)+source.substring(i+1));
                for (String string : temp) {
                    permutations.add(source.charAt(i)+string);
                }
            }
        }

        return permutations;
    }
}
