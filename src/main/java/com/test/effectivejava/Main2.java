package com.test.effectivejava;

import com.test.effectivejava.pojo.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
        // equals vs. ==,  pozor na == u Stringu nebo například Integer
        // SPATNE!!!
        System.out.println("test" == "test"); // NEPOUZIVAT, vrati true
        System.out.println(new String("test") == new String("test")); // NEPOUZIVAT, vrati false
        System.out.println(new Integer(1) == new Integer(1)); // NEPOUZIVAT, vrati false

        // SPRAVNE => pomoci equals()
        System.out.println("test".equals("test")); // vrati true
        System.out.println(new String("test").equals(new String("test"))); // vrati true
        System.out.println(1 == 1); // u primitivnich datovych typu OK, vrati true
        System.out.println(new Integer(1).equals(new Integer(1))); // vrati true

        // K cemu je ==
        // 1. Zjisteni, jestli 2 primitivni datove typy obsahuji stejnou hodnotu
        // 2. Jestli 2 reference ukazuji na stejny objekt
        String s1 = new String("test");
        String s2 = s1;
        String s3 = new String("test");
        // mame: 1 objekt, ALE 2 reference na ten objekt

        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf(10);
        System.out.println(i1 == i2); // SPATNE, true
        System.out.println(i1.equals(i2)); // SPRAVNE, true

        Integer i3 = Integer.valueOf(10_000);
        Integer i4 = Integer.valueOf(10_000);
        System.out.println(i3 == i4); // SPATNE, false
        System.out.println(i3.equals(i4)); // SPRAVNE, true

        Person p1 = Person.builder()
                .firstName("Jirka")
                .build();
        Person p2 = Person.builder()
                .firstName("Jirka")
                .build();
        System.out.println(p1.equals(p2));

        // toString
        List<Person> people = List.of(p1, p2);
        for (Person person : people) {
            System.out.println(person);
        }

        // TOHLE NEE!!!
        ArrayList<String> list1 = new ArrayList<>();
        // TOHLE ANO!!!
        List<String> list2 = new ArrayList<>();

        printPeople(p1, p2, null, new Person());

//        isDead(null);


        System.out.println(0.1 + 0.2); // NEEEEEEE!!!
        BigDecimal b1 = BigDecimal.valueOf(0.1);
        BigDecimal b2 = BigDecimal.valueOf(0.2);
        System.out.println(b1.add(b2).doubleValue());
    }

    public static void printPeople(Person ... people) { // varargs (nahrada statickeho pole)
        for (Person person : people) {
            System.out.println(person);
        }
    }

    // Check parameters for validity
    public static boolean isDead(Person person) {
        Objects.requireNonNull(person);
        return ! person.isAlive();
    }

    public static List<String> getStrings(List<Person> persons) {
        if(persons == null) {
            return null;
        }
        if(persons.isEmpty()) {
//            return null; // SPATNE!!!
            return List.of(); // SPRAVNE! Return empty collections or arrays, not nulls
        }
        return persons.stream()
                .map(Person::getFirstName)
                .toList();
    }

}

enum Days {

    MONDAY(0), FRIDAY(5), SATURDAY(6);

    private int index;

    Days(int index) {
        this.index = index;
    }
}