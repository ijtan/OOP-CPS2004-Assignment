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
                            "FFFFTTTF";

        String img[] = exampleImg.split("\n");
        point start = new point(0,0);
        point end = new point(img[0].length(),img.length);
        quadtree qt = new quadtree(start, end, img);
        for(char[] cA : qt.print()) {
            for (char c : cA) {
                System.out.print(c);
        }
        System.out.print('\n');
        }
    }
}