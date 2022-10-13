package com.test.effectivejava.homeworks.fileStreams;

import lombok.*;

import java.math.BigDecimal;
import java.net.URI;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FileAttributes {

    private String nameOfFile;
    private long sizeOfFile;


    public static void main(String[] args) {

        //FileAttributes fa = new FileAttributesBuilder().nameOfFile("1").sizeOfFile(BigDecimal.valueOf(1.0)).build();


    }


}
