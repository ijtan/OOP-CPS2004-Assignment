package Task1;

// import java.util.ArrayList;
// import java.util.List;

public class Main {
    public static void main(String args[]) {
        String exampleImg = "TTTTTTTT\n" + "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n"
                + "FFFFTTTT\n" + "FFFFTTTF\n";
                
        // System.out.println("Starting!");
        char[] imgArray = exampleImg.replaceAll("\n", "").toCharArray();
        int sideLength = (int) Math.sqrt(imgArray.length);

        qt tree = new qt(1);
        tree.init(1, sideLength * sideLength);
        tree.assign(imgArray);

        System.out.println("Diff checker");
        System.out.println("assigned");
        for (int i : tree.indexShifter()) {
            System.out.print(tree.getPixelByChar(i));
        }
        
        System.out.println();
        System.out.println("original");
        for (int i = 1; i <= imgArray.length; i++) {
            System.out.print(imgArray[i - 1]);
        }

        System.out.println("\n\n");
        System.out.println("test indexes");
        
        int j =0;
        for (int i : tree.indexShifter()) {
            System.out.print(i+"\t");
            j++;

            if(j==8){
                System.out.println();
                j = 0;
            }
        }


        System.out.println("testing assigned indexes::::");
        System.out.println(tree.printIndex());


        System.out.println("recurse print");
        System.out.println(tree.recursePrint());

        System.out.println("\n\n");
        System.out.println("Assigned tree print");
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