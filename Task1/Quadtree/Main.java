package Quadtree;
import java.io.File;

public class Main {
    public static void main(String args[]) {
        tests.runTests();

        if(args.length < 1) {
        System.err.println("Error: no input file passed as argument");
	    System.exit(1);
    }
   
    // Scanner reader = new Scanner(new FileInputStream(args[0]));
        String path = new File(args[0]).getAbsolutePath();
        String exampleImg = imageProcessor.readfile(path);

        assert exampleImg!=null : "Aborting due to error: example image cannot be loaded; Is filename correct?";

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
