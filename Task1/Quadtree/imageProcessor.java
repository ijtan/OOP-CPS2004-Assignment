package Quadtree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class imageProcessor {
    public static ArrayList<Integer> indices;

    public static String print(qt QuadTree) {
        String print = "";
        return print;
    }

    private static String readCSV(String filename) {

        List<Integer> reads = new ArrayList<Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 2)
                    System.err.println("Error, CSV file might be incorrect");

                int x = Integer.parseInt(values[0]);
                int y = Integer.parseInt(values[1]);

                reads.add(x);
                reads.add(y);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading file: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
            return null;
        }

        
        ArrayList<Integer> whites = new ArrayList<>();
        

        int minSideSize = Collections.max(reads);      

        int size = (int) Math.pow(4, 1);
        
        for (int i = 1; Math.sqrt(size) < minSideSize; i++)
            size = (int) Math.pow(4, i);



        System.out.println("Defaulting to minimum size which holds all values as: " + size);

        int sideLength = (int) Math.sqrt(size);
        for (int i = 0; i < reads.size(); i += 2)
            whites.add(coordinateToLinear(sideLength, reads.get(i), reads.get(i + 1)));
        String image = "";

        for (int i = 0; i < size; i++) {
            if (whites.contains(i))
                image += 'T';
            else
                image += 'F';
        }
        System.out.println("got image: " + image);
        return image;
    }

    private static int coordinateToLinear(int sideLength, int x, int y) {
        return (y - 1) * sideLength + (x-1);
    }

    private static String readTxt(String file) {
        Path filename = Path.of(file);
        String image = "";
        try {
            image = Files.readString(filename);
        } catch (IOException e) {
            System.err.println("error while reading the txt file: " + e.getMessage());
            return null;
        }
        if (!image.contains("F") || !image.contains("T") || image.contains(",")) {
            System.err.println("Wrong format detected, aborting!");
            return null;
        }
        return image;
    }

    public static String readfile(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1);

        if (extension.equals("csv"))
            return readCSV(filename);
        if (extension.equals("txt"))
            return readTxt(filename);

        System.err.println("File extension not supported! only csv and txt file can be read!");
        return null;
    }

    public static String preprocess(String image) {
        image = image.strip().replaceAll("\n", "");
        int length = image.length();
        double sizeTest = Math.log(length) / Math.log(4);
        if (!(sizeTest == Math.floor(sizeTest))) {
            System.err.println("Image in not of size 4^n !!\nAborting...");
            return null;
        }
        imageProcessor.indices = indexShifter(length);
        char[] formattedImage = new char[length];
        for (int n = 0; n < length; n++)
            formattedImage[indices.get(n)] = image.charAt(n);
        return new String(formattedImage);
    }

    public static ArrayList<Integer> indexShifter(int current) {
        ArrayList<Integer> prevIndeces = new ArrayList<Integer>();
        if (current <= 4) {
            prevIndeces.add(0);
            prevIndeces.add(1);
            prevIndeces.add(2);
            prevIndeces.add(3);
            return prevIndeces;
        }
        prevIndeces = indexShifter(current / 4);

        double size = (int) Math.sqrt(current) / 2;

        ArrayList<Integer> indexes = new ArrayList<Integer>();

        for (int i = 0; i < size; i += 1) { // how many y axis (*2)
            for (int j = 0; j < size; j++) {
                int now = 4 * prevIndeces.get(j) + 8 * prevIndeces.get(i);
                indexes.add(now + 0);
                indexes.add(now + 1);
            }
            for (int j = 0; j < size; j++) {
                int now = 4 * prevIndeces.get(j) + 8 * prevIndeces.get(i);
                indexes.add(now + 2);
                indexes.add(now + 3);
            }
        }
        return indexes;
    }
}
