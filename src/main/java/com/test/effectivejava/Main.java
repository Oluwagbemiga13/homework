package com.test.effectivejava;

import com.test.effectivejava.pojo.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Static factory methods instead of constructors
        Boolean value1 = Boolean.valueOf("true"); // ANO
        Boolean value2 = new Boolean("true"); // NEE

        List<Integer> integers1 = List.of(1, 2, 3); // ANO
        List<Integer> integers2 = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(1,2,3))); // NEE

        // Builder
        Person person1 = new Person();
        person1.setFirstName("Jirka");
        person1.setLastName("Pinkas");
        person1.setAlive(true);

        Person person2 = Person.builder()
                .firstName("Jirka")
                .lastName("Pinkas")
                .build();

        // Avoid creating unnecessary objects
        String s = new String("bikini"); // DON'T DO THIS!

        // Prefer try-with-resources to try-finally
        // STARE RESENI
        {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = Files.newBufferedReader(Path.of("pom.xml"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        // NOVE RESENI: try-with-resources (od Java 7)
        // DO try () patri objekt implementujici AutoCloseable interface
        {
            try(BufferedReader bufferedReader = Files.newBufferedReader(Path.of("pom.xml"))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
