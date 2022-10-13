package com.test.effectivejava.homeworks.fileStreams;

import lombok.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//import static com.test.effectivejava.homeworks.fileStreams.FileAttributes.isValidPath;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reader {

    private Charset charset;
    private List<File> fileList;
    private List<Path> pathList;



    static List<FileAttributes> getAllFilesAsFileAttributesList(Path dirPath, String suffix) throws IOException {
        try (Stream<Path> pathStream = Files.walk(dirPath)){
        return pathStream
                .filter(path -> getExtensionByApacheCommonLib(path.toString()).equals(suffix))
                .map(FileAttributes::getFileAttributes)
                .toList();
        }
    }

    static List<String> getNamesOfFiles(List<FileAttributes> fileList){
        return fileList.stream()
                .map(FileAttributes::getNameOfFile)
                .toList();
    }

    static Optional<FileAttributes> getSmallestSize (List <FileAttributes> fileList) {
        return fileList.stream()
                .min(Comparator.comparing(FileAttributes::getSizeOfFile));
    }

    static Optional<FileAttributes> getBiggestSize (List <FileAttributes> fileList) {
        return fileList.stream()
                .max(Comparator.comparing(FileAttributes::getSizeOfFile));
    }

    static long getSizeOfAllFiles (List <FileAttributes> fileList){
        return fileList.stream()
                .mapToLong(FileAttributes::getSizeOfFile)
                .sum();
    }

    static void printAllInfo(List <FileAttributes> fileList) {
        Optional<FileAttributes> smallest = getSmallestSize(fileList);
        Optional<FileAttributes> biggest = getBiggestSize(fileList);

        if(!fileList.isEmpty()) {
            System.out.println("\nALL FILES:");

            getNamesOfFiles(fileList)
                .stream()
                .forEach(s -> System.out.println(s));

            System.out.println("\nSmallest: " + smallest.get().getNameOfFile() + " size: " + smallest.get().getSizeOfFile());
            System.out.println("Biggest: " + biggest.get().getNameOfFile() + " size: " + biggest.get().getSizeOfFile());

            System.out.println("\nSize of all files: " + getSizeOfAllFiles(fileList));
            }
    }

    public static String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public static void main(String[] args) throws IOException {

        Path dirPath = Paths.get
                ("C:\\Users\\danie\\Documents\\NetBeansProjects\\effective-java-main");

        List <FileAttributes> testFA = getAllFilesAsFileAttributesList(dirPath, "java");

        printAllInfo(testFA);



    }
}
