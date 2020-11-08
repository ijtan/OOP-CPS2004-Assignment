package Task1;

public class Main {
    public static void main(String args[]){ //T G
        String exampleImg = "TTTTTTTT\n"+   //F G
                            "TTTTTTTT\n"+
                            "TTTTFFFT\n"+
                            "TTTTTTFF\n"+
                            "FFFFTTFF\n"+
                            "FFFFTTFF\n"+
                            "FFFFTTTT\n"+
                            "FFFFTTTF\n";
    //     System.out.println("Starting!");
        String strImg[] = exampleImg.split("\n");
        char[][] img = new char[strImg.length][strImg[0].length()];
        for (int i = 0; i<strImg[0].length();i++) {
            img[i] = strImg[i].toCharArray();
        }

        point start = new point(0,0);
        point end = new point(img[0].length-1,img.length-1);
    //     quadtree qt = new quadtree(start, end, img);
    //     char ret[][] = new char[qt.end.x+1][qt.end.y+1];
    //     for(char[] cA : qt.print(ret)) {
    //         for (char c : cA) {
    //             System.out.print(c);
    //     }
    //     System.out.print('\n');
    //     }
    // }
    qt tree = new qt(start, end);
    tree.init(1,((int)Math.pow(end.x+1,2)));
    tree.assign(img);
    System.out.println("tree count = " + tree.size);
    tree.optimize();
    System.out.println("final tree count = "+tree.getSize());
    }
}