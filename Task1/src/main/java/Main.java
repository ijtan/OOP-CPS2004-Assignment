package Task1.src.main.java;

// import java.util.ArrayList;
// import java.util.List;
public class Main {
    public static void main(String args[]) {

        // String exampleImg = "TTTTTTTT\n" + "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n"
        //         + "FFFFTTTT\n" + "FFFFTTTF\n";

        
        // String exampleImg = imageProcessor.readfile("Task1/testCSV.csv");
        String exampleImg = imageProcessor.readfile("Task1/testTXT.txt");

        if (exampleImg == null) {
            System.err.println("Aborting due to error");
            return;
        }

        String image = imageProcessor.preprocess(exampleImg.replaceAll("\n", ""));
        if (image == null) {
            System.err.println("Aborting due to error");
            return;
        }
        
        qt tree = new qt(1);
        tree.init(1, image.toCharArray());

       

        System.out.println("\n");
        System.out.println("Pre optimization leaf count = " + tree.leafCount());
        System.out.println("Pre optimization parent node count = " + tree.nodeCount());
        
        System.out.println("\nOptimizing Tree...\n");
        tree.optimize();

        System.out.println("Post optimization leaf count = " + tree.leafCount());
        System.out.println("Post optimization parent node count = " + tree.nodeCount());
        
        System.out.println("\n");
        System.out.println(tree.print());

    }
}
