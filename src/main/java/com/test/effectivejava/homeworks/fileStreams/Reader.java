package com.test.effectivejava.homeworks.fileStreams;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * Class containing static methods for extracting FileAttributes from files.
 */
public class Reader {

    /**
     * Method returning instance of FileAttribute from Path
     * @param path to file
     * @return Instance of FileAttributes.
     */
    static FileAttributes constructFileAttributes(Path path){
        return FileAttributes.builder()
                .nameOfFile(path.toFile().getName())
                .sizeOfFile(getSizeOfFile(path))
                .build();
    }

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
                .map(Reader::constructFileAttributes)
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

    /**
     * Method for extracting names of FileAttributes from List
     * @param fileList - List of FileAttributes
     * @return List of names
     */
    static List<String> getNamesOfFiles(List<FileAttributes> fileList){
        return fileList.stream()
                .map(FileAttributes::toString)
                .toList();
    }

    /**
     * Method for finding the smallest sized FileAttribute from List
     * @param fileList - List of FileAttributes
     * @return the smallest sized FileAttribute
     */
    static Optional<FileAttributes> getSmallestSize (List <FileAttributes> fileList) {
        return fileList.stream()
                .min(Comparator.comparing(FileAttributes::getSizeOfFile));
    }

    /**
     * Method for finding the biggest sized FileAttribute from List
     * @param fileList - List of FileAttributes
     * @return the biggest sized FileAttribute
     */
    static Optional<FileAttributes> getBiggestSize (List <FileAttributes> fileList) {
        return fileList.stream()
                .max(Comparator.comparing(FileAttributes::getSizeOfFile));
    }

    /**
     * Method for determining size of all FileAttributes in List
     * @param fileList - List of FileAttributes
     * @return size of all FileAttributes
     */
    static long getSizeOfAllFiles (List <FileAttributes> fileList){
        return fileList.stream()
                .mapToLong(FileAttributes::getSizeOfFile)
                .sum();
    }

    /**
     * Method for printing info about FileAttributes
     * @param fileList - List of FileAttributes
     */
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

    /**
     * Method for getting size of file form Path
     * Used for Lombok builder in Stream
     * @param path to file
     * @return size of file
     */
    static long getSizeOfFile(Path path) {
        return  FileUtils.sizeOf(new File(path.toUri()));
    }

    /**
     * Method for getting extention of file
     * @param filename - name of file
     * @return extension without "."
     */
    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

}
