package com.test.effectivejava.homeworks.dogs;

import com.test.effectivejava.homeworks.dogs.Dog;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.test.effectivejava.homeworks.dogs.Dog.printDogsInfoFromList;
import static java.lang.Math.round;

public class DogMain {

    public static BigDecimal getTotalWeightOfDogs(List<Dog> listOfDogs){
        BigDecimal totalWeight = BigDecimal.ZERO;
        for (Dog dog : listOfDogs) {
            totalWeight = totalWeight.add(BigDecimal.valueOf(dog.getWeight()));
        }
        System.out.println("Total weight: " + totalWeight + "kg.");
        return totalWeight;
    }

    public static void main(String[] args) {

        Dog dog1 = new Dog.DogBuilder()
                .name("Bobina")
                .race("Sausage dog")
                .age(5)
                .weight(0.1)
                .build();

        Dog dog2 = new Dog.DogBuilder()
                .name("Bobik")
                .race("Greyhound")
                .age(6)
                .weight(0.2)
                .build();

        Dog dog3 = new Dog.DogBuilder()
                .name("Lassie")
                .race("Collie")
                .age(7)
                .weight(0.0)
                .build();

        List<Dog> smecka = Arrays.asList(dog1,dog2,dog3);

        printDogsInfoFromList(smecka);

        getTotalWeightOfDogs(smecka);

    }
}
