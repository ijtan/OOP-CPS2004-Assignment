package Task1;

public class quadtree {
    point start, end;

    // False = Black
    // True = White
    boolean state;
    boolean isLeaf;

    // N
    // W E
    // S
    quadtree NW;
    quadtree NE;
    quadtree SW;
    quadtree SE;

    public quadtree(point start, point end, String[] img) {
        this.start = start;
        this.end = end;
        // System.out.println("I have been initalized; x,y x,y");
        // System.out.println(""+start.x+','+start.y+'\t'+end.x+','+end.y);

        // S##A###
        // # # #
        // # # #
        // C##M##B
        // # # #
        // # # #
        // ###D##E
        // estabilish mid point around which we will have 4 squares to edges
        point mid = new point(start.x + (end.x - start.x) / 2, start.y + (end.y - start.y) / 2);

        if (end.x == start.x && end.y == start.y) { // if current start/end are the same, we are a pixel, thus take data
                                                    // from the image
            this.isLeaf = true;
            // System.out.println("accessing point "+start.x+','+start.y);
            if (img[start.y].charAt(start.x) == 'T') {
                this.state = true;
                // System.out.println("true");
            } else if (img[start.y].charAt(start.x) == 'F') {
                this.state = false;
                // System.out.println("false");
            }

            this.NW = this.NE = this.SW = this.SE = null; // since we are a leaf we dont need children
        }

        else { // else we are in a quadtree which will hold 4 quadtress/pixels
            if ((mid.x == start.x && mid.y == start.y) || (mid.x == end.x && mid.y == end.y)) { // if we are 2*2 we can
                                                                                                // just intialise 4
                                                                                                // pixel trees
                this.NW = new quadtree(start, start, img);
                point tmp = new point(start.x + 1, start.y);
                this.NE = new quadtree(tmp, tmp, img);

                tmp = new point(start.x, start.y + 1);
                this.SW = new quadtree(tmp, tmp, img);

                this.SE = new quadtree(end, end, img);
            } else { // else we will initialise 4 quadtrees (we divide our area into 4 areas and
                     // assign each onbe accordingly)

                if (start.x < mid.x && start.y < mid.y) { // 
                    System.out.println("opening nw");
                }
                if (start.x > mid.x && start.y < mid.y) { // 
                    System.out.println("opening ne");
                }
                if (start.x < mid.x && start.y > mid.y) { // 
                    System.out.println("opening sw");
                }
                if (start.x > mid.x && start.y > mid.y) { // 
                    System.out.println("opening se");
                }

                point mid4 = new point(mid.x + 1, mid.y + 1);
                point A = new point(mid4.x, start.y);
                point B = new point(end.x, mid4.y);
                point C = new point(start.x, mid4.y);
                point D = new point(mid4.x, end.y);
                

                this.NW = new quadtree(start, mid, img);
                this.NE = new quadtree(A, B, img);
                this.SW = new quadtree(C, D, img);
                this.SE = new quadtree(mid4, end, img);
            }

            // absorption (check if 4 children are the same in which case delete the, and
            // take their value)
            if (NW.isLeaf && SW.isLeaf && NE.isLeaf && SE.isLeaf) {
                if (NW.state == SW.state == NE.state == SE.state) {
                    // if(start.x==0 && end.x == 3 && start.y==4)
                    System.out.println("Absorbing 4 points -> " + start.x + ',' + start.y + '\t' + end.x + ',' + end.y);
                    this.state = NW.state;
                    this.isLeaf = true;
                    this.NW = this.NE = this.SW = this.SE = null;
                }

            }
        }
    }

    public char[][] print(char[][] ret) {
        // char ret[][] = new char[end.x-start.x][end.y-start.y];
        quadtree quadrant[] = { NW, NE, SW, SE };
        for (quadtree q : quadrant) {

            for (int x = q.start.x; x <= q.end.x; x++) {
                for (int y = q.start.y; y <= q.end.y; y++) {

                    if (q.isLeaf)
                        ret[y][x] = q.stateToChar();
                    else {
                        point mid = new point(start.x + (end.x - start.x) / 2, start.y + (end.y - start.y) / 2);
                        if (x <= mid.x && y <= mid.y) { // NW
                            ret = q.print(ret);
                        }
                        if (x > mid.x && y < mid.y) { // NE
                            ret = q.print(ret);
                        }
                        if (x < mid.x && y > mid.y) { // SW
                            ret = q.print(ret);
                        }
                        if (x > mid.x && y > mid.y) { // SE
                            ret = q.print(ret);
                        }
                    }
                }

                // S##A###
                // # # #
                // C##M##B
                // # # #
                // ###D##E

            }
        }
        return ret;
    }

    char stateToChar() {
        if (state == true)
            return 'T';
        return 'F';
    }
}
