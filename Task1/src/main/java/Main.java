package src.main.java;
import java.io.File;
public class Main {
    public static void main(String args[]) {

        // String exampleImg = "TTTTTTTT\n" + "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n"
        //         + "FFFFTTTT\n" + "FFFFTTTF\n";

        
        // String exampleImg = imageProcessor.readfile("Task1/testCSV.csv");
        String path = new File("testTXT.txt").getAbsolutePath();
        String exampleImg = imageProcessor.readfile(path);

        if (exampleImg == null) {
            System.err.println("Aborting due to error");
            return;
        }
        assert 1==2 : "Aborting due to error: example image cannot be loaded";

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
