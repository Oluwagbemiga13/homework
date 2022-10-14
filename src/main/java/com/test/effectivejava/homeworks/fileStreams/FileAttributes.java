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

}
