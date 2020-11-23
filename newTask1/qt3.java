package newTask1;
import java.util.ArrayList;

public class qt3 {
    private boolean leaf;
    private qt3 NW,NE,SW,SE;
    private point start,end;

    public qt3(String Image){
        String[] split = Image.split("\n");
        int len = split.length; 
        System.out.println("length is "+len);
        point start = new point(0, 0);
        point end = new point(len-1, len-1);
    }

    public qt3(point start, point end, ArrayList<ArrayList<Character>> Image) {
        if(this.start.x == this.end.x && this.start.y == this.end.y){
            this.leaf = true;
            this.NW = this.NE = this.SE = this.SW = null;
            return;
        }
        char[] gay;
        gay.
        int xLength = start.x - end.x;
        int xLenChild = xLength/2;

        int yLength = start.y - end.y;
        int yLenChild = yLength/2;

        // qt[] childrens = new qt[4];
        point s = new point(start.x,start.y);
        point e = new point(start.x+xLenChild,start.y+yLenChild);
        this.NW = new qt3(s,e,Image);

        s = e;
        e = new point(end.x,end.y);
        this.SE = new qt3(s,e,Image);

        
        s = new point(start.x+xLenChild,end.x);
        e = new point(end.x, start.y+yLenChild);
        this.NW = new qt3(s, e, Image);


        s = new point(start.x, start.y+xLenChild);
        e = new point(start.x+xLenChild, end.x);
        this.NE = new qt3(s, e, Image);
    }
}
