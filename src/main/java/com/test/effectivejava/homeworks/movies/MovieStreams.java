package com.test.effectivejava.homeworks.movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class MovieStreams {

    public static List <String> getLinesAsRawString(String filename){
        List <String> rawStrings = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            bufferedReader.lines()
                    .skip(1)
                    .forEach(rawStrings::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rawStrings;
    }

    public static List<BigDecimal> getRowFromDataAsBigDecimalList(String filename, int row){
        List <String> decimalStringList = new ArrayList<>();

        // IDK how to write it as Stream ???
        for (String s: getLinesAsRawString(filename)) {
            String [] tempArr  = s.split(",");
            decimalStringList.add(tempArr[row]);
        }
        return decimalStringList
                .stream()
                .mapToDouble(value -> Double.valueOf(value))
                .mapToObj(e -> BigDecimal.valueOf(e))
                .toList();
    }

    public static List<String> getRowsFromDataAsString(String filename, int row){
        List <String> stringList = new ArrayList<>();
        //
        for (String s: getLinesAsRawString(filename)) {
            String [] tempArr  = s.split(",");
            stringList.add(tempArr[row]);
        }
        return stringList
                .stream()
                .toList();
    }

    public static BigDecimal getHigestRatingAsBigDecimal(String filename){
        OptionalDouble highest  = getRowFromDataAsBigDecimalList(filename, 1)
                .stream()
                .mapToDouble(e -> Double.parseDouble(e.toString()))
                .max();

        return BigDecimal.valueOf(highest.getAsDouble());
    }

    public static BigDecimal getLowestRatingAsBigDecimal(String filename){
        OptionalDouble lowest  = getRowFromDataAsBigDecimalList(filename, 1)
                .stream()
                .mapToDouble(e -> Double.parseDouble(e.toString()))
                .min();

        return BigDecimal.valueOf(lowest.getAsDouble());
    }

    public static String getLowestToString (String filename){
        BigDecimal lowest = getLowestRatingAsBigDecimal(filename);

        int indexOfLowestElement = getRowFromDataAsBigDecimalList(filename,1).indexOf(lowest);

        return "Lowest rating: " + getRowsFromDataAsString(filename,0).get(indexOfLowestElement) + " " + lowest;
    }

    public static String getHighestToString (String filename){
        BigDecimal highest = getHigestRatingAsBigDecimal(filename);

        int index = getRowFromDataAsBigDecimalList(filename,1).indexOf(highest);

        return "Highest rating: " + getRowsFromDataAsString(filename,0).get(index) + " " + highest;
    }

    public static void main(String[] args) {

        getRowsFromDataAsString("movies.csv",0)
                .stream()
                .forEach(System.out::println);

        System.out.println(getLowestToString("movies.csv"));

        System.out.println(getHighestToString("movies.csv"));
    }

}
