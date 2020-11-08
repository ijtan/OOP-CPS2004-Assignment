package Task1;

public class quadtree {
    point start, end;

    //False = Black
    //True = White
    boolean state;
    boolean isLeaf;

    //      N
    //  W       E
    //      S
    quadtree NW;
    quadtree NE;
    quadtree SW;
    quadtree SE;

    public quadtree(point start, point end, String[] img){
        this.start = start;
        this.end = end;
        System.out.println("I have been initalized; x,y x,y");
        System.out.println(' '+start.x+','+start.y+'\t'+end.x+','+end.y);
        //S##A###
        //#  #  #
        //C##M##B
        //#  #  #
        //###D##E
        point mid = new point(start.x + (end.x - start.x) / 2, start.y +(end.y - start.y) / 2);
        
        point A = new point(mid.x, start.y);
        point B = new point(end.x, mid.y);
        point C = new point(start.x, mid.y);
        point D = new point(end.y, mid.x);

        if(end.x-start.x == 1 && end.y - start.y == 1){
            this.isLeaf = true;
            if(img[start.y].charAt(start.x) == 'T')
                this.state = true;
            else  if(img[start.y].charAt(start.x) == 'F')
                this.state = false;

            this.NW = this.NE = this.SW = this.SE = null;
        }else{
            this.NW = new quadtree(start, mid, img);
            this.NE = new quadtree(A, B, img);
            this.SW = new quadtree(C, D, img);
            this.SE = new quadtree(mid, end, img);

            if (NW.isLeaf && SW.isLeaf && NE.isLeaf && SE.isLeaf) {
                if (NW.state == SW.state == NE.state == SE.state) {
                    this.state = NW.state;
                    this.isLeaf = true;
                    this.NW = this.NE = this.SW = this.SE = null;
                }

            }
        }
    }

    public char[][] print(char[][] ret){
        // char ret[][] = new char[end.x-start.x][end.y-start.y];
        quadtree quadrant[] = {NW,NE,SW,SE};
        for (quadtree q : quadrant) {
            if (q.isLeaf) {
                for (int x = 0; x < q.end.x - q.start.x; x++) {
                    for (int y = 0; y < q.end.y - q.start.y; y++) {
                        ret[x][y] = q.stateToChar();
                    }
                }
            } else {
                point mid = new point(start.x + (end.x - start.x) / 2, start.y + (end.y - start.y) / 2);
                //S##A###
                //#  #  #
                //C##M##B
                //#  #  #
                //###D##E
                for (int x = 0; x < q.end.x - q.start.x; x++) {
                    for (int y = 0; y < q.end.y - q.start.y; y++) {
                        if(x<mid.x && y>mid.y){ //NW
                            ret = q.print(ret);
                        }
                        if (x > mid.x && y > mid.y) { //NE
                            ret = q.print(ret);
                        }
                        if (x < mid.x && y < mid.y) { //SW
                            ret = q.print(ret);
                        }
                        if (x > mid.x && y < mid.y) { //SE
                            ret = q.print(ret);
                        }
                    }
                }

            } 
        }
        return ret;
    }
    char stateToChar(){
        if(state==true)
            return 'T';
        return 'F';
    }
}
