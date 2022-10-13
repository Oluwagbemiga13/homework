package com.test.effectivejava.homeworks.dogs;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    private String name;
    private String race;
    private int age;

    private double weight;

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }

    public static void printDogsInfoFromList(List<Dog> listOfDogs) {
        for (Dog d : listOfDogs) {
            System.out.println(d.toString());
        }
    }

    public static void main(String[] args) {

        Dog dog1 = new DogBuilder()
                .name("Bobina")
                .race("Sausage dog")
                .age(5)
                .weight(0.1)
                .build();

        Dog dog2 = new DogBuilder()
                .name("Bobik")
                .race("Greyhound")
                .age(6)
                .weight(0.2)
                .build();

        Dog dog3 = new DogBuilder()
                .name("Lassie")
                .race("Collie")
                .age(7)
                .weight(0.0)
                .build();

        List<Dog> smecka = Arrays.asList(dog1,dog2,dog3);

        printDogsInfoFromList(smecka);

        //System.out.println(getTotalWeight(smecka));

    }
}
