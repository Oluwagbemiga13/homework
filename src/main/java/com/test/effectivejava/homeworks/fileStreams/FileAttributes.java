package com.test.effectivejava.homeworks.fileStreams;

import lombok.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class FileAttributes {

    private String nameOfFile;
    private long sizeOfFile;

    @Override
    public String toString(){
        return this.getNameOfFile() + " " + this.getSizeOfFile() + " bytes";
    }

    /**
     * Used for Lombok builder in Stream
     * @param path to file
     * @return size of file
     */
    static long getSizeOfFile(Path path) {
         return  FileUtils.sizeOf(new File(path.toUri()));
    }

    /**
     *
     * @param path to file
     * @return Instance of FileAttributes.
     */
    static FileAttributes constructFileAttributes(Path path){
        return FileAttributes.builder()
                .nameOfFile(path.toFile().getName())
                .sizeOfFile(getSizeOfFile(path))
                .build();
    }

    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

}
