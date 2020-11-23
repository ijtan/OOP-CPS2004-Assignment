package Task1;

public class MainSplitQT {
    public static void main(String args[]) {
        String exampleImg = "TTTTTTTT\n" + "TTTTTTTT\n" + "TTTTFFFT\n" + "TTTTTTFF\n" + "FFFFTTFF\n" + "FFFFTTFF\n"
                + "FFFFTTTT\n" + "FFFFTTTF\n";
                
        // System.out.println("Starting!");
        char[] imgArray = exampleImg.replaceAll("\n", "").toCharArray();
        int sideLength = (int) Math.sqrt(imgArray.length);
        point start = new point(0, 0);
        point end = new point(8, 8);
        String[] img = exampleImg.split("\n");
        quadtree tree = new quadtree(start,end,img);
        char[][]ret = {{' '}};
        tree.print(ret);
    }
}
