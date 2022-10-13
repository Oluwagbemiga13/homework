package com.test.effectivejava.pojo;

import lombok.*;
@Data
//@ToString
//@EqualsAndHashCode
//@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String firstName;

    private String lastName;

    private boolean alive;

}
