package Quadtree;
import java.io.File;

public class Main {
    public static void main(String args[]) {
        tests.runTests();

        if(args.length < 1) {
        System.err.println("Error: no input file passed as argument");
	    System.exit(1);
    }
        String path = new File(args[0]).getAbsolutePath();
        String fileData = imageProcessor.readfile(path);

        assert fileData!=null : "Aborting due to error: example image cannot be loaded; Is filename correct?";
        String image = imageProcessor.preprocess(fileData);
        assert image!=null : "Aborting due to error: image could not be processed";
        
        qt tree = new qt();
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
