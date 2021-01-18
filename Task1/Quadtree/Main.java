package Quadtree;
import java.io.File;

public class Main {
    public static void main(String args[]) {
        testRunner.runTests();
        // String exampleImg = "TTTTTTTT\n" + "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n"
        //         + "FFFFTTTT\n" + "FFFFTTTF\n";

        
        // String exampleImg = imageProcessor.readfile("Task1/testCSV.csv");
        String path = new File("testTXT.txt").getAbsolutePath();
        String exampleImg = imageProcessor.readfile(path);

        assert exampleImg!=null : "Aborting due to error: example image cannot be loaded";

        String image = imageProcessor.preprocess(exampleImg.replaceAll("\n", ""));
        assert image!=null : "Aborting due to error: image could not be processed";
        
        qt tree = new qt(1);
        tree.init(1, image.toCharArray());

       
        System.out.println("\nPre optimization leaf count = " + tree.leafCount());
        System.out.println("Pre optimization parent node count = " + tree.nodeCount());
        
        System.out.println("\nOptimizing Tree...\n");
        tree.optimize();

        System.out.println("\nPost optimization leaf count = " + tree.leafCount());
        System.out.println("Post optimization parent node count = " + tree.nodeCount());
        
        System.out.println("Assigned Tree Representation: ");
        System.out.println(tree.print());

    }
}
