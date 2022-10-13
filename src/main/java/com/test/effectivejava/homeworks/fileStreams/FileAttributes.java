package com.test.effectivejava.homeworks.fileStreams;

import lombok.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;

import static com.test.effectivejava.homeworks.fileStreams.Reader.getExtensionByApacheCommonLib;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FileAttributes {

    private String nameOfFile;
    private long sizeOfFile;

    static FileAttributes constructFile(String nameOfFile, long sizeOfFile){
        return new FileAttributes(nameOfFile,sizeOfFile);
    }

    static long getSizeOfFileFromInstance(Path path) {
        long l = FileUtils.sizeOf(new File(path.toUri()));

        return l;
    }

    static FileAttributes getFileAttributes(Path path){
        return FileAttributes.builder()
                .nameOfFile(path.toFile().getName())
                .sizeOfFile(getSizeOfFileFromInstance(path))
                .build();
    }

//    static boolean isValidPath(String suffix, Path path){
//        getExtensionByApacheCommonLib(path.toString()).equals(suffix);
//    }
}
