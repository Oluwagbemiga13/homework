package com.test.effectivejava.homeworks.movies;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class MovieReader {

    public static Map <String, BigDecimal> getMoviesAsNameRatingMap(String fileName) throws IOException {

        Map <String, BigDecimal> nameRatingDecimalMap = new HashMap<>();

        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        while (sc.hasNext()) {

            String name = sc.nextLine();
            // if(!name.equals("MOVIE") && !name.equals("RATING")) - PROČ nefunguhe
            String [] tempArr = name.split("[,\\n]");

//            for (int i = 0; i < tempArr.length; i++){
//                System.out.println(i + ": " + tempArr [i]);
//            }
            nameRatingDecimalMap.put(tempArr[0], BigDecimal.valueOf(Double.parseDouble(tempArr[1])));
        }
        return nameRatingDecimalMap;
    }
    public static List <String> getMoviesNamesAsList (String fileName) throws FileNotFoundException {

        List <String> namesList = new ArrayList<>();
        // Scanner NE!
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            String name = sc.nextLine();
            String [] tempArr = name.split(",");
            namesList.add(tempArr[0]);
        }
        for (int i = 0; i < namesList.size(); i++){
            System.out.println("Movie at index " + i + ": " + namesList.get(i));
        }
        return namesList;
    }
    public static List <BigDecimal> getRatingsAsList(String fileName) throws FileNotFoundException {

        List <String> stringRatingList = new ArrayList<>();

        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            String name = sc.nextLine();
            String [] tempArr = name.split(",");
            stringRatingList.add(tempArr[1]);
        }
//        for (int i = 0; i < stringRatingList.size(); i++){
//            System.out.println("Rating at index " + i + ": " + stringRatingList.get(i));
//        }
        List <BigDecimal> decimalArrayList = new ArrayList<>();

        for (String s : stringRatingList){
            decimalArrayList.add(BigDecimal.valueOf(Double.valueOf(s)));
        }
        return decimalArrayList;
    }

    public static BigDecimal getAverageRating (String fileName, int precision) throws IOException {
        BigDecimal averageRating = BigDecimal.ZERO;
        BigDecimal totalRating = BigDecimal.ZERO;

        for(BigDecimal bigDecimal : getMoviesAsNameRatingMap(fileName).values()){
            totalRating = totalRating.add(bigDecimal);
        }
        //getMoviesAsNameRatingMap(fileName).keySet();

        averageRating = totalRating.divide(BigDecimal.valueOf(getMoviesAsNameRatingMap(fileName).size()));
        averageRating = averageRating.round(new MathContext(precision));

        System.out.println("Average rating: " + averageRating);
        return  averageRating;
    }
    public static BigDecimal getAverageRatingFromList (List <BigDecimal> decimalList, int precision){
        BigDecimal averageRating = BigDecimal.ZERO;
        BigDecimal totalRating = BigDecimal.ZERO;

        for(BigDecimal bigDecimal : decimalList){
            totalRating = totalRating.add(bigDecimal);
        }
        averageRating = totalRating.divide(BigDecimal.valueOf(decimalList.size()));
        averageRating = averageRating.round(new MathContext(precision));
        System.out.println("Average rating: " + averageRating);
        return averageRating;
    }
    public static String getMovieWithWorstRating (String fileName) throws IOException {

        List <BigDecimal> ratings = new ArrayList<>(getMoviesAsNameRatingMap(fileName).values());
        List <String> names = new ArrayList<String>(getMoviesAsNameRatingMap(fileName).keySet());

        int indexOfLowest = -1;
        BigDecimal lowestRating = null;

        for (BigDecimal decimal : ratings){
            if(lowestRating == null){
                lowestRating = decimal;
            }
            else{
                if(lowestRating.compareTo(decimal) > 0){
                    lowestRating = decimal;
                    indexOfLowest = ratings.indexOf(decimal);
                }
            }
        }
        System.out.println("Movie with lowest rating is: " + names.get(indexOfLowest) + " "
                + getMoviesAsNameRatingMap(fileName).get(names.get(indexOfLowest)));
        return names.get(indexOfLowest);
    }
    public static String getMovieWithBestRating (String fileName) throws IOException {

        List <BigDecimal> ratings = new ArrayList<>(getMoviesAsNameRatingMap(fileName).values());
        List <String> names = new ArrayList<String>(getMoviesAsNameRatingMap(fileName).keySet());

        int indexOfHighest = -1;
        BigDecimal highestRating = null;

        for (BigDecimal decimal : ratings){
            if(highestRating == null){
                highestRating = decimal;
            }
            else{
                if(highestRating.compareTo(decimal) == - 1){
                    highestRating = decimal;
                    indexOfHighest = ratings.indexOf(decimal);
                }
            }
        }
        System.out.println("Movie with highest rating is: " + names.get(indexOfHighest) + " "
                + getMoviesAsNameRatingMap(fileName).get(names.get(indexOfHighest)));
        return names.get(indexOfHighest);
    }



    public static void main(String[] args) throws IOException {
        //getMoviesAsNameRatingMap("movies.csv");

        getMoviesNamesAsList("movies.csv");

        //getRatingsAsList("movies.csv");

        //getAverageRating("movies.csv", 3);

        getAverageRatingFromList(getRatingsAsList ("movies.csv"), 3);

        getMovieWithWorstRating("movies.csv");

        getMovieWithBestRating ("movies.csv");
    }

}
