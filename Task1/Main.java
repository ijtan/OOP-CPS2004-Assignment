package Task1;

// import java.util.ArrayList;
// import java.util.List;

public class Main {
    public static void main(String args[]) {


        String exampleImg = "TTTTTTTT\n" + "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n"
                + "FFFFTTTT\n" + "FFFFTTTF\n";

        
        String image = imageProcessor.preprocess(exampleImg.replaceAll("\n", ""));
        qt tree = new qt(1);
        tree.init(1, image.toCharArray());

        System.out.println("Diff checker");
        System.out.println("assigned");
        // for (int i = 1; i < 65; i++) {
            for(int i = 1; i <= image.length(); i++){
                System.out.print(tree.getPixelByChar(i));
        }

        System.out.println();
        System.out.println("original");
        for (int i = 1; i <= exampleImg.length(); i++) 
            System.out.print(exampleImg.charAt(i - 1));
        

       

        System.out.println("\n\n");
        // System.out.println("Assigned tree print");
        // System.out.println(tree.print());
        // System.out.println("\n\n");
        System.out.println("Pre optimization leaf count = " + tree.leafCount());
        System.out.println("Pre optimization node count = " + tree.nodeCount());
        
        System.out.println("Optimizing Tree...");
        tree.optimize();

        System.out.println("Post optimization leaf count = " + tree.leafCount());
        System.out.println("Post optimization node count = " + tree.nodeCount());
        
        System.out.println("\n\n");
        System.out.println(tree.print());
        System.out.println("\n\n");

    }
}
