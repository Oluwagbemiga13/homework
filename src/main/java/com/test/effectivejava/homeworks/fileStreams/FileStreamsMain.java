package com.test.effectivejava.homeworks.fileStreams;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.test.effectivejava.homeworks.fileStreams.Reader.*;

public class FileStreamsMain {
    public static void main(String[] args) throws IOException {

        Path dirPath = Paths.get(".");

        List<FileAttributes> testFA = getAllFilesAsFileAttributesList(dirPath, "java");

        testFA = sortBySize(testFA);

        printAllInfo(testFA);

    }
}
