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



        tree.init(1, sideLength * sideLength, imgArray);
        System.out.println("\n\n");

        // tree.assign(imgArray);

        System.out.println("Diff checker");
        System.out.println("assigned");
        // for (int i = 1; i < 65; i++) {
            for(int i: tree.indeces){
            System.out.print(tree.getPixelByChar(i+1));
        }

        System.out.println();
        System.out.println("original");
        for (int i = 1; i <= imgArray.length; i++) 
            System.out.print(imgArray[i - 1]);
        

       

        System.out.println("\n\n");
        System.out.println("Assigned tree print");
        System.out.println(tree.print());
        System.out.println("\n\n");
        System.out.println("Pre optimization leaf count = " + tree.leafCount());
        System.out.println("Pre optimization node count = " + tree.nodeCount());
        tree.optimize();







        System.out.println("Diff checker2");
        System.out.println("assigned2");
        // for (int i = 1; i < 65; i++) {
        for (int i : tree.indeces) {
            System.out.print(tree.getPixelByChar(i + 1));
        }

        System.out.println();
        System.out.println("original2");
        for (int i = 1; i <= imgArray.length; i++)
            System.out.print(imgArray[i - 1]);
        System.out.println();

        System.out.println("Post optimization leaf count = " + tree.leafCount());
        System.out.println("Post optimization node count = " + tree.nodeCount());
        System.out.println("\n\n");
        System.out.println(tree.print());
        System.out.println("\n\n");

    }
}
