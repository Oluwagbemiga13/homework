package com.test.effectivejava.homeworks.fileStreams;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.test.effectivejava.homeworks.fileStreams.FileAttributes.getExtension;

public class Reader {

    /**
     * Method for extracting instances of FileAttributes as List. Goes through whole directory.
     * @param dirPath - Path of main directory.
     * @param extention - extention you want to include in list.
     * @return List of FileAttributes
     * @throws IOException an I/O error is thorwn when accesing the starting file.
     */
    static List<FileAttributes> getAllFilesAsFileAttributesList(Path dirPath, String extention) throws IOException {
        try (Stream<Path> pathStream = Files.walk(dirPath)){
        return pathStream
                .filter(path -> getExtension(path.toString()).equals(extention))
                .map(FileAttributes::constructFileAttributes)
                .toList();
        }
    }

    /**
     * Method sorting List of FileAttributes by their size
     * from smallest to biggest
     * @param fileAttributesList - List that needs to be sorted
     * @return sorted List of fileAttributes
     */
    public static List<FileAttributes> sortBySize(List<FileAttributes> fileAttributesList){
        return fileAttributesList
                .stream()
                .sorted(Comparator.comparingLong(FileAttributes::getSizeOfFile))
                .toList();
    }

    static List<String> getNamesOfFiles(List<FileAttributes> fileList){
        return fileList.stream()
                .map(FileAttributes::toString)
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

        if(!fileList.isEmpty()) {
            getNamesOfFiles(fileList)
                .forEach(System.out::println);

            System.out.println("\nSmallest: " + getSmallestSize(fileList).get());
            System.out.println("Biggest: " + getBiggestSize(fileList).get()) ;

            System.out.println("\nSize of all files: " + getSizeOfAllFiles(fileList));
            }
        else {
            System.out.println("There are no files!");
        }
    }



    public static void main(String[] args) throws IOException {

        Path dirPath = Paths.get(".");

        List <FileAttributes> testFA = getAllFilesAsFileAttributesList(dirPath, "java");

        testFA = sortBySize(testFA);

        printAllInfo(testFA);
        
    }
}
