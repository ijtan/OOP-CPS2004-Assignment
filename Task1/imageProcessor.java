package Task1;

import java.util.ArrayList;

public class imageProcessor {
    public static  ArrayList<Integer> indices;
    public static String print(qt QuadTree){
        String print = ""; 
        return print;       
    }

    public static String readCSV(String filename){
        String image = "";
        return image;
    }
    
    public static String readFile(String filename) {
        String image = "";
        return image;
    }

    public static String preprocess(String image) {
        image = image.strip().replaceAll("\n", "");
        int length = image.length();
        double sizeTest = Math.log(length) / Math.log(4); 
        if(!(sizeTest == Math.floor(sizeTest))){
            System.err.println("Image in not of size 4^n !!\nAborting...");
            return "";
        }
        imageProcessor.indices =  indexShifter(length);
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



