package Task1;

// import java.util.ArrayList;
// import java.util.List;

public class Main {
    public static void main(String args[]) { // T G
        String exampleImg = "TTTTTTTT\n" + // F G
                "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n" + "FFFFTTTT\n" + "FFFFTTTF\n";
        // System.out.println("Starting!");
        char[] imgArray = exampleImg.replaceAll("\n", "").toCharArray();
        int sideLength = (int) Math.sqrt(imgArray.length);


        qt tree = new qt(1);
        tree.init(1, sideLength * sideLength);
        tree.assign(imgArray);


        for (int i = 1; i <= imgArray.length; i++) {
            System.out.print(tree.getPixelByChar(i));
        }
        System.out.println();
        for (int i = 1; i <= imgArray.length; i++) {
            System.out.print(imgArray[i - 1]);
        }
        
        System.out.println("\n\n");
        System.out.println(tree.print());
        System.out.println("\n\n");
        System.out.println("Pre optimization leaf count = " + tree.leafCount());
        System.out.println("Pre optimization node count = " + tree.nodeCount());
        tree.optimize();
        System.out.println("Post optimization leaf count = " + tree.leafCount());
        System.out.println("Post optimization node count = " + tree.nodeCount());
        System.out.println("\n\n");
        System.out.println(tree.print());
        System.out.println("\n\n");
    }
}