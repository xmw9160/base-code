package com.xmw.java8.java8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * FlatMap test
 *
 * @author mingwei.xia
 * @date 2018/8/22 15:45
 * @since V1.0
 */
public class FlatMapExample {

    public static void main(String args[]) {
        List<Zoo> zooList = new ArrayList<>();
        Zoo nationalZoo = new Zoo("National");
        nationalZoo.add("Lion");
        nationalZoo.add("Tiger");
        nationalZoo.add("Peacock");
        nationalZoo.add("Gorilla");

        Zoo aCountyZoo = new Zoo("Wills County");
        aCountyZoo.add("Peacock");
        aCountyZoo.add("Camelion");

        zooList.add(nationalZoo);
        zooList.add(aCountyZoo);

        // to get the aggregate
        List<String> animalList = zooList.stream()
                .flatMap(element -> element.getAnimals().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(animalList);

        // to get the unique set
        Set<String> animalSet = zooList.stream()
                .flatMap(element -> element.getAnimals().stream())
                .collect(Collectors.toSet());
        System.out.println(animalSet);

    }

}

class Zoo {
    private String zooName;
    private Set<String> animals;

    public Zoo(String zooName) {
        this.zooName = zooName;
        this.animals = new HashSet<>();
    }

    public void add(String animal) {
        this.animals.add(animal);
    }

    public Set<String> getAnimals() {
        return animals;
    }
}

